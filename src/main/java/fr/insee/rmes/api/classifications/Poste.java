package fr.insee.rmes.api.classifications;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
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
	String contenuLimite;
	String contenuCentral;
	String contenuExclu;
	String noteGenerale;
	
	@JsonInclude(Include.NON_NULL)
	List<Poste> postesEnfants;

	
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


	public void setContenuLimite(String contenuLimite) {
		this.contenuLimite = contenuLimite;
	}

	public void setContenuCentral(String contenuCentral) {
		this.contenuCentral = contenuCentral;
	}

	public void setContenuExclu(String contenuExclu) {
		this.contenuExclu = contenuExclu;
	}
	
	public void setNoteGenerale(String noteGenerale) {
		this.noteGenerale = noteGenerale;
	}

	@JacksonXmlProperty(isAttribute=true, localName="Poste")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Poste> getPostesEnfants() {
		return postesEnfants;
	}

	public void setPostesEnfants(List<Poste> postesEnfants) {
		this.postesEnfants = postesEnfants;
	}
	
	public void addPosteEnfant(Poste posteEnfant) {
		if (postesEnfants == null) postesEnfants = new ArrayList<>();
		postesEnfants.add(posteEnfant);
	}

}
