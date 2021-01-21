package fr.insee.rmes.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.config.Configuration;

public class SparqlUtils {

    private static Logger logger = LogManager.getLogger(SparqlUtils.class);

    public String executeSparqlQuery(String query) {

        String uri = this.queryToURI(query);
        Client client = ClientBuilder.newBuilder().build();
        String response = client.target(uri).request("text/csv").get(String.class);

        logger.debug("SPARQL query returned: \n {}", response);
        return response;
    }

    public String queryToURI(String query) {
        return Configuration.getSparqlEndPoint() + "?query=" + this.encode(QueryUtils.PREFIXES + query);

    }

    public String encode(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            return "Issue while encoding: " + e.getMessage();
        }
    }
    
    
    public List<String> getResponseAsList(String response){
    	List<String> list = new ArrayList<>(Arrays.asList(response.split("\r\n")));
    	list.remove(0);
    	return list;
    	
    }

}
