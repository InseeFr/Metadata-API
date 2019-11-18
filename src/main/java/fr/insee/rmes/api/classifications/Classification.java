package fr.insee.rmes.api.classifications;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Nomenclature")
@XmlAccessorType(XmlAccessType.FIELD)
public class Classification {

    String code;
    String uri;
    String intitule;

    public Classification() {}

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
