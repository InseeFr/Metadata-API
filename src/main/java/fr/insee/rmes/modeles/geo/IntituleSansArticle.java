package fr.insee.rmes.modeles.geo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="Intitulé sans son article et article associé")
@JsonPropertyOrder({"intituleSansArticle", "typeArticle"})
public class IntituleSansArticle {

    //@Schema(example = "Aigle")
    private String label = null;

    @Schema(example = "5")
    private String typeArticle = null;

    
    @JacksonXmlText
   // @JsonProperty("intituleSansArticle")
    @JacksonXmlProperty(localName="intituleSansArticle")
    @Schema(example = "Aigle")
    public String getIntituleSansArticle() {
        return label;
    }

    public void setIntituleSansArticle(String intituleSansArticle) {
        this.label = intituleSansArticle;
    }

    @JacksonXmlProperty(isAttribute = true)
    @JsonProperty("typeArticle")
    public String getTypeArticle() {
        return typeArticle;
    }

    public void setTypeArticle(String typeArticle) {
        this.typeArticle = typeArticle;
    }

}
