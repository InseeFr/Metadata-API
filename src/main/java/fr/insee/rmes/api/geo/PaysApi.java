package fr.insee.rmes.api.geo;

import fr.insee.rmes.modeles.geo.territoires.PaysS;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoires.Territoires;
import fr.insee.rmes.modeles.utils.Date;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import fr.insee.rmes.modeles.geo.territoire.Pays;
import fr.insee.rmes.modeles.utils.Header;
import fr.insee.rmes.queries.geo.GeoQueries;
import fr.insee.rmes.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path(ConstGeoApi.PATH_GEO)
@Tag(name = ConstGeoApi.TAG_NAME, description = ConstGeoApi.TAG_DESCRIPTION)
public class PaysApi extends AbstractGeoApi {

    private static final String CODE_PATTERN = "/{code: " + ConstGeoApi.PATTERN_PAYS + "}";
    private static final String LITTERAL_ID_OPERATION = "getcogpays";
    private static final String LITTERAL_OPERATION_SUMMARY =
            "Informations sur un pays identifié par son code (cinq chiffres, les deux premiers étant 99)";
    private static final String LITTERAL_RESPONSE_DESCRIPTION = "Pays";
    private static final String LITTERAL_PARAMETER_TYPE_DESCRIPTION = "Filtre sur le type de territoire renvoyé.";
    private static final String LITTERAL_DATE_EXAMPLE = "2000-01-01";
    private static final String LITTERAL_CODE_HISTORY_EXAMPLE = "44";



    @Path(ConstGeoApi.PATH_PAYS + CODE_PATTERN)
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(operationId = LITTERAL_ID_OPERATION, summary = LITTERAL_OPERATION_SUMMARY, responses = {
            @ApiResponse(
                    content = @Content(schema = @Schema(implementation = Pays.class)),
                    description = LITTERAL_RESPONSE_DESCRIPTION)
    })
    public Response getByCode(
            @Parameter(
                    description = ConstGeoApi.PATTERN_PAYS_DESCRIPTION,
                    required = true,
                    schema = @Schema(
                            pattern = ConstGeoApi.PATTERN_PAYS,
                            type = Constants.TYPE_STRING, example = "99217")) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) Header header) {
        if (!isValidCode(code)) {
            return Response.status(Status.BAD_REQUEST).entity("Invalid code").build();
        }

        Pays pays = new Pays(code);
        String sanitizedCode = escapeSparql(code);
        String csvResult = sparqlUtils.executeSparqlQuery(GeoQueries.getPays(sanitizedCode));
        pays = (Pays) csvUtils.populatePOJO(csvResult, pays);

        if (pays.getUri() == null) {
            return Response.status(Status.NOT_FOUND).entity("").build();
        } else {
            String acceptHeader = sanitizeAndValidateHeader(header.getString());

            if (acceptHeader == null) {
                return Response.status(Status.BAD_REQUEST).entity("Invalid Accept header").build();
            }
            return Response.ok(responseUtils.produceResponse(pays, acceptHeader)).build();
        }
    }

    @Path(ConstGeoApi.PATH_PAYS)
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(operationId = LITTERAL_ID_OPERATION, summary = LITTERAL_OPERATION_SUMMARY, responses = {
            @ApiResponse(
                    content = @Content(schema = @Schema(implementation = PaysS.class)),
                    description = LITTERAL_RESPONSE_DESCRIPTION)
    })
    public Response getListe(
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = "Filtre pour renvoyer les pays actifs à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')" + LITTERAL_PARAMETER_DATE_WITH_HISTORY,
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) Date date)  {
        String dateString = null;
        if (date !=null) {
            dateString = date.getString();
        }
        if ( ! this.verifyParameterDateIsRightWithHistory(dateString)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                    .generateResponseListOfTerritoire(
                            sparqlUtils
                                    .executeSparqlQuery(GeoQueries.getListPays(this.formatValidParameterDateIfIsNull(dateString))),
                            header,
                            PaysS.class,
                            Pays.class);
        }
    }

    @Path(ConstGeoApi.PATH_PAYS + CODE_PATTERN + ConstGeoApi.PATH_DESCENDANT)
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
            operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_DESCENDANTS,
            summary = "Informations concernant les territoires inclus dans le pays",
            responses = {
                    @ApiResponse(
                            content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
                            description = LITTERAL_RESPONSE_DESCRIPTION)
            })
    public Response getDescendants(
            @Parameter(
                    description = ConstGeoApi.PATTERN_PAYS_DESCRIPTION,
                    required = true,
                    schema = @Schema(
                            pattern = ConstGeoApi.PATTERN_PAYS,
                            type = Constants.TYPE_STRING, example="002")) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = "Filtre pour renvoyer les territoires inclus dans le pays actif à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')",
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) Date date,
            @Parameter(
                    description = LITTERAL_PARAMETER_TYPE_DESCRIPTION,
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, example="ArrondissementMunicipal")) @QueryParam(
                    value = Constants.PARAMETER_TYPE) String typeTerritoire) {
        String dateString = null;
        if (date !=null) {
            dateString = date.getString();
        }
        if ( ! this.verifyParametersTypeAndDateAreValid(typeTerritoire, dateString)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                    .generateResponseListOfTerritoire(
                            sparqlUtils
                                    .executeSparqlQuery(
                                            GeoQueries
                                                    .getDescendantsPays(
                                                            code,
                                                            this.formatValidParameterDateIfIsNull(dateString),
                                                            this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire))),
                            header,
                            Territoires.class,
                            Territoire.class);
        }
    }

    @Path(ConstGeoApi.PATH_PAYS + CODE_PATTERN + ConstGeoApi.PATH_SUIVANT)
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
            operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_SUIVANT,
            summary = "Informations concernant les pays qui succèdent au pays",
            responses = {
                    @ApiResponse(
                            content = @Content(schema = @Schema(implementation = Pays.class)),
                            description = LITTERAL_RESPONSE_DESCRIPTION)
            })
    public Response getSuivant(
            @Parameter(
                    description = ConstGeoApi.PATTERN_PAYS_DESCRIPTION,
                    required = true,
                    schema = @Schema(
                            pattern = ConstGeoApi.PATTERN_PAYS,
                            type = Constants.TYPE_STRING, example="41")) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = "Filtre pour préciser le pays de départ. Par défaut, c’est la date courante qui est utilisée. (Format : 'AAAA-MM-JJ')",
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE, example=LITTERAL_DATE_EXAMPLE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) Date date) {
        String dateString = null;
        if (date != null){
            dateString = date.getString();
        }
        if ( ! this.verifyParameterDateIsRightWithoutHistory(dateString)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                    .generateResponseListOfTerritoire(
                            sparqlUtils
                                    .executeSparqlQuery(
                                            GeoQueries.getNextPays(code, this.formatValidParameterDateIfIsNull(dateString))),
                            header,
                            PaysS.class,
                            Pays.class);
        }
    }


    @Path(ConstGeoApi.PATH_PAYS + CODE_PATTERN + ConstGeoApi.PATH_PRECEDENT)
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
            operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_PRECEDENT,
            summary = "Informations concernant les pays qui précèdent le pays",
            responses = {
                    @ApiResponse(
                            content = @Content(schema = @Schema(implementation = Pays.class)),
                            description = LITTERAL_RESPONSE_DESCRIPTION)
            })
    public Response getPrecedent(
            @Parameter(
                    description = ConstGeoApi.PATTERN_PAYS_DESCRIPTION,
                    required = true,
                    schema = @Schema(
                            pattern = ConstGeoApi.PATTERN_PAYS,
                            type = Constants.TYPE_STRING, example=LITTERAL_CODE_HISTORY_EXAMPLE)) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = "Filtre pour préciser le pays de départ. Par défaut, c’est la date courante qui est utilisée. (Format : 'AAAA-MM-JJ')",
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) Date date) {
        String dateString = null;
        if (date != null){
            dateString = date.getString();
        }
        if ( ! this.verifyParameterDateIsRightWithoutHistory(dateString)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                    .generateResponseListOfTerritoire(
                            sparqlUtils
                                    .executeSparqlQuery(
                                            GeoQueries.getPreviousPays(code, this.formatValidParameterDateIfIsNull(dateString))),
                            header,
                            PaysS.class,
                            Pays.class);
        }
    }

    private boolean isValidCode(String code) {
        return code != null && code.matches(ConstGeoApi.PATTERN_PAYS);
    }

     private String escapeSparql(String input) {
        return input.replace("\"", "\\\"").replace("<", "\\u003C").replace(">", "\\u003E");
    }
    private String sanitizeAndValidateHeader(String header) {
        if (header == null || header.isEmpty()) {
            return null;
        }
        if (header.equals(MediaType.APPLICATION_JSON) || header.equals(MediaType.APPLICATION_XML)) {
            return header;
        }

        return null;
    }
}

