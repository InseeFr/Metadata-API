package fr.insee.rmes.modeles.classification;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Activites")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Tableau représentant des activités")
public class Activites {

    private List<Activite> activitesList = null;

    public Activites() {} // No-args constructor needed for JAXB

    public Activites(List<Activite> activites) {
        this.activitesList = activites;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "Activite")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Activite> getActivites() {
        return activitesList;
    }

    public void setActivites(List<Activite> activites) {
        this.activitesList = activites;
    }

}
