package fr.insee.rmes.api.geo.territoire;

import fr.insee.rmes.api.AbstractApiTest;
import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.territoire.CantonOuVille;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
@ExtendWith(MockitoExtension.class)
public class CantonOuVilleApiTest extends AbstractApiTest {


    @InjectMocks
    private CantonOuVilleApi geoApi;

    private CantonOuVille cantonOuVille = new CantonOuVille();
    @Test
    void givenGetCantonOuVille_whenCorrectRequestt_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock methods
        cantonOuVille.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(cantonOuVille, Boolean.TRUE);

        // Call method
        geoApi.getByCode("something", MediaType.APPLICATION_JSON, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVille_whenCorrectRequestt_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock methods
        cantonOuVille.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(cantonOuVille, Boolean.TRUE);

        // Call method
        geoApi.getByCode("something", MediaType.APPLICATION_XML, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVille_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnOnePojo(cantonOuVille, Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getByCode("something", MediaType.APPLICATION_XML, null);
        Assertions.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getByCode("something", MediaType.APPLICATION_JSON, null);
        Assertions.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVille_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
        cantonOuVille.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(cantonOuVille, Boolean.TRUE);

        // Call method header content = xml
        geoApi.getByCode("something", MediaType.APPLICATION_XML, "2000-01-01");
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVille_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getByCode("something", MediaType.APPLICATION_XML, "nimportequoi");
        Assertions.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void givenGetListeCantonOuVille_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method
        geoApi.getListe(MediaType.APPLICATION_JSON, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetListeCantonOuVille_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method
        geoApi.getListe(MediaType.APPLICATION_XML, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetListeCantonOuVille_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getListe(MediaType.APPLICATION_XML, null);
        Assertions.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getListe(MediaType.APPLICATION_JSON, null);
        Assertions.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @ParameterizedTest
    @ValueSource(strings = {"2000-01-01", "*"})
    @NullSource
        //default = current day
    void givenGetListeCantonOuVille_WhenCorrectRequest_thenParameterDateIsRight(String date) {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method header content = xml
        geoApi.getListe(MediaType.APPLICATION_XML, date);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetListeCantonOuVille_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getListe(MediaType.APPLICATION_XML, "nimportequoi");
        Assertions.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void givenGetListCantonOuVille_WhenHeaderIsNotAcceptable() {
        // Mock methods
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populateMultiPOJO(Mockito.anyString(), Mockito.any())).thenReturn(list);
        list.add(new CantonOuVille());

        // Call method header content = text plain
        Response response = geoApi.getListe(MediaType.TEXT_PLAIN, null);
        Assertions.assertEquals(Response.Status.NOT_ACCEPTABLE.getStatusCode(), response.getStatus());
    }

    @Test
    void givenGetCantonOuVilleDescendants_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method
        geoApi.getDescendants("something", MediaType.APPLICATION_JSON, null, null, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVilleDescendants_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method
        geoApi.getDescendants("something", MediaType.APPLICATION_XML, null, null, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVilleDescendants_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getDescendants("something", MediaType.APPLICATION_JSON, null, null, null);
        Assertions.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getDescendants("something", MediaType.APPLICATION_XML, null, null, null);
        Assertions.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVilleDescendants_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method header content = xml
        geoApi.getDescendants("something", MediaType.APPLICATION_XML, "2000-01-01", null, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVilleDescendants_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getDescendants("something", MediaType.APPLICATION_XML, "nimportequoi", null, null);
        Assertions.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void givenGetCantonOuVilleDescendants_WhenCorrectRequest_thenParameterTypeIsNull() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method header content = xml
        geoApi.getDescendants("something", MediaType.APPLICATION_XML, null, null, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVilleDescendants_WhenCorrectRequest_thenParameterTypeIsRight() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method header content = xml
        geoApi
                .getDescendants(
                        "something",
                        MediaType.APPLICATION_XML,
                        null,
                            EnumTypeGeographie.CANTON_OU_VILLE.getTypeObjetGeo(), null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVilleDescendants_WhenCorrectRequest_thenParameterTypeIsBad() {

        // Call method header content = xml
        Response response = geoApi.getDescendants("something", MediaType.APPLICATION_XML, null, "unTypeQuelconque", null);
        Assertions.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }


    @Test
    void givenGetCantonOuVilleAscendants_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method
        geoApi.getAscendants("something", MediaType.APPLICATION_JSON, null, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVilleAscendants_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method
        geoApi.getAscendants("something", MediaType.APPLICATION_XML, null, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVilleAscendants_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getAscendants("something", MediaType.APPLICATION_JSON, null, null);
        Assertions.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getAscendants("something", MediaType.APPLICATION_XML, null, null);
        Assertions.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVilleAscendants_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method header content = xml
        geoApi.getAscendants("something", MediaType.APPLICATION_XML, "2000-01-01", null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVilleAscendants_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getAscendants("something", MediaType.APPLICATION_XML, "nimportequoi", null);
        Assertions.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void givenGetCantonOuVilleAscendants_WhenCorrectRequest_thenParameterTypeIsNull() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method header content = xml
        geoApi.getAscendants("something", MediaType.APPLICATION_XML, null, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVilleAscendants_WhenCorrectRequest_thenParameterTypeIsRight() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method header content = xml
        geoApi
                .getAscendants(
                        "something",
                        MediaType.APPLICATION_XML,
                        null,
                        EnumTypeGeographie.CANTON_OU_VILLE.getTypeObjetGeo());
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVilleAscendants_WhenCorrectRequest_thenParameterTypeIsBad() {

        // Call method header content = xml
        Response response = geoApi.getAscendants("something", MediaType.APPLICATION_XML, null, "unTypeQuelconque");
        Assertions.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void givenGetCantonOuVillePrecedent_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method
        geoApi.getPrecedent("something", MediaType.APPLICATION_JSON, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVillePrecedent_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method
        geoApi.getPrecedent("something", MediaType.APPLICATION_XML, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVillePrecedent_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getSuivant("something", MediaType.APPLICATION_JSON, null);
        Assertions.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getPrecedent("something", MediaType.APPLICATION_XML, null);
        Assertions.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVillePrecedent_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method header content = xml
        geoApi.getPrecedent("something", MediaType.APPLICATION_XML, "2000-01-01");
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVillePrecedent_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getPrecedent("something", MediaType.APPLICATION_XML, "nimportequoi");
        Assertions.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }


    @Test
    void givenGetCantonOuVilleSuivant_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method
        geoApi.getSuivant("something", MediaType.APPLICATION_JSON, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVilleSuivant_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method
        geoApi.getSuivant("something", MediaType.APPLICATION_XML, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVilleSuivant_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getSuivant("something", MediaType.APPLICATION_JSON, null);
        Assertions.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getSuivant("something", MediaType.APPLICATION_XML, null);
        Assertions.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVilleSuivant_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method header content = xml
        geoApi.getSuivant("something", MediaType.APPLICATION_XML, "2000-01-01");
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVilleSuivant_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getSuivant("something", MediaType.APPLICATION_XML, "nimportequoi");
        Assertions.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }


    @Test
    void givenGetCantonOuVilleProjetes_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method
        geoApi.getProjection("something", MediaType.APPLICATION_JSON, null, "2019-01-01");
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVilleProjetes_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method
        geoApi.getProjection("something", MediaType.APPLICATION_XML, null, "2019-01-01");
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVilleProjetes_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApi.getProjection("something", MediaType.APPLICATION_JSON, null, "2019-01-01");
        Assertions.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getProjection("something", MediaType.APPLICATION_XML, null, "2019-01-01");
        Assertions.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVilleProjetes_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method header content = xml
        geoApi.getProjection("something", MediaType.APPLICATION_XML, "2000-01-01", "2019-01-01");
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVilleProjetes_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getProjection("something", MediaType.APPLICATION_XML, "nimportequoi", "2019-01-01");
        Assertions.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void givenGetCantonOuVilleProjetes_WhenCorrectRequest_thenParameterDateProjeteIsRight() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method header content = xml
        geoApi.getProjection("something", MediaType.APPLICATION_XML, "2000-01-01", "2019-01-01");
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonOuVilleProjetes_WhenCorrectRequest_thenParameterDateProjeteIsBad() {

        // Call method header content = xml
        Response response =
                geoApi.getProjection("something", MediaType.APPLICATION_XML, "nimportequoi", "nimportequoi");
        Assertions.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void givenGetCantonOuVilleProjetes_WhenCorrectRequest_thenParameterDateProjeteIsNull() {

        // Call method header content = xml
        Response response = geoApi.getProjection("something", MediaType.APPLICATION_XML, "nimportequoi", null);
        Assertions.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
}