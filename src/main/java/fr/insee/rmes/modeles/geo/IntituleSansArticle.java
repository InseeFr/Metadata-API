package fr.insee.rmes.modeles.geo;

import io.swagger.v3.oas.annotations.media.Schema;

public class IntituleSansArticle {

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

    public String getTypeArticle() {
        return typeArticle;
    }

    public void setTypeArticle(String typeArticle) {
        this.typeArticle = typeArticle;
    }

}
