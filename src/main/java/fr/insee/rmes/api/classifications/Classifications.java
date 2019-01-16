package fr.insee.rmes.api.classifications;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Classifications {

	private List<Classification> listDescriptions = new ArrayList<Classification>();

	public Classifications() {
	}

	public Classifications(List<Classification> listDescriptions) {
		this.listDescriptions = listDescriptions;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "Classification")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Classification> getlistDescriptions() {
		return this.listDescriptions;
	}

	public void setListItems(List<Classification> listDescriptions) {
		this.listDescriptions = listDescriptions;
	}

}
