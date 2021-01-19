package fr.insee.rmes.modeles.operations;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import fr.insee.rmes.modeles.StringWithLang;
import fr.insee.rmes.utils.Lang;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objet représentant une famille d'opérations statistiques")
public class Famille {

    private String id = null;
    private List<StringWithLang> label = new ArrayList<>();

    @Schema(example = "http://id.insee.fr/operations/famille/s1")
    private String uri = null;

    @JsonInclude(Include.NON_NULL)
    private List<Serie> series;

    @JsonInclude(Include.NON_NULL)
    private List<StringWithLang> altLabel;

    public Famille(String uri, String id, String labelLg1, String labelLg2, Serie serie) {
        if (serie != null) {
            this.setSeries(new ArrayList<>());
            series.add(serie);
        }
        this.id = id;
        label.add(new StringWithLang(labelLg1, Lang.FR));
        if (!labelLg2.equals("")) {
            label.add(new StringWithLang(labelLg2, Lang.EN));
        }
        this.uri = uri;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "Serie")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    public void addSerie(Serie serie) {
        if (series == null) {
            this.setSeries(new ArrayList<>());
        }
        this.series.add(serie);
    }

    @JacksonXmlProperty(isAttribute = true, localName = "id")
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

    @JacksonXmlProperty(isAttribute = true, localName = "uri")
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @JacksonXmlProperty(localName = "AltLabel")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<StringWithLang> getAltLabel() {
        return altLabel;
    }

    public void setAltLabel(String altLabelLg1, String altLabelLg2) {
        if (!altLabelLg1.equals("")) {
            if (altLabel == null) {
                altLabel = new ArrayList<>();
            }
            label.add(new StringWithLang(altLabelLg1, Lang.FR));
        }
        if (!altLabelLg2.equals("")) {
            if (altLabel == null) {
                altLabel = new ArrayList<>();
            }
            label.add(new StringWithLang(altLabelLg2, Lang.EN));
        }
    }

}
