package fr.insee.rmes.api.geo.territoire;

import fr.insee.rmes.api.geo.AbstractGeoApi;
import fr.insee.rmes.api.geo.ConstGeoApi;
import fr.insee.rmes.modeles.geo.territoire.CantonOuVille;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoires.CantonsEtVilles;
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

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(ConstGeoApi.PATH_GEO)
@Tag(name = ConstGeoApi.TAG_NAME, description = ConstGeoApi.TAG_DESCRIPTION)
public class CantonOuVilleApi extends AbstractGeoApi {

    private static final String CODE_PATTERN = "/{code}";
    private static final String LITTERAL_ID_OPERATION = "getcogcantonouville";
    private static final String LITTERAL_OPERATION_SUMMARY =
            "Informations sur un canton-ou-ville identifié par son code (quatre chiffres pour la métropole ou cinq pour les DOM)";
    private static final String LITTERAL_PARAMETER_NAME_DESCRIPTION = "Filtre sur le nom des territoires renvoyés" ;
    private static final String LITTERAL_PARAMETER_TYPE_DESCRIPTION = "Filtre sur le type de territoire renvoyé.";

    private static final String LITTERAL_RESPONSE_DESCRIPTION = "CantonOuVille";
    private static final String LITTERAL_CODE_EXAMPLE = "0101";
    private static final String LITTERAL_CODE_HISTORY_EXAMPLE = "0104";
    private static final String LITTERAL_PARAMETER_DATE_DESCRIPTION =
            "Filtre pour renvoyer le canton-ou-ville actif à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')";
    private static final String LITTERAL_DATE_EXAMPLE = "2016-01-01";

    protected CantonOuVilleApi(SparqlUtils sparqlUtils, CSVUtils csvUtils, ResponseUtils responseUtils) {
        super(sparqlUtils, csvUtils, responseUtils);
    }

    public CantonOuVilleApi() {
        // Constructeur par défaut
    }

    @Path(ConstGeoApi.PATH_CANTON_OU_VILLE + CODE_PATTERN)
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(operationId = LITTERAL_ID_OPERATION, summary = LITTERAL_OPERATION_SUMMARY, responses = {
            @ApiResponse(
                    content = @Content(schema = @Schema(implementation = CantonOuVille.class)),
                    description = LITTERAL_RESPONSE_DESCRIPTION)
    })
    public Response getByCode(
            @Parameter(
                    description = ConstGeoApi.PATTERN_CANTON_OU_VILLE_DESCRIPTION,
                    required = true,
                    schema = @Schema(
                            pattern = ConstGeoApi.PATTERN_CANTON_OU_VILLE,
                            type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = LITTERAL_PARAMETER_DATE_DESCRIPTION,
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) String date) {

        if (!code.matches(ConstGeoApi.PATTERN_CANTON_OU_VILLE)) {
            String errorMessage = ConstGeoApi.ERREUR_PATTERN;
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorMessage)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        if ( ! this.verifyParameterDateIsRightWithoutHistory(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                    .generateResponseATerritoireByCode(
                            sparqlUtils
                                    .executeSparqlQuery(
                                            GeoQueries.getCantonOuVilleByCodeAndDate(code, this.formatValidParameterDateIfIsNull(date))),
                            header,
                            new CantonOuVille(code));
        }
    }

    @Path(ConstGeoApi.PATH_LISTE_CANTON_OU_VILLE)
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
            operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_LISTE,
            summary = "Informations sur toutes les cantons-et-villes actifs à la date donnée. Par défaut, c’est la date courante.",
            responses = {
                    @ApiResponse(
                            content = @Content(schema = @Schema(type = ARRAY, implementation = CantonOuVille.class)),
                            description = LITTERAL_RESPONSE_DESCRIPTION)
            })
    public Response getListe(
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = "Filtre pour renvoyer les cantons-ou-villes actifs à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')" + LITTERAL_PARAMETER_DATE_WITH_HISTORY,
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
                                    .executeSparqlQuery(GeoQueries.getListCantonsOuVilles(this.formatValidParameterDateIfIsNull(date))),
                            header,
                            CantonsEtVilles.class,
                            CantonOuVille.class);
        }
    }

    @Path(ConstGeoApi.PATH_CANTON_OU_VILLE + CODE_PATTERN + ConstGeoApi.PATH_DESCENDANT)
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
            operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_DESCENDANTS,
            summary = "Informations concernant les territoires inclus dans le canton-ou-ville",
            responses = {
                    @ApiResponse(
                            content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
                            description = LITTERAL_RESPONSE_DESCRIPTION)
            })
    public Response getDescendants(
            @Parameter(
                    description = ConstGeoApi.PATTERN_CANTON_OU_VILLE_DESCRIPTION,
                    required = true,
                    schema = @Schema(
                            pattern = ConstGeoApi.PATTERN_CANTON_OU_VILLE,
                            type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = "Filtre pour renvoyer les territoires inclus dans le canton-ou-ville actif à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')",
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

        if (!code.matches(ConstGeoApi.PATTERN_CANTON_OU_VILLE)) {
            String errorMessage = ConstGeoApi.ERREUR_PATTERN;
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorMessage)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        if (!this.verifyParametersTypeAndDateAreValid(typeTerritoire, date)) {
            return this.generateBadRequestResponse();
        } else {
            return this
                    .generateResponseListOfTerritoire(
                            sparqlUtils
                                    .executeSparqlQuery(
                                            GeoQueries
                                                    .getDescendantsCantonOuVille(
                                                            code,
                                                            this.formatValidParameterDateIfIsNull(date),
                                                            this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire), this.formatValidParameterFiltreIfIsNull(filtreNom))),
                            header,
                            Territoires.class,
                            Territoire.class);
        }
    }


    @Path(ConstGeoApi.PATH_CANTON_OU_VILLE + CODE_PATTERN + ConstGeoApi.PATH_ASCENDANT)
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
            operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_ASCENDANTS,
            summary = "Informations concernant les territoires qui contiennent le canton-ou-ville",
            responses = {
                    @ApiResponse(
                            content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
                            description = LITTERAL_RESPONSE_DESCRIPTION)
            })
    public Response getAscendants(
            @Parameter(
                    description = ConstGeoApi.PATTERN_CANTON_OU_VILLE_DESCRIPTION,
                    required = true,
                    schema = @Schema(
                            pattern = ConstGeoApi.PATTERN_CANTON_OU_VILLE,
                            type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = "Filtre pour renvoyer les territoires contenant le canton-ou-ville actif à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')",
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) String date,
            @Parameter(
                    description = LITTERAL_PARAMETER_TYPE_DESCRIPTION,
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING)) @QueryParam(
                    value = Constants.PARAMETER_TYPE) String typeTerritoire) {

        if (!code.matches(ConstGeoApi.PATTERN_CANTON_OU_VILLE)) {
            String errorMessage = ConstGeoApi.ERREUR_PATTERN;
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorMessage)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        if ( ! this.verifyParametersTypeAndDateAreValid(typeTerritoire, date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                    .generateResponseListOfTerritoire(
                            sparqlUtils
                                    .executeSparqlQuery(
                                            GeoQueries
                                                    .getAscendantsCantonOuVille(
                                                            code,
                                                            this.formatValidParameterDateIfIsNull(date),
                                                            this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire))),
                            header,
                            Territoires.class,
                            Territoire.class);
        }
}

    @Path(ConstGeoApi.PATH_CANTON_OU_VILLE + CODE_PATTERN + ConstGeoApi.PATH_PRECEDENT)
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
            operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_PRECEDENT,
            summary = "Informations concernant les cantons-ou-villes qui précèdent le canton-ou-ville",
            responses = {
                    @ApiResponse(
                            content = @Content(schema = @Schema(implementation = CantonOuVille.class)),
                            description = LITTERAL_RESPONSE_DESCRIPTION)
            })
    public Response getPrecedent(
            @Parameter(
                    description = ConstGeoApi.PATTERN_CANTON_OU_VILLE_DESCRIPTION,
                    required = true,
                    schema = @Schema(
                            pattern = ConstGeoApi.PATTERN_CANTON_OU_VILLE,
                            type = Constants.TYPE_STRING, example=LITTERAL_CODE_HISTORY_EXAMPLE)) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = "Filtre pour préciser le canton-ou-ville de départ. Par défaut, c’est la date courante qui est utilisée. (Format : 'AAAA-MM-JJ')",
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) String date) {

        if (!code.matches(ConstGeoApi.PATTERN_CANTON_OU_VILLE)) {
            String errorMessage = ConstGeoApi.ERREUR_PATTERN;
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorMessage)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        if ( ! this.verifyParameterDateIsRightWithoutHistory(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                    .generateResponseListOfTerritoire(
                            sparqlUtils
                                    .executeSparqlQuery(
                                            GeoQueries.getPreviousCantonOuVille(code, this.formatValidParameterDateIfIsNull(date))),
                            header,
                            CantonsEtVilles.class,
                            CantonOuVille.class);
        }
    }

    @Path(ConstGeoApi.PATH_CANTON_OU_VILLE + CODE_PATTERN + ConstGeoApi.PATH_SUIVANT)
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
            operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_SUIVANT,
            summary = "Informations concernant les régions qui succèdent à la région",
            responses = {
                    @ApiResponse(
                            content = @Content(schema = @Schema(implementation = CantonOuVille.class)),
                            description = LITTERAL_RESPONSE_DESCRIPTION)
            })
    public Response getSuivant(
            @Parameter(
                    description = ConstGeoApi.PATTERN_CANTON_OU_VILLE_DESCRIPTION,
                    required = true,
                    schema = @Schema(
                            pattern = ConstGeoApi.PATTERN_CANTON_OU_VILLE,
                            type = Constants.TYPE_STRING, example="0103")) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = "Filtre pour préciser le canton-ou-ville de départ. Par défaut, c’est la date courante qui est utilisée. (Format : 'AAAA-MM-JJ')",
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE, example=LITTERAL_DATE_EXAMPLE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) String date) {

        if (!code.matches(ConstGeoApi.PATTERN_CANTON_OU_VILLE)) {
            String errorMessage = ConstGeoApi.ERREUR_PATTERN;
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorMessage)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        if ( ! this.verifyParameterDateIsRightWithoutHistory(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                    .generateResponseListOfTerritoire(
                            sparqlUtils
                                    .executeSparqlQuery(
                                            GeoQueries.getNextCantonOuVille(code, this.formatValidParameterDateIfIsNull(date))),
                            header,
                            CantonsEtVilles.class,
                            CantonOuVille.class);
        }
    }

    @Path(ConstGeoApi.PATH_CANTON_OU_VILLE + CODE_PATTERN + ConstGeoApi.PATH_PROJECTION)
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
            operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_PROJECTION,
            summary = "Informations concernant les cantons-ou-villes qui résultent de la projection de la région à la date passée en paramètre. ",
            responses = {
                    @ApiResponse(
                            content = @Content(schema = @Schema(implementation = CantonOuVille.class)),
                            description = LITTERAL_RESPONSE_DESCRIPTION)
            })
    public Response getProjection(
            @Parameter(
                    description = ConstGeoApi.PATTERN_CANTON_OU_VILLE_DESCRIPTION,
                    required = true,
                    schema = @Schema(
                            pattern = ConstGeoApi.PATTERN_CANTON_OU_VILLE,
                            type = Constants.TYPE_STRING, example=LITTERAL_CODE_HISTORY_EXAMPLE)) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = "Filtre pour préciser le canton-ou-ville de départ. Par défaut, c’est la date courante qui est utilisée. (Format : 'AAAA-MM-JJ')",
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) String date,
            @Parameter(
                    description = "Date vers laquelle est projetée le canton-ou-ville. Paramètre obligatoire (Format : 'AAAA-MM-JJ', erreur 400 si absent)",
                    required = true,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE, example=LITTERAL_DATE_EXAMPLE)) @QueryParam(
                    value = Constants.PARAMETER_DATE_PROJECTION) String dateProjection) {

        if (!code.matches(ConstGeoApi.PATTERN_CANTON_OU_VILLE)) {
            String errorMessage = ConstGeoApi.ERREUR_PATTERN;
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorMessage)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        if ( ! this.verifyParameterDateIsRightWithoutHistory(date) || ! this.verifyParameterDateIsRightWithoutHistory(dateProjection)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                    .generateResponseListOfTerritoire(
                            sparqlUtils
                                    .executeSparqlQuery(
                                            GeoQueries
                                                    .getProjectionCantonOuVille(
                                                            code,
                                                            this.formatValidParameterDateIfIsNull(date),
                                                            dateProjection)),
                            header,
                            CantonsEtVilles.class,
                            CantonOuVille.class);
        }
    }


}
