package fr.insee.rmes.modeles.geo;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Commune")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant une commune")
public class Commune {

    @Schema(example = "55323")
    private String code = null;
    @Schema(example = "http://id.insee.fr/geo/commune/55323")
    private String uri = null;
    @Schema(example = "L'Aigle")
    private String intitule = null;
    private EnumTypeGeographie type = EnumTypeGeographie.COMMUNE;
    @Schema(description = "Date de création de la commune si elle n’existait pas au premier COG du 1er janvier 1943")
    @JsonInclude(Include.NON_NULL)
    private Date dateCreation = null;
    @Schema(description = "Date de suppression de la commune si elle a été supprimée. ")
    @JsonInclude(Include.NON_NULL)
    private Date dateSuppression = null;
    private IntituleSansArticle intituleSansArticle;

    public Commune() {} // No-args constructor needed for JAXB

    public Commune(String code) {
        this.code = code;
        this.intituleSansArticle = new IntituleSansArticle();
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

    @JsonProperty("intituleSansArticle")
    @JacksonXmlProperty(isAttribute = true, localName = "IntituleSansArticle")
    @JacksonXmlElementWrapper(useWrapping = false)
    public IntituleSansArticle getIntituleSansArticle() {
        return intituleSansArticle;
    }

    public void setIntituleSansArticle(IntituleSansArticle intituleSansArticle) {
        this.intituleSansArticle = intituleSansArticle;
    }

    public void setIntituleSansArticle(String intituleSansArticle) {
        this.intituleSansArticle.setIntituleSansArticle(intituleSansArticle);
    }

    public void setTypeArticle(String typeArticle) {
        this.intituleSansArticle.setTypeArticle(typeArticle);
    }

}
