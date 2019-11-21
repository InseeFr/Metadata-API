package fr.insee.rmes.modeles.concepts;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Definitions")
@XmlAccessorType(XmlAccessType.FIELD)
public class Definitions {

    private List<Definition> definitions = null;

    public Definitions() {}

    public Definitions(List<Definition> definitions) {
        this.definitions = definitions;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "Definition")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Definition> getConcepts() {
        return definitions;
    }

    public void setConcepts(List<Definition> definitions) {
        this.definitions = definitions;
    }

}
