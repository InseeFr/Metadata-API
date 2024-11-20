package fr.insee.rmes.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;

import fr.insee.rmes.modeles.utils.Header;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.text.StringEscapeUtils;

import java.net.URI;
import java.net.URISyntaxException;

@Path("/technicalService")
public class DescribeApi extends AbstractMetadataApi {


	@GET
	@Path("/describe")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId = "describeUri", summary = "Service pour déréférencer une URI")
	public Response describeUri(
			@Parameter(required = true, description = "Iri à déréférencer", schema = @Schema(type = "string")) @QueryParam("uri") String uri,
			@Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String acceptHeader) {

		// Validation de l'URI
		if (StringUtils.isEmpty(uri) || !isValidUri(uri)) {
			return Response.status(Status.BAD_REQUEST).entity("Invalid URI").build();
		}

		// Exécution de la requête ASK
		Boolean result = sparqlUtils.executeAskQuery("ASK WHERE { { <" + escapeSparql(uri) + "> ?p ?o . } UNION { ?s ?p <" + escapeSparql(uri) + "> . }} ");

		if (Boolean.FALSE.equals(result)) {
			return Response.status(Status.NOT_FOUND).entity("URI not found").build();
		} else {
			// Récupération de la description et encodage du résultat
			String queryResult = sparqlUtils.executeSparqlQuery("DESCRIBE <" + escapeSparql(uri) + ">", sanitizeHeader(acceptHeader));
			return Response.ok(encodeForJson(queryResult)).build();
		}
	}

	// Méthode pour valider l'URI
	private boolean isValidUri(String uri) {
		try {
			new URI(uri);
			return true;
		} catch (URISyntaxException e) {
			return false;
		}
	}

	// Échappement des caractères spéciaux dans les requêtes SPARQL
	private String escapeSparql(String input) {
		return input.replace("\"", "\\\"").replace("<", "\\u003C").replace(">", "\\u003E");
	}

	// Encodage des résultats pour JSON afin de prévenir XSS
	private String encodeForJson(String input) {
		return StringEscapeUtils.escapeJson(input);
	}

	// Sanitize du header
	private String sanitizeHeader(String header) {
		return header != null ? header.replaceAll("[\n\r]", "") : "";
	}
}
    
    
