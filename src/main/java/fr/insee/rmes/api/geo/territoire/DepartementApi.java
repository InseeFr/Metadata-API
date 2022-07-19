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

import fr.insee.rmes.api.geo.AbstractGeoApi;
import fr.insee.rmes.api.geo.ConstGeoApi;
import fr.insee.rmes.modeles.geo.territoire.Departement;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoires.Departements;
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
                value = Constants.PARAMETER_DATE) String date) {

        if ( ! this.verifyParameterDateIsRightWithoutHistory(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseATerritoireByCode(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries.getDepartementByCodeAndDate(code, this.formatValidParameterDateIfIsNull(date))),
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
        summary = "Informations concernant les territoires qui contiennent la departement",
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
                                .getAscendantsDepartement(
                                    code,
                                    this.formatValidParameterDateIfIsNull(date),
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
                value = Constants.PARAMETER_DATE) String date,
        @Parameter(
            description = LITTERAL_PARAMETER_TYPE_DESCRIPTION,
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING)) @QueryParam(
                value = Constants.PARAMETER_TYPE) String typeTerritoire,	       
        @Parameter(
    	                description = LITTERAL_PARAMETER_NAME_DESCRIPTION,
    	                required = false,
    	                schema = @Schema(type = Constants.TYPE_STRING)) @QueryParam(
    	                    value = Constants.PARAMETER_FILTRE) String filtreNom) {

        if ( ! this.verifyParametersTypeAndDateAreValid(typeTerritoire, date)) {
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
                                    this.formatValidParameterDateIfIsNull(date),
                                    this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire),this.formatValidParameterFiltreIfIsNull(filtreNom))),
                    header,
                    Territoires.class,
                    Territoire.class);
        }
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
                value = Constants.PARAMETER_DATE) String date) {

        if ( ! this.verifyParameterDateIsRightWithHistory(date)) {
            return this.generateBadRequestResponse();
        }
        else {

            return this
                .generateResponseListOfTerritoire(
                    sparqlUtils.executeSparqlQuery(GeoQueries.getListDepartements(this.formatValidParameterDateIfIsNull(date))),
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
                value = Constants.PARAMETER_DATE) String date) {

        if ( ! this.verifyParameterDateIsRightWithoutHistory(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseListOfTerritoire(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries.getNextDepartement(code, this.formatValidParameterDateIfIsNull(date))),
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
                value = Constants.PARAMETER_DATE) String date) {

        if ( ! this.verifyParameterDateIsRightWithoutHistory(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseListOfTerritoire(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries.getPreviousDepartement(code, this.formatValidParameterDateIfIsNull(date))),
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
                value = Constants.PARAMETER_DATE) String date,
        @Parameter(
            description = "Date vers laquelle est projetée le departement. Paramètre obligatoire (Format : 'AAAA-MM-JJ', erreur 400 si absent)",
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
                                .getProjectionDepartement(
                                    code,
                                    this.formatValidParameterDateIfIsNull(date),
                                    dateProjection)),
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
                value = Constants.PARAMETER_DATE) String date,
        @Parameter(
            description = "Date vers laquelle sont projetées les departements. Paramètre obligatoire (erreur 400 si absent)",
            required = true,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE_PROJECTION) String dateProjection) {

        if ( ! this.verifyParameterDateIsRightWithoutHistory(date) || ! this.verifyParameterDateIsRightWithoutHistory(dateProjection)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseListOfProjection(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries
                                .getAllProjectionDepartement(
                                    this.formatValidParameterDateIfIsNull(date),
                                    dateProjection)),
                    header);
        }
    } 
}
