package fr.insee.rmes.api.correspondences;

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

import fr.insee.rmes.api.utils.CSVUtils;
import fr.insee.rmes.api.utils.ResponseUtils;
import fr.insee.rmes.api.utils.SparqlUtils;

@Path("/correspondances")
public class CorrespondencesApi {
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllCorrespondences(@HeaderParam(value = HttpHeaders.ACCEPT) String header) {

		
		String csvResult = SparqlUtils.executeSparqlQuery(CorrespondencesQueries.getAllCorrespondences());
		
		@SuppressWarnings("unchecked")
		List<CorrespondenceDescription> itemsList = (List<CorrespondenceDescription>) CSVUtils.populateMultiPOJO(csvResult, CorrespondenceDescription.class);
		
		if (itemsList.size() == 0) return Response.status(Status.NOT_FOUND).entity("").build();
		
		else if (header.equals(MediaType.APPLICATION_XML))
			return Response.ok(ResponseUtils.produceResponse(new CorrespondenceDescriptionsList(itemsList), header)).build();
			
		else return Response.ok(ResponseUtils.produceResponse(itemsList, header)).build();
	}

	
	
	@GET
	@Path("/{codeNomenclatureSource}/{codeNomenclatureCible}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getCorrespondencesByIds(
			@PathParam("codeNomenclatureSource") String codeClassification,@PathParam("codeNomenclatureCible") String targetCodeClassification,
			@HeaderParam(value = HttpHeaders.ACCEPT) String header) {

		
		String csvResult = SparqlUtils.executeSparqlQuery(CorrespondencesQueries.getCorrespondencesByIds(codeClassification.toLowerCase(),targetCodeClassification.toLowerCase()));
		
		@SuppressWarnings("unchecked")
		
		/*RawCorrespondence direct mapping from sparql request - correspondences are not symetrical in RDF model */
		List<RawCorrespondence> rawItemsList = (List<RawCorrespondence>) CSVUtils.populateMultiPOJO(csvResult, RawCorrespondence.class);
				
		/*raw sparql resulted must be handled according to source / target classifications */
		//Map<Poste, List<Poste>>  itemsList = CorrespondencesUtils.getTreeMapTargetItemsBySource(codeClassification, targetCodeClassification, rawItemsList);
		
		Correspondences itemsList = CorrespondencesUtils.getCorrespondences(codeClassification, targetCodeClassification, rawItemsList);
		
		if (itemsList.getCorrespondences().isEmpty()) return Response.status(Status.NOT_FOUND).entity("").build();
		
		else if (header.equals(MediaType.APPLICATION_XML))
			return Response.ok(ResponseUtils.produceResponse(/*new RawCorrespondences(rawItemsList)*/itemsList, header)).build();
			
		else return Response.ok(ResponseUtils.produceResponse(itemsList, header)).build();
	}
	
	

}
