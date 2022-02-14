package fr.insee.rmes.modeles.geo.territoires;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.territoire.CollectiviteDOutreMer;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "CollectivitesDOutreMer")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "CollectivitesDOutreMer", description = "Tableau représentant les collectivites d'outre-mer")

public class CollectivitesDOutreMer  extends Territoires{

    private List<CollectiviteDOutreMer> collectivitesDOutreMer = null;

    public CollectivitesDOutreMer() {}

    public CollectivitesDOutreMer(List<CollectiviteDOutreMer> collectivitesDOutreMer) {
        this.collectivitesDOutreMer = collectivitesDOutreMer;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "collectiviteDOutreMer")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<CollectiviteDOutreMer> getCollectivitesDOutreMer() {
        return collectivitesDOutreMer;
    }

    public void setCollectivitesDOutreMer(List<CollectiviteDOutreMer> collectivitesDOutreMer) {
        this.collectivitesDOutreMer = collectivitesDOutreMer;
    }

}





