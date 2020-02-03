package fr.insee.rmes.modeles.geo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Arrondissement")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant un arrondissement")
public class Arrondissement extends Territoire {

    @Schema(example = "591")
    private String code = null;

    @Schema(example = "http://id.insee.fr/geo/arrondissement/591")
    private String uri = null;

    @Schema(example = "Avesnes-sur-Helpe")
    private String intitule = null;

    @Schema(example = "Arrondissement")
    private String type = EnumTypeGeographie.ARRONDISSEMENT.getTypeObjetGeo();

    @Schema(description = "Date de création de l’arrondissement s’il a été créé après le 1er janvier 1993")
    @JsonInclude(Include.NON_EMPTY)
    private String dateCreation = null;

    @Schema(description = "Date de suppression de l’arrondissement s’il a été supprimé. ")
    @JsonInclude(Include.NON_EMPTY)
    private String dateSuppression = null;

    private IntituleSansArticle intituleSansArticle;

    @Schema(description = "Code Insee de la commune sous-préfecture de l’arrondissement.")
    private String chefLieu = null;

    // No-args constructor needed for JAXB
    public Arrondissement() {
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public Arrondissement(String code) {
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

    @JacksonXmlProperty(localName = "Type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JacksonXmlProperty(localName = "DateCreation")
    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    @JacksonXmlProperty(localName = "DateSuppression")
    public String getDateSuppression() {
        return dateSuppression;
    }

    public void setDateSuppression(String dateSuppression) {
        this.dateSuppression = dateSuppression;
    }

    @JacksonXmlProperty(localName = "IntituleSansArticle")
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

    @JacksonXmlProperty(localName = "ChefLieu")
    public String getChefLieu() {
        return chefLieu;
    }

    public void setChefLieu(String chefLieu) {
        this.chefLieu = chefLieu;
    }
}
