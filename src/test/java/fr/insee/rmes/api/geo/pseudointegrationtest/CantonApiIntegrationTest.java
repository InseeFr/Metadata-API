package fr.insee.rmes.api.geo.pseudointegrationtest;

import fr.insee.rmes.api.geo.territoire.CantonAPI;
import fr.insee.rmes.utils.SparqlUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static fr.insee.rmes.api.geo.pseudointegrationtest.ConstantForIntegration.assertEqualsJson;
import static fr.insee.rmes.api.geo.pseudointegrationtest.ConstantForIntegration.assertEqualsXml;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CantonApiIntegrationTest {

    @InjectMocks
    private CantonAPI geoApi;
    private final static String CODE = "0105";

    @Mock
    protected SparqlUtils mockSparqlUtils;

    @Test
    void givengetCanton_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
                .thenReturn(ConstantForIntegration.CANTON_MOCK_SERVER_RETURN_GET);
        Response response = geoApi.getByCode(CODE, MediaType.APPLICATION_JSON, null);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEqualsJson(ConstantForIntegration.CANTON_EXPECTED_RESPONSE_GET_JSON, response.getEntity());
    }

    @Test
    void givengetCanton_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
                .thenReturn(ConstantForIntegration.CANTON_MOCK_SERVER_RETURN_GET);
        Response response = geoApi.getByCode(CODE, MediaType.APPLICATION_XML, null);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEqualsXml(ConstantForIntegration.CANTON_EXPECTED_RESPONSE_GET_XML, response.getEntity());
    }

    

}