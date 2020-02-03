package fr.insee.rmes.modeles.geo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Territoires")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Territoires", description = "Tableau représentant les territoires")
public class Territoires {

    private List<Territoire> territoires = null;

    public Territoires() {}

    public Territoires(List<Territoire> territoires) {
        this.territoires = territoires;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "Commune")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Territoire> getTerritoires() {
        return territoires;
    }

    public void setTerritoires(List<Territoire> territoires) {
        this.territoires = territoires;
    }

}
