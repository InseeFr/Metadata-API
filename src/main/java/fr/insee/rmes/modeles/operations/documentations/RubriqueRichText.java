package fr.insee.rmes.modeles.operations.documentations;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import fr.insee.rmes.utils.Lang;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonClassDescription("Objet représentant une rubrique texte riche d'une documentation SIMS")
public class RubriqueRichText {


	 @JsonProperty("label")
	 private String string = null;
	    
	 @Schema(example = "fr")
	 @JsonProperty("langue")
	 private Lang lang = null;

    @JsonInclude(Include.NON_NULL)
    private List<Document> documents;
    
    
    public RubriqueRichText(String string, Lang lang) {
		super();
		this.string = string;
		this.lang = lang;
	}

	public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getLang() {
        return lang.getLang();
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }

    @JacksonXmlProperty(localName = "document")
    @JacksonXmlElementWrapper(localName = "documents", useWrapping = true)
    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }


}
