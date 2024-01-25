package fr.insee.rmes.api.classifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;

import fr.insee.rmes.api.AbstractMetadataApi;
import fr.insee.rmes.modeles.classification.Poste;
import fr.insee.rmes.modeles.classification.PosteJson;
import fr.insee.rmes.modeles.classification.PosteXml;
import fr.insee.rmes.modeles.classification.Postes;
import fr.insee.rmes.queries.classifications.ClassificationsQueries;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Hidden
@Path("/nomenclature")
@Tag(name = "nomenclatures", description = "Nomenclatures API")
public class ClassificationApi extends AbstractMetadataApi {


	@Hidden
	@GET
	@Path("/{code}/postes")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Operation(operationId = "getClassificationByCode", summary = "Liste des postes d'une nomenclature (autres que \"catégories juridiques\")", responses = {
			@ApiResponse(description = "Liste des postes de la nomenclature", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = ARRAY, implementation = PosteJson.class)),
					@Content(mediaType = MediaType.APPLICATION_XML, schema = @Schema(type = ARRAY, implementation = PosteXml.class)) }) })
	public Response getClassificationByCode(
			@Parameter(required = true, description = "Identifiant de la nomenclature (hors cj)", example = "nafr2") @PathParam("code") String code,
			@Parameter(hidden = true) @HeaderParam(value = HttpHeaders.ACCEPT) String header) {

		final String csvResult = sparqlUtils.executeSparqlQuery(ClassificationsQueries.getClassification(code));
		final List<Poste> itemsList = csvUtils.populateMultiPOJO(csvResult, Poste.class);

		if (itemsList.isEmpty()) {
			return Response.status(Status.NOT_FOUND).entity("").build();
		} else if (header.equals(MediaType.APPLICATION_XML)) {
			final List<? extends Poste> itemsListXml = csvUtils.populateMultiPOJO(csvResult, PosteXml.class);
			return Response.ok(responseUtils.produceResponse(new Postes(itemsListXml), header)).build();
		} else {
			final List<? extends Poste> itemsListJson = csvUtils.populateMultiPOJO(csvResult, PosteJson.class);
			return Response.ok(responseUtils.produceResponse(itemsListJson, header)).build();
		}
	}

	@Hidden
	@GET
	@Path("/{code}/arborescence")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Operation(operationId = "getClassificationTreeByCode", summary = "Liste des postes d'une nomenclature (autres que \"catégories juridiques\")", responses = {
			@ApiResponse(description = "Liste des postes de la nomenclature", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = ARRAY, implementation = PosteJson.class)),
					@Content(mediaType = MediaType.APPLICATION_XML, schema = @Schema(type = ARRAY, implementation = PosteXml.class)) }) })
	public Response getClassificationTreeByCode(
			@Parameter(required = true, description = "Identifiant de la nomenclature (hors cj)", example = "nafr2") @PathParam("code") String code,
			@Parameter(hidden = true) @HeaderParam(value = HttpHeaders.ACCEPT) String header) {

		final String csvResult = sparqlUtils.executeSparqlQuery(ClassificationsQueries.getClassification(code));
		final List<? extends Poste> itemsList = csvUtils.populateMultiPOJO(csvResult, Poste.class);

		if (itemsList.isEmpty()) {
			return Response.status(Status.NOT_FOUND).entity("").build();
		}

		if (header.equals(MediaType.APPLICATION_XML)) {
			final List<PosteXml> root = this.getTree(csvResult, PosteXml.class);
			return Response.ok(responseUtils.produceResponse(new Postes(root), header)).build();
		} else {
			final List<PosteJson> root = this.getTree(csvResult, PosteJson.class);
			return Response.ok(responseUtils.produceResponse(new Postes(root), header)).build();
		}
	}

	private <P> List<P> getTree(String csvResult, Class<P> posteClass) {
		final List<P> root = new ArrayList<>();
		final List<P> liste = csvUtils.populateMultiPOJO(csvResult, posteClass);
		final Map<String, P> postes = liste.stream()
				.collect(Collectors.toMap(p -> ((Poste) p).getCode(), Function.identity()));

		for (final P poste : liste) {
			if (StringUtils.isNotEmpty(((Poste) poste).getCodeParent())) {
				final P posteParent = postes.get(((Poste) poste).getCodeParent());
				((Poste) posteParent).addPosteEnfant((Poste) poste);
			} else {
				root.add(poste);
			}
		}
		return root;
	}

}
