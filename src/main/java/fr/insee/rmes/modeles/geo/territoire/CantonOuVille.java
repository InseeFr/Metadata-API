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

@XmlRootElement(name = "CantonOuVille")
@JacksonXmlRootElement(localName = "CantonOuVille")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant un cantonOuVille")
public class CantonOuVille extends Territoire {

    public CantonOuVille(String code) {
        this.type = EnumTypeGeographie.CANTON_OU_VILLE.getTypeObjetGeo();
        this.code = code;
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public CantonOuVille() {
        this.type = EnumTypeGeographie.CANTON_OU_VILLE.getTypeObjetGeo();
        this.intituleSansArticle = new IntituleSansArticle();
    }


    public CantonOuVille(
            String code,
            String uri,
            String intitule,
            String type,
            String dateCreation,
            String dateSuppression,
            IntituleSansArticle intituleSansArticle,
            String typeArticle,
            String chefLieu) {
        super(code, uri, intitule, type, dateCreation, dateSuppression, intituleSansArticle,chefLieu);
        this.setTypeArticle(typeArticle);
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "0101")
    public String getCode() {
        return code;
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "http://id.insee.fr/geo/cantonOuVille/0101")
    public String getUri() {
        return uri;
    }

    @Override
    @JacksonXmlProperty(localName = "Intitule")
    @JsonProperty(value = "intitule")
    @Schema(example = "Ambérieu-en-Bugey")
    public String getIntitule() {
        return intitule;
    }

    @Override
    @JacksonXmlProperty(localName = "Type")
    @Schema(example = "CantonOuVille")
    public String getType() {
        return type;
    }

    @Override
    @JacksonXmlProperty(localName = "DateCreation")
    @Schema(
            description = "Date de création du canton si elle n’existait pas au premier COG du 1er janvier 1943",
            example = "2016-01-01")
    public String getDateCreation() {
        return dateCreation;
    }

    @Override
    @JacksonXmlProperty(localName = "DateSuppression")
    @Schema(description = "Date de suppression du canton si elle a été supprimée. ", example = "2019-01-01")
    public String getDateSuppression() {
        return dateSuppression;
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "01004")
    public String getChefLieu() {
        return chefLieu;
    }

}
