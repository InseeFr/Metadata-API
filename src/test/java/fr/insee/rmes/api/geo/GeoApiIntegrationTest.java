package fr.insee.rmes.api.geo;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class GeoApiIntegrationTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(GeoAPI.class);
    }

    @Test
    public void givengetCommune_whenCorrectRequest_thenResponseIsOk() {
        Response response = this.target("/geo/commune/59000").request().get();

        assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());

    }
}
