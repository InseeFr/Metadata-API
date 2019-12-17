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
import fr.insee.rmes.modeles.classification.Classification;
import fr.insee.rmes.modeles.classification.Classifications;
import fr.insee.rmes.queries.classifications.ClassificationsQueries;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/nomenclatures")
@Tag(name = "nomenclatures", description = "Nomenclatures API")
public class ClassificationsApi extends MetadataApi {

    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getAllClassifications",
        summary = "Liste des nomenclatures disponibles (autres que \"catégories juridiques\")",
        responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = Classifications.class)), description="Liste de nomenclatures")
        })
    public Response getAllClassifications(
        @Parameter(hidden = true) @HeaderParam(value = HttpHeaders.ACCEPT) String header) {

        String csvResult = sparqlUtils.executeSparqlQuery(ClassificationsQueries.getAllClassifications());
        List<Classification> itemsList = csvUtils.populateMultiPOJO(csvResult, Classification.class);

        if (itemsList.isEmpty()) {
            return Response.status(Status.NOT_FOUND).entity("").build();
        }
        else if (header.equals(MediaType.APPLICATION_XML)) {
            return Response.ok(responseUtils.produceResponse(new Classifications(itemsList), header)).build();
        }
        else {
            return Response.ok(responseUtils.produceResponse(itemsList, header)).build();
        }
    }

}
