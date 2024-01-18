package fr.insee.rmes.utils;

import fr.insee.rmes.modeles.geo.territoire.Commune;
import org.junit.jupiter.api.Test;

class CSVUtilsTest {

    @Test
    void populateMultiPOJO() {
        CSVUtils csvutils= new CSVUtils();
        csvutils.populateMultiPOJO("uri,type,code,intitule,intituleSansArticle,typeArticle,typeDIris,dateCreation,dateSuppression\n" +
                "http://id.insee.fr/geo/commune/6be6a231-0e6f-4462-9799-94903bdf3ca5,Commune,988330000,Kouaoua,Kouaoua,0,,2008-01-01,\n" +
                "http://id.insee.fr/geo/commune/44901691-98ac-4ac0-ad8d-dd84d75c3017,Commune,988320000,Yaté,Yaté,0,,2008-01-01,\n" +
                "http://id.insee.fr/geo/commune/775edf31-a9f5-4b1a-a848-f4b1cbe07812,Commune,988310000,Voh,Voh,0,,2008-01-01,\n" +
                "http://id.insee.fr/geo/commune/2cb6fd01-6d8d-4f58-99fd-771ae51305b7,Commune,988300000,Touho,Touho,0,,2008-01-01,\n" +
                "http://id.insee.fr/geo/commune/389da151-6883-4a3f-b7bf-2f50308c0ff0,Commune,988290000,Thio,Thio,0,,2008-01-01,\n" +
                "http://id.insee.fr/geo/commune/f168b24d-1880-418c-9ccd-1a31736d6996,Commune,988280000,Sarraméa,Sarraméa,0,,2008-01-01,\n" +
                "http://id.insee.fr/geo/commune/98741ff3-4355-415a-9c72-3d81b29873ab,Commune,988270000,Poya,Poya,0,,2008-01-01,\n" +
                "http://id.insee.fr/geo/commune/fa1abbc5-b24e-4d8b-a8c2-c3f2df4e40e4,Commune,988260000,Poum,Poum,0,,2008-01-01,\n" +
                "http://id.insee.fr/geo/commune/f2c631cb-f134-4101-b7a7-15d60e07b8a7,Iris,988250000,Pouembout,Pouembout,0,H,2008-01-01,\n", Commune.class);

    }
}