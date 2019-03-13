package fr.insee.rmes.api.correspondences;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="TablesDeCorrespondance")
public class CorrespondenceDescriptionsList {

	private List<CorrespondenceDescription> itemsList = new ArrayList<CorrespondenceDescription>();
	
	public CorrespondenceDescriptionsList() {
		
	}

	public CorrespondenceDescriptionsList(List<CorrespondenceDescription> itemsList) {

		this.itemsList = itemsList;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "TableDeCorrespondance")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<CorrespondenceDescription> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<CorrespondenceDescription> itemsList) {
		this.itemsList = itemsList;
	}
	
	
	

}
