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

@XmlRootElement(name = "ClasseNAF2008")
@JacksonXmlRootElement(localName = "ClasseNAF2008")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant une classe de la NAF 2008 (rév. 2)")
public class ClasseNAF2008 {
	@XmlAttribute
    @Schema(example = "46.66")
    private String code = null;
	@XmlAttribute
    @Schema(example = "http://id.insee.fr/codes/nafr2/classe/46.66")
    private String uri = null;
	@XmlElement(name = "Intitule")
    @Schema(example = "Commerce de gros d\'autres machines et équipements de bureau")
    private String intitule = null;

    public ClasseNAF2008() {} // No-args constructor needed for JAXB

    public ClasseNAF2008(String code) {
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
