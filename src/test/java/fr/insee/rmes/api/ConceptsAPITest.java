package fr.insee.rmes.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;

import fr.insee.rmes.api.concepts.Definition;
import fr.insee.rmes.api.ConceptsAPI;
import fr.insee.rmes.api.utils.CSVUtils;
import fr.insee.rmes.api.utils.SparqlUtils;

@PrepareForTest(fullyQualifiedNames = "fr.insee.rmes.api.utils.*")
public class ConceptsAPITest extends JerseyTest {

    SparqlUtils mockSparqlUtils = mock(SparqlUtils.class);
    CSVUtils mockCSVUtils = mock(CSVUtils.class);

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

        when(SparqlUtils.executeSparqlQuery(Mockito.anyString())).thenReturn("");
        when(CSVUtils.populateMultiPOJO(Mockito.anyString(), Definition.class)).thenReturn(null);

        Response response = target("concepts/definitions").request().get();

        assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals(
            "Http Content-Type should be: ",
            MediaType.APPLICATION_JSON,
            response.getHeaderString(HttpHeaders.CONTENT_TYPE));
    }
}
