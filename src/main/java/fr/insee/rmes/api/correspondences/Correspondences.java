package fr.insee.rmes.api.correspondences;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="Correspondances")
@XmlAccessorType(XmlAccessType.FIELD)
public class Correspondences {
	
	@JsonProperty("Correspondances")
	@JacksonXmlProperty(localName="Correspondance")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Correspondence> correspondences = new ArrayList<Correspondence>();
	
	public Correspondences() {

	}

	
	public List<Correspondence> getCorrespondences() {
		return correspondences;
	}

	public void setCorrespondences(List<Correspondence> correspondences) {
		this.correspondences = correspondences;
	}
	
	

}
