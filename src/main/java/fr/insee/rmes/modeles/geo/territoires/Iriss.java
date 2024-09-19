package fr.insee.rmes.modeles.geo.territoires;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.insee.rmes.modeles.geo.territoire.Iris;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import java.util.List;
@JacksonXmlRootElement(localName = "Iriss")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Iriss", description = "Tableau représentant les Iriss")
public class Iriss extends Territoires{
    private List<Iris> iriss = null;

    public Iriss() {}

    public Iriss(List<Iris> iriss) {
        this.iriss = iriss;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "Iris")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Iris> getIriss() {
        return iriss;
    }

    public void setIriss(List<Iris> cantons) {
        this.iriss = iriss;
    }
}
