package fr.insee.rmes.modeles.operations.documentations;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

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
    
	 @JsonProperty("texte")
	public String getString() {
		if (string == null) return null;
		else return new String(string.getBytes(), StandardCharsets.UTF_8);
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

    @JacksonXmlProperty(localName = "Document")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

	@Override
	public int hashCode() {
		return Objects.hash(documents, lang, string);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RubriqueRichText other = (RubriqueRichText) obj;
		return Objects.equals(documents, other.documents) && lang == other.lang && Objects.equals(string, other.string);
	}


}
