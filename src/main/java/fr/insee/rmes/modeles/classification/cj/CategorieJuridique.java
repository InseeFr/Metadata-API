package fr.insee.rmes.modeles.classification.cj;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "CategorieJuridique")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant une catégorie juridique")
public class CategorieJuridique {
    @Schema(example = "1100")
    private String code = null;
    @Schema(example = "http://id.insee.fr/codes/cj/niveauIII/1100/197")
    private String uri = null;
    @Schema(example = "Entrepreneur individuel")
    private String intitule = null;
    @Schema(name = "DateDebutValidite", example = "1973-01-01")
    private String issued = null;
    @Schema(name = "dateFinValidite", example = "2018-06-30")
    private String valid = null;

    public CategorieJuridique() {} // No-args constructor needed for JAXB

    public CategorieJuridique(String code) {
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

    @JacksonXmlProperty(localName = "DateDebutValidite")
    @JsonProperty(value = "dateDebutValidite")
    @JsonInclude(Include.NON_EMPTY)
    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = DateUtils.getDateStringFromDateTimeString(issued);
    }

    @JacksonXmlProperty(localName = "DateFinValidite")
    @JsonProperty(value = "dateFinValidite")
    @JsonInclude(Include.NON_EMPTY)
    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = DateUtils.getDateStringFromDateTimeString(valid);
    }
}
