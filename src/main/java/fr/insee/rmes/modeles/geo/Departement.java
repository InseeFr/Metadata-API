package fr.insee.rmes.modeles.geo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Departement")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant un departement")
public class Departement {

    @Schema(example = "22")
    private String code = null;

    @Schema(example = "http://id.insee.fr/geo/departement/22")
    private String uri = null;

    @Schema(example = "Côtes-du-Nord")
    private String intitule = null;

    @Schema(example = "Departement")
    private EnumTypeGeographie type = EnumTypeGeographie.DEPARTEMENT;

    @Schema(
        description = "Date de création du département si elle n’existait pas au premier COG du 1er janvier 1943",
        example = "1943-01-01")
    @JsonInclude(Include.NON_EMPTY)
    private String dateCreation = null;

    @Schema(description = "Date de suppression du département si elle a été supprimée.", example = "2019-01-01")
    @JsonInclude(Include.NON_EMPTY)
    private String dateSuppression = null;

    private IntituleSansArticle intituleSansArticle;

    @Schema(description = "Code Insee de la commune préfecture du département.")
    private String chefLieu = null;

    // No-args constructor needed for JAXB
    public Departement() {
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public Departement(String code) {
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

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateSuppression() {
        return dateSuppression;
    }

    public void setDateSuppression(String dateSuppression) {
        this.dateSuppression = dateSuppression;
    }

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

    public String getChefLieu() {
        return chefLieu;
    }

    public void setChefLieu(String chefLieu) {
        this.chefLieu = chefLieu;
    }

}
