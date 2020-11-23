package fr.insee.rmes.api.operations.pseudointegrationtest;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.insee.rmes.api.operations.OperationsApiService;
import fr.insee.rmes.modeles.operations.SimpleObject;
import fr.insee.rmes.modeles.operations.documentations.Document;
import fr.insee.rmes.modeles.operations.documentations.Rubrique;
import fr.insee.rmes.modeles.operations.documentations.RubriqueRichText;
import fr.insee.rmes.utils.Lang;
import fr.insee.rmes.utils.SparqlUtils;

@ExtendWith(MockitoExtension.class)
class OperationsApiServiceTest  {

    @InjectMocks
    private OperationsApiService operationsApiService;

    @Mock
    protected SparqlUtils mockSparqlUtils;
    
    private static final String MOCK_CSV_GET_DOCUMENTATION = 
    		"uri,id,idParent,titreLg1,titreLg2,type,valeurSimple,labelLg1,labelLg2,codeUri,organisationUri,hasDocLg1,hasDocLg2,labelObjLg1,labelObjLg2,maxOccurs,geoUri\r\n"
    				+ "http://test/qualite/simsv2fr/attribut/S.3.7,S.3.7,S.3,Zone géographique de référence,Reference area,GEOGRAPHY,franceHorsMayotte,,,,,,,France hors Mayotte,,,http://test/qualite/territoire/franceHorsMayotte\r\n"
    				+ "http://test/qualite/simsv2fr/attribut/S.18.3,S.18.3,S.18,Collecte des données,Data collection,RICH_TEXT,,<p>France&nbsp; enti&egrave;re</p>,\"<p>Whole France</p>\",,,false,false,,,,\r\n"
    				+ "http://test/qualite/simsv2fr/attribut/S.3.6,S.3.6,S.3,Population statistique,Statistical population,RICH_TEXT,,\"<p>Texte riche avec documents</p>\",\"<p>With docs</p>\",,,true,true,,,,\r\n"
    				+ "http://test/qualite/simsv2fr/attribut/S.9,S.9,,Fréquence de diffusion,Frequency of dissemination,CODE_LIST,M,,,http://test/codes/frequence/M,,,,Mensuelle,Monthly,unbounded,\r\n";

    private static final String MOCK_CSV_GET_DOCUMENTS_EMPTY = "url,labelLg1,labelLg2,dateMiseAJour,langue";
    
    private static List<Rubrique> EXPECTED_RUBRICS ; 

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        initExpected();
    }



	@ParameterizedTest
    @ValueSource(strings = {""})
    @NullSource
    void givenGetListRubriques_whenQueryReturnNothing_thenResponseIsEmpty(String id) {
    	 when(mockSparqlUtils.executeSparqlQuery(Mockito.anyString())).thenReturn("");
    	 List<Rubrique> liste = operationsApiService.getListRubriques(id);
    	 Assertions.assertEquals(new ArrayList<Rubrique>(),liste);
    }
    
    
    @Test
    void givenGetListRubriques_whenQueryOk_thenResponseIsOk() {
    	 when(mockSparqlUtils.executeSparqlQuery(Mockito.anyString()))
    	 	.thenReturn(MOCK_CSV_GET_DOCUMENTATION)//first call : get list rubrics
    	 	.thenReturn(MOCK_CSV_GET_DOCUMENTS_EMPTY)//get documents fr 
    	 	.thenReturn(MOCK_CSV_GET_DOCUMENTS_EMPTY);//get documents en
    	 List<Rubrique> liste = operationsApiService.getListRubriques("1774");
    //	 assertTrue(EXPECTED_RUBRICS.equals(liste));
    	 Assertions.assertEquals(EXPECTED_RUBRICS,liste);
    	// Assertions.assertArrayEquals(EXPECTED_RUBRICS.toArray(), liste.toArray());
    	// assertSame(EXPECTED_RUBRICS, liste);
       //  assertThat(liste, containsExactlyInAnyOrder(EXPECTED_RUBRICS));
    }
    
    

    
    private void initExpected() {
    	EXPECTED_RUBRICS = new ArrayList<>();
    	
    	Rubrique r1 = new Rubrique("S.3.7", "http://test/qualite/simsv2fr/attribut/S.3.7", "GEOGRAPHY");
    	r1.setIdParent("S.3");
    	r1.setTitre("Zone géographique de référence", "Reference area");
    	r1.setValeurGeographie(new SimpleObject("franceHorsMayotte", "http://test/qualite/territoire/franceHorsMayotte", "France hors Mayotte", null));
    	EXPECTED_RUBRICS.add(r1);
    	
    	Rubrique r2 = new Rubrique("S.18.3", "http://test/qualite/simsv2fr/attribut/S.18.3", "RICH_TEXT");
    	r2.setIdParent("S.18");
    	r2.setTitre("Collecte des données","Data collection");
    	RubriqueRichText r2rtFr = new RubriqueRichText("<p>France&nbsp; enti&egrave;re</p>", Lang.FR);
    	RubriqueRichText r2rtEn = new RubriqueRichText("<p>Whole France</p>", Lang.EN);
    	r2.addRichTexts(r2rtFr);
    	r2.addRichTexts(r2rtEn);
    	EXPECTED_RUBRICS.add(r2);

    	Rubrique r3 = new Rubrique("S.3.6", "http://test/qualite/simsv2fr/attribut/S.3.6", "RICH_TEXT");
    	r3.setIdParent("S.3");
    	r3.setTitre("Population statistique","Statistical population");
    	RubriqueRichText r3rtFr = new RubriqueRichText("<p>Texte riche avec documents</p>", Lang.FR);
    	List<Document> docs = new ArrayList<>();
    	r3rtFr.setDocuments(docs);
    	RubriqueRichText r3rtEn = new RubriqueRichText("<p>With docs</p>", Lang.EN);
    	r3rtEn.setDocuments(docs);
    	r3.addRichTexts(r3rtFr);
    	r3.addRichTexts(r3rtEn);
    	EXPECTED_RUBRICS.add(r3);
    	
    	Rubrique r4 = new Rubrique("S.9", "http://test/qualite/simsv2fr/attribut/S.9", "CODE_LIST");
    	r4.setIdParent("");
    	r4.setTitre("Fréquence de diffusion","Frequency of dissemination");
    	SimpleObject r4so = new SimpleObject("M","http://test/codes/frequence/M","Mensuelle","Monthly" );
    	List<SimpleObject> r4listSo = new ArrayList<>();
    	r4listSo.add(r4so);
    	r4.setValeurCode(r4listSo);
    	EXPECTED_RUBRICS.add(r4);
	}

   
}
