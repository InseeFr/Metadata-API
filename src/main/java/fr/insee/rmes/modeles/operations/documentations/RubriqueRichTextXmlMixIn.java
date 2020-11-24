package fr.insee.rmes.modeles.operations.documentations;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public abstract class RubriqueRichTextXmlMixIn {

    @JsonCreator
    public RubriqueRichTextXmlMixIn(@JsonProperty("label") String string, @JsonProperty("langue") String lang, List<Document> documents) {}

	 @JsonProperty("label")
	 @JsonRawValue
    abstract String getString();

    @JacksonXmlProperty(isAttribute = true)
    @JsonProperty("langue")
    abstract String getLang();
    
    abstract List<Document> getDocuments();

}
