package fr.insee.rmes.modeles.concepts;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonAlias;
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
    private List<StringWithLang> editorialNote= new ArrayList<>();
    
    @JsonInclude(Include.NON_EMPTY)
    private List<StringWithLang> scopeNote= new ArrayList<>();
    
    @JsonAlias("replaces")
    @JsonInclude(Include.NON_EMPTY)
    private String remplace = null;
    @Schema(example = "http://id.insee.fr/concepts/definition/c1500")
    @JsonAlias("isReplacedBy")
    @JsonInclude(Include.NON_EMPTY)
    private String estRemplacePar = null;
    
    @JsonInclude(Include.NON_EMPTY)
    private String updateDate = null;

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
    
    @JacksonXmlProperty(localName = "remarques")
    @JsonProperty(value="remarques")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<StringWithLang> getEditorialNote() {
        return editorialNote;
    }

    public void setEditorialNote(List<StringWithLang> editorialNote) {
        this.editorialNote = editorialNote;
    }
    
    public void setEditorialNoteFr(String editorialNoteFr) {
        if (!editorialNoteFr.equals("")) {
            editorialNote.add(new StringWithLang(editorialNoteFr, Lang.FR));
        }
    }
    
    public void setEditorialNoteEn(String editorialNoteEn) {
        if (!editorialNoteEn.equals("")) {
            editorialNote.add(new StringWithLang(editorialNoteEn, Lang.EN));
        }
    }


    public String getRemplace() {
        return remplace;
    }

    public void setRemplace(String remplace) {
        this.remplace = remplace;
    }

    public String getEstRemplacePar() {
        return estRemplacePar;
    }

    public void setEstRemplacePar(String estRemplacePar) {
        this.estRemplacePar = estRemplacePar;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

}
