package fr.insee.rmes.modeles.geo.territoires;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.territoire.CommuneDeleguee;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "CommunesAssociees")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "CommunesAssociees", description = "Tableau représentant les communes associées")
public class CommunesAssociees {

    private List<CommuneDeleguee> communesAssociees = null;

    public CommunesAssociees() {}

    public CommunesAssociees(List<CommuneDeleguee> communesAssociees) {
        this.communesAssociees = communesAssociees;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "CommuneDeleguee")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<CommuneDeleguee> getCommunesAssociees() {
        return communesAssociees;
    }

    public void setCommunesAssociees(List<CommuneDeleguee> communesAssociees) {
        this.communesAssociees = communesAssociees;
    }

}
