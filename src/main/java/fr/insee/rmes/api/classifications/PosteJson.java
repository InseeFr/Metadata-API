package fr.insee.rmes.api.classifications;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Poste")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_EMPTY) 
public class PosteJson extends Poste {

	
	@JacksonXmlProperty(localName="ContenuLimite")
	public String getContenuLimite() {
		return contenuLimite;
	}

	@JacksonXmlProperty(localName="ContenuCentral")
	public String getContenuCentral() {
		return contenuCentral;
	}

	@JacksonXmlProperty(localName="ContenuLimite")
	public String getContenuExclu() {
		return contenuExclu;
	}
	
	@JacksonXmlProperty(localName="NoteGenerale")
	public String getNoteGenerale() {
		return noteGenerale;
	}	

}
