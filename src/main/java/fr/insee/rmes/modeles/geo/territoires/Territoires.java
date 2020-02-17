package fr.insee.rmes.modeles.geo.territoires;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import fr.insee.rmes.modeles.geo.territoire.Territoire;

@XmlAccessorType(XmlAccessType.FIELD)
public class Territoires {

    private List<Territoire> territoires = null;

    public Territoires() {}

    public Territoires(List<Territoire> territoires) {
        this.territoires = territoires;
    }

    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT, visible=true, defaultImpl=Territoire.class)
    public List<Territoire> getTerritoires() {
        return territoires;
    }

    public void setTerritoires(List<Territoire> territoires) {
        this.territoires = territoires;
    }

}
