package fr.insee.rmes.api.classifications;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@XmlRootElement(name = "Poste")
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel(value = "ClassificationItem", description = "Objet représentant un poste d'une nomenclature")
@JsonInclude(JsonInclude.Include.NON_EMPTY) 
public class Poste {

	String uri;
	String code;
	String uriParent;
	String codeParent;
	String intituleFr;
	String intituleEn;
	String contenuLimite;
	String contenuCentral;
	String exclusions;
	
	
	
	public Poste() {
	} // No-args constructor needed for JAXB

	@JacksonXmlProperty(isAttribute = true)
	@ApiModelProperty(example = "")
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@JacksonXmlProperty(isAttribute = true)
	@ApiModelProperty(example = "")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@JacksonXmlProperty(isAttribute = true)
	@ApiModelProperty(example = "")
	public String getUriParent() {
		return uriParent;
	}

	public void setUriParent(String uriParent) {
		this.uriParent = uriParent;
	}

	@JacksonXmlProperty(isAttribute = true)
	@ApiModelProperty(example = "")
	public String getCodeParent() {
		return codeParent;
	}

	public void setCodeParent(String codeParent) {
		this.codeParent = codeParent;
	}

	@JacksonXmlProperty(isAttribute = true)
	@ApiModelProperty(example = "")
	public String getIntituleFr() {
		return intituleFr;
	}

	public void setIntituleFr(String intituleFr) {
		this.intituleFr = intituleFr;
	}

	@JacksonXmlProperty(isAttribute = true)
	@ApiModelProperty(example = "")
	public String getIntituleEn() {
		return intituleEn;
	}

	public void setIntituleEn(String intituleEn) {
		this.intituleEn = intituleEn;
	}

	@JacksonXmlProperty(isAttribute = true)
	@ApiModelProperty(example = "")
	public String getContenuLimite() {
		return contenuLimite;
	}

	public void setContenuLimite(String contenuLimite) {
		this.contenuLimite = contenuLimite;
	}

	@JacksonXmlProperty(isAttribute = true)
	@ApiModelProperty(example = "")
	public String getContenuCentral() {
		return contenuCentral;
	}

	public void setContenuCentral(String contenuCentral) {
		this.contenuCentral = contenuCentral;
	}
	@JacksonXmlProperty(isAttribute = true)
	@ApiModelProperty(example = "")
	public String getExclusions() {
		return exclusions;
	}

	public void setExclusions(String exclusions) {
		this.exclusions = exclusions;
	}

	

	
}
