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
import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.territoire.AireAttraction;

@ExtendWith(MockitoExtension.class)
public class AireAttractionAPITest extends AbstractApiTest {

    @InjectMocks
    private AireAttractionApi geoApi;
    private AireAttraction aireAttraction = new AireAttraction();
    
    /*
     * Test Identification methods
     */    
    
    @Test
    void givenGetAireAttraction_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock methods
    	aireAttraction.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(aireAttraction, Boolean.TRUE);

        // Call method
        geoApi.getByCode("something", MediaType.APPLICATION_JSON, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }
    
    @Test
    void givenGetAireAttraction_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock methods
    	aireAttraction.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(aireAttraction, Boolean.TRUE);

        // Call method
        geoApi.getByCode("something", MediaType.APPLICATION_XML, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }
    
    @Test
    void givenGetAireAttraction_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnOnePojo(aireAttraction, Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getByCode("something", MediaType.APPLICATION_XML, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getByCode("something", MediaType.APPLICATION_JSON, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetAireAttraction_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
    	aireAttraction.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(aireAttraction, Boolean.TRUE);

        // Call method header content = xml
        geoApi.getByCode("something", MediaType.APPLICATION_XML, "2000-01-01");
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetAireAttraction_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getByCode("something", MediaType.APPLICATION_XML, "nimportequoi");
        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
    
    /*
     * Test Descendants methods
     */
    
    @Test
    void givenGetAireAttractionDescendants_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new AireAttraction());

        // Call method
        geoApi.getDescendants("something", MediaType.APPLICATION_JSON, null, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetAireAttractionDescendants_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new AireAttraction());

        // Call method
        geoApi.getDescendants("something", MediaType.APPLICATION_XML, null, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetAireAttractionDescendants_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getDescendants("something", MediaType.APPLICATION_JSON, null, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getDescendants("something", MediaType.APPLICATION_XML, null, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetAireAttractionDescendants_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new AireAttraction());

        // Call method header content = xml
        geoApi.getDescendants("something", MediaType.APPLICATION_XML, "2000-01-01", null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetAireAttractionDescendants_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getDescendants("something", MediaType.APPLICATION_XML, "nimportequoi", null);
        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void givenGetAireAttractionDescendants_WhenCorrectRequest_thenParameterTypeIsNull() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new AireAttraction());

        // Call method header content = xml
        geoApi.getDescendants("something", MediaType.APPLICATION_XML, null, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetAireAttractionDescendants_WhenCorrectRequest_thenParameterTypeIsRight() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new AireAttraction());

        // Call method header content = xml
        geoApi
            .getDescendants(
                "something",
                MediaType.APPLICATION_XML,
                null,
                EnumTypeGeographie.ARRONDISSEMENT.getTypeObjetGeo());
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetAireAttractionDescendants_WhenCorrectRequest_thenParameterTypeIsBad() {

        // Call method header content = xml
        Response response = geoApi.getDescendants("something", MediaType.APPLICATION_XML, null, "unTypeQuelconque");
        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    
}
