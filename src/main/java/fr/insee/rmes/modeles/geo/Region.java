package fr.insee.rmes.modeles.geo;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Region")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant une région")
public class Region {

    @Schema(example = "27")
    private String code = null;
    @Schema(example = "http://id.insee.fr/geo/region/27")
    private String uri = null;
    @Schema(example = "Bourgogne-Franche-Comté")
    private String intitule = null;
    private EnumTypeGeographie type = EnumTypeGeographie.REGION;
    @Schema(description = "Date de création de la région si elle n’existait pas au premier COG du 1er janvier 1943")
    private Date dateCreation = null;
    @Schema(description = "Date de suppression de la région si elle a été supprimée. ")
    private Date dateSuppression = null;
    private IntituleGeoGraphieSansArticle intituleSansArticle;
    @Schema(example = "Code Insee de la commune préfecture de la région")
    private Commune chefLieu = null;

    public Region() {} // No-args constructor needed for JAXB

    public Region(String code) {
        this.code = code;
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @JacksonXmlProperty(localName = "Intitule")
    @JsonProperty(value = "intitule")
    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public EnumTypeGeographie getType() {
        return type;
    }

    public void setType(EnumTypeGeographie type) {
        this.type = type;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateSuppression() {
        return dateSuppression;
    }

    public void setDateSuppression(Date dateSuppression) {
        this.dateSuppression = dateSuppression;
    }

    @JacksonXmlElementWrapper(useWrapping = false)
    public IntituleGeoGraphieSansArticle getIntituleSansArticle() {
        return intituleSansArticle;
    }

    public void setIntituleSansArticle(IntituleGeoGraphieSansArticle intituleSansArticle) {
        this.intituleSansArticle = intituleSansArticle;
    }

    public Commune getChefLieu() {
        return chefLieu;
    }

    public void setChefLieu(Commune chefLieu) {
        this.chefLieu = chefLieu;
    }

}
