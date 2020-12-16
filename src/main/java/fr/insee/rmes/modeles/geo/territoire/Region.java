package fr.insee.rmes.modeles.geo.territoire;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Region")
@XmlRootElement(name = "Region")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant une région")
public class Region extends Territoire {

    // No-args constructor needed for JAXB
    public Region() {
        this.type = EnumTypeGeographie.REGION.getTypeObjetGeo();
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public Region(String code) {
        this.type = EnumTypeGeographie.REGION.getTypeObjetGeo();
        this.code = code;
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public Region(
        String code,
        String uri,
        String intitule,
        String type,
        String dateCreation,
        String dateSuppression,
        IntituleSansArticle intituleSansArticle,
        String chefLieu) {
        super(code, uri, intitule, type, dateCreation, dateSuppression, intituleSansArticle, chefLieu);
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "27")
    public String getCode() {
        return code;
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "http://id.insee.fr/geo/region/27")
    public String getUri() {
        return uri;
    }

    @Override
    @JacksonXmlProperty(localName = "Intitule")
    @JsonProperty(value = "intitule")
    @Schema(example = "Bourgogne-Franche-Comté")
    public String getIntitule() {
        return intitule;
    }

    @Override
    @JacksonXmlProperty(localName = "Type")
    @Schema(example = "Region")
    public String getType() {
        return type;
    }

    @Override
    @JacksonXmlProperty(localName = "DateCreation")
    @Schema(
        description = "Date de création de la région si elle n’existait pas au premier COG du 1er janvier 1943",
        example = "1943-01-01")
    public String getDateCreation() {
        return dateCreation;
    }

    @Override
    @JacksonXmlProperty(localName = "DateSuppression")
    @Schema(description = "Date de suppression de la région si elle a été supprimée. ", example = "2019-01-01")
    public String getDateSuppression() {
        return dateSuppression;
    }

    @Override
    @JacksonXmlProperty(localName = "ChefLieu")
    @Schema(description = "Code Insee de la commune préfecture de la région")
    public String getChefLieu() {
        return chefLieu;
    }

}
