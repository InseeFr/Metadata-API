package fr.insee.rmes.api.geo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.insee.rmes.api.AbstractApiTest;
import fr.insee.rmes.modeles.geo.Country;
import fr.insee.rmes.modeles.utils.Header;

@ExtendWith(MockitoExtension.class)
class PaysApiTest extends AbstractApiTest {

    @InjectMocks
    private PaysApi geoApi;

    private Country country = new Country();

    private Header headerJSON = new Header(MediaType.APPLICATION_JSON);
    private Header headerXML = new Header( MediaType.APPLICATION_XML);


/*    @Test
    void givenGetCountry_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Call method
        country.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(country, Boolean.TRUE);

        // Call method
        geoApi.getByCode("something",headerJSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }*/
@Test
void givenGetCountry_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {
    country.setUri("http://id.insee.fr/geo/pays/99217");
    this.mockUtilsMethodsThenReturnOnePojo(country, Boolean.TRUE);
    Response response = geoApi.getByCode("99217", headerJSON);
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
}

/*    @Test
    void givenGetCountry_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Call method
        country.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(country, Boolean.TRUE);

        // Mock methods
        geoApi.getByCode("something",headerXML);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }*/
@Test
void givenGetCountry_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {
    country.setUri("http://id.insee.fr/geo/pays/99217");
    this.mockUtilsMethodsThenReturnOnePojo(country, Boolean.TRUE);
    Response response = geoApi.getByCode("99217", headerXML);
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
}
/*    @Test
    void givenGetCountry_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnOnePojo(country, Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getByCode("something", headerXML);
        assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getByCode("something", headerJSON);
        assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }*/
@Test
void givenGetCountry_WhenCorrectRequest_thenResponseIsNotFound() {
    this.mockUtilsMethodsThenReturnOnePojo(country, Boolean.FALSE);
    Response response = geoApi.getByCode("99217", headerXML);
    assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
    response = geoApi.getByCode("99217", headerJSON);
    assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
    verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
}

}
