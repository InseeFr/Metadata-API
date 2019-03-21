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

@Path("/correspondance") 
public class CorrespondenceApi {

	@GET
	@Path("/{idNomenclatureSource}/{idNomenclatureCible}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getCorrespondencesByIds(@PathParam("idNomenclatureSource") String codeClassification,
			@PathParam("idNomenclatureCible") String targetCodeClassification,
			@HeaderParam(value = HttpHeaders.ACCEPT) String header) {

		String csvResult = SparqlUtils.executeSparqlQuery(CorrespondencesQueries
				.getCorrespondencesByIds(codeClassification.toLowerCase(), targetCodeClassification.toLowerCase()));

		@SuppressWarnings("unchecked")

		/*RawCorrespondence direct mapping from sparql request - correspondences are not symetrical in RDF model */
		List<RawCorrespondence> rawItemsList = (List<RawCorrespondence>) CSVUtils.populateMultiPOJO(csvResult,
				RawCorrespondence.class);

		if (rawItemsList != null && !rawItemsList.isEmpty()) {

			/*raw sparql result fields order must be handled according to source / target classifications */
			Associations itemsList = CorrespondencesUtils.getCorrespondences(codeClassification,
					targetCodeClassification, rawItemsList);

			return Response.ok(ResponseUtils.produceResponse(itemsList, header)).build();

		}

		else {
			
			return Response.status(Status.NOT_FOUND).entity("").build();
			
		}
	}

}
