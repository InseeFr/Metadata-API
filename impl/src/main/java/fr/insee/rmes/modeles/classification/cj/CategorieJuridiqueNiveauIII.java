package fr.insee.rmes.modeles.classification.cj;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "CategorieJuridiqueNiveauIII")
@JacksonXmlRootElement(localName = "CategorieJuridiqueNiveauIII")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant une catégorie juridique détaillée (niveau 3)")
public class CategorieJuridiqueNiveauIII {
    @Schema(example = "7112")
    @XmlAttribute
    private String code;
    @Schema(example = "http://id.insee.fr/codes/cj/n3/7112")
    @XmlAttribute
    private String uri;
	@XmlElement(name = "Intitule")
    @Schema(example = "Autorité administrative ou publique indépendante")
    private String intitule;

    public CategorieJuridiqueNiveauIII() {} // No-args constructor needed for JAXB

    public CategorieJuridiqueNiveauIII(String code) {
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
