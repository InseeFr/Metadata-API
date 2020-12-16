package fr.insee.rmes.modeles.classification.naf2008;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "SousClasseNAF2008")
@JacksonXmlRootElement(localName = "SousClasseNAF2008")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant une sous-classe de la NAF 2008 (rév. 2)")
public class SousClasseNAF2008 {
	@XmlAttribute
    @Schema(example = "27.40Z")
    private String code;
	@XmlAttribute
    @Schema(example = "http://id.insee.fr/codes/nafr2/sousClasse/27.40Z")
    private String uri;
	@XmlElement(name = "Intitule")
    @Schema(example = "Fabrication d'appareils d'éclairage électrique")
    private String intitule;

    public SousClasseNAF2008() {} // No-args constructor needed for JAXB

    public SousClasseNAF2008(String code) {
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
