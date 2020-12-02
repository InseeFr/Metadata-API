package fr.insee.rmes.modeles.classification.correspondence;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Correspondance", description = "Objet représentant une correspondance entre deux nomenclatures")
public class Correspondence {

    @Schema(example = "nafr2-cpfr21")
    String id;
    @Schema(example = "nafr2")
    String idSource;
    @Schema(example = "cpfr21")
    String idCible;
    @Schema(example = "http://rdf.insee.fr/codes/nafr2-cpfr21")
    String uri;
    @Schema(example = "Correspondance Naf rév. 2 / Cpf rév. 2.1")
    String intituleFr;
    @Schema(example = "Naf2/Cpf2.1 correspondence")
    String intituleEn;
    String descriptionFr;
    String descriptionEn;

    public Correspondence() {
    	// No-args constructor needed for JAXB
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getIdSource() {
        return idSource;
    }

    public void setIdSource(String idSource) {
        this.idSource = idSource;
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getIdCible() {
        return idCible;
    }

    public void setIdCible(String idCible) {
        this.idCible = idCible;
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @JacksonXmlProperty(localName = "IntituleFr")
    public String getIntituleFr() {
        return intituleFr;
    }

    public void setIntituleFr(String intituleFr) {
        this.intituleFr = intituleFr;
    }

    @JacksonXmlProperty(localName = "IntituleEn")
    public String getIntituleEn() {
        return intituleEn;
    }

    public void setIntituleEn(String intituleEn) {
        this.intituleEn = intituleEn;
    }

    @JacksonXmlProperty(localName = "DescriptionFr")
    public String getDescriptionFr() {
        return descriptionFr;
    }

    public void setDescriptionFr(String descriptionFr) {
        this.descriptionFr = descriptionFr;
    }

    @JacksonXmlProperty(localName = "DescriptionEn")
    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

}
