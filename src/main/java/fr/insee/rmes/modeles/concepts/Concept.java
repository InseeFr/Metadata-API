package fr.insee.rmes.modeles.concepts;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.lang3.StringUtils;

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
 
    private String id = null;
    @Schema(example = "http://id.insee.fr/concepts/definition/c2066")
    private String uri = null;
    
    @Schema(example = "Chômage")
    @JsonInclude(Include.NON_EMPTY)
    private List<StringWithLang> intitule = new ArrayList<>();
    
    @JsonInclude(Include.NON_EMPTY)
    private List<StringWithLang> definition= new ArrayList<>();
    
    @JsonInclude(Include.NON_EMPTY)
    private List<StringWithLang> noteEditoriale= new ArrayList<>();
    
    @JsonInclude(Include.NON_EMPTY)
    private List<StringWithLang> scopeNote= new ArrayList<>();
    
    @JsonInclude(Include.NON_EMPTY)
    private SimpleObject remplace = null;

    @Schema(example = "http://id.insee.fr/concepts/definition/c1500")
    @JsonInclude(Include.NON_EMPTY)
    private SimpleObject estRemplacePar = null;
    
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

    @JacksonXmlProperty(localName = "intitule")
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
    
    @JacksonXmlProperty(localName = "definition")
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


    
    @JacksonXmlProperty(localName = "definitionCourte")
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


    public SimpleObject getRemplace() {
        return remplace;
    }

    public SimpleObject getEstRemplacePar() {
        return estRemplacePar;
    }

    public String getDateMiseAJour() {
        return dateMiseAJour.substring(0,10);
    }

    public void setDateMiseAJour(String dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

	public void setReplaces(String replaces) {
		if (StringUtils.isNotEmpty(replaces)) {
			remplace = new SimpleObject(getIdByUri(replaces),replaces);
		}
	}

	private String getIdByUri(String str) {
		if (StringUtils.isEmpty(str)) return null;
		int index=str.lastIndexOf('/')+1;
		return str.substring(index,str.length());
	}


	public void setIsReplacedBy(String isReplacedBy) {
		if (StringUtils.isNotEmpty(isReplacedBy)) {
			estRemplacePar = new SimpleObject(getIdByUri(isReplacedBy),isReplacedBy);
		}
	}

}
