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
import fr.insee.rmes.modeles.geo.Country;
import fr.insee.rmes.modeles.geo.territoire.Arrondissement;
import fr.insee.rmes.modeles.geo.territoire.Commune;
import fr.insee.rmes.modeles.geo.territoire.Departement;
import fr.insee.rmes.modeles.geo.territoire.Region;

@ExtendWith(MockitoExtension.class)
public class GeoApiIdentificationTest extends AbstractApiTest {

    @InjectMocks
    private GeoApiIdentification geoApi;

    private Commune commune = new Commune();
    private Country country = new Country();
    private Region region = new Region();
    private Departement departement = new Departement();
    private Arrondissement arrondissement = new Arrondissement();

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
    public void givenGetCommune_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
        commune.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(commune, Boolean.TRUE);

        // Call method header content = xml
        geoApi.getCommune("something", MediaType.APPLICATION_XML, "2000-01-01");
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetCommune_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getCommune("something", MediaType.APPLICATION_XML, "nimportequoi");
        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
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

    @Test
    public void givenGetRegion_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
        region.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(region, Boolean.TRUE);

        // Call method header content = xml
        geoApi.getRegion("something", MediaType.APPLICATION_XML, "2000-01-01");
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetRegion_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getRegion("something", MediaType.APPLICATION_XML, "nimportequoi");
        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void givenGetDepartement_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnOnePojo(departement, Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getDepartement("something", MediaType.APPLICATION_XML, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getDepartement("something", MediaType.APPLICATION_JSON, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetDepartement_whenCorrectRequestt_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock methods
        departement.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(departement, Boolean.TRUE);

        // Call method
        geoApi.getDepartement("something", MediaType.APPLICATION_JSON, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetDepartement_whenCorrectRequestt_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock methods
        departement.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(departement, Boolean.TRUE);

        // Call method
        geoApi.getDepartement("something", MediaType.APPLICATION_XML, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetDepartement_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
        departement.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(departement, Boolean.TRUE);

        // Call method header content = xml
        geoApi.getDepartement("something", MediaType.APPLICATION_XML, "2000-01-01");
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetDepartement_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getDepartement("something", MediaType.APPLICATION_XML, "nimportequoi");
        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

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
