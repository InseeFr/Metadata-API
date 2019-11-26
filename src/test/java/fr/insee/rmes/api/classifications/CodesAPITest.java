package fr.insee.rmes.api.classifications;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.insee.rmes.api.AbstractTest;
import fr.insee.rmes.modeles.classification.cj.CategorieJuridique;
import fr.insee.rmes.modeles.classification.cj.CategorieJuridiqueNiveauII;
import fr.insee.rmes.modeles.classification.cj.CategorieJuridiqueNiveauIII;
import fr.insee.rmes.modeles.classification.naf2008.SousClasseNAF2008;

@ExtendWith(MockitoExtension.class)
public class CodesAPITest extends AbstractTest {

    @InjectMocks
    private CodesAPI codesAPI;
    
    Object cj2 = new CategorieJuridiqueNiveauII();
    Object cj3 = new CategorieJuridiqueNiveauIII();
    Object ssClasseNaf = new SousClasseNAF2008();

    


    @Test
    public void givenGetCategorieJuridiqueNiveauII_whenCorrectRequest_thenResponseIsOk() {
        ((CategorieJuridiqueNiveauII) cj2).setUri("uri");

        // Mock
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(cj2);
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);

        // Call method
        codesAPI.getCategorieJuridiqueNiveauII("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());

        codesAPI.getCategorieJuridiqueNiveauII("", MediaType.APPLICATION_XML);
        verify(mockResponseUtils, times(2)).produceResponse(Mockito.any(), Mockito.any());

    }

    @Test
    public void givenGetCategorieJuridiqueNiveauII_whenCorrectRequest_andClassificationNotFound_thenResponseIsNotFound() {

        // Mock
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(cj2);

        // Call method
        Response response = codesAPI.getCategorieJuridiqueNiveauII("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
    
    @Test
    public void givenGetCategorieJuridiqueNiveauIII_whenCorrectRequest_thenResponseIsOk() {
        ((CategorieJuridiqueNiveauIII) cj3).setUri("uri");

        // Mock
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(cj3);
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);

        // Call method
        codesAPI.getCategorieJuridiqueNiveauIII("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());

        codesAPI.getCategorieJuridiqueNiveauIII("", MediaType.APPLICATION_XML);
        verify(mockResponseUtils, times(2)).produceResponse(Mockito.any(), Mockito.any());

    }

    @Test
    public void givenGetCategorieJuridiqueNiveauIII_whenCorrectRequest_andClassificationNotFound_thenResponseIsNotFound() {

        // Mock
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(cj3);

        // Call method
        Response response = codesAPI.getCategorieJuridiqueNiveauIII("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
    
    @Test
    public void givenGetCategoriesJuridiques_whenCorrectRequest_thenResponseIsOk() {
        list.add(new CategorieJuridique("1"));

        // Mock
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populateMultiPOJO(Mockito.anyString(), Mockito.any())).thenReturn(list);
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);


        // Call method
        codesAPI.getCategoriesJuridiques("",null, MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());
        
        codesAPI.getCategoriesJuridiques("","*", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(2)).produceResponse(Mockito.any(), Mockito.any());
        
        codesAPI.getCategoriesJuridiques("","2010-10-10", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(3)).produceResponse(Mockito.any(), Mockito.any());
        
        codesAPI.getCategoriesJuridiques("",null, MediaType.APPLICATION_XML);
        verify(mockResponseUtils, times(4)).produceResponse(Mockito.any(), Mockito.any());

    }
    
    @Test
    public void givenGetCategoriesJuridiques_whenCorrectRequest_andCJNotFound_thenResponseIsNotFound() {


        // Mock
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populateMultiPOJO(Mockito.anyString(), Mockito.any())).thenReturn(list);

        // Call method
        Response response = codesAPI.getCategoriesJuridiques("",null, MediaType.APPLICATION_JSON);  
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
        
        list.add(new CategorieJuridique(""));
        response = codesAPI.getCategoriesJuridiques("",null, MediaType.APPLICATION_JSON);  
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
    
    @Test
    public void givenGetCategoriesJuridiques_whenBadRequest_thenResponseIsBadRequest() {
        // Call method
        Response response = codesAPI.getCategoriesJuridiques("","1234", MediaType.APPLICATION_JSON);  
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
        Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
    
    @Test
    public void givenGetSousClasseNAF2008_whenCorrectRequest_thenResponseIsOk() {
        ((SousClasseNAF2008) ssClasseNaf).setUri("uri");

        // Mock
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(ssClasseNaf);
        when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);

        // Call method
        codesAPI.getSousClasseNAF2008("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, times(1)).produceResponse(Mockito.any(), Mockito.any());

        codesAPI.getSousClasseNAF2008("", MediaType.APPLICATION_XML);
        verify(mockResponseUtils, times(2)).produceResponse(Mockito.any(), Mockito.any());

    }

    @Test
    public void givenGetSousClasseNAF2008_whenCorrectRequest_andClassificationNotFound_thenResponseIsNotFound() {

        // Mock
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(ssClasseNaf);

        // Call method
        Response response = codesAPI.getSousClasseNAF2008("", MediaType.APPLICATION_JSON);
        verify(mockResponseUtils, never()).produceResponse(Mockito.any(), Mockito.any());
        Assertions.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

}
