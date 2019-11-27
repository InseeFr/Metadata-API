package fr.insee.rmes.api.operations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.insee.rmes.api.AbstractApiTest;
import fr.insee.rmes.modeles.operations.CsvIndicateur;
import fr.insee.rmes.modeles.operations.CsvSerie;
import fr.insee.rmes.modeles.operations.Famille;
import fr.insee.rmes.modeles.operations.FamilyToOperation;
import fr.insee.rmes.modeles.operations.documentations.DocumentationSims;

@ExtendWith(MockitoExtension.class)
public class OperationsAPITest extends AbstractApiTest {

    @InjectMocks
    private OperationsAPI operationsAPI;

    @Mock
    private OperationsApiService mockOperationApiService;

    private DocumentationSims sims = new DocumentationSims();
    private CsvSerie csvSerie = new CsvSerie();
    private CsvIndicateur csvIndic = new CsvIndicateur();

    @Override
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenGetOperationsTree_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock
        list.add(new FamilyToOperation());
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populateMultiPOJO(Mockito.anyString(), Mockito.any())).thenReturn(list);
        when(mockOperationApiService.getListeFamilyToOperation(Mockito.any()))
            .thenReturn(new HashMap<String, Famille>());
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);

        // Call method with header content is json
        operationsAPI.getOperationsTree("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetOperationsTree_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock
        list.add(new FamilyToOperation());
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populateMultiPOJO(Mockito.anyString(), Mockito.any())).thenReturn(list);
        when(mockOperationApiService.getListeFamilyToOperation(Mockito.any()))
            .thenReturn(new HashMap<String, Famille>());
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);

        // Call method with header content is xml
        operationsAPI.getOperationsTree("toto", MediaType.APPLICATION_XML);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetOperationsTree_whenCorrectRequest_andDiffusuerIsInseeFr_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock
        list.add(new FamilyToOperation());
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populateMultiPOJO(Mockito.anyString(), Mockito.any())).thenReturn(list);
        when(mockOperationApiService.getListeFamilyToOperation(Mockito.any()))
            .thenReturn(new HashMap<String, Famille>());
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);

        // Call method with header content is json
        operationsAPI.getOperationsTree("insee.fr", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetOperationsTree_whenCorrectRequest_thenResponseIsNotFound() {

        // Mock
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populateMultiPOJO(Mockito.anyString(), Mockito.any())).thenReturn(list);

        // Call method with header content is json
        Response response = operationsAPI.getOperationsTree("insee.fr", MediaType.APPLICATION_JSON);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        response = operationsAPI.getOperationsTree("insee.fr", MediaType.APPLICATION_XML);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, Mockito.never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetDocumentation_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock
        sims.setUri("something");
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(sims);
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);

        // Call method with header content is json
        operationsAPI.getDocumentation("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetDocumentation_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock
        sims.setUri("something");
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(sims);
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);

        // Call method with header content is xml
        operationsAPI.getDocumentation("toto", MediaType.APPLICATION_XML);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetDocumentation_whenCorrectRequest_thenResponseIsNotFound() {

        // Mock
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(sims);

        // Call method with header content is json
        Response response = operationsAPI.getDocumentation("", MediaType.APPLICATION_JSON);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        response = operationsAPI.getDocumentation("", MediaType.APPLICATION_XML);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, Mockito.never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetSeries_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock
        csvSerie.setSeriesId("something");
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(csvSerie);
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);

        // Call method with header content is json
        operationsAPI.getSeries("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetSeries_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock
        csvSerie.setSeriesId("something");
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(csvSerie);
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);

        // Call method with header content is xml
        operationsAPI.getSeries("toto", MediaType.APPLICATION_XML);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetSeries_whenCorrectRequest_thenResponseIsNotFound() {

        // Mock
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(csvSerie);

        // Call method with header content is json
        Response response = operationsAPI.getSeries("", MediaType.APPLICATION_JSON);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        response = operationsAPI.getSeries("", MediaType.APPLICATION_XML);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, Mockito.never()).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetIndicateur_whenCorrectRequest_andHeaderContentIsJson_thenResponseIsOk() {

        // Mock
        csvIndic.setId("something");
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(csvIndic);
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);

        // Call method with header content is json
        operationsAPI.getIndicateur("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetIndicateur_whenCorrectRequest_andHeaderContentIsXml_thenResponseIsOk() {

        // Mock
        csvIndic.setId("something");
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(csvIndic);
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);

        // Call method with header content is xml
        operationsAPI.getIndicateur("toto", MediaType.APPLICATION_XML);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenGetIndicateur_whenCorrectRequest_thenResponseIsNotFound() {

        // Mock
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(csvIndic);

        // Call method with header content is json
        Response response = operationsAPI.getIndicateur("", MediaType.APPLICATION_JSON);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        response = operationsAPI.getIndicateur("", MediaType.APPLICATION_XML);
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

        verify(mockResponseUtils, Mockito.never()).produceResponse(Mockito.any(), Mockito.any());
    }

}
