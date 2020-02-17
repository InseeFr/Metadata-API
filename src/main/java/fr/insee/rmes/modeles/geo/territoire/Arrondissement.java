package fr.insee.rmes.modeles.geo.territoire;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Arrondissement")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant un arrondissement")
public class Arrondissement extends Territoire {

    // No-args constructor needed for JAXB
    public Arrondissement() {
        super();
        this.type = EnumTypeGeographie.ARRONDISSEMENT.getTypeObjetGeo();
    }

    public Arrondissement(String code) {
        super();
        this.type = EnumTypeGeographie.ARRONDISSEMENT.getTypeObjetGeo();
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "591")
    public String getCode() {
        return code;
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "http://id.insee.fr/geo/arrondissement/591")
    public String getUri() {
        return uri;
    }

    @Override
    @JacksonXmlProperty(localName = "Intitule")
    @JsonProperty(value = "intitule")
    @Schema(example = "Avesnes-sur-Helpe")
    public String getIntitule() {
        return intitule;
    }

    @Override
    @JacksonXmlProperty(localName = "Type")
    @Schema(example = "Arrondissement")
    public String getType() {
        return type;
    }

    @Override
    @JacksonXmlProperty(localName = "DateCreation")
    @Schema(
        description = "Date de création de l'arrondissement si elle n’existait pas au premier COG du 1er janvier 1943",
        example = "1943-01-01")
    public String getDateCreation() {
        return dateCreation;
    }

    @Override
    @JacksonXmlProperty(localName = "DateSuppression")
    @Schema(description = "Date de suppression de l'arrondissement si elle a été supprimée. ", example = "2019-01-01")
    public String getDateSuppression() {
        return dateSuppression;
    }

    @Override
    @JacksonXmlProperty(localName = "ChefLieu")
    @Schema(description = "Code Insee de la commune sous-préfecture de l’arrondissement.")
    public String getChefLieu() {
        return chefLieu;
    }

}
