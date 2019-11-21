package fr.insee.rmes.modeles.classification.correspondence;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Associations")
@XmlAccessorType(XmlAccessType.FIELD)
public class Associations {

    @JsonProperty("associations")
    @JacksonXmlProperty(localName = "Association")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Association> associations = new ArrayList<Association>();

    public Associations() {

    }

    public List<Association> getAssociations() {
        return associations;
    }

    public void setAssociations(List<Association> associations) {
        this.associations = associations;
    }

}
