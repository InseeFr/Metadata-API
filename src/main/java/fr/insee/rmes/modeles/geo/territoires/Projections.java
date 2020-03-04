package fr.insee.rmes.modeles.geo.territoires;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.territoire.Projection;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Projections")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Projections", description = "Tableau représentant les projections")
public class Projections {

    private List<Projection> projection = null;
    
    public Projections() {

    }

    public Projections(List<Projection> projection) {
        this.projection = projection;
    }

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Projection")
    public List<Projection> getProjection() {
        return projection;
    }

    public void setProjection(List<Projection> projection) {
        this.projection = projection;
    }
}
