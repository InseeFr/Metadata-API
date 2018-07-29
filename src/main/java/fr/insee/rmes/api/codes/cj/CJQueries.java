package fr.insee.rmes.api.codes.cj;

import org.joda.time.DateTime;

import fr.insee.rmes.config.Configuration;

public class CJQueries {
	
	public static String getCategorieJuridiqueNiveauII(String code) {
		return "SELECT ?uri ?intitule WHERE { \n"
				+ "FILTER(STRENDS(STR(?uri), '" + code + "')) \n"
				+ "?lastCJThirdLevel skos:member ?uri . \n"
				+ "?uri skos:prefLabel ?intitule  \n"
				+ "FILTER (lang(?intitule) = 'fr') \n"
				+ "{ \n"
				+ "SELECT ?lastCJThirdLevel WHERE { \n"
				+ "?lastCJThirdLevel xkos:organizedBy <" + Configuration.BASE_HOST + "/concepts/cj/cjNiveauII> . \n"
				+ "BIND(STRBEFORE(STRAFTER(STR(?lastCJThirdLevel), '" + Configuration.BASE_HOST + "/codes/cj/cj'), '/niveauII') AS ?lastCJVersion) \n"
				+ "BIND(xsd:float(?lastCJVersion) AS ?lastCJVersionFloat)"
				+ "} \n"
				+ "ORDER BY DESC (?lastCJVersionFloat) \n"
				+ "LIMIT 1 \n"
				+ "} \n"
				+ "}";
	}	

	public static String getCategorieJuridiqueNiveauIII(String code) {
		return "SELECT ?uri ?intitule WHERE { \n"
				+ "FILTER(STRENDS(STR(?uri), '" + code + "')) \n"
				+ "?lastCJThirdLevel skos:member ?uri . \n"
				+ "?uri skos:prefLabel ?intitule  \n"
				+ "FILTER (lang(?intitule) = 'fr') \n"
				+ "{ \n"
				+ "SELECT ?lastCJThirdLevel WHERE { \n"
				+ "?lastCJThirdLevel xkos:organizedBy <" + Configuration.BASE_HOST + "/concepts/cj/cjNiveauIII> . \n"
				+ "BIND(STRBEFORE(STRAFTER(STR(?lastCJThirdLevel), '" + Configuration.BASE_HOST + "/codes/cj/cj'), '/niveauIII') AS ?lastCJVersion) \n"
				+ "BIND(xsd:float(?lastCJVersion) AS ?lastCJVersionFloat)"
				+ "} \n"
				+ "ORDER BY DESC (?lastCJVersionFloat) \n"
				+ "LIMIT 1 \n"
				+ "} \n"
				+ "}";
	}
	
	public static String getCJByCodeAndDate(String code, DateTime date) {
		return "SELECT ?code ?uri ?intitule ?dateDebutValidite ?dateFinValidite WHERE { \n"
				+ "?classification dcterms:issued ?dateDebutValidite . \n"
				+ "FILTER(REGEX(STR(?classification), '/codes/cj/')) \n"
				+ "FILTER (?dateDebutValidite <= '" + date + "'^^xsd:dateTime) \n"
				+ "OPTIONAL {?classification dcterms:valid ?dateFinValidite .} \n"
				+ "BIND(IF(!BOUND(?dateFinValidite), '9999-01-01T00:00:00.000+01:00'^^xsd:dateTime, "
				+ "?dateFinValidite) as ?validFilter) \n"
				+ "FILTER ('" + date + "'^^xsd:dateTime <= ?validFilter) \n"
				+ "?uri skos:inScheme ?classification . \n"
				+ "?uri skos:notation '" + code + "' . \n"
				+ "?uri skos:notation ?code . \n"
				+ "?uri skos:prefLabel ?intitule  \n"
				+ "FILTER (lang(?intitule) = 'fr') \n"
				+ "}";
	}
	
	public static String getCJByCode(String code) {
		return getCJByCodeAndDate(code, DateTime.now());
	}
	
	public static String getCJ(String code) {
		return "SELECT ?code ?uri ?intitule ?dateDebutValidite ?dateFinValidite WHERE { \n"
				+ "?classification dcterms:issued ?dateDebutValidite . \n"
				+ "FILTER(REGEX(STR(?classification), '/codes/cj/')) \n"
				+ "OPTIONAL {?classification dcterms:valid ?dateFinValidite .} \n"
				+ "?uri skos:inScheme ?classification . \n"
				+ "?uri skos:notation '" + code + "' . \n"
				+ "?uri skos:notation ?code . \n"
				+ "?uri skos:prefLabel ?intitule  \n"
				+ "FILTER (lang(?intitule) = 'fr') \n"
				+ "}";
	}

}
