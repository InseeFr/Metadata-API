package fr.insee.rmes.api.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.api.Configuration;

public class SparqlUtils {

	private static Logger logger = LogManager.getLogger(SparqlUtils.class);

	public static String executeSparqlQuery(String query) {

		String uri = queryToURI(query);
		Client client = ClientBuilder.newBuilder().build();
		String response = client.target(uri).request("text/csv").get(String.class);

		logger.debug("SPARQL query returned: \n" + response);
		return response;
	}

	public static String queryToURI(String query) {
		return Configuration.SPARQL_END_POINT + "?query=" + encode(QueryUtils.PREFIXES + query);

	}

	public static String encode(String url) {
		try {
			String encodeURL = URLEncoder.encode(url, "UTF-8");
			return encodeURL;
		} catch (UnsupportedEncodingException e) {
			return "Issue while encoding: " + e.getMessage();
		}
	}

}
