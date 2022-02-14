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
import fr.insee.rmes.modeles.geo.territoire.CirconscriptionTerritoriale;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoires.Territoires;
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

public class CirconscriptionTerritorialeApi extends AbstractGeoApi {
		
		    private static Logger logger = LogManager.getLogger(CirconscriptionTerritorialeApi.class);
		    private static final String CODE_PATTERNCIRCO_TER = "/{code: " + ConstGeoApi.PATTERN_CIRCO_TER + "}";
		    private static final String LITTERAL_ID_OPERATION = "getcogdistrict";
		    private static final String LITTERAL_OPERATION_SUMMARY =
		        "Informations sur une circonscription territoriale identifiée par son code (cinq caractères)";
		    private static final String LITTERAL_RESPONSE_DESCRIPTION = "circonscription territoriale d'une collectivité d'outre-mer";
		    private static final String LITTERAL_PARAMETER_DATE_DESCRIPTION =
		        "Filtre pour renvoyer la circonscription territoriale active à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')";
		    private static final String LITTERAL_PARAMETER_TYPE_DESCRIPTION = "Filtre sur le type de territoire renvoyé.";
		    private static final String LITTERAL_CODE_EXAMPLE = "98611";
		    
		    
		    @Path(ConstGeoApi.PATH_CIRCO_TER + CODE_PATTERNCIRCO_TER)
		    @GET
		    @Produces({
		        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
		    })
		    @Operation(
		        operationId = LITTERAL_ID_OPERATION,
		        summary = LITTERAL_OPERATION_SUMMARY,
		        responses = {
		            @ApiResponse(
		                content = @Content(schema = @Schema(implementation = CirconscriptionTerritoriale.class)),
		                description = LITTERAL_RESPONSE_DESCRIPTION)
		        })
		    public Response getByCode(
		        @Parameter(
		            description = ConstGeoApi.PATTERN_CIRCO_TER_DESCRIPTION,
		            required = true,
		            schema = @Schema(
		                pattern = ConstGeoApi.PATTERN_CIRCO_TER,
		                type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
		        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
		        @Parameter(
		            description = LITTERAL_PARAMETER_DATE_DESCRIPTION,
		            required = false,
		            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
		                value = Constants.PARAMETER_DATE) String date) {

		        logger.debug("Received GET request for collectivite d'outre-mer {}", code);

		        if ( ! this.verifyParameterDateIsRightWithoutHistory(date)) {
		            return this.generateBadRequestResponse();
		        }
		        else {
		            return this
		                .generateResponseATerritoireByCode(
		                    sparqlUtils
		                        .executeSparqlQuery(
		                            GeoQueries.getCirconscriptionTerritorialeByCodeAndDate(code, this.formatValidParameterDateIfIsNull(date))),
		                    header,
		                    new CirconscriptionTerritoriale(code));
		        }
		    }
		    
		    @Path(ConstGeoApi.PATH_CIRCO_TER + CODE_PATTERNCIRCO_TER + ConstGeoApi.PATH_ASCENDANT)
		    @GET
		    @Produces({
		        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
		    })
		    @Operation(
		        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_ASCENDANTS,
		        summary = "Informations concernant les territoires qui contiennent la circonscription territoriale",
		        responses = {
		            @ApiResponse(
		                content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
		                description = LITTERAL_RESPONSE_DESCRIPTION)
		        })
		    public Response getAscendants(
		        @Parameter(
		            description = ConstGeoApi.PATTERN_CIRCO_TER_DESCRIPTION,
		            required = true,
		            schema = @Schema(
		                pattern = ConstGeoApi.PATTERN_CIRCO_TER,
		                type = Constants.TYPE_STRING, example="98611")) @PathParam(Constants.CODE) String code,
		        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
		        @Parameter(
		            description = "Filtre pour renvoyer les territoires contenant la circonscription territoriale actif à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')",
		            required = false,
		            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
		                value = Constants.PARAMETER_DATE) String date,
		        @Parameter(
		            description = LITTERAL_PARAMETER_TYPE_DESCRIPTION,
		            required = false,
		            schema = @Schema(type = Constants.TYPE_STRING)) @QueryParam(
		                value = Constants.PARAMETER_TYPE) String typeTerritoire) {

		        logger.debug("Received GET request for ascendants of circonscription territoriale {}", code);

		        if ( ! this.verifyParametersTypeAndDateAreValid(typeTerritoire, date)) {
		            return this.generateBadRequestResponse();
		        }
		        else {
		            return this
		                .generateResponseListOfTerritoire(
		                    sparqlUtils
		                        .executeSparqlQuery(
		                            GeoQueries
		                                .getAscendantsCirconscriptionTerritoriale(
		                                    code,
		                                    this.formatValidParameterDateIfIsNull(date),
		                                    this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire))),
		                    header,
		                    Territoires.class,
		                    Territoire.class);
		        }
		    }
		    

}
