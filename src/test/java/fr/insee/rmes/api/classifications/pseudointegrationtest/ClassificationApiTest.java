package fr.insee.rmes.api.classifications.pseudointegrationtest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.insee.rmes.api.classifications.ClassificationApi;
import fr.insee.rmes.utils.SparqlUtils;

@ExtendWith(MockitoExtension.class)
class ClassificationApiTest  {

    @InjectMocks
    private ClassificationApi classificationAPI;

    @Mock
    protected SparqlUtils mockSparqlUtils;
    
    private static final String MOCK_CSV_GET_POSTE =
    		"uri,code,uriParent,codeParent,intituleFr,intituleEn,noteGenerale,contenuLimite,contenuCentral,contenuExclu\r\n"
    		+ "http://id.insee.fr/codes/nafr2/division/01,01,http://id.insee.fr/codes/nafr2/section/A,A,Intitulé FR 01,En title 01,<div xmlns='http://www.w3.org/1999/xhtml'><p>Note générale</p></div>,<div xmlns='http://www.w3.org/1999/xhtml'><p>Limite</p></div>,<div xmlns='http://www.w3.org/1999/xhtml'><p>Central</p></div>,<div xmlns='http://www.w3.org/1999/xhtml'>Exclu</div>\r\n"
    		+ "http://id.insee.fr/codes/nafr2/groupe/01.1,01.1,http://id.insee.fr/codes/nafr2/division/01,01,Titre fr 01.1,En title 01.1,<div xmlns='http://www.w3.org/1999/xhtml'><p>Note général & par ça €</p></div>,<div xmlns='http://www.w3.org/1999/xhtml'><p>Limite</p></div>,<div xmlns='http://www.w3.org/1999/xhtml'><p>Central</p></div>,<div xmlns='http://www.w3.org/1999/xhtml'><p>Exclu</p></div>";

    
    private static final String EXPECTED_JSON = "[{\"uri\":\"http://id.insee.fr/codes/nafr2/division/01\",\"code\":\"01\",\"uriParent\":\"http://id.insee.fr/codes/nafr2/section/A\",\"codeParent\":\"A\",\"intituleFr\":\"Intitulé FR 01\",\"intituleEn\":\"En title 01\",\"contenuLimite\":\"<div xmlns='http://www.w3.org/1999/xhtml'><p>Limite</p></div>\",\"contenuCentral\":\"<div xmlns='http://www.w3.org/1999/xhtml'><p>Central</p></div>\",\"contenuExclu\":\"<div xmlns='http://www.w3.org/1999/xhtml'>Exclu</div>\",\"noteGenerale\":\"<div xmlns='http://www.w3.org/1999/xhtml'><p>Note générale</p></div>\"},{\"uri\":\"http://id.insee.fr/codes/nafr2/groupe/01.1\",\"code\":\"01.1\",\"uriParent\":\"http://id.insee.fr/codes/nafr2/division/01\",\"codeParent\":\"01\",\"intituleFr\":\"Titre fr 01.1\",\"intituleEn\":\"En title 01.1\",\"contenuLimite\":\"<div xmlns='http://www.w3.org/1999/xhtml'><p>Limite</p></div>\",\"contenuCentral\":\"<div xmlns='http://www.w3.org/1999/xhtml'><p>Central</p></div>\",\"contenuExclu\":\"<div xmlns='http://www.w3.org/1999/xhtml'><p>Exclu</p></div>\",\"noteGenerale\":\"<div xmlns='http://www.w3.org/1999/xhtml'><p>Note général & par ça €</p></div>\"}]";
    private static final String EXPECTED_XML = "<Postes><Poste uri=\"http://id.insee.fr/codes/nafr2/division/01\" code=\"01\" uriParent=\"http://id.insee.fr/codes/nafr2/section/A\" codeParent=\"A\"><IntituleFr>Intitulé FR 01</IntituleFr><IntituleEn>En title 01</IntituleEn><ContenuLimite><div xmlns='http://www.w3.org/1999/xhtml'><p>Limite</p></div></ContenuLimite><ContenuCentral><div xmlns='http://www.w3.org/1999/xhtml'><p>Central</p></div></ContenuCentral><ContenuExclu><div xmlns='http://www.w3.org/1999/xhtml'>Exclu</div></ContenuExclu><NoteGenerale><div xmlns='http://www.w3.org/1999/xhtml'><p>Note générale</p></div></NoteGenerale></Poste><Poste uri=\"http://id.insee.fr/codes/nafr2/groupe/01.1\" code=\"01.1\" uriParent=\"http://id.insee.fr/codes/nafr2/division/01\" codeParent=\"01\"><IntituleFr>Titre fr 01.1</IntituleFr><IntituleEn>En title 01.1</IntituleEn><ContenuLimite><div xmlns='http://www.w3.org/1999/xhtml'><p>Limite</p></div></ContenuLimite><ContenuCentral><div xmlns='http://www.w3.org/1999/xhtml'><p>Central</p></div></ContenuCentral><ContenuExclu><div xmlns='http://www.w3.org/1999/xhtml'><p>Exclu</p></div></ContenuExclu><NoteGenerale><div xmlns='http://www.w3.org/1999/xhtml'><p>Note général & par ça €</p></div></NoteGenerale></Poste></Postes>";
    		
    
    
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    
    @Test
    void givenGetListRubriques_whenJSONQueryOk_thenResponseIsOk() {
    	 when(mockSparqlUtils.executeSparqlQuery(Mockito.anyString()))
    	 	.thenReturn(MOCK_CSV_GET_POSTE);
    	 Response r = classificationAPI.getClassificationByCode("nafr2", MediaType.APPLICATION_JSON);
         assertEquals(Status.OK.getStatusCode(), r.getStatus());
    	 Assertions.assertEquals(EXPECTED_JSON,r.getEntity());
    }
    
    @Test
    void givenGetListRubriques_whenXMLQueryOk_thenResponseIsOk() {
    	 when(mockSparqlUtils.executeSparqlQuery(Mockito.anyString()))
    	 	.thenReturn(MOCK_CSV_GET_POSTE);
    	 Response r = classificationAPI.getClassificationByCode("nafr2", MediaType.APPLICATION_XML);
         assertEquals(Status.OK.getStatusCode(), r.getStatus());
    	 Assertions.assertEquals(EXPECTED_XML,r.getEntity());
    }

   

   
}
