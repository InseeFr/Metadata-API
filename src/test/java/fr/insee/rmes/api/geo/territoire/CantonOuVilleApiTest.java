package fr.insee.rmes.api.geo.territoire;

import fr.insee.rmes.api.AbstractApiTest;
import fr.insee.rmes.api.geo.pseudointegrationtest.ConstantForIntegration.GetWithCodeAndDate;
import fr.insee.rmes.modeles.geo.territoire.CantonOuVille;
import fr.insee.rmes.modeles.utils.Date;
import fr.insee.rmes.utils.CSVUtils;
import fr.insee.rmes.utils.ResponseUtils;
import fr.insee.rmes.utils.SparqlUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static fr.insee.rmes.utils.JavaLangUtils.merge;
import static javax.ws.rs.core.MediaType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CantonOuVilleApiTest extends AbstractApiTest {


    private final CantonOuVille cantonOuVille = new CantonOuVille();

    private final String codeCantonOuVilleCorrect = "97400";

    private final CantonOuVilleApi geoApi;

    private CantonOuVilleApiTest() {
        super.mockSparqlUtils = Mockito.mock(SparqlUtils.class);
        super.mockCSVUtils = Mockito.mock(CSVUtils.class);
        super.mockResponseUtils = Mockito.mock(ResponseUtils.class);
        geoApi = new CantonOuVilleApi(mockSparqlUtils, mockCSVUtils, mockResponseUtils);
    }

    public Stream<Arguments> getWithCodeAndDatefactory() {
        Stream<GetWithCodeAndDate> gets = Stream.of(geoApi::getByCode, geoApi::getSuivant, geoApi::getPrecedent);
        var intermediare = merge(gets, List.of(APPLICATION_XML, APPLICATION_JSON), Pair::of);
        return merge(intermediare, Arrays.asList(null, new Date("1982-07-19"), new Date("nimportequoi")), (pair, d) -> Arguments.of(pair.getLeft(), pair.getRight(), d));
    }

    public Stream<Arguments> getWithCodeAndDatefactoryWithOnlyGoodDate() {
        Stream<GetWithCodeAndDate> gets = Stream.of(geoApi::getByCode, geoApi::getSuivant, geoApi::getPrecedent);
        var intermediare = merge(gets, List.of(APPLICATION_XML, APPLICATION_JSON), Pair::of);
        return merge(intermediare, Arrays.asList(null, new Date("1982-07-19")), (pair, d) -> Arguments.of(pair.getLeft(), pair.getRight(), d));
    }

    @BeforeEach
    void resetSomeMocks(){
        Mockito.reset(super.mockResponseUtils);
        super.list.clear();
        cantonOuVille.setUri(null);
    }

    @ParameterizedTest
    @MethodSource("getWithCodeAndDatefactory")
    void givenAllGetsWithCodeAndDate_whenIncorrectCode_thenResponseIsKo(GetWithCodeAndDate getWithCodeAndDate, String mediaType, Date date) {
        // Call method
        String codeCantonIncorrect = "something";
        // pour précédent ? list.add(new CantonOuVille());
        var response = getWithCodeAndDate.get(codeCantonIncorrect, mediaType, date);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @ParameterizedTest
    @MethodSource("getWithCodeAndDatefactoryWithOnlyGoodDate")
    void givenAllGetsWithCodeAndDate_whenCorrectRequest_thenResponseIsOk(GetWithCodeAndDate getWithCodeAndDate, String mediaType, Date date) {

        // Mock methods
        cantonOuVille.setUri("something");
        super.list.add(new CantonOuVille());
        // pour précédent ? list.add(new CantonOuVille());
        this.mockUtilsMethodsThenReturnOnePojo(cantonOuVille, Boolean.TRUE);

        var response = getWithCodeAndDate.get(codeCantonOuVilleCorrect, mediaType, date);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }


    @ParameterizedTest
    @MethodSource("getWithCodeAndDatefactoryWithOnlyGoodDate")
    void givenAllGetsWithCodeAndDate_WhenCorrectRequest_thenResponseIsNotFound(GetWithCodeAndDate getWithCodeAndDate, String mediaType, Date date) {

        // Mock methods
        this.mockUtilsMethodsThenReturnOnePojo(cantonOuVille, Boolean.FALSE);

        // Call method header content = xml
        Response response = getWithCodeAndDate.get(codeCantonOuVilleCorrect, mediaType, date);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }



    public Stream<Arguments> getWithCodeAndDatefactoryBadDate() {
        Stream<GetWithCodeAndDate> gets = Stream.of(geoApi::getByCode, geoApi::getSuivant, geoApi::getPrecedent);
        var intermediare = merge(gets, List.of(APPLICATION_XML, APPLICATION_JSON), Pair::of);
        return merge(intermediare, List.of("mauvaiseDate"), (pair, d) -> Arguments.of(pair.getLeft(), pair.getRight(), d));
    }



    @ParameterizedTest
    @MethodSource("getWithCodeAndDatefactoryBadDate")
    void givenAllGetsWithCodeAndDate_WhenCorrectRequest_thenParameterDateIsBad(GetWithCodeAndDate getWithCodeAndDate, String mediaType, Date date) {

        // Call method header content = xml
        Response response = getWithCodeAndDate.get(codeCantonOuVilleCorrect, mediaType, date);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("callsWithCorrectRequest")
    void givenAnyCall_WhenCorrectRequest_thenResponseIsOK(String titre, Supplier<Response> geoApiGet) {

        // Mock
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new CantonOuVille());

        // Call method
        geoApiGet.get();
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());

    }


    @ParameterizedTest(name = "{0}")
    @MethodSource("callsWithCorrectRequest")
    void givenAnyCall_WhenCorrectRequest_thenResponseIsNotFound(String titre, Supplier<Response> geoApiGet) {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);

        // Call method header content = xml
        Response response = geoApiGet.get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("callsWithBadParameters")
    void givenAnyCall_WhenParameterIsBad(String titre, Supplier<Response> geoApiGet) {

        // Call method header content = xml
        Response response = geoApiGet.get();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("callsWithBadHeaders")
    void givenAnyCall_WhenHeaderIsNotAcceptable(String titre, Supplier<Response> geoApiGet) {
        // Mock methods
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populateMultiPOJO(Mockito.anyString(), Mockito.any())).thenReturn(list);
        list.add(new CantonOuVille());

        // Call method header content = text plain
        Response response = geoApiGet.get();
        assertEquals(Response.Status.NOT_ACCEPTABLE.getStatusCode(), response.getStatus());
    }


    public Stream<Arguments> callsWithBadParameters() {
        return Stream.of(
                Arguments.of("getListeWithBadDate", (Supplier<Response>) () -> geoApi.getListe(APPLICATION_XML, new Date("nimportequoi"))),
                Arguments.of("getDescendantsWithBadDate", (Supplier<Response>) () -> geoApi.getDescendants(codeCantonOuVilleCorrect, APPLICATION_XML, new Date("nimportequoi"), null, null)),
                Arguments.of("getDescendantsWithBadTerritory", (Supplier<Response>) () -> geoApi.getDescendants(codeCantonOuVilleCorrect, APPLICATION_XML, null, "unTypeQuelconque", null)),
                Arguments.of("getAscendantsWithBadDate", (Supplier<Response>) () -> geoApi.getAscendants(codeCantonOuVilleCorrect, APPLICATION_XML, new Date("nimportequoi"), null)),
                Arguments.of("getAscendantsWithBadTerritory", (Supplier<Response>) () -> geoApi.getAscendants(codeCantonOuVilleCorrect, APPLICATION_XML, null, "unTypeQuelconque")),
                Arguments.of("getProjectionWithBadDate", (Supplier<Response>) () -> geoApi.getProjection(codeCantonOuVilleCorrect, APPLICATION_XML, new Date("2010-01-01"), new Date("nimportequoi"))),
                Arguments.of("getProjectionWithTwoBadDates", (Supplier<Response>) () -> geoApi.getProjection(codeCantonOuVilleCorrect, APPLICATION_XML, new Date("nimportequoi"), new Date("nimportequoi"))),
                Arguments.of("getDescendantsWithBadDateAndNull", (Supplier<Response>) () -> geoApi.getProjection(codeCantonOuVilleCorrect, APPLICATION_XML, new Date("nimportequoi"), null))
        );
    }

    public Stream<Arguments> callsWithBadHeaders() {
        return Stream.of(
                Arguments.of("getListeWithBadHeader", (Supplier<Response>) () -> geoApi.getListe(TEXT_PLAIN, null)),
                Arguments.of("getDescendantsWithBadHeader", (Supplier<Response>) () -> geoApi.getDescendants(codeCantonOuVilleCorrect, TEXT_PLAIN,  null, null, null)),
                Arguments.of("getAscendantsWithBadHeader", (Supplier<Response>) () -> geoApi.getAscendants(codeCantonOuVilleCorrect, TEXT_PLAIN, null, null)),
                Arguments.of("getProjectionWithBadHeader", (Supplier<Response>) () -> geoApi.getProjection(codeCantonOuVilleCorrect, TEXT_PLAIN, null, new Date("2010-01-01")))
        );
    }

    public Stream<Arguments> callsWithCorrectRequest() {
        return Stream.of(
                Arguments.of("getProjection_JSON_null_date_projection", (Supplier<Response>) () -> geoApi.getProjection(codeCantonOuVilleCorrect, APPLICATION_JSON, null, new Date("2010-01-01"))),
                Arguments.of("getProjection_XML_null_date_projection", (Supplier<Response>) () -> geoApi.getProjection(codeCantonOuVilleCorrect, APPLICATION_XML, null, new Date("2010-01-01"))),
                Arguments.of("getProjection_XML_noDateNull", (Supplier<Response>) () -> geoApi.getProjection(codeCantonOuVilleCorrect, APPLICATION_XML, new Date("2010-01-01"), new Date("2010-01-01"))),
                Arguments.of("getListe_JSON_noDate", (Supplier<Response>) () -> geoApi.getListe(APPLICATION_JSON, null)),
                Arguments.of("getListe_XML_noDate", (Supplier<Response>) () -> geoApi.getListe(APPLICATION_XML, null)),
                Arguments.of("getListe_XML_date", (Supplier<Response>) () -> geoApi.getListe(APPLICATION_XML, new Date("2010-01-01"))),
                Arguments.of("getListe_dateStar", (Supplier<Response>) () -> geoApi.getListe(APPLICATION_JSON, new Date("*"))),
                Arguments.of("getDescendants_JSON", (Supplier<Response>) () -> geoApi.getDescendants(codeCantonOuVilleCorrect, APPLICATION_JSON, null, null, null)),
                Arguments.of("getDescendants_XML", (Supplier<Response>) () -> geoApi.getDescendants(codeCantonOuVilleCorrect, APPLICATION_XML, null, null, null)),
                Arguments.of("getDescendants_XML_date", (Supplier<Response>) () -> geoApi.getDescendants(codeCantonOuVilleCorrect, APPLICATION_XML, new Date("2010-01-01"), null, null)),
                Arguments.of("getDescendants_XML_Territory", (Supplier<Response>) () -> geoApi.getDescendants(codeCantonOuVilleCorrect, APPLICATION_XML, null, "CantonOuVille", null)),
                Arguments.of("getAscendants_JSON", (Supplier<Response>) () -> geoApi.getAscendants(codeCantonOuVilleCorrect, APPLICATION_JSON, null, null)),
                Arguments.of("getAscendants_XML", (Supplier<Response>) () -> geoApi.getAscendants(codeCantonOuVilleCorrect, APPLICATION_XML, null, null)),
                Arguments.of("getAscendants_XML_date", (Supplier<Response>) () -> geoApi.getAscendants(codeCantonOuVilleCorrect, APPLICATION_XML, new Date("2010-01-01"), null)),
                Arguments.of("getAscendants_XML_territory", (Supplier<Response>) () -> geoApi.getAscendants(codeCantonOuVilleCorrect, APPLICATION_XML, null, "CantonOuVille"))
        );
    }
}