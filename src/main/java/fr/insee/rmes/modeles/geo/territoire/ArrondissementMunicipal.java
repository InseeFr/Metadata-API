package fr.insee.rmes.modeles.geo.territoire;

import jakarta.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;
import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "ArrondissementMunicipal")
public class ArrondissementMunicipal extends Territoire {
    // No-args constructor needed for JAXB
    public ArrondissementMunicipal() {
        this.type = EnumTypeGeographie.ARRONDISSEMENT_MUNICIPAL.getTypeObjetGeo();
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public ArrondissementMunicipal(String code) {
        this.type = EnumTypeGeographie.ARRONDISSEMENT_MUNICIPAL.getTypeObjetGeo();
        this.code = code;
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public ArrondissementMunicipal(
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
    @Schema(example = "69388")
    public String getCode() {
        return code;
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "http://id.insee.fr/geo/arrondissementMunicipal/0b5aef6b-ee47-4cd0-ad4a-7a745dd62b3f")
    public String getUri() {
        return uri;
    }

    @Override
    @JacksonXmlProperty(localName = "Intitule")
    @JsonProperty(value = "intitule")
    @Schema(example = "Lyon 8e Arrondissement")
    public String getIntitule() {
        return intitule;
    }

    @Override
    @JacksonXmlProperty(localName = "Type")
    @Schema(example = "ArrondissementMunicipal")
    public String getType() {
        return type;
    }

    @Override
    @JacksonXmlProperty(localName = "DateCreation")
    @Schema(
        description = "Date de création de l'arrondissement municipal s'il n’existait pas au premier COG du 1er janvier 1943",
        example = "1943-01-01")
    public String getDateCreation() {
        return dateCreation;
    }

    @Override
    @JacksonXmlProperty(localName = "DateSuppression")
    @Schema(description = "Date de suppression de l'arrondissement municipal s'il a été supprimé. ", example = "2019-01-01")
    public String getDateSuppression() {
        return dateSuppression;
    }

}
