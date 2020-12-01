package fr.insee.rmes.modeles.classification;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Poste")
public class PosteXml extends Poste {

    @JsonRawValue // don't encode XML
    @JacksonXmlProperty(localName = "ContenuLimite")//change name
    @Schema(example = "exemple de contenu limite", name = "ContenuLimite") // add example
    @Override
    public String getContenuLimite() {
        if (StringUtils.isNotEmpty(contenuLimite)) {
        	return contenuLimite;
        }
        else
            return null;
    }

    @JsonRawValue
    @JacksonXmlProperty(localName = "ContenuCentral")
    @Schema(example = "exemple de contenu central", name="ContenuCentral")
    @Override
    public String getContenuCentral() {
        if (StringUtils.isNotEmpty(contenuCentral)) {
        	return contenuCentral;
        }
        else
            return null;
    }

    @JsonRawValue
    @JacksonXmlProperty(localName = "ContenuExclu")
    @Schema(example = "exemple d'exclusions", name="ContenuExclu")
    @Override
    public String getContenuExclu() {
        if (StringUtils.isNotEmpty(contenuExclu)) {
        	return contenuExclu;
        }
        else
            return null;
    }

    @JsonRawValue
    @JacksonXmlProperty(localName = "NoteGenerale")
    @Schema(example = "exemple de contenu général", name = "NoteGenerale" )
    @Override
    public String getNoteGenerale() {
        if (StringUtils.isNotEmpty(noteGenerale)) {
        	return noteGenerale ;
        }
        else
            return null;
    }
    
    @JacksonXmlProperty(isAttribute = true, localName = "Poste")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonInclude(Include.NON_NULL)
    @Schema(implementation=PosteXml.class, name="Poste")
    @Override
    public List<Poste> getPostesEnfants() {
        return postesEnfants;
    }
    
}
