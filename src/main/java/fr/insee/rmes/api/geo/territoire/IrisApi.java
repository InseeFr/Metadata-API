package fr.insee.rmes.api.geo.territoire;

import fr.insee.rmes.api.geo.AbstractGeoApi;
import fr.insee.rmes.api.geo.ConstGeoApi;
import fr.insee.rmes.modeles.geo.territoire.CodeIris;
import fr.insee.rmes.modeles.geo.territoire.Iris;
import fr.insee.rmes.modeles.geo.territoire.PseudoIris;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoires.Territoires;
import fr.insee.rmes.queries.geo.GeoQueries;
import fr.insee.rmes.utils.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path(ConstGeoApi.PATH_GEO)
@Tag(name = ConstGeoApi.TAG_NAME, description = ConstGeoApi.TAG_DESCRIPTION)
public class IrisApi extends AbstractGeoApi {
    private static final String CODE_PATTERN = "/{code:}";
    private static final String LITTERAL_PARAMETER_TYPE_DESCRIPTION = "Filtre sur le type de territoire renvoyé.";

    private static final String LITTERAL_RESPONSE_DESCRIPTION = "Iris";
    private static final String LITTERAL_ID_OPERATION = "getcogiris";
    private static final String LITTERAL_CODE_EXAMPLE = "010040101";
    private static final String LITTERAL_PARAMETER_DATE_DESCRIPTION =
            "Filtre pour renvoyer l'Iris active à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')";
    private static final String LITTERAL_OPERATION_SUMMARY =
            "Informations sur un Iris identifié par son code (neuf chiffres pour la métropole ou 2A/2B plus 7 chiffres pour la Corse)";


    private final IrisUtils irisUtils;

    public IrisApi() {
        // Constructeur par défaut
        this.irisUtils = new IrisUtils();
    }

    protected IrisApi(SparqlUtils sparqlUtils, CSVUtils csvUtils, ResponseUtils responseUtils, IrisUtils irisUtils) {
        super(sparqlUtils, csvUtils, responseUtils);
        this.irisUtils = irisUtils;
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
                            type = Constants.TYPE_STRING, example = LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = LITTERAL_PARAMETER_DATE_DESCRIPTION,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) String date) {

        var codeIris = CodeIris.of(code);
        if (codeIris.isInvalid()) {
            return generateBadRequestResponse(ConstGeoApi.ERREUR_PATTERN);
        }
        if (!this.verifyParameterDateIsRightWithoutHistory(date)) {
            return this.generateBadRequestResponse();
        }

        return getResponseForIrisOrPseudoIris(codeIris, header, date);
    }

    private Response getResponseForIrisOrPseudoIris(CodeIris codeIris, String header, String date) {
        if (irisUtils.hasIrisDescendant(codeIris.codeCommune())) {
            return getResponseForIris(codeIris, header, date);
        } else {
            return getResponseForPseudoIris(codeIris, header, date);
        }

    }

    private Response getResponseForPseudoIris(CodeIris codeIris, String header, String date) {
        if (codeIris.isPseudoIrisCode()) {
            return this.generateResponseATerritoireByCode(
                    sparqlUtils.executeSparqlQuery(
                            GeoQueries.getIrisByCodeAndDate(codeIris.code(), this.formatValidParameterDateIfIsNull(date))),
                    header,
                    new PseudoIris(codeIris.code()));
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("").build();
        }
    }

    private Response getResponseForIris(CodeIris codeIris, String header, String date) {
        if (codeIris.isPseudoIrisCode()) {
            return Response.status(Response.Status.NOT_FOUND).entity("").build();
        } else {
            Territoire territoire = new Iris(codeIris.code());
            return this.generateResponseATerritoireByCode(
                    sparqlUtils.executeSparqlQuery(
                            GeoQueries.getIrisByCodeAndDate(codeIris.code(), this.formatValidParameterDateIfIsNull(date))),
                    header,
                    territoire);
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
                    description = "Filtre pour renvoyer les Iris ou faux-Iris à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')",
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) String date,
            @Parameter(description = "les Iris (et pseudo-iris) des collectivités d'outre-mer",
                    required = true,
                    schema = @Schema(type = Constants.TYPE_BOOLEAN, allowableValues = {"true", "false"}, example = "false", defaultValue = "false"))
            @QueryParam(
                    value = Constants.PARAMETER_STRING) Boolean com
    ) {

        if (!this.verifyParameterDateIsRightWithHistory(date)) {
            return this.generateBadRequestResponse();
        } else {
            return this
                    .generateResponseListOfTerritoire(
                            sparqlUtils
                                    .executeSparqlQuery(GeoQueries.getListIris(this.formatValidParameterDateIfIsNull(date), this.formatValidParameterBooleanIfIsNull(com))),
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
                            type = Constants.TYPE_STRING, example = LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = "Filtre pour renvoyer les territoires contenant l'iris actif à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')",
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) String date,
            @Parameter(
                    description = LITTERAL_PARAMETER_TYPE_DESCRIPTION,
                    schema = @Schema(type = Constants.TYPE_STRING)) @QueryParam(
                    value = Constants.PARAMETER_TYPE) String typeTerritoire) {

        if (!this.verifyParametersTypeAndDateAreValid(typeTerritoire, date)) {
            return this.generateBadRequestResponse();
        } else {
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
