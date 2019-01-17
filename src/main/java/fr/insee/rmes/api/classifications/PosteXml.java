package fr.insee.rmes.api.classifications;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Poste")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PosteXml extends Poste {

	@JsonRawValue // don't encode XML
	public String getContenuLimite() {
		if (StringUtils.isNotEmpty(contenuLimite)) {
			return "<ContenuLimite>" + contenuLimite + "</ContenuLimite>";
		} else
			return null;
	}

	@JsonRawValue
	public String getContenuCentral() {
		if (StringUtils.isNotEmpty(contenuCentral)) {
			return "<ContenuCentral>" + contenuCentral + "</ContenuCentral>";
		} else
			return null;
	}

	@JsonRawValue
	public String getContenuExclu() {
		if (StringUtils.isNotEmpty(contenuExclu)) {
			return "<ContenuExclu>" + contenuExclu + "</ContenuExclu>";
		} else
			return null;
	}
	
	@JsonRawValue
	public String getNoteGenerale() {
		if (StringUtils.isNotEmpty(noteGenerale)) {
			return "<NoteGenerale>" + noteGenerale + "</NoteGenerale>";
		} else
			return null;
	}
}
