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
    
    private static String version = "";
    private static String title = "";
    private static String description = "";
    
    public Configuration() {
        Properties props = null;
        try {
            props = propertiesLoading.getProperties();
            setConfig(props);
        }
        catch (IOException e) {
            logger.error("Configuration error, can't read properties", e);
        }

    }
    
    private static void setConfig(Properties props) {

        version = props.getProperty("fr.insee.rmes.api.version");
        title = props.getProperty("fr.insee.rmes.api.title");
        description = props.getProperty("fr.insee.rmes.api.description");

        sparqlEndPoint = props.getProperty("fr.insee.rmes.api.sparqlEndpoint");
        baseHost = props.getProperty("fr.insee.rmes.api.baseHost");
        fileStorage = props.getProperty("fr.insee.rmes.api.fileStorage");
        fileStorageLocation = props.getProperty("fr.insee.rmes.api.storage.document");

        swaggerHost = props.getProperty("fr.insee.rmes.api.host");
        swaggerBasepath = props.getProperty("fr.insee.rmes.api.basepath");
        requiresSsl = Boolean.valueOf(props.getProperty("fr.insee.rmes.api.force.ssl"));
        swaggerUrl =
            (Boolean.TRUE.equals(requiresSsl) ? "https" : "http") + "://" + swaggerHost + "/" + swaggerBasepath;
        
        printMajorConfig();

    }
    
	public static void printMajorConfig() {
		logger.info("*********************** CONFIG USED ***********************************");
		
		logger.info("SERVEUR RDF : {}",sparqlEndPoint);
		
		logger.info("DOCUMENT STORAGE : {}", fileStorageLocation);
		
		logger.info("SWAGGER URL : {}", swaggerUrl);
		
		logger.info("*********************** END CONFIG USED ***********************************");
		
		
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

	public static String getVersion() {
		return version;
	}

	public static void setVersion(String version) {
		Configuration.version = version;
	}

	public static String getTitle() {
		return title;
	}

	public static void setTitle(String title) {
		Configuration.title = title;
	}

	public static String getDescription() {
		return description;
	}

	public static void setDescription(String description) {
		Configuration.description = description;
	}

}
