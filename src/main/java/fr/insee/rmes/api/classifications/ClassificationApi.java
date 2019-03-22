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

import fr.insee.rmes.api.utils.CSVUtils;
import fr.insee.rmes.api.utils.ResponseUtils;
import fr.insee.rmes.api.utils.SparqlUtils;

@Path("/nomenclature")
public class ClassificationApi {
	
	private static Logger logger = LogManager.getLogger(ClassificationApi.class);


	
	@GET
	@Path("/{code}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@SuppressWarnings("unchecked")
	public Response getClassificationByCode(
			@PathParam("code") String code,
			@HeaderParam(value = HttpHeaders.ACCEPT) String header) {
		logger.debug("Received GET request for classification " + code);
		
		String csvResult = SparqlUtils.executeSparqlQuery(ClassificationsQueries.getClassification(code));
		List<Poste> itemsList = (List<Poste>) CSVUtils.populateMultiPOJO(csvResult, Poste.class);

		if (itemsList.size() == 0) {
			return Response.status(Status.NOT_FOUND).entity("").build();
		}else if (header.equals(MediaType.APPLICATION_XML)) {
			List<? extends Poste> itemsListXml = (List<PosteXml>) CSVUtils.populateMultiPOJO(csvResult, PosteXml.class);
			return Response.ok(ResponseUtils.produceResponse(new Postes(itemsListXml), header)).build();
		}else {
			List<PosteJson> itemsListJson = (List<PosteJson>) CSVUtils.populateMultiPOJO(csvResult, PosteJson.class);
			return Response.ok(ResponseUtils.produceResponse(itemsListJson, header)).build();
		}
	}

}
