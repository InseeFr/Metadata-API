package fr.insee.rmes.api.operations;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="Arborescence")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tree {

	List<Family> families = null;
	
	public Tree() {}

	public Tree(List<Family> families) {
		this.families = families;
	}

	@JacksonXmlProperty(isAttribute=true, localName="Famille")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Family> getFamilies() {
		return families;
	}

	public void setFamilies(List<Family> families) {
		this.families = families;
	}
	
}
