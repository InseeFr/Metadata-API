package fr.insee.rmes.modeles.geo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class IntituleGeoGraphieSansArticle {

    @Schema(example = "Aigle")
    private String intituleSansArticle = null;

    @Schema(example = "5")
    private String typeArticle = null;

    //@JacksonXmlElementWrapper(useWrapping = false)
    public String getIntituleSansArticle() {
        return intituleSansArticle;
    }

    public void setIntituleSansArticle(String intituleSansArticle) {
        this.intituleSansArticle = intituleSansArticle;
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getTypeArticle() {
        return typeArticle;
    }

    public void setTypeArticle(String typeArticle) {
        this.typeArticle = typeArticle;
    }

}
