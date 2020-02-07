package fr.insee.rmes.modeles.geo.territoire;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Departement")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant un departement")
public class Departement extends Territoire {

    // No-args constructor needed for JAXB
    public Departement() {
        super();
        this.type = EnumTypeGeographie.DEPARTEMENT.getTypeObjetGeo();
    }

    public Departement(String code) {
        super();
        this.type = EnumTypeGeographie.DEPARTEMENT.getTypeObjetGeo();
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "22")
    public String getCode() {
        return code;
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "http://id.insee.fr/geo/departement/22")
    public String getUri() {
        return uri;
    }

    @Override
    @JacksonXmlProperty(localName = "Intitule")
    @JsonProperty(value = "intitule")
    @Schema(example = "Côtes-du-Nord")
    public String getIntitule() {
        return intitule;
    }

    @Override
    @JacksonXmlProperty(localName = "Type")
    @Schema(example = "Departement")
    public String getType() {
        return type;
    }

    @Override
    @JacksonXmlProperty(localName = "DateCreation")
    @Schema(
        description = "Date de création du département si elle n’existait pas au premier COG du 1er janvier 1943",
        example = "1943-01-01")
    public String getDateCreation() {
        return dateCreation;
    }

    @Override
    @JacksonXmlProperty(localName = "DateSuppression")
    @Schema(description = "Date de suppression du département si elle a été supprimée. ", example = "2019-01-01")
    public String getDateSuppression() {
        return dateSuppression;
    }

    @Override
    @JacksonXmlProperty(localName = "ChefLieu")
    @Schema(description = "Code Insee de la commune préfecture du département.")
    public String getChefLieu() {
        return chefLieu;
    }

}
