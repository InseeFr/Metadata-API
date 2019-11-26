package fr.insee.rmes.modeles.concepts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Definition")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Définition", description = "Objet représentant la définition d'un concept statistique de l'Insee")
public class Definition {

    private String id = null;
    @Schema(example = "http://id.insee.fr/concepts/definition/c2066")
    private String uri = null;
    @Schema(example = "")
    private String intitule = null;
    private String replaces = null;
    @Schema(example = "http://id.insee.fr/concepts/definition/c1500")
    private String isReplacedBy = null;

    public Definition() {}

    public Definition(String id) {
        this.id = id;
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @JacksonXmlProperty(localName = "Remplace")
    @JsonProperty(value = "remplace")
    public String getReplaces() {
        return replaces;
    }

    public void setReplaces(String replaces) {
        this.replaces = replaces;
    }

    @JacksonXmlProperty(localName = "EstRemplacePar")
    @JsonProperty(value = "estRemplacePar")
    public String getIsReplacedBy() {
        return isReplacedBy;
    }

    public void setIsReplacedBy(String isReplacedBy) {
        this.isReplacedBy = isReplacedBy;
    }

}
