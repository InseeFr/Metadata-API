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
import fr.insee.rmes.modeles.geo.territoire.Intercommunalite;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoires.Intercommunalites;
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

public class IntercommunaliteAPI extends AbstractGeoApi {
		
		private static final String CODE_PATTERN_INTERCO = "/{code: " + ConstGeoApi.PATTERN_INTERCO + "}";
	    private static final String LITTERAL_ID_OPERATION = "getcogInterco";
	    private static final String LITTERAL_OPERATION_SUMMARY =
	        "Informations sur une intercommunalité identifiée par son code (neuf caractères)";
	    private static final String LITTERAL_RESPONSE_DESCRIPTION = "intercommunalité";
	    private static final String LITTERAL_PARAMETER_DATE_DESCRIPTION =
	        "Filtre pour renvoyer l'intercommunalité active à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')";
	    private static final String LITTERAL_CODE_EXAMPLE = "240100883";
	    private static final String LITTERAL_PARAMETER_TYPE_DESCRIPTION = "Filtre sur le type de territoire renvoyé.";
	    private static final String LITTERAL_PARAMETER_NAME_DESCRIPTION = "Filtre sur le nom de l'intercommunalité" ;
	    private static final String LITTERAL_DATE_EXAMPLE = "2014-01-01";
	    
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
				@Parameter(description = ConstGeoApi.PATTERN_INTERCO_DESCRIPTION,
						required = true,
						schema = @Schema(pattern = ConstGeoApi.PATTERN_INTERCO,
								type = Constants.TYPE_STRING, example = LITTERAL_CODE_EXAMPLE))
				@PathParam(Constants.CODE) String code,
				@Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
				@Parameter(description = LITTERAL_PARAMETER_DATE_DESCRIPTION,
						required = false,
						schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE))
				@QueryParam(value = Constants.PARAMETER_DATE) Date date) {

			String dateString = (date != null) ? date.getString() : null;

			if (!this.verifyParameterDateIsRightWithoutHistory(dateString)) {
				return this.generateBadRequestResponse();
			} else {
				return this.generateResponseATerritoireByCode(
						sparqlUtils.executeSparqlQuery(
								GeoQueries.getIntercommunaliteByCodeAndDate(code, this.formatValidParameterDateIfIsNull(dateString))),
						header,
						new Intercommunalite(code));
			}
		}
	    
	    @Path(ConstGeoApi.PATH_LISTE_INTERCO)
	    @GET
	    @Produces({
	        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
	    })
	    @Operation(
	        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_LISTE,
	        summary = "Informations sur toutes les intercommunalités actives à la date donnée. Par défaut, c’est la date courante.",
	        responses = {
	            @ApiResponse(
	                content = @Content(schema = @Schema(type = ARRAY, implementation = Intercommunalite.class)),
	                description = LITTERAL_RESPONSE_DESCRIPTION)
	        })
		public Response getListe(
				@Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
				@Parameter(description = "Filtre pour renvoyer les intercommunalités à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')",
						required = false,
						schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE))
				@QueryParam(value = Constants.PARAMETER_DATE) Date date,
				@Parameter(description = LITTERAL_PARAMETER_NAME_DESCRIPTION,
						required = false,
						schema = @Schema(type = Constants.TYPE_STRING, example = "Plaine de l'Ain"))
				@QueryParam(value = Constants.PARAMETER_FILTRE) FiltreNom filtreNom) {

			String dateString = (date != null) ? date.getString() : null;
			String filtreNomString = (filtreNom != null) ? sanitizeFiltreNom(filtreNom.getString()) : null;

			if (!this.verifyParameterDateIsRightWithHistory(dateString)) {
				return this.generateBadRequestResponse();
			} else {
				return this.generateResponseListOfTerritoire(
						sparqlUtils.executeSparqlQuery(
								GeoQueries.getListIntercommunalites(this.formatValidParameterDateIfIsNull(dateString),
										this.formatValidParameterFiltreIfIsNull(filtreNomString))),
						header,
						Intercommunalites.class,
						Intercommunalite.class);
			}
		}


		// Méthode pour encoder et valider le filtreNom
		private String sanitizeFiltreNom(String filtreNom) {
			if (filtreNom == null || filtreNom.isEmpty()) {
				return null;
			}
			//on peut ajouter d'autres contrôles
			return filtreNom.replaceAll("[<>\"]", "");
		}

	    @Path(ConstGeoApi.PATH_INTERCO + CODE_PATTERN_INTERCO + ConstGeoApi.PATH_PRECEDENT)
	    @GET
	    @Produces({
	        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
	    })
	    @Operation(
	        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_PRECEDENT,
	        summary = "Informations concernant les intercommunalités qui précèdent l’intercommunalité",
	        responses = {
	            @ApiResponse(
	                content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
	                description = LITTERAL_RESPONSE_DESCRIPTION)
	        })
		public Response getAscendants(
				@Parameter(description = ConstGeoApi.PATTERN_INTERCO_DESCRIPTION,
						required = true,
						schema = @Schema(pattern = ConstGeoApi.PATTERN_INTERCO,
								type = Constants.TYPE_STRING, example = "200046977"))
				@PathParam(Constants.CODE) String code,
				@Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
				@Parameter(description = "Filtre pour renvoyer les informations concernant les intercommunalités qui précèdent l’intercommunalité à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')",
						required = false,
						schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE))
				@QueryParam(value = Constants.PARAMETER_DATE) Date date) {

			String dateString = (date != null) ? date.getString() : null;

			if (!this.verifyParameterDateIsRightWithoutHistory(dateString)) {
				return this.generateBadRequestResponse();
			} else {
				return this.generateResponseListOfTerritoire(
						sparqlUtils.executeSparqlQuery(
								GeoQueries.getPreviousIntercommunalite(code, this.formatValidParameterDateIfIsNull(dateString))),
						header,
						Intercommunalites.class,
						Intercommunalite.class);
			}
		}

	    
	    @Path(ConstGeoApi.PATH_INTERCO + CODE_PATTERN_INTERCO + ConstGeoApi.PATH_DESCENDANT)
	    @GET
	    @Produces({
	        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
	    })
	    @Operation(
	        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_DESCENDANTS,
	        summary = "Informations concernant les territoires inclus dans l'intercommunalite",
	        responses = {
	            @ApiResponse(
	                content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
	                description = LITTERAL_RESPONSE_DESCRIPTION)
	        })
		public Response getDescendants(
				@Parameter(description = ConstGeoApi.PATTERN_INTERCO_DESCRIPTION,
						required = true,
						schema = @Schema(pattern = ConstGeoApi.PATTERN_INTERCO,
								type = Constants.TYPE_STRING, example = "200000438"))
				@PathParam(Constants.CODE) String code,
				@Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
				@Parameter(description = "Filtre pour renvoyer les territoires inclus dans l'intercommunalité active à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')",
						required = false,
						schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE))
				@QueryParam(value = Constants.PARAMETER_DATE) Date date,
				@Parameter(description = LITTERAL_PARAMETER_TYPE_DESCRIPTION,
						required = false,
						schema = @Schema(type = Constants.TYPE_STRING, example = "Commune"))
				@QueryParam(value = Constants.PARAMETER_TYPE) String typeTerritoire) {

			String dateString = (date != null) ? date.getString() : null;

			if (!this.verifyParametersTypeAndDateAreValid(typeTerritoire, dateString)) {
				return this.generateBadRequestResponse();
			} else {
				return this.generateResponseListOfTerritoire(
						sparqlUtils.executeSparqlQuery(
								GeoQueries.getDescendantsIntercommunalite(
										code,
										this.formatValidParameterDateIfIsNull(dateString),
										this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire))),
						header,
						Territoires.class,
						Territoire.class);
			}
		}

	    
	    @Path(ConstGeoApi.PATH_INTERCO + CODE_PATTERN_INTERCO + ConstGeoApi.PATH_SUIVANT)
	    @GET
	    @Produces({
	        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
	    })
	    @Operation(
	        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_SUIVANT,
	        summary = "Informations concernant les intercommunalités qui succèdent à l'intercommunalite",
	        responses = {
	            @ApiResponse(
	                content = @Content(schema = @Schema(implementation = Intercommunalite.class)),
	                description = LITTERAL_RESPONSE_DESCRIPTION)
	        })
		public Response getSuivant(
				@Parameter(description = ConstGeoApi.PATTERN_INTERCO_DESCRIPTION,
						required = true,
						schema = @Schema(pattern = ConstGeoApi.PATTERN_INTERCO,
								type = Constants.TYPE_STRING, example = "246900245"))
				@PathParam(Constants.CODE) String code,
				@Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
				@Parameter(description = "Filtre pour préciser l'intercommunalité de départ. Par défaut, c’est la date courante qui est utilisée. (Format : 'AAAA-MM-JJ')",
						required = false,
						schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE, example = LITTERAL_DATE_EXAMPLE))
				@QueryParam(value = Constants.PARAMETER_DATE) Date date) {

			String dateString = (date != null) ? date.getString() : null;

			if (!this.verifyParameterDateIsRightWithoutHistory(dateString)) {
				return this.generateBadRequestResponse();
			} else {
				return this.generateResponseListOfTerritoire(
						sparqlUtils.executeSparqlQuery(
								GeoQueries.getNextIntercommunalite(code, this.formatValidParameterDateIfIsNull(dateString))),
						header,
						Intercommunalites.class,
						Intercommunalite.class);
			}
		}

	    
	   
	    @Path(ConstGeoApi.PATH_INTERCO + CODE_PATTERN_INTERCO + ConstGeoApi.PATH_PROJECTION)
	    @GET
	    @Produces({
	        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
	    })
	    @Operation(
	        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_PROJECTION,
	        summary = "Informations concernant les intercommunalites qui résultent de la projection de l'intercommunalité à la date passée en paramètre. ",
	        responses = {
	            @ApiResponse(
	                content = @Content(schema = @Schema(implementation = Intercommunalite.class)),
	                description = LITTERAL_RESPONSE_DESCRIPTION)
	        })
		public Response getProjection(
				@Parameter(description = ConstGeoApi.PATTERN_INTERCO_DESCRIPTION,
						required = true,
						schema = @Schema(pattern = ConstGeoApi.PATTERN_INTERCO,
								type = Constants.TYPE_STRING, example = "200046977"))
				@PathParam(Constants.CODE) String code,
				@Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
				@Parameter(description = "Filtre pour préciser l'intercommunalité de départ. Par défaut, c’est la date courante qui est utilisée. (Format : 'AAAA-MM-JJ')",
						required = false,
						schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE))
				@QueryParam(value = Constants.PARAMETER_DATE) Date date,
				@Parameter(description = "Date vers laquelle est projetée l'intercommunalité. Paramètre obligatoire (Format : 'AAAA-MM-JJ', erreur 400 si absent)",
						required = true,
						schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE, example = "2013-01-01"))
				@QueryParam(value = Constants.PARAMETER_DATE_PROJECTION) Date dateProjection) {

			String dateString = (date != null) ? date.getString() : null;
			String dateProjectionString = (dateProjection != null) ? dateProjection.getString() : null;

			if (!this.verifyParameterDateIsRightWithoutHistory(dateString) || !this.verifyParameterDateIsRightWithoutHistory(dateProjectionString)) {
				return this.generateBadRequestResponse();
			} else {
				return this.generateResponseListOfTerritoire(
						sparqlUtils.executeSparqlQuery(
								GeoQueries.getProjectionIntercommunalite(code, this.formatValidParameterDateIfIsNull(dateString), dateProjectionString)),
						header,
						Intercommunalites.class,
						Intercommunalite.class);
			}
		}

}
