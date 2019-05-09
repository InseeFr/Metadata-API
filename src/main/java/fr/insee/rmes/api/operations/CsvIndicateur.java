package fr.insee.rmes.api.operations;

public class CsvIndicateur {
	
	private String id;
	private String indic;//uri
	private String labelLg1;
	private String labelLg2;
	private String altLabelLg1;
	private String altLabelLg2;
	
	private String abstractLg1;
	private String abstractLg2;
	private String historyNoteLg1;
	private String historyNoteLg2;
	
	private Boolean hasContributor;
	private Boolean hasReplaces;
	private Boolean hasIsReplacedBy;
	private Boolean hasSeeAlso;
	private Boolean hasWasGeneratedBy ;
	
	private String periodicity;
	private String periodicityLabelLg1;
	private String periodicityLabelLg2;
	private String periodicityId;
	
	private String uriCreator;
	private String idCreator;
	private String labelFrCreator;
	private String labelEnCreator;

	private String simsId = null;
		
	public CsvIndicateur() {
	}


	public String getLabelLg1() {
		return labelLg1;
	}


	public void setLabelLg1(String labelLg1) {
		this.labelLg1 = labelLg1;
	}


	public String getLabelLg2() {
		return labelLg2;
	}


	public void setLabelLg2(String labelLg2) {
		this.labelLg2 = labelLg2;
	}


	public String getAltLabelLg1() {
		return altLabelLg1;
	}


	public void setAltLabelLg1(String altLabelLg1) {
		this.altLabelLg1 = altLabelLg1;
	}


	public String getAltLabelLg2() {
		return altLabelLg2;
	}


	public void setAltLabelLg2(String altLabelLg2) {
		this.altLabelLg2 = altLabelLg2;
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


	public Boolean getHasContributor() {
		return hasContributor;
	}


	public void setHasContributor(Boolean hasContributor) {
		this.hasContributor = hasContributor;
	}


	public Boolean getHasReplaces() {
		return hasReplaces;
	}


	public void setHasReplaces(Boolean hasReplaces) {
		this.hasReplaces = hasReplaces;
	}


	public Boolean getHasIsReplacedBy() {
		return hasIsReplacedBy;
	}


	public void setHasIsReplacedBy(Boolean hasIsReplacedBy) {
		this.hasIsReplacedBy = hasIsReplacedBy;
	}


	public Boolean getHasSeeAlso() {
		return hasSeeAlso;
	}


	public void setHasSeeAlso(Boolean hasSeeAlso) {
		this.hasSeeAlso = hasSeeAlso;
	}


	public Boolean getHasWasGeneratedBy() {
		return hasWasGeneratedBy;
	}


	public void setHasWasGeneratedBy(Boolean hasWasGeneratedBy) {
		this.hasWasGeneratedBy = hasWasGeneratedBy;
	}


	public String getSimsId() {
		return simsId;
	}


	public void setSimsId(String simsId) {
		this.simsId = simsId;
	}


	public String getPeriodicity() {
		return periodicity;
	}


	public void setPeriodicity(String periodicity) {
		this.periodicity = periodicity;
	}


	public String getPeriodicityLabelLg1() {
		return periodicityLabelLg1;
	}


	public void setPeriodicityLabelLg1(String periodicityLabelLg1) {
		this.periodicityLabelLg1 = periodicityLabelLg1;
	}


	public String getPeriodicityLabelLg2() {
		return periodicityLabelLg2;
	}


	public void setPeriodicityLabelLg2(String periodicityLabelLg2) {
		this.periodicityLabelLg2 = periodicityLabelLg2;
	}


	public String getPeriodicityId() {
		return periodicityId;
	}


	public void setPeriodicityId(String periodicityId) {
		this.periodicityId = periodicityId;
	}


	public String getUriCreator() {
		return uriCreator;
	}


	public void setUriCreator(String uriCreator) {
		this.uriCreator = uriCreator;
	}


	public String getIdCreator() {
		return idCreator;
	}


	public void setIdCreator(String idCreator) {
		this.idCreator = idCreator;
	}


	public String getLabelFrCreator() {
		return labelFrCreator;
	}


	public void setLabelFrCreator(String labelFrCreator) {
		this.labelFrCreator = labelFrCreator;
	}


	public String getLabelEnCreator() {
		return labelEnCreator;
	}


	public void setLabelEnCreator(String labelEnCreator) {
		this.labelEnCreator = labelEnCreator;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getIndic() {
		return indic;
	}


	public void setIndic(String indic) {
		this.indic = indic;
	}


}
