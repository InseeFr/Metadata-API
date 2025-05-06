package fr.insee.rmes.api.geo.pseudointegrationtest;

import fr.insee.rmes.api.geo.PaysApi;
import fr.insee.rmes.modeles.utils.Header;
import fr.insee.rmes.utils.SparqlUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaysApiIntegrationTest {

    @InjectMocks
    private PaysApi geoApi;
    private final static String CODE = "99350";

    @Mock
    protected SparqlUtils mockSparqlUtils;



/*
    @Test
    void givengetDepartement_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
                .thenReturn(ConstantForIntegration.PAYS_MOCK_SERVER_RETURN_GET);
        Header header = new Header(MediaType.APPLICATION_JSON);
        Response response = geoApi.getByCode(CODE, header);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.PAYS_EXPECTED_RESPONSE_GET_JSON, response.getEntity());
    }

    @Test
    void givengetDepartement_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.PAYS_MOCK_SERVER_RETURN_GET);
        Header header = new Header(MediaType.APPLICATION_JSON);
        Response response = geoApi.getByCode(CODE, header);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.PAYS_EXPECTED_RESPONSE_GET_XML, response.getEntity());
    }

@Test
void givengetDepartement_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
    when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(ConstantForIntegration.PAYS_MOCK_SERVER_RETURN_GET);
    Header header = new Header(MediaType.APPLICATION_XML);
    Response response = geoApi.getByCode(CODE, header);
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    assertEquals(ConstantForIntegration.PAYS_EXPECTED_RESPONSE_GET_XML, response.getEntity());
}
*/
}
