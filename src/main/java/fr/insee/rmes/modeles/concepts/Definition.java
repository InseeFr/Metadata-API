package fr.insee.rmes.modeles.concepts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "IntituleDefinition")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "IntituleDefinition", description = "Objet représentant la définition d'un concept statistique de l'Insee")
public class Definition {

    private String id = null;
    @Schema(example = "http://id.insee.fr/concepts/definition/c2066")
    private String uri = null;
    @Schema(example = "Intitulé du concept à définir")
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
    @JsonInclude(Include.NON_NULL)
    public String getReplaces() {
        return replaces;
    }

    public void setReplaces(String replaces) {
        if (StringUtils.isNotBlank(replaces)) {
            this.replaces = replaces;
        }
    }

    @JacksonXmlProperty(localName = "EstRemplacePar")
    @JsonProperty(value = "estRemplacePar")
    @JsonInclude(Include.NON_NULL)
    public String getIsReplacedBy() {
        return isReplacedBy;
    }

    public void setIsReplacedBy(String isReplacedBy) {
        if (StringUtils.isNotBlank(isReplacedBy)) {
            this.isReplacedBy = isReplacedBy;
        }
    }

}
