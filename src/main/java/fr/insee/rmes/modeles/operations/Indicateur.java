package fr.insee.rmes.modeles.operations;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import fr.insee.rmes.modeles.StringWithLang;
import fr.insee.rmes.utils.Lang;
import io.swagger.v3.oas.annotations.media.Schema;

public class Indicateur {

    private String id = null;
    @Schema(example = "http://id.insee.fr/produits/indicateur/p1647")
    private String uri = null;
    private List<StringWithLang> label = new ArrayList<>();
    @JsonInclude(Include.NON_NULL)
    private List<StringWithLang> altLabel;

    @JsonInclude(Include.NON_NULL)
    private List<StringWithLang> abstractIndic = null;
    @JsonInclude(Include.NON_NULL)
    private List<StringWithLang> historyNote;

    @JsonInclude(Include.NON_NULL)
    private SimpleObject publisher;
    @JsonInclude(Include.NON_NULL)
    private List<SimpleObject> contributors;
    @JsonInclude(Include.NON_NULL)
    private List<Indicateur> replaces;
    @JsonInclude(Include.NON_NULL)
    private List<Indicateur> isReplacedBy;
    @JsonInclude(Include.NON_NULL)
    private List<ObjectWithSimsId> seeAlso;
    @JsonInclude(Include.NON_NULL)
    private List<Serie> wasGeneratedBy;

    @JsonInclude(Include.NON_NULL)
    private SimpleObject accrualPeriodicity = null;

    @JsonInclude(Include.NON_NULL)
    @Schema(example = "1011")
    private String simsId = null;

    public Indicateur(String uri, String id, String labelLg1, String labelLg2, String simsId) {
        super();
        this.id = id;
        label.add(new StringWithLang(labelLg1, Lang.FR));
        if ( ! labelLg2.equals("")) {
            label.add(new StringWithLang(labelLg2, Lang.EN));
        }
        if ( ! simsId.equals("")) {
            this.simsId = simsId;
        }
        this.uri = uri;
    }

    public Indicateur() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JacksonXmlProperty(localName = "Label")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<StringWithLang> getLabel() {
        return label;
    }

    public void setLabel(List<StringWithLang> label) {
        this.label = label;
    }

    @JacksonXmlProperty(localName = "SimsId")
    public String getSimsId() {
        return simsId;
    }

    public void setSimsId(String simsId) {
        if (StringUtils.isNotEmpty(simsId)) {
            this.simsId = simsId;
        }
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setLabelFr(String labelFr) {
        this.setLabel(labelFr, Lang.FR);
    }

    public void setLabelEn(String labelEn) {
        this.setLabel(labelEn, Lang.EN);
    }

    private void setLabel(String newlabel, Lang lang) {
        if (StringUtils.isNotEmpty(newlabel)) {
            label.add(new StringWithLang(newlabel, lang));
        }
    }

    @JacksonXmlProperty(localName = "AltLabel")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<StringWithLang> getAltLabel() {
        return altLabel;
    }

    public void setAltLabel(List<StringWithLang> altLabel) {
        this.altLabel = altLabel;
    }

    public void setAltLabel(String altLabelLg1, String altLabelLg2) {
        if ( ! altLabelLg1.equals("")) {
            this.initAltLabel();
            altLabel.add(new StringWithLang(altLabelLg1, Lang.FR));
        }
        if ( ! altLabelLg2.equals("")) {
            this.initAltLabel();
            altLabel.add(new StringWithLang(altLabelLg2, Lang.EN));
        }
    }

    private void initAltLabel() {
        if (altLabel == null) {
            altLabel = new ArrayList<>();
        }
    }

    public void addSeeAlso(ObjectWithSimsId sa) {
        if (seeAlso == null) {
            this.setSeeAlso(new ArrayList<>());
        }
        this.seeAlso.add(sa);
    }

    public void addReplaces(Indicateur rep) {
        if (replaces == null) {
            this.setReplaces(new ArrayList<>());
        }
        this.replaces.add(rep);
    }

    public void addIsReplacedBy(Indicateur irb) {
        if (isReplacedBy == null) {
            this.setIsReplacedBy(new ArrayList<>());
        }
        this.isReplacedBy.add(irb);
    }

    @JsonProperty("remplace")
    @JacksonXmlProperty(isAttribute = true, localName = "remplace")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Indicateur> getReplaces() {
        return replaces;
    }

    public void setReplaces(List<Indicateur> replaces) {
        this.replaces = replaces;
    }

    @JsonProperty("estRemplacePar")
    @JacksonXmlProperty(isAttribute = true, localName = "estRemplacePar")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Indicateur> getIsReplacedBy() {
        return isReplacedBy;
    }

    public void setIsReplacedBy(List<Indicateur> isReplacedBy) {
        this.isReplacedBy = isReplacedBy;
    }

    @JsonProperty("voirAussi")
    @JacksonXmlProperty(isAttribute = true, localName = "voirAussi")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<ObjectWithSimsId> getSeeAlso() {
        return seeAlso;
    }

    public void setSeeAlso(List<ObjectWithSimsId> seeAlso) {
        this.seeAlso = seeAlso;
    }

    @JsonProperty("produitDe")
    @JacksonXmlProperty(isAttribute = true, localName = "produitDe")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Serie> getWasGeneratedBy() {
        return wasGeneratedBy;
    }

    public void setWasGeneratedBy(List<Serie> wasGeneratedBy) {
        this.wasGeneratedBy = wasGeneratedBy;
    }

    @JsonProperty("periodicite")
    @JacksonXmlProperty(isAttribute = true, localName = "periodicite")
    @JacksonXmlElementWrapper(useWrapping = false)
    public SimpleObject getAccrualPeriodicity() {
        return accrualPeriodicity;
    }

    public void setAccrualPeriodicity(SimpleObject accrualPeriodicity) {
        this.accrualPeriodicity = accrualPeriodicity;
    }

    @JsonProperty("resume")
    @JacksonXmlProperty(localName = "resume")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<StringWithLang> getAbstractIndic() {
        return abstractIndic;
    }

    public void setAbstractLg1(String abstractLg1) {
        this.setAbstract(abstractLg1, Lang.FR);
    }

    public void setAbstractLg2(String abstractLg2) {
        this.setAbstract(abstractLg2, Lang.EN);
    }

    private void setAbstract(String abstr, Lang lang) {
        if (StringUtils.isNotEmpty(abstr)) {
            if (abstractIndic == null) {
                abstractIndic = new ArrayList<>();
            }
            abstractIndic.add(new StringWithLang(abstr, lang));
        }
    }

    @JsonProperty("noteHistorique")
    @JacksonXmlProperty(localName = "noteHistorique")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<StringWithLang> getHistoryNote() {
        return historyNote;
    }

    public void setHistoryNoteLg1(String str) {
        if (StringUtils.isNotEmpty(str)) {
            if (historyNote == null) {
                historyNote = new ArrayList<>();
            }
            historyNote.add(new StringWithLang(str, Lang.FR));
        }
    }

    public void setHistoryNoteLg2(String str) {
        if (StringUtils.isNotEmpty(str)) {
            if (historyNote == null) {
                historyNote = new ArrayList<>();
            }
            historyNote.add(new StringWithLang(str, Lang.EN));
        }
    }

    @JsonProperty("organismeResponsable")
    @JacksonXmlProperty(isAttribute = true, localName = "organismeResponsable")
    @JacksonXmlElementWrapper(useWrapping = false)
    public SimpleObject getPublisher() {
        return publisher;
    }

    public void setPublisher(SimpleObject publisher) {
        this.publisher = publisher;
    }

    @JsonProperty("partenaire")
    @JacksonXmlProperty(isAttribute = true, localName = "partenaire")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<SimpleObject> getContributors() {
        return contributors;
    }

    public void setContributors(List<SimpleObject> contributors) {
        this.contributors = contributors;
    }

}