package fr.insee.rmes.api.old;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Ignore;
import org.junit.Test;

import fr.insee.rmes.api.geo.AbstractGeoApi;

// TODO needs to be transformed into fonctional test

@Ignore
public class GeoAPITest extends JerseyTest {

    @Override
    public Application configure() {
        return new ResourceConfig(AbstractGeoApi.class);
    }

    @Test
    public void testGetCommune() {
        Response output = this.target("geo/commune/1234").request().accept(MediaType.APPLICATION_JSON).get();
        assertEquals("Should return status 404", 404, output.getStatus());
        output = this.target("geo/commune/").request().get();
        assertEquals("Should return status 400", 400, output.getStatus());

    }

    @Test
    public void testGetCountry() {
        Response output = this.target("geo/pays/99217").request().accept(MediaType.APPLICATION_JSON).get();
        assertEquals("Should return status 200", 200, output.getStatus());
    }

    @Test
    public void testGetRegion() {
        Response output = this.target("/geo/region/12").request().accept(MediaType.APPLICATION_JSON).get();
        assertEquals("Should return status 200", 200, output.getStatus());
    }

}
