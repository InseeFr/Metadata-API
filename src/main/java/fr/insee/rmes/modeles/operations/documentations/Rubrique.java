package fr.insee.rmes.modeles.operations.documentations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import fr.insee.rmes.modeles.StringWithLang;
import fr.insee.rmes.modeles.operations.SimpleObject;
import fr.insee.rmes.utils.Lang;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonClassDescription("Objet représentant une rubrique d'une documentation SIMS")
public class Rubrique {

    private String id = null;
    @Schema(example = "http://id.insee.fr/qualite/attribut/1907/I.18.11")
    private String uri = null;
    @Schema(description = "Titre de la rubrique")
    private List<StringWithLang> titre = new ArrayList<>();
    private String idParent = null;
    private String type;

    /* CODE_LIST */
    @JsonInclude(Include.NON_NULL)
    private List<SimpleObject> valeurCode;

    /* DATE */
    @JsonInclude(Include.NON_NULL)
    private String valeurSimple = null;

    /* ORGANISATION */
    @JsonInclude(Include.NON_NULL)
    private SimpleObject valeurOrganisation;
    
    /* GEOGRAPHY */
    @JsonInclude(Include.NON_NULL)
    private SimpleObject valeurGeographie;

    /* TEXT - RICH_TEXT */
    @JsonInclude(Include.NON_NULL)
    private List<StringWithLang> label;

    /* RICH_TEXT */
    @JsonInclude(Include.NON_NULL)
    private List<RubriqueRichText> richTexts;

    public Rubrique(String id, String uri, String type) {
        this.id = id;
        this.uri = uri;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    @JacksonXmlProperty(localName = "titre")
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

    @JacksonXmlProperty(localName = "label")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<StringWithLang> getLabel() {
        return label;
    }

    public void setLabelLg1(String labelLg1) {
        if (StringUtils.isNotEmpty(labelLg1)) {
            if (label == null) {
                label = new ArrayList<>();
            }
            label.add(new StringWithLang(labelLg1, Lang.FR));
        }
    }

    public void setLabelLg2(String labelLg2) {
        if (StringUtils.isNotEmpty(labelLg2)) {
            if (label == null) {
                label = new ArrayList<>();
            }
            label.add(new StringWithLang(labelLg2, Lang.EN));
        }
    }

   
    public SimpleObject getValeurOrganisation() {
        return valeurOrganisation;
    }

    public void setValeurOrganisation(SimpleObject valeurOrganisation) {
        this.valeurOrganisation = valeurOrganisation;
    }

    public void setTitre(String titreLg1, String titreLg2) {
        if (titre == null) {
            titre = new ArrayList<>();
        }
        if (StringUtils.isNotEmpty(titreLg1)) {
            titre.add(new StringWithLang(titreLg1, Lang.FR));
        }
        if (StringUtils.isNotEmpty(titreLg2)) {
            titre.add(new StringWithLang(titreLg2, Lang.EN));
        }
    }

    @JacksonXmlProperty(localName = "contenu")
    @JacksonXmlElementWrapper(localName = "contenus", useWrapping = true)
    public List<RubriqueRichText> getRichTexts() {
        return richTexts;
    }

    public void setRichTexts(List<RubriqueRichText> richTexts) {
        this.richTexts = richTexts;
    }
    
    public void addRichTexts(RubriqueRichText r) {
        if (richTexts == null) {
        	richTexts = new ArrayList<>();
        }
        this.richTexts.add(r);
    }

    public List<SimpleObject> getValeurCode() {
        return valeurCode;
    }

    public void setValeurCode(List<SimpleObject> valeurCode) {
        this.valeurCode = valeurCode;
    }
    
    public void addValeurCode(SimpleObject so) {
        if (valeurCode == null) {
            valeurCode = new ArrayList<>();
        }
        this.valeurCode.add(so);
    }

    public SimpleObject getValeurGeographie() {
        return valeurGeographie;
    }

    public void setValeurGeographie(SimpleObject valeurGeographie) {
        this.valeurGeographie = valeurGeographie;
    }

	@Override
	public int hashCode() {
		return Objects.hash(id, idParent, label, richTexts, titre, type, uri, valeurCode, valeurGeographie,
				valeurOrganisation, valeurSimple);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rubrique other = (Rubrique) obj;
		return Objects.equals(id, other.id) && Objects.equals(idParent, other.idParent)
				&& Objects.equals(label, other.label) && Objects.equals(richTexts, other.richTexts)
				&& Objects.equals(titre, other.titre) && Objects.equals(type, other.type)
				&& Objects.equals(uri, other.uri) && Objects.equals(valeurCode, other.valeurCode)
				&& Objects.equals(valeurGeographie, other.valeurGeographie)
				&& Objects.equals(valeurOrganisation, other.valeurOrganisation)
				&& Objects.equals(valeurSimple, other.valeurSimple);
	}

}
