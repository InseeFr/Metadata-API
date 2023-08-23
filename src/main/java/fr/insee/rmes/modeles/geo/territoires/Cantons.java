package fr.insee.rmes.modeles.geo.territoires;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.insee.rmes.modeles.geo.territoire.Canton;
import fr.insee.rmes.modeles.geo.territoire.Commune;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@JacksonXmlRootElement(localName = "Cantons")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Cantons", description = "Tableau représentant les cantons")
public class Cantons  extends Territoires{
    private List<Canton> cantons = null;

    public Cantons() {}

    public Cantons(List<Canton> cantons) {
        this.cantons = cantons;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "Canton")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Canton> getCantons() {
        return cantons;
    }

    public void setCantons(List<Canton> cantons) {
        this.cantons = cantons;
    }

}
