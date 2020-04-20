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

import fr.insee.rmes.api.geo.territoire.RegionApi;
import fr.insee.rmes.utils.SparqlUtils;

public class RegionApiIntegrationTest {

    @InjectMocks
    private RegionApi geoApi;
    private final static String CODE = "01";

    @Mock
    protected SparqlUtils mockSparqlUtils;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givengetRegion_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.REGION_MOCK_SERVER_RETURN_GET);
        Response response = geoApi.getByCode(CODE, MediaType.APPLICATION_JSON, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.REGION_EXPECTED_RESPONSE_GET_JSON, response.getEntity());
    }

    @Test
    public void givengetRegion_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.REGION_MOCK_SERVER_RETURN_GET);
        Response response = geoApi.getByCode(CODE, MediaType.APPLICATION_XML, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.REGION_EXPECTED_RESPONSE_GET_XML, response.getEntity());
    }

    @Test
    public void givengetListeRegions_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.REGION_MOCK_SERVER_RETURN_LISTE);
        Response response = geoApi.getListe(MediaType.APPLICATION_JSON, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.REGION_EXPECTED_RESPONSE_LISTE_TOP_JSON, response.getEntity());
    }

    @Test
    public void givengetListeRegions_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.REGION_MOCK_SERVER_RETURN_LISTE);
        Response response = geoApi.getListe(MediaType.APPLICATION_XML, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.REGION_EXPECTED_RESPONSE_LISTE_TOP_XML, response.getEntity());
    }

    @Test
    public void givengetDescendantsRegions_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.REGION_MOCK_SERVER_RETURN_DESCENDANTS);
        Response response = geoApi.getDescendants(CODE, MediaType.APPLICATION_JSON, null, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.REGION_EXPECTED_RESPONSE_DESCENDANTS_JSON, response.getEntity());
    }

    @Test
    public void givengetDescendantsRegions_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.REGION_MOCK_SERVER_RETURN_DESCENDANTS);
        Response response = geoApi.getDescendants(CODE, MediaType.APPLICATION_XML, null, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.REGION_EXPECTED_RESPONSE_DESCENDANTS_XML, response.getEntity());
    }

    @Test
    public void givengetPrecedentsRegions_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.REGION_MOCK_SERVER_RETURN_PRECEDENTS);
        Response response = geoApi.getPrecedent(CODE, MediaType.APPLICATION_JSON, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.REGION_EXPECTED_RESPONSE_PRECEDENTS_JSON, response.getEntity());
    }

    @Test
    public void givengetPrecedentsRegions_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.REGION_MOCK_SERVER_RETURN_PRECEDENTS);
        Response response = geoApi.getPrecedent(CODE, MediaType.APPLICATION_XML, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.REGION_EXPECTED_RESPONSE_PRECEDENTS_XML, response.getEntity());
    }

    @Test
    public void givengetSuivantsRegions_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.REGION_MOCK_SERVER_RETURN_SUIVANTS);
        Response response = geoApi.getSuivant(CODE, MediaType.APPLICATION_JSON, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.REGION_EXPECTED_RESPONSE_SUIVANTS_JSON, response.getEntity());
    }

    @Test
    public void givengetSuivantsRegions_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.REGION_MOCK_SERVER_RETURN_SUIVANTS);
        Response response = geoApi.getSuivant(CODE, MediaType.APPLICATION_XML, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.REGION_EXPECTED_RESPONSE_SUIVANTS_XML, response.getEntity());
    }
}
