package fr.insee.rmes.modeles;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.insee.rmes.utils.Lang;
import io.swagger.v3.oas.annotations.media.Schema;
@Schema(name = "TexteMultiLangue", description = "TexteMultiLangue")
public class StringWithLang {
    @Schema(example = "Texte en français")
    @JsonProperty("contenu")
    private String string = null;
    
    @Schema(example = "fr")
    @JsonProperty("langue")
    private Lang lang = null;

    public String getString() {
        return string;
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

}
