package fr.insee.rmes.api.classifications;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
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

@Path("/nomenclatures")
public class ClassificationsApi {
	

	@SuppressWarnings("unchecked")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllClassifications(@HeaderParam(value = HttpHeaders.ACCEPT) String header) {

		String csvResult = SparqlUtils.executeSparqlQuery(ClassificationsQueries.getAllClassifications());
		List<Classification> itemsList = (List<Classification>) CSVUtils.populateMultiPOJO(csvResult, Classification.class);
		
		if (itemsList.size() == 0) { 
			return Response.status(Status.NOT_FOUND).entity("").build();
		}else if (header.equals(MediaType.APPLICATION_XML)) {
			return Response.ok(ResponseUtils.produceResponse(new Classifications(itemsList), header)).build();
		}else {
			return Response.ok(ResponseUtils.produceResponse(itemsList, header)).build();
		}
	}

	
}
