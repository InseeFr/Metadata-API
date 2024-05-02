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

@XmlRootElement(name = "ZoneDEmploi2020")
@JacksonXmlRootElement(localName = "ZoneDEmploi2020")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant une zone d'emploi")
public class ZoneDEmploi2020 extends Territoire {

	  // No-args constructor needed for JAXB
    public ZoneDEmploi2020() {
        this.type = EnumTypeGeographie.ZONE_EMPLOI.getTypeObjetGeo();
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public ZoneDEmploi2020(String code) {
        this.type = EnumTypeGeographie.ZONE_EMPLOI.getTypeObjetGeo();
        this.code = code;
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public ZoneDEmploi2020(
        String code,
        String uri,
        String intitule,
        String type,
        String dateCreation,
        String dateSuppression,
        IntituleSansArticle intituleSansArticle,
        String typeArticle) {
        super(code, uri, intitule, type, dateCreation, dateSuppression, intituleSansArticle);
        this.setTypeArticle(typeArticle);
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "2415")
    public String getCode() {
        return code;
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "http://id.insee.fr/geo/ZoneDEmploi2020/2415")
    public String getUri() {
        return uri;
    }

    @Override
    @JacksonXmlProperty(localName = "Intitule")
    @JsonProperty(value = "intitule")
    @Schema(example = "Vierzon")
    public String getIntitule() {
        return intitule;
    }

    @Override
    @JacksonXmlProperty(localName = "Type")
    @Schema(example = "Zone d'emploi")
    public String getType() {
        return type;
    }

    @Override
    @JacksonXmlProperty(localName = "DateCreation")
    @Schema(
        description = "Date de création de la zone d'emploi si elle n’existait pas au premier COG du 1er janvier 1943",
        example = "1943-01-01")
    public String getDateCreation() {
        return dateCreation;
    }

    @Override
    @JacksonXmlProperty(localName = "DateSuppression")
    @Schema(description = "Date de suppression de la zone d'emploi si elle a été supprimée. ", example = "2019-01-01")
    public String getDateSuppression() {
        return dateSuppression;
    }


}
