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
import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.territoire.Commune;
import fr.insee.rmes.modeles.geo.territoire.Departement;

@ExtendWith(MockitoExtension.class)
public class GeoApiDescendantsTest extends AbstractApiTest {

    @InjectMocks
    private GeoApiDescendants geoApi;

    public void givenGetCommuneDescendants_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Commune());

        // Call method
        geoApi.getDescendantsFromCommune("something", MediaType.APPLICATION_JSON, null, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetCommuneDescendants_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Commune());

        // Call method
        geoApi.getDescendantsFromCommune("something", MediaType.APPLICATION_XML, null, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetCommuneDescendants_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getDescendantsFromCommune("something", MediaType.APPLICATION_JSON, null, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getDescendantsFromCommune("something", MediaType.APPLICATION_XML, null, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetCommuneDescendants_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Commune());

        // Call method header content = xml
        geoApi.getDescendantsFromCommune("something", MediaType.APPLICATION_XML, "2000-01-01", null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetCommuneDescendants_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response =
            geoApi.getDescendantsFromCommune("something", MediaType.APPLICATION_XML, "nimportequoi", null);
        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void givenGetCommuneDescendants_WhenCorrectRequest_thenParameterTypeIsNull() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Commune());

        // Call method header content = xml
        geoApi.getDescendantsFromCommune("something", MediaType.APPLICATION_XML, null, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetCommuneDescendants_WhenCorrectRequest_thenParameterTypeIsRight() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Commune());

        // Call method header content = xml
        geoApi
            .getDescendantsFromCommune(
                "something",
                MediaType.APPLICATION_XML,
                null,
                EnumTypeGeographie.ARRONDISSEMENT.getTypeObjetGeo());
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetCommuneDescendants_WhenCorrectRequest_thenParameterTypeIsBad() {

        // Call method header content = xml
        Response response =
            geoApi.getDescendantsFromCommune("something", MediaType.APPLICATION_XML, null, "unTypeQuelconque");
        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    public void givenGetDepartementDescendants_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Departement());

        // Call method
        geoApi.getDescendantsFromDepartement("something", MediaType.APPLICATION_JSON, null, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetDepartementDescendants_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Departement());

        // Call method
        geoApi.getDescendantsFromDepartement("something", MediaType.APPLICATION_XML, null, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetDepartementDescendants_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getDescendantsFromDepartement("something", MediaType.APPLICATION_JSON, null, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getDescendantsFromDepartement("something", MediaType.APPLICATION_XML, null, null);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetDepartementDescendants_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Departement());

        // Call method header content = xml
        geoApi.getDescendantsFromDepartement("something", MediaType.APPLICATION_XML, "2000-01-01", null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetDepartementDescendants_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response =
            geoApi.getDescendantsFromDepartement("something", MediaType.APPLICATION_XML, "nimportequoi", null);
        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void givenGetDepartementDescendants_WhenCorrectRequest_thenParameterTypeIsNull() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Departement());

        // Call method header content = xml
        geoApi.getDescendantsFromDepartement("something", MediaType.APPLICATION_XML, null, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetDepartementDescendants_WhenCorrectRequest_thenParameterTypeIsRight() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Departement());

        // Call method header content = xml
        geoApi
            .getDescendantsFromDepartement(
                "something",
                MediaType.APPLICATION_XML,
                null,
                EnumTypeGeographie.COMMUNE.getTypeObjetGeo());
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetDepartementDescendants_WhenCorrectRequest_thenParameterTypeIsBad() {

        // Call method header content = xml
        Response response =
            geoApi.getDescendantsFromDepartement("something", MediaType.APPLICATION_XML, null, "unTypeQuelconque");
        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

}
