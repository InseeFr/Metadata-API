package fr.insee.rmes.modeles.classification.cj;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "CategoriesJuridiques")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Tableau représentant des catégories juridiques")
public class CategoriesJuridiques {

    private List<CategorieJuridique> categoriesJuridiques = null;

    public CategoriesJuridiques() {} // No-args constructor needed for JAXB

    public CategoriesJuridiques(List<CategorieJuridique> categoriesJuridiques) {
        this.categoriesJuridiques = categoriesJuridiques;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "CategorieJuridique")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<CategorieJuridique> getCategoriesJuridiques() {
        return categoriesJuridiques;
    }

    public void setCategoriesJuridiques(List<CategorieJuridique> categoriesJuridiques) {
        this.categoriesJuridiques = categoriesJuridiques;
    }

}
