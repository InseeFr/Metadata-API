package fr.insee.rmes.api.geo.territoire;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.ws.rs.Path;
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
import fr.insee.rmes.api.geo.ConstGeoApi;
import fr.insee.rmes.modeles.geo.territoire.ZoneEmploi;
import io.swagger.v3.oas.annotations.tags.Tag;

@ExtendWith(MockitoExtension.class)
public class ZoneEmploiAPITest extends AbstractApiTest {

    @InjectMocks
    private ZoneEmploiApi geoApi;
    private ZoneEmploi zoneEmploi = new ZoneEmploi();
    
    /*
     * Test Identification methods
     */    
    
    @Test
    void givenGetZoneEmploi_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock methods
    	zoneEmploi.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(zoneEmploi, Boolean.TRUE);

        // Call method
        geoApi.getByCode("something", MediaType.APPLICATION_JSON, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }
    
    @Test
    void givenGetZoneEmploi_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock methods
    	zoneEmploi.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(zoneEmploi, Boolean.TRUE);

        // Call method
        geoApi.getByCode("something", MediaType.APPLICATION_XML, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }
    
    @Test
    void givenGetZoneEmploi_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnOnePojo(zoneEmploi, Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getByCode("something", MediaType.APPLICATION_XML, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getByCode("something", MediaType.APPLICATION_JSON, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetZoneEmploi_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
    	zoneEmploi.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(zoneEmploi, Boolean.TRUE);

        // Call method header content = xml
        geoApi.getByCode("something", MediaType.APPLICATION_XML, "2000-01-01");
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetZoneEmploi_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getByCode("something", MediaType.APPLICATION_XML, "nimportequoi");
        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
    
    /*
     * Test Ascendants methods
     */
    
}
