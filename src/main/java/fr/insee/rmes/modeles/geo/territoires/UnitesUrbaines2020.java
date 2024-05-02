package fr.insee.rmes.modeles.geo.territoires;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.territoire.UniteUrbaine2020;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "UnitesUrbaines2020")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Unités urbaines", description = "Tableau représentant les unités urbaines")
public class UnitesUrbaines2020 extends Territoires {

    private List<UniteUrbaine2020> unitesUrbaines2020 = null;

    public UnitesUrbaines2020() {}

    public UnitesUrbaines2020(List<UniteUrbaine2020> unitesUrbaines2020) {
        this.unitesUrbaines2020 = unitesUrbaines2020;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "UniteUrbaine2020")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<UniteUrbaine2020> getUnitesUrbaines() {
        return unitesUrbaines2020;
    }

    public void setUnitesUrbaines(List<UniteUrbaine2020> unitesUrbaines2020) {
        this.unitesUrbaines2020 = unitesUrbaines2020;
    }
}
