package fr.insee.rmes.api.operations;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import fr.insee.rmes.api.utils.Lang;
import fr.insee.rmes.api.utils.StringWithLang;

public class Indicateur {
	
	private String id = null;
	private List<StringWithLang> label =  new ArrayList<StringWithLang>();
	private String uri = null;
	
	@JsonInclude(Include.NON_NULL)
	private String simsId = null;
	
	public Indicateur(String uri, String id, String labelLg1, String labelLg2, String simsId) {
		super();
		this.id = id;
		label.add( new StringWithLang(labelLg1, Lang.FR));
		if (labelLg2 != "") {
			label.add(new StringWithLang(labelLg2, Lang.EN));
		}
		if (simsId != "") this.simsId = simsId;
		this.uri = uri;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JacksonXmlProperty(localName="Label")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<StringWithLang> getLabel() {
		return label;
	}

	public void setLabel(List<StringWithLang> label) {
		this.label = label;
	}

	public String getSimsId() {
		return simsId;
	}

	public void setSimsId(String simsId) {
		if (simsId != "") this.simsId = simsId;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	

}