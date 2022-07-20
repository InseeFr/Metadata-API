package fr.insee.rmes.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/admin/cache")
public class CacheApi extends AbstractMetadataApi {

    @GET
    @Path("/clean/sousClasseNaf2008Cache")
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    public Response cleanSousClasseNaf2008Cache() {
       cacheHelper.cleanSousClasseNaf2008Cache();
       return Response.ok().build();
    }
    
    @GET
    @Path("/clean/actualCommunesCache")
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    public Response cleanActualCommunesCache() {
       cacheHelper.cleanActualCommunesCache();
       return Response.ok().build();
    }
}