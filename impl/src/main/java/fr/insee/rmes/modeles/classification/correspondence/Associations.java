package fr.insee.rmes.modeles.classification.correspondence;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Associations")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Tableau représentant la liste des associations entre deux nomenclatures")
public class Associations {

    @JsonProperty("associations")
    @JacksonXmlProperty(localName = "Association")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Association> associations = new ArrayList<>();

    public Associations() {
    	// No-args constructor needed for JAXB
    }

    public List<Association> getAssociations() {
        return associations;
    }

    public void setAssociations(List<Association> associations) {
        this.associations = associations;
    }

}
