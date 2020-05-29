package fr.insee.rmes.api.geo;

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
import fr.insee.rmes.api.geo.PaysApi;
import fr.insee.rmes.modeles.geo.Country;

@ExtendWith(MockitoExtension.class)
public class PaysApiTest extends AbstractApiTest {

    @InjectMocks
    private PaysApi geoApi;

    private Country country = new Country();

    @Test
    public void givenGetCountry_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Call method
        country.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(country, Boolean.TRUE);

        // Call method
        geoApi.getByCode("something", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetCountry_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Call method
        country.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(country, Boolean.TRUE);

        // Mock methods
        geoApi.getByCode("something", MediaType.APPLICATION_XML);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetCountry_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnOnePojo(country, Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getByCode("something", MediaType.APPLICATION_XML);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getByCode("something", MediaType.APPLICATION_JSON);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

}
