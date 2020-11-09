package fr.insee.rmes.api.geo.territoire;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.api.geo.AbstractGeoApi;
import fr.insee.rmes.api.geo.ConstGeoApi;
import fr.insee.rmes.modeles.geo.territoire.Arrondissement;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoires.Arrondissements;
import fr.insee.rmes.modeles.geo.territoires.Projections;
import fr.insee.rmes.modeles.geo.territoires.Territoires;
import fr.insee.rmes.queries.geo.GeoQueries;
import fr.insee.rmes.utils.Constants;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path(ConstGeoApi.PATH_GEO)
@Tag(name = ConstGeoApi.TAG_NAME, description = ConstGeoApi.TAG_DESCRIPTION)
public class ArrondissementApi extends AbstractGeoApi {

    private static final String LITTERAL_DATE_PROJECTION_DESCRIPTION = "Date vers laquelle est projetée l'arrondissement. Paramètre obligatoire (erreur 400 si absent, Format : 'AAAA-MM-JJ')";
    private static final String LITTERAL_DATE_ORIGINE_PROJ_DESCRIPTION = "Filtre pour préciser l'arrondissement de départ. Par défaut, c’est la date courante qui est utilisée. (Format : 'AAAA-MM-JJ')";

    private static Logger logger = LogManager.getLogger(ArrondissementApi.class);

    private static final String CODE_PATTERN = "/{code: " + ConstGeoApi.PATTERN_ARRONDISSEMENT + "}";
    private static final String LITTERAL_ID_OPERATION = "getcogarr";
    private static final String LITTERAL_OPERATION_SUMMARY =
        "Informations sur un arrondissement français identifié par son code (trois caractères)";
    private static final String LITTERAL_RESPONSE_DESCRIPTION = "Arrondissement";
    private static final String LITTERAL_PARAMETER_DATE_DESCRIPTION =
        "Filtre pour renvoyer l'arrondissement actif à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')";
    private static final String LITTERAL_PARAMETER_TYPE_DESCRIPTION = "Filtre sur le type de territoire renvoyé.";
    
    private static final String LITTERAL_CODE_EXAMPLE = "674";
    private static final String LITTERAL_CODE_EXAMPLE_SUIVANT = "042";
    private static final String LITTERAL_DATE_EXAMPLE = "2016-01-01";
    
    
    @Path(ConstGeoApi.PATH_ARRONDISSEMENT + CODE_PATTERN)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(operationId = LITTERAL_ID_OPERATION, summary = LITTERAL_OPERATION_SUMMARY, responses = {
        @ApiResponse(
            content = @Content(schema = @Schema(implementation = Arrondissement.class)),
            description = LITTERAL_RESPONSE_DESCRIPTION)
    })
    public Response getByCode(
        @Parameter(
            description = ConstGeoApi.PATTERN_ARRONDISSEMENT_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_ARRONDISSEMENT,
                type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = LITTERAL_PARAMETER_DATE_DESCRIPTION,
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE) String date) {

        logger.debug("Received GET request for arrondissement {}", code);

        if ( ! this.verifyParameterDateIsRight(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseATerritoireByCode(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries
                                .getArrondissementByCodeAndDate(code, this.formatValidParameterDateIfIsNull(date))),
                    header,
                    new Arrondissement(code));
        }
    }

    @Path(ConstGeoApi.PATH_ARRONDISSEMENT + CODE_PATTERN + ConstGeoApi.PATH_ASCENDANT)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_ASCENDANTS,
        summary = "Récupérer les informations concernant les territoires qui contiennent l'arrondissement",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getAscendants(
        @Parameter(
            description = ConstGeoApi.PATTERN_ARRONDISSEMENT_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_ARRONDISSEMENT,
                type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = LITTERAL_PARAMETER_DATE_DESCRIPTION,
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE) String date,
        @Parameter(
            description = LITTERAL_PARAMETER_TYPE_DESCRIPTION,
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING)) @QueryParam(
                value = Constants.PARAMETER_TYPE) String typeTerritoire) {

        logger.debug("Received GET request for ascendants of arrondissement {}", code);

        if ( ! this.verifyParametersTypeAndDateAreValid(typeTerritoire, date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseListOfTerritoire(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries
                                .getAscendantsArrondissement(
                                    code,
                                    this.formatValidParameterDateIfIsNull(date),
                                    this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire))),
                    header,
                    Territoires.class,
                    Territoire.class);
        }
    }

    @Path(ConstGeoApi.PATH_ARRONDISSEMENT + CODE_PATTERN + ConstGeoApi.PATH_DESCENDANT)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_DESCENDANTS,
        summary = "Récupérer les informations concernant les territoires inclus dans l'arrondissement",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getDescendants(
        @Parameter(
            description = ConstGeoApi.PATTERN_ARRONDISSEMENT_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_ARRONDISSEMENT,
                type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = LITTERAL_PARAMETER_DATE_DESCRIPTION,
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE) String date,
        @Parameter(
            description = LITTERAL_PARAMETER_TYPE_DESCRIPTION,
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING), example="CommuneDeleguee") @QueryParam(
                value = Constants.PARAMETER_TYPE) String typeTerritoire) {

        logger.debug("Received GET request for descendants of arrondissement {}", code);

        if ( ! this.verifyParametersTypeAndDateAreValid(typeTerritoire, date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseListOfTerritoire(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries
                                .getDescendantsArrondissement(
                                    code,
                                    this.formatValidParameterDateIfIsNull(date),
                                    this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire))),
                    header,
                    Territoires.class,
                    Territoire.class);
        }
    }

    @Path(ConstGeoApi.PATH_LISTE_ARRONDISSEMENT)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_LISTE,
        summary = "La requête renvoie toutes les arrondissements actifs à la date donnée. Par défaut, c’est la date courante.",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = Arrondissement.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getListe(
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = LITTERAL_PARAMETER_DATE_DESCRIPTION,
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE) String date) {

        logger.debug("Received GET request for all arrondissements");

        if ( ! this.verifyParameterDateIsRight(date)) {
            return this.generateBadRequestResponse();
        }
        else {

            return this
                .generateResponseListOfTerritoire(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries.getListArrondissements(this.formatValidParameterDateIfIsNull(date))),
                    header,
                    Arrondissements.class,
                    Arrondissement.class);
        }
    }

    @Path(ConstGeoApi.PATH_ARRONDISSEMENT + CODE_PATTERN + ConstGeoApi.PATH_SUIVANT)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_SUIVANT,
        summary = "Récupérer les informations concernant les arrondissements qui succèdent à l'arrondissement",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(implementation = Arrondissement.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getSuivant(
        @Parameter(
            description = ConstGeoApi.PATTERN_ARRONDISSEMENT_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_ARRONDISSEMENT,
                type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE_SUIVANT)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = LITTERAL_DATE_ORIGINE_PROJ_DESCRIPTION,
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE, example=LITTERAL_DATE_EXAMPLE)) @QueryParam(
                value = Constants.PARAMETER_DATE) String date) {

        logger.debug("Received GET request for suivant arrondissement {}", code);

        if ( ! this.verifyParameterDateIsRight(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseListOfTerritoire(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries.getNextArrondissement(code, this.formatValidParameterDateIfIsNull(date))),
                    header,
                    Arrondissements.class,
                    Arrondissement.class);
        }
    }

    @Path(ConstGeoApi.PATH_ARRONDISSEMENT + CODE_PATTERN + ConstGeoApi.PATH_PRECEDENT)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_PRECEDENT,
        summary = "Récupérer les informations concernant les arrondissements qui précèdent l'arrondissement",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(implementation = Arrondissement.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getPrecedent(
        @Parameter(
            description = ConstGeoApi.PATTERN_ARRONDISSEMENT_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_ARRONDISSEMENT,
                type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = LITTERAL_DATE_ORIGINE_PROJ_DESCRIPTION,
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE) String date) {

        logger.debug("Received GET request for precedent arrondissement {}", code);

        if ( ! this.verifyParameterDateIsRight(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseListOfTerritoire(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries.getPreviousArrondissement(code, this.formatValidParameterDateIfIsNull(date))),
                    header,
                    Arrondissements.class,
                    Arrondissement.class);
        }
    }

    @Path(ConstGeoApi.PATH_ARRONDISSEMENT + CODE_PATTERN + ConstGeoApi.PATH_PROJECTION)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_PROJECTION,
        summary = "Récupérer les informations concernant les arrondissements qui résultent de la projection de l'arrondissement à la date passée en paramètre. ",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = Arrondissement.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getProjection(
        @Parameter(
            description = ConstGeoApi.PATTERN_ARRONDISSEMENT_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_ARRONDISSEMENT,
                type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = LITTERAL_DATE_ORIGINE_PROJ_DESCRIPTION,
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE) String date,
        @Parameter(
            description = LITTERAL_DATE_PROJECTION_DESCRIPTION,
            required = true,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE), example="1994-07-30") @QueryParam(
                value = Constants.PARAMETER_DATE_PROJECTION) String dateProjection) {

        logger.debug("Received GET request for arrondissement {} projection", code);

        if ( ! this.verifyParameterDateIsRight(date) || ! this.verifyParameterDateIsRight(dateProjection)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseListOfTerritoire(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries
                                .getProjectionArrondissement(
                                    code,
                                    this.formatValidParameterDateIfIsNull(date),
                                    dateProjection)),
                    header,
                    Arrondissements.class,
                    Arrondissement.class);
        }
    }

    @Hidden
    @Path(ConstGeoApi.PATH_LISTE_ARRONDISSEMENT + ConstGeoApi.PATH_PROJECTION)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_PROJECTIONS,
        summary = "Récupérer la projection des arrondissements vers la date passée en paramètre.",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(implementation = Projections.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getAllProjections(
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour préciser les arrondissements de départ. Par défaut, c’est la date courante qui est utilisée.",
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE) String date,
        @Parameter(
            description = "Date vers laquelle sont projetées les arrondissements. Paramètre obligatoire (erreur 400 si absent)",
            required = true,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE_PROJECTION) String dateProjection) {

        logger.debug("Received GET request for all arrondissements projections");

        if ( ! this.verifyParameterDateIsRight(date) || ! this.verifyParameterDateIsRight(dateProjection)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseListOfProjection(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries
                                .getAllProjectionArrondissement(
                                    this.formatValidParameterDateIfIsNull(date),
                                    dateProjection)),
                    header);
        }
    }
}
