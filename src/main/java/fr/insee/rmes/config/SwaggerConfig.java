package fr.insee.rmes.config;

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
public class SwaggerConfig extends ResourceConfig {

    private static final Logger logger = LogManager.getLogger(SwaggerConfig.class);
    
    public SwaggerConfig(@Context ServletConfig servletConfig) {
        super();
        OpenAPI openApi = new OpenAPI();

        logger
            .info(
                "ServletConfig : {}",
                (servletConfig != null ? servletConfig.getServletContext() : "ServletConfig is null"));

        // describe API
        Info info = new Info().title(Configuration.getTitle()).version(Configuration.getVersion()).description(Configuration.getDescription());
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
        this.register(openApiResource);
        this.register(MultiPartFeature.class);
        
    }
    

    

}