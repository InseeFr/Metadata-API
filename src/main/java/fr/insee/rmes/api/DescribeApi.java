package fr.insee.rmes.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;

import fr.insee.rmes.modeles.utils.Header;

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
    		@Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) Header header) {
    	if (StringUtils.isEmpty(uri)){
    		 return Response.serverError().build();
    	 }
    	 Boolean result = sparqlUtils.executeAskQuery("ASK WHERE {{<"+uri+"> ?p ?o .} UNION {?s ?p <"+uri+"> .}} ");
    	 if (Boolean.FALSE.equals(result)) {
    		 return Response.status(Status.NOT_FOUND).entity("").build();
    	 }
    	 else return Response.ok(sparqlUtils.executeSparqlQuery("DESCRIBE <"+uri+"> ", header.getString())).build();
    }
    
    
}