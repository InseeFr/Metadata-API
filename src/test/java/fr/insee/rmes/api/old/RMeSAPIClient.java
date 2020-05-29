package fr.insee.rmes.api.old;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.Ignore;

// TODO needs to be transformed into fonctional test

@Ignore
public class RMeSAPIClient {

    static Client client = ClientBuilder.newClient(new ClientConfig());
    static WebTarget target = client.target(getBaseURI());

    public static void main(String[] args) {

        // getResource("/geo/commune/12345", MediaType.APPLICATION_JSON);
        getResource("/geo/region/12", MediaType.APPLICATION_XML);
        // getResource("/codes/cj/n3/5432", MediaType.APPLICATION_JSON);
        // getResource("/codes/nafr2/classe/27.40", MediaType.APPLICATION_JSON);
    }

    private static void getResource(String path, String mediaType) {
        String answer = target.path(path).request().accept(mediaType).get(String.class);
        System.out.println(answer);
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/metadata-api").build();
    }

}