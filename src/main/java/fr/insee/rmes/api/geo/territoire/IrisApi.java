package fr.insee.rmes.api.geo.territoire;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.rmes.api.geo.AbstractGeoApi;
import fr.insee.rmes.api.geo.ConstGeoApi;
import fr.insee.rmes.modeles.geo.territoire.Commune;
import fr.insee.rmes.modeles.geo.territoire.Iris;
import fr.insee.rmes.modeles.geo.territoire.PseudoIris;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoires.Territoires;
import fr.insee.rmes.queries.geo.GeoQueries;
import fr.insee.rmes.utils.CSVUtils;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.ResponseUtils;
import fr.insee.rmes.utils.SparqlUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Collections;
import java.util.List;


@Path(ConstGeoApi.PATH_GEO)
@Tag(name = ConstGeoApi.TAG_NAME, description = ConstGeoApi.TAG_DESCRIPTION)
public class IrisApi extends AbstractGeoApi {
    private static final String CODE_PATTERN = "/{code:}";
    private static final String LITTERAL_PARAMETER_TYPE_DESCRIPTION = "Filtre sur le type de territoire renvoyé.";
    private static final String LITTERAL_PARAMETER_NAME_DESCRIPTION = "Filtre sur le nom des communes renvoyées" ;

    private static final String LITTERAL_RESPONSE_DESCRIPTION = "Iris";
    private static final String LITTERAL_ID_OPERATION = "getcogiris";
    private static final String LITTERAL_CODE_EXAMPLE = "010040101";

    private static final String LITTERAL_CODE_HISTORY_EXAMPLE = "010040101";
    private static final String LITTERAL_PARAMETER_DATE_DESCRIPTION =
            "Filtre pour renvoyer l'Iris active à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')";
    private static final String LITTERAL_OPERATION_SUMMARY =
            "Informations sur un Iris identifié par son code (neuf chiffres pour la métropole ou 2A/2B plus 7 chiffres pour la Corse)";

    private static final String LITTERAL_DATE_EXAMPLE = "2020-01-01";

    public IrisApi() {
        // Constructeur par défaut
    }

    protected IrisApi(SparqlUtils sparqlUtils, CSVUtils csvUtils, ResponseUtils responseUtils) {
        super(sparqlUtils, csvUtils, responseUtils);
    }
    @Path(ConstGeoApi.PATH_IRIS + CODE_PATTERN)
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(operationId = LITTERAL_ID_OPERATION, summary = LITTERAL_OPERATION_SUMMARY, responses = {
            @ApiResponse(
                    content = @Content(schema = @Schema(implementation = Iris.class)),
                    description = LITTERAL_RESPONSE_DESCRIPTION)

    })
    public Response getByCode(
            @Parameter(
                    description = ConstGeoApi.PATTERN_IRIS_DESCRIPTION,
                    required = true,
                    schema = @Schema(
                            pattern = ConstGeoApi.PATTERN_IRIS,
                            type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = LITTERAL_PARAMETER_DATE_DESCRIPTION,
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) String date) {

        if (!code.matches(ConstGeoApi.PATTERN_IRIS)) {
            String errorMessage = ConstGeoApi.ERREUR_PATTERN;
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorMessage)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }

        if (!this.verifyParameterDateIsRightWithoutHistory(date)) {
            return this.generateBadRequestResponse();
        } else {
            String codeCommune = code.substring(0, 5);
            boolean hasIrisDescendant = false;


            String sparqlQuery = "SELECT DISTINCT ?type\n"
                    + "FROM <http://rdf.insee.fr/graphes/geo/cog>\n"
                    + "WHERE {\n"
                    + "    ?origine a igeo:Commune ;\n"
                    + "    igeo:codeINSEE '" + codeCommune + "' .\n"
                    + "    ?uri igeo:subdivisionDirecteDe+ ?origine .\n"
                    + "    ?uri a ?type .\n"
                    + "}\n"
                    + "ORDER BY ?type";

            List<String> resultTypes = Collections.singletonList(sparqlUtils.executeSparqlQuery(sparqlQuery));

            for (String type : resultTypes) {
                if (type.endsWith("#Iris\r\n")) {
                    hasIrisDescendant = true;
                    break;
                }
            }

            Territoire territoire;
            if (code.endsWith("0000") && hasIrisDescendant) {
                return Response.status(Response.Status.NOT_FOUND).entity("").build();
            }
            else if (hasIrisDescendant) {
                territoire = new Iris(code);
                return this.generateResponseATerritoireByCode(
                        sparqlUtils.executeSparqlQuery(
                                GeoQueries.getIrisByCodeAndDate(code, this.formatValidParameterDateIfIsNull(date))),
                        header,
                        territoire);
            } else if (code.matches("^(?=.{9}$)(?!.*0000$).*$") && !hasIrisDescendant ) {
                return Response.status(Response.Status.NOT_FOUND).entity("").build();
            } else {
                territoire = new Commune(code);
                try {
                    Response responseInitial = this.generateResponseATerritoireByCode(
                            sparqlUtils.executeSparqlQuery(
                                    GeoQueries.getIrisByCodeAndDate(code, this.formatValidParameterDateIfIsNull(date))),
                            header,
                            territoire);

                    PseudoIris territoireFinal;

                    if (header.contains(MediaType.APPLICATION_JSON)) {
                        territoireFinal = new PseudoIris(territoire.getCode(), territoire.getUri(), territoire.getIntitule(),
                                territoire.getType(), territoire.getDateCreation(), territoire.getDateSuppression(),
                                territoire.getIntituleSansArticle());
                        territoireFinal.setCode(code);
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode Node = objectMapper.valueToTree(territoireFinal);
                        String jsonModifie = objectMapper.writeValueAsString(Node);
                        return Response.ok(jsonModifie, MediaType.APPLICATION_JSON_TYPE).build();

                    } else if (header.contains(MediaType.APPLICATION_XML)) {
                        territoireFinal = new PseudoIris(territoire.getCode(), territoire.getUri(), territoire.getIntitule(),
                                territoire.getType(), territoire.getDateCreation(), territoire.getDateSuppression(),
                                territoire.getIntituleSansArticle());
                        JAXBContext jaxbContext = JAXBContext.newInstance(PseudoIris.class);
                        territoireFinal.setCode(code);
                        Marshaller marshaller = jaxbContext.createMarshaller();
                        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
                        StringWriter writer = new StringWriter();
                        marshaller.marshal(territoireFinal, writer);
                        return Response.ok(writer.toString(), MediaType.APPLICATION_XML_TYPE).build();
                    } else {
                        return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                }
            }
        }
    }
    @Path(ConstGeoApi.PATH_LISTE_IRIS)
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
            operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_LISTE,
            summary = "Informations sur toutes les iris actifs à la date donnée. Par défaut, c’est la date courante.",
            responses = {
                    @ApiResponse(
                            content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
                            description = LITTERAL_RESPONSE_DESCRIPTION)
            })
    public Response getListe(
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = "Filtre pour renvoyer les Iris ou faux-Iris à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')" + LITTERAL_PARAMETER_DATE_WITH_HISTORY,
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) String date,
            @Parameter(description = "les Iris (et pseudo-iris) des collectivités d'outre-mer",
                    required = false,
                    schema = @Schema(type = Constants.TYPE_BOOLEAN, allowableValues = {"true","false"},example="false", defaultValue = "false"))
            @QueryParam(
                    value = Constants.PARAMETER_STRING) Boolean com
    ) {

        if ( ! this.verifyParameterDateIsRightWithHistory(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                    .generateResponseListOfTerritoire(
                            sparqlUtils
                                    .executeSparqlQuery(GeoQueries.getListIris(this.formatValidParameterDateIfIsNull(date),this.formatValidParameterBooleanIfIsNull(com))),
                            header,
                            Territoires.class,
                            Territoire.class);
        }
    }


    @Path(ConstGeoApi.PATH_IRIS + CODE_PATTERN + ConstGeoApi.PATH_ASCENDANT)
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
            operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_ASCENDANTS,
            summary = "Informations concernant les territoires qui contiennent l'Iris",
            responses = {
                    @ApiResponse(
                            content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
                            description = LITTERAL_RESPONSE_DESCRIPTION)
            })
    public Response getAscendants(
            @Parameter(
                    description = ConstGeoApi.PATTERN_IRIS_DESCRIPTION,
                    required = true,
                    schema = @Schema(
                            pattern = ConstGeoApi.PATTERN_IRIS,
                            type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = "Filtre pour renvoyer les territoires contenant l'iris actif à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')",
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) String date,
            @Parameter(
                    description = LITTERAL_PARAMETER_TYPE_DESCRIPTION,
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING)) @QueryParam(
                    value = Constants.PARAMETER_TYPE) String typeTerritoire) {

        if ( ! this.verifyParametersTypeAndDateAreValid(typeTerritoire, date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                    .generateResponseListOfTerritoire(
                            sparqlUtils
                                    .executeSparqlQuery(
                                            GeoQueries
                                                    .getAscendantsIris(
                                                            code,
                                                            this.formatValidParameterDateIfIsNull(date),
                                                            this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire))),
                            header,
                            Territoires.class,
                            Territoire.class);
        }
    }

}
