package fr.insee.rmes.utils;

public class IrisUtils {


    private final SparqlUtils sparqlUtils=new SparqlUtils();

    public boolean hasIrisDescendant(String codeCommune) {
        String sparqlQuery = String.format(
                "SELECT DISTINCT ?type\n"
                        + "FROM <http://rdf.insee.fr/graphes/geo/cog>\n"
                        + "WHERE {\n"
                        + "    {\n"
                        + "        ?origine a igeo:Commune ;\n"
                        + "        igeo:codeINSEE \"%s\" .\n"
                        + "    }\n"
                        + "    UNION\n"
                        + "    {\n"
                        + "        ?origine a igeo:ArrondissementMunicipal ;\n"
                        + "        igeo:codeINSEE \"%s\" .\n"
                        + "    }\n"
                        + "    ?uri igeo:subdivisionDirecteDe+ ?origine .\n"
                        + "    ?uri a ?type .\n"
                        + "}\n"
                        + "ORDER BY ?type", codeCommune, codeCommune);

        var type=this.sparqlUtils.executeSparqlQuery(sparqlQuery);
        return  type.endsWith("#Iris\r\n");
    }

}
