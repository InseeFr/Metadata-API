package fr.insee.rmes.modeles.geo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Commune")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant une commune")
public class Commune {

    @Schema(example = "55323")
    private String code = null;
    @Schema(example = "http://id.insee.fr/geo/commune/55323")
    private String uri = null;
    @Schema(example = "Martincourt-sur-Meuse")
    private String intitule = null;

    public Commune() {} // No-args constructor needed for JAXB

    public Commune(String code) {
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
}
