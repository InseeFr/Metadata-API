package fr.insee.rmes.api.concepts;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.insee.rmes.modeles.concepts.Definition;
import fr.insee.rmes.utils.CSVUtils;
import fr.insee.rmes.utils.SparqlUtils;

@ExtendWith(MockitoExtension.class)
public class ConceptsAPITest {

    @InjectMocks
    private ConceptsAPI conceptsAPI;

    @Mock
    private SparqlUtils mockSparqlUtils;

    @Mock
    private CSVUtils mockCSVUtils;

    List<Object> listeDefinition = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenGetConcepts_whenCorrectRequest_thenResponseIsOk() {

        listeDefinition.add(new Definition());
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populateMultiPOJO(Mockito.anyString(), Mockito.any())).thenReturn(listeDefinition);

        Response response = conceptsAPI.getConcepts("", MediaType.APPLICATION_JSON);

        assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals(
            "Http Content-Type should be: ",
            MediaType.APPLICATION_JSON,
            response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        response = conceptsAPI.getConcepts("", MediaType.APPLICATION_XML);

        assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals(
            "Http Content-Type should be: ",
            MediaType.APPLICATION_XML,
            response.getHeaderString(HttpHeaders.CONTENT_TYPE));
    }
}
