package fr.insee.rmes.config;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Context;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@ApplicationPath("/")
public class MetadataApiConfig extends ResourceConfig {

    private static final Logger logger = LogManager.getLogger(MetadataApiConfig.class);
    
    public MetadataApiConfig(@Context ServletConfig servletConfig) {
        super();
        
        //Swagger
        OpenApiResource openApiResource = initializeSwagger(servletConfig);
        this.register(openApiResource);
        this.register(MultiPartFeature.class);
        
        //Logs
        this.register(LogRequestFilter.class);
        
    }

	private OpenApiResource initializeSwagger(ServletConfig servletConfig) {
		OpenAPI openApi = new OpenAPI();

        logger.info("ServletConfig : {}",
                (servletConfig != null ? servletConfig.getServletContext() : "ServletConfig is null"));

        // describe API
        Info info = new Info().title(Configuration.getTitle()).version(convertInUtf8(Configuration.getVersion())).description(convertInUtf8(Configuration.getDescription()));
        openApi.info(info);

        // set Server API
        Server server = new Server();
        server.url(Configuration.getSwaggerUrl());
        openApi.addServersItem(server);

        // define where API are described (with annotations)
        SwaggerConfiguration oasConfig =
            new SwaggerConfiguration()
                .openAPI(openApi)
                .resourcePackages(Stream.of("fr.insee.rmes.api").collect(Collectors.toSet()))
                .prettyPrint(true);

        logger.info("SWAGGER : {}", (oasConfig != null ? oasConfig.getOpenAPI() : "SwaggerConfiguration is null"));

        OpenApiResource openApiResource = new OpenApiResource();
        openApiResource.setOpenApiConfiguration(oasConfig);
		return openApiResource;
	}
    
    private String convertInUtf8(String strToEncode) {
    	ByteBuffer buffer = StandardCharsets.UTF_8.encode(strToEncode); 
    	return StandardCharsets.UTF_8.decode(buffer).toString();
    }
    

    

}