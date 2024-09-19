package fr.insee.rmes.api.geo.pseudointegrationtest;

import fr.insee.rmes.api.geo.territoire.CantonAPI;
import fr.insee.rmes.modeles.utils.Date;
import fr.insee.rmes.utils.SparqlUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.util.stream.Stream;

import static fr.insee.rmes.api.geo.pseudointegrationtest.ConstantForIntegration.*;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.MediaType.APPLICATION_XML;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CantonApiIntegrationTest {

    private static final String NO_DATE = null;
    @InjectMocks
    private CantonAPI geoApi;
    private final static String CODE = "5923";

    private static final String DATE1991 = "1991-12-19";
    private static final String DATE2000 = "2000-02-27";

    private static final String INCORRECT_DATE="*";

    @Mock
    protected SparqlUtils mockSparqlUtils;

    @Test
    void givengetCanton_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString()))
                .thenReturn(ConstantForIntegration.CANTON_MOCK_SERVER_RETURN_GET);
        Response response = geoApi.getByCode(CODE, APPLICATION_JSON, null);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertEqualsJson(ConstantForIntegration.CANTON_EXPECTED_RESPONSE_GET_JSON, response.getEntity());
    }

    @ParameterizedTest(name = "{0} - {2} : {4}")
    @MethodSource("argumentProvider")
    void test_getCommunes(String titre, String sparqlResult, String media, Date date, Status status, String expected) {
        lenient().when(mockSparqlUtils.executeSparqlQuery(anyString())).thenReturn(sparqlResult);
        Response response = geoApi.getCommunes(CODE, media, date);
        Assertions.assertEquals(status.getStatusCode(), response.getStatus());
        if (Status.OK==status){
            switch (media){
                case APPLICATION_XML:
                    assertEqualsXml(expected, response.getEntity() );
                    break;
                case APPLICATION_JSON:
                    assertEqualsJson(expected, response.getEntity());
                    break;
            }
        }
    }

    static Stream<Arguments> argumentProvider() {
        return Stream.of(
                //TODO fix sparql request pb and enable this test
                //arguments("Communes de Lille-1 au "+DATE1991,COMMUNES_INCLUSES_DATEES1991_MOCK_SPARQL_RESPONSE, APPLICATION_JSON, DATE1991, Status.OK, COMMUNES_INCLUSES_DATEES1991_RESPONSE_GET_JSON),
                //TODO fix sparql request pb and enable this test
                //arguments("Communes de Lille-1 au "+DATE2000,COMMUNES_INCLUSES_DATEES2000_MOCK_SPARQL_RESPONSE, APPLICATION_JSON, DATE2000, Status.OK, COMMUNES_INCLUSES_DATEES2000_RESPONSE_GET_JSON),
                arguments("Mauvaise date", COMMUNES_INCLUSES_MOCK_SPARQL_RESPONSE, APPLICATION_JSON, INCORRECT_DATE, Status.BAD_REQUEST, null),
                arguments("Mauvaise date", COMMUNES_INCLUSES_MOCK_SPARQL_RESPONSE, APPLICATION_XML, INCORRECT_DATE, Status.BAD_REQUEST, null),
                //TODO fix order of output xml : actual is "Intitule, Type, DateCreation, IntituleSansArticle, Inclusion" but should be "Type, Intitule, IntituleSansArticle, Inclusion, DateCreation"
                //arguments("Communes de Lille-1 ", COMMUNES_INCLUSES_MOCK_SPARQL_RESPONSE, APPLICATION_XML, NO_DATE, Status.OK, COMMUNES_INCLUSES_RESPONSE_GET_XML),
                arguments("Communes de Lille-1 ", COMMUNES_INCLUSES_MOCK_SPARQL_RESPONSE, APPLICATION_JSON, NO_DATE, Status.OK, COMMUNES_INCLUSES_EXPECTED_RESPONSE_GET_JSON)
        );
    }

}