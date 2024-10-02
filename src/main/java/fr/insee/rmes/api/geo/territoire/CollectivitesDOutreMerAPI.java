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
import fr.insee.rmes.modeles.geo.territoire.CollectiviteDOutreMer;
import fr.insee.rmes.modeles.geo.territoire.Commune;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoires.CollectivitesDOutreMer;
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
	
	public class CollectivitesDOutreMerAPI extends AbstractGeoApi {

	    private static final String CODE_PATTERNCOM = "/{code: " + ConstGeoApi.PATTERN_COM + "}";
	    private static final String LITTERAL_ID_OPERATION = "getcogcom";
	    private static final String LITTERAL_OPERATION_SUMMARY =
	        "Informations sur une collectivité d'outre-mer identifiée par son code (cinq caractères)";
	    private static final String LITTERAL_RESPONSE_DESCRIPTION = "collectivité d'outre-mer";
	    private static final String LITTERAL_PARAMETER_DATE_DESCRIPTION =
	        "Filtre pour renvoyer la collectivite d'outre-mer active à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')";
	    private static final String LITTERAL_PARAMETER_TYPE_DESCRIPTION = "Filtre sur le type de territoire renvoyé.";
	    private static final String LITTERAL_CODE_EXAMPLE = "988";



	    @Path(ConstGeoApi.PATH_LISTE_COM)
	    @GET
	    @Produces({
	        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
	    })
	    
	    @Operation(
	        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_LISTE,
	        summary = "Informations sur toutes les collectivités d'outre-mer actives à la date donnée. Par défaut, c’est la date courante.",
	        responses = {
	            @ApiResponse(
	                content = @Content(schema = @Schema(type = ARRAY, implementation = Commune.class)),
	                description = LITTERAL_RESPONSE_DESCRIPTION)
	        })
	    
	    public Response getListe(
	        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
	        @Parameter(
	            description = "Filtre pour renvoyer les collectivités d'outre-mer actives à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')" + LITTERAL_PARAMETER_DATE_WITH_HISTORY,
	            required = false,
	            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
	                value = Constants.PARAMETER_DATE) Date date)
        {
		String dateString = null;
		if (date != null){
			dateString = date.getString();
		}
       if ( ! this.verifyParameterDateIsRightWithHistory(dateString)) {
           return this.generateBadRequestResponse();
       }
       else {
           return this
               .generateResponseListOfTerritoire(
                   sparqlUtils.executeSparqlQuery(GeoQueries.getListCollectivitesDOutreMer(this.formatValidParameterDateIfIsNull(dateString))),
                   header,
                   CollectivitesDOutreMer.class,
                   CollectiviteDOutreMer.class );
       }
   }
	    
	    
	    @Path(ConstGeoApi.PATH_COM + CODE_PATTERNCOM)
	    @GET
	    @Produces({
	        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
	    })
	    @Operation(
	        operationId = LITTERAL_ID_OPERATION,
	        summary = LITTERAL_OPERATION_SUMMARY,
	        responses = {
	            @ApiResponse(
	                content = @Content(schema = @Schema(implementation = CollectiviteDOutreMer.class)),
	                description = LITTERAL_RESPONSE_DESCRIPTION)
	        })
	    public Response getByCode(
	        @Parameter(
	            description = ConstGeoApi.PATTERN_COM_DESCRIPTION,
	            required = true,
	            schema = @Schema(
	                pattern = ConstGeoApi.PATTERN_COM,
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
	                            GeoQueries.getCollectiviteDOutreMerByCodeAndDate(code, this.formatValidParameterDateIfIsNull(dateString))),
	                    header,
	                    new CollectiviteDOutreMer(code));
	        }
	    }
	    
	    
	    @Path(ConstGeoApi.PATH_COM + CODE_PATTERNCOM + ConstGeoApi.PATH_DESCENDANT)
	    @GET
	    @Produces({
	        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
	    })
	    @Operation(
	        operationId = LITTERAL_ID_OPERATION + ConstGeoApi.ID_OPERATION_DESCENDANTS,
	        summary = "Informations concernant les territoires inclus dans la collectivite d'outre-mer",
	        responses = {
	            @ApiResponse(
	                content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
	                description = LITTERAL_RESPONSE_DESCRIPTION)
	        })
	    public Response getDescendants(
	        @Parameter(
	            description = "code de la collectivité d'outre-mer (3 caractères)",
	            required = true,
	            schema = @Schema(
	                pattern = ConstGeoApi.PATTERN_COM,
	                type = Constants.TYPE_STRING, example=LITTERAL_CODE_EXAMPLE)) @PathParam(Constants.CODE) String code,
	        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
	        @Parameter(
	            description ="Filtre pour renvoyer les territoires inclus dans la collectivité d'outre-mer active à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')",
	            required = false,
	            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
	                value = Constants.PARAMETER_DATE) Date date,
	        @Parameter(
	            description = LITTERAL_PARAMETER_TYPE_DESCRIPTION+ "(Commune ou District)",
	            required = false,
	            schema = @Schema(type = Constants.TYPE_STRING)) @QueryParam(
	                value = Constants.PARAMETER_TYPE) String typeTerritoire,
	        @Parameter(
	                description = "Filtre sur le nom du ou des territoires renvoyés",
	                required = false,
	                schema = @Schema(type = Constants.TYPE_STRING)) @QueryParam(
	                    value = Constants.PARAMETER_FILTRE) FiltreNom filtreNom) {
			String dateString = null;
			if (date != null){
				dateString = date.getString();
			}
	        if ( ! this.verifyParametersTypeAndDateAreValid(typeTerritoire, dateString)) {
	            return this.generateBadRequestResponse();
	        }
			else {
				return this.generateResponseListOfTerritoire(
						sparqlUtils.executeSparqlQuery(GeoQueries.getDescendantsCollectiviteDOutreMer(code,
								this.formatValidParameterDateIfIsNull(dateString),
								this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire),
								this.formatValidParameterFiltreIfIsNull(filtreNom.getString()))),
						header, Territoires.class, Territoire.class);
			}
	    }
	    
	}
