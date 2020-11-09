package fr.insee.rmes.modeles.classification.correspondence;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Correspondance")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PosteCorrespondence implements Comparable<Object> {

    String code;
    String uri;
    String intituleFr;
    String intituleEn;

    public PosteCorrespondence(String code, String uri, String intituleFr, String intituleEn) {
        super();
        this.code = code;
        this.uri = uri;
        this.intituleFr = intituleFr;
        this.intituleEn = intituleEn;
    }

    public PosteCorrespondence() {
    	// No-args constructor needed for JAXB
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

    @JacksonXmlProperty(localName = "IntituleFr")
    public String getIntituleFr() {
        return intituleFr;
    }

    public void setIntituleFr(String intituleFr) {
        this.intituleFr = intituleFr;
    }

    @JacksonXmlProperty(localName = "IntituleEn")
    public String getIntituleEn() {
        return intituleEn;
    }

    public void setIntituleEnPosteSource(String intituleEn) {
        this.intituleEn = intituleEn;
    }

    @Override
    public int compareTo(Object p) {
        PosteCorrespondence posteToCompare = (PosteCorrespondence) p;
        String posteToCompareUri = posteToCompare.getUri();
        if (this.uri.equals(posteToCompareUri)) {
            return 0;
        }
        return 1;
    }

	@Override
	public int hashCode() {
		return Objects.hash(code, intituleEn, intituleFr, uri);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PosteCorrespondence other = (PosteCorrespondence) obj;
		return Objects.equals(code, other.code) && Objects.equals(intituleEn, other.intituleEn)
				&& Objects.equals(intituleFr, other.intituleFr) && Objects.equals(uri, other.uri);
	}

}