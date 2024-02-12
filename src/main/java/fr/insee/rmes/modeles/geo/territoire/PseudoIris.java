package fr.insee.rmes.modeles.geo.territoire;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Commune")
@JacksonXmlRootElement(localName = "Commune")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PseudoIris extends Territoire{

    public PseudoIris(
            String code,
            String uri,
            String intitule,
            String dateCreation,
            String dateSuppression,
            IntituleSansArticle intituleSansArticle) {
        super(code, uri, intitule, EnumTypeGeographie.COMMUNE.getTypeObjetGeo(), dateCreation, dateSuppression, intituleSansArticle);
    }

    public PseudoIris() {
        this(null);
    }

    public PseudoIris(String code) {
        this(code, null, null, null, null, new IntituleSansArticle());
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (this.code==null) {
            this.code=code;
        }
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
