package fr.insee.rmes.modeles.geo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public abstract class IntituleSansArticleXmlMixIn {

    @JsonCreator
    public IntituleSansArticleXmlMixIn(@JsonProperty("IntituleSansArticle") String intituleSansArticle, @JsonProperty("TypeArticle") String typeArticle) {}


    @JacksonXmlText
    abstract String getIntituleSansArticle();

    @JacksonXmlProperty(isAttribute = true)
    @JsonProperty("TypeArticle")
    abstract String getTypeArticle();

}
