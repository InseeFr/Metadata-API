package fr.insee.rmes.api.codes.cj;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CategorieJuridiqueNiveauIII")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategorieJuridiqueNiveauIII {
	
	@XmlAttribute
	private String code;
	@XmlAttribute
	private String uri;
	@XmlElement(name="Intitule")
	private String intitule;
	
	public CategorieJuridiqueNiveauIII() {
		
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

}
