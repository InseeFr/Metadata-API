package fr.insee.rmes.api.classifications;

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
import fr.insee.rmes.modeles.classification.PosteJson;

@ExtendWith(MockitoExtension.class)
class ClassificationAPITest extends AbstractApiTest {

    @InjectMocks
    private ClassificationApi classificationAPI;

    @Test
    void givenGetClassificationById_whenCorrectRequest_thenResponseIsOk() {
        list.add(new PosteJson());

        // Mock
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);

        // Call method
        classificationAPI.getClassificationByCode("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());

        classificationAPI.getClassificationByCode("", MediaType.APPLICATION_XML);
        verify(mockResponseUtils, times(2)).produceResponse(Mockito.any(), Mockito.any());

    }

    @Test
    void givenGetClassificationById_whenCorrectRequest_andClassificationNotFound_thenResponseIsNotFound() {

        // Mock
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);

        // Call method
        Response response = classificationAPI.getClassificationByCode("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    void givenGetClassificationTreeById_whenCorrectRequest_thenResponseIsOk() {
        list.add(new PosteJson());

        // Mock
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);

        // Call method
        classificationAPI.getClassificationTreeByCode("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());

        classificationAPI.getClassificationTreeByCode("", MediaType.APPLICATION_XML);
        verify(mockResponseUtils, times(2)).produceResponse(Mockito.any(), Mockito.any());

    }

    @Test
    void givenGetClassificationTreeById_whenCorrectRequest_andClassificationNotFound_thenResponseIsNotFound() {

        // Mock
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);

        // Call method
        Response response = classificationAPI.getClassificationTreeByCode("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

}
