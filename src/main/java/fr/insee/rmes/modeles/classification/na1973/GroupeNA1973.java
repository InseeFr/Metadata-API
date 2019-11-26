package fr.insee.rmes.modeles.classification.na1973;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "GroupeNA1973")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant un groupe de la NA 1973")
public class GroupeNA1973 {
    @Schema(example = "45.23")
    private String code = null;
    @Schema(example = "http://id.insee.fr/codes/na73/groupe/45.23")
    private String uri = null;
    @Schema(example = "Fabrication d'articles divers en cuir et similaires")
    private String intitule = null;

    public GroupeNA1973() {} // No-args constructor needed for JAXB

    public GroupeNA1973(String code) {
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
