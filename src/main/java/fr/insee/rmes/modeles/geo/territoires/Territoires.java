package fr.insee.rmes.modeles.geo.territoires;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import fr.insee.rmes.modeles.geo.territoire.Territoire;

public class Territoires {

    @JsonUnwrapped
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    private List<Territoire> listeTerritoires = null;

    public Territoires() {}

    public Territoires(List<Territoire> territoires) {
        this.listeTerritoires = territoires;
    }

    public List<Territoire> getTerritoires() {
        return listeTerritoires;
    }

    public void setTerritoires(List<Territoire> territoires) {
        this.listeTerritoires = territoires;
    }

}
