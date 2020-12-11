package fr.insee.rmes.api.concepts.pseudointegrationtest;

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

import fr.insee.rmes.api.concepts.ConceptsAPI;
import fr.insee.rmes.utils.SparqlUtils;

@ExtendWith(MockitoExtension.class)
class ConceptsApiTest  {

    @InjectMocks
    private ConceptsAPI conceptsAPI;

    @Mock
    protected SparqlUtils mockSparqlUtils;
    
    private static final String MOCK_CSV_GET_DEF_ELEC =
    		"id,uri,intitule,replaces,isReplacedBy\r\n"
    		+ "c1769,http://id.insee.fr/concepts/definition/c1769,Commerce électronique,,\r\n"
    		+ "c1158,http://id.insee.fr/concepts/definition/c1158,Fichier général des électeurs ,,http://id.insee.fr/concepts/definition/c2131\r\n"
    		+ "c2131,http://id.insee.fr/concepts/definition/c2131,Répertoire électoral unique,http://id.insee.fr/concepts/definition/c1158,\r\n"
    		;
    
    private static final String EXPECTED_JSON_ELEC = "[{\"id\":\"c1769\",\"uri\":\"http://id.insee.fr/concepts/definition/c1769\",\"intitule\":\"Commerce électronique\"},{\"id\":\"c1158\",\"uri\":\"http://id.insee.fr/concepts/definition/c1158\",\"intitule\":\"Fichier général des électeurs \",\"estRemplacePar\":{\"id\":\"c2131\",\"uri\":\"http://id.insee.fr/concepts/definition/c2131\"}},{\"id\":\"c2131\",\"uri\":\"http://id.insee.fr/concepts/definition/c2131\",\"intitule\":\"Répertoire électoral unique\",\"remplace\":{\"id\":\"c1158\",\"uri\":\"http://id.insee.fr/concepts/definition/c1158\"}}]";
    private static final String EXPECTED_XML_ELEC = "<Definitions><Definition id=\"c1769\" uri=\"http://id.insee.fr/concepts/definition/c1769\"><Intitule>Commerce électronique</Intitule></Definition><Definition id=\"c1158\" uri=\"http://id.insee.fr/concepts/definition/c1158\"><Intitule>Fichier général des électeurs </Intitule><EstRemplacePar><id>c2131</id><uri>http://id.insee.fr/concepts/definition/c2131</uri></EstRemplacePar></Definition><Definition id=\"c2131\" uri=\"http://id.insee.fr/concepts/definition/c2131\"><Intitule>Répertoire électoral unique</Intitule><Remplace><id>c1158</id><uri>http://id.insee.fr/concepts/definition/c1158</uri></Remplace></Definition></Definitions>";
    
    private static final String MOCK_CSV_GET_C1500 =
    		"uri,intituleFr,intituleEn,replaces,isReplacedBy,definitionFr,definitionEn,editorialNoteFr,editorialNoteEn,scopeNoteFr,scopeNoteEn,dateMiseAJour\r\n"
    		+ "http://id.insee.fr/concepts/definition/c1500,Micro-entrepreneur,Micro-entrepreneur,http://id.insee.fr/concepts/definition/c2066,http://id.insee.fr/concepts/definition/c2067,\"<div xmlns=\"\"http://www.w3.org/1999/xhtml\"\"><p>Un micro-entrepreneur ...</p></div>\",\"<div xmlns=\"\"http://www.w3.org/1999/xhtml\"\"><p>A micro-entrepreneur ...</p></div>\",\"<div xmlns=\"\"http://www.w3.org/1999/xhtml\"\"><ul><li>Depuis janvier 2011, </li></ul></div>\",\"<div xmlns=\"\"http://www.w3.org/1999/xhtml\"\"><ul><li>Since January, 2011,</li></ul><p><br/></p></div>\",\"<div xmlns=\"\"http://www.w3.org/1999/xhtml\"\"><p>Un micro-entrepreneur ...</p></div>\",\"<div xmlns=\"\"http://www.w3.org/1999/xhtml\"\"><p>A micro-entrepreneur...</p></div>\",2019-11-05T12:22:30.772\r\n"
    		;
    
    private static final String EXPECTED_JSON_C1500 = "{\"id\":\"c1500\",\"uri\":\"http://id.insee.fr/concepts/definition/c1500\",\"intitule\":[{\"contenu\":\"Micro-entrepreneur\",\"langue\":\"fr\"},{\"contenu\":\"Micro-entrepreneur\",\"langue\":\"en\"}],\"definition\":[{\"contenu\":\"<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\"><p>Un micro-entrepreneur ...</p></div>\",\"langue\":\"fr\"},{\"contenu\":\"<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\"><p>A micro-entrepreneur ...</p></div>\",\"langue\":\"en\"}],\"noteEditoriale\":[{\"contenu\":\"<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\"><ul><li>Depuis janvier 2011, </li></ul></div>\",\"langue\":\"fr\"},{\"contenu\":\"<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\"><ul><li>Since January, 2011,</li></ul><p><br/></p></div>\",\"langue\":\"en\"}],\"definitionCourte\":[{\"contenu\":\"<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\"><p>Un micro-entrepreneur ...</p></div>\",\"langue\":\"fr\"},{\"contenu\":\"<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\"><p>A micro-entrepreneur...</p></div>\",\"langue\":\"en\"}],\"remplace\":{\"id\":\"c2066\",\"uri\":\"http://id.insee.fr/concepts/definition/c2066\"},\"estRemplacePar\":{\"id\":\"c2067\",\"uri\":\"http://id.insee.fr/concepts/definition/c2067\"},\"dateMiseAJour\":\"2019-11-05\"}";
    private static final String EXPECTED_XML_C1500 = "<Definition id=\"c1500\" uri=\"http://id.insee.fr/concepts/definition/c1500\"><Intitule langue=\"fr\"><contenu>Micro-entrepreneur</contenu></Intitule><Intitule langue=\"en\"><contenu>Micro-entrepreneur</contenu></Intitule><Definition langue=\"fr\"><contenu><div xmlns=\"http://www.w3.org/1999/xhtml\"><p>Un micro-entrepreneur ...</p></div></contenu></Definition><Definition langue=\"en\"><contenu><div xmlns=\"http://www.w3.org/1999/xhtml\"><p>A micro-entrepreneur ...</p></div></contenu></Definition><NoteEditoriale langue=\"fr\"><contenu><div xmlns=\"http://www.w3.org/1999/xhtml\"><ul><li>Depuis janvier 2011, </li></ul></div></contenu></NoteEditoriale><NoteEditoriale langue=\"en\"><contenu><div xmlns=\"http://www.w3.org/1999/xhtml\"><ul><li>Since January, 2011,</li></ul><p><br/></p></div></contenu></NoteEditoriale><DefinitionCourte langue=\"fr\"><contenu><div xmlns=\"http://www.w3.org/1999/xhtml\"><p>Un micro-entrepreneur ...</p></div></contenu></DefinitionCourte><DefinitionCourte langue=\"en\"><contenu><div xmlns=\"http://www.w3.org/1999/xhtml\"><p>A micro-entrepreneur...</p></div></contenu></DefinitionCourte><Remplace><id>c2066</id><uri>http://id.insee.fr/concepts/definition/c2066</uri></Remplace><EstRemplacePar><id>c2067</id><uri>http://id.insee.fr/concepts/definition/c2067</uri></EstRemplacePar><DateMiseAJour>2019-11-05</DateMiseAJour></Definition>";
    
    
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    
    @Test
    void givenGetListDefinitions_whenJSONQueryOk_thenResponseIsOk() {
    	 when(mockSparqlUtils.executeSparqlQuery(Mockito.anyString()))
    	 	.thenReturn(MOCK_CSV_GET_DEF_ELEC);
    	 Response r = conceptsAPI.getConcepts("élec", MediaType.APPLICATION_JSON);
         assertEquals(Status.OK.getStatusCode(), r.getStatus());
    	 Assertions.assertEquals(EXPECTED_JSON_ELEC,r.getEntity());
    }
    
    @Test
    void givenGetListDefinitions_whenXMLQueryOk_thenResponseIsOk() {
    	 when(mockSparqlUtils.executeSparqlQuery(Mockito.anyString()))
    	 	.thenReturn(MOCK_CSV_GET_DEF_ELEC);
    	 Response r = conceptsAPI.getConcepts("élec", MediaType.APPLICATION_XML);
         assertEquals(Status.OK.getStatusCode(), r.getStatus());
    	 Assertions.assertEquals(EXPECTED_XML_ELEC,r.getEntity());
    }

    @Test
    void givenGetConceptById_whenXMLQueryOk_thenResponseIsOk() {
    	 when(mockSparqlUtils.executeSparqlQuery(Mockito.anyString()))
    	 	.thenReturn(MOCK_CSV_GET_C1500);
    	 Response r = conceptsAPI.getConceptById("c1500", MediaType.APPLICATION_XML);
         assertEquals(Status.OK.getStatusCode(), r.getStatus());
    	 Assertions.assertEquals(EXPECTED_XML_C1500,r.getEntity());
    }
    

    @Test
    void givenGetConceptById_whenJSONQueryOk_thenResponseIsOk() {
    	 when(mockSparqlUtils.executeSparqlQuery(Mockito.anyString()))
    	 	.thenReturn(MOCK_CSV_GET_C1500);
    	 Response r = conceptsAPI.getConceptById("c1500", MediaType.APPLICATION_JSON);
         assertEquals(Status.OK.getStatusCode(), r.getStatus());
    	 Assertions.assertEquals(EXPECTED_JSON_C1500,r.getEntity());
    }


   
}
