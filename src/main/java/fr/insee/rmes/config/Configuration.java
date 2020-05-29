package fr.insee.rmes.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Configuration {
    private static Logger logger = LogManager.getLogger(Configuration.class);
    private PropertiesLoading propertiesLoading = new PropertiesLoading();

    private static String sparqlEndPoint = "";

    // Base for all uri in our database
    private static String baseHost = "";

    // folder where documents are stored
    private static String fileStorage = "";
    // Server where documents are stored
    private static String fileStorageLocation = "";

    // API Server
    private static String swaggerHost = "";
    // API name
    private static String swaggerBasepath = "";
    // Build with host and basepath
    private static String swaggerUrl = "";
    // Https or Http
    private static Boolean requiresSsl = false;

    public Configuration() {
        Properties props = null;
        try {
            props = propertiesLoading.getProperties();

            sparqlEndPoint = props.getProperty("fr.insee.rmes.api.sparqlEndpoint");
            baseHost = props.getProperty("fr.insee.rmes.api.baseHost");
            fileStorage = props.getProperty("fr.insee.rmes.api.fileStorage");
            fileStorageLocation = props.getProperty("fr.insee.rmes.storage.document");

            swaggerHost = props.getProperty("fr.insee.rmes.api.host");
            swaggerBasepath = props.getProperty("fr.insee.rmes.api.basepath");
            requiresSsl = Boolean.valueOf(props.getProperty("fr.insee.rmes.api.force.ssl"));
            swaggerUrl =
                (Boolean.TRUE.equals(requiresSsl) ? "https" : "http") + "://" + swaggerHost + "/" + swaggerBasepath;

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
