package fr.insee.rmes.queries.classifications;

import org.joda.time.DateTime;

import fr.insee.rmes.config.Configuration;

public class ActivitesQueries {

    public static String getActiviteByCodeAndDate(String code, DateTime date) {
        return "SELECT ?code ?uri ?intitule ?dateDebutValidite ?dateFinValidite WHERE { \n"
            + "?classification dcterms:issued ?dateDebutValidite . \n"
            + "FILTER (?dateDebutValidite <= '"
            + date
            + "'^^xsd:dateTime) \n"
            + "OPTIONAL {?classification dcterms:valid ?dateFinValidite .} \n"
            + "BIND(IF(!BOUND(?dateFinValidite), '9999-01-01T00:00:00.000+01:00'^^xsd:dateTime, "
            + "?dateFinValidite) as ?validFilter) \n"
            + "FILTER ('"
            + date
            + "'^^xsd:dateTime <= ?validFilter) \n"
            + "?uri skos:inScheme ?classification . \n"
            + "?uri skos:notation '"
            + code
            + "' . \n"
            + "?uri skos:notation ?code . \n"
            + "?uri skos:prefLabel ?intitule  \n"
            + "FILTER (lang(?intitule) = 'fr') \n"
            + "VALUES ?classification { <"
            + Configuration.BASE_HOST
            + "/codes/na73/na> \n"
            + "<"
            + Configuration.BASE_HOST
            + "/codes/naf/naf> \n"
            + "<"
            + Configuration.BASE_HOST
            + "/codes/nafr1/naf> \n"
            + "<"
            + Configuration.BASE_HOST
            + "/codes/nafr2/naf> } \n"
            + "}";
    }

    public static String getActiviteByCode(String code) {
        return getActiviteByCodeAndDate(code, DateTime.now());
    }

    public static String getActivites(String code) {
        return "SELECT ?code ?uri ?intitule ?dateDebutValidite ?dateFinValidite WHERE { \n"
            + "?classification dcterms:issued ?dateDebutValidite . \n"
            + "OPTIONAL {?classification dcterms:valid ?dateFinValidite .} \n"
            + "?uri skos:inScheme ?classification . \n"
            + "?uri skos:notation '"
            + code
            + "' . \n"
            + "?uri skos:notation ?code . \n"
            + "?uri skos:prefLabel ?intitule  \n"
            + "FILTER (lang(?intitule) = 'fr') \n"
            + "VALUES ?classification { <"
            + Configuration.BASE_HOST
            + "/codes/na73/na> \n"
            + "<"
            + Configuration.BASE_HOST
            + "/codes/naf/naf> \n"
            + "<"
            + Configuration.BASE_HOST
            + "/codes/nafr1/naf> \n"
            + "<"
            + Configuration.BASE_HOST
            + "/codes/nafr2/naf> } \n"
            + "} \n"
            + "ORDER BY ?dateDebutValidite";
    }

}
