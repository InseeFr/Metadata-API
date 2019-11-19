package fr.insee.rmes.api.concepts;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import fr.insee.rmes.api.utils.SparqlUtils;

public class ConceptsAPITest extends JerseyTest {

    private String retourSparql = "";

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        super.setUp();
    }

    @AfterEach
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    public Application configure() {
        return new ResourceConfig(ConceptsAPI.class);
    }

    @Test
    public void givenGetConcepts_whenCorrectRequest_thenResponseIsOk() {

        when(SparqlUtils.executeSparqlQuery(any())).thenReturn(retourSparql);

        Response response = target("concepts/definitions").request().get();

        assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals(
            "Http Content-Type should be: ",
            MediaType.APPLICATION_JSON,
            response.getHeaderString(HttpHeaders.CONTENT_TYPE));
    }
}
