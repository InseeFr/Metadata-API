package fr.insee.rmes.api.concepts;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;

import fr.insee.rmes.api.AbstractMetadataApi;
import fr.insee.rmes.modeles.concepts.Concept;
import fr.insee.rmes.modeles.concepts.ConceptLink;
import fr.insee.rmes.modeles.concepts.Definition;
import fr.insee.rmes.modeles.concepts.Definitions;
import fr.insee.rmes.queries.concepts.ConceptsQueries;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/concepts")
@Tag(name = "concepts", description = "Concepts API")
public class ConceptsAPI extends AbstractMetadataApi {

	@Path("/definitions")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Operation(operationId = "getConcepts", summary = "Informations sur les définitions des concepts statistiques de l'Insee", responses = {
			@ApiResponse(content = {
					@Content(schema = @Schema(type = "array", implementation = Definitions.class), mediaType = MediaType.APPLICATION_XML),
					@Content(schema = @Schema(type = "array", implementation = Definition.class), mediaType = MediaType.APPLICATION_JSON) }, description = "Concepts") })
	public Response getConcepts(
			@Parameter(description = "Recherche dans les libellés", schema = @Schema(type = "string"), example = "élect") @QueryParam("libelle") String libelle,
			@Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header) {

		String label = StringUtils.isEmpty(libelle) ? "" : libelle;

		String csvResult = sparqlUtils.executeSparqlQuery(ConceptsQueries.getConceptsByLabel(label));
		List<Definition> conceptList = csvUtils.populateMultiPOJO(csvResult, Definition.class);

		conceptList.forEach(concept -> {
			if (Boolean.TRUE.equals(concept.getHasLink())) {
				String csvLinks = sparqlUtils.executeSparqlQuery(ConceptsQueries.getConceptLinks(concept.getId()));
				List<ConceptLink> links = csvUtils.populateMultiPOJO(csvLinks, ConceptLink.class);
				concept.setLinks(links);
			}
		});

		if (conceptList.isEmpty()) {
			return Response.status(Status.NOT_FOUND).entity("").build();
		} else if (StringUtils.equalsAnyIgnoreCase(header, MediaType.APPLICATION_XML)) {
			return Response.ok(responseUtils.produceResponse(new Definitions(conceptList), header)).build();
		} else if (StringUtils.equalsAnyIgnoreCase(header, MediaType.APPLICATION_JSON)) {
			return Response.ok(responseUtils.produceResponse(conceptList, header)).build();
		} else {
			return Response.status(Status.NOT_ACCEPTABLE).entity("").build();
		}

	}

	@Path("/definition/{id : c[0-9]{4}}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Operation(operationId = "getconcept", summary = "Informations sur la définition d'un concept statistique de l'Insee", responses = {
			@ApiResponse(content = @Content(schema = @Schema(implementation = Concept.class)), description = "Concept") })
	public Response getConceptById(
			@Parameter(required = true, description = "Identifiant du concept (format : c[0-9]{4})", schema = @Schema(pattern = "c[0-9]{4}", type = "string"), example = "c2066") @PathParam("id") String id,
			@Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header) {

		Concept concept = new Concept(id);
		String csvResult = sparqlUtils.executeSparqlQuery(ConceptsQueries.getConceptById(id));
		concept = (Concept) csvUtils.populatePOJO(csvResult, concept);
		if (Boolean.TRUE.equals(concept.getHasLink())) {
			String csvLinks = sparqlUtils.executeSparqlQuery(ConceptsQueries.getConceptLinks(id));
			List<ConceptLink> links = csvUtils.populateMultiPOJO(csvLinks, ConceptLink.class);
			concept.setLinks(links);
		}

		if (concept.getUri() == null) {
			return Response.status(Status.NOT_FOUND).entity("").build();
		} else {
			return Response.ok(responseUtils.produceResponse(concept, header)).build();
		}
	}
}
