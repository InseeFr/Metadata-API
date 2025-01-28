package fr.insee.rmes.modeles.concepts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public abstract class StringXmlMixInConcept {

    @JsonCreator
    public StringXmlMixInConcept(String string, String lang) {}

    @JsonProperty("contenu")
    @JsonRawValue
    abstract String getString();

    @JacksonXmlProperty(isAttribute = true)
    @JsonProperty("langue")
    abstract String getLang();

}
