package fr.insee.rmes.api.geo.territoire;

import fr.insee.rmes.api.AbstractMetadataApi;
import fr.insee.rmes.api.geo.AbstractGeoApi;
import fr.insee.rmes.api.geo.ConstGeoApi;
import fr.insee.rmes.modeles.geo.territoire.Canton;
import fr.insee.rmes.modeles.geo.territoires.Cantons;
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

    private static final String LITTERAL_RESPONSE_DESCRIPTION = "Canton";
    private static final String LITTERAL_ID_OPERATION = "getcogcanton";

    private static final String LITTERAL_OPERATION_SUMMARY =
            "Informations sur un canton identifié par son code (cinq chiffres)";
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
    public Response getByCode(){
        return null;
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
}
