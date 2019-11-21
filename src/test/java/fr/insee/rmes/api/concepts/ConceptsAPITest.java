package fr.insee.rmes.api.concepts;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.insee.rmes.modeles.concepts.Definition;
import fr.insee.rmes.utils.CSVUtils;
import fr.insee.rmes.utils.ResponseUtils;
import fr.insee.rmes.utils.SparqlUtils;

@ExtendWith(MockitoExtension.class)
public class ConceptsAPITest {

    @InjectMocks
    private ConceptsAPI conceptsAPI;

    @Mock
    private SparqlUtils mockSparqlUtils;

    @Mock
    private CSVUtils mockCSVUtils;

    @Mock
    private ResponseUtils mockResponseUtils;

    List<Object> listeDefinition = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenGetConcepts_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        listeDefinition.add(new Definition());
        this.callMock(listeDefinition);
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);

        // Call method with header content is json
        conceptsAPI.getConcepts("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetConcepts_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        listeDefinition.add(new Definition());
        this.callMock(listeDefinition);
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);

        // Call method with header content is xml
        conceptsAPI.getConcepts("toto", MediaType.APPLICATION_XML);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetConcepts_WhenBadRequest_andHeaderContentIsNull_thenResponseIsNotAcceptable() {

        listeDefinition.add(new Definition());
        this.callMock(listeDefinition);

        Response response = conceptsAPI.getConcepts("", null);
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
        Assertions.assertEquals(Status.NOT_ACCEPTABLE.getStatusCode(), response.getStatus());

        response = conceptsAPI.getConcepts("", "nawak");
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
        Assertions.assertEquals(Status.NOT_ACCEPTABLE.getStatusCode(), response.getStatus());
    }

    @Test
    public void givenGetConcepts_WhenCorrectRequest_thenResponseIsNotFound() {

        this.callMock(listeDefinition);

        Response response = conceptsAPI.getConcepts("", MediaType.APPLICATION_XML);
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        response = conceptsAPI.getConcepts("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    private void callMock(List<Object> listeDefinition) {
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populateMultiPOJO(Mockito.anyString(), Mockito.any())).thenReturn(listeDefinition);
    }
}
