package fr.insee.rmes.modeles.classification;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Postes")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Tableau représentant les postes d'une nomenclature")
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

    public void setListItems(List<? extends Poste> listItems) {
        this.listItems = listItems;
    }

}
