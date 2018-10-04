package fr.insee.rmes.api.classifications;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.lang3.StringEscapeUtils;
import org.glassfish.jersey.message.internal.Statuses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Poste")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_EMPTY) 
public class Poste {

	String uri;
	String code;
	String uriParent;
	String codeParent;
	String intituleFr;
	String intituleEn;
	//@JacksonXmlText
	String contenuLimite;
	String contenuCentral;
	String contenuExclu;
	
	
	
	public Poste() {
	} // No-args constructor needed for JAXB

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
	public String getUriParent() {
		return uriParent;
	}

	public void setUriParent(String uriParent) {
		this.uriParent = uriParent;
	}

	@JacksonXmlProperty(isAttribute = true)
	public String getCodeParent() {
		return codeParent;
	}

	public void setCodeParent(String codeParent) {
		this.codeParent = codeParent;
	}

	@JacksonXmlProperty(localName="IntituleFr")
	public String getIntituleFr() {
		return intituleFr;
	}

	public void setIntituleFr(String intituleFr) {
		this.intituleFr = intituleFr;
	}

	@JacksonXmlProperty(localName="IntituleEn")
	public String getIntituleEn() {
		return intituleEn;
	}

	public void setIntituleEn(String intituleEn) {
		this.intituleEn = intituleEn;
	}

	@JacksonXmlProperty(localName="ContenuLimite")
	@JacksonXmlCData
	public String getContenuLimite() {
		return contenuLimite;
	}

	public void setContenuLimite(String contenuLimite) {
		this.contenuLimite = contenuLimite;
	}

	@JacksonXmlProperty(localName="ContenuCentral")
	@JacksonXmlCData
	public String getContenuCentral() {
		return contenuCentral;
	}

	public void setContenuCentral(String contenuCentral) {
		this.contenuCentral = contenuCentral;
	}

	@JacksonXmlProperty(localName="ContenuExclu")
	@JacksonXmlCData
	public String getContenuExclu() {
		return contenuExclu;
	}

	public void setContenuExclu(String contenuExclu) {
		this.contenuExclu = contenuExclu;
	}



	
}
