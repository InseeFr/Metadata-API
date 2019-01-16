package fr.insee.rmes.api.classifications;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="Postes")
@XmlAccessorType(XmlAccessType.FIELD)
public class Postes {

	private List<PosteXml> listItems = new ArrayList<PosteXml>();
		
	public Postes() {
		}
	
	public Postes(List<PosteXml> listItems) {
		this.listItems = listItems ;
	}
		
	@JacksonXmlProperty(isAttribute=true, localName="Poste")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<PosteXml> getListItems() {
		return this.listItems;
	}

	public void setListItems(List<PosteXml> listItems) {
		this.listItems = listItems;
	}
}
