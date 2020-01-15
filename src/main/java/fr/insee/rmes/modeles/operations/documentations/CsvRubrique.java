package fr.insee.rmes.modeles.operations.documentations;

public class CsvRubrique {

    private String id = null;
    private String uri = null;
    private String idParent = null;
    private String titreLg1;
    private String titreLg2;
    private String type;

    private String valeurSimple = null;
    private String labelLg1;
    private String labelLg2;
    private String codeUri;
    private String organisationUri;

    private String labelObjLg1;
    private String labelObjLg2;

    private Boolean hasDoc;

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

    public String getIdParent() {
        return idParent;
    }

    public void setIdParent(String idParent) {
        this.idParent = idParent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValeurSimple() {
        return valeurSimple;
    }

    public void setValeurSimple(String valeurSimple) {
        this.valeurSimple = valeurSimple;
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

    public String getCodeUri() {
        return codeUri;
    }

    public void setCodeUri(String codeUri) {
        this.codeUri = codeUri;
    }

    public String getOrganisationUri() {
        return organisationUri;
    }

    public void setOrganisationUri(String organisationUri) {
        this.organisationUri = organisationUri;
    }

    public Boolean isHasDoc() {
        return hasDoc;
    }

    public void setHasDoc(Boolean hasDoc) {
        this.hasDoc = hasDoc;
    }

    public String getLabelObjLg1() {
        return labelObjLg1;
    }

    public void setLabelObjLg1(String labelObjLg1) {
        this.labelObjLg1 = labelObjLg1;
    }

    public String getLabelObjLg2() {
        return labelObjLg2;
    }

    public void setLabelObjLg2(String labelObjLg2) {
        this.labelObjLg2 = labelObjLg2;
    }

    public String getTitreLg1() {
        return titreLg1;
    }

    public void setTitreLg1(String titreLg1) {
        this.titreLg1 = titreLg1;
    }

    public String getTitreLg2() {
        return titreLg2;
    }

    public void setTitreLg2(String titreLg2) {
        this.titreLg2 = titreLg2;
    }

}
