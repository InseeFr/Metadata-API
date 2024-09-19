package fr.insee.rmes.api.geo.territoire;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fr.insee.rmes.modeles.utils.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.insee.rmes.api.AbstractApiTest;
import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.territoire.CollectiviteDOutreMer;






@ExtendWith(MockitoExtension.class)
public class CollectivitesDOutreMerApiTest extends AbstractApiTest {
	
	@InjectMocks
    private CollectivitesDOutreMerAPI geoApi;

    private CollectiviteDOutreMer collectiviteDOutreMer = new CollectiviteDOutreMer();
    
    @Test
    void givenGetCollectiviteDOutreMer_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock methods
    	collectiviteDOutreMer.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(collectiviteDOutreMer, Boolean.TRUE);

        // Call method fr.insee.rmes.api.geo.territoire.CollectivitesDOutreMerAPI.getByCode(String, String, String)
        geoApi.getByCode("something", MediaType.APPLICATION_JSON, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }
    
    @Test
    void givenGetCollectiviteDOutreMer_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock methods
    	collectiviteDOutreMer.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(collectiviteDOutreMer, Boolean.TRUE);

        // Call method
        geoApi.getByCode("something", MediaType.APPLICATION_XML, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }
    
    @Test
    void givenGetCollectiviteDOutreMer_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnOnePojo(collectiviteDOutreMer, Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getByCode("something", MediaType.APPLICATION_XML, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getByCode("something", MediaType.APPLICATION_JSON, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }
    
    
    @Test
    void givenGetCollectiviteDOutreMer_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
    	collectiviteDOutreMer.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(collectiviteDOutreMer, Boolean.TRUE);

        // Call method header content = xml
        geoApi.getByCode("something", MediaType.APPLICATION_XML, new Date("2000-01-01"));
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }
    
    @Test
    void givenGetCollectiviteDOutreMer_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getByCode("something", MediaType.APPLICATION_XML, new Date("nimportequoi"));
        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void givenGetCollectiviteDOutreMerDescendants_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CollectiviteDOutreMer());

        // Call method
        geoApi.getDescendants("something", MediaType.APPLICATION_JSON, null, null, null);/*modifier suite a changement du nombre de variables */
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCollectiviteDOutreMerDescendants_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CollectiviteDOutreMer());

        // Call method
        geoApi.getDescendants("something", MediaType.APPLICATION_XML, null, null, null);/*modifier suite a changement du nombre de variables */
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    
    @Test
    void givenGetCollectiviteDOutreMerDescendants_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getDescendants("something", MediaType.APPLICATION_JSON, null, null, null);/*modifier suite a changement du nombre de variables */
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getDescendants("something", MediaType.APPLICATION_XML, null, null, null);/*modifier suite a changement du nombre de variables */
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCollectiviteDOutreMerDescendants_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CollectiviteDOutreMer());

        // Call method header content = xml
        geoApi.getDescendants("something", MediaType.APPLICATION_XML, new Date("2000-01-01"), null, null);/*modifier suite a changement du nombre de variables */
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCollectiviteDOutreMerDescendants_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getDescendants("something", MediaType.APPLICATION_XML, new Date("nimportequoi"), null, null);/*modifier suite a changement du nombre de variables */
        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void givenGetCollectiviteDOutreMerDescendants_WhenCorrectRequest_thenParameterTypeIsNull() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CollectiviteDOutreMer());

        // Call method header content = xml
        geoApi.getDescendants("something", MediaType.APPLICATION_XML, null, null, null);/*modifier suite a changement du nombre de variables */
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    
    @Test
    void givenGetCollectiviteDOutreMerDescendants_WhenCorrectRequest_thenParameterTypeIsRight() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CollectiviteDOutreMer());

        // Call method header content = xml
        geoApi
            .getDescendants("something", MediaType.APPLICATION_XML, null, EnumTypeGeographie.COMMUNE.getTypeObjetGeo(), null);/*modifier suite a changement du nombre de variables */
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCollectiviteDOutreMerDescendants_WhenCorrectRequest_thenParameterTypeIsBad() {

        // Call method header content = xml
        Response response = geoApi.getDescendants("something", MediaType.APPLICATION_XML, null, "unTypeQuelconque", null);/*modifier suite a changement du nombre de variables */
        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    
}
