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

import fr.insee.rmes.api.MetadataApi;
import fr.insee.rmes.modeles.classification.correspondence.Correspondence;
import fr.insee.rmes.modeles.classification.correspondence.Correspondences;
import fr.insee.rmes.queries.classifications.CorrespondencesQueries;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/correspondances")
@Tag(name = "correspondances", description = "Correspondances API")
public class CorrespondencesApi extends MetadataApi {

    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getAllCorrespondances",
        summary = "Liste des correspondances entre deux nomenclatures",
        responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = Correspondences.class)))
        })
    public Response getAllCorrespondences(@Parameter(hidden = true) @HeaderParam(value = HttpHeaders.ACCEPT) String header) {

        String csvResult = sparqlUtils.executeSparqlQuery(CorrespondencesQueries.getAllCorrespondences());

        List<Correspondence> itemsList = csvUtils.populateMultiPOJO(csvResult, Correspondence.class);

        if (itemsList.size() == 0) {
            return Response.status(Status.NOT_FOUND).entity("").build();
        }
        else if (header.equals(MediaType.APPLICATION_XML)) {
            return Response.ok(responseUtils.produceResponse(new Correspondences(itemsList), header)).build();
        }
        else {
            return Response.ok(responseUtils.produceResponse(itemsList, header)).build();
        }
    }

}
