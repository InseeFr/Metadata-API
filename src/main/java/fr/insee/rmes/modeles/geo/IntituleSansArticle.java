package fr.insee.rmes.modeles.geo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="Intitulé sans son article et article associé")
@XmlAccessorType(XmlAccessType.PROPERTY)
@JsonPropertyOrder({"intituleSansArticle", "typeArticle"})
@XmlRootElement(name="IntituleSansArticle")
public class IntituleSansArticle {

    //@Schema(example = "Aigle")
    @XmlValue
    private String label = null;

    @Schema(example = "5")
    private String typeArticle = null;

    
    @JacksonXmlText
   // @JsonProperty("intituleSansArticle")
  //  @JacksonXmlProperty(localName="intituleSansArticle")
    @Schema(example = "Aigle")
    @XmlValue
    public String getIntituleSansArticle() {
        return label;
    }

    public void setIntituleSansArticle(String intituleSansArticle) {
        this.label = intituleSansArticle;
    }

    @JacksonXmlProperty(isAttribute = true)
    @JsonProperty("typeArticle")
    @XmlAttribute
    public String getTypeArticle() {
        return typeArticle;
    }

    public void setTypeArticle(String typeArticle) {
        this.typeArticle = typeArticle;
    }

}
