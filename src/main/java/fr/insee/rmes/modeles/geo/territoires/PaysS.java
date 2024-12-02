package fr.insee.rmes.modeles.geo.territoires;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.insee.rmes.modeles.geo.territoire.Pays;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "Countries")
@JacksonXmlRootElement(localName = "Countries")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Liste des pays", description = "Tableau représentant la liste des pays")
public class PaysS extends Territoires {

    private List<Pays> listePays = null;

    public PaysS() {}

    public PaysS(List<Pays> listePays) {
        this.listePays = listePays;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "Liste des pays")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Pays> getListePays() {
        return listePays;
    }

    public void setListePays(List<Pays> listePays) {
        this.listePays = listePays;
    }
}
