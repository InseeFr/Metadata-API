package fr.insee.rmes.api.operations;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import fr.insee.rmes.api.utils.Lang;
import fr.insee.rmes.api.utils.StringWithLang;

public class Operation {
	
	private String id = null;
	
	private List<StringWithLang> label =  new ArrayList<StringWithLang>();
	private String uri = null;
	
	@JsonInclude(Include.NON_NULL)
	private String simsId = null;
	
	public Operation(String uri, String id, String labelFr, String labelEn, String simsId) {
		super();
		this.id = id;
		label.add( new StringWithLang(labelFr, Lang.FR));
		if (labelEn != "") {
			label.add(new StringWithLang(labelEn, Lang.EN));
		}
		if (simsId != "") this.simsId = simsId;
		this.uri = uri;
	}

	public Operation() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@JacksonXmlProperty(localName="label")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<StringWithLang> getLabel() {
		return label;
	}

	public void setLabel(List<StringWithLang> label) {
		this.label = label;
	}

	public void setLabelFr(String labelFr) {
		if (StringUtils.isNotEmpty(labelFr)) {
			label.add(new StringWithLang(labelFr, Lang.FR));
		}
	}
	
	public void setLabelEn(String labelEn) {
		if (StringUtils.isNotEmpty(labelEn)) {
			label.add(new StringWithLang(labelEn, Lang.EN));
		}
	}
	

}