package fr.insee.rmes.api.geo.territoire;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.rmes.api.AbstractApiTest;
import fr.insee.rmes.api.geo.pseudointegrationtest.ConstantForIntegration;
import fr.insee.rmes.modeles.geo.territoire.*;
import fr.insee.rmes.utils.CSVUtils;
import fr.insee.rmes.utils.ResponseUtils;
import fr.insee.rmes.utils.SparqlUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static fr.insee.rmes.utils.JavaLangUtils.merge;
import static javax.ws.rs.core.MediaType.*;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IrisApiTest extends AbstractApiTest {

    @InjectMocks
    private IrisApi geoApi;

    private final IrisApi geoApiSansMock=new IrisApi();

    private final Iris iris = new Iris();

    private final String codeIrisCorrect="010040101";

    public Stream<Arguments> getWithCodeAndDatefactory() {
        Stream<ConstantForIntegration.GetWithCodeAndDate> gets = Stream.of(geoApiSansMock::getByCode);
        var intermediaire= merge(gets, List.of(APPLICATION_XML, APPLICATION_JSON), Pair::of );
        return merge(intermediaire, Arrays.asList(null, "mauvaiseDate", "1982-07-19"), (pair, d)->Arguments.of(pair.getLeft(), pair.getRight(), d));
    }

    @BeforeEach
    void resetSomeMocks(){
        Mockito.reset(super.mockResponseUtils);
        super.list.clear();
        iris.setUri(null);
    }

     @ParameterizedTest
    @MethodSource("getWithCodeAndDatefactory")
    void givenAllGetsIrisWithCodeAndDate_whenIncorrectCode_thenResponseIsKo(ConstantForIntegration.GetWithCodeAndDate getWithCodeAndDate, String mediaType, String date) {

        // Call method
        String codeIrisIncorrect = "something";
        var response=getWithCodeAndDate.get(codeIrisIncorrect, mediaType, date);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }


    @Test
    void givenGetIris_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getByCode(codeIrisCorrect, APPLICATION_XML, "nimportequoi");
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetResponseXml() throws JAXBException {
        String codeTest = "25002";
        Territoire territoireTest = new Commune(codeTest);
        territoireTest.setCode("codeTerritoire");
        territoireTest.setUri("uriTerritoire");
        territoireTest.setIntitule("intituleTerritoire");
        territoireTest.setType("typeTerritoire");
        territoireTest.setDateCreation("dateCreationTerritoire");
        territoireTest.setDateSuppression("dateSuppressionTerritoire");
        territoireTest.setIntituleSansArticle("intituleSansArticleTerritoire");
        Response response = IrisApi.getResponseXml(codeTest, territoireTest);
        assertNotNull(response);
        assertEquals(200, response.getStatus());
        String contenuXml = response.getEntity().toString();
        assertNotNull(contenuXml);
    }

    @Test
    public void testGetResponseJson() throws Exception {
        String codeTest = "25002";
        Territoire territoireTest = new Commune(codeTest);
        territoireTest.setCode("codeTerritoire");
        territoireTest.setUri("uriTerritoire");
        territoireTest.setIntitule("intituleTerritoire");
        territoireTest.setType("typeTerritoire");
        territoireTest.setDateCreation("dateCreationTerritoire");
        territoireTest.setDateSuppression("dateSuppressionTerritoire");
        territoireTest.setIntituleSansArticle("intituleSansArticleTerritoire");
        Response response = IrisApi.getResponseJson(codeTest, territoireTest);
        assertNotNull(response);
        assertEquals(200, response.getStatus());
        String contenuJson = response.getEntity().toString();
        assertNotNull(contenuJson);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(contenuJson);
        assertNotNull(rootNode);
        assertEquals(codeTest, rootNode.get("code").asText());
        assertEquals("uriTerritoire", rootNode.get("uri").asText());
    }

    @ParameterizedTest
    @ValueSource(strings = {MediaType.APPLICATION_JSON, APPLICATION_XML})
    void givenGetListeIris_whenCorrectRequest_thenResponseIsOk(String mediaType) {

        // Mock
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Iris());

        // Call method
        geoApi.getListe(mediaType, null,true);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetListeIris_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);
        list.clear();

        // Call method header content = xml
        Response response = geoApi.getListe(APPLICATION_XML, null,true);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getListe(MediaType.APPLICATION_JSON, null,true);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @ParameterizedTest
    @ValueSource(strings = {"2000-01-01", "*"})
    @NullSource
        //default = current day
    void givenGetListeIris_WhenCorrectRequest_thenParameterDateIsRight(String date) {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Iris());

        // Call method header content = xml
        geoApi.getListe(APPLICATION_XML, date,true);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetListeIris_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getListe(APPLICATION_XML, "nimportequoi",true);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void givenGetListIris_WhenHeaderIsNotAcceptable() {
        // Mock methods
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populateMultiPOJO(Mockito.anyString(), Mockito.any())).thenReturn(list);
        list.add(new Iris());

        // Call method header content = text plain
        Response response = geoApi.getListe(MediaType.TEXT_PLAIN, null,true);
        assertEquals(Response.Status.NOT_ACCEPTABLE.getStatusCode(), response.getStatus());
    }

    @ParameterizedTest
    @CsvSource({
            "'"+MediaType.APPLICATION_JSON+"', ,",
            "'"+APPLICATION_XML+"', ,",
            "'"+APPLICATION_XML+"',,'Iris'",
            "'"+APPLICATION_XML+"','2000-01-01',"
    })
    void givenGetIrisAscendants_whenCorrectRequest_thenResponseIsOk(String mediaType, String date, String typeTerritoire) {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Canton());

        // Call method
        geoApi.getAscendants(codeIrisCorrect, mediaType, date, typeTerritoire);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetIrisAscendants_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);
        list.clear();

        // Call method header content = xml
        Response response = geoApi.getAscendants(codeIrisCorrect, MediaType.APPLICATION_JSON, null, null);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getAscendants(codeIrisCorrect, APPLICATION_XML, null, null);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }


    @ParameterizedTest
    @CsvSource({
            "'"+APPLICATION_XML+"', 'nimportequoi',",
            "'"+APPLICATION_XML+"',,'unTypeQuelconque'",
    })
    void givenGetIrisAscendants_WhenCorrectRequest_WithBadParameter(String mediaType, String date, String typeTerritoire) {

        // Call method header content = xml
        Response response = geoApi.getAscendants(codeIrisCorrect,mediaType, date, typeTerritoire);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
    public Stream<Arguments> callsWithBadParameters() {
        return Stream.of(
                Arguments.of("getListeWithBadDate", (Supplier<Response>) () -> geoApi.getListe(APPLICATION_XML, "nimportequoi",null)),
                Arguments.of("getAscendantsWithBadDate", (Supplier<Response>) () -> geoApi.getAscendants(codeIrisCorrect, APPLICATION_XML, "nimportequoi", null)),
                Arguments.of("getAscendantsWithBadTerritory", (Supplier<Response>) () -> geoApi.getAscendants(codeIrisCorrect, APPLICATION_XML, null, "unTypeQuelconque"))
        );
    }

    public Stream<Arguments> callsWithBadHeaders() {
        return Stream.of(
                Arguments.of("getListeWithBadHeader", (Supplier<Response>) () -> geoApi.getListe(TEXT_PLAIN, null,true)),
                Arguments.of("getAscendantsWithBadHeader", (Supplier<Response>) () -> geoApi.getAscendants(codeIrisCorrect, TEXT_PLAIN, null, null))
        );
    }

    public Stream<Arguments> callsWithCorrectRequest() {
        return Stream.of(
                Arguments.of("getListe_JSON_noDate", (Supplier<Response>) () -> geoApi.getListe(APPLICATION_JSON, null,true)),
                Arguments.of("getListe_XML_noDate", (Supplier<Response>) () -> geoApi.getListe(APPLICATION_XML, null,true)),
                Arguments.of("getListe_XML_date", (Supplier<Response>) () -> geoApi.getListe(APPLICATION_XML, "2000-01-01",true)),
                Arguments.of("getListe_dateStar", (Supplier<Response>) () -> geoApi.getListe(APPLICATION_JSON, "*",true))
        );
    }
}
