package fr.insee.rmes.modeles.geo.territoires;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.territoire.Intercommunalite;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Intercommunalites")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Intercommunalites", description = "Tableau représentant les intercommunalités")

public class Intercommunalites extends Territoires {
	
	private List<Intercommunalite> intercommunalites = null;

    public Intercommunalites() {}

    public Intercommunalites(List<Intercommunalite> intercommunalites) {
        this.intercommunalites = intercommunalites;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "Intercommunalite")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Intercommunalite> getIntercommunalites() {
        return intercommunalites;
    }

    public void setIntercommunalites(List<Intercommunalite> intercommunalites) {
        this.intercommunalites = intercommunalites;
    }

}
