package fr.insee.rmes.modeles.classification;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "Nomenclatures")
@Schema(name = "Nomenclatures", description = "Tableau représentant la liste des nomenclatures")
public class Classifications {

    private List<Classification> listDescriptions = new ArrayList<>();

    public Classifications() {}

    public Classifications(List<Classification> listDescriptions) {
        this.listDescriptions = listDescriptions;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "Nomenclature")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Classification> getlistDescriptions() {
        return this.listDescriptions;
    }

    public void setListItems(List<Classification> listDescriptions) {
        this.listDescriptions = listDescriptions;
    }

}
