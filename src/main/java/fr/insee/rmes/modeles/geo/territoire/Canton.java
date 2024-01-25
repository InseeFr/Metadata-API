package fr.insee.rmes.modeles.geo.territoire;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Canton")
@JacksonXmlRootElement(localName = "Canton")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant un canton")
public class Canton extends Territoire {
    public Canton() {
        this.type = EnumTypeGeographie.CANTON.getTypeObjetGeo();
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public Canton(String code) {
        this.type = EnumTypeGeographie.CANTON.getTypeObjetGeo();
        this.code = code;
        this.intituleSansArticle = new IntituleSansArticle();
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "0101")
    public String getCode() {
        return code;
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(description = "URI du canton",example = "http://id.insee.fr/geo/canton/f96a2438-478f-4ebb-b659-434305dff18f")

    public String getUri() {
        return uri;
    }

    @Override
    @JacksonXmlProperty(localName = "Intitule")
    @JsonProperty(value = "intitule")
    @Schema(description = "Nom du canton (avec article)",example = "Ambérieu-en-Bugey")

    public String getIntitule() {
        return intitule;
    }

    @Override
    @JacksonXmlProperty(localName = "IntituleSansArticle")
    @JsonProperty(value = "intituleSansArticle")
    @Schema(description = "Nom du canton sans article",example = "Ambérieu-en-Bugey")
    public IntituleSansArticle getIntituleSansArticle() {
        return intituleSansArticle;
    }

    @Override
    @JacksonXmlProperty(localName = "Type")
    @Schema(example = "Canton")
    public String getType() {
        return type;
    }

    @Override
    @JacksonXmlProperty(localName = "DateCreation")
    @Schema(
            description = "Date de création du canton si il n’existait pas au premier COG du 1er janvier 2016",
            example = "2016-01-01")
    public String getDateCreation() {
        return dateCreation;
    }

    @Override
    @JacksonXmlProperty(localName = "DateSuppression")
    @Schema(description = "Date de suppression du canton si il a été supprimé. ", example = "2019-01-01")

    public String getDateSuppression() {
        return dateSuppression;
    }

    @Override

    @JacksonXmlProperty(localName = "ChefLieu")
    @Schema(description = "Code Insee de la commune bureau centralisateur du canton. \n", example="19031")

    public String getChefLieu() {
        return chefLieu;
    }

}

