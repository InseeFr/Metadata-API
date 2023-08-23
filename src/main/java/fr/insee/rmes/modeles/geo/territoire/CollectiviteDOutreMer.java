package fr.insee.rmes.modeles.geo.territoire;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;
import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "CollectiviteDOutreMER")
@JacksonXmlRootElement(localName = "CollectiviteDOutreMer")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant une collectivite d'outre-mer")

public class CollectiviteDOutreMer extends Territoire {
	
	// No-args constructor needed for JAXB
    public CollectiviteDOutreMer() {
        this.type = EnumTypeGeographie.COLLECTIVITE_D_OUTRE_MER.getTypeObjetGeo();
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public CollectiviteDOutreMer(String code) {
        this.type = EnumTypeGeographie.COLLECTIVITE_D_OUTRE_MER.getTypeObjetGeo();
        this.code = code;
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public CollectiviteDOutreMer(
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
    @Schema(example = "98735")
    public String getCode() {
        return code;
    }

    @Override
    @JacksonXmlProperty(isAttribute = true)
    @Schema(example = "http://id.insee.fr/geo/commune/98735")
    public String getUri() {
        return uri;
    }

    @Override
    @JacksonXmlProperty(localName = "Intitule")
    @JsonProperty(value = "intitule")
    @Schema(example = "Papeete")
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
        description = "Date de création de la commune/district si il/elle n’existait pas au premier COG du 1er janvier 1943",
        example = "1943-01-01")
    public String getDateCreation() {
        return dateCreation;
    }

    @Override
    @JacksonXmlProperty(localName = "DateSuppression")
    @Schema(description = "Date de suppression de la commune/district si il/elle a été supprimé(e). ", example = "2019-01-01")
    public String getDateSuppression() {
        return dateSuppression;
    }



}
