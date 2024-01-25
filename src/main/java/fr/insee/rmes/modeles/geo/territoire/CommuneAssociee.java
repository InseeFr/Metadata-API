package fr.insee.rmes.modeles.geo.territoire;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;
import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "CommuneAssociee")
@JacksonXmlRootElement(localName = "CommuneAssociee")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant une commune associee")
public class CommuneAssociee extends Territoire {

    // No-args constructor needed for JAXB
    public CommuneAssociee() {
        this.type = EnumTypeGeographie.COMMUNE_ASSOCIEE.getTypeObjetGeo();
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public CommuneAssociee(String code) {
        this.type = EnumTypeGeographie.COMMUNE_ASSOCIEE.getTypeObjetGeo();
        this.code = code;
        this.intituleSansArticle = new IntituleSansArticle();
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "14463")
    public String getCode() {
        return code;
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "http://id.insee.fr/geo/communeAssociee/14463")
    public String getUri() {
        return uri;
    }

    @Override
    @JacksonXmlProperty(localName = "Intitule")
    @JsonProperty(value = "intitule")
    @Schema(example = "Neuilly-le-Malherbe")
    public String getIntitule() {
        return intitule;
    }

    @Override
    @JacksonXmlProperty(localName = "Type")
    @Schema(example = "Commune Associee")
    public String getType() {
        return type;
    }

    @Override
    @JacksonXmlProperty(localName = "DateCreation")
    @Schema(
        description = "Date de création de la commune si elle n’existait pas au premier COG du 1er janvier 1943",
        example = "1943-01-01")
    public String getDateCreation() {
        return dateCreation;
    }

    @Override
    @JacksonXmlProperty(localName = "DateSuppression")
    @Schema(description = "Date de suppression de la commune si elle a été supprimée. ", example = "2019-01-01")
    public String getDateSuppression() {
        return dateSuppression;
    }

    @Override
    @JacksonXmlProperty(localName = "IntituleSansArticle")
    public IntituleSansArticle getIntituleSansArticle() {
        return intituleSansArticle;
    }
}
