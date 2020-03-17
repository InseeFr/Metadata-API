package fr.insee.rmes.modeles.geo.territoires;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.territoire.Region;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Regions")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Regions", description = "Tableau représentant les régions")
public class Regions extends Territoires{

    private List<Region> region = null;

    public Regions() {

    }

    public Regions(List<Region> region) {
        this.region = region;
    }

    @JsonUnwrapped
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Region")
    public List<Region> getRegion() {
        return region;
    }

    public void setRegion(List<Region> region) {
        this.region = region;
    }
}
