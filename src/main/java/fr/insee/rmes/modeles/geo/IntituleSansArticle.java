package fr.insee.rmes.modeles.geo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="Intitulé sans son article et article associé")
public class IntituleSansArticle {

    @Schema(example = "Aigle")
    private String intituleSansArticle = null;

    @Schema(example = "5")
    private String typeArticle = null;

    
    @JacksonXmlText
    public String getIntituleSansArticle() {
        return intituleSansArticle;
    }

    public void setIntituleSansArticle(String intituleSansArticle) {
        this.intituleSansArticle = intituleSansArticle;
    }

    @JacksonXmlProperty(localName = "TypeArticle", isAttribute = true)
    @JsonProperty("typeArticle")
    public String getTypeArticle() {
        return typeArticle;
    }

    public void setTypeArticle(String typeArticle) {
        this.typeArticle = typeArticle;
    }

}
