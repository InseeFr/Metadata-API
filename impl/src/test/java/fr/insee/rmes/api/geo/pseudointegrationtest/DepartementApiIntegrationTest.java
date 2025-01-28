package fr.insee.rmes.api.geo.pseudointegrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import fr.insee.rmes.modeles.utils.Date;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.insee.rmes.api.geo.territoire.DepartementApi;
import fr.insee.rmes.utils.SparqlUtils;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DepartementApiIntegrationTest {

    @InjectMocks
    private DepartementApi geoApi;
    private final static String CODE = "01";
    private final static String CODE_PREC_OR_SUIV = "78";

    @Mock
    protected SparqlUtils mockSparqlUtils;



    @Test
    public void givengetDepartement_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.DEPARTEMENT_MOCK_SERVER_RETURN_GET);
        Response response = geoApi.getByCode(CODE, MediaType.APPLICATION_JSON, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.DEPARTEMENT_EXPECTED_RESPONSE_GET_JSON, response.getEntity());
    }

    @Test
    public void givengetDepartement_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.DEPARTEMENT_MOCK_SERVER_RETURN_GET);
        Response response = geoApi.getByCode(CODE, MediaType.APPLICATION_XML, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.DEPARTEMENT_EXPECTED_RESPONSE_GET_XML, response.getEntity());
    }

    @Test
    public void givengetListeDepartements_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.DEPARTEMENT_MOCK_SERVER_RETURN_LISTE);
        Response response = geoApi.getListe(MediaType.APPLICATION_JSON, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.DEPARTEMENT_EXPECTED_RESPONSE_LISTE_TOP_JSON, response.getEntity());
    }

    @Test
    public void givengetListeDepartements_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.DEPARTEMENT_MOCK_SERVER_RETURN_LISTE);
        Response response = geoApi.getListe(MediaType.APPLICATION_XML, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.DEPARTEMENT_EXPECTED_RESPONSE_LISTE_TOP_XML, response.getEntity());
    }

    @Test
    public void givengetAscendantsDepartements_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.DEPARTEMENT_MOCK_SERVER_RETURN_ASCENDANTS);
        Response response = geoApi.getAscendants(CODE, MediaType.APPLICATION_JSON, null, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.DEPARTEMENT_EXPECTED_RESPONSE_ASCENDANTS_JSON, response.getEntity());
    }

    @Test
    public void givengetAscendantsDepartements_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.DEPARTEMENT_MOCK_SERVER_RETURN_ASCENDANTS);
        Response response = geoApi.getAscendants(CODE, MediaType.APPLICATION_XML, null, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.DEPARTEMENT_EXPECTED_RESPONSE_ASCENDANTS_XML, response.getEntity());
    }

    @Test /*modifier suite a changement du nombre de variables */
    public void givengetDescendantsDepartements_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.DEPARTEMENT_MOCK_SERVER_RETURN_DESCENDANTS);
        Response response = geoApi.getDescendants(CODE, MediaType.APPLICATION_JSON, null, null, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.DEPARTEMENT_EXPECTED_RESPONSE_DESCENDANTS_JSON, response.getEntity());
    }

    @Test /*modifier suite a changement du nombre de variables */
    public void givengetDescendantsDepartements_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.DEPARTEMENT_MOCK_SERVER_RETURN_DESCENDANTS);
        Response response = geoApi.getDescendants(CODE, MediaType.APPLICATION_XML, null, null, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.DEPARTEMENT_EXPECTED_RESPONSE_DESCENDANTS_XML, response.getEntity());
    }

    @Test
    public void givengetPrecedentsDepartements_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.DEPARTEMENT_MOCK_SERVER_RETURN_PRECEDENTS);
        Response response = geoApi.getPrecedent(CODE_PREC_OR_SUIV, MediaType.APPLICATION_JSON, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.DEPARTEMENT_EXPECTED_RESPONSE_PRECEDENTS_JSON, response.getEntity());
    }

    @Test
    public void givengetPrecedentsDepartements_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.DEPARTEMENT_MOCK_SERVER_RETURN_PRECEDENTS);
        Response response = geoApi.getPrecedent(CODE_PREC_OR_SUIV, MediaType.APPLICATION_XML, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.DEPARTEMENT_EXPECTED_RESPONSE_PRECEDENTS_XML, response.getEntity());
    }

    @Test
    public void givengetSuivantsDepartements_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.DEPARTEMENT_MOCK_SERVER_RETURN_SUIVANTS);
        Response response = geoApi.getSuivant(CODE_PREC_OR_SUIV, MediaType.APPLICATION_JSON, new Date("1973-01-01"));
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.DEPARTEMENT_EXPECTED_RESPONSE_SUIVANTS_JSON, response.getEntity());
    }

    @Test
    public void givengetSuivantsDepartements_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.DEPARTEMENT_MOCK_SERVER_RETURN_SUIVANTS);
        Response response = geoApi.getSuivant(CODE_PREC_OR_SUIV, MediaType.APPLICATION_XML, new Date("1973-01-01"));
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.DEPARTEMENT_EXPECTED_RESPONSE_SUIVANTS_XML, response.getEntity());
    }

}
