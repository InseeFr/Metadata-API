package fr.insee.rmes.config;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Provider
public class LogRequestFilter implements ContainerRequestFilter, ContainerResponseFilter  {

	 private static final Logger log = LoggerFactory.getLogger(LogRequestFilter.class);

    @Override
    public void filter(ContainerRequestContext  requestContext) throws IOException {
    	 StringBuilder sb = logRequest(requestContext);
    	 sb.append(", Params : ").append(requestContext.getUriInfo().getQueryParameters());
    	 String toLog = sb.toString();
		 toLog = toLog.replaceAll("[\n\r]", "_");
    	 log.info("START {}", toLog);
    }


    @Override
    public void filter(ContainerRequestContext  requestContext, ContainerResponseContext  responseContext) throws IOException {
 	   	StringBuilder sb = logRequest(requestContext);
	    sb.append(" with code : ").append(responseContext.getStatus());
	    String toLog = sb.toString();
		toLog = toLog.replaceAll("[\n\r]", "_");
    	log.info("END {}", toLog);
    }        
    

	private StringBuilder logRequest(ContainerRequestContext requestContext) {
		StringBuilder sb = new StringBuilder();
    	   sb.append(requestContext.getMethod()).append(" ").append(requestContext.getUriInfo().getPath());
    	   sb.append(", User: ").append(requestContext.getSecurityContext().getUserPrincipal() == null ? "unknown"
    	            : requestContext.getSecurityContext().getUserPrincipal());
		return sb;
	}
}
