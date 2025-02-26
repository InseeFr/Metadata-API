package fr.insee.rmes.modeles.geo.territoire;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonIgnoreProperties({ "intituleSansArticle","typeArticle"})
public class Pays extends Territoire {

    @Schema(example = "99100")
    private String code = null;
    @Schema(example ="http://id.insee.fr/geo/pays/b7e3f0c9-b653-4a3e-904a-de63b80e108b")
    private String uri = null;
    @Schema(example = "France")
    private String intitule = null;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(example = "République française")
    private String intituleComplet = null;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(example = "FR")
    private String iso3166alpha2 = null;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(example = "FRA")
    private String iso3166alpha3 = null;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(example = "250")
    private String iso3166num = null;
    @Schema(example = "1943-01-01")
    private String dateCreation = null;
    @Schema(example = "2025-12-12")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
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


    @XmlElement(name = "Intitule")
    @JacksonXmlProperty(localName = "Intitule")
    @JsonProperty(value = "intitule")
    public String getIntitule() {
        return intitule;
    }
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    @XmlElement(name = "IntituleComplet")
    @JacksonXmlProperty(localName = "IntituleComplet")
    @JsonProperty(value = "intituleComplet")
    public String getIntituleComplet() {
        return intituleComplet;
    }

    public void setIntituleComplet(String intituleComplet) {
        this.intituleComplet = intituleComplet;
    }


    @XmlElement(name = "Iso3166alpha2")
    @JacksonXmlProperty(localName = "Iso3166alpha2")
    @JsonProperty(value = "iso3166alpha2")
    public String getIso3166alpha2() {
        return iso3166alpha2;
    }

    public void setIso3166alpha2(String iso3166alpha2) {
        this.iso3166alpha2 = iso3166alpha2;
    }

    @XmlElement(name = "Iso3166alpha3")
    @JacksonXmlProperty(localName = "Iso3166alpha3")
    @JsonProperty(value = "iso3166alpha3")
    public String getIso3166alpha3() {
        return iso3166alpha3;
    }

    public void setIso3166alpha3(String iso3166alpha3) {
        this.iso3166alpha3 = iso3166alpha3;
    }

    @XmlElement(name = "Iso3166num")
    @JacksonXmlProperty(localName = "Iso3166num")
    @JsonProperty(value = "iso3166num")
    public String getIso3166num() {
        return iso3166num;
    }
    public void setIso3166num(String iso3166num) {
        this.iso3166num = iso3166num;
    }


}
