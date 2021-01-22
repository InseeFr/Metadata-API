package fr.insee.rmes.modeles.geo.territoires;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.territoire.ZoneEmploi;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Zones d'emplois")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Zones d'emploi", description = "Tableau représentant les zones d'emploi")
public class ZonesEmploi extends Territoires {

    private List<ZoneEmploi> zonesEmploi = null;

    public ZonesEmploi() {}

    public ZonesEmploi(List<ZoneEmploi> zonesEmploi) {
        this.zonesEmploi = zonesEmploi;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "Commune")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<ZoneEmploi> getZonesEmploi() {
        return zonesEmploi;
    }

    public void setZonesEmploi(List<ZoneEmploi> zonesEmploi) {
        this.zonesEmploi = zonesEmploi;
    }
}
