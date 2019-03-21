package fr.insee.rmes.api.correspondences;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Association")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_EMPTY) 
public class Correspondence {

	
	@JacksonXmlProperty(localName="Source")
	@JsonProperty("source")
	private Poste itemSource;
	
	
	@JacksonXmlProperty(localName="Cible")
	@JacksonXmlElementWrapper(useWrapping = false)
	@JsonProperty("cibles")
	private List<Poste> itemTargets;

	public Correspondence() {

	}
	
	public Correspondence(Poste k, List<Poste> v) {
		
		this.itemSource = k;
		this.itemTargets = v;
		
	}

	public Poste getItemSource() {
		return itemSource;
	}

	public void setItemSource(Poste ItemSource) {
		this.itemSource = ItemSource;
	}

	public List<Poste> getItemTargets() {
		return itemTargets;
	}

	public void setItemTargets(List<Poste> ItemTargets) {
		this.itemTargets = ItemTargets;
	}

}
