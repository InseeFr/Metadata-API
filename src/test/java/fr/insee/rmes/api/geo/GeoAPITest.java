package fr.insee.rmes.api.geo;

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

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenGetCommune_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock methods
        commune.setUri("quelquechose");
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(commune);
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);

        // Call method
        geoApi.getCommune("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetCommune_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock methods
        commune.setUri("quelquechose");
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(commune);
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);

        // Call method
        geoApi.getCommune("", MediaType.APPLICATION_XML);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetCountry_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Call method
        country.setUri("quelquechose");
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(country);
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);

        // Call method
        geoApi.getCountry("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetCountry_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Call method
        country.setUri("quelquechose");
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(country);
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);

        // Mock methods
        geoApi.getCountry("", MediaType.APPLICATION_XML);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetRegion_whenCorrectRequestt_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock methods
        region.setUri("quelquechose");
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(region);
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);

        // Call method
        geoApi.getRegion("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetRegion_whenCorrectRequestt_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock methods
        region.setUri("quelquechose");
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(region);
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);

        // Call method
        geoApi.getRegion("", MediaType.APPLICATION_XML);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetCommune_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(new Commune());

        // Call method header content = xml
        Response response = geoApi.getCommune("", MediaType.APPLICATION_XML);
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getCommune("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void givenGetCountry_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(new Country());

        // Call method header content = xml
        Response response = geoApi.getCountry("", MediaType.APPLICATION_XML);
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getCountry("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void givenGetRegion_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(new Region());

        // Call method header content = xml
        Response response = geoApi.getRegion("", MediaType.APPLICATION_XML);
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getRegion("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

}
