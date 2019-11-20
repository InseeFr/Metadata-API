package fr.insee.rmes.modeles.classification;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.api.utils.DateUtils;

@JacksonXmlRootElement(localName="Activite")
@XmlAccessorType(XmlAccessType.FIELD)
public class Activite {

	private String code = null;
	private String uri = null;
	private String intitule = null;
	private String issued = null;
	private String valid = null;

	public Activite() {} // No-args constructor needed for JAXB

	public Activite(String code) {
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
	
	@JacksonXmlProperty(localName="DateDebutValidite")
	@JsonProperty(value="dateDebutValidite")
	public String getIssued() {
		return issued;
	}

	public void setIssued(String issued) {
		this.issued = DateUtils.getDateStringFromDateTimeString(issued);
	}
	
	@JacksonXmlProperty(localName="DateFinValidite")
	@JsonProperty(value="dateFinValidite")
	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = DateUtils.getDateStringFromDateTimeString(valid);
	}
}
