package fr.insee.rmes.modeles.geo.territoire;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Arrondissement")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant un arrondissement")
public class Arrondissement extends Territoire {

    // No-args constructor needed for JAXB
    public Arrondissement() {
        super();
        this.type = EnumTypeGeographie.ARRONDISSEMENT.getTypeObjetGeo();
    }

    public Arrondissement(String code) {
        super();
        this.type = EnumTypeGeographie.ARRONDISSEMENT.getTypeObjetGeo();
    }

    public Arrondissement(
        String code,
        String uri,
        String intitule,
        String type,
        String dateCreation,
        String dateSuppression,
        IntituleSansArticle intituleSansArticle,
        String chefLieu) {
        super(code, uri, intitule, type, dateCreation, dateSuppression, intituleSansArticle, chefLieu);
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "191")
    public String getCode() {
        return code;
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "http://id.insee.fr/geo/arrondissement/c38af455-45e1-4f44-9d58-165e1626441a")
    public String getUri() {
        return uri;
    }

    @Override
    @JacksonXmlProperty(localName = "Intitule")
    @JsonProperty(value = "intitule")
    @Schema(example = "Brive-la-Gaillarde")
    public String getIntitule() {
        return intitule;
    }

    @Override
    @JacksonXmlProperty(localName = "Type")
    @Schema(example = "Arrondissement")
    public String getType() {
        return type;
    }

    @Override
    @JacksonXmlProperty(localName = "DateCreation")
    @Schema(
        description = "Date de création de l'arrondissement s'il n’existait pas au premier COG du 1er janvier 1943",
        example = "1993-01-01")
    public String getDateCreation() {
        return dateCreation;
    }

    @Override
    @JacksonXmlProperty(localName = "DateSuppression")
    @Schema(description = "Date de suppression de l'arrondissement s'il a été supprimé. ", example = "2017-01-01")
    public String getDateSuppression() {
        return dateSuppression;
    }

    @Override
    @JacksonXmlProperty(localName = "ChefLieu")
    @Schema(description = "Code Insee de la commune sous-préfecture de l’arrondissement.", example="19031")
    public String getChefLieu() {
        return chefLieu;
    }

}
