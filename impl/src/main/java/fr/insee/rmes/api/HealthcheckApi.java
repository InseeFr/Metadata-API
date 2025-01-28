package fr.insee.rmes.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

@Path("/healthcheck")
public class HealthcheckApi extends AbstractMetadataApi {

    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    public Response getHealthcheck() {
        if (StringUtils.isEmpty(sparqlUtils.executeSparqlQuery("SELECT * { ?s a ?t } LIMIT 1"))) {
            return Response.serverError().build();
        }
        return Response.ok().build();
    }
}