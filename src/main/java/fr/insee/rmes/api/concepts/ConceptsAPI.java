package fr.insee.rmes.api.concepts;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.api.MetadataApi;
import fr.insee.rmes.modeles.concepts.Definition;
import fr.insee.rmes.modeles.concepts.Definitions;
import fr.insee.rmes.queries.concepts.ConceptsQueries;
import fr.insee.rmes.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/concepts")
@Tag(name = "concepts", description = "Concepts API")
public class ConceptsAPI extends MetadataApi {

    private static Logger logger = LogManager.getLogger(ConceptsAPI.class);

    private ConceptsQueries conceptQueries = new ConceptsQueries();

    @Path("/definitions")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getConcepts",
        summary = "Informations sur les définitions des concepts statistiques de l'Insee",
        responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = Definitions.class)))
        })
    public Response getConcepts(
        @QueryParam("libelle") String libelle,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header) {

        logger.debug("Received GET request concepts");

        String label = StringUtils.isEmpty(libelle) ? "" : libelle;

        String csvResult = sparqlUtils.executeSparqlQuery(conceptQueries.getConceptsByLabel(label));
        List<Definition> conceptList = csvUtils.populateMultiPOJO(csvResult, Definition.class);

        if (conceptList.size() == 0)
            return Response.status(Status.NOT_FOUND).entity("").build();

        else if (StringUtils.equalsAnyIgnoreCase(header, MediaType.APPLICATION_XML))
            return Response.ok(ResponseUtils.produceResponse(new Definitions(conceptList), header)).build();

        else if (StringUtils.equalsAnyIgnoreCase(header, MediaType.APPLICATION_JSON)) {
            return Response.ok(ResponseUtils.produceResponse(conceptList, header)).build();
        }
        else {
            return Response.status(Status.NOT_ACCEPTABLE).entity("").build();
        }

    }

    @Path("/definition/{id : c[0-9]{4}}")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    public Response getConceptById(@PathParam("id") String id, @HeaderParam("accept") String header) {

        logger.debug("Received GET request for Concept: " + id);

        Definition concept = new Definition(id);
        String csvResult = sparqlUtils.executeSparqlQuery(conceptQueries.getConceptById(id));
        csvUtils.populatePOJO(csvResult, concept);

        if (concept.getUri() == null) return Response.status(Status.NOT_FOUND).entity("").build();
        return Response.ok(ResponseUtils.produceResponse(concept, header)).build();
    }

}
