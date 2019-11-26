package fr.insee.rmes.config;

import java.io.IOException;
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

    private final static Logger logger = LogManager.getLogger(SwaggerConfig.class);
    

    public SwaggerConfig(@Context ServletConfig servletConfig) throws IOException {
        super();
        OpenAPI openApi = new OpenAPI();

        Info info = new Info().title("API RMéS").version("1.2.0").description("API sur les métadonnées de l'Insee");
        openApi.info(info);

        Server server = new Server();
        server.url(Configuration.SWAGGER_URL);
        openApi.addServersItem(server);

        SwaggerConfiguration oasConfig =
            new SwaggerConfiguration()
                .openAPI(openApi)
                .resourcePackages(Stream.of("fr.insee.rmes.api").collect(Collectors.toSet()))
                .prettyPrint(true);

        logger.debug("SWAGGER : " + oasConfig.toString());

        OpenApiResource openApiResource = new OpenApiResource();
        openApiResource.setOpenApiConfiguration(oasConfig);
        register(openApiResource);
        register(MultiPartFeature.class);
    }

}