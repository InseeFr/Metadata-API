package fr.insee.rmes.modeles.concepts;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.StringWithLang;
import fr.insee.rmes.utils.Lang;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Definition")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Definition", description = "Objet représentant la définition d'un concept statistique de l'Insee")
public class Concept {
    @Schema(example = "c2066")
    private String id = null;
    @Schema(example = "http://id.insee.fr/concepts/definition/c2066")
    private String uri = null;
    
    @JsonInclude(Include.NON_EMPTY)
    private List<StringWithLang> intitule = new ArrayList<>();
    
    @JsonInclude(Include.NON_EMPTY)
    private List<StringWithLang> definition= new ArrayList<>();
    
    @JsonInclude(Include.NON_EMPTY)
    private List<StringWithLang> noteEditoriale= new ArrayList<>();
    
    @JsonInclude(Include.NON_EMPTY)
    private List<StringWithLang> scopeNote= new ArrayList<>();
    

    private Boolean hasLink;
    
    @JsonInclude(Include.NON_EMPTY)
    private List<SimpleObject> remplace = new ArrayList<>();

    @JsonInclude(Include.NON_EMPTY)
    private List<SimpleObject> estRemplacePar = new ArrayList<>();
    
    @Schema(example = "2020-11-10", pattern = "AAAA-MM-JJ")
    @JsonInclude(Include.NON_EMPTY)
    private String dateMiseAJour = null;

    public Concept() {}

    public Concept(String id) {
        this.id = id;
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @JsonProperty(value = "intitule")
    @JacksonXmlProperty(localName = "Intitule")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<StringWithLang> getIntitule() {
        return intitule;
    }

    public void setIntitule(List<StringWithLang> intitule) {
        this.intitule = intitule;
    }
    
    public void setIntituleFr(String intituleFr) {
        if (!intituleFr.equals("")) {
            intitule.add(new StringWithLang(intituleFr, Lang.FR));
        }
    }
    
    public void setIntituleEn(String intituleEn) {
        if (!intituleEn.equals("")) {
            intitule.add(new StringWithLang(intituleEn, Lang.EN));
        }
    }
    
    @JsonProperty(value = "definition")
    @JacksonXmlProperty(localName = "Definition")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<StringWithLang> getDefinition() {
        return definition;
    }

    public void setDefinition(List<StringWithLang> definition) {
        this.definition = definition;
    }
    
   
    @JsonProperty(value = "definitionFr")
    public void setDefinitionFr(String definitionFr) {
        if (!definitionFr.equals("")) {
            definition.add(new StringWithLang(definitionFr, Lang.FR));
        }
    }
    
    public void setDefinitionEn(String definitionEn) {
        if (!definitionEn.equals("")) {
            definition.add(new StringWithLang(definitionEn, Lang.EN));
        }
    }


    
    @JacksonXmlProperty(localName = "DefinitionCourte")
    @JsonProperty(value="definitionCourte")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<StringWithLang> getScopeNote() {
        return scopeNote;
    }

    public void setScopeNote(List<StringWithLang> scopeNote) {
        this.scopeNote = scopeNote;
    }
    
    public void setScopeNoteFr(String scopeNoteFr) {
        if (!scopeNoteFr.equals("")) {
            scopeNote.add(new StringWithLang(scopeNoteFr, Lang.FR));
        }
    }
    
    public void setScopeNoteEn(String scopeNoteEn) {
        if (!scopeNoteEn.equals("")) {
            scopeNote.add(new StringWithLang(scopeNoteEn, Lang.EN));
        }
    }
    
    @JacksonXmlProperty(localName = "NoteEditoriale")
    @JsonProperty(value = "noteEditoriale")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<StringWithLang> getNoteEditoriale() {
        return noteEditoriale;
    }

    public void setNoteEditoriale(List<StringWithLang> editorialNote) {
        this.noteEditoriale = editorialNote;
    }
    
    public void setEditorialNoteFr(String editorialNoteFr) {
        if (!editorialNoteFr.equals("")) {
            noteEditoriale.add(new StringWithLang(editorialNoteFr, Lang.FR));
        }
    }
    
    public void setEditorialNoteEn(String editorialNoteEn) {
        if (!editorialNoteEn.equals("")) {
            noteEditoriale.add(new StringWithLang(editorialNoteEn, Lang.EN));
        }
    }

    @JsonProperty(value = "remplace")
    @JacksonXmlProperty(localName = "Remplace")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<SimpleObject> getRemplace() {
        return remplace;
    }

    @JsonProperty(value = "estRemplacePar")
    @JacksonXmlProperty(localName = "EstRemplacePar")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<SimpleObject> getEstRemplacePar() {
        return estRemplacePar;
    }

    @JsonProperty(value = "dateMiseAJour")
    @JacksonXmlProperty(localName = "DateMiseAJour")
    public String getDateMiseAJour() {
        return dateMiseAJour.substring(0,10);
    }

    public void setDateMiseAJour(String dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    @JsonIgnore
    public Boolean getHasLink() {
		return hasLink;
	}

    @JsonProperty(value = "hasLink")
	public void setHasLink(Boolean hasLink) {
		this.hasLink = hasLink;
	}

	public void setLinks(List<ConceptLink> links) {
		links.forEach(link -> {
			SimpleObject so = new SimpleObject(link.getId(), link.getUri());
			if (StringUtils.equals("replaces",link.getTypeOfLink())) {
				remplace.add(so);
			}else if (StringUtils.equals("isReplacedBy",link.getTypeOfLink())) {
				estRemplacePar.add(so);
			}
		});		
	}

}
