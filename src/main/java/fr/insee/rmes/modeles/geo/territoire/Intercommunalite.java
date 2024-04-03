package fr.insee.rmes.modeles.geo.territoire;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;
import io.swagger.v3.oas.annotations.media.Schema;


@XmlRootElement(name = "Intercommunalite")
@JacksonXmlRootElement(localName = "Intercommunalite")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant une intercommunalite")

public class Intercommunalite extends Territoire {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @XmlElement(name="IntituleComplet")
    private String intituleComplet = null;

    public Intercommunalite(String code) {
        this();
        this.code = code;

    }

    public Intercommunalite() {
        this.type = EnumTypeGeographie.INTERCOMMUNALITE.getTypeObjetGeo();
        this.intituleSansArticle = new IntituleSansArticle();
    }

    @JacksonXmlProperty(localName = "IntituleComplet")
    public String getIntituleComplet() {
        return intituleComplet;
    }

    public void setIntituleComplet(String intituleComplet) {
        this.intituleComplet = intituleComplet;
    }

}
