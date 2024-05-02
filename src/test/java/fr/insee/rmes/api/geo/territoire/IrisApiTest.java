package fr.insee.rmes.api.geo.territoire;

import fr.insee.rmes.api.AbstractApiTest;
import fr.insee.rmes.api.geo.pseudointegrationtest.ConstantForIntegration;
import fr.insee.rmes.modeles.geo.territoire.Canton;
import fr.insee.rmes.modeles.geo.territoire.CodeIris;
import fr.insee.rmes.modeles.geo.territoire.Iris;
import fr.insee.rmes.utils.CSVUtils;
import fr.insee.rmes.utils.IrisUtils;
import fr.insee.rmes.utils.ResponseUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static fr.insee.rmes.api.geo.pseudointegrationtest.ConstantForIntegration.assertEqualsJson;
import static fr.insee.rmes.api.geo.pseudointegrationtest.ConstantForIntegration.assertEqualsXml;
import static fr.insee.rmes.utils.JavaLangUtils.merge;
import static jakarta.ws.rs.core.MediaType.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IrisApiTest extends AbstractApiTest {

    private static final String PSEUDOIRIS_EXPECTED_XML = "\t<Commune code=\"250020000\" uri=\"http://id.insee.fr/geo/commune/d22b67f9-76c1-44fb-99d4-bdbda1e1ec3f\">\n" +
            "\t\t<Intitule>Abbans-Dessus</Intitule>\n" +
            "\t\t<Type>Commune</Type>\n" +
            "\t\t<DateCreation>1943-01-01</DateCreation>\n" +
            "\t\t<IntituleSansArticle typeArticle=\"1\">Abbans-Dessus</IntituleSansArticle>\n" +
            "\t</Commune>\n";
    private static final String PSEUDOIRIS_CSV = "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu,categorieJuridique,intituleComplet\n" +
            "http://id.insee.fr/geo/commune/d22b67f9-76c1-44fb-99d4-bdbda1e1ec3f,25002,1,Abbans-Dessus,Abbans-Dessus,1943-01-01,,,,";
    private static final String PSEUDOIRIS_EXPECTED_JSON = "{\n" +
            "  \"code\": \"250020000\",\n" +
            "  \"uri\": \"http://id.insee.fr/geo/commune/d22b67f9-76c1-44fb-99d4-bdbda1e1ec3f\",\n" +
            "  \"type\": \"Commune\",\n" +
            "  \"dateCreation\": \"1943-01-01\",\n" +
            "  \"intituleSansArticle\": \"Abbans-Dessus\",\n" +
            "  \"typeArticle\": \"1\",\n" +
            "  \"intitule\": \"Abbans-Dessus\"\n" +
            "}";

    private static final String IRIS_CSV = "uri,type,code,intituleSansArticle,intitule,typeDIris,dateCreation,dateSuppression,typeArticle\n" +
            "http://id.insee.fr/geo/iris/3adf17ae-ac6a-424f-9b08-dea51c72ee8c,Iris,693870103,Centre Berthelot,Centre Berthelot,H,2008-01-01,,X";
    private static final String IRIS_EXPECTED_XML ="<Iris code=\"693870103\" uri=\"http://id.insee.fr/geo/iris/3adf17ae-ac6a-424f-9b08-dea51c72ee8c\">\n" +
            "  <Intitule>Centre Berthelot</Intitule>\n" +
            "  <Type>Iris</Type>\n" +
            "  <DateCreation>2008-01-01</DateCreation>\n" +
            "  <IntituleSansArticle typeArticle=\"X\">Centre Berthelot</IntituleSansArticle>\n" +
            "  <TypeDIris>H</TypeDIris>\n" +
            "</Iris>";
    private static final String IRIS_EXPECTED_JSON="{\n" +
            "  \"code\": \"693870103\",\n" +
            "  \"uri\": \"http://id.insee.fr/geo/iris/3adf17ae-ac6a-424f-9b08-dea51c72ee8c\",\n" +
            "  \"type\": \"Iris\",\n" +
            "  \"dateCreation\": \"2008-01-01\",\n" +
            "  \"typeDIris\": \"H\",\n" +
            "  \"intitule\": \"Centre Berthelot\",\n" +
            "  \"intituleSansArticle\": \"Centre Berthelot\",\n" +
            "  \"typeArticle\": \"X\"\n" +
            "}";
    @InjectMocks
    private IrisApi geoApi;

    @Mock
    private IrisUtils irisUtils;

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
    public void given_CorrectPseudoIrisCode_thenReturnCommune_XML() {
        //given
        String inputCode = "250020000";
        String header= APPLICATION_XML;
        when(irisUtils.hasIrisDescendant(inputCode.substring(0,5))).thenReturn(false);
        when(mockSparqlUtils.executeSparqlQuery(anyString())).thenReturn(PSEUDOIRIS_CSV);
        IrisApi irisApi=new IrisApi(mockSparqlUtils, new CSVUtils(), new ResponseUtils(), irisUtils);
        //when
        var response=irisApi.getByCode(inputCode, header, null);
        //then
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEqualsXml(PSEUDOIRIS_EXPECTED_XML, response.getEntity());
    }

    @Test
    public void given_CorrectPseudoIrisCode_thenReturnCommune_Json() {
        //given
        String inputCode = "250020000";
        String header= APPLICATION_JSON;
        when(irisUtils.hasIrisDescendant(inputCode.substring(0,5))).thenReturn(false);
        when(mockSparqlUtils.executeSparqlQuery(anyString())).thenReturn(PSEUDOIRIS_CSV);
        IrisApi irisApi=new IrisApi(mockSparqlUtils, new CSVUtils(), new ResponseUtils(), irisUtils);
        //when
        var response=irisApi.getByCode(inputCode, header, null);
        //then
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEqualsJson(PSEUDOIRIS_EXPECTED_JSON, response.getEntity());
    }

    @Test
    public void given_CorrectIrisCode_thenReturnCommune_XML() {
        //given
        String inputCode = "693870103";
        String header= APPLICATION_XML;
        when(irisUtils.hasIrisDescendant(inputCode.substring(0,5))).thenReturn(true);
        when(mockSparqlUtils.executeSparqlQuery(anyString())).thenReturn(IRIS_CSV);
        IrisApi irisApi=new IrisApi(mockSparqlUtils, new CSVUtils(), new ResponseUtils(), irisUtils);
        //when
        var response=irisApi.getByCode(inputCode, header, null);
        //then
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEqualsXml(IRIS_EXPECTED_XML, response.getEntity());
    }

    @Test
    public void given_CorrectIrisCode_thenReturnCommune_Json() {
        //given
        String inputCode = "693870103";
        String header= APPLICATION_JSON;
        when(irisUtils.hasIrisDescendant(inputCode.substring(0,5))).thenReturn(true);
        when(mockSparqlUtils.executeSparqlQuery(anyString())).thenReturn(IRIS_CSV);
        IrisApi irisApi=new IrisApi(mockSparqlUtils, new CSVUtils(), new ResponseUtils(), irisUtils);
        //when
        var response=irisApi.getByCode(inputCode, header, null);
        //then
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEqualsJson(IRIS_EXPECTED_JSON, response.getEntity());
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

    @ParameterizedTest
    @ValueSource(strings = {"123456789", "2A1110000", "2A1110001", "2B1110001"})
    void test_CodeIris_validPattern(String code){
        //given param code
        //when
        var codeIris= CodeIris.of(code);
        //then
        assertFalse(codeIris.isInvalid());
        assertDoesNotThrow(codeIris::code);
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345A789", "2a1110000", "xxxxxxxxx", "2B111000", "2B111000199"})
    void test_CodeIris_invalidPattern(String code){
        //given param code
        //when
        var codeIris= CodeIris.of(code);
        //then
        assertTrue(codeIris.isInvalid());
        assertThrows(IllegalStateException.class,codeIris::code);
    }
}
