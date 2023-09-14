package fr.insee.rmes.api.geo.pseudointegrationtest;

import fr.insee.rmes.api.geo.territoire.CantonAPI;
import fr.insee.rmes.utils.SparqlUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.stream.Stream;

import static fr.insee.rmes.api.geo.pseudointegrationtest.ConstantForIntegration.*;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CantonApiIntegrationTest {

    private static final String DATE1991 = "1991-12-19";
    private static final String DATE2000 = "2000-02-27";
    @InjectMocks
    private CantonAPI geoApi;
    private final static String CODE = "3319";

    private static final String INCORRECT_DATE="*";

    @Mock
    protected SparqlUtils mockSparqlUtils;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @ParameterizedTest(name = "{0} - {2} : {4}")
    @MethodSource("argumentProvider")
    void test_getCommunes(String titre, String sparqlResult,String media, String date, Status status, String expected) {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
                .thenReturn(sparqlResult);
        Response response = geoApi.getCommunes(CODE, media, date);
        Assertions.assertEquals(status.getStatusCode(), response.getStatus());
        if (Status.OK==status){
            Assertions.assertEquals(expected, response.getEntity());
        }
    }

    static Stream<Arguments> argumentProvider() {
        return Stream.of(
                arguments("Communes de Lille-1 au "+DATE1991,COMMUNES_INCLUSES_DATEES1991_MOCK_SERVER_RETURN_GET, APPLICATION_JSON, DATE1991, Status.OK, COMMUNES_INCLUSES_DATEES1991_RESPONSE_GET_JSON),
                arguments("Communes de Lille-1 au "+DATE2000,COMMUNES_INCLUSES_DATEES2000_MOCK_SERVER_RETURN_GET, APPLICATION_JSON, DATE2000, Status.OK, COMMUNES_INCLUSES_DATEES2000_RESPONSE_GET_JSON),
                arguments("Mauvaise date", COMMUNES_INCLUSES_MOCK_SERVER_RETURN_GET, APPLICATION_JSON, INCORRECT_DATE, Status.BAD_REQUEST, null),
                arguments("Mauvaise date", COMMUNES_INCLUSES_MOCK_SERVER_RETURN_GET, APPLICATION_XML, INCORRECT_DATE, Status.BAD_REQUEST, null),
                arguments("Communes de Merignac-2 ", COMMUNES_INCLUSES_MOCK_SERVER_RETURN_GET, APPLICATION_XML, INCORRECT_DATE, Status.OK, COMMUNES_INCLUSES_RESPONSE_GET_XML),
                arguments("Communes de Merignac-2 ", COMMUNES_INCLUSES_MOCK_SERVER_RETURN_GET, APPLICATION_JSON, INCORRECT_DATE, Status.OK, COMMUNES_INCLUSES_EXPECTED_RESPONSE_GET_JSON)
        );
    }


}
