package fr.insee.rmes.modeles.geo.territoire;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import fr.insee.rmes.modeles.geo.IntituleSansArticle;

// @JsonTypeInfo(use = JsonTypeInfo.Id.CUSTOM, include = JsonTypeInfo.As.WRAPPER_OBJECT)
// @JsonTypeIdResolver(value = TerritoireIdResolver.class)
public abstract class Territoire {

    protected String code = null;

    protected String uri = null;

    protected String intitule = null;

    protected String type;

    @JsonInclude(Include.NON_EMPTY)
    protected String dateCreation = null;

    @JsonInclude(Include.NON_EMPTY)
    protected String dateSuppression = null;

    protected IntituleSansArticle intituleSansArticle;

    @JsonInclude(Include.NON_EMPTY)
    protected String chefLieu = null;

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

    public Territoire() {
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public Territoire(String code) {
        this.code = code;
        this.intituleSansArticle = new IntituleSansArticle();
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
