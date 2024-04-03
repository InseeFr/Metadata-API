package fr.insee.rmes.modeles.geo.territoires;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.territoire.ArrondissementMunicipal;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "ArrondissementsMunicipaux")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "ArrondissementsMunicipaux", description = "Tableau représentant les arrondissementsMunicipaux")
public class ArrondissementsMunicipaux  extends Territoires{

    private List<ArrondissementMunicipal> arrondissementsMunicipaux = null;

    public ArrondissementsMunicipaux() {}

    public ArrondissementsMunicipaux(List<ArrondissementMunicipal> arrondissementsMunicipaux) {
        this.arrondissementsMunicipaux = arrondissementsMunicipaux;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "ArrondissementMunicipal")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<ArrondissementMunicipal> getArrondissementsMunicipaux() {
        return arrondissementsMunicipaux;
    }

    public void setArrondissementsMunicipaux(List<ArrondissementMunicipal> arrondissementsMunicipaux) {
        this.arrondissementsMunicipaux = arrondissementsMunicipaux;
    }

}
