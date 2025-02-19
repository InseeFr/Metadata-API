package fr.insee.rmes.modeles.geo.territoires;


import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.insee.rmes.modeles.geo.territoire.TerritoireNonAbstract;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.util.List;

@JacksonXmlRootElement(localName = "Territoires")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Terriroies", description = "Tableau représentant les territoires")
public class TerritoiresNonAbstract extends Territoires{
    private List<TerritoireNonAbstract> territoiresNonAbstract = null;

    public TerritoiresNonAbstract() {
    }

    public TerritoiresNonAbstract(List<TerritoireNonAbstract> territoires) {
        this.territoiresNonAbstract = territoires;
    }

    @JsonUnwrapped
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Territoire")
    public List<TerritoireNonAbstract> getTerritoiresNonAbstract() {
        return territoiresNonAbstract;
    }

    public void setTerritoiresNonAbstract(List<TerritoireNonAbstract> territoires) {
        this.territoiresNonAbstract = territoires;
    }

}
