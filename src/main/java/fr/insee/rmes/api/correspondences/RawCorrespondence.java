package fr.insee.rmes.api.correspondences;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Correspondence")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RawCorrespondence {
	

	private String codePoste1;
	private String uriPoste1;
	private String intituleFrPoste1;
	private String intituleEnPoste1;
	private String codePoste2;
	private String uriPoste2;
	private String intituleFrPoste2;
	private String intituleEnPoste2;
	
	public RawCorrespondence() {
	};// No-args constructor needed for JAXB

	@JacksonXmlProperty(isAttribute = true)
	public String getCodePoste1() {
		return codePoste1;
	}
	

	public void setCodePoste1(String codePoste1) {
		this.codePoste1 = codePoste1;
	}

	@JacksonXmlProperty(isAttribute = true)
	public String getUriPoste1() {
		return uriPoste1;
	}


	public void setUriPoste1(String uriPoste1) {
		this.uriPoste1 = uriPoste1;
	}

	@JacksonXmlProperty(localName="IntituleFrPoste1")
	public String getIntituleFrPoste1() {
		return intituleFrPoste1;
	}

	public void setIntituleFrPoste1(String intituleFrPoste1) {
		this.intituleFrPoste1 = intituleFrPoste1;
	}
	
	@JacksonXmlProperty(localName="IntituleEnPoste1")
	public String getIntituleEnPoste1() {
		return intituleEnPoste1;
	}

	public void setIntituleEnPoste1(String intituleEnPoste1) {
		this.intituleEnPoste1 = intituleEnPoste1;
	}

	@JacksonXmlProperty(isAttribute = true)
	public String getCodePoste2() {
		return codePoste2;
	}

	public void setCodePoste2(String codePoste2) {
		this.codePoste2 = codePoste2;
	}
	
	@JacksonXmlProperty(isAttribute = true)
	public String getUriPoste2() {
		return uriPoste2;
	}

	public void setUriPoste2(String uriPoste2) {
		this.uriPoste2 = uriPoste2;
	}

	@JacksonXmlProperty(localName="IntituleFrPoste2")
	public String getIntituleFrPoste2() {
		return intituleFrPoste2;
	}

	
	public void setIntituleFrPoste2(String intituleFrPoste2) {
		this.intituleFrPoste2 = intituleFrPoste2;
	}
	
	@JacksonXmlProperty(localName="IntituleEnPoste2")
	public String getIntituleEnPoste2() {
		return intituleEnPoste2;
	}

	public void setIntituleEnPoste2(String intituleEnPoste2) {
		this.intituleEnPoste2 = intituleEnPoste2;
	}; 
	
	
	

}