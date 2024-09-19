package fr.insee.rmes.api.geo.territoire;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import fr.insee.rmes.modeles.utils.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.insee.rmes.api.AbstractApiTest;
import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.territoire.CirconscriptionTerritoriale;


@ExtendWith(MockitoExtension.class)
public class CirconscriptionTerritorialeApiTest extends AbstractApiTest {
	
	 @InjectMocks
	    private CirconscriptionTerritorialeApi geoApi;
	 
	 	private CirconscriptionTerritoriale circonscriptionTerritoriale = new CirconscriptionTerritoriale();
	 	
	 	
	    @Test
	    void givenGetCirconscriptionTerritoriale_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

	        // Mock methods
	    	circonscriptionTerritoriale.setUri("something");
	        this.mockUtilsMethodsThenReturnOnePojo(circonscriptionTerritoriale, Boolean.TRUE);

	        // Call method
	        geoApi.getByCode("something", MediaType.APPLICATION_JSON, null);
	        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
	    }
	

	    @Test
	    void givenGetCirconscriptionTerritoriale_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

	        // Mock methods
	    	circonscriptionTerritoriale.setUri("something");
	        this.mockUtilsMethodsThenReturnOnePojo(circonscriptionTerritoriale, Boolean.TRUE);

	        // Call method
	        geoApi.getByCode("something", MediaType.APPLICATION_XML, null);
	        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
	    }
	    
	    @Test
	    void givenGetCirconscriptionTerritoriale_WhenCorrectRequest_thenResponseIsNotFound() {

	        // Mock methods
	        this.mockUtilsMethodsThenReturnOnePojo(circonscriptionTerritoriale, Boolean.FALSE);

	        // Call method header content = xml
	        Response response = geoApi.getByCode("something", MediaType.APPLICATION_XML, null);
	        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

	        // Call method header content = json
	        response = geoApi.getByCode("something", MediaType.APPLICATION_JSON, null);
	        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

	        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
	    }
	    
	    
	    @Test
	    void givenGetCirconscriptionTerritoriale_WhenCorrectRequest_thenParameterDateIsRight() {

	        // Mock methods
	    	circonscriptionTerritoriale.setUri("something");
	        this.mockUtilsMethodsThenReturnOnePojo(circonscriptionTerritoriale, Boolean.TRUE);

	        // Call method header content = xml
	        geoApi.getByCode("something", MediaType.APPLICATION_XML, new Date("2000-01-01"));
	        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
	    }
	    
	    @Test
	    void givenGetCirconscriptionTerritoriale_WhenCorrectRequest_thenParameterDateIsBad() {

	        // Call method header content = xml
	        Response response = geoApi.getByCode("something", MediaType.APPLICATION_XML, new Date("nimportequoi"));
	        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	    }

	    @Test
	    void givenGetCirconscriptionTerritorialeAscendants_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

	        // Mock methods
	        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
	        list.add(new CirconscriptionTerritoriale());

	        // Call method
	        geoApi.getAscendants("something", MediaType.APPLICATION_JSON, null, null);
	        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
	    }
	    
	    @Test
	    void givenGetCirconscriptionTerritorialeAscendants_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

	        // Mock methods
	        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
	        list.add(new CirconscriptionTerritoriale());

	        // Call method
	        geoApi.getAscendants("something", MediaType.APPLICATION_XML, null, null);
	        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
	    }
	    
	    @Test
	    void givenGetCirconscriptionTerritorialeAscendants_WhenCorrectRequest_thenResponseIsNotFound() {

	        // Mock methods
	        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);

	        // Call method header content = xml
	        Response response = geoApi.getAscendants("something", MediaType.APPLICATION_JSON, null, null);
	        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

	        // Call method header content = json
	        response = geoApi.getAscendants("something", MediaType.APPLICATION_XML, null, null);
	        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

	        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
	    }

	    @Test
	    void givenGetCirconscriptionTerritorialeAscendants_WhenCorrectRequest_thenParameterDateIsRight() {

	        // Mock methods
	        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
	        list.add(new CirconscriptionTerritoriale());

	        // Call method header content = xml
	        geoApi.getAscendants("something", MediaType.APPLICATION_XML, new Date("2000-01-01"), null);
	        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
	    }
	    
	    @Test
	    void givenGetCirconscriptionTerritorialeAscendants_WhenCorrectRequest_thenParameterDateIsBad() {

	        // Call method header content = xml
	        Response response = geoApi.getAscendants("something", MediaType.APPLICATION_XML, new Date("nimportequoi"), null);
	        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	    }

	    @Test
	    void givenGetCirconscriptionTerritorialeAscendants_WhenCorrectRequest_thenParameterTypeIsNull() {

	        // Mock methods
	        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
	        list.add(new CirconscriptionTerritoriale());

	        // Call method header content = xml
	        geoApi.getAscendants("something", MediaType.APPLICATION_XML, null, null);
	        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
	    }

	    @Test
	    void givenGetCirconscriptionTerritorialeAscendants_WhenCorrectRequest_thenParameterTypeIsRight() {

	        // Mock methods
	        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
	        list.add(new CirconscriptionTerritoriale());

	        // Call method header content = xml
	        geoApi
	            .getAscendants(
	                "something",
	                MediaType.APPLICATION_XML,
	                null,
	                EnumTypeGeographie.COLLECTIVITE_D_OUTRE_MER.getTypeObjetGeo());
	        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
	    }
	    
}
