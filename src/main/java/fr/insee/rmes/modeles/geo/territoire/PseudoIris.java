package fr.insee.rmes.modeles.geo.territoire;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Commune")
@JacksonXmlRootElement(localName = "Commune")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PseudoIris {

    @XmlAttribute
    public String code;

    @XmlAttribute
    public String uri;

    @XmlElement(name="Intitule")
    public String intitule;

    @XmlElement(name="Type")
    public String type;

    @XmlElement(name="DateCreation")
    public String dateCreation;

    @XmlElement(name="DateSuppression")
    public String dateSuppression;

    @XmlElement(name="IntituleSansArticle")
    public IntituleSansArticle intituleSansArticle;

    public PseudoIris () {}
    public PseudoIris (
            String code,
            String uri,
            String intitule,
            String type,
            String dateCreation,
            String dateSuppression,
            IntituleSansArticle intituleSansArticle
    ) {
        this.code = code;
        this.uri = uri;
        this.intitule = intitule;
        this.type = type;
        this.dateCreation = dateCreation;
        this.dateSuppression = dateSuppression;
        this.intituleSansArticle = intituleSansArticle;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
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


    public String getIntituleSansArticle() {
        return intituleSansArticle.getIntituleSansArticle();
    }


    public String getTypeArticle() {
        return intituleSansArticle.getTypeArticle();
    }

    public void setIntituleSansArticle(IntituleSansArticle intituleSansArticle) {
        this.intituleSansArticle = intituleSansArticle;
    }

    @Override
    public String toString() {
        return "PseudoIris{" +
                "code='" + code + '\'' +
                ", uri='" + uri + '\'' +
                ", intitule='" + intitule + '\'' +
                ", type='" + type + '\'' +
                ", dateCreation='" + dateCreation + '\'' +
                ", dateSuppression='" + dateSuppression + '\'' +
                ", intituleSansArticle=" + intituleSansArticle +
                '}';
    }
}
