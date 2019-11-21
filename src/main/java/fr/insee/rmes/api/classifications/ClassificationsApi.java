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
import fr.insee.rmes.utils.ResponseUtils;

@Path("/nomenclatures")
public class ClassificationsApi extends MetadataApi {

    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    public Response getAllClassifications(@HeaderParam(value = HttpHeaders.ACCEPT) String header) {

        String csvResult = sparqlUtils.executeSparqlQuery(ClassificationsQueries.getAllClassifications());
        List<Classification> itemsList = csvUtils.populateMultiPOJO(csvResult, Classification.class);

        if (itemsList.size() == 0) {
            return Response.status(Status.NOT_FOUND).entity("").build();
        }
        else if (header.equals(MediaType.APPLICATION_XML)) {
            return Response.ok(ResponseUtils.produceResponse(new Classifications(itemsList), header)).build();
        }
        else {
            return Response.ok(ResponseUtils.produceResponse(itemsList, header)).build();
        }
    }

}
