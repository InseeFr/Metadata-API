package fr.insee.rmes.api.classifications;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.annotations.ApiModelProperty;

@JacksonXmlRootElement(localName="Classification")
@XmlAccessorType(XmlAccessType.FIELD)
public class Classification {
	
	
	String code;
	String uri;
	String intitule;
	

	public Classification() {		
	}

	
	@JacksonXmlProperty(isAttribute = true)
	@ApiModelProperty(example = "")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@JacksonXmlProperty(isAttribute = true)
	@ApiModelProperty(example = "")
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@JacksonXmlProperty(isAttribute = true)
	@ApiModelProperty(example = "")
	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}


	
	
	

}
