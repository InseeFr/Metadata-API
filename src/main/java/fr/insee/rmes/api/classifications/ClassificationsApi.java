package fr.insee.rmes.api.classifications;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.api.classifications.Postes;
import fr.insee.rmes.api.classifications.Classification;
import fr.insee.rmes.api.classifications.Classifications;
import fr.insee.rmes.api.classifications.Poste;
import fr.insee.rmes.api.classifications.ClassificationsQueries;
import fr.insee.rmes.api.utils.CSVUtils;
import fr.insee.rmes.api.utils.ResponseUtils;
import fr.insee.rmes.api.utils.SparqlUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Path("/classifications")
public class ClassificationsApi {
	
	private static Logger logger = LogManager.getLogger(ClassificationsApi.class);

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(nickname = "getClassifications",
			value = "Ensemble des postes d'une nomenclature",
			response = List.class)
	public Response getClassifications(@HeaderParam(value = HttpHeaders.ACCEPT) String header) {

		String csvResult = SparqlUtils.executeSparqlQuery(ClassificationsQueries.getClassificationsDescriptions());
		
		@SuppressWarnings("unchecked")
		List<Classification> itemsList = (List<Classification>) CSVUtils.populateMultiPOJO(csvResult, Classification.class);
		
		if (itemsList.size() == 0) return Response.status(Status.NOT_FOUND).entity("").build();
		
		else if (header.equals(MediaType.APPLICATION_XML))
			return Response.ok(ResponseUtils.produceResponse(new Classifications(itemsList), header)).build();
			
		else return Response.ok(ResponseUtils.produceResponse(itemsList, header)).build();
	}

	
	@GET
	@Path("/{code}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(nickname = "getClassificationByCode",
			value = "Ensemble des postes d'une nomenclature",
			response = List.class)
	public Response getClassificationByCode(
			@ApiParam(value = "Identifiant de la  nomenclature (ex: nafr2, na, etc.)",
					required = true) @PathParam("code") String code,
			@ApiParam(hidden = true) @HeaderParam(value = HttpHeaders.ACCEPT) String header) {

		logger.debug("Received GET request for NAF class " + code);

		
		String csvResult = SparqlUtils.executeSparqlQuery(ClassificationsQueries.getClassification(code));
		
		@SuppressWarnings("unchecked")
		List<Poste> itemsList = (List<Poste>) CSVUtils.populateMultiPOJO(csvResult, Poste.class);
		
		if (itemsList.size() == 0) return Response.status(Status.NOT_FOUND).entity("").build();
		
		else if (header.equals(MediaType.APPLICATION_XML))
			return Response.ok(ResponseUtils.produceResponse(new Postes(itemsList), header)).build();
			
		else return Response.ok(ResponseUtils.produceResponse(itemsList, header)).build();
	}

}
