package fr.insee.rmes.api;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

@Path("/technicalService")
public class DescribeApi extends AbstractMetadataApi {

    @GET
    @Path("/describe")
    @Produces(MediaType.WILDCARD)
    @Operation(operationId = "describeUri", summary = "Service pour déréférencer une uri")
    public Response describeUri(
    		@Parameter(required = true, description = "Iri à déréférencer", schema = @Schema(type = "string")) @QueryParam("uri") String uri,
    		@Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header) {
    	if (StringUtils.isEmpty(uri)){
    		 return Response.serverError().build();
    	 }
    	 Boolean result = sparqlUtils.executeAskQuery("ASK WHERE {{<"+uri+"> ?p ?o .} UNION {?s ?p <"+uri+"> .}} ");
    	 if (Boolean.FALSE.equals(result)) {
    		 return Response.status(Status.NOT_FOUND).entity("").build();
    	 }
    	 else return Response.ok(sparqlUtils.executeSparqlQuery("DESCRIBE <"+uri+"> ", header)).build();
    }
    
    
}