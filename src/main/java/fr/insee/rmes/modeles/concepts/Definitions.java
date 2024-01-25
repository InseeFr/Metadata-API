package fr.insee.rmes.modeles.concepts;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Definitions")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Definitions", description = "Tableau représentant des définitions de concepts statistiques de l'Insee")
public class Definitions {

    private List<Definition> definitions = null;

    public Definitions() {}

    public Definitions(List<Definition> definitions) {
        this.definitions = definitions;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "Definition")
    @JacksonXmlElementWrapper(useWrapping = false, localName = "Definition")
    public List<Definition> getConcepts() {
        return definitions;
    }

    public void setConcepts(List<Definition> definitions) {
        this.definitions = definitions;
    }

}
