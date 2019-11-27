package fr.insee.rmes.api.classifications;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.insee.rmes.api.AbstractApiTest;
import fr.insee.rmes.modeles.classification.Classification;

@ExtendWith(MockitoExtension.class)
public class ClassificationsAPITest extends AbstractApiTest {

	@InjectMocks
	private ClassificationsApi classificationsAPI;

    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void givenGetAllClassifications_whenCorrectRequest_thenResponseIsOk() {
        list.add(new Classification());
    	
        // Mock
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populateMultiPOJO(Mockito.anyString(), Mockito.any())).thenReturn(list);
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);

        // Call method
        classificationsAPI.getAllClassifications(MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());

        classificationsAPI.getAllClassifications(MediaType.APPLICATION_XML);
        verify(mockResponseUtils, times(2)).produceResponse(Mockito.any(), Mockito.any());

    }

    @Test
    public void givenGetAllClassifications_whenCorrectRequest_andClassificationNotFound_thenResponseIsNotFound() {

        // Mock
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populateMultiPOJO(Mockito.anyString(), Mockito.any())).thenReturn(list);

        // Call method
        Response response = classificationsAPI.getAllClassifications(MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
 
    
	
}
