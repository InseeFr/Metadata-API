package fr.insee.rmes.api.operations;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import fr.insee.rmes.api.utils.Lang;
import fr.insee.rmes.api.utils.StringWithLang;

public class SimpleObject {

	private String id = null;
	private String uri = null;
	
	private List<StringWithLang> label =  new ArrayList<StringWithLang>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SimpleObject(String id, String uri, String labelFr, String labelEn) {
		this.id = id;
		this.uri = uri;
		if (StringUtils.isNotEmpty(labelFr)) {
			label.add(new StringWithLang(labelFr, Lang.FR));
		}
		if (StringUtils.isNotEmpty(labelEn)) {
			label.add(new StringWithLang(labelFr, Lang.EN));
		}
	}

	public SimpleObject() {
		super();
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@JacksonXmlProperty(localName="label")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<StringWithLang> getLabel() {
		return label;
	}

	public void setLabel(List<StringWithLang> label) {
		this.label = label;
	}
	
}
