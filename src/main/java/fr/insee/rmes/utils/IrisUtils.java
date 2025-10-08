package fr.insee.rmes.utils;

public class IrisUtils {


    private final SparqlUtils sparqlUtils=new SparqlUtils();

    public boolean hasIrisDescendant(String codeCommune) {
        String sparqlQuery = String.format(
                "PREFIX igeo: <http://rdf.insee.fr/def/geo#>\n" +
                        "ASK FROM <http://rdf.insee.fr/graphes/geo/cog>\n" +
                        "WHERE {\n" +
                        "    {\n" +
                        "        ?origine a igeo:Commune ;\n" +
                        "        igeo:codeINSEE \"%s\" .\n" +
                        "    }\n" +
                        "    UNION\n" +
                        "    {\n" +
                        "        ?origine a igeo:ArrondissementMunicipal ;\n" +
                        "        igeo:codeINSEE \"%s\" .\n" +
                        "    }\n" +
                        "    ?uri igeo:subdivisionDirecteDe+ ?origine .\n" +
                        "    ?uri a ?type .\n" +
                        "    FILTER(STRENDS(STR(?type), \"#Iris\"))\n" +
                        "}\n",
                codeCommune, codeCommune);

        return this.sparqlUtils.executeAskQuery(sparqlQuery);
    }

}
