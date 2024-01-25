package fr.insee.rmes.modeles;

import jakarta.xml.bind.annotation.XmlValue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public abstract class StringXmlMixIn {

    @JsonCreator
    public StringXmlMixIn(String string, String lang) {}

    //@JsonProperty("contenu")
    //@JsonRawValue
    @JacksonXmlText
    @XmlValue
    abstract String getString();

    @JacksonXmlProperty(isAttribute = true)
    @JsonProperty("xmllang")
    abstract String getLang();

}
