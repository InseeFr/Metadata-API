package fr.insee.rmes.modeles.classification.cj;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="CategoriesJuridiques")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesJuridiques {
	
	private List<CategorieJuridique> categoriesJuridiques = null;
	
	public CategoriesJuridiques() {} // No-args constructor needed for JAXB

	public CategoriesJuridiques(List<CategorieJuridique> categoriesJuridiques) {
		this.categoriesJuridiques = categoriesJuridiques;
	}

	@JacksonXmlProperty(isAttribute=true, localName="CategorieJuridique")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<CategorieJuridique> getCategoriesJuridiques() {
		return categoriesJuridiques;
	}

	public void setCategoriesJuridiques(List<CategorieJuridique> categoriesJuridiques) {
		this.categoriesJuridiques = categoriesJuridiques;
	}

}
