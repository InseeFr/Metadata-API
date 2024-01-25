package fr.insee.rmes.modeles;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAttribute;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import fr.insee.rmes.utils.Lang;
import io.swagger.v3.oas.annotations.media.Schema;
@Schema(name = "TexteMultiLangue", description = "TexteMultiLangue")
public class StringWithLang {
    @Schema(example = "Texte en français")
    @JsonProperty("contenu")
    private String string = null;
    
    @Schema(example = "fr")
    @JsonProperty("langue")
    @XmlAttribute
    @JacksonXmlProperty(isAttribute = true, localName = "xmllang")
    private Lang lang = null;

    public String getString() {
		if (string == null) return null;
		else return new String(string.getBytes(), StandardCharsets.UTF_8);
    }

    public StringWithLang(String string, Lang lang) {
        super();
        this.string = string;
        this.lang = lang;
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

	@Override
	public int hashCode() {
		return Objects.hash(lang, string);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StringWithLang other = (StringWithLang) obj;
		return lang == other.lang && Objects.equals(string, other.string);
	}

}
