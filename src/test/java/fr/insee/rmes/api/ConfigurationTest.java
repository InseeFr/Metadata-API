package fr.insee.rmes.api;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigurationTest {
    private static Logger logger = LogManager.getLogger(ConfigurationTest.class);

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

    private Properties getProperties() throws IOException {
        Properties props = new Properties();
        props.load(this.getClass().getClassLoader().getResourceAsStream("rmes-api-test.properties"));
        this.loadIfExists(props, "rmes-api-test.properties");
        return props;
    }

    /*
     * load properties on catalina base
     */
    private void loadIfExists(Properties props, String filename) throws IOException {
        File f;
        f = new File(String.format("%s/webapps/%s", System.getProperty("catalina.base"), filename));
        if (f.exists() && ! f.isDirectory()) {
            FileReader r = new FileReader(f);
            props.load(r);
            r.close();
        }
    }

    public ConfigurationTest() {
        Properties props = null;
        try {
            props = this.getProperties();

            sparqlEndPoint = props.getProperty("fr.insee.rmes.api.sparqlEndpoint");
            baseHost = props.getProperty("fr.insee.rmes.api.baseHost");
            fileStorage = props.getProperty("fr.insee.rmes.api.fileStorage");
            fileStorageLocation = props.getProperty("fr.insee.rmes.storage.document");

            swaggerHost = props.getProperty("fr.insee.rmes.api.host");
            swaggerBasepath = props.getProperty("fr.insee.rmes.api.basepath");
            requiresSsl = Boolean.valueOf(props.getProperty("fr.insee.rmes.api.force.ssl"));
            swaggerUrl = (requiresSsl ? "https" : "http") + "://" + swaggerHost + "/" + swaggerBasepath;

        }
        catch (IOException e) {
            logger.error("Configuration error, can't read properties", e);
        }

    }

    public static String getSparqlEndPoint() {
        return sparqlEndPoint;
    }

    public static void setSparqlEndPoint(String sparqlEndPoint) {
        ConfigurationTest.sparqlEndPoint = sparqlEndPoint;
    }

    public static String getBaseHost() {
        return baseHost;
    }

    public static void setBaseHost(String baseHost) {
        ConfigurationTest.baseHost = baseHost;
    }

    public static String getFileStorage() {
        return fileStorage;
    }

    public static void setFileStorage(String fileStorage) {
        ConfigurationTest.fileStorage = fileStorage;
    }

    public static String getFileStorageLocation() {
        return fileStorageLocation;
    }

    public static void setFileStorageLocation(String fileStorageLocation) {
        ConfigurationTest.fileStorageLocation = fileStorageLocation;
    }

    public static String getSwaggerHost() {
        return swaggerHost;
    }

    public static void setSwaggerHost(String swaggerHost) {
        ConfigurationTest.swaggerHost = swaggerHost;
    }

    public static String getSwaggerBasepath() {
        return swaggerBasepath;
    }

    public static void setSwaggerBasepath(String swaggerBasepath) {
        ConfigurationTest.swaggerBasepath = swaggerBasepath;
    }

    public static String getSwaggerUrl() {
        return swaggerUrl;
    }

    public static void setSwaggerUrl(String swaggerUrl) {
        ConfigurationTest.swaggerUrl = swaggerUrl;
    }

    public static Boolean getRequiresSsl() {
        return requiresSsl;
    }

    public static void setRequiresSsl(Boolean requiresSsl) {
        ConfigurationTest.requiresSsl = requiresSsl;
    }

}
