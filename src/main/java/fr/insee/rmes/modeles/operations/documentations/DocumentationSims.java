package fr.insee.rmes.modeles.operations.documentations;

import java.util.ArrayList;
import java.util.List;

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

@JsonClassDescription("Objet représentant une documentation SIMS")
public class DocumentationSims {

    private String id = null;
    @Schema(example = "http://id.insee.fr/qualite/attribut/1907/I.18.11")
    private String uri = null;
    private List<StringWithLang> label = new ArrayList<>();

    @JsonInclude(Include.NON_NULL)
    private SimpleObject cible;

    private List<Rubrique> rubriques = new ArrayList<>();

    @JacksonXmlProperty(isAttribute = true, localName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "uri")
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @JacksonXmlProperty(localName = "Label")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<StringWithLang> getLabel() {
        return label;
    }

    public void setLabelLg1(String labelLg1) {
        label.add(new StringWithLang(labelLg1, Lang.FR));
    }

    public void setLabelLg2(String labelLg2) {
        label.add(new StringWithLang(labelLg2, Lang.EN));
    }

    @JacksonXmlProperty(localName = "Rubrique")
    @JacksonXmlElementWrapper(localName = "Rubriques", useWrapping = true)
    public List<Rubrique> getRubriques() {
        return rubriques;
    }

    public void setRubriques(List<Rubrique> rubriques) {
        this.rubriques = rubriques;
    }

    public SimpleObject getCible() {
        return cible;
    }

    public void setIdCible(String idCible) {
        if (StringUtils.isNotEmpty(idCible)) {
            this.checkCibleExists();
            cible.setId(idCible);
        }
    }

    public void setCible(String cibleUri) {
        if (StringUtils.isNotEmpty(cibleUri)) {
            this.checkCibleExists();
            cible.setUri(cibleUri);
        }
    }

    public void setLabelCibleLg1(String labelCibleLg1) {
        if (StringUtils.isNotEmpty(labelCibleLg1)) {
            this.checkCibleExists();
            List<StringWithLang> temp = cible.getLabel();
            temp.add(new StringWithLang(labelCibleLg1, Lang.FR));
            cible.setLabel(temp);
        }
    }

    public void setLabelCibleLg2(String labelCibleLg2) {
        if (StringUtils.isNotEmpty(labelCibleLg2)) {
            this.checkCibleExists();
            List<StringWithLang> temp = cible.getLabel();
            temp.add(new StringWithLang(labelCibleLg2, Lang.EN));
            cible.setLabel(temp);
        }
    }

    private void checkCibleExists() {
        if (cible == null) {
            cible = new SimpleObject();
        }
    }

}
