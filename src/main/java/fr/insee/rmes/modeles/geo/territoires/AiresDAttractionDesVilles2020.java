package fr.insee.rmes.modeles.geo.territoires;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.territoire.AireDAttractionDesVilles2020;
import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "AireDAttractionDesVilles2020")
@JacksonXmlRootElement(localName = "AiresDAttractionDesVilles2020")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Aires d'attraction", description = "Tableau représentant les aires d'attraction")
public class AiresDAttractionDesVilles2020 extends Territoires {

	private List<AireDAttractionDesVilles2020> airesDAttractionDesVilles2020 = null;

	public AiresDAttractionDesVilles2020() {}

    public AiresDAttractionDesVilles2020(List<AireDAttractionDesVilles2020> airesDAttractionDesVilles2020) {
        this.airesDAttractionDesVilles2020 = airesDAttractionDesVilles2020;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "AireDAttractionDesVilles2020")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<AireDAttractionDesVilles2020> getAiresAttraction() {
        return airesDAttractionDesVilles2020;
    }

    public void setAiresAttraction(List<AireDAttractionDesVilles2020> airesDAttractionDesVilles2020) {
        this.airesDAttractionDesVilles2020 = airesDAttractionDesVilles2020;
    }
}
