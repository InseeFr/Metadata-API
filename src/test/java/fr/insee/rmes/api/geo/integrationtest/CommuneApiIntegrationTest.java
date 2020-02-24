package fr.insee.rmes.api.geo.integrationtest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.insee.rmes.api.geo.territoire.CommuneApi;
import fr.insee.rmes.utils.SparqlUtils;

public class CommuneApiIntegrationTest {

    @InjectMocks
    private CommuneApi geoApi;

    @Mock
    protected SparqlUtils mockSparqlUtils;

    private final static String MOCK_SERVER_RETURN_GET =
        "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
            + "http://id.insee.fr/geo/commune/2217cf41-787a-4d8a-8c61-abd5683c5b8a,01002,5,L'Abergement-de-Varey,Abergement-de-Varey,1943-01-01,,\r\n"
            + "";
    private final static String EXPECTED_RESPONSE_GET_JSON =
        "{\"code\":\"01002\",\"uri\":\"http://id.insee.fr/geo/commune/2217cf41-787a-4d8a-8c61-abd5683c5b8a\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Abergement-de-Varey\",\"typeArticle\":\"5\",\"intitule\":\"L'Abergement-de-Varey\"}";

    private final static String EXPECTED_RESPONSE_GET_XML =
        "<Commune code=\"01002\" uri=\"http://id.insee.fr/geo/commune/2217cf41-787a-4d8a-8c61-abd5683c5b8a\">"
            + "<Intitule>L'Abergement-de-Varey</Intitule>"
            + "<Type>Commune</Type>"
            + "<DateCreation>1943-01-01</DateCreation>"
            + "<IntituleSansArticle typeArticle=\"5\">Abergement-de-Varey</IntituleSansArticle>"
            + "</Commune>";

    private static final String MOCK_SERVER_RETURN_LISTE =
        "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
            + "http://id.insee.fr/geo/commune/0ba291b3-c220-45e2-b2f9-a666789623f8,01001,5,L'Abergement-Clémenciat,Abergement-Clémenciat,1943-01-01,,\r\n"
            + "http://id.insee.fr/geo/commune/2217cf41-787a-4d8a-8c61-abd5683c5b8a,01002,5,L'Abergement-de-Varey,Abergement-de-Varey,1943-01-01,,\r\n"
            + "http://id.insee.fr/geo/commune/00b46592-a710-486c-9a4c-bdf964c90c82,01004,1,Ambérieu-en-Bugey,Ambérieu-en-Bugey,1955-03-31,,\r\n"
            + "http://id.insee.fr/geo/commune/3d68f1b7-513d-4a52-aa9b-ba3b93bd02c4,01005,1,Ambérieux-en-Dombes,Ambérieux-en-Dombes,1943-01-01,,\r\n"
            + "";

    private static final String EXPECTED_RESPONSE_LISTE_TOP_JSON =
        "[{\"code\":\"01001\",\"uri\":\"http://id.insee.fr/geo/commune/0ba291b3-c220-45e2-b2f9-a666789623f8\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Abergement-Clémenciat\",\"typeArticle\":\"5\",\"intitule\":\"L'Abergement-Clémenciat\"},{\"code\":\"01002\",\"uri\":\"http://id.insee.fr/geo/commune/2217cf41-787a-4d8a-8c61-abd5683c5b8a\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Abergement-de-Varey\",\"typeArticle\":\"5\",\"intitule\":\"L'Abergement-de-Varey\"},{\"code\":\"01004\",\"uri\":\"http://id.insee.fr/geo/commune/00b46592-a710-486c-9a4c-bdf964c90c82\",\"type\":\"Commune\",\"dateCreation\":\"1955-03-31\",\"intituleSansArticle\":\"Ambérieu-en-Bugey\",\"typeArticle\":\"1\",\"intitule\":\"Ambérieu-en-Bugey\"},{\"code\":\"01005\",\"uri\":\"http://id.insee.fr/geo/commune/3d68f1b7-513d-4a52-aa9b-ba3b93bd02c4\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Ambérieux-en-Dombes\",\"typeArticle\":\"1\",\"intitule\":\"Ambérieux-en-Dombes\"}]";

    private static final String EXPECTED_RESPONSE_LISTE_TOP_XML = null;

    private static final String MOCK_SERVER_RETURN_ASCENDANTS =
        "uri,code,type,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\\r\\nhttp://id.insee.fr/geo/arrondissement/d4677635-ed01-444c-9dfe-c9e7b70ad515,011,http://rdf.insee.fr/def/geo#Arrondissement,0,Belley,Belley,2017-01-01,,01034\\r\\nhttp://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc,01,http://rdf.insee.fr/def/geo#Departement,5,Ain,Ain,1943-01-01,,01053\\r\\nhttp://id.insee.fr/geo/region/6db44a21-5ae3-41b5-b16d-0ab30964f78d,84,http://rdf.insee.fr/def/geo#Region,1,Auvergne-Rhône-Alpes,Auvergne-Rhône-Alpes,2016-01-01,,69123\\r\\n";

    private static final Object EXPECTED_RESPONSE_ASCENDANTS_JSON =
        "[\r\n"
            + "  {\r\n"
            + "    \"code\": \"011\",\r\n"
            + "    \"uri\": \"http://id.insee.fr/geo/arrondissement/d4677635-ed01-444c-9dfe-c9e7b70ad515\",\r\n"
            + "    \"type\": \"Arrondissement\",\r\n"
            + "    \"dateCreation\": \"2017-01-01\",\r\n"
            + "    \"intituleSansArticle\": \"Belley\",\r\n"
            + "    \"typeArticle\": \"0\",\r\n"
            + "    \"chefLieu\": \"01034\",\r\n"
            + "    \"intitule\": \"Belley\"\r\n"
            + "  },\r\n"
            + "  {\r\n"
            + "    \"code\": \"01\",\r\n"
            + "    \"uri\": \"http://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc\",\r\n"
            + "    \"type\": \"Departement\",\r\n"
            + "    \"dateCreation\": \"1943-01-01\",\r\n"
            + "    \"intituleSansArticle\": \"Ain\",\r\n"
            + "    \"typeArticle\": \"5\",\r\n"
            + "    \"chefLieu\": \"01053\",\r\n"
            + "    \"intitule\": \"Ain\"\r\n"
            + "  },\r\n"
            + "  {\r\n"
            + "    \"code\": \"84\",\r\n"
            + "    \"uri\": \"http://id.insee.fr/geo/region/6db44a21-5ae3-41b5-b16d-0ab30964f78d\",\r\n"
            + "    \"type\": \"Region\",\r\n"
            + "    \"dateCreation\": \"2016-01-01\",\r\n"
            + "    \"intituleSansArticle\": \"Auvergne-Rhône-Alpes\",\r\n"
            + "    \"typeArticle\": \"1\",\r\n"
            + "    \"chefLieu\": \"69123\",\r\n"
            + "    \"intitule\": \"Auvergne-Rhône-Alpes\"\r\n"
            + "  }\r\n"
            + "]";

    private static final Object EXPECTED_RESPONSE_ASCENDANTS_XML = null;

    private static final String MOCK_SERVER_RETURN_DESCENDANTS = null;

    private static final Object EXPECTED_RESPONSE_DESCENDANTS_JSON = null;

    private static final Object EXPECTED_RESPONSE_DESCENDANTS_XML = null;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givengetCommune_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString())).thenReturn(MOCK_SERVER_RETURN_GET);
        Response response = geoApi.getCommune("01002", MediaType.APPLICATION_JSON, null);
        assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals(EXPECTED_RESPONSE_GET_JSON, response.getEntity());
    }

    @Test
    public void givengetCommune_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString())).thenReturn(MOCK_SERVER_RETURN_GET);
        Response response = geoApi.getCommune("01002", MediaType.APPLICATION_XML, null);
        assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals(EXPECTED_RESPONSE_GET_XML, response.getEntity());
    }

    @Test
    public void givengetListeCommunes_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
        when(mockSparqlUtils.executeSparqlQuery(anyString())).thenReturn(MOCK_SERVER_RETURN_LISTE);
        Response response = geoApi.getListeCommunes(MediaType.APPLICATION_JSON, null);
        assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals(EXPECTED_RESPONSE_LISTE_TOP_JSON, response.getEntity());
    }

    // @Test
    // public void givengetListeCommunes_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
    // when(mockSparqlUtils.executeSparqlQuery(anyString())).thenReturn(MOCK_SERVER_RETURN_LISTE);
    // Response response = geoApi.getListeCommunes(MediaType.APPLICATION_XML, null);
    // assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
    // assertEquals(EXPECTED_RESPONSE_LISTE_TOP_XML, response.getEntity());
    // }
    //
    // @Test
    // public void givengetAscendantsCommunes_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
    // when(mockSparqlUtils.executeSparqlQuery(anyString())).thenReturn(MOCK_SERVER_RETURN_ASCENDANTS);
    // Response response = geoApi.getAscendantsFromCommune("01002", MediaType.APPLICATION_JSON, null, null);
    // assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
    // assertEquals(EXPECTED_RESPONSE_ASCENDANTS_JSON, response.getEntity());
    // }
    //
    // @Test
    // public void givengetAscendantsCommunes_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
    // when(mockSparqlUtils.executeSparqlQuery(anyString())).thenReturn(MOCK_SERVER_RETURN_ASCENDANTS);
    // Response response = geoApi.getAscendantsFromCommune("01002", MediaType.APPLICATION_XML, null, null);
    // assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
    // assertEquals(EXPECTED_RESPONSE_ASCENDANTS_XML, response.getEntity());
    // }
    //
    // @Test
    // public void givengetDescendantsCommunes_whenCorrectRequest_With_JSON_Header_thenResponseIsOk() {
    // when(mockSparqlUtils.executeSparqlQuery(anyString())).thenReturn(MOCK_SERVER_RETURN_DESCENDANTS);
    // Response response = geoApi.getDescendantsFromCommune("01015", MediaType.APPLICATION_JSON, null, null);
    // assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
    // assertEquals(EXPECTED_RESPONSE_DESCENDANTS_JSON, response.getEntity());
    // }
    //
    // @Test
    // public void givengetDescendantsCommunes_whenCorrectRequest_With_XML_Header_thenResponseIsOk() {
    // when(mockSparqlUtils.executeSparqlQuery(anyString())).thenReturn(MOCK_SERVER_RETURN_DESCENDANTS);
    // Response response = geoApi.getDescendantsFromCommune("01015", MediaType.APPLICATION_XML, null, null);
    // assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
    // assertEquals(EXPECTED_RESPONSE_DESCENDANTS_XML, response.getEntity());
    // }

}
