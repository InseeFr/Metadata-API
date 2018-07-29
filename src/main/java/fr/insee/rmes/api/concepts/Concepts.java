package fr.insee.rmes.api.concepts;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="Concepts")
@XmlAccessorType(XmlAccessType.FIELD)
public class Concepts {

	private List<Concept> concepts = null;
	
	public Concepts() {}

	public Concepts(List<Concept> concepts) {
		this.concepts = concepts;
	}

	@JacksonXmlProperty(isAttribute=true, localName="Concept")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Concept> getConcepts() {
		return concepts;
	}

	public void setConcepts(List<Concept> concepts) {
		this.concepts = concepts;
	}
	
}
