package fr.insee.rmes.modeles.geo.territoire;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;
import io.swagger.v3.oas.annotations.media.Schema;

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
    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "0103")
    public String getCode() {
        return code;
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "http://id.insee.fr/geo/cantonOuVille/cb4d9856-a39d-4283-a9ab-d91396ebd705\"")
    public String getUri() {
        return uri;
    }

    @Override
    @JacksonXmlProperty(localName = "Intitule")
    @JsonProperty(value = "intitule")
    @Schema(example = "Valserhône")
    public String getIntitule() {
        return intitule;
    }

    @Override
    @JacksonXmlProperty(localName = "IntituleSansArticle")
    @JsonProperty(value = "intituleSansArticle")
    @Schema(example = "Valserhône")
    public IntituleSansArticle getIntituleSansArticle() {
        return intituleSansArticle;
    }

    @Override
    @JacksonXmlProperty(localName = "DateCreation")
    @Schema(
            description = "Date de création du canton-ou-ville si il n’existait pas au premier COG du 1er janvier 1943",
            example = "1943-01-01")
    public String getDateCreation() {
        return dateCreation;
    }

    @Override
    @JacksonXmlProperty(localName = "DateSuppression")
    @Schema(description = "Date de suppression du canton-ou-ville si il a été supprimé. ",
            example = "2019-01-01")
    public String getDateSuppression() {
        return dateSuppression;
    }

    @Override
    @JacksonXmlProperty(localName = "Type")
    @Schema(example = "CantonOuVille")
    public String getType() {
        return type;
    }


}
