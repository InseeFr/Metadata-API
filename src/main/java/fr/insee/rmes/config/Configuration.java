package fr.insee.rmes.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Configuration {
    private static Logger logger = LogManager.getLogger(Configuration.class);

    private static String sparqlEndPoint = "";

    private static String baseHost = "";
    private static String fileStorage = "";
    private static String fileStorageLocation = "";

    private static String swaggerHost = "";
    private static String swaggerBasepath = "";
    private static String swaggerUrl = "";
    private static Boolean requiresSsl = false;

    private Properties getProperties() throws IOException {
        Properties props = new Properties();
        props.load(this.getClass().getClassLoader().getResourceAsStream("rmes-api.properties"));
        this.loadIfExists(props, "rmes-api.properties");
        this.loadIfExists(props, "rmeswnci.properties");
        this.loadIfExists(props, "rmeswncz.properties\"");
        return props;
    }

    private void loadIfExists(Properties props, String filename) throws IOException {
        File f;
        f = new File(String.format("%s/webapps/%s", System.getProperty("catalina.base"), filename));
        if (f.exists() && ! f.isDirectory()) {
            FileReader r = new FileReader(f);
            props.load(r);
            r.close();
        }
    }

    public Configuration() {
        Properties props = null;
        try {
            props = this.getProperties();

            sparqlEndPoint = props.getProperty("fr.insee.rmes.api.sparqlEndpoint");
            baseHost = props.getProperty("fr.insee.rmes.api.baseHost");
            fileStorage = props.getProperty("fr.insee.rmes.api.fileStorage");
            fileStorageLocation = props.getProperty("fr.insee.rmes.storage.document");

            swaggerHost = props.getProperty("fr.insee.rmes.api.host");
            swaggerBasepath = props.getProperty("fr.insee.rmes.api.basepath");
            swaggerUrl = (requiresSsl ? "https" : "http") + "://" + swaggerHost + "/" + swaggerBasepath;
            requiresSsl = Boolean.valueOf(props.getProperty("fr.insee.rmes.api.force.ssl"));

        }
        catch (IOException e) {
            logger.error("Configuration error, can't read properties", e);
        }

    }

    public static String getSparqlEndPoint() {
        return sparqlEndPoint;
    }

    public static void setSparqlEndPoint(String sparqlEndPoint) {
        Configuration.sparqlEndPoint = sparqlEndPoint;
    }

    public static String getBaseHost() {
        return baseHost;
    }

    public static void setBaseHost(String baseHost) {
        Configuration.baseHost = baseHost;
    }

    public static String getFileStorage() {
        return fileStorage;
    }

    public static void setFileStorage(String fileStorage) {
        Configuration.fileStorage = fileStorage;
    }

    public static String getFileStorageLocation() {
        return fileStorageLocation;
    }

    public static void setFileStorageLocation(String fileStorageLocation) {
        Configuration.fileStorageLocation = fileStorageLocation;
    }

    public static String getSwaggerHost() {
        return swaggerHost;
    }

    public static void setSwaggerHost(String swaggerHost) {
        Configuration.swaggerHost = swaggerHost;
    }

    public static String getSwaggerBasepath() {
        return swaggerBasepath;
    }

    public static void setSwaggerBasepath(String swaggerBasepath) {
        Configuration.swaggerBasepath = swaggerBasepath;
    }

    public static String getSwaggerUrl() {
        return swaggerUrl;
    }

    public static void setSwaggerUrl(String swaggerUrl) {
        Configuration.swaggerUrl = swaggerUrl;
    }

    public static Boolean getRequiresSsl() {
        return requiresSsl;
    }

    public static void setRequiresSsl(Boolean requiresSsl) {
        Configuration.requiresSsl = requiresSsl;
    }

}
