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
import fr.insee.rmes.modeles.geo.Commune;
import fr.insee.rmes.modeles.geo.Country;
import fr.insee.rmes.modeles.geo.Region;

@ExtendWith(MockitoExtension.class)
public class GeoAPITest extends AbstractApiTest {

    @InjectMocks
    private GeoAPI geoApi;

    private Commune commune = new Commune();
    private Country country = new Country();
    private Region region = new Region();

    @Test
    public void givenGetCommune_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock methods
        commune.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(commune, Boolean.TRUE);

        // Call method
        geoApi.getCommune("something", MediaType.APPLICATION_JSON, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetCommune_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock methods
        commune.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(commune, Boolean.TRUE);

        // Call method
        geoApi.getCommune("something", MediaType.APPLICATION_XML, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetCountry_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Call method
        country.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(country, Boolean.TRUE);

        // Call method
        geoApi.getCountry("something", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetCountry_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Call method
        country.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(country, Boolean.TRUE);

        // Mock methods
        geoApi.getCountry("something", MediaType.APPLICATION_XML);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetRegion_whenCorrectRequestt_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock methods
        region.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(region, Boolean.TRUE);

        // Call method
        geoApi.getRegion("something", MediaType.APPLICATION_JSON, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetRegion_whenCorrectRequestt_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock methods
        region.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(region, Boolean.TRUE);

        // Call method
        geoApi.getRegion("something", MediaType.APPLICATION_XML, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetCommune_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnOnePojo(commune, Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getCommune("something", MediaType.APPLICATION_XML, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getCommune("something", MediaType.APPLICATION_JSON, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetCountry_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnOnePojo(country, Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getCountry("something", MediaType.APPLICATION_XML);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getCountry("something", MediaType.APPLICATION_JSON);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetRegion_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnOnePojo(region, Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getRegion("something", MediaType.APPLICATION_XML, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getRegion("something", MediaType.APPLICATION_JSON, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

}
