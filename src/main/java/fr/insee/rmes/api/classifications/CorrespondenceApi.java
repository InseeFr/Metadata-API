package fr.insee.rmes.api.classifications;

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

import fr.insee.rmes.api.MetadataApi;
import fr.insee.rmes.modeles.classification.correspondence.Associations;
import fr.insee.rmes.modeles.classification.correspondence.RawCorrespondence;
import fr.insee.rmes.queries.classifications.CorrespondencesQueries;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/correspondance")
@Tag(name = "correspondance", description = "Correspondance API")
public class CorrespondenceApi extends MetadataApi {

    @GET
    @Path("/{idCorrespondance}")
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getCorrespondenceByCode",
        summary = "Correspondance entre deux nomenclatures",
        responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = Associations.class)))
        })
    public Response getCorrespondencesById(
        @Parameter(
            required = true,
            description = "Identifiant de la correspondance") @PathParam("idCorrespondance") String idCorrespondance,
        @Parameter(hidden = true) @HeaderParam(value = HttpHeaders.ACCEPT) String header) {

        String csvResult =
            sparqlUtils
                .executeSparqlQuery(CorrespondencesQueries.getCorrespondenceById(idCorrespondance.toLowerCase()));

        /* RawCorrespondence direct mapping from sparql request */
        List<RawCorrespondence> rawItemsList = csvUtils.populateMultiPOJO(csvResult, RawCorrespondence.class);

        if (rawItemsList != null && ! rawItemsList.isEmpty()) {

            /* raw sparql result fields order must be got in shape 1 source -> many targets */
            Associations itemsList = CorrespondencesUtils.getCorrespondenceByCorrespondenceId(rawItemsList);

            return Response.ok(responseUtils.produceResponse(itemsList, header)).build();

        }

        else {

            return Response.status(Status.NOT_FOUND).entity("").build();

        }
    }

    @GET
    @Path("/{idNomenclatureSource}/{idNomenclatureCible}")
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getCorrespondenceByClassificationCodes",
        summary = " Liste des associations de correspondance entre deux nomenclatures",
        responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = Associations.class)))
        })
    public Response getCorrespondenceByIds(
        @Parameter(
            required = true,
            description = "Identifiant de la nomenclature source") @PathParam("idNomenclatureSource") String codeClassification,
        @Parameter(
            required = true,
            description = "Identifiant de la nomenclature cible") @PathParam("idNomenclatureCible") String targetCodeClassification,
        @Parameter(hidden = true) @HeaderParam(value = HttpHeaders.ACCEPT) String header) {

        String csvResult =
            sparqlUtils
                .executeSparqlQuery(
                    CorrespondencesQueries
                        .getCorrespondenceByIds(
                            codeClassification.toLowerCase(),
                            targetCodeClassification.toLowerCase()));

        /* RawCorrespondence direct mapping from sparql request - correspondences are not symetrical in RDF model */
        List<RawCorrespondence> rawItemsList = csvUtils.populateMultiPOJO(csvResult, RawCorrespondence.class);

        if (rawItemsList != null && ! rawItemsList.isEmpty()) {

            /* raw sparql result fields order must be handled according to source / target classifications */
            Associations itemsList =
                CorrespondencesUtils
                    .getCorrespondenceByclassificationIds(codeClassification, targetCodeClassification, rawItemsList);

            return Response.ok(responseUtils.produceResponse(itemsList, header)).build();

        }

        else {

            return Response.status(Status.NOT_FOUND).entity("").build();

        }
    }

}
