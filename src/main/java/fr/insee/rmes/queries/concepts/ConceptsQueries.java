package fr.insee.rmes.queries.concepts;

import org.apache.commons.lang3.StringUtils;

public class ConceptsQueries {

    public static String getConceptsByLabel(String label) {

        return "SELECT ?id ?uri ?intitule ?remplace ?estRemplacePar WHERE { \n"
            + "?uri skos:inScheme ?conceptScheme . \n"
            + "FILTER(REGEX(STR(?conceptScheme),'/concepts/definitions/scheme')) \n"
            + "?uri skos:notation ?id . \n"
            + "?uri skos:prefLabel ?intitule . \n"
            + "FILTER(lang(?intitule) = 'fr') \n"
            + "FILTER(CONTAINS(LCASE(STR(?intitule)),\""
            + (StringUtils.isEmpty(label) ? "" : label.toLowerCase())
            + "\"))"
            + "OPTIONAL{ ?uri dcterms:replaces ?remplace } \n"
            + "OPTIONAL{ ?estRemplacePar dcterms:replaces ?uri } \n"
            + "}"
            + "ORDER BY ?intitule";
    }

    public static String getConceptById(String id) {
        return "SELECT ?uri ?intitule ?remplace ?estRemplacePar WHERE { \n"
            + "?uri skos:inScheme ?conceptScheme . \n"
            + "FILTER(REGEX(STR(?conceptScheme),'/concepts/definitions/scheme')) \n"
            + "?uri skos:notation '"
            + id
            + "' . \n"
            + "?uri skos:prefLabel ?intitule . \n"
            + "FILTER(lang(?intitule) = 'fr') \n"
            + "OPTIONAL{ ?uri dcterms:replaces ?remplace } \n"
            + "OPTIONAL{ ?estRemplacePar dcterms:replaces ?uri } \n"
            + "}"
            + "ORDER BY ?intitule";
    }

}
