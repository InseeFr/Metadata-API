package fr.insee.rmes.modeles.geo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Pays")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant un pays")
public class Country extends Territoire {

    @Schema(example = "99217")
    private String code = null;
    @Schema(example = "http://id.insee.fr/geo/pays/99217")
    private String uri = null;
    @Schema(example = "Japon")
    private String intitule = null;
    @Schema(example = "Japon")
    private String intituleEntier = null;

    public Country() {} // No-args constructor needed for JAXB

    public Country(String code) {
        this.code = code;
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @JacksonXmlProperty(localName = "Intitule")
    @JsonProperty(value = "intitule")
    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    @JacksonXmlProperty(localName = "IntituleEntier")
    @JsonProperty(value = "intituleEntier")
    public String getIntituleEntier() {
        return intituleEntier;
    }

    public void setIntituleEntier(String intituleEntier) {
        this.intituleEntier = intituleEntier;
    }
}
