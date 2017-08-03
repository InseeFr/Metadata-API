package fr.insee.rmes.api.test;

import org.junit.Test;

import fr.insee.rmes.api.codes.cj.CategorieJuridiqueNiveauIII;

public class ModelTest {

	@Test
	public void testPopulateFromCSV() {

		CategorieJuridiqueNiveauIII cj = new CategorieJuridiqueNiveauIII();
		cj.setCode("7112");
		cj.setUri("http://id.insee.fr/codes/cj/n3/7112");
		cj.setIntitule("mon intitué");
		System.out.println(cj.getCode() + " - " + cj.getIntitule() + " - " + cj.getUri());
	}

}
