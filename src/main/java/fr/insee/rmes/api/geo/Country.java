package fr.insee.rmes.api.geo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Pays")
@XmlAccessorType(XmlAccessType.FIELD)
public class Country {

	@XmlAttribute
	private String code = null;
	@XmlAttribute
	private String uri = null;
	@XmlElement(name="Intitule")
	private String intitule = null;
	
	public Country() {
		
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
