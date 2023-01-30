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

import fr.insee.rmes.api.geo.territoire.CommuneApi;
import fr.insee.rmes.config.CacheHelper;
import fr.insee.rmes.utils.SparqlUtils;

public class CommuneApiIntegrationTest {

    @InjectMocks
    private CommuneApi geoApi;
    private final static String CODE = "01002";
    private final static String CODE_DESCENDANTS = "01015";
    private final static String CODE_SUIVANT = "01004";

    @Mock
    protected SparqlUtils mockSparqlUtils;
    
    @Before
    public void init() {
    	CacheHelper.getInstance().cleanActualCommunesCache();
        MockitoAnnotations.initMocks(this);
        
    }

    @Test
    public void givengetCommune_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.COMMUNE_MOCK_SERVER_RETURN_GET);
        Response response = geoApi.getByCode(CODE, MediaType.APPLICATION_JSON, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.COMMUNE_EXPECTED_RESPONSE_GET_JSON, response.getEntity());
    }

    @Test
    public void givengetCommune_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.COMMUNE_MOCK_SERVER_RETURN_GET);
        Response response = geoApi.getByCode(CODE, MediaType.APPLICATION_XML, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.COMMUNE_EXPECTED_RESPONSE_GET_XML, response.getEntity());
    }

    @Test
    public void givengetListeCommunes_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.COMMUNE_MOCK_SERVER_RETURN_LISTE);
        Response response = geoApi.getListe(MediaType.APPLICATION_JSON, null,"*", null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.COMMUNE_EXPECTED_RESPONSE_LISTE_TOP_JSON, response.getEntity());
    }

    @Test
    public void givengetListeCommunes_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.COMMUNE_MOCK_SERVER_RETURN_LISTE);
        Response response = geoApi.getListe(MediaType.APPLICATION_XML, null,null, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.COMMUNE_EXPECTED_RESPONSE_LISTE_TOP_XML, response.getEntity());
    }

    @Test
    public void givengetAscendantsCommunes_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.COMMUNE_MOCK_SERVER_RETURN_ASCENDANTS);
        Response response = geoApi.getAscendants(CODE, MediaType.APPLICATION_JSON, null, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.COMMUNE_EXPECTED_RESPONSE_ASCENDANTS_JSON, response.getEntity());
    }

    @Test
    public void givengetAscendantsCommunes_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.COMMUNE_MOCK_SERVER_RETURN_ASCENDANTS);
        Response response = geoApi.getAscendants(CODE, MediaType.APPLICATION_XML, null, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.COMMUNE_EXPECTED_RESPONSE_ASCENDANTS_XML, response.getEntity());
    }

    @Test
    public void givengetDescendantsCommunes_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.COMMUNE_MOCK_SERVER_RETURN_DESCENDANTS);
        Response response = geoApi.getDescendants(CODE_DESCENDANTS, MediaType.APPLICATION_JSON, null, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.COMMUNE_EXPECTED_RESPONSE_DESCENDANTS_JSON, response.getEntity());
    }

    @Test
    public void givengetDescendantsCommunes_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.COMMUNE_MOCK_SERVER_RETURN_DESCENDANTS);
        Response response = geoApi.getDescendants(CODE_DESCENDANTS, MediaType.APPLICATION_XML, null, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.COMMUNE_EXPECTED_RESPONSE_DESCENDANTS_XML, response.getEntity());
    }

    @Test
    public void givengetPrecedentsCommunes_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.COMMUNE_MOCK_SERVER_RETURN_PRECEDENTS);
        Response response = geoApi.getPrecedent(CODE, MediaType.APPLICATION_JSON, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.COMMUNE_EXPECTED_RESPONSE_PRECEDENTS_JSON, response.getEntity());
    }

    @Test
    public void givengetPrecedentsCommunes_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.COMMUNE_MOCK_SERVER_RETURN_PRECEDENTS);
        Response response = geoApi.getPrecedent(CODE, MediaType.APPLICATION_XML, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.COMMUNE_EXPECTED_RESPONSE_PRECEDENTS_XML, response.getEntity());
    }

    @Test
    public void givengetSuivantsCommunes_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.COMMUNE_MOCK_SERVER_RETURN_PRECEDENTS);
        Response response = geoApi.getSuivant(CODE_SUIVANT, MediaType.APPLICATION_JSON, "1973-01-01");
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.COMMUNE_EXPECTED_RESPONSE_PRECEDENTS_JSON, response.getEntity());
    }

    @Test
    public void givengetSuivantsCommunes_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.COMMUNE_MOCK_SERVER_RETURN_PRECEDENTS);
        Response response = geoApi.getSuivant(CODE_SUIVANT, MediaType.APPLICATION_XML, "1973-01-01");
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.COMMUNE_EXPECTED_RESPONSE_PRECEDENTS_XML, response.getEntity());
    }

}
