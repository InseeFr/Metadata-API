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
import fr.insee.rmes.modeles.geo.territoire.District;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoires.Territoires;
import fr.insee.rmes.modeles.utils.Date;
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



public class DistrictApi extends AbstractGeoApi {
		
	    private static final String CODE_PATTERNDISTRICT = "/{code: " + ConstGeoApi.PATTERN_DISTRICT + "}";
	    private static final String LITTERAL_ID_OPERATION = "getcogdistrict";
	    private static final String LITTERAL_OPERATION_SUMMARY =
	        "Informations sur un district identifiée par son code (cinq caractères)";
	    private static final String LITTERAL_RESPONSE_DESCRIPTION = "district d'une collectivité d'outre-mer";
	    private static final String LITTERAL_PARAMETER_DATE_DESCRIPTION =
	        "Filtre pour renvoyer le district actif à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')";
	    private static final String LITTERAL_PARAMETER_TYPE_DESCRIPTION = "Filtre sur le type de territoire renvoyé.";
	    private static final String LITTERAL_CODE_EXAMPLE = "98411";
	    
	    @Path(ConstGeoApi.PATH_DISTRICT + CODE_PATTERNDISTRICT)
	    @GET
	    @Produces({
	        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
	    })
	    @Operation(
	        operationId = LITTERAL_ID_OPERATION,
	        summary = LITTERAL_OPERATION_SUMMARY,
	        responses = {
	            @ApiResponse(
	                content = @Content(schema = @Schema(implementation = District.class)),
	                description = LITTERAL_RESPONSE_DESCRIPTION)
	        })
	    public Response getByCode(
	        @Parameter(
	            description = ConstGeoApi.PATTERN_DISTRICT_DESCRIPTION,
	            required = true,
	            schema = @Schema(
	                pattern = ConstGeoApi.PATTERN_DISTRICT,
	                type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
	        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
	        @Parameter(
	            description = LITTERAL_PARAMETER_DATE_DESCRIPTION,
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
	                            GeoQueries.getDistrictByCodeAndDate(code, this.formatValidParameterDateIfIsNull(dateString))),
	                    header,
	                    new District(code));
	        }
	    }    

	    @Path(ConstGeoApi.PATH_DISTRICT + CODE_PATTERNDISTRICT + ConstGeoApi.PATH_ASCENDANT)
	    @GET
	    @Produces({
	        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
	    })
	    @Operation(
	        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_ASCENDANTS,
	        summary = "Informations concernant les territoires qui contiennent le district",
	        responses = {
	            @ApiResponse(
	                content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
	                description = LITTERAL_RESPONSE_DESCRIPTION)
	        })
	    public Response getAscendants(
	        @Parameter(
	            description = ConstGeoApi.PATTERN_DISTRICT_DESCRIPTION,
	            required = true,
	            schema = @Schema(
	                pattern = ConstGeoApi.PATTERN_DISTRICT,
	                type = Constants.TYPE_STRING, example="98411")) @PathParam(Constants.CODE) String code,
	        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
	        @Parameter(
	            description = "Filtre pour renvoyer les territoires contenant le district actif à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')",
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
	                                .getAscendantsDistrict(
	                                    code,
	                                    this.formatValidParameterDateIfIsNull(dateString),
	                                    this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire))),
	                    header,
	                    Territoires.class,
	                    Territoire.class);
	        }
	    }
}
