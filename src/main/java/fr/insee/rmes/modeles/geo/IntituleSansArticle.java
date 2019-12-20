package fr.insee.rmes.modeles.geo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class IntituleSansArticle {

    @Schema(example = "Aigle")
    private String intituleSansArticle = null;

    @Schema(example = "5")
    @JacksonXmlProperty(isAttribute = true)
    private String typeArticle = null;

    public String getIntituleSansArticle() {
        return intituleSansArticle;
    }

    public void setIntituleSansArticle(String intituleSansArticle) {
        this.intituleSansArticle = intituleSansArticle;
    }

    public String getTypeArticle() {
        return typeArticle;
    }

    public void setTypeArticle(String typeArticle) {
        this.typeArticle = typeArticle;
    }

}
