package fr.insee.rmes.modeles.geo.territoires;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.territoire.BassinDeVie2022;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "BassinsDeVie2022")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "BassinsDeVie2022", description = "Tableau représentant les bassins de vie")

public class BassinsDeVie2022 extends Territoires {
	
	private List<BassinDeVie2022> bassinsDeVie2022 = null;

    public BassinsDeVie2022() {}

    public BassinsDeVie2022(List<BassinDeVie2022> bassinsDeVie2022) {
        this.bassinsDeVie2022 = bassinsDeVie2022;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "BassinDeVie2022")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<BassinDeVie2022> getBassinsDeVie2022() {
        return bassinsDeVie2022;
    }

    public void setBassinsDeVie2022(List<BassinDeVie2022> bassinsDeVie2022) {
        this.bassinsDeVie2022 = bassinsDeVie2022;
    }

}