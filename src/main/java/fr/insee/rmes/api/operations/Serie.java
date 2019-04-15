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
	private String altLabel = null;
	
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
	private List<Serie> seeAlso;
	
	
	public Serie(String uri, String id, String labelLg1, String labelLg2) {	
		this.id=id;
		label.add( new StringWithLang(labelLg1, Lang.FR));
		if (labelLg2 != "") {
			label.add(new StringWithLang(labelLg2, Lang.EN));
		}
		this.uri = uri;
	}
	
	public Serie(String id) {
		this.id=id;
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

	public void addSeeAlso(Serie sa) {
		if (seeAlso==null) {
			setSeeAlso(new ArrayList<Serie>());
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
	
	public SimpleObject getFamily() {
		return family;
	}

		public String getAltLabel() {
		return altLabel;
	}

	public void setAltLabel(String altlabel) {
		this.altLabel = altlabel;
	}

	public SimpleObject getType() {
		return type;
	}

	public void setType(SimpleObject type) {
		this.type = type;
	}

	public List<Serie> getReplaces() {
		return replaces;
	}

	public void setReplaces(List<Serie> replaces) {
		this.replaces = replaces;
	}

	public List<Serie> getIsReplacedBy() {
		return isReplacedBy;
	}

	public void setIsReplacedBy(List<Serie> isReplacedBy) {
		this.isReplacedBy = isReplacedBy;
	}

	public List<Serie> getSeeAlso() {
		return seeAlso;
	}

	public void setSeeAlso(List<Serie> seeAlso) {
		this.seeAlso = seeAlso;
	}

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
	
}
