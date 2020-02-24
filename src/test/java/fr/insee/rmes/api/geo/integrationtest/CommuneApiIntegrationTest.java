package fr.insee.rmes.api.geo.integrationtest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.insee.rmes.api.geo.AbstractGeoApi;
import fr.insee.rmes.config.Configuration;
import fr.insee.rmes.utils.SparqlUtils;

public class CommuneApiIntegrationTest extends JerseyTest {

    public static final String URL_GET_COMMUNE = "metadata-api/geo/commune/01002";

    @Mock
    protected SparqlUtils mockSparqlUtils;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Configuration conf = new Configuration();
        System.out.println("conf chargée " + conf);
    }

    @Override
    protected Application configure() {
        this.forceSet(TestProperties.CONTAINER_PORT, "0");
        MockitoAnnotations.initMocks(this);
        return new ResourceConfig(AbstractGeoApi.class);
    }

    @Ignore
    @Test
    public void givengetCommune_whenCorrectRequest_WithoutHeader_thenResponseIsOk() {

        when(mockSparqlUtils.executeSparqlQuery(anyString()))
            .thenReturn(
                "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/commune/2217cf41-787a-4d8a-8c61-abd5683c5b8a,01002,5,L'Abergement-de-Varey,Abergement-de-Varey,1943-01-01,,\r\n"
                    + "");
        Response response = this.target(URL_GET_COMMUNE).request().get();
        assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());

    }
}
