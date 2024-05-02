package fr.insee.rmes.modeles.classification;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Nomenclature")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Nomenclature", description = "Objet représentant une nomenclature")
public class Classification {
    @Schema(example = "nafr2")
    String code;
    @Schema(example = "http://id.insee.fr/codes/nafr2/naf")
    String uri;
    @Schema(example = "Nomenclature d'activités française - NAF rév. 2")
    String intitule;

    public Classification() {
    	super();
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
    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

}
