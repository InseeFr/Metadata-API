package fr.insee.rmes.modeles.operations;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import fr.insee.rmes.modeles.StringWithLang;
import fr.insee.rmes.utils.Lang;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ObjetSimple", description = "Objet simple")
public class SimpleObject {

	protected String id = null;
    @Schema(example = "http://id.insee.fr/...")
    protected String uri = null;

    protected List<StringWithLang> label = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SimpleObject(String id, String uri, String labelFr, String labelEn) {
        this.id = id;
        this.uri = uri;
        if (StringUtils.isNotEmpty(labelFr)) {
            label.add(new StringWithLang(labelFr, Lang.FR));
        }
        if (StringUtils.isNotEmpty(labelEn)) {
            label.add(new StringWithLang(labelEn, Lang.EN));
        }
    }

    public SimpleObject() {
        super();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @JacksonXmlProperty(localName = "label")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<StringWithLang> getLabel() {
        return label;
    }

    public void setLabel(List<StringWithLang> label) {
        this.label = label;
    }

    public void setLabelFr(String labelFr) {
        if (StringUtils.isNotEmpty(labelFr)) {
            label.add(new StringWithLang(labelFr, Lang.FR));
        }
    }

    public void setLabelEn(String labelEn) {
        if (StringUtils.isNotEmpty(labelEn)) {
            label.add(new StringWithLang(labelEn, Lang.EN));
        }
    }

}
