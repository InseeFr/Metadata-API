package fr.insee.rmes.api.correspondences;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

//@JacksonXmlRootElement(localName="CorrespondenceDescription")
@JsonInclude(JsonInclude.Include.NON_EMPTY) 
@XmlAccessorType(XmlAccessType.FIELD)
public class Correspondence {

	String id;
	String idSource;
	String idCible;
	String uri;
	String intituleFr;
	String intituleEn;
	String descriptionFr;
	String descriptionEn;

	public Correspondence() {

	}

	@JacksonXmlProperty(isAttribute = true)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JacksonXmlProperty(isAttribute = true)
	public String getIdSource() {
		return idSource;
	}

	public void setIdSource(String idSource) {
		this.idSource = idSource;
	}

	@JacksonXmlProperty(isAttribute = true)
	public String getIdCible() {
		return idCible;
	}

	public void setIdCible(String idCible) {
		this.idCible = idCible;
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

	public void setIntituleEn(String intituleEn) {
		this.intituleEn = intituleEn;
	}

	@JacksonXmlProperty(localName = "DescriptionFr")
	public String getDescriptionFr() {
		return descriptionFr;
	}

	public void setDescriptionFr(String descriptionFr) {
		this.descriptionFr = descriptionFr;
	}
	
	@JacksonXmlProperty(localName = "DescriptionEn")
	public String getDescriptionEn() {
		return descriptionEn;
	}

	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}
	
	
	
	

}
