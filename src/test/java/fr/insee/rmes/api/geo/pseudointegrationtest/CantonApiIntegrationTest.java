package fr.insee.rmes.api.geo.pseudointegrationtest;

import fr.insee.rmes.api.geo.territoire.CantonAPI;
import fr.insee.rmes.utils.SparqlUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CantonApiIntegrationTest {



    @InjectMocks
    private CantonAPI geoApi;
    private final static String CODE = "0105";
    private final static String CODE_ASCENDANTS = "0105";
    private final static String CODE_SUIVANT = "0105";

    @Mock
    protected SparqlUtils mockSparqlUtils;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givengetCanton_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
                .thenReturn(ConstantForIntegration.CANTON_MOCK_SERVER_RETURN_GET);
        Response response = geoApi.getByCode(CODE, MediaType.APPLICATION_JSON, null);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.CANTON_EXPECTED_RESPONSE_GET_JSON, response.getEntity());
    }

    @Test
    public void givengetCanton_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
                .thenReturn(ConstantForIntegration.CANTON_MOCK_SERVER_RETURN_GET);
        Response response = geoApi.getByCode(CODE, MediaType.APPLICATION_XML, null);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ConstantForIntegration.CANTON_EXPECTED_RESPONSE_GET_XML, response.getEntity());
    }

}