package fr.insee.rmes.modeles.classification.naf1993;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "ClasseNAF1993")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant une classe de la NAF 1993")
public class ClasseNAF1993 {
    @Schema(example = "01.1A")
    private String code = null;
    @Schema(example = "http://id.insee.fr/codes/naf/classe/01.1A")
    private String uri = null;
    @Schema(example = "Culture de céréales ; cultures industrielles")
    private String intitule = null;

    public ClasseNAF1993() {} // No-args constructor needed for JAXB

    public ClasseNAF1993(String code) {
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
