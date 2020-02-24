package fr.insee.rmes.modeles.geo.territoires;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.territoire.CommuneDeleguee;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "CommunesDeleguees")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "CommunesDeleguees", description = "Tableau représentant les communes déléguées")
public class CommunesDeleguees {

    private List<CommuneDeleguee> communesDeleguees = null;

    public CommunesDeleguees() {}

    public CommunesDeleguees(List<CommuneDeleguee> communesDeleguees) {
        this.communesDeleguees = communesDeleguees;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "CommuneDeleguee")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<CommuneDeleguee> getCommunesDeleguees() {
        return communesDeleguees;
    }

    public void setCommunesDeleguees(List<CommuneDeleguee> communesDeleguees) {
        this.communesDeleguees = communesDeleguees;
    }

}
