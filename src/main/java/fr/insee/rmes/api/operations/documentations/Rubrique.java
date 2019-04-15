package fr.insee.rmes.api.operations.documentations;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import fr.insee.rmes.api.operations.SimpleObject;
import fr.insee.rmes.api.utils.Lang;
import fr.insee.rmes.api.utils.StringWithLang;

public class Rubrique {

	private String id = null;
	private String uri = null;
	private List<StringWithLang> titre =  new ArrayList<StringWithLang>();
	private String idParent = null;
	private String type;
	
	/*CODE_LIST*/
	@JsonInclude(Include.NON_NULL)
	private SimpleObject valeurCode;

	/*DATE*/
	@JsonInclude(Include.NON_NULL)
	private String valeurSimple = null;

	/*ORGANISATION*/
	@JsonInclude(Include.NON_NULL)
	private SimpleObject valeurOrganisation;

	/*TEXT - RICH_TEXT*/
	@JsonInclude(Include.NON_NULL)
	private List<StringWithLang> label ;
	
	/*RICH_TEXT*/
	@JsonInclude(Include.NON_NULL)
	private List<Document> documents ;

	
	
	public Rubrique(String id, String uri, String type) {
		this.id=id;
		this.uri=uri;
		this.type=type;
	}
	
	public String getId() {
		return id;
	}
	
	public String getUri() {
		return uri;
	}
	
	@JacksonXmlProperty(localName="titre")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<StringWithLang> getTitre() {
		return titre;
	}
	public void setTitre(List<StringWithLang> titre) {
		this.titre = titre;
	}
	public String getIdParent() {
		return idParent;
	}
	public void setIdParent(String idParent) {
		this.idParent = idParent;
	}
	public String getType() {
		return type;
	}
	
	public String getValeurSimple() {
		return valeurSimple;
	}
	public void setValeurSimple(String valeurSimple) {
		this.valeurSimple = valeurSimple;
	}
	
	@JacksonXmlProperty(localName="label")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<StringWithLang> getLabel() {
		return label;
	}
	
	public void setLabelLg1(String labelLg1) {
		if (StringUtils.isNotEmpty(labelLg1)) {
			if (label == null) label = new ArrayList<>();
			label.add(new StringWithLang(labelLg1, Lang.FR));
		}
	}
	
	public void setLabelLg2(String labelLg2) {
		if (StringUtils.isNotEmpty(labelLg2)) {
			if (label == null) label = new ArrayList<>();
			label.add(new StringWithLang(labelLg2, Lang.EN));
		}
	}
	

	public SimpleObject getValeurCode() {
		return this.valeurCode;
	}
	
	public void setValeurCode(SimpleObject so) {
		this.valeurCode = so;
	}

	public SimpleObject getValeurOrganisation() {
		return valeurOrganisation;
	}

	public void setValeurOrganisation(SimpleObject valeurOrganisation) {
		this.valeurOrganisation = valeurOrganisation;
	}

	public void setTitre(String titreLg1, String titreLg2) {
		if (titre == null) titre = new ArrayList<>();
		if (StringUtils.isNotEmpty(titreLg1))
		titre.add(new StringWithLang(titreLg1, Lang.FR));
		if (StringUtils.isNotEmpty(titreLg2))
		titre.add(new StringWithLang(titreLg2, Lang.EN));
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

}
