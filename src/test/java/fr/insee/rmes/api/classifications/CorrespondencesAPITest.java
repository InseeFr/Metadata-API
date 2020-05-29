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
import fr.insee.rmes.modeles.classification.Activite;

@ExtendWith(MockitoExtension.class)
public class CorrespondencesAPITest extends AbstractApiTest {

    @InjectMocks
    private CorrespondencesApi correspondencesApi;

    @Test
    public void givenGetAllCorrespondences_whenCorrectRequest_thenResponseIsOk() {
        list.add(new Activite());

        // Mock
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);

        // Call method
        correspondencesApi.getAllCorrespondences(MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());

        correspondencesApi.getAllCorrespondences(MediaType.APPLICATION_XML);
        verify(mockResponseUtils, times(2)).produceResponse(Mockito.any(), Mockito.any());

    }

    @Test
    public void givenGetAllCorrespondences_whenCorrectRequest_andNotFound_thenResponseIsNotFound() {

        // Mock
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);

        // Call method
        Response response = correspondencesApi.getAllCorrespondences(MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

    }

}
