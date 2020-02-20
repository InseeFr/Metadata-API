package fr.insee.rmes.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

@Path("/healthcheck")
public class HealthcheckApi extends AbstractMetadataApi {
    

private final static String ASK_QUERY = "ASK { ?a a ?b .}";

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

//https://qfrmessnczlht01.ad.insee.intra/sparql?query=ASK+{+?a+a+?b+.}
