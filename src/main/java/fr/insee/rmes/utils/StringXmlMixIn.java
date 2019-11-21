package fr.insee.rmes.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public abstract class StringXmlMixIn {

    @JsonCreator
    public StringXmlMixIn(@JsonProperty("contenu") String string, @JsonProperty("langue") String lang) {}

    @JsonProperty("contenu")
    @JsonRawValue
    abstract String getString();

    @JacksonXmlProperty(isAttribute = true)
    @JsonProperty("langue")
    abstract String getLang();

}
