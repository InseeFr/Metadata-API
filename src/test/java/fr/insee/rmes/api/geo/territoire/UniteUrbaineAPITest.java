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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.insee.rmes.api.AbstractApiTest;
import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.territoire.UniteUrbaine;

@ExtendWith(MockitoExtension.class)
public class UniteUrbaineAPITest extends AbstractApiTest {

    @InjectMocks
    private UniteUrbaineApi geoApi;
    private UniteUrbaine uniteUrbaine = new UniteUrbaine();
    
    /*
     * Test Identification methods
     */    
    
    @Test
    void givenGetUniteUrbaine_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock methods
    	uniteUrbaine.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(uniteUrbaine, Boolean.TRUE);

        // Call method
        geoApi.getByCode("something", MediaType.APPLICATION_JSON, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }
    
    @Test
    void givenGetUniteUrbaine_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock methods
    	uniteUrbaine.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(uniteUrbaine, Boolean.TRUE);

        // Call method
        geoApi.getByCode("something", MediaType.APPLICATION_XML, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }
    
    @Test
    void givenGetUniteUrbaine_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnOnePojo(uniteUrbaine, Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getByCode("something", MediaType.APPLICATION_XML, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getByCode("something", MediaType.APPLICATION_JSON, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetUniteUrbaine_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
    	uniteUrbaine.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(uniteUrbaine, Boolean.TRUE);

        // Call method header content = xml
        geoApi.getByCode("something", MediaType.APPLICATION_XML, "2000-01-01");
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetUniteUrbaine_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getByCode("something", MediaType.APPLICATION_XML, "nimportequoi");
        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
    
    /*
     * Test Descendants methods
     */
    
    @Test
    void givenGetUniteUrbaineDescendants_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new UniteUrbaine());

        // Call method
        geoApi.getDescendants("something", MediaType.APPLICATION_JSON, null, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetUniteUrbaineDescendants_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new UniteUrbaine());

        // Call method
        geoApi.getDescendants("something", MediaType.APPLICATION_XML, null, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetUniteUrbaineDescendants_WhenCorrectRequest_thenResponseIsNotFound() {

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
    void givenGetUniteUrbaineDescendants_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new UniteUrbaine());

        // Call method header content = xml
        geoApi.getDescendants("something", MediaType.APPLICATION_XML, "2000-01-01", null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetUniteUrbaineDescendants_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getDescendants("something", MediaType.APPLICATION_XML, "nimportequoi", null);
        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void givenGetUniteUrbaineDescendants_WhenCorrectRequest_thenParameterTypeIsNull() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new UniteUrbaine());

        // Call method header content = xml
        geoApi.getDescendants("something", MediaType.APPLICATION_XML, null, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetUniteUrbaineDescendants_WhenCorrectRequest_thenParameterTypeIsRight() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new UniteUrbaine());

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
    void givenGetUniteUrbaineDescendants_WhenCorrectRequest_thenParameterTypeIsBad() {

        // Call method header content = xml
        Response response = geoApi.getDescendants("something", MediaType.APPLICATION_XML, null, "unTypeQuelconque");
        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }


    /*
     * Test getList method
     */
    
    @Test
    void givenGetListeUniteUrbaine_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new UniteUrbaine());

        // Call method
        geoApi.getListe(MediaType.APPLICATION_JSON, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetListeUniteUrbaine_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new UniteUrbaine());

        // Call method
        geoApi.getListe(MediaType.APPLICATION_XML, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetListeUniteUrbaine_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getListe(MediaType.APPLICATION_XML, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getListe(MediaType.APPLICATION_JSON, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @ParameterizedTest
    @ValueSource(strings = {"2000-01-01", "*"})
    @NullSource //default = current day
    void givenGetListeUniteUrbaine_WhenCorrectRequest_thenParameterDateIsRight(String date) {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new UniteUrbaine());

        // Call method header content = xml
        geoApi.getListe(MediaType.APPLICATION_XML, date);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetListeUniteUrbaine_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getListe(MediaType.APPLICATION_XML, "nimportequoi");
        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

}
