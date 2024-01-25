package fr.insee.rmes.modeles.geo.territoire;

import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;
import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "Commune")
@JacksonXmlRootElement(localName = "Commune")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant une commune")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Commune extends Territoire {

    private final Inclusion inclusion;

    // No-args constructor needed for JAXB
    public Commune() {
        this.type = EnumTypeGeographie.COMMUNE.getTypeObjetGeo();
        this.intituleSansArticle = new IntituleSansArticle();
        this.inclusion=null;
    }

    public Commune(String code) {
        this();
        this.code = code;
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "55323")
    public String getCode() {
        return code;
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "http://id.insee.fr/geo/commune/55323")
    public String getUri() {
        return uri;
    }

    @Override
    @JacksonXmlProperty(localName = "Intitule")
    @JsonProperty(value = "intitule")
    @Schema(example = "L'Aigle")
    public String getIntitule() {
        return intitule;
    }

    @Override
    @JacksonXmlProperty(localName = "Type")
    @Schema(example = "Commune")
    public String getType() {
        return type;
    }

    @Override
    @JacksonXmlProperty(localName = "DateCreation")
    @Schema(
        description = "Date de création de la commune si elle n’existait pas au premier COG du 1er janvier 1943",
        example = "1943-01-01")
    public String getDateCreation() {
        return dateCreation;
    }

    @Override
    @JacksonXmlProperty(localName = "DateSuppression")
    @Schema(description = "Date de suppression de la commune si elle a été supprimée. ", example = "2019-01-01")
    public String getDateSuppression() {
        return dateSuppression;
    }


    @JacksonXmlProperty(localName = "Inclusion")
    @NotNull
    @Schema(description = "inclusion totale ou partielle dans un canton", example = "totale")
    public Inclusion getInclusion() {
        return inclusion;
    }


}
