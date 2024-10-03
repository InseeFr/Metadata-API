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
import fr.insee.rmes.modeles.geo.territoire.Departement;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoires.Departements;
import fr.insee.rmes.modeles.geo.territoires.Projections;
import fr.insee.rmes.modeles.geo.territoires.Territoires;
import fr.insee.rmes.modeles.utils.Date;
import fr.insee.rmes.modeles.utils.FiltreNom;
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
public class DepartementApi extends AbstractGeoApi {


    private static final String CODE_PATTERN = "/{code: " + ConstGeoApi.PATTERN_DEPARTEMENT + "}";
    private static final String LITTERAL_ID_OPERATION = "getcogdep";
    private static final String LITTERAL_OPERATION_SUMMARY =
        "Informations sur un departement identifié par son code (deux ou trois caractères)";
    private static final String LITTERAL_RESPONSE_DESCRIPTION = "Departement";
    private static final String LITTERAL_PARAMETER_TYPE_DESCRIPTION = "Filtre sur le type de territoire renvoyé.";
    private static final String LITTERAL_PARAMETER_NAME_DESCRIPTION = "Filtre sur le nom des territoires renvoyés" ;
    private static final String LITTERAL_CODE_EXAMPLE = "22";
    private static final String LITTERAL_DATE_EXAMPLE = "1950-01-01";

    
    @Path(ConstGeoApi.PATH_DEPARTEMENT + CODE_PATTERN)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(operationId = LITTERAL_ID_OPERATION, summary = LITTERAL_OPERATION_SUMMARY, responses = {
        @ApiResponse(
            content = @Content(schema = @Schema(implementation = Departement.class)),
            description = LITTERAL_RESPONSE_DESCRIPTION)
    })
    public Response getByCode(
        @Parameter(
            description = ConstGeoApi.PATTERN_DEPARTEMENT_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_DEPARTEMENT,
                type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer la département actif à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')",
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
                .generateResponseATerritoireByCode(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries.getDepartementByCodeAndDate(code, this.formatValidParameterDateIfIsNull(dateString))),
                    header,
                    new Departement(code));
        }
    }

    @Path(ConstGeoApi.PATH_DEPARTEMENT + CODE_PATTERN + ConstGeoApi.PATH_ASCENDANT)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_ASCENDANTS,
        summary = "Informations concernant les territoires qui contiennent le département",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getAscendants(
        @Parameter(
            description = ConstGeoApi.PATTERN_DEPARTEMENT_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_DEPARTEMENT,
                type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer les territoires contenant le département actif à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')",
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE) Date date,
        @Parameter(
            description = LITTERAL_PARAMETER_TYPE_DESCRIPTION,
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING)) @QueryParam(
                value = Constants.PARAMETER_TYPE) String typeTerritoire) {
        String dateString = null;
        if (date != null){
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
                                .getAscendantsDepartement(
                                    code,
                                    this.formatValidParameterDateIfIsNull(dateString),
                                    this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire))),
                    header,
                    Territoires.class,
                    Territoire.class);
        }
    }

    @Path(ConstGeoApi.PATH_DEPARTEMENT + CODE_PATTERN + ConstGeoApi.PATH_DESCENDANT)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_DESCENDANTS,
        summary = "Informations concernant les territoires inclus dans le departement",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getDescendants(
        @Parameter(
            description = ConstGeoApi.PATTERN_DEPARTEMENT_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_DEPARTEMENT,
                type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer les territoires inclus dans le département actif à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')",
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE) Date date,
        @Parameter(
            description = LITTERAL_PARAMETER_TYPE_DESCRIPTION,
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING)) @QueryParam(
                value = Constants.PARAMETER_TYPE) String typeTerritoire,	       
        @Parameter(
    	                description = LITTERAL_PARAMETER_NAME_DESCRIPTION,
    	                required = false,
    	                schema = @Schema(type = Constants.TYPE_STRING)) @QueryParam(
    	                    value = Constants.PARAMETER_FILTRE) FiltreNom filtreNom) {
        String dateString = null;
        if (date != null){
            dateString = date.getString();
        }
        String filtreNomString = (filtreNom != null) ? sanitizeFiltreNom(filtreNom.getString()) : null;
        if ( ! this.verifyParametersTypeAndDateAreValid(typeTerritoire, dateString)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseListOfTerritoire(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries
                                .getDescendantsDepartement(
                                    code,
                                    this.formatValidParameterDateIfIsNull(dateString),
                                    this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire),this.formatValidParameterFiltreIfIsNull(filtreNomString))),
                    header,
                    Territoires.class,
                    Territoire.class);
        }
    }
    private String sanitizeFiltreNom(String filtreNom) {
        if (filtreNom == null || filtreNom.isEmpty()) {
            return null;
        }
        return filtreNom.replaceAll("[<>\"']", "");
    }

    @Path(ConstGeoApi.PATH_LISTE_DEPARTEMENT)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_LISTE,
        summary = "Informations sur tous les départements actifs à la date donnée. Par défaut, c’est la date courante.",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = Departement.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getListe(
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer le département actif à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')" + LITTERAL_PARAMETER_DATE_WITH_HISTORY,
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE) Date date) {
        String dateString = null;
        if (date != null){
            dateString = date.getString();
        }
        if ( ! this.verifyParameterDateIsRightWithHistory(dateString)) {
            return this.generateBadRequestResponse();
        }
        else {

            return this
                .generateResponseListOfTerritoire(
                    sparqlUtils.executeSparqlQuery(GeoQueries.getListDepartements(this.formatValidParameterDateIfIsNull(dateString))),
                    header,
                    Departements.class,
                    Departement.class);
        }
    }

    @Path(ConstGeoApi.PATH_DEPARTEMENT + CODE_PATTERN + ConstGeoApi.PATH_SUIVANT)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_SUIVANT,
        summary = "Informations concernant les départements qui succèdent au département",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(implementation = Departement.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getSuivant(
        @Parameter(
            description = ConstGeoApi.PATTERN_DEPARTEMENT_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_DEPARTEMENT,
                type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour préciser le departement de départ. Par défaut, c’est la date courante qui est utilisée. (Format : 'AAAA-MM-JJ')",
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
                            GeoQueries.getNextDepartement(code, this.formatValidParameterDateIfIsNull(dateString))),
                    header,
                    Departements.class,
                    Departement.class);
        }
    }

    @Path(ConstGeoApi.PATH_DEPARTEMENT + CODE_PATTERN + ConstGeoApi.PATH_PRECEDENT)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_PRECEDENT,
        summary = "Informations concernant les departements qui précèdent le departement",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(implementation = Departement.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getPrecedent(
        @Parameter(
            description = ConstGeoApi.PATTERN_DEPARTEMENT_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_DEPARTEMENT,
                type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour préciser le departement de départ. Par défaut, c’est la date courante qui est utilisée. (Format : 'AAAA-MM-JJ')",
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
                            GeoQueries.getPreviousDepartement(code, this.formatValidParameterDateIfIsNull(dateString))),
                    header,
                    Departements.class,
                    Departement.class);
        }
    }

    @Path(ConstGeoApi.PATH_DEPARTEMENT + CODE_PATTERN + ConstGeoApi.PATH_PROJECTION)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_PROJECTION,
        summary = "Informations concernant les départements qui résultent de la projection du département à la date passée en paramètre. ",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(implementation = Departement.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getProjection(
        @Parameter(
            description = ConstGeoApi.PATTERN_DEPARTEMENT_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_DEPARTEMENT,
                type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour préciser le departement de départ. Par défaut, c’est la date courante qui est utilisée. (Format : 'AAAA-MM-JJ')",
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE) Date date,
        @Parameter(
            description = "Date vers laquelle est projetée le departement. Paramètre obligatoire (Format : 'AAAA-MM-JJ', erreur 400 si absent)",
            required = true,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE, example=LITTERAL_DATE_EXAMPLE)) @QueryParam(
                value = Constants.PARAMETER_DATE_PROJECTION) Date dateProjection) {
        String dateString = null;
        if (date != null){
            dateString = date.getString();
        }
        if ( ! this.verifyParameterDateIsRightWithoutHistory(dateString) || ! this.verifyParameterDateIsRightWithoutHistory(dateProjection.getString())) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseListOfTerritoire(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries
                                .getProjectionDepartement(
                                    code,
                                    this.formatValidParameterDateIfIsNull(dateString),
                                    dateProjection.getString())),
                    header,
                    Departements.class,
                    Departement.class);
        }
    }

    @Hidden
    @Path(ConstGeoApi.PATH_LISTE_DEPARTEMENT + ConstGeoApi.PATH_PROJECTION)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_PROJECTIONS,
        summary = "Récupérer la projection des departements vers la date passée en paramètre.",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(implementation = Projections.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getAllProjections(
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour préciser les departements de départ. Par défaut, c’est la date courante qui est utilisée.",
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE) Date date,
        @Parameter(
            description = "Date vers laquelle sont projetées les departements. Paramètre obligatoire (erreur 400 si absent)",
            required = true,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE_PROJECTION) Date dateProjection) {
        String dateString = null;
        String dateProjectionString = null;
        if (date != null){
            dateString = date.getString();
        }
        if (dateProjection != null){
            dateProjectionString = dateProjection.getString();
        }
        if ( ! this.verifyParameterDateIsRightWithoutHistory(dateString) || ! this.verifyParameterDateIsRightWithoutHistory(dateProjectionString)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseListOfProjection(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries
                                .getAllProjectionDepartement(
                                    this.formatValidParameterDateIfIsNull(dateString),
                                    dateProjectionString)),
                    header);
        }
    } 
}
