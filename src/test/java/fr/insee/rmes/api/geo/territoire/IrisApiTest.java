package fr.insee.rmes.api.geo.territoire;

import fr.insee.rmes.api.AbstractApiTest;
import fr.insee.rmes.api.geo.pseudointegrationtest.ConstantForIntegration;
import fr.insee.rmes.modeles.geo.territoire.Canton;
import fr.insee.rmes.modeles.geo.territoire.Iris;
import org.apache.commons.lang3.tuple.Pair;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static fr.insee.rmes.utils.JavaLangUtils.merge;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        var intermediare= merge(gets, List.of(APPLICATION_XML, APPLICATION_JSON), Pair::of );
        return merge(intermediare, Arrays.asList(null, "mauvaiseDate", "1982-07-19"), (pair, d)->Arguments.of(pair.getLeft(), pair.getRight(), d));
    }

    @ParameterizedTest
    @ValueSource(strings = {APPLICATION_XML, MediaType.APPLICATION_JSON})
    void givenGetIris_whenCorrectRequestt_thenResponseIsOk(String mediaType) {

        // Mock methods
        iris.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(iris, Boolean.TRUE);

        // Call method
        geoApi.getByCode(codeIrisCorrect, mediaType, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @ParameterizedTest
    @MethodSource("getWithCodeAndDatefactory")
    void givenAllGetsWithCodeAndDate_whenIncorrectCode_thenResponseIsKo(ConstantForIntegration.GetWithCodeAndDate getWithCodeAndDate, String mediaType, String date) {

        // Call method
        String codeIrisIncorrect = "something";
        var response=getWithCodeAndDate.get(codeIrisIncorrect, mediaType, date);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetIris_WhenCorrectRequest_thenParameterDateIsRight() {

        // Mock methods
        iris.setUri("something");
        this.mockUtilsMethodsThenReturnOnePojo(iris, Boolean.TRUE);

        // Call method header content = xml
        geoApi.getByCode(codeIrisCorrect, APPLICATION_XML, "2000-01-01");
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetIris_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getByCode(codeIrisCorrect, APPLICATION_XML, "nimportequoi");
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }



    @ParameterizedTest
    @ValueSource(strings = {MediaType.APPLICATION_JSON, APPLICATION_XML})
    void givenGetListeIris_whenCorrectRequest_thenResponseIsOk(String mediaType) {

        // Mock
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Iris());

        // Call method
        geoApi.getListe(mediaType, null);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetListeIris_WhenCorrectRequest_thenResponseIsNotFound() {

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
        //default = current day
    void givenGetListeIris_WhenCorrectRequest_thenParameterDateIsRight(String date) {

        // Mock methods
        this.mockUtilsMethodsThenReturnListOfPojo(Boolean.TRUE);
        list.add(new Iris());

        // Call method header content = xml
        geoApi.getListe(APPLICATION_XML, date);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    void givenGetListeIris_WhenCorrectRequest_thenParameterDateIsBad() {

        // Call method header content = xml
        Response response = geoApi.getListe(APPLICATION_XML, "nimportequoi");
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void givenGetListIris_WhenHeaderIsNotAcceptable() {
        // Mock methods
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populateMultiPOJO(Mockito.anyString(), Mockito.any())).thenReturn(list);
        list.add(new Iris());

        // Call method header content = text plain
        Response response = geoApi.getListe(MediaType.TEXT_PLAIN, null);
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

}
