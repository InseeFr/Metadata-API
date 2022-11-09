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
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoire.ZoneDEmploi2020;
import fr.insee.rmes.modeles.geo.territoires.Territoires;
import fr.insee.rmes.modeles.geo.territoires.ZonesDEmploi2020;
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
public class ZoneEmploiApi  extends AbstractGeoApi {

	private static final String CODE_PATTERN = "/{code: " + ConstGeoApi.PATTERN_ZONE_EMPLOI + "}";
	private static final String LITTERAL_ID_OPERATION = "getcogze";
	private static final String LITTERAL_OPERATION_SUMMARY =
			"Informations sur une zone d'emploi française identifiée par son code (quatre chiffres)";
	private static final String LITTERAL_RESPONSE_DESCRIPTION = "Zone d'emploi";
	private static final String LITTERAL_PARAMETER_DATE_DESCRIPTION =
			"Filtre pour renvoyer la zone d'emploi active à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')";
	private static final String LITTERAL_PARAMETER_TYPE_DESCRIPTION = "Filtre sur le type de territoire renvoyé.";

	private static final String LITTERAL_CODE_EXAMPLE = "2415";


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
							content = @Content(schema = @Schema(implementation = ZoneDEmploi2020.class)),
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
							new ZoneDEmploi2020(code));
		}
	}

	@Path(ConstGeoApi.PATH_ZONE_EMPLOI + CODE_PATTERN + ConstGeoApi.PATH_DESCENDANT)
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_DESCENDANTS,
        summary = "Informations concernant les territoires inclus dans la zone d'emploi",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
                description = LITTERAL_RESPONSE_DESCRIPTION)
        })
    public Response getDescendants(
        @Parameter(
            description = ConstGeoApi.PATTERN_ZONE_EMPLOI_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_ZONE_EMPLOI,
                type = Constants.TYPE_STRING, example="2415")) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer les territoires inclus dans la zone d'emploi active à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')",
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.PARAMETER_DATE) String date,
        @Parameter(
            description = LITTERAL_PARAMETER_TYPE_DESCRIPTION,
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, example="ArrondissementMunicipal")) @QueryParam(
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
                                .getDescendantsZoneEmploi(
                                    code,
                                    this.formatValidParameterDateIfIsNull(date),
                                    this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire))),
                    header,
                    Territoires.class,
                    Territoire.class);
        }
    }

	   @Path(ConstGeoApi.PATH_LISTE_ZONE_EMPLOI)
	    @GET
	    @Produces({
	        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
	    })
	    @Operation(
	        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_LISTE,
	        summary = "Informations sur toutes les zones d'emploi actives à la date donnée. Par défaut, c’est la date courante.",
	        responses = {
	            @ApiResponse(
	                content = @Content(schema = @Schema(type = ARRAY, implementation = ZoneDEmploi2020.class)),
	                description = LITTERAL_RESPONSE_DESCRIPTION)
	        })
	    public Response getListe(
	        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
	        @Parameter(
	            description = "Filtre pour renvoyer les zones d'emploi actives à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')" + LITTERAL_PARAMETER_DATE_WITH_HISTORY,
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
	                        .executeSparqlQuery(GeoQueries.getListZonesEmploi(this.formatValidParameterDateIfIsNull(date))),
	                    header,
	                    ZonesDEmploi2020.class,
	                    ZoneDEmploi2020.class);
	        }
	    }
	
}
