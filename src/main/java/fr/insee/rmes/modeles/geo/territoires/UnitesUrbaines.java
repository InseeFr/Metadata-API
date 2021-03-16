package fr.insee.rmes.modeles.geo.territoires;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.territoire.UniteUrbaine;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Unités urbaines")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Unités urbaines", description = "Tableau représentant les unités urbaines")
public class UnitesUrbaines extends Territoires {

    private List<UniteUrbaine> unitesUrbaines = null;

    public UnitesUrbaines() {}

    public UnitesUrbaines(List<UniteUrbaine> unitesUrbaines) {
        this.unitesUrbaines = unitesUrbaines;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "UniteUrbaine")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<UniteUrbaine> getUnitesUrbaines() {
        return unitesUrbaines;
    }

    public void setUnitesUrbaines(List<UniteUrbaine> unitesUrbaines) {
        this.unitesUrbaines = unitesUrbaines;
    }
}
