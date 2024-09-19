package fr.insee.rmes.api.geo.territoire;

import fr.insee.rmes.api.geo.AbstractGeoApi;
import fr.insee.rmes.api.geo.ConstGeoApi;
import fr.insee.rmes.modeles.geo.territoire.Canton;
import fr.insee.rmes.modeles.geo.territoire.Commune;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoires.Cantons;
import fr.insee.rmes.modeles.geo.territoires.Communes;
import fr.insee.rmes.modeles.geo.territoires.Territoires;
import fr.insee.rmes.modeles.utils.Date;
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

    private static final String CODE_PATTERN = "/{code:[0-9]{4}}";
    private static final String LITTERAL_PARAMETER_TYPE_DESCRIPTION = "Filtre sur le type de territoire renvoyé.";
    private static final String LITTERAL_PARAMETER_NAME_DESCRIPTION = "Filtre sur le nom des communes renvoyées" ;

    private static final String LITTERAL_RESPONSE_DESCRIPTION = "Canton";
    private static final String LITTERAL_ID_OPERATION = "getcogcanton";
    private static final String LITTERAL_CODE_EXAMPLE = "0101";

    private static final String LITTERAL_CODE_HISTORY_EXAMPLE = "0103";
    private static final String LITTERAL_PARAMETER_DATE_DESCRIPTION =
            "Filtre pour renvoyer la region active à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')";
    private static final String LITTERAL_OPERATION_SUMMARY =
            "Informations sur un canton identifié par son code (quatre chiffres pour la métropole ou cinq pour les DOM ou 2A/2B plus deux chiffres pour la Corse)";

    private static final String LITTERAL_DATE_EXAMPLE = "2020-01-01";



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
        value = Constants.PARAMETER_DATE) Date date) {
        String dateString = null;
            if (date !=null) {
                dateString = date.getString();
            }
            if (!code.matches(ConstGeoApi.PATTERN_CANTON)) {
                String errorMessage = ConstGeoApi.ERREUR_PATTERN;
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(errorMessage)
                        .type(MediaType.TEXT_PLAIN)
                        .build();
            }
            if ( ! this.verifyParameterDateIsRightWithoutHistory(dateString)) {
                return this.generateBadRequestResponse();
            }
            else {
                return this
                        .generateResponseATerritoireByCode(
                                sparqlUtils
                                        .executeSparqlQuery(
                                                GeoQueries.getCantonByCodeAndDate(code, this.formatValidParameterDateIfIsNull(dateString))),
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
                    value = Constants.PARAMETER_DATE) Date date) {
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
                                    .executeSparqlQuery(GeoQueries.getListCantons(this.formatValidParameterDateIfIsNull(dateString))),
                            header,
                            Cantons.class,
                            Canton.class);
        }
    }

    @Path(ConstGeoApi.PATH_CANTON +CODE_PATTERN + ConstGeoApi.PATH_SUIVANT)
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
                            type = Constants.TYPE_STRING, example="0103")) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = "Filtre pour préciser le canton de départ. Par défaut, c’est la date courante qui est utilisée. (Format : 'AAAA-MM-JJ')",
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE, example=LITTERAL_DATE_EXAMPLE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) Date date) {
        String dateString = null;
        if (date !=null) {
            dateString = date.getString();
        }
        if (!code.matches(ConstGeoApi.PATTERN_CANTON)) {
            String errorMessage = ConstGeoApi.ERREUR_PATTERN;
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorMessage)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        if ( ! this.verifyParameterDateIsRightWithoutHistory(dateString)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                    .generateResponseListOfTerritoire(
                            sparqlUtils
                                    .executeSparqlQuery(
                                            GeoQueries.getNextCanton(code, this.formatValidParameterDateIfIsNull(dateString))),
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
                    value = Constants.PARAMETER_DATE) Date date) {
        String dateString = null;
        if (date !=null) {
            dateString = date.getString();
        }
        if (!code.matches(ConstGeoApi.PATTERN_CANTON)) {
            String errorMessage = ConstGeoApi.ERREUR_PATTERN;
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorMessage)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        if ( ! this.verifyParameterDateIsRightWithoutHistory(dateString) ) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                    .generateResponseListOfTerritoire(
                            sparqlUtils
                                    .executeSparqlQuery(
                                            GeoQueries.getPreviousCanton(code, this.formatValidParameterDateIfIsNull(dateString))),
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
                    value = Constants.PARAMETER_DATE) Date date,
            @Parameter(
                    description = "Date vers laquelle est projetée le canton. Paramètre obligatoire (Format : 'AAAA-MM-JJ', erreur 400 si absent)",
                    required = true,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE, example=LITTERAL_DATE_EXAMPLE)) @QueryParam(
                    value = Constants.PARAMETER_DATE_PROJECTION) Date dateProjection) {
        String dateString = null;
        if (date !=null) {
            dateString = date.getString();
        }
        String dateProjectionString = null;
        if (dateProjection != null){
            dateProjectionString = dateProjection.getString();
        }
        if (!code.matches(ConstGeoApi.PATTERN_CANTON)) {
            String errorMessage = ConstGeoApi.ERREUR_PATTERN;
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorMessage)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        if ( ! this.verifyParameterDateIsRightWithoutHistory(dateString) || ! this.verifyParameterDateIsRightWithoutHistory(dateProjectionString) ) {
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
                                                            this.formatValidParameterDateIfIsNull(dateString),
                                                            dateProjectionString)),
                            header,
                            Cantons.class,
                            Canton.class);
        }
    }

    @Path(ConstGeoApi.PATH_CANTON + CODE_PATTERN + "/communes")
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
            operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_COMMUNES,
            summary = "Récupérer les informations concernant les communes qui ont un territoire commun avec le canton {code}\n",
            responses = {
                    @ApiResponse(
                            content = @Content(schema = @Schema(implementation = Canton.class)),
                            description = LITTERAL_RESPONSE_DESCRIPTION)
            })
    public Response getCommunes( @Parameter(
            description = ConstGeoApi.PATTERN_CANTON_DESCRIPTION,
            required = true,
            schema = @Schema(
                    pattern = ConstGeoApi.PATTERN_CANTON,
                    type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
                                 @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
                                 @Parameter(
                                         description = "La requête renvoie les communes actives à la date donnée. Par défaut, c’est la date courante.  (Format : 'AAAA-MM-JJ')",
                                         required = false,
                                         schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                                         value = Constants.PARAMETER_DATE) Date date){
        String dateString = null;
        if (date !=null) {
            dateString = date.getString();
        }

        if (!code.matches(ConstGeoApi.PATTERN_CANTON)) {
            String errorMessage = ConstGeoApi.ERREUR_PATTERN;
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorMessage)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        if ( ! this.verifyParameterDateIsRightWithoutHistory(dateString)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                    .generateResponseListOfTerritoire(
                            sparqlUtils
                                    .executeSparqlQuery(
                                            GeoQueries
                                                    .getCommunesCanton(
                                                            code,
                                                            this.formatValidParameterDateIfIsNull(dateString))),
                            header,
                            Communes.class,
                            Commune.class);
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
                    value = Constants.PARAMETER_DATE) Date date,
            @Parameter(
                    description = LITTERAL_PARAMETER_TYPE_DESCRIPTION,
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING)) @QueryParam(
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
                                                    .getAscendantsCanton(
                                                            code,
                                                            this.formatValidParameterDateIfIsNull(dateString),
                                                            this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire))),
                            header,
                            Territoires.class,
                            Territoire.class);
        }
    }


}
