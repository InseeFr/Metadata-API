package fr.insee.rmes.api.correspondences;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fr.insee.rmes.api.utils.CSVUtils;
import fr.insee.rmes.api.utils.ResponseUtils;
import fr.insee.rmes.api.utils.SparqlUtils;

@Path("/correspondances") 
public class CorrespondencesApi {

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllTablesDeCorrespondance(@HeaderParam(value = HttpHeaders.ACCEPT) String header) {

		String csvResult = SparqlUtils.executeSparqlQuery(CorrespondencesQueries.getAllCorrespondences());

		@SuppressWarnings("unchecked")
		List<Correspondence> itemsList = (List<Correspondence>) CSVUtils
				.populateMultiPOJO(csvResult, Correspondence.class);

		if (itemsList.size() == 0)
			return Response.status(Status.NOT_FOUND).entity("").build();

		else if (header.equals(MediaType.APPLICATION_XML))
			return Response.ok(ResponseUtils.produceResponse(new Correspondences(itemsList), header))
					.build();

		else
			return Response.ok(ResponseUtils.produceResponse(itemsList, header)).build();
	}



}
