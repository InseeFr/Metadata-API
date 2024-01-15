package fr.insee.rmes.api.geo.territoire;

import fr.insee.rmes.api.geo.AbstractGeoApi;
import fr.insee.rmes.api.geo.ConstGeoApi;
import fr.insee.rmes.modeles.geo.territoire.Canton;
import fr.insee.rmes.modeles.geo.territoire.Commune;
import fr.insee.rmes.modeles.geo.territoire.Iris;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoires.Cantons;
import fr.insee.rmes.modeles.geo.territoires.Communes;
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
public class IrisApi extends AbstractGeoApi {
    private static final String CODE_PATTERN = "/{code:}";
    private static final String LITTERAL_PARAMETER_TYPE_DESCRIPTION = "Filtre sur le type de territoire renvoyé.";
    private static final String LITTERAL_PARAMETER_NAME_DESCRIPTION = "Filtre sur le nom des communes renvoyées" ;

    private static final String LITTERAL_RESPONSE_DESCRIPTION = "Iris";
    private static final String LITTERAL_ID_OPERATION = "getcogiris";
    private static final String LITTERAL_CODE_EXAMPLE = "010040101";

    private static final String LITTERAL_CODE_HISTORY_EXAMPLE = "010040101";
    private static final String LITTERAL_PARAMETER_DATE_DESCRIPTION =
            "Filtre pour renvoyer l'Iris active à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')";
    private static final String LITTERAL_OPERATION_SUMMARY =
            "Informations sur un Iris identifié par son code (quatre chiffres pour la métropole ou cinq pour les DOM ou 2A/2B plus deux chiffres pour la Corse)";

    private static final String LITTERAL_DATE_EXAMPLE = "2020-01-01";

    @Path(ConstGeoApi.PATH_IRIS + CODE_PATTERN)
    @GET
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(operationId = LITTERAL_ID_OPERATION, summary = LITTERAL_OPERATION_SUMMARY, responses = {
            @ApiResponse(
                    content = @Content(schema = @Schema(implementation = Iris.class)),
                    description = LITTERAL_RESPONSE_DESCRIPTION)

    })
    public Response getByCode(
            @Parameter(
                    description = ConstGeoApi.PATTERN_IRIS_DESCRIPTION,
                    required = true,
                    schema = @Schema(
                            pattern = ConstGeoApi.PATTERN_IRIS,
                            type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
            @Parameter(
                    description = LITTERAL_PARAMETER_DATE_DESCRIPTION,
                    required = false,
                    schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                    value = Constants.PARAMETER_DATE) String date) {

        if (!code.matches(ConstGeoApi.PATTERN_IRIS)) {
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
                                            GeoQueries.getIrisByCodeAndDate(code, this.formatValidParameterDateIfIsNull(date))),
                            header,
                            new Iris(code));
        }
    }


}
