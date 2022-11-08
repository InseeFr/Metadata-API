package fr.insee.rmes.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.ws.rs.core.MediaType;

import fr.insee.rmes.modeles.operations.documentations.DocumentationSims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.insee.rmes.modeles.TestObject;
import fr.insee.rmes.modeles.operations.documentations.RubriqueRichText;

@ExtendWith(MockitoExtension.class)
class ResponseUtilsTest {
	
    @InjectMocks
    private ResponseUtils responseUtils;
    
    TestObject testObj;
	DocumentationSims testSims;
    String expectedJson;
    String expectedXml;
	String expectedXmlSims;
    
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
	@ParameterizedTest
    @ValueSource(strings = {"text to Try", "Text with accent éùàèô", "Text with special XML/Json char < > "})
    void givenProduceResponse_whenQueryOk_thenResponseIsOk(String textToTry) {
    	initExpected(textToTry);
    	String resultXml = responseUtils.produceResponse(testObj, MediaType.APPLICATION_XML);
    	assertEquals(expectedXml,resultXml);

		String resultXmlSims = responseUtils.produceResponse(testObj, MediaType.APPLICATION_XML);
		assertEquals(expectedXmlSims,resultXmlSims);

    	String resultJson = responseUtils.produceResponse(testObj, MediaType.APPLICATION_JSON);
    	assertEquals(expectedJson,resultJson);
    }

    
    private void initExpected(String str) {
    	testObj = new TestObject();
    	testObj.setString(str);



    	RubriqueRichText r = new RubriqueRichText(str, Lang.FR);
    	testObj.setRubriqueRichText(r);
    	
    	String strXmlSims = str.replace("&", "&amp;")
				 .replace(">", "&gt;")
				 .replace("<", "&lt;")
				 .replace("\"", "&quot;")
				 .replace("'", "&apos;");
    	String strXml = str;
    	String strJson = str;
    	
    	
    	expectedXml =
    			"<TestObject>"
    			+ "<string>"+strXml+"</string>"
    			+ "<rubriqueRichText xml:lang=\"fr\"><Texte>" +strXml+"</Texte></rubriqueRichText>"
    			+ "</TestObject>";
    	
    	expectedJson =
    				"{\"string\":\""+strJson+"\","
    				+ "\"rubriqueRichText\":{\"texte\":\""+strJson+"\",\"langue\":\"fr\"}}";


		expectedXmlSims = "<TestObject>"
				+ "<string>"+strXmlSims+"</string>"
				+ "<rubriqueRichText xml:lang=\"fr\"><Texte>" +strXmlSims+"</Texte></rubriqueRichText>"
				+ "</TestObject>";
	}
    

}
