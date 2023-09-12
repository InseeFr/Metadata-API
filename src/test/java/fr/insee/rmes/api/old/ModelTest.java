package fr.insee.rmes.api.old;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import fr.insee.rmes.modeles.classification.cj.CategorieJuridiqueNiveauIII;

// TODO needs to be transformed into fonctional test

@Ignore
public class ModelTest {

    @Test
    public void testPopulateFromCSV() {

        CategorieJuridiqueNiveauIII cj = new CategorieJuridiqueNiveauIII();
        cj.setCode("7112");
        cj.setUri("http://id.insee.fr/codes/cj/n3/7112");
        cj.setIntitule("mon intitué");
        System.out.println(cj.getCode() + " - " + cj.getIntitule() + " - " + cj.getUri());
        Assertions.assertTrue(true);
    }

}
