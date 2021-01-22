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
import fr.insee.rmes.modeles.geo.territoire.ZoneEmploi;
import fr.insee.rmes.queries.geo.GeoQueries;
import fr.insee.rmes.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public class ZoneEmploiApi  extends AbstractGeoApi {

	private static final String LITTERAL_DATE_EXAMPLE = "1945-06-26";

	private static Logger logger = LogManager.getLogger(CommuneApi.class);

	private static final String CODE_PATTERN = "/{code: " + ConstGeoApi.PATTERN_ZONE_EMPLOI + "}";
	private static final String LITTERAL_ID_OPERATION = "getcogze";
	private static final String LITTERAL_OPERATION_SUMMARY =
			"Informations sur une zone d'emploi française identifiée par son code (quatre chiffres)";
	private static final String LITTERAL_RESPONSE_DESCRIPTION = "Zone d'emploi";
	private static final String LITTERAL_PARAMETER_DATE_DESCRIPTION =
			"Filtre pour renvoyer la zone d'emploi active à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')";
	private static final String LITTERAL_PARAMETER_TYPE_DESCRIPTION = "Filtre sur le type de territoire renvoyé.";

	private static final String LITTERAL_CODE_EXAMPLE = "14475";


	@Path(ConstGeoApi.PATH_ZONE_EMPLOI + CODE_PATTERN)
	@GET
	@Produces({
		MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
	})
	@Operation(
			operationId = LITTERAL_ID_OPERATION,
			summary = LITTERAL_OPERATION_SUMMARY,
			responses = {
					@ApiResponse(
							content = @Content(schema = @Schema(implementation = ZoneEmploi.class)),
							description = LITTERAL_RESPONSE_DESCRIPTION)
			})
	public Response getByCode(
			@Parameter(
					description = ConstGeoApi.PATTERN_ZONE_EMPLOI_DESCRIPTION,
					required = true,
					schema = @Schema(
							pattern = ConstGeoApi.PATTERN_ZONE_EMPLOI,
							type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
			@Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
			@Parameter(
					description = LITTERAL_PARAMETER_DATE_DESCRIPTION,
					required = false,
					schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
							value = Constants.PARAMETER_DATE) String date) {

		logger.debug("Received GET request for zone d'emploi {}", code);

		if ( ! this.verifyParameterDateIsRightWithoutHistory(date)) {
			return this.generateBadRequestResponse();
		}
		else {
			return this
					.generateResponseATerritoireByCode(
							sparqlUtils
							.executeSparqlQuery(
									GeoQueries.getZoneEmploiByCodeAndDate(code, this.formatValidParameterDateIfIsNull(date))),
							header,
							new Commune(code));
		}
	}

}
