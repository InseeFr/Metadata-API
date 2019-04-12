package fr.insee.rmes.api.operations.documentations;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import fr.insee.rmes.api.utils.Lang;
import fr.insee.rmes.api.utils.StringWithLang;

public class Document {
	private List<StringWithLang> label =  new ArrayList<StringWithLang>();
	private String dateMiseAJour;
	private String langue;
	private String url;
	
	
	@JacksonXmlProperty(localName="Label")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<StringWithLang> getLabel() {
		return label;
	}
	
	public void setLabelLg1(String labelLg1) {
		label.add(new StringWithLang(labelLg1, Lang.FR));
	}
	
	public void setLabelLg2(String labelLg2) {
		label.add(new StringWithLang(labelLg2, Lang.EN));
	}
	
	public String getDateMiseAJour() {
		return dateMiseAJour;
	}
	public void setDateMiseAJour(String dateMiseAJour) {
		this.dateMiseAJour = dateMiseAJour;
	}
	public String getLangue() {
		return langue;
	}
	public void setLangue(String langue) {
		this.langue = langue;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
