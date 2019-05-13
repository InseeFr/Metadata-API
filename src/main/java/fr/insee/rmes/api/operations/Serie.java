package fr.insee.rmes.api.operations;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import fr.insee.rmes.api.utils.Lang;
import fr.insee.rmes.api.utils.StringWithLang;

public class Serie {

	private String id = null;
	private List<StringWithLang> label =  new ArrayList<StringWithLang>();
	private String uri = null;
	

	@JsonInclude(Include.NON_NULL)
	private SimpleObject family = null;

	@JsonInclude(Include.NON_NULL)
	private List<StringWithLang> abstractSerie;

	@JsonInclude(Include.NON_NULL)
	private List<StringWithLang> historyNote;
	
	@JsonInclude(Include.NON_NULL)
	private List<StringWithLang> altLabel;
	
	@JsonInclude(Include.NON_NULL)
	private SimpleObject type = null;
	@JsonInclude(Include.NON_NULL)
	private SimpleObject accrualPeriodicity = null;

	@JsonInclude(Include.NON_NULL)
	private String simsId = null;
	
	@JsonInclude(Include.NON_NULL)
	private List<Operation> operations;
	@JsonInclude(Include.NON_NULL)
	private List<Indicateur> indicateurs;


	@JsonInclude(Include.NON_NULL)
	private List<Serie> replaces;
	@JsonInclude(Include.NON_NULL)
	private List<Serie> isReplacedBy;
	@JsonInclude(Include.NON_NULL)
	private List<SimpleObject> seeAlso;
	@JsonInclude(Include.NON_NULL)
	private List<SimpleObject> creators;
	@JsonInclude(Include.NON_NULL)
	private List<SimpleObject> contributors;
	
	
	public Serie(String uri, String id, String labelLg1, String labelLg2) {	
		this.id=id;
		label.add( new StringWithLang(labelLg1, Lang.FR));
		if (labelLg2 != "") {
			label.add(new StringWithLang(labelLg2, Lang.EN));
		}
		this.uri = uri;
	}
	
	public Serie() {
		super();
	}

	public void addOperation(Operation op) {
		if (operations ==null) {
			setOperations(new ArrayList<Operation>());
		}
		this.operations.add(op);
	}

	public void addIndicateur(Indicateur indic) {
		if (indicateurs==null) {
				setIndicateurs(new ArrayList<Indicateur>());
		}
		this.indicateurs.add(indic);
	}

	public void addSeeAlso(SimpleObject sa) {
		if (seeAlso==null) {
			setSeeAlso(new ArrayList<SimpleObject>());
	}
	this.seeAlso.add(sa);
	}
	
	public void addReplaces(Serie rep) {
		if (replaces==null) {
			setReplaces(new ArrayList<Serie>());
	}
	this.replaces.add(rep);
	}
	
	public void addIsReplacedBy(Serie irb) {
		if (isReplacedBy==null) {
			setIsReplacedBy(new ArrayList<Serie>());
	}
	this.isReplacedBy.add(irb);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JacksonXmlProperty(localName="label")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<StringWithLang> getLabel() {
		return label;
	}

	public String getSimsId() {
		return simsId;
	}

	public void setSimsId(String simsId) {
		if (simsId != "") this.simsId = simsId;
	}

	@JacksonXmlProperty(isAttribute=true, localName="Operation")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	@JacksonXmlProperty(isAttribute=true, localName="Indicateur")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Indicateur> getIndicateurs() {
		return indicateurs;
	}

	public void setIndicateurs(List<Indicateur> indicateurs) {
		this.indicateurs = indicateurs;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public void setFamily(SimpleObject f) {
		this.family = f;		
	}
	
	@JsonProperty("famille")
	@JacksonXmlProperty(isAttribute=true, localName="Famille")
	@JacksonXmlElementWrapper(useWrapping = false)
	public SimpleObject getFamily() {
		return family;
	}






	public SimpleObject getType() {
		return type;
	}

	public void setType(SimpleObject type) {
		this.type = type;
	}

	@JsonProperty("remplace")
	@JacksonXmlProperty(isAttribute=true, localName="remplace")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Serie> getReplaces() {
		return replaces;
	}

	public void setReplaces(List<Serie> replaces) {
		this.replaces = replaces;
	}

	@JsonProperty("estRemplacePar")
	@JacksonXmlProperty(isAttribute=true, localName="estRemplacePar")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Serie> getIsReplacedBy() {
		return isReplacedBy;
	}

	public void setIsReplacedBy(List<Serie> isReplacedBy) {
		this.isReplacedBy = isReplacedBy;
	}

	@JsonProperty("voirAussi")
	@JacksonXmlProperty(isAttribute=true, localName="voirAussi")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<SimpleObject> getSeeAlso() {
		return seeAlso;
	}

	public void setSeeAlso(List<SimpleObject> seeAlso) {
		this.seeAlso = seeAlso;
	}

	@JsonProperty("periodicite")
	@JacksonXmlProperty(isAttribute=true, localName="periodicite")
	@JacksonXmlElementWrapper(useWrapping = false)
	public SimpleObject getAccrualPeriodicity() {
		return accrualPeriodicity;
	}

	public void setAccrualPeriodicity(SimpleObject accrualPeriodicity) {
		this.accrualPeriodicity = accrualPeriodicity;
	}
	
	@JsonProperty("resume")
	@JacksonXmlProperty(localName="resume")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<StringWithLang> getAbstractSerie() {
		return abstractSerie;
	}
	
	public void setAbstractLg1(String abstractLg1) {
		if (StringUtils.isNotEmpty(abstractLg1)) {
			if (abstractSerie == null) abstractSerie = new ArrayList<>();
			abstractSerie.add(new StringWithLang(abstractLg1, Lang.FR));
		}
	}
	
	public void setAbstractLg2(String abstractLg2) {
		if (StringUtils.isNotEmpty(abstractLg2)) {
			if (abstractSerie == null) abstractSerie = new ArrayList<>();
			abstractSerie.add(new StringWithLang(abstractLg2, Lang.EN));
		}
	}
	
	@JsonProperty("noteHistorique")
	@JacksonXmlProperty(localName="noteHistorique")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<StringWithLang> getHistoryNote() {
		return historyNote;
	}
	
	public void setHistoryNoteLg1(String str) {
		if (StringUtils.isNotEmpty(str)) {
			if (historyNote == null) historyNote = new ArrayList<>();
			historyNote.add(new StringWithLang(str, Lang.FR));
		}
	}
	
	public void setHistoryNoteLg2(String str) {
		if (StringUtils.isNotEmpty(str)) {
			if (historyNote == null) historyNote = new ArrayList<>();
			historyNote.add(new StringWithLang(str, Lang.EN));
		}
	}

	@JsonProperty("organismeResponsable")
	@JacksonXmlProperty(isAttribute=true, localName="organismeResponsable")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<SimpleObject> getCreators() {
		return creators;
	}

	public void setCreators(List<SimpleObject> creators) {
		this.creators = creators;
	}

	@JsonProperty("partenaire")
	@JacksonXmlProperty(isAttribute=true, localName="partenaire")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<SimpleObject> getContributors() {
		return contributors;
	}

	public void setContributors(List<SimpleObject> contributors) {
		this.contributors = contributors;
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
	
	@JacksonXmlProperty(localName="altLabel")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<StringWithLang> getAltLabel() {
		return altLabel;
	}
	
	public void setAltLabel(String altLabelLg1, String altLabelLg2 ) {
		if (altLabelLg1 != "") {
			if (altLabel == null) altLabel =  new ArrayList<StringWithLang>();
			label.add(new StringWithLang(altLabelLg1, Lang.FR));
		}
		if (altLabelLg2 != "") {
			if (altLabel == null) altLabel =  new ArrayList<StringWithLang>();
			label.add(new StringWithLang(altLabelLg2, Lang.EN));
		}
	}
	
}
