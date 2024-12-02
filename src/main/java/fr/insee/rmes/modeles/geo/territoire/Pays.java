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

    @Schema(example = "99254")
    private String code = null;
    @Schema(example = "http://id.insee.fr/geo/pays/99254")
    private String uri = null;
    @Schema(example = "CHYPRE")
    private String intitule = null;
    @Schema(example = "RÉPUBLIQUE DE CHYPRE")
    private String intituleEntier = null;

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

    @XmlElement(name = "IntituleEntier")
    @JacksonXmlProperty(localName = "IntituleEntier")
    @JsonProperty(value = "intituleEntier")
    public String getIntituleEntier() {
        return intituleEntier;
    }

    public void setIntituleEntier(String intituleEntier) {
        this.intituleEntier = intituleEntier;
    }
}
