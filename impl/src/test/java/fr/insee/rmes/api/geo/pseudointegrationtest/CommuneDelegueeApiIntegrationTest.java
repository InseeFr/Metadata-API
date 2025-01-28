package fr.insee.rmes.api.geo.pseudointegrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.insee.rmes.api.geo.territoire.CommuneDelegueeApi;
import fr.insee.rmes.utils.SparqlUtils;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CommuneDelegueeApiIntegrationTest {

    @InjectMocks
    private CommuneDelegueeApi geoApi;
    private final static String CODE = "50564";

    @Mock
    protected SparqlUtils mockSparqlUtils;



    @Test
    public void givengetCommuneDeleguee_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.COMMUNE_DELEGUEE_MOCK_SERVER_RETURN_GET);
        Response response = geoApi.getByCode(CODE, MediaType.APPLICATION_JSON, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.COMMUNE_DELEGUEE_EXPECTED_RESPONSE_GET_JSON, response.getEntity());
    }

    @Test
    public void givengetCommuneDeleguee_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.COMMUNE_DELEGUEE_MOCK_SERVER_RETURN_GET);
        Response response = geoApi.getByCode(CODE, MediaType.APPLICATION_XML, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.COMMUNE_DELEGUEE_EXPECTED_RESPONSE_GET_XML, response.getEntity());
    }

    @Test
    public void givengetListeCommuneDeleguees_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.COMMUNE_DELEGUEE_MOCK_SERVER_RETURN_LISTE);
        Response response = geoApi.getListe(MediaType.APPLICATION_JSON, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.COMMUNE_DELEGUEE_EXPECTED_RESPONSE_LISTE_TOP_JSON, response.getEntity());
    }

    @Test
    public void givengetListeCommuneDeleguees_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.COMMUNE_DELEGUEE_MOCK_SERVER_RETURN_LISTE);
        Response response = geoApi.getListe(MediaType.APPLICATION_XML, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.COMMUNE_DELEGUEE_EXPECTED_RESPONSE_LISTE_TOP_XML, response.getEntity());
    }

    @Test
    public void givengetAscendantsCommuneDeleguees_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.COMMUNE_DELEGUEE_MOCK_SERVER_RETURN_ASCENDANTS);
        Response response = geoApi.getAscendants(CODE, MediaType.APPLICATION_JSON, null, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.COMMUNE_DELEGUEE_EXPECTED_RESPONSE_ASCENDANTS_JSON, response.getEntity());
    }

    @Test
    public void givengetAscendantsCommuneDeleguees_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.COMMUNE_DELEGUEE_MOCK_SERVER_RETURN_ASCENDANTS);
        Response response = geoApi.getAscendants(CODE, MediaType.APPLICATION_XML, null, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.COMMUNE_DELEGUEE_EXPECTED_RESPONSE_ASCENDANTS_XML, response.getEntity());
    }

}
