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
import fr.insee.rmes.modeles.geo.territoire.Commune;
import fr.insee.rmes.modeles.geo.territoire.CommuneAssociee;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
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
public class CommuneAssocieeApi extends AbstractGeoApi {

    private static Logger logger = LogManager.getLogger(CommuneAssocieeApi.class);

    private static final String CODE_PATTERN = "/{code: " + ConstGeoApi.PATTERN_COMMUNE + "}";
    private static final String LITTERAL_ID_OPERATION = "getcogcomass";
    private static final String LITTERAL_OPERATION_SUMMARY =
        "Informations sur une commune française identifiée par son code (cinq caractères)";
    private static final String LITTERAL_OPERATION_DESCRIPTION =
        "Cette requête renvoie également les communes des collectivités d'Outre-Mer";
    private static final String LITTERAL_RESPONSE_DESCRIPTION = "Commune associée";
    private static final String LITTERAL_PARAMETER_DATE_DESCRIPTION =
        "Filtre pour renvoyer la commune associée active à la date donnée. Par défaut, c’est la date courante.";
    private static final String LITTERAL_PARAMETER_TYPE_DESCRIPTION = "Filtre sur le type de territoire renvoyé.";

    @Path(ConstGeoApi.PATH_COMMUNE_ASSOCIEE + CODE_PATTERN)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION,
        summary = LITTERAL_OPERATION_SUMMARY,
        description = LITTERAL_OPERATION_DESCRIPTION,
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(implementation = CommuneAssociee.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getCommune(
        @Parameter(
            description = ConstGeoApi.PATTERN_COMMUNE_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_COMMUNE,
                type = Constants.TYPE_STRING)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = LITTERAL_PARAMETER_DATE_DESCRIPTION,
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE) String date) {

        logger.debug("Received GET request for commune associée {}", code);

        if ( ! this.verifyParameterDateIsRight(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseATerritoireByCode(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries
                                .getCommuneAssocieeByCodeAndDate(code, this.formatValidParameterDateIfIsNull(date))),
                    header,
                    new Commune(code));
        }
    }

    @Path(ConstGeoApi.PATH_COMMUNE_ASSOCIEE + CODE_PATTERN + ConstGeoApi.PATH_ASCENDANT)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_ASCENDANTS,
        summary = "Récupérer les informations concernant les territoires qui contiennent la commune",
        description = LITTERAL_OPERATION_DESCRIPTION,
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getAscendantsFromCommune(
        @Parameter(
            description = ConstGeoApi.PATTERN_COMMUNE_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_COMMUNE,
                type = Constants.TYPE_STRING)) @PathParam(Constants.CODE) String code,
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

        logger.debug("Received GET request for ascendants of commune associée {}", code);

        if ( ! this.verifyParametersTypeAndDateAreValid(typeTerritoire, date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseListOfTerritoire(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries
                                .getAscendantsCommuneAssociee(
                                    code,
                                    this.formatValidParameterDateIfIsNull(date),
                                    this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire))),
                    header,
                    Territoire.class);
        }
    }

    @Path(ConstGeoApi.PATH_LISTE_COMMUNE_ASSOCIEE)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_LISTE,
        summary = "La requête renvoie toutes les communes associée actives à la date donnée. Par défaut, c’est la date courante.",
        description = LITTERAL_OPERATION_DESCRIPTION,
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = Commune.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getListeCommunes(
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = LITTERAL_PARAMETER_DATE_DESCRIPTION,
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE) String date) {

        logger.debug("Received GET request for all communes associées");

        if ( ! this.verifyParameterDateIsRight(date)) {
            return this.generateBadRequestResponse();
        }
        else {

            return this
                .generateResponseListOfTerritoire(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries.getListCommunesAssociees(this.formatValidParameterDateIfIsNull(date))),
                    header,
                    CommuneAssociee.class);
        }
    }
}
