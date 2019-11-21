package fr.insee.rmes.modeles.operations;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import fr.insee.rmes.utils.Lang;
import fr.insee.rmes.utils.StringWithLang;

public class Famille {

    private String id = null;
    private List<StringWithLang> label = new ArrayList<StringWithLang>();
    private String uri = null;

    @JsonInclude(Include.NON_NULL)
    private List<Serie> series;

    @JsonInclude(Include.NON_NULL)
    private List<StringWithLang> altLabel;

    public Famille(String uri, String id, String labelLg1, String labelLg2, Serie serie) {
        if (serie != null) {
            setSeries(new ArrayList<Serie>());
            series.add(serie);
        }
        this.id = id;
        label.add(new StringWithLang(labelLg1, Lang.FR));
        if (labelLg2 != "") {
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
            setSeries(new ArrayList<Serie>());
        }
        this.series.add(serie);
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

    public void setLabel(List<StringWithLang> label) {
        this.label = label;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @JacksonXmlProperty(localName = "altLabel")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<StringWithLang> getAltLabel() {
        return altLabel;
    }

    public void setAltLabel(String altLabelLg1, String altLabelLg2) {
        if (altLabelLg1 != "") {
            if (altLabel == null) altLabel = new ArrayList<StringWithLang>();
            label.add(new StringWithLang(altLabelLg1, Lang.FR));
        }
        if (altLabelLg2 != "") {
            if (altLabel == null) altLabel = new ArrayList<StringWithLang>();
            label.add(new StringWithLang(altLabelLg2, Lang.EN));
        }
    }

}
