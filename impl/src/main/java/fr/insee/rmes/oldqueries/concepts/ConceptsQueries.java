package fr.insee.rmes.oldqueries.concepts;

public class ConceptsQueries {
    
    private static final String QUERIES_FOLDER = "concepts/";

    public static String getConceptsByLabel(String label) {
        return "SELECT ?id ?uri ?intitule ?hasLink "
        		+ "FROM <http://rdf.insee.fr/graphes/concepts/definitions> "
        		+ "WHERE { \n"
            + "?uri skos:inScheme ?conceptScheme . \n"
            + "FILTER(REGEX(STR(?conceptScheme),'/concepts/definitions/scheme')) \n"
            + "?uri skos:notation ?id . \n"
            + "?uri skos:prefLabel ?intitule . \n"
            + "FILTER(lang(?intitule) = 'fr') \n"
            + "FILTER(CONTAINS(LCASE(STR(?intitule)),\""
                //    + (StringUtils.isEmpty(label) ? "" : label.toLowerCase())
            + "\")) \n"
            + "BIND(EXISTS{?uri dcterms:replaces|^dcterms:replaces ?repl } AS ?hasLink) \n"
            + "}"
            + "ORDER BY ?intitule";
    }



    
}
