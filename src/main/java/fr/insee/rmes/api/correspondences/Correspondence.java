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
	

	String codePosteSource;
	String uriPosteSource;
	String intituleFrPosteSource;
	String intituleEnPosteSource;
	String codePosteCible;
	String uriPosteCible;
	String intituleFrPosteCible;
	String intituleEnPosteCible;
	
	public Correspondence() {
	};// No-args constructor needed for JAXB

	@JacksonXmlProperty(isAttribute = true)
	public String getCodePosteSource() {
		return codePosteSource;
	}
	

	public void setCodePosteSource(String codePosteSource) {
		this.codePosteSource = codePosteSource;
	}

	@JacksonXmlProperty(isAttribute = true)
	public String getUriPosteSource() {
		return uriPosteSource;
	}


	public void setUriPosteSource(String uriPosteSource) {
		this.uriPosteSource = uriPosteSource;
	}

	@JacksonXmlProperty(localName="IntituleFrPosteSource")
	public String getintituleFrPosteSource() {
		return intituleFrPosteSource;
	}

	public void setintituleFrPosteSource(String intituleFrPosteSource) {
		this.intituleFrPosteSource = intituleFrPosteSource;
	}
	
	@JacksonXmlProperty(localName="IntituleEnPosteSource")
	public String getintituleEnPosteSource() {
		return intituleEnPosteSource;
	}

	public void setintituleEnPosteSource(String intituleEnPosteSource) {
		this.intituleEnPosteSource = intituleEnPosteSource;
	}

	@JacksonXmlProperty(isAttribute = true)
	public String getCodePosteCible() {
		return codePosteCible;
	}

	public void setCodePosteCible(String codePosteCible) {
		this.codePosteCible = codePosteCible;
	}
	
	@JacksonXmlProperty(isAttribute = true)
	public String getUriPosteCible() {
		return uriPosteCible;
	}

	public void setUriPosteCible(String uriPosteCible) {
		this.uriPosteCible = uriPosteCible;
	}

	@JacksonXmlProperty(localName="IntituleFrPosteCible")
	public String getintituleFrPosteCible() {
		return intituleFrPosteCible;
	}

	
	public void setintituleFrPosteCible(String intituleFrPosteCible) {
		this.intituleFrPosteCible = intituleFrPosteCible;
	}
	
	@JacksonXmlProperty(localName="IntituleEnPosteCible")
	public String getintituleEnPosteCible() {
		return intituleEnPosteCible;
	}

	public void setintituleEnPosteCible(String intituleEnPosteCible) {
		this.intituleEnPosteCible = intituleEnPosteCible;
	}; 
	
	

	
	
	
	
	
	
	
	
	

}
