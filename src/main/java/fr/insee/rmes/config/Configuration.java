package fr.insee.rmes.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Configuration {
	private static Logger logger = LogManager.getLogger(Configuration.class);


	public static String SPARQL_END_POINT = "";
	public static String BASE_HOST = "";
	public static String FILE_STORAGE = "";
	public static String BASE_PATH = "";

	
	private Properties getProperties() throws IOException {
        Properties props = new Properties();
        props.load(getClass().getClassLoader().getResourceAsStream("rmes-api.properties"));
        File f = new File(String.format("%s/webapps/%s", System.getProperty("catalina.base"), "rmes-api.properties"));
        if(f.exists() && !f.isDirectory()) {
            FileReader r = new FileReader(f);
            props.load(r);
            r.close();
        }
        return props;
    }

	public Configuration() {
		Properties props = null;
		try {
			props = getProperties();
		} catch (IOException e) {
			logger.error("Configuration error, can't read properties", e);
		}
		SPARQL_END_POINT = props.getProperty("fr.insee.rmes.api.sparqlEndpoint");
		BASE_HOST = props.getProperty("fr.insee.rmes.api.baseHost");
		FILE_STORAGE =  props.getProperty("fr.insee.rmes.api.fileStorage");
		BASE_PATH = props.getProperty("fr.insee.rmes.api.basePath");
	}

}
