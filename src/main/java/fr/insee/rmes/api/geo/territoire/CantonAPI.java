package fr.insee.rmes.api.geo.territoire;

import fr.insee.rmes.api.AbstractMetadataApi;
import fr.insee.rmes.api.geo.AbstractGeoApi;
import fr.insee.rmes.api.geo.ConstGeoApi;
import fr.insee.rmes.modeles.geo.territoire.Canton;
import fr.insee.rmes.modeles.geo.territoire.Region;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoires.Cantons;
import fr.insee.rmes.modeles.geo.territoires.Regions;
import fr.insee.rmes.modeles.geo.territoires.Territoires;
import fr.insee.rmes.queries.geo.GeoQueries;
import fr.insee.rmes.utils.Constants;
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
@Path(ConstGeoApi.PATH_GEO)
@Tag(name = ConstGeoApi.TAG_NAME, description = ConstGeoApi.TAG_DESCRIPTION)
public class CantonAPI extends AbstractGeoApi {

    private static final String CODE_PATTERN = "/{code: " + ConstGeoApi.PATTERN_CANTON + "}";
    private static final String LITTERAL_PARAMETER_TYPE_DESCRIPTION = "Filtre sur le type de territoire renvoyé.";

    private static final String LITTERAL_RESPONSE_DESCRIPTION = "Canton";
    private static final String LITTERAL_ID_OPERATION = "getcogcanton";
    private static final String LITTERAL_CODE_EXAMPLE = "06";

    private static final String LITTERAL_CODE_HISTORY_EXAMPLE = "44";
    private static final String LITTERAL_PARAMETER_DATE_DESCRIPTION =
            "Filtre pour renvoyer la region active à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')";
    private static final String LITTERAL_OPERATION_SUMMARY =
            "Informations sur un canton identifié par son code (cinq chiffres)";

    private static final String LITTERAL_DATE_EXAMPLE = "2000-01-01";



    @Path(ConstGeoApi.PATH_CANTON + CODE_PATTERN)
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(operationId = LITTERAL_ID_OPERATION, summary = LITTERAL_OPERATION_SUMMARY, responses = {
            @ApiResponse(
                    content = @Content(schema = @Schema(implementation = Canton.class)),
                    description = LITTERAL_RESPONSE_DESCRIPTION)

    })
    public Response getByCode(
        @Parameter(
                description = ConstGeoApi.PATTERN_CANTON_DESCRIPTION,
                required = true,
                schema = @Schema(
                        pattern = ConstGeoApi.PATTERN_CANTON,
                        type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
                description = LITTERAL_PARAMETER_DATE_DESCRIPTION,
                required = false,
                schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
        value = Constants.PARAMETER_DATE) String date) {

            if ( ! this.verifyParameterDateIsRightWithoutHistory(date)) {
                return this.generateBadRequestResponse();
            }
            else {
                return this
                        .generateResponseATerritoireByCode(
                                sparqlUtils
                                        .executeSparqlQuery(
                                                GeoQueries.getCantonByCodeAndDate(code, this.formatValidParameterDateIfIsNull(date))),
                                header,
                                new Canton(code));
            }
        }





    @Path(ConstGeoApi.PATH_LISTE_CANTON)
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
            operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_LISTE,
            summary = "Informations sur toutes les cantons actifs à la date donnée. Par défaut, c’est la date courante.",
            responses = {
                    @ApiResponse(
                            content = @Content(schema = @Schema(type = ARRAY, implementation = Canton.class)),
                            description = LITTERAL_RESPONSE_DESCRIPTION)
            })
    public Response getListe(
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = "Filtre pour renvoyer les cantons actifs à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')" + LITTERAL_PARAMETER_DATE_WITH_HISTORY,
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) String date) {
        if ( ! this.verifyParameterDateIsRightWithHistory(date)) {
            return this.generateBadRequestResponse();
        }
        else {

            return this
                    .generateResponseListOfTerritoire(
                            sparqlUtils
                                    .executeSparqlQuery(GeoQueries.getListCantons(this.formatValidParameterDateIfIsNull(date))),
                            header,
                            Cantons.class,
                            Canton.class);
        }
    }

    @Path(ConstGeoApi.PATH_CANTON + CODE_PATTERN + ConstGeoApi.PATH_SUIVANT)
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
            operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_SUIVANT,
            summary = "Informations concernant les cantons qui succèdent au canton",
            responses = {
                    @ApiResponse(
                            content = @Content(schema = @Schema(implementation = Canton.class)),
                            description = LITTERAL_RESPONSE_DESCRIPTION)
            })
    public Response getSuivant(
            @Parameter(
                    description = ConstGeoApi.PATTERN_CANTON_DESCRIPTION,
                    required = true,
                    schema = @Schema(
                            pattern = ConstGeoApi.PATTERN_CANTON,
                            type = Constants.TYPE_STRING, example="41")) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = "Filtre pour préciser le canton de départ. Par défaut, c’est la date courante qui est utilisée. (Format : 'AAAA-MM-JJ')",
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE, example=LITTERAL_DATE_EXAMPLE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) String date) {

        if ( ! this.verifyParameterDateIsRightWithoutHistory(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                    .generateResponseListOfTerritoire(
                            sparqlUtils
                                    .executeSparqlQuery(
                                            GeoQueries.getNextCanton(code, this.formatValidParameterDateIfIsNull(date))),
                            header,
                            Cantons.class,
                            Canton.class);
        }
    }


    @Path(ConstGeoApi.PATH_CANTON + CODE_PATTERN + ConstGeoApi.PATH_PRECEDENT)
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
            operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_PRECEDENT,
            summary = "Informations concernant les cantons qui précèdent le canton",
            responses = {
                    @ApiResponse(
                            content = @Content(schema = @Schema(implementation = Canton.class)),
                            description = LITTERAL_RESPONSE_DESCRIPTION)
            })
    public Response getPrecedent(
            @Parameter(
                    description = ConstGeoApi.PATTERN_CANTON_DESCRIPTION,
                    required = true,
                    schema = @Schema(
                            pattern = ConstGeoApi.PATTERN_CANTON,
                            type = Constants.TYPE_STRING, example=LITTERAL_CODE_HISTORY_EXAMPLE)) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = "Filtre pour préciser le canton de départ. Par défaut, c’est la date courante qui est utilisée. (Format : 'AAAA-MM-JJ')",
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) String date) {

        if ( ! this.verifyParameterDateIsRightWithoutHistory(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                    .generateResponseListOfTerritoire(
                            sparqlUtils
                                    .executeSparqlQuery(
                                            GeoQueries.getPreviousCanton(code, this.formatValidParameterDateIfIsNull(date))),
                            header,
                            Cantons.class,
                            Canton.class);
        }
    }

    @Path(ConstGeoApi.PATH_CANTON + CODE_PATTERN + ConstGeoApi.PATH_PROJECTION)
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
            operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_PROJECTION,
            summary = "Informations concernant les cantons qui résultent de la projection du canton à la date passée en paramètre. ",
            responses = {
                    @ApiResponse(
                            content = @Content(schema = @Schema(implementation = Canton.class)),
                            description = LITTERAL_RESPONSE_DESCRIPTION)
            })
    public Response getProjection(
            @Parameter(
                    description = ConstGeoApi.PATTERN_CANTON_DESCRIPTION,
                    required = true,
                    schema = @Schema(
                            pattern = ConstGeoApi.PATTERN_CANTON,
                            type = Constants.TYPE_STRING, example=LITTERAL_CODE_HISTORY_EXAMPLE)) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = "Filtre pour préciser le canton de départ. Par défaut, c’est la date courante qui est utilisée. (Format : 'AAAA-MM-JJ')",
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) String date,
            @Parameter(
                    description = "Date vers laquelle est projetée le canton. Paramètre obligatoire (Format : 'AAAA-MM-JJ', erreur 400 si absent)",
                    required = true,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE, example=LITTERAL_DATE_EXAMPLE)) @QueryParam(
                    value = Constants.PARAMETER_DATE_PROJECTION) String dateProjection) {

        if ( ! this.verifyParameterDateIsRightWithoutHistory(date) || ! this.verifyParameterDateIsRightWithoutHistory(dateProjection)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                    .generateResponseListOfTerritoire(
                            sparqlUtils
                                    .executeSparqlQuery(
                                            GeoQueries
                                                    .getProjectionCanton(
                                                            code,
                                                            this.formatValidParameterDateIfIsNull(date),
                                                            dateProjection)),
                            header,
                            Cantons.class,
                            Canton.class);
        }
    }



    @Path(ConstGeoApi.PATH_CANTON + CODE_PATTERN + ConstGeoApi.PATH_ASCENDANT)
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
            operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_ASCENDANTS,
            summary = "Informations concernant les territoires qui contiennent le canton",
            responses = {
                    @ApiResponse(
                            content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
                            description = LITTERAL_RESPONSE_DESCRIPTION)
            })
    public Response getAscendants(
            @Parameter(
                    description = ConstGeoApi.PATTERN_CANTON_DESCRIPTION,
                    required = true,
                    schema = @Schema(
                            pattern = ConstGeoApi.PATTERN_CANTON,
                            type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = "Filtre pour renvoyer les territoires contenant le canton actif à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')",
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
                                                    .getAscendantsCanton(
                                                            code,
                                                            this.formatValidParameterDateIfIsNull(date),
                                                            this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire))),
                            header,
                            Territoires.class,
                            Territoire.class);
        }
    }



}
