package fr.insee.rmes.api.correspondences;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Correspondence")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ItemCorrespondence {

	String code;
	String uri;
	String intituleFr;
	String intituleEn;

	public ItemCorrespondence(String code, String uri, String intituleFr, String intituleEn) {
		super();
		this.code = code;
		this.uri = uri;
		this.intituleFr = intituleFr;
		this.intituleEn = intituleEn;
	}

	public ItemCorrespondence() {
	};// No-args constructor needed for JAXB

	@JacksonXmlProperty(isAttribute = true)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@JacksonXmlProperty(isAttribute = true)
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@JacksonXmlProperty(localName = "IntituleFr")
	public String getIntituleFr() {
		return intituleFr;
	}

	public void setIntituleFr(String intituleFr) {
		this.intituleFr = intituleFr;
	}

	@JacksonXmlProperty(localName = "IntituleEn")
	public String getIntituleEn() {
		return intituleEn;
	}

	public void setIntituleEnPosteSource(String intituleEn) {
		this.intituleEn = intituleEn;
	}

}