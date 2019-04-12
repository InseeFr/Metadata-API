package fr.insee.rmes.api.operations;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Serie {

	private String id = null;
	private String labelLg1 = null;
	private String labelLg2 = null;
	private String uri = null;
	

	@JsonInclude(Include.NON_NULL)
	//private Famille family = null;
	private SimpleObject family = null;

	@JsonInclude(Include.NON_NULL)
	private String abstractLg1 = null;
	@JsonInclude(Include.NON_NULL)
	private String abstractLg2 = null;
	@JsonInclude(Include.NON_NULL)
	private String historyNoteLg1 = null;
	@JsonInclude(Include.NON_NULL)
	private String historyNoteLg2 = null;
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
		this.labelLg1 = labelLg1;
		this.labelLg2 = labelLg2;
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
	
	public String getLabelLg1() {
		return labelLg1;
	}

	public void setLabelLg1(String seriesLabelLg1) {
		this.labelLg1 = seriesLabelLg1;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabelLg2() {
		return labelLg2;
	}

	public void setLabelLg2(String labelLg2) {
		this.labelLg2 = labelLg2;
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

	public String getAbstractLg1() {
		return abstractLg1;
	}

	public void setAbstractLg1(String abstractLg1) {
		this.abstractLg1 = abstractLg1;
	}

	public String getAbstractLg2() {
		return abstractLg2;
	}

	public void setAbstractLg2(String abstractLg2) {
		this.abstractLg2 = abstractLg2;
	}

	public String getHistoryNoteLg1() {
		return historyNoteLg1;
	}

	public void setHistoryNoteLg1(String historyNoteLg1) {
		this.historyNoteLg1 = historyNoteLg1;
	}

	public String getHistoryNoteLg2() {
		return historyNoteLg2;
	}

	public void setHistoryNoteLg2(String historyNoteLg2) {
		this.historyNoteLg2 = historyNoteLg2;
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
}
