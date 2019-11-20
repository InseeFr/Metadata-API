package fr.insee.rmes.modeles.classification;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Postes")
@XmlAccessorType(XmlAccessType.FIELD)
public class Postes {

    private List<? extends Poste> listItems = null;

    public Postes() {}

    public Postes(List<? extends Poste> itemsListXml) {
        this.listItems = itemsListXml;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "Poste")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<? extends Poste> getListItems() {
        return listItems;
    }

    @SuppressWarnings("unchecked")
    public void setListItems(List<? extends Poste> listItems) {
        this.listItems = (List<Poste>) listItems;
    }

}
