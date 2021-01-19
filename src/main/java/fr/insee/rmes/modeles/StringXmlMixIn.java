package fr.insee.rmes.modeles;

import javax.xml.bind.annotation.XmlValue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public abstract class StringXmlMixIn {

    @JsonCreator
    public StringXmlMixIn(String string, @JsonProperty("langue") String lang) {}

    //@JsonProperty("contenu")
    //@JsonRawValue
    @JacksonXmlText
    @XmlValue
    abstract String getString();

    @JacksonXmlProperty(isAttribute = true)
    @JsonProperty("langue")
    abstract String getLang();

}
