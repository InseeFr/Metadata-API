package fr.insee.rmes.modeles.geo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Communes")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Communes", description = "Tableau représentant les communes")
public class Communes {

    private List<Commune> communes = null;

    public Communes() {}

    public Communes(List<Commune> communes) {
        this.communes = communes;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "Commune")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Commune> getCommunes() {
        return communes;
    }

    public void setCommunes(List<Commune> communes) {
        this.communes = communes;
    }

}
