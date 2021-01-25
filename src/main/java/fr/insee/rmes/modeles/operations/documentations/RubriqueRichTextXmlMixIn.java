package fr.insee.rmes.modeles.operations.documentations;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public abstract class RubriqueRichTextXmlMixIn {

    @JsonCreator
    public RubriqueRichTextXmlMixIn(String string, @JsonProperty("xmllang") String lang, List<Document> documents) {}

    @JsonProperty("Texte")
    abstract String getString();

    
    @JacksonXmlProperty(isAttribute = true, localName = "xmllang")
    @JsonProperty("xmllang")
    abstract String getLang();
    
    abstract List<Document> getDocuments();

}
