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
import fr.insee.rmes.modeles.geo.territoire.CollectiviteDOutreMer;
import fr.insee.rmes.modeles.geo.territoire.Commune;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoires.CollectivitesDOutreMer;
import fr.insee.rmes.modeles.geo.territoires.Communes;
import fr.insee.rmes.modeles.geo.territoires.Projections;
import fr.insee.rmes.modeles.geo.territoires.Territoires;
import fr.insee.rmes.queries.geo.GeoQueries;
import fr.insee.rmes.utils.Constants;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

	
	@Path(ConstGeoApi.PATH_GEO)
	@Tag(name = ConstGeoApi.TAG_NAME, description = ConstGeoApi.TAG_DESCRIPTION)
	
	public class CollectivitesDOutreMerAPI extends AbstractGeoApi {

	    private static final String LITTERAL_DATE_EXAMPLE = "1945-06-26";

	    private static Logger logger = LogManager.getLogger(CollectiviteDOutreMer.class);

	    private static final String CODE_PATTERN = "/{code: " + ConstGeoApi.PATTERN_COMMUNE + "}";
	    private static final String CODE_PATTERNCOM = "/{code: " + ConstGeoApi.PATTERN_COM + "}";
	    private static final String LITTERAL_ID_OPERATION = "getcogcom";
	    private static final String LITTERAL_OPERATION_SUMMARY =
	        "Informations sur une collectivité d'outre-mer identifiée par son code (cinq caractères)";
	    private static final String LITTERAL_RESPONSE_DESCRIPTION = "collectivité d'outre-mer";
	    private static final String LITTERAL_PARAMETER_DATE_DESCRIPTION =
	        "Filtre pour renvoyer la collectivite d'outre-mer active à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')";
	    private static final String LITTERAL_PARAMETER_TYPE_DESCRIPTION = "Filtre sur le type de territoire renvoyé.";
	    private static final String LITTERAL_PARAMETER_NAME_DESCRIPTION = "Filtre sur le nom de la collectivite d'outre-mer" ;
	    private static final String LITTERAL_CODE_EXAMPLE = "986";
	    private static final String LITTERAL_PARAMETER_COM_DESCRIPTION="Filtre pour inclure ou pas les collectivités d’outre-mer";


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
	                value = Constants.PARAMETER_DATE) String date)
	   
        {

       logger.debug("Received GET request for all collectivités d'outre-mer");

       if ( ! this.verifyParameterDateIsRightWithHistory(date)) {
           return this.generateBadRequestResponse();
       }
       else {
           return this
               .generateResponseListOfTerritoire(
                   sparqlUtils.executeSparqlQuery(GeoQueries.getListCollectivitesDOutreMer(this.formatValidParameterDateIfIsNull(date))),
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
	                content = @Content(schema = @Schema(implementation = Commune.class)),
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
	                value = Constants.PARAMETER_DATE) String date) {

	        logger.debug("Received GET request for commune {}", code);

	        if ( ! this.verifyParameterDateIsRightWithoutHistory(date)) {
	            return this.generateBadRequestResponse();
	        }
	        else {
	            return this
	                .generateResponseATerritoireByCode(
	                    sparqlUtils
	                        .executeSparqlQuery(
	                            GeoQueries.getListCollectivitesDOutreMer(code, this.formatValidParameterDateIfIsNull(date))),
	                    header,
	                    new Commune(code));
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
	            description = ConstGeoApi.PATTERN_COM_DESCRIPTION,
	            required = true,
	            schema = @Schema(
	                pattern = ConstGeoApi.PATTERN_COM,
	                type = Constants.TYPE_STRING, example="986")) @PathParam(Constants.CODE) String code,
	        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
	        @Parameter(
	            description ="Filtre pour renvoyer les territoires inclus dans la collectivité d'outre-mer active à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')",
	            required = false,
	            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
	                value = Constants.PARAMETER_DATE) String date,
	        @Parameter(
	            description = LITTERAL_PARAMETER_TYPE_DESCRIPTION+ "( Commune ou District )",
	            required = false,
	            schema = @Schema(type = Constants.TYPE_STRING, example="CollectiviteDOutreMer")) @QueryParam(
	                value = Constants.PARAMETER_TYPE) String typeTerritoire,
	        @Parameter(
	                description = LITTERAL_PARAMETER_NAME_DESCRIPTION,
	                required = false,
	                schema = @Schema(type = Constants.TYPE_STRING, example="Noumea")) @QueryParam(
	                    value = Constants.PARAMETER_FILTRE) String filtreNom) {

	        logger.debug("Received GET request for descendants of collectivite d'outre-mer {}", code);

	        if ( ! this.verifyParametersTypeAndDateAreValid(typeTerritoire, date)) {
	            return this.generateBadRequestResponse();
	        }
	        else {
	            return this
	                .generateResponseListOfTerritoire(
	                    sparqlUtils
	                        .executeSparqlQuery(
	                            GeoQueries
	                                .getDescendantsCollectiviteDOutreMer(
	                                    code,
	                                    this.formatValidParameterDateIfIsNull(date),
	                                    this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire),filtreNom)),
	                        			header,
	                    Territoires.class,
	                    Territoire.class);
	        }
	    }
	    
	}
