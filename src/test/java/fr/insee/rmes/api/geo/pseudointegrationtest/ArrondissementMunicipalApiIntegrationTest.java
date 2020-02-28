package fr.insee.rmes.api.geo.pseudointegrationtest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.insee.rmes.api.geo.territoire.ArrondissementMunicipalApi;
import fr.insee.rmes.utils.SparqlUtils;

public class ArrondissementMunicipalApiIntegrationTest {

    @InjectMocks
    private ArrondissementMunicipalApi geoApi;
    private final static String CODE = "75107";
    private final static String CODE_SUIV_OU_PREC = "69385";

    @Mock
    protected SparqlUtils mockSparqlUtils;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givengetArrondissementMunicipal_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.ARRONDISSEMENT_MUNICIPAL_MOCK_SERVER_RETURN_GET);
        Response response = geoApi.getByCode(CODE, MediaType.APPLICATION_JSON, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.ARRONDISSEMENT_MUNICIPAL_EXPECTED_RESPONSE_GET_JSON, response.getEntity());
    }

    @Test
    public void givengetArrondissementMunicipal_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.ARRONDISSEMENT_MUNICIPAL_MOCK_SERVER_RETURN_GET);
        Response response = geoApi.getByCode(CODE, MediaType.APPLICATION_XML, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.ARRONDISSEMENT_MUNICIPAL_EXPECTED_RESPONSE_GET_XML, response.getEntity());
    }

    @Test
    public void givengetListeArrondissementMunicipals_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.ARRONDISSEMENT_MUNICIPAL_MOCK_SERVER_RETURN_LISTE);
        Response response = geoApi.getListe(MediaType.APPLICATION_JSON, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(
            ConstantForIntegration.ARRONDISSEMENT_MUNICIPAL_EXPECTED_RESPONSE_LISTE_TOP_JSON,
            response.getEntity());
    }

    @Test
    public void givengetListeArrondissementMunicipals_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.ARRONDISSEMENT_MUNICIPAL_MOCK_SERVER_RETURN_LISTE);
        Response response = geoApi.getListe(MediaType.APPLICATION_XML, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(
            ConstantForIntegration.ARRONDISSEMENT_MUNICIPAL_EXPECTED_RESPONSE_LISTE_TOP_XML,
            response.getEntity());
    }

    @Test
    public void givengetAscendantsArrondissementMunicipals_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.ARRONDISSEMENT_MUNICIPAL_MOCK_SERVER_RETURN_ASCENDANTS);
        Response response = geoApi.getAscendants(CODE, MediaType.APPLICATION_JSON, null, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(
            ConstantForIntegration.ARRONDISSEMENT_MUNICIPAL_EXPECTED_RESPONSE_ASCENDANTS_JSON,
            response.getEntity());
    }

    @Test
    public void givengetAscendantsArrondissementMunicipals_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.ARRONDISSEMENT_MUNICIPAL_MOCK_SERVER_RETURN_ASCENDANTS);
        Response response = geoApi.getAscendants(CODE, MediaType.APPLICATION_XML, null, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(
            ConstantForIntegration.ARRONDISSEMENT_MUNICIPAL_EXPECTED_RESPONSE_ASCENDANTS_XML,
            response.getEntity());
    }


    @Test
    public void givengetPrecedentsArrondissementMunicipals_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.ARRONDISSEMENT_MUNICIPAL_MOCK_SERVER_RETURN_PRECEDENTS);
        Response response = geoApi.getPrecedent(CODE_SUIV_OU_PREC, MediaType.APPLICATION_JSON, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(
            ConstantForIntegration.ARRONDISSEMENT_MUNICIPAL_EXPECTED_RESPONSE_PRECEDENTS_JSON,
            response.getEntity());
    }


    @Test
    public void givengetPrecedentsArrondissementMunicipals_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.ARRONDISSEMENT_MUNICIPAL_MOCK_SERVER_RETURN_PRECEDENTS);
        Response response = geoApi.getPrecedent(CODE_SUIV_OU_PREC, MediaType.APPLICATION_XML, "1943-01-01");
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(
            ConstantForIntegration.ARRONDISSEMENT_MUNICIPAL_EXPECTED_RESPONSE_PRECEDENTS_XML,
            response.getEntity());
    }

 
    @Test
    public void givengetSuivantsArrondissementMunicipals_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.ARRONDISSEMENT_MUNICIPAL_MOCK_SERVER_RETURN_PRECEDENTS);
        Response response = geoApi.getSuivant(CODE_SUIV_OU_PREC, MediaType.APPLICATION_JSON, "1943-01-01");
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(
            ConstantForIntegration.ARRONDISSEMENT_MUNICIPAL_EXPECTED_RESPONSE_SUIVANTS_JSON,
            response.getEntity());
    }


    @Test
    public void givengetSuivantsArrondissementMunicipals_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.ARRONDISSEMENT_MUNICIPAL_MOCK_SERVER_RETURN_PRECEDENTS);
        Response response = geoApi.getSuivant(CODE_SUIV_OU_PREC, MediaType.APPLICATION_XML, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(
            ConstantForIntegration.ARRONDISSEMENT_MUNICIPAL_EXPECTED_RESPONSE_SUIVANTS_XML,
            response.getEntity());
    }

}
