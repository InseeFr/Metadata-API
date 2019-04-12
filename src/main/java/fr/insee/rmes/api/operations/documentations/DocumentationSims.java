package fr.insee.rmes.api.operations.documentations;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import fr.insee.rmes.api.operations.SimpleObject;
import fr.insee.rmes.api.utils.Lang;
import fr.insee.rmes.api.utils.StringWithLang;

public class DocumentationSims {

	private String id = null;
	private String uri = null;
	private List<StringWithLang> label =  new ArrayList<StringWithLang>();
	
	@JsonInclude(Include.NON_NULL)
	private SimpleObject cible;
		
	private List<Rubrique> rubriques  =  new ArrayList<Rubrique>();


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
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


	public List<Rubrique> getRubriques() {
		return rubriques;
	}

	public void setRubriques(List<Rubrique> rubriques) {
		this.rubriques = rubriques;
	}

	public SimpleObject getCible() {
		return cible;
	}

	public void setIdCible(String idCible) {
		checkCibleExists();
		cible.setId(idCible);
	}
	
	public void setCible(String cibleUri) {
		checkCibleExists();
		cible.setUri(cibleUri);
	}

	public void setLabelCibleLg1(String labelCibleLg1) {
		checkCibleExists();
		List<StringWithLang> temp = cible.getLabel();
		temp.add(new StringWithLang(labelCibleLg1, Lang.FR));
		cible.setLabel(temp);
	}
	
	public void setLabelCibleLg2(String labelCibleLg2) {
		checkCibleExists();
		List<StringWithLang> temp = cible.getLabel();
		temp.add(new StringWithLang(labelCibleLg2, Lang.EN));
		cible.setLabel(temp);
	}

	private void checkCibleExists() {
		if (cible == null) {
			cible = new SimpleObject();
		}
	}

}
