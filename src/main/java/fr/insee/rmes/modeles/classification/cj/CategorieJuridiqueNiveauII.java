package fr.insee.rmes.modeles.classification.cj;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="CategorieJuridiqueNiveauII")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategorieJuridiqueNiveauII {
	
	private String code;
	private String uri;
	private String intitule;
	
	public CategorieJuridiqueNiveauII() {} // No-args constructor needed for JAXB

	public CategorieJuridiqueNiveauII(String code) {
		this.code = code;
	}
	
	@JacksonXmlProperty(isAttribute=true)
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	@JacksonXmlProperty(isAttribute=true)
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	@JacksonXmlProperty(localName="Intitule")
	@JsonProperty(value="intitule")
	public String getIntitule() {
		return intitule;
	}
	
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
}
