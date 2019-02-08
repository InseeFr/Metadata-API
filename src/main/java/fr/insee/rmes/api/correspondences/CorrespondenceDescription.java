package fr.insee.rmes.api.correspondences;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="CorrespondenceDescription")
@JsonInclude(JsonInclude.Include.NON_EMPTY) 
@XmlAccessorType(XmlAccessType.FIELD)
public class CorrespondenceDescription {

	String idTableCorrespondance;
	String idNomclatureSource;
	String idNomclatureCible;
	String uriTableCorrespondance;
	String intituleFr;
	String intituleEn;
	String descriptionFr;
	String descriptionEn;

	public CorrespondenceDescription() {

	}

	@JacksonXmlProperty(isAttribute = true)
	public String getidTableCorrespondance() {
		return idTableCorrespondance;
	}

	public void setidTableCorrespondance(String idTableCorrespondance) {
		this.idTableCorrespondance = idTableCorrespondance;
	}

	@JacksonXmlProperty(isAttribute = true)
	public String getIdNomclatureSource() {
		return idNomclatureSource;
	}

	public void setIdNomclatureSource(String idNomclatureSource) {
		this.idNomclatureSource = idNomclatureSource;
	}

	@JacksonXmlProperty(isAttribute = true)
	public String getIdNomclatureCible() {
		return idNomclatureCible;
	}

	public void setIdNomclatureCible(String idNomclatureCible) {
		this.idNomclatureCible = idNomclatureCible;
	}

	@JacksonXmlProperty(isAttribute = true)
	public String geturiTableCorrespondance() {
		return uriTableCorrespondance;
	}

	public void seturiTableCorrespondance(String uriTableCorrespondance) {
		this.uriTableCorrespondance = uriTableCorrespondance;
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
