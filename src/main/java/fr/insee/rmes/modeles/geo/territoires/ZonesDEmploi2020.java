package fr.insee.rmes.modeles.geo.territoires;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.territoire.ZoneDEmploi2020;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "ZonesDEmploi2020")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Zones d'emploi", description = "Tableau représentant les zones d'emploi")
public class ZonesDEmploi2020 extends Territoires {

    private List<ZoneDEmploi2020> zonesDEmploi2020 = null;

    public ZonesDEmploi2020() {}

    public ZonesDEmploi2020(List<ZoneDEmploi2020> zonesDEmploi2020) {
        this.zonesDEmploi2020 = zonesDEmploi2020;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "ZoneDEmploi2020")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<ZoneDEmploi2020> getZonesEmploi() {
        return zonesDEmploi2020;
    }

    public void setZonesEmploi(List<ZoneDEmploi2020> zonesDEmploi2020) {
        this.zonesDEmploi2020 = zonesDEmploi2020;
    }
}
