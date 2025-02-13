package fr.insee.rmes.modeles.geo.territoire;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "Pays")
@JacksonXmlRootElement(localName = "Pays")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant un pays")
public class Pays extends Territoire {

    @Schema(example = "99100")
    private String code = null;
    @Schema(example ="http://id.insee.fr/geo/pays/b7e3f0c9-b653-4a3e-904a-de63b80e108b")
    private String uri = null;
    @Schema(example = "France")
    private String nom = null;
    @Schema(example = "République française")
    private String nomLong = null;
    @Schema(example = "FR")
    private String iso3166alpha2 = null;
    @Schema(example = "FRA")
    private String iso3166alpha3 = null;
    @Schema(example = "250")
    private String iso3166num = null;
    @Schema(example = "1943-01-01")
    private String dateCreation = null;
    @Schema(example = "2025-12-12")
    private String dateSuppresion = null;

    public Pays() {} // No-args constructor needed for JAXB

    public Pays(String code) {
        this.code = code;
    }

    @XmlAttribute
    @JacksonXmlProperty(isAttribute = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlAttribute
    @JacksonXmlProperty(isAttribute = true)
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    @XmlElement(name = "Nom")
    @JacksonXmlProperty(localName = "Nom")
    @JsonProperty(value = "nom")

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlElement(name = "NomLong")
    @JacksonXmlProperty(localName = "NomLong")
    @JsonProperty(value = "nomLong")

    public String getNomLong() {
        return nomLong;
    }

    public void setNomLong(String nomLong) {
        this.nomLong = nomLong;
    }
}
