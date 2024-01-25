package fr.insee.rmes.modeles.geo.territoires;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.territoire.CommuneAssociee;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "CommunesAssociees")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "CommunesAssociees", description = "Tableau représentant les communes associées")
public class CommunesAssociees  extends Territoires{

    private List<CommuneAssociee> communesAssociees = null;

    public CommunesAssociees() {}

    public CommunesAssociees(List<CommuneAssociee> communesAssociees) {
        this.communesAssociees = communesAssociees;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "CommuneAssociee")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<CommuneAssociee> getCommunesAssociees() {
        return communesAssociees;
    }

    public void setCommunesAssociees(List<CommuneAssociee> communesAssociees) {
        this.communesAssociees = communesAssociees;
    }

}
