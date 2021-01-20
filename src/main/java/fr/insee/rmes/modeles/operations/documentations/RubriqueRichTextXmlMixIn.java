package fr.insee.rmes.modeles.operations.documentations;

import java.util.List;

import javax.xml.bind.annotation.XmlValue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public abstract class RubriqueRichTextXmlMixIn {

    @JsonCreator
    public RubriqueRichTextXmlMixIn(@JsonProperty("label") String string, @JsonProperty("langue") String lang, List<Document> documents) {}

    @JacksonXmlText
    @XmlValue
    abstract String getString();

    @JacksonXmlProperty(isAttribute = true)
    @JsonProperty("langue")
    abstract String getLang();
    
    abstract List<Document> getDocuments();

}
