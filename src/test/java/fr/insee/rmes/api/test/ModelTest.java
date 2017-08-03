package fr.insee.rmes.api.test;

import org.junit.Test;

import fr.insee.rmes.api.codes.CategorieJuridiqueNiveauIII;

public class ModelTest {

	@Test
	public void testPopulateFromCSV() {

		CategorieJuridiqueNiveauIII cj = new CategorieJuridiqueNiveauIII();
		cj.setCode("7112");
		cj.populateFromCSV("http://id.insee.fr/codes/cj/n3/7112,mon intitué");
		System.out.println(cj.getCode() + " - " + cj.getIntitule() + " - " + cj.getUri());
	}

}
