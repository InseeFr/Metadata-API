package fr.insee.rmes.api.geo.territoire;

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
import fr.insee.rmes.modeles.geo.territoire.ArrondissementMunicipal;

@ExtendWith(MockitoExtension.class)
public class ArrondissementMunicipalApiTest extends AbstractApiTest {

    @InjectMocks
    private ArrondissementMunicipalApi geoApi;

    private ArrondissementMunicipal arrondissement = new ArrondissementMunicipal();

    public void givenGetArrondissement_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnOnePojo(arrondissement, Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getArrondissement("something", MediaType.APPLICATION_XML, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getArrondissement("something", MediaType.APPLICATION_JSON, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetArrondissement_whenCorrectRequestt_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock methods
        arrondissement.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(arrondissement, Boolean.TRUE);

        // Call method
        geoApi.getArrondissement("something", MediaType.APPLICATION_JSON, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetArrondissement_whenCorrectRequestt_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock methods
        arrondissement.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(arrondissement, Boolean.TRUE);

        // Call method
        geoApi.getArrondissement("something", MediaType.APPLICATION_XML, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetArrondissement_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
        arrondissement.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(arrondissement, Boolean.TRUE);

        // Call method header content = xml
        geoApi.getArrondissement("something", MediaType.APPLICATION_XML, "2000-01-01");
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetArrondissement_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getArrondissement("something", MediaType.APPLICATION_XML, "nimportequoi");
        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
}
