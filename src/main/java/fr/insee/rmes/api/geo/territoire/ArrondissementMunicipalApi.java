package fr.insee.rmes.api.geo.territoire;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import fr.insee.rmes.api.geo.AbstractGeoApi;
import fr.insee.rmes.api.geo.ConstGeoApi;
import fr.insee.rmes.modeles.geo.territoire.ArrondissementMunicipal;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoires.ArrondissementsMunicipaux;
import fr.insee.rmes.modeles.geo.territoires.Projections;
import fr.insee.rmes.modeles.geo.territoires.Territoires;
import fr.insee.rmes.modeles.utils.Date;
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
public class ArrondissementMunicipalApi extends AbstractGeoApi {

    private static final String CODE_PATTERN = "/{code: " + ConstGeoApi.PATTERN_ARRONDISSEMENT_MUNICIPAL + "}";
    private static final String LITTERAL_ID_OPERATION = "getcogarrmun";
    private static final String LITTERAL_OPERATION_SUMMARY =
        "Informations sur un arrondissement municipal identifié par son code (cinq caractères)";
    private static final String LITTERAL_RESPONSE_DESCRIPTION = "Arrondissement municipal";
    private static final String LITTERAL_PARAMETER_DATE_DESCRIPTION =
        "Filtre pour renvoyer la arrondissement municipal actif à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')";
    private static final String LITTERAL_PARAMETER_TYPE_DESCRIPTION = "Filtre sur le type de territoire renvoyé.";
    
    private static final String LITTERAL_DATE_PROJECTION_DESCRIPTION = "Date vers laquelle est projetée l'arrondissement municipal. Paramètre obligatoire (erreur 400 si absent, Format : 'AAAA-MM-JJ'))";
    private static final String LITTERAL_DATE_ORIGINE_PROJ_DESCRIPTION = "Filtre pour préciser l'arrondissement municipal de départ. Par défaut, c’est la date courante qui est utilisée. (Format : 'AAAA-MM-JJ')";

    private static final String LITTERAL_CODE_EXAMPLE = "69385";
    private static final String LITTERAL_DATE_PROJETE_EXAMPLE = "2011-12-31";
    private static final String LITTERAL_DATE_EXAMPLE = "1960-01-01";

    @Path(ConstGeoApi.PATH_ARRONDISSEMENT_MUNICIPAL + CODE_PATTERN)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(operationId = LITTERAL_ID_OPERATION, summary = LITTERAL_OPERATION_SUMMARY, responses = {
        @ApiResponse(
            content = @Content(schema = @Schema(implementation = ArrondissementMunicipal.class)),
            description = LITTERAL_RESPONSE_DESCRIPTION)
    })
    public Response getByCode(
        @Parameter(
            description = ConstGeoApi.PATTERN_ARRONDISSEMENT_MUNICIPAL_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_ARRONDISSEMENT_MUNICIPAL,
                type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = LITTERAL_PARAMETER_DATE_DESCRIPTION,
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE) Date date) {
        String dateString =null;
        if (date != null){
            dateString = date.getString();
        }
        if ( ! this.verifyParameterDateIsRightWithoutHistory(dateString)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseATerritoireByCode(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries
                                .getArrondissementmunicipalByCodeAndDate(
                                    code,
                                    this.formatValidParameterDateIfIsNull(dateString))),
                    header,
                    new ArrondissementMunicipal(code));
        }
    }

    @Path(ConstGeoApi.PATH_ARRONDISSEMENT_MUNICIPAL + CODE_PATTERN + ConstGeoApi.PATH_ASCENDANT)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_ASCENDANTS,
        summary = "Informations concernant les territoires qui contiennent l'arrondissement municipal",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getAscendants(
        @Parameter(
            description = ConstGeoApi.PATTERN_ARRONDISSEMENT_MUNICIPAL_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_ARRONDISSEMENT_MUNICIPAL,
                type = Constants.TYPE_STRING)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer les territoires contenant l'arrondissement municipal actif à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')",
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE) Date date,
        @Parameter(
            description = LITTERAL_PARAMETER_TYPE_DESCRIPTION,
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING)) @QueryParam(
                value = Constants.PARAMETER_TYPE) String typeTerritoire) {
        String dateString =null;
        if (date != null){
            dateString = date.getString();
        }
        if (!this.verifyParametersTypeAndDateAreValid(typeTerritoire, dateString)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseListOfTerritoire(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries
                                .getAscendantsArrondissementMunicipal(
                                    code,
                                    this.formatValidParameterDateIfIsNull(dateString),
                                    this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire))),
                    header,
                    Territoires.class,
                    Territoire.class);
        }
    }

    @Path(ConstGeoApi.PATH_LISTE_ARRONDISSEMENT_MUNICIPAL)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_LISTE,
        summary = "Informations sur tous les arrondissements municipaux actifs à la date donnée. Par défaut, c’est la date courante.",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = ArrondissementMunicipal.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getListe(
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = LITTERAL_PARAMETER_DATE_DESCRIPTION + LITTERAL_PARAMETER_DATE_WITH_HISTORY,
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE) Date date) {
        String dateString =null;
        if (date != null){
            dateString = date.getString();
        }
        if ( ! this.verifyParameterDateIsRightWithHistory(dateString)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseListOfTerritoire(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries.getListArrondissementsMunicipaux(this.formatValidParameterDateIfIsNull(dateString))),
                    header,
                    ArrondissementsMunicipaux.class,
                    ArrondissementMunicipal.class);
        }
    }

    @Path(ConstGeoApi.PATH_ARRONDISSEMENT_MUNICIPAL + CODE_PATTERN + ConstGeoApi.PATH_SUIVANT)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_SUIVANT,
        summary = "Informations concernant les arrondissements municipaux qui succèdent à l'arrondissement municipal",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = ArrondissementMunicipal.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getSuivant(
        @Parameter(
            description = ConstGeoApi.PATTERN_ARRONDISSEMENT_MUNICIPAL_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_ARRONDISSEMENT_MUNICIPAL,
                type = Constants.TYPE_STRING),
            example=LITTERAL_CODE_EXAMPLE) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = LITTERAL_DATE_ORIGINE_PROJ_DESCRIPTION,
            required = false,
            example=LITTERAL_DATE_EXAMPLE,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE) Date date) {
        String dateString =null;
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
                            GeoQueries
                                .getNextArrondissementMunicipal(code, this.formatValidParameterDateIfIsNull(dateString))),
                    header,
                    ArrondissementsMunicipaux.class,
                    ArrondissementMunicipal.class);
        }
    }

    @Path(ConstGeoApi.PATH_ARRONDISSEMENT_MUNICIPAL + CODE_PATTERN + ConstGeoApi.PATH_PRECEDENT)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_PRECEDENT,
        summary = "Informations concernant les arrondissement municipaux qui précèdent l'arrondissement municipal",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = ArrondissementMunicipal.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getPrecedent(
        @Parameter(
            description = ConstGeoApi.PATTERN_ARRONDISSEMENT_MUNICIPAL_DESCRIPTION,
            required = true,
            example=LITTERAL_CODE_EXAMPLE,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_ARRONDISSEMENT_MUNICIPAL,
                type = Constants.TYPE_STRING)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = LITTERAL_DATE_ORIGINE_PROJ_DESCRIPTION,
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE) Date date) {
        String dateString =null;
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
                            GeoQueries
                                .getPreviousArrondissementMunicipal(code, this.formatValidParameterDateIfIsNull(dateString))),
                    header,
                    ArrondissementsMunicipaux.class,
                    ArrondissementMunicipal.class);
        }
    }

    @Path(ConstGeoApi.PATH_ARRONDISSEMENT_MUNICIPAL + CODE_PATTERN + ConstGeoApi.PATH_PROJECTION)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_PROJECTION,
        summary = "Informations concernant les arrondissements municipaux qui résultent de la projection de l'arrondissement municipal à la date passée en paramètre. ",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = ArrondissementMunicipal.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getProjection(
        @Parameter(
            description = ConstGeoApi.PATTERN_ARRONDISSEMENT_MUNICIPAL_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_ARRONDISSEMENT_MUNICIPAL,
                type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = LITTERAL_DATE_ORIGINE_PROJ_DESCRIPTION,
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE, example=LITTERAL_DATE_EXAMPLE)) @QueryParam(
                value = Constants.PARAMETER_DATE) Date date,
        @Parameter(
            description = LITTERAL_DATE_PROJECTION_DESCRIPTION,
            required = true,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE, example=LITTERAL_DATE_PROJETE_EXAMPLE)) @QueryParam(
                value = Constants.PARAMETER_DATE_PROJECTION) Date dateProjection) {
        String dateString = null;
        String dateprojectionString = null;
        if (date != null){
            dateString = date.getString();
        }
        if (dateProjection != null){
            dateprojectionString = dateProjection.getString();
        }
         if ( ! this.verifyParameterDateIsRightWithoutHistory(dateString) || ! this.verifyParameterDateIsRightWithoutHistory(dateprojectionString) || dateprojectionString == null) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseListOfTerritoire(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries
                                .getProjectionArrondissementMunicipal(
                                    code,
                                    this.formatValidParameterDateIfIsNull(dateString),
                                    dateprojectionString)),
                    header,
                    ArrondissementsMunicipaux.class,
                    ArrondissementMunicipal.class);
        }
    }

    @Hidden
    @Path(ConstGeoApi.PATH_LISTE_ARRONDISSEMENT_MUNICIPAL + ConstGeoApi.PATH_PROJECTION)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_PROJECTIONS,
        summary = "Récupérer la projection des arrondissements municipaux vers la date passée en paramètre.",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(implementation = Projections.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getAllProjections(
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour préciser les arrondissements municipaux de départ. Par défaut, c’est la date courante qui est utilisée.",
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE) Date date,
        @Parameter(
            description = "Date vers laquelle sont projetées les arrondissements municipaux. Paramètre obligatoire (erreur 400 si absent)",
            required = true,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE_PROJECTION) Date dateProjection) {
        String dateString = null;
        String dateprojectionString = null;
        if (date != null){
            dateString = date.getString();
        }
        if (dateProjection != null){
            dateprojectionString = dateProjection.getString();
        }
        if ( ! this.verifyParameterDateIsRightWithoutHistory(dateString) || ! this.verifyParameterDateIsRightWithoutHistory(dateprojectionString)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseListOfProjection(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries
                                .getAllProjectionArrondissementMunicipal(
                                    this.formatValidParameterDateIfIsNull(dateString),
                                    dateprojectionString)),
                    header);
        }
    }
}
