package fr.insee.rmes.modeles.geo.territoire;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Iris")
@JacksonXmlRootElement(localName = "Iris")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant un Iris")
public class Iris extends Territoire {

    public Iris() {
        this.type = EnumTypeGeographie.IRIS.getTypeObjetGeo();
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public Iris(String code) {
            this.type = EnumTypeGeographie.IRIS.getTypeObjetGeo();
            this.code = code;
            this.intituleSansArticle = new IntituleSansArticle();
     }

    @Override
    @JacksonXmlProperty(localName = "Type")
    @Schema(example = "Iris")
    public String getType() {
        return type;
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "010040101")
    public String getCode() {
        return code;
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(description = "URI de l'Iris ",example = "http://id.insee.fr/geo/iris/b8c772de-9551-4f13-81c5-eca5bb0f2f7d")
    public String getUri() {
        return uri;
    }

    @Override
    @JacksonXmlProperty(localName = "Intitule")
    @JsonProperty(value = "intitule")
    @Schema(description = "Nom de l'Iris (avec article)",example = "Les Pérouses-Triangle d'Activités")
    public String getIntitule() {
        return intitule;
    }

    @Override
    @JacksonXmlProperty(localName = "IntituleSansArticle")
    @JsonProperty(value = "intituleSansArticle")
    @Schema(description = "Nom de l'Iris sans article",example = "Pérouses-Triangle d'Activité")
    public IntituleSansArticle getIntituleSansArticle() {
        return intituleSansArticle;
    }

    @Override
    @JacksonXmlProperty(localName = "TypeDIris")
    @Schema(
            description = "Code du type d’Iris (H, A ou D)",
            example = "H")
    public String getTypeDIris() {return typeDIris;}

    @Override
    @JacksonXmlProperty(localName = "DateCreation")
    @Schema(
            description = "Date de création de l'Iris si il n’existait pas au premier COG du 1er janvier 2016",
            example = "2016-01-01")
    public String getDateCreation() {
        return dateCreation;
    }

    @Override
    @JacksonXmlProperty(localName = "DateSuppression")
    @Schema(description = "Date de suppression de l'Iris si il a été supprimé. ", example = "2019-01-01")
    public String getDateSuppression() {
        return dateSuppression;
    }

    // getters and setters
}
