package fr.insee.rmes.modeles.geo.territoires;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.territoire.CirconscriptionTerritoriale;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "CirconscriptionsTerritoriales")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "CirconscriptionsTerritoriales", description = "Tableau représentant les circonscriptions territoriales")

public class CirconscriptionsTerritoriales extends Territoires {
	
	private List<CirconscriptionTerritoriale> circonscriptionsTerritoriales = null;

    public CirconscriptionsTerritoriales() {}

    public CirconscriptionsTerritoriales(List<CirconscriptionTerritoriale> circonscriptionsTerritoriales) {
        this.circonscriptionsTerritoriales = circonscriptionsTerritoriales;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "CirconscriptionTerritoriale")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<CirconscriptionTerritoriale> getCirconscriptionsTerritoriales() {
        return circonscriptionsTerritoriales;
    }

    public void setCirconscriptionsTerritoriales(List<CirconscriptionTerritoriale> circonscriptionsTerritoriales) {
        this.circonscriptionsTerritoriales = circonscriptionsTerritoriales;
    }

}
