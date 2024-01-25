package fr.insee.rmes.modeles.geo.territoires;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.insee.rmes.modeles.geo.territoire.CantonOuVille;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@JacksonXmlRootElement(localName = "CantonsEtVilles")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "CantonsEtVilles", description = "Tableau représentant les cantonsEtVilles")
public class CantonsEtVilles extends Territoires{
    private List<CantonOuVille> cantonsEtVilles = null;

    public CantonsEtVilles() {}

    public CantonsEtVilles(List<CantonOuVille> cantonsEtVilles) {
        this.cantonsEtVilles  = cantonsEtVilles;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "CantonOuVille")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<CantonOuVille> getCantonsEtVilles() {
        return cantonsEtVilles;
    }

    public void setCantons(List<CantonOuVille> cantonsEtVilles) {
        this.cantonsEtVilles = cantonsEtVilles;
    }

}
