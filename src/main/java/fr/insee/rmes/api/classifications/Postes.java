package fr.insee.rmes.api.classifications;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JacksonXmlRootElement(localName="Postes")
@XmlAccessorType(XmlAccessType.FIELD)
//@ApiModel(value = "Classification", description = "Objet représentant une nomenclature")
public class Postes {

	
	private List<Poste> listItems = new ArrayList<Poste>();
	
	
	public Postes() {
		
	}
	
	
	public Postes(List<Poste> listItems) {
		this.listItems = listItems ;
	}
	
	
	@JacksonXmlProperty(isAttribute=true, localName="Poste")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Poste> getListItems() {
		return this.listItems;
	}

	public void setListItems(List<Poste> listItems) {
		this.listItems = listItems;
	}
	
	

}
