package fr.insee.rmes.modeles.operations;

public class CsvSerie {

    private String familyId = null;
    private String familyLabelLg1 = null;
    private String familyLabelLg2 = null;
    private String family = null;

    private String seriesId = null;
    private String seriesLabelLg1 = null;
    private String seriesLabelLg2 = null;
    private String series = null;

    private String seriesAbstractLg1 = null;
    private String seriesAbstractLg2 = null;
    private String seriesHistoryNoteLg1 = null;
    private String seriesHistoryNoteLg2 = null;
    private String seriesAltLabelLg1 = null;
    private String seriesAltLabelLg2 = null;

    private Boolean hasSeeAlso = null;
    private Boolean hasReplaces = null;
    private Boolean hasIsReplacedBy = null;

    private String periodicity = null;
    private String periodicityLabelLg1 = null;
    private String periodicityLabelLg2 = null;
    private String periodicityId = null;

    private String type = null;
    private String typeLabelLg1 = null;
    private String typeLabelLg2 = null;
    private String typeId = null;

    private Boolean hasOperation = null;
    private Boolean hasIndic = null;

    private Boolean hasContributor = null;
    private Boolean hasCreator = null;

    private String simsId = null;

    public CsvSerie() {}

    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    public String getFamilyLabelLg1() {
        return familyLabelLg1;
    }

    public void setFamilyLabelLg1(String familyLabelLg1) {
        this.familyLabelLg1 = familyLabelLg1;
    }

    public String getSeriesLabelLg1() {
        return seriesLabelLg1;
    }

    public void setSeriesLabelLg1(String seriesLabelLg1) {
        this.seriesLabelLg1 = seriesLabelLg1;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getFamilyLabelLg2() {
        return familyLabelLg2;
    }

    public void setFamilyLabelLg2(String familyLabelLg2) {
        this.familyLabelLg2 = familyLabelLg2;
    }

    public String getSeriesLabelLg2() {
        return seriesLabelLg2;
    }

    public void setSeriesLabelLg2(String seriesLabelLg2) {
        this.seriesLabelLg2 = seriesLabelLg2;
    }

    public String getSeriesAbstractLg1() {
        return seriesAbstractLg1;
    }

    public void setSeriesAbstractLg1(String seriesAbstractLg1) {
        this.seriesAbstractLg1 = seriesAbstractLg1;
    }

    public String getSeriesAbstractLg2() {
        return seriesAbstractLg2;
    }

    public void setSeriesAbstractLg2(String seriesAbstractLg2) {
        this.seriesAbstractLg2 = seriesAbstractLg2;
    }

    public String getSeriesHistoryNoteLg1() {
        return seriesHistoryNoteLg1;
    }

    public void setSeriesHistoryNoteLg1(String seriesHistoryNoteLg1) {
        this.seriesHistoryNoteLg1 = seriesHistoryNoteLg1;
    }

    public String getSeriesHistoryNoteLg2() {
        return seriesHistoryNoteLg2;
    }

    public void setSeriesHistoryNoteLg2(String seriesHistoryNoteLg2) {
        this.seriesHistoryNoteLg2 = seriesHistoryNoteLg2;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeLabelLg1() {
        return typeLabelLg1;
    }

    public void setTypeLabelLg1(String typeLabelLg1) {
        this.typeLabelLg1 = typeLabelLg1;
    }

    public String getTypeLabelLg2() {
        return typeLabelLg2;
    }

    public void setTypeLabelLg2(String typeLabelLg2) {
        this.typeLabelLg2 = typeLabelLg2;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getSimsId() {
        return simsId;
    }

    public void setSimsId(String simsId) {
        this.simsId = simsId;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Boolean getHasSeeAlso() {
        return hasSeeAlso;
    }

    public void setHasSeeAlso(Boolean hasSeeAlso) {
        this.hasSeeAlso = hasSeeAlso;
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

    public Boolean getHasOperation() {
        return hasOperation;
    }

    public void setHasOperation(Boolean hasOperation) {
        this.hasOperation = hasOperation;
    }

    public Boolean getHasIndic() {
        return hasIndic;
    }

    public void setHasIndic(Boolean hasIndic) {
        this.hasIndic = hasIndic;
    }

    public Boolean getHasContributor() {
        return hasContributor;
    }

    public void setHasContributor(Boolean hasContributor) {
        this.hasContributor = hasContributor;
    }

    public Boolean getHasCreator() {
        return hasCreator;
    }

    public void setHasCreator(Boolean hasCreator) {
        this.hasCreator = hasCreator;
    }

    public String getSeriesAltLabelLg1() {
        return seriesAltLabelLg1;
    }

    public void setSeriesAltLabelLg1(String seriesAltLabelLg1) {
        this.seriesAltLabelLg1 = seriesAltLabelLg1;
    }

    public String getSeriesAltLabelLg2() {
        return seriesAltLabelLg2;
    }

    public void setSeriesAltLabelLg2(String seriesAltLabelLg2) {
        this.seriesAltLabelLg2 = seriesAltLabelLg2;
    }

}
