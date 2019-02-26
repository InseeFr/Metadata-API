package fr.insee.rmes.api.correspondences;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="Correspondances")
public class RawCorrespondences
{

	private List<RawCorrespondence> listCorrespondences = new ArrayList<RawCorrespondence>();

	public RawCorrespondences() {

	}

	public RawCorrespondences(List<RawCorrespondence> listCorrespondences) {
		this.listCorrespondences = listCorrespondences;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "Correspondance")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<RawCorrespondence> getlistDescriptions() {
		return this.listCorrespondences;
	}

	public void setListItems(List<RawCorrespondence> listCorrespondences) {
		this.listCorrespondences = listCorrespondences;
	}

}
