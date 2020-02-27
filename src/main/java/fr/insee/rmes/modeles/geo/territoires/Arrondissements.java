package fr.insee.rmes.modeles.geo.territoires;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.territoire.Arrondissement;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Arrondissements")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Arrondissements", description = "Tableau représentant les arrondissements")
public class Arrondissements {

    private List<Arrondissement> arrondissements = null;

    public Arrondissements() {}

    public Arrondissements(List<Arrondissement> arrondissements) {
        this.arrondissements = arrondissements;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "Arrondissement")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Arrondissement> getArrondissements() {
        return arrondissements;
    }

    public void setArrondissements(List<Arrondissement> arrondissements) {
        this.arrondissements = arrondissements;
    }

}
