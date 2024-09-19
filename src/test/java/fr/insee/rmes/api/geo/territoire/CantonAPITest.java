package fr.insee.rmes.api.geo.territoire;

import fr.insee.rmes.api.AbstractApiTest;
import fr.insee.rmes.api.geo.pseudointegrationtest.ConstantForIntegration.GetWithCodeAndDate;
import fr.insee.rmes.modeles.geo.territoire.Canton;
import fr.insee.rmes.modeles.utils.Date;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static fr.insee.rmes.utils.JavaLangUtils.merge;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.MediaType.APPLICATION_XML;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CantonAPITest extends AbstractApiTest {

    @InjectMocks
    private CantonAPI geoApi;

    private final CantonAPI geoApiSansMock=new CantonAPI();

    private final Canton canton = new Canton();

    private final String codeCantonCorrect="1234";

    public Stream<Arguments> getWithCodeAndDateFactory() {
        Stream<GetWithCodeAndDate> gets = Stream.of(geoApiSansMock::getByCode, geoApiSansMock::getSuivant, geoApiSansMock::getCommunes, geoApiSansMock::getPrecedent );
        var intermediare= merge(gets, List.of(APPLICATION_XML, APPLICATION_JSON), Pair::of );
        return merge(intermediare, Arrays.asList(null, new Date("mauvaiseDate"), new Date("1982-07-19")), (pair, d)->Arguments.of(pair.getLeft(), pair.getRight(), d));
    }
    @ParameterizedTest
    @ValueSource(strings = {APPLICATION_XML, MediaType.APPLICATION_JSON})
    void givenGetCanton_whenCorrectRequestt_thenResponseIsOk(String mediaType) {

        // Mock methods
        canton.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(canton, Boolean.TRUE);

        // Call method
        geoApi.getByCode(codeCantonCorrect, mediaType, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @ParameterizedTest
    @MethodSource("getWithCodeAndDateFactory")
    void givenAllGetsWithCodeAndDate_whenIncorrectCode_thenResponseIsKo(GetWithCodeAndDate getWithCodeAndDate, String mediaType, Date date) {

        // Call method
        String codeCantonIncorrect = "something";
        var response=getWithCodeAndDate.get(codeCantonIncorrect, mediaType,date);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCanton_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
        canton.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(canton, Boolean.TRUE);

        // Call method header content = xml
        geoApi.getByCode(codeCantonCorrect, APPLICATION_XML, new Date("2010-01-01"));
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCanton_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getByCode(codeCantonCorrect, APPLICATION_XML, new Date("nimportequoi"));
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }



    @ParameterizedTest
    @ValueSource(strings = {MediaType.APPLICATION_JSON, APPLICATION_XML})
    void givenGetListeCanton_whenCorrectRequest_thenResponseIsOk(String mediaType) {

        // Mock
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Canton());

        // Call method
        geoApi.getListe(mediaType, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetListeCanton_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);
        list.clear();

        // Call method header content = xml
        Response response = geoApi.getListe(APPLICATION_XML, null);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getListe(MediaType.APPLICATION_JSON, null);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @ParameterizedTest
    @ValueSource(strings = {"2000-01-01", "*"})
    @NullSource
    void givenGetListeCanton_WhenCorrectRequest_thenParameterDateIsRight(String date) {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Canton());

        // Call method header content = xml
        geoApi.getListe(APPLICATION_XML, new Date(date));
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetListeCanton_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getListe(APPLICATION_XML, new Date("nimportequoi"));
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void givenGetListCanton_WhenHeaderIsNotAcceptable() {
        // Mock methods
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populateMultiPOJO(Mockito.anyString(), Mockito.any())).thenReturn(list);
        list.add(new Canton());

        // Call method header content = text plain
        Response response = geoApi.getListe(MediaType.TEXT_PLAIN, null);
        assertEquals(Response.Status.NOT_ACCEPTABLE.getStatusCode(), response.getStatus());
    }


    @ParameterizedTest
    @ValueSource(strings = {MediaType.APPLICATION_JSON, APPLICATION_XML})
    void givenGetcantonSuivant_whenCorrectRequest_thenResponseIsOk(String mediaType) {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Canton());

        // Call method
        geoApi.getSuivant(codeCantonCorrect, mediaType, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonSuivant_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);
        list.clear();

        // Call method header content = xml
        Response response = geoApi.getSuivant(codeCantonCorrect, MediaType.APPLICATION_JSON, null);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getSuivant(codeCantonCorrect, APPLICATION_XML, null);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetcantonSuivant_whenCorrectRequestWithDate_thenResponseIsOk() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Canton());

        // Call method header content = xml
        geoApi.getSuivant(codeCantonCorrect, APPLICATION_XML, new Date("2010-01-01"));
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonSuivant_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getSuivant(codeCantonCorrect, APPLICATION_XML, new Date("nimportequoi"));
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }


    @ParameterizedTest
    @ValueSource(strings = {MediaType.APPLICATION_JSON, APPLICATION_XML})
    void givenGetCantonPrecedent_whenCorrectRequest_thenResponseIsOk(String mediaType) {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Canton());

        // Call method
        geoApi.getPrecedent(codeCantonCorrect, mediaType, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }


    @Test
    void givenGetCantonPrecedent_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);
        list.clear();

        // Call method header content = xml
        Response response = geoApi.getPrecedent(codeCantonCorrect, MediaType.APPLICATION_JSON, null);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getPrecedent(codeCantonCorrect, APPLICATION_XML, null);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonPrecedent_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Canton());

        // Call method header content = xml
        geoApi.getPrecedent(codeCantonCorrect, APPLICATION_XML, new Date("2010-01-01"));
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonPrecedent_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getPrecedent(codeCantonCorrect, APPLICATION_XML, new Date("nimportequoi"));
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @ParameterizedTest
    @ValueSource(strings = {MediaType.APPLICATION_JSON, APPLICATION_XML})
    void givenGetCantonProjetes_whenCorrectRequest_thenResponseIsOk(String mediaType) {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Canton());

        // Call method
        geoApi.getProjection(codeCantonCorrect, mediaType, null, new Date("2010-01-01"));
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }


    @Test
    void givenGetCantonProjetes_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);
        list.clear();

        // Call method header content = xml
        Response response = geoApi.getProjection(codeCantonCorrect, MediaType.APPLICATION_JSON, null, new Date("2010-01-01"));
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getProjection(codeCantonCorrect, APPLICATION_XML, null, new Date("2010-01-01"));
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonProjetes_WhenCorrectRequest_thenParameterDateIsRight() {
        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Canton());

        // Call method header content = xml
        geoApi.getProjection(codeCantonCorrect, APPLICATION_XML, new Date("2010-01-01"), new Date("2018-01-01"));
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @ParameterizedTest
    @CsvSource({
            "'nimportequoi', '2019-01-01'",
            "'nimportequoi', 'nimportequoi'",
            "'nimportequoi', "

    })
    void givenGetCantonProjetes_WhenCorrectRequest_thenParameterDateIsBad(String date, String dateProjection) {

        // Call method header content = xml
        Response response = geoApi.getProjection(codeCantonCorrect, APPLICATION_XML, new Date(date), new Date(dateProjection));
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void givenGetCantonProjetes_WhenCorrectRequest_thenParameterDateProjeteIsRight() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Canton());

        // Call method header content = xml
        geoApi.getProjection(codeCantonCorrect, APPLICATION_XML, new Date("2010-01-01"), new Date("2020-01-01"));
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }


    @ParameterizedTest
    @CsvSource({
            "'"+MediaType.APPLICATION_JSON+"', ,",
            "'"+APPLICATION_XML+"', ,",
            "'"+APPLICATION_XML+"',,'Canton'",
            "'"+APPLICATION_XML+"','2000-01-01',"
    })
    void givenGetCantonAscendants_whenCorrectRequest_thenResponseIsOk(String mediaType, String date, String typeTerritoire) {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Canton());

        // Call method
        geoApi.getAscendants(codeCantonCorrect, mediaType, new Date(date), typeTerritoire);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetCantonAscendants_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);
        list.clear();

        // Call method header content = xml
        Response response = geoApi.getAscendants(codeCantonCorrect, MediaType.APPLICATION_JSON, null, null);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getAscendants(codeCantonCorrect, APPLICATION_XML, null, null);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }


    @ParameterizedTest
    @CsvSource({
            "'"+APPLICATION_XML+"', 'nimportequoi',",
            "'"+APPLICATION_XML+"',,'unTypeQuelconque'",
    })
    void givenGetCantonAscendants_WhenCorrectRequest_WithBadParameter(String mediaType, String date, String typeTerritoire) {

        // Call method header content = xml
        Response response = geoApi.getAscendants(codeCantonCorrect,mediaType, new Date(date), typeTerritoire);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }


    @ParameterizedTest
    @CsvSource({
            "'"+APPLICATION_XML+"','2000-01-01'",
            "'"+APPLICATION_JSON+"',",
            "'"+ APPLICATION_XML+"',"
    })
    void givenGetCommunes_WhenCorrectRequest(String mediaType, String date) {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Canton());

        // Call method header content = xml
        geoApi.getCommunes(codeCantonCorrect, mediaType, new Date(date));
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }
    @Test
    void givenGetCommunes_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getCommunes(codeCantonCorrect, APPLICATION_XML, new Date("nimportequoi"));
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }


    @Test
    void givenGetCommunes_WhenCorrectRequest_thenResponseIsNotFound() {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.FALSE);
        list.clear();

        // Call method header content = xml
        Response response = geoApi.getCommunes(codeCantonCorrect, MediaType.APPLICATION_JSON, null);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        // Call method header content = json
        response = geoApi.getCommunes(codeCantonCorrect, APPLICATION_XML, null);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }
}