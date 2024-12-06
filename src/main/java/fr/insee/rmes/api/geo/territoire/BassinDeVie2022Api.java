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
import fr.insee.rmes.modeles.geo.territoire.BassinDeVie2022;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoires.BassinsDeVie2022;
import fr.insee.rmes.modeles.geo.territoires.Territoires;
import fr.insee.rmes.modeles.utils.Date;
import fr.insee.rmes.modeles.utils.FiltreNom;
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
public class BassinDeVie2022Api extends AbstractGeoApi {

	private static final String CODE_PATTERN_BASSINDEVIE = "/{code: " + ConstGeoApi.PATTERN_BASSINDEVIE + "}";
    private static final String LITTERAL_ID_OPERATION = "getcogBassinDevie2022";
    private static final String LITTERAL_OPERATION_SUMMARY =
        "Informations sur un bassin de vie identifiée par son code (neuf caractères)";
    private static final String LITTERAL_RESPONSE_DESCRIPTION = "bassin de vie 2022";
    private static final String LITTERAL_PARAMETER_DATE_DESCRIPTION =
        "Filtre pour renvoyer le bassin de vie active à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')";
    private static final String LITTERAL_CODE_EXAMPLE = "01004";
    private static final String LITTERAL_PARAMETER_TYPE_DESCRIPTION = "Filtre sur le type de territoire renvoyé.";
    private static final String LITTERAL_PARAMETER_NAME_DESCRIPTION = "Filtre sur le nom de l'intercommunalité" ;
    
    

    @Path(ConstGeoApi.PATH_BASSINDEVIE + CODE_PATTERN_BASSINDEVIE)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION,
        summary = LITTERAL_OPERATION_SUMMARY,
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(implementation = BassinDeVie2022.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getByCode(

            @Parameter(
                    description = ConstGeoApi.PATTERN_BASSINDEVIE_DESCRIPTION,
                    required = true,
                    schema = @Schema(
                            pattern = ConstGeoApi.PATTERN_BASSINDEVIE,
                            type = Constants.TYPE_STRING, example="01004")) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = LITTERAL_PARAMETER_DATE_DESCRIPTION,
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) Date date) {

        // Gérer la nullité de la date
        String dateString = (date != null) ? date.getString() : null;

        // Si la date est invalide ou nulle, retourner une réponse BAD_REQUEST
        if (dateString != null && !this.verifyParameterDateIsRightWithoutHistory(dateString)) {
            return this.generateBadRequestResponse();
        }

        // Requête SPARQL avec la date correcte ou null
        return this.generateResponseATerritoireByCode(
                sparqlUtils.executeSparqlQuery(
                        GeoQueries.getBassinDeVie2022ByCodeAndDate(
                                code, this.formatValidParameterDateIfIsNull(dateString)
                        )
                ),
                header,
                new BassinDeVie2022(code)
        );
    }

    @Path(ConstGeoApi.PATH_LISTE_BASSINDEVIE)
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
            operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_LISTE,
            summary = "Informations sur tous les bassins de vie actifs à la date donnée. Par défaut, c’est la date courante.",
            responses = {
                    @ApiResponse(
                            content = @Content(schema = @Schema(type = ARRAY, implementation = BassinDeVie2022.class)),
                            description = LITTERAL_RESPONSE_DESCRIPTION)
            })
    public Response getListe(

            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = "Filtre pour renvoyer les bassins de vie à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')" + LITTERAL_PARAMETER_DATE_WITH_HISTORY,
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) Date date,
            @Parameter(
                    description = LITTERAL_PARAMETER_NAME_DESCRIPTION,
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, example="Ambérieu-en-Bugey")) @QueryParam(
                    value = Constants.PARAMETER_FILTRE) FiltreNom filtreNom)
    {
        // Gérer la nullité de la date
        String dateString = (date != null) ? date.getString() : null;

        // Validation de la date si elle est fournie
        if (dateString != null && !this.verifyParameterDateIsRightWithHistory(dateString)) {
            return this.generateBadRequestResponse();
        }

        // Validation et encodage du filtreNom
        String filtreNomString = (filtreNom != null) ? sanitizeFiltreNom(filtreNom.getString()) : null;

        // Exécution de la requête SPARQL avec les paramètres validés
        return this.generateResponseListOfTerritoire(
                sparqlUtils.executeSparqlQuery(
                        GeoQueries.getListBassinsDeVie(
                                this.formatValidParameterDateIfIsNull(dateString),
                                this.formatValidParameterFiltreIfIsNull(filtreNomString)
                        )
                ),
                header,
                BassinsDeVie2022.class,
                BassinDeVie2022.class
        );
    }


    // Méthode pour encoder et valider le filtreNom
    private String sanitizeFiltreNom(String filtreNom) {
        if (filtreNom == null || filtreNom.isEmpty()) {
            return null;
        }
        //on peut ajouter d'autres contrôles
        return filtreNom.replaceAll("[<>\"]", "");
    }
    
    @Path(ConstGeoApi.PATH_BASSINDEVIE + CODE_PATTERN_BASSINDEVIE + ConstGeoApi.PATH_DESCENDANT)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_DESCENDANTS,
        summary = "Informations concernant les territoires inclus dans le bassin de vie",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getDescendants(

            @Parameter(
                    description = ConstGeoApi.PATTERN_BASSINDEVIE_DESCRIPTION,
                    required = true,
                    schema = @Schema(
                            pattern = ConstGeoApi.PATTERN_BASSINDEVIE,
                            type = Constants.TYPE_STRING, example="35176")) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = "Filtre pour renvoyer les territoires inclus dans le bassin de vie actif à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')",
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) Date date,
            @Parameter(
                    description = LITTERAL_PARAMETER_TYPE_DESCRIPTION,
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, example="Commune")) @QueryParam(
                    value = Constants.PARAMETER_TYPE) String typeTerritoire) {

        // Gérer la nullité de la date
        String dateString = (date != null) ? date.getString() : null;

        // Validation de la date et du type de territoire
        if (!this.verifyParametersTypeAndDateAreValid(typeTerritoire, dateString)) {
            return this.generateBadRequestResponse();
        }

        // Exécution de la requête SPARQL
        return this
                .generateResponseListOfTerritoire(

                        sparqlUtils.executeSparqlQuery(
                                GeoQueries.getDescendantsBassinDeVie(
                                        code,
                                        this.formatValidParameterDateIfIsNull(dateString),
                                        this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire)
                                )
                        ),
                        header,
                        Territoires.class,
                        Territoire.class
                );
    }
    
}
