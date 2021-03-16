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

import fr.insee.rmes.api.AbstractMetadataApi;
import fr.insee.rmes.modeles.classification.correspondence.Association;
import fr.insee.rmes.modeles.classification.correspondence.Associations;
import fr.insee.rmes.modeles.classification.correspondence.RawCorrespondence;
import fr.insee.rmes.queries.classifications.CorrespondencesQueries;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Hidden
@Path("/correspondance")
@Tag(name = "nomenclatures", description = "Nomenclatures API")
public class CorrespondenceApi extends AbstractMetadataApi {

	@Hidden
    @GET
    @Path("/{idCorrespondance}")
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getCorrespondenceByCode",
        summary = "Correspondance entre deux nomenclatures",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = Association.class)),
                description = "Correspondances entre deux nomenclatures")
        })
    public Response getCorrespondencesById(
        @Parameter(
            required = true,
            description = "Identifiant de la correspondance",
            example = "nafr2-cpfr21") @PathParam("idCorrespondance") String idCorrespondance,
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

	@Hidden
    @GET
    @Path("/{idNomenclatureSource}/{idNomenclatureCible}")
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getCorrespondenceByClassificationCodes",
        summary = " Liste des associations de correspondance entre deux nomenclatures",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = Association.class)),
                description = "Correspondances entre deux nomenclatures")
        })
    public Response getCorrespondenceByIds(
        @Parameter(
            required = true,
            description = "Identifiant de la nomenclature source",
            example = "nafr2") @PathParam("idNomenclatureSource") String codeClassification,
        @Parameter(
            required = true,
            description = "Identifiant de la nomenclature cible",
            example = "cpfr21") @PathParam("idNomenclatureCible") String targetCodeClassification,
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
                CorrespondencesUtils.getCorrespondenceByclassificationIds(targetCodeClassification, rawItemsList);
            return Response.ok(responseUtils.produceResponse(itemsList, header)).build();
        }

        else {
            return Response.status(Status.NOT_FOUND).entity("").build();
        }
    }

}
