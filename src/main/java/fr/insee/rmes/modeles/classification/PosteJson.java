package fr.insee.rmes.modeles.classification;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Poste")
public class PosteJson extends Poste {

    @JacksonXmlProperty(localName = "contenuLimite")
  @Schema(example = "exemple de contenu limite", name = "contenuLimite")
    @Override
    public String getContenuLimite() {
        return contenuLimite;
    }

    @JacksonXmlProperty(localName = "contenuCentral")
  @Schema(example = "exemple de contenu central", name="contenuCentral")
    @Override
    public String getContenuCentral() {
        return contenuCentral;
    }

    @JacksonXmlProperty(localName = "contenuExclu")
    @Schema(example = "exemple d'exclusions", name="contenuExclu")
    @Override
    public String getContenuExclu() {
        return contenuExclu;
    }

    @JacksonXmlProperty(localName = "noteGenerale")
    @Schema(example = "exemple de contenu général", name = "noteGenerale" )
    @Override
    public String getNoteGenerale() {
        return noteGenerale;
    }
    
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonInclude(Include.NON_NULL)
    @Schema(implementation=PosteJson.class,name = "Poste")
    @Override
    public List<Poste> getPostesEnfants() {
        return postesEnfants;
    }

}
