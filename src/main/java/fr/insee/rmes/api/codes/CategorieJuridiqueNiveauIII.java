package fr.insee.rmes.api.codes;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CategorieJuridiqueNiveauIII")
public class CategorieJuridiqueNiveauIII {
	
	@XmlAttribute
	private String code;
	@XmlAttribute
	private String uri;
	@XmlElement(name="Intitule")
	private String intitule;
	
	public void populateFromCSV(String csvString) {

		int firstComma = csvString.indexOf(',');
		uri = csvString.substring(0, firstComma);
		intitule = csvString.substring(firstComma + 1);
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

}
