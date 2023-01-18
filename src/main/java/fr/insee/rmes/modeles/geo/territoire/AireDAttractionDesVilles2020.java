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

@XmlRootElement(name = "AireDAttractionDesVilles2020")
@JacksonXmlRootElement(namespace = "AireDAttractionDesVilles2020")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant une aire d'attraction")
public class AireDAttractionDesVilles2020 extends Territoire {

	  // No-args constructor needed for JAXB

	public AireDAttractionDesVilles2020() {
        this.type = EnumTypeGeographie.AIRE_ATTRACTION.getTypeObjetGeo();
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public AireDAttractionDesVilles2020(String code) {
        this.type = EnumTypeGeographie.AIRE_ATTRACTION.getTypeObjetGeo();
        this.code = code;
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public AireDAttractionDesVilles2020(
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
    @Schema(example = "062")
    public String getCode() {
        return code;
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "http://id.insee.fr/geo/aireDAttractionDesVilles2020/062")
    public String getUri() {
        return uri;
    }

    @Override
    @JacksonXmlProperty(localName = "Intitule")
    @JsonProperty(value = "intitule")
    @Schema(example = "Angoulême")
    public String getIntitule() {
        return intitule;
    }

    @Override
    @JacksonXmlProperty(localName = "Type")
    @Schema(example = "Aire d'attraction")
    public String getType() {
        return type;
    }

    @Override
    @JacksonXmlProperty(localName = "DateCreation")
    @Schema(
        description = "Date de création de l'aire d'attraction si elle n’existait pas au premier COG du 1er janvier 1943",
        example = "1943-01-01")
    public String getDateCreation() {
        return dateCreation;
    }

    @Override
    @JacksonXmlProperty(localName = "DateSuppression")
    @Schema(description = "Date de suppression de l'aire d'attraction si elle a été supprimée. ", example = "2019-01-01")
    public String getDateSuppression() {
        return dateSuppression;
    }


}
