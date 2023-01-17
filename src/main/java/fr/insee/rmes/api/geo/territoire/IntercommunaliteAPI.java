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
import fr.insee.rmes.modeles.geo.territoire.Intercommunalite;
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

public class IntercommunaliteAPI extends AbstractGeoApi {
		
		private static final String CODE_PATTERN_INTERCO = "/{code: " + ConstGeoApi.PATTERN_INTERCO + "}";
	    private static final String LITTERAL_ID_OPERATION = "getcogInterco";
	    private static final String LITTERAL_OPERATION_SUMMARY =
	        "Informations sur une intercommunalité identifiée par son code (neuf caractères)";
	    private static final String LITTERAL_RESPONSE_DESCRIPTION = "intercommunalité";
	    private static final String LITTERAL_PARAMETER_DATE_DESCRIPTION =
	        "Filtre pour renvoyer l'intercommunalité active à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')";
	    private static final String LITTERAL_CODE_EXAMPLE = "240100883";
	    
	    @Path(ConstGeoApi.PATH_INTERCO + CODE_PATTERN_INTERCO)
	    @GET
	    @Produces({
	        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
	    })
	    @Operation(
	        operationId = LITTERAL_ID_OPERATION,
	        summary = LITTERAL_OPERATION_SUMMARY,
	        responses = {
	            @ApiResponse(
	                content = @Content(schema = @Schema(implementation = Intercommunalite.class)),
	                description = LITTERAL_RESPONSE_DESCRIPTION)
	        })
	    public Response getByCode(
	        @Parameter(
	            description = ConstGeoApi.PATTERN_INTERCO_DESCRIPTION,
	            required = true,
	            schema = @Schema(
	                pattern = ConstGeoApi.PATTERN_INTERCO,
	                type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
	        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
	        @Parameter(
	            description = LITTERAL_PARAMETER_DATE_DESCRIPTION,
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
	                            GeoQueries.getIntercommunaliteByCodeAndDate(code, this.formatValidParameterDateIfIsNull(date))),
	                    header,
	                    new Intercommunalite(code));
	        }
	    }

}
