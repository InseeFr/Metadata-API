package fr.insee.rmes.api.correspondences;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="Correspondances")
public class Correspondences
{

	private List<Correspondence> listCorrespondences = new ArrayList<Correspondence>();

	public Correspondences() {

	}

	public Correspondences(List<Correspondence> listCorrespondences) {
		this.listCorrespondences = listCorrespondences;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "Correspondance")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Correspondence> getlistDescriptions() {
		return this.listCorrespondences;
	}

	public void setListItems(List<Correspondence> listCorrespondences) {
		this.listCorrespondences = listCorrespondences;
	}

}
