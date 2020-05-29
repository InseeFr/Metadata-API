package fr.insee.rmes.modeles.classification.correspondence;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Association")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Schema(description = "Objet représentant une Association")
public class Association {

    @JacksonXmlProperty(localName = "Source")
    @JsonProperty("source")
    private PosteCorrespondence itemSource;

    @JacksonXmlProperty(localName = "Cible")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("cibles")
    @Schema(description = "Liste des postes cibles")
    private List<PosteCorrespondence> itemTargets;

    public Association() {

    }

    public Association(PosteCorrespondence k, List<PosteCorrespondence> v) {

        this.itemSource = k;
        this.itemTargets = v;

    }

    public PosteCorrespondence getItemSource() {
        return itemSource;
    }

    public void setItemSource(PosteCorrespondence itemSource) {
        this.itemSource = itemSource;
    }

    public List<PosteCorrespondence> getItemTargets() {
        return itemTargets;
    }

    public void setItemTargets(List<PosteCorrespondence> itemTargets) {
        this.itemTargets = itemTargets;
    }

}
