package fr.insee.rmes.modeles.classification;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Poste")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Schema(description = "Objet représentant un poste d'une nomenclature", oneOf = { PosteJson.class, PosteXml.class })
public class Poste {
    @Schema(description = "URI du poste de la nomenclature",example = "http://id.insee.fr/codes/nafr2/sousClasse/23.99Z")
    String uri;
    @Schema(example = "23.99Z")
    String code;
    @Schema(example = "http://id.insee.fr/codes/nafr2/classe/23.99")
    String uriParent;
    @Schema(example = "23.99")
    String codeParent;
    @Schema(example = "Fabrication d'autres produits minéraux non métalliques n.c.a.")
    String intituleFr;
    @Schema(example = "Manufacture of other non-metallic mineral products n.e.c.")
    String intituleEn;
    String contenuLimite;
    String contenuCentral;
    String contenuExclu;
    String noteGenerale;


    List<Poste> postesEnfants;

    public Poste() {
        // No-args constructor needed for JAXB
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getUriParent() {
        return uriParent;
    }

    public void setUriParent(String uriParent) {
        this.uriParent = uriParent;
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getCodeParent() {
        return codeParent;
    }

    public void setCodeParent(String codeParent) {
        this.codeParent = codeParent;
    }

    @JacksonXmlProperty(localName = "IntituleFr")
    public String getIntituleFr() {
        return intituleFr;
    }

    public void setIntituleFr(String intituleFr) {
        this.intituleFr = intituleFr;
    }

    @JacksonXmlProperty(localName = "IntituleEn")
    public String getIntituleEn() {
        return intituleEn;
    }

    public void setIntituleEn(String intituleEn) {
        this.intituleEn = intituleEn;
    }

    public void setContenuLimite(String contenuLimite) {
        this.contenuLimite = contenuLimite;
    }

    public void setContenuCentral(String contenuCentral) {
        this.contenuCentral = contenuCentral;
    }

    public void setContenuExclu(String contenuExclu) {
        this.contenuExclu = contenuExclu;
    }

    public void setNoteGenerale(String noteGenerale) {
        this.noteGenerale = noteGenerale;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "Poste")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonInclude(Include.NON_NULL)
    @Schema(implementation=Poste.class, example="")
    public List<Poste> getPostesEnfants() {
        return postesEnfants;
    }

    public void setPostesEnfants(List<Poste> postesEnfants) {
        this.postesEnfants = postesEnfants;
    }

    public void addPosteEnfant(Poste posteEnfant) {
        if (postesEnfants == null) {
            postesEnfants = new ArrayList<>();
        }
        postesEnfants.add(posteEnfant);
    }

//    @Schema(example = "exemple de contenu limite", name = "ContenuLimite")
//    @JacksonXmlProperty(localName = "ContenuLimite")
    public String getContenuLimite() {
        return contenuLimite;
    }

//    @Schema(example = "exemple de contenu central", name="ContenuCentral")
//    @JacksonXmlProperty(localName = "ContenuCentral")
    public String getContenuCentral() {
        return contenuCentral;
    }

    //@Schema(example = "exemple d'exclusions", name="ContenuExclu")
    //@JacksonXmlProperty(localName = "ContenuExclu")
    public String getContenuExclu() {
        return contenuExclu;
    }

    public String getNoteGenerale() {
        return noteGenerale;
    }

}
