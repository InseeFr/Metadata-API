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
import fr.insee.rmes.utils.ResponseUtils;

@Path("/correspondance")
public class CorrespondenceApi extends MetadataApi {

    @GET
    @Path("/{idCorrespondance}")
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    public Response getCorrespondencesById(
        @PathParam("idCorrespondance") String idCorrespondance,
        @HeaderParam(value = HttpHeaders.ACCEPT) String header) {

        String csvResult =
            sparqlUtils
                .executeSparqlQuery(CorrespondencesQueries.getCorrespondenceById(idCorrespondance.toLowerCase()));

        /* RawCorrespondence direct mapping from sparql request */
        List<RawCorrespondence> rawItemsList = csvUtils.populateMultiPOJO(csvResult, RawCorrespondence.class);

        if (rawItemsList != null && ! rawItemsList.isEmpty()) {

            /* raw sparql result fields order must be got in shape 1 source -> many targets */
            Associations itemsList =
                CorrespondencesUtils.getCorrespondenceByCorrespondenceId(idCorrespondance, rawItemsList);

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
    public Response getCorrespondenceByIds(
        @PathParam("idNomenclatureSource") String codeClassification,
        @PathParam("idNomenclatureCible") String targetCodeClassification,
        @HeaderParam(value = HttpHeaders.ACCEPT) String header) {

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
