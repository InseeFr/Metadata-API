package fr.insee.rmes.api.concepts;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.insee.rmes.modeles.concepts.Definition;
import fr.insee.rmes.queries.concepts.ConceptsQueries;
import fr.insee.rmes.utils.CSVUtils;
import fr.insee.rmes.utils.SparqlUtils;

public class ConceptsAPITest extends JerseyTest {

    @InjectMocks
    private ConceptsAPI conceptsAPI;

    @Mock
    SparqlUtils mockSparqlUtils;

    @Mock
    CSVUtils mockCSVUtils;

    @Mock
    ConceptsQueries mockConceptsQueries;

    List<Object> listeDefinition = new ArrayList<>();

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @AfterEach
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    public Application configure() {
        MockitoAnnotations.initMocks(this);
        return new ResourceConfig(ConceptsAPI.class);
    }

    @Test
    public void givenGetConcepts_whenCorrectRequest_thenResponseIsOk() {

        listeDefinition.add(new Definition());
        when(mockConceptsQueries.getConceptsByLabel(Mockito.anyString())).thenReturn("");
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populateMultiPOJO(Mockito.anyString(), Mockito.any())).thenReturn(listeDefinition);

        Response response = conceptsAPI.getConcepts("", "application/json");

        assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals(
            "Http Content-Type should be: ",
            MediaType.APPLICATION_JSON,
            response.getHeaderString(HttpHeaders.CONTENT_TYPE));
    }
}
