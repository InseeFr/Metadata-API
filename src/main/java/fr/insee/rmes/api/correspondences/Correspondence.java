package fr.insee.rmes.api.correspondences;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Correspondence")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_EMPTY) 
public class Correspondence {
	
	
	String uri;
	String code;
	String intitule;
	String uriCible;
	String codeCible;
	String intituleCible;
	String typeCorrespondence;
	
	public Correspondence() {
	} // No-args constructor needed for JAXB

	//@JacksonXmlProperty(localName="Intitule")
	@JacksonXmlProperty(isAttribute = true)
	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	@JacksonXmlProperty(isAttribute = true)
	public String getUriCible() {
		return uriCible;
	}

	
	public void setUriCible(String uriCible) {
		this.uriCible = uriCible;
	}

	@JacksonXmlProperty(isAttribute = true)
	public String getCodeCible() {
		return codeCible;
	}

	public void setCodeCible(String codeCible) {
		this.codeCible = codeCible;
	}

	@JacksonXmlProperty(isAttribute = true)
	public String getIntituleCible() {
		return intituleCible;
	}

	public void setIntituleCible(String intituleCible) {
		this.intituleCible = intituleCible;
	}



	@JacksonXmlProperty(isAttribute = true)
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@JacksonXmlProperty(isAttribute = true)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@JacksonXmlProperty(isAttribute = true)
	public String getTypeCorrespondence() {
		return typeCorrespondence;
	}

	public void setTypeCorrespondence(String typeCorrespondence) {
		this.typeCorrespondence = typeCorrespondence;
	}
	
	



	
}
