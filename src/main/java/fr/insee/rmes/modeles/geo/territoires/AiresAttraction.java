package fr.insee.rmes.modeles.geo.territoires;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.territoire.AireAttraction;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "AiresAttraction")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Aires d'attraction", description = "Tableau représentant les aires d'attraction")
public class AiresAttraction extends Territoires {

    private List<AireAttraction> airesAttraction = null;

    public AiresAttraction() {}

    public AiresAttraction(List<AireAttraction> airesAttraction) {
        this.airesAttraction = airesAttraction;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "AireAttraction")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<AireAttraction> getAiresAttraction() {
        return airesAttraction;
    }

    public void setAiresAttraction(List<AireAttraction> airesAttraction) {
        this.airesAttraction = airesAttraction;
    }
}
