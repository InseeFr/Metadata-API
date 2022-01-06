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
	    private static final String LITTERAL_ID_OPERATION = "getcogcom";
	    private static final String LITTERAL_OPERATION_SUMMARY =
	        "Informations sur une commune française identifiée par son code (cinq caractères)";
	    private static final String LITTERAL_RESPONSE_DESCRIPTION = "Commune";
	    private static final String LITTERAL_PARAMETER_DATE_DESCRIPTION =
	        "Filtre pour renvoyer la commune active à la date donnée. Par défaut, c’est la date courante. (Format : 'AAAA-MM-JJ')";
	    private static final String LITTERAL_PARAMETER_TYPE_DESCRIPTION = "Filtre sur le type de territoire renvoyé.";
	    private static final String LITTERAL_PARAMETER_NAME_DESCRIPTION = "Filtre sur le nom de la commune" ;
	    private static final String LITTERAL_CODE_EXAMPLE = "14475";
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
	}
