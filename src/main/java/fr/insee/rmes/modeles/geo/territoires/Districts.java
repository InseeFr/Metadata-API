package fr.insee.rmes.modeles.geo.territoires;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.territoire.District;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Districts")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Districts", description = "Tableau représentant les districts")

public class Districts extends Territoires {

	private List<District> districts = null;

    public Districts() {}

    public Districts(List<District> districts) {
        this.districts = districts;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "District")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
	
}
