package fr.insee.rmes.modeles.operations;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Familles")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant l'arborescence des opérations statistiques")
public class Familles {

    List<Famille> families = null;

    public Familles() {}

    public Familles(List<Famille> families) {
        this.families = families;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "Famille")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Famille> getFamilies() {
        return families;
    }

    public void setFamilies(List<Famille> families) {
        this.families = families;
    }

}
