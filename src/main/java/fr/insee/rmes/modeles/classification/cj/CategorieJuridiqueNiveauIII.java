package fr.insee.rmes.modeles.classification.cj;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "CategorieJuridiqueNiveauIII")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant une catégorie juridique détaillée (niveau 3)")
public class CategorieJuridiqueNiveauIII {
    @Schema(example = "7112")
    private String code;
    @Schema(example = "http://id.insee.fr/codes/cj/n3/7112")
    private String uri;
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
