package fr.insee.rmes.modeles.classification;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "Activite")
@JacksonXmlRootElement(localName = "Activite")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant une activité")
public class Activite {
	@XmlAttribute
    @Schema(example = "01.1A")
    private String code = null;
	@XmlAttribute
    @Schema(example = "http://id.insee.fr/codes/nafr1/classe/01.1A")
    private String uri = null;
	
    @XmlElement(name = "Intitule")
    @Schema(example = "Culture de céréales ; cultures industrielles")
    private String intitule = null;
    
    @XmlElement(name = "DateDebutValidite")
    @Schema(example = "2003-01-01", format="date")
    private String issued = null;
    
    @XmlElement(name = "DateFinValidite")
    @Schema( example = "2007-12-31", format="date")
    private String valid = null;

    public Activite() {} // No-args constructor needed for JAXB

    public Activite(String code) {
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
