package fr.insee.rmes.api.concepts;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.api.utils.CSVUtils;
import fr.insee.rmes.api.utils.ResponseUtils;
import fr.insee.rmes.api.utils.SparqlUtils;

@Path("/definition")
public class ConceptsAPI {

	private static Logger logger = LogManager.getLogger(ConceptsAPI.class);
	
	@SuppressWarnings("unchecked")
	@Path("/concepts")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getConcepts(@QueryParam("libelle") String libelle, @HeaderParam("Accept") String header) {
		
		logger.debug("Received GET request concepts");
		
		String label = libelle == null ? "" : libelle;

		String csvResult = SparqlUtils.executeSimpleSparqlQuery(ConceptsQueries.getConceptsByLabel(label));
		List<Concept> conceptList = (List<Concept>) CSVUtils.populateMultiPOJO(csvResult, Concept.class);
		
		if (conceptList.size() == 0) return Response.status(Status.NOT_FOUND).entity("").build();
		
		else if (header.equals(MediaType.APPLICATION_XML))
			return Response.ok(ResponseUtils.produceResponse(new Concepts(conceptList), header)).build();
			
		else return Response.ok(ResponseUtils.produceResponse(conceptList, header)).build();
		
		
	}
	
	@Path("/concept/{id : c[0-9]{4}}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getConceptById(@PathParam("id") String id, @HeaderParam("Accept") String header) {
		
		logger.debug("Received GET request for Concept: " + id);

		Concept concept = new Concept(id);
		String csvResult = SparqlUtils.executeSimpleSparqlQuery(ConceptsQueries.getConceptById(id));
		CSVUtils.populatePOJO(csvResult, concept);
		
		if (concept.getUri() == null) return Response.status(Status.NOT_FOUND).entity("").build();
		return Response.ok(ResponseUtils.produceResponse(concept, header)).build();
	}

}

