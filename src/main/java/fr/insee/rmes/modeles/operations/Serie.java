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

@Schema(description = "Objet représentant une série d'opérations statistiques")
public class Serie {

    private String id = null;
    private List<StringWithLang> label = new ArrayList<>();

    @Schema(example = "http://id.insee.fr/operations/serie/s1234")
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

    @Schema(example = "1011")
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
    private List<SimpleObject> publishers;
    @JsonInclude(Include.NON_NULL)
    private List<SimpleObject> contributors;

    public Serie(String uri, String id, String labelLg1, String labelLg2) {
        this.id = id;
        label.add(new StringWithLang(labelLg1, Lang.FR));
        if ( ! labelLg2.equals("")) {
            label.add(new StringWithLang(labelLg2, Lang.EN));
        }
        this.uri = uri;
    }

    public Serie() {
        super();
    }

    public void addOperation(Operation op) {
        if (operations == null) {
            this.setOperations(new ArrayList<>());
        }
        this.operations.add(op);
    }

    public void addIndicateur(Indicateur indic) {
        if (indicateurs == null) {
            this.setIndicateurs(new ArrayList<>());
        }
        this.indicateurs.add(indic);
    }

    public void addSeeAlso(SimpleObject sa) {
        if (seeAlso == null) {
            this.setSeeAlso(new ArrayList<>());
        }
        this.seeAlso.add(sa);
    }

    public void addReplaces(Serie rep) {
        if (replaces == null) {
            this.setReplaces(new ArrayList<>());
        }
        this.replaces.add(rep);
    }

    public void addIsReplacedBy(Serie irb) {
        if (isReplacedBy == null) {
            this.setIsReplacedBy(new ArrayList<>());
        }
        this.isReplacedBy.add(irb);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JacksonXmlProperty(localName = "label")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<StringWithLang> getLabel() {
        return label;
    }

    public String getSimsId() {
        return simsId;
    }

    public void setSimsId(String simsId) {
        if ( ! simsId.equals("")) {
            this.simsId = simsId;
        }
    }

    @JacksonXmlProperty(isAttribute = true, localName = "Operation")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "Indicateur")
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
    @JacksonXmlProperty(isAttribute = true, localName = "Famille")
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
    @JacksonXmlProperty(isAttribute = true, localName = "remplace")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Serie> getReplaces() {
        return replaces;
    }

    public void setReplaces(List<Serie> replaces) {
        this.replaces = replaces;
    }

    @JsonProperty("estRemplacePar")
    @JacksonXmlProperty(isAttribute = true, localName = "estRemplacePar")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Serie> getIsReplacedBy() {
        return isReplacedBy;
    }

    public void setIsReplacedBy(List<Serie> isReplacedBy) {
        this.isReplacedBy = isReplacedBy;
    }

    @JsonProperty("voirAussi")
    @JacksonXmlProperty(isAttribute = true, localName = "voirAussi")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<SimpleObject> getSeeAlso() {
        return seeAlso;
    }

    public void setSeeAlso(List<SimpleObject> seeAlso) {
        this.seeAlso = seeAlso;
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
    public List<StringWithLang> getAbstractSerie() {
        return abstractSerie;
    }

    public void setAbstractLg1(String abstractLg1) {
        setAbstract(abstractLg1, Lang.FR);
    }

    public void setAbstractLg2(String abstractLg2) {
        setAbstract(abstractLg2, Lang.FR);
    }

    private void setAbstract(String abstr, Lang lang) {
        if (StringUtils.isNotEmpty(abstr)) {
            if (abstractSerie == null) {
                abstractSerie = new ArrayList<>();
            }
            abstractSerie.add(new StringWithLang(abstr, lang));
        }
    }

    @JsonProperty("noteHistorique")
    @JacksonXmlProperty(localName = "noteHistorique")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<StringWithLang> getHistoryNote() {
        return historyNote;
    }

    public void setHistoryNoteLg1(String str) {
        setHistoryNote(str, Lang.FR);
    }

    public void setHistoryNoteLg2(String str) {
        setHistoryNote(str, Lang.EN);
    }

    private void setHistoryNote(String str, Lang lang) {
        if (StringUtils.isNotEmpty(str)) {
            if (historyNote == null) {
                historyNote = new ArrayList<>();
            }
            historyNote.add(new StringWithLang(str, lang));
        }
    }

    @JsonProperty("organismeResponsable")
    @JacksonXmlProperty(isAttribute = true, localName = "organismeResponsable")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<SimpleObject> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<SimpleObject> publishers) {
        this.publishers = publishers;
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

    public void setLabelFr(String labelFr) {
        setLabel(labelFr, Lang.FR);
    }
    
    public void setLabelEn(String labelEn) {
        setLabel(labelEn, Lang.EN);
    }

    private void setLabel(String newlabel, Lang lang) {
        if (StringUtils.isNotEmpty(newlabel)) {
            label.add(new StringWithLang(newlabel, lang));
        }
    }

    @JacksonXmlProperty(localName = "altLabel")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<StringWithLang> getAltLabel() {
        return altLabel;
    }

    public void setAltLabel(String altLabelLg1, String altLabelLg2) {
        if ( ! altLabelLg1.equals("")) {
            initAltLabel();
            altLabel.add(new StringWithLang(altLabelLg1, Lang.FR));
        }
        if ( ! altLabelLg2.equals("")) {
            initAltLabel();
            altLabel.add(new StringWithLang(altLabelLg2, Lang.EN));
        }
    }

    private void initAltLabel() {
        if (altLabel == null) {
            altLabel = new ArrayList<>();
        }
    }

}
