package fr.insee.rmes.api.concepts;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.insee.rmes.api.AbstractApiTest;
import fr.insee.rmes.modeles.concepts.Concept;
import fr.insee.rmes.modeles.concepts.Definition;

@ExtendWith(MockitoExtension.class)
public class ConceptsAPITest extends AbstractApiTest {

    @InjectMocks
    private ConceptsAPI conceptsAPI;

    private Definition definition = new Definition();
    private Concept concept = new Concept();


    @Test
    public void givenGetConcepts_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock
        list.add(new Definition());
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);

        // Call method with header content is json
        conceptsAPI.getConcepts("something", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetConcepts_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock
        list.add(new Definition());
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);

        // Call method with header content is xml
        conceptsAPI.getConcepts("something", MediaType.APPLICATION_XML);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetConcepts_WhenBadRequest_andHeaderContentIsNull_thenResponseIsNotAcceptable() {

        // Mock
        list.add(new Definition());
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);

        // Call method with null parameter
        Response response = conceptsAPI.getConcepts("something", null);
        Assertions.assertEquals(Status.NOT_ACCEPTABLE.getStatusCode(), response.getStatus());

        // Call method with false parameter
        response = conceptsAPI.getConcepts("something", "somethingFalse");
        Assertions.assertEquals(Status.NOT_ACCEPTABLE.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetConcepts_WhenCorrectRequest_thenResponseIsNotFound() {

        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);

        Response response = conceptsAPI.getConcepts("something", MediaType.APPLICATION_XML);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        response = conceptsAPI.getConcepts("something", MediaType.APPLICATION_JSON);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetConceptById_whenCorrectRequest_thenResponseIsOk() {

        // Mock
        concept.setUri("aUri");
        this.mockUtilsMethodsThenReturnOnePojo(concept, Boolean.TRUE);

        // Call method
        conceptsAPI.getConceptById("something", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetConceptById_whenCorrectRequest_andDefinitionNotFound_thenResponseIsNotFound() {

        // Mock
        this.mockUtilsMethodsThenReturnOnePojo(concept, Boolean.FALSE);

        // Call method
        Response response = conceptsAPI.getConceptById("something", MediaType.APPLICATION_JSON);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
        response = conceptsAPI.getConceptById("something", MediaType.APPLICATION_XML);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }
}
