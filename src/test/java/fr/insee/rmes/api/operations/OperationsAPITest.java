package fr.insee.rmes.api.operations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.insee.rmes.api.AbstractApiTest;
import fr.insee.rmes.modeles.operations.Famille;
import fr.insee.rmes.modeles.operations.FamilyToOperation;

@ExtendWith(MockitoExtension.class)
public class OperationsAPITest extends AbstractApiTest {

    @InjectMocks
    private OperationsAPI operationsAPI;

    @Mock
    private OperationsApiService mockOperationApiService;

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

}
