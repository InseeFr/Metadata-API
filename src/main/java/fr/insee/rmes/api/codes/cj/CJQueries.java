package fr.insee.rmes.api.codes.cj;

public class CJQueries {

	public static String getCategorieJuridiqueNiveauIII(String code) {
		return "SELECT ?uri ?intitule WHERE { \n"
				+ "?uri skos:notation '" + code + "' . \n"
				+ "?lastCJThirdLevel skos:member ?uri . \n"
				+ "?uri skos:prefLabel ?intitule  \n"
				+ "FILTER (lang(?intitule) = 'fr') \n"
				+ "{ \n"
				+ "SELECT ?lastCJThirdLevel WHERE { \n"
				+ "?lastCJThirdLevel xkos:organizedBy <http://id.insee.fr/concepts/cj/cjNiveauIII> . \n"
				+ "BIND(STRBEFORE(STRAFTER(STR(?lastThirdLevel ),'http://id.insee.fr/codes/cj/cj'), '/niveauIII') AS ?lastCJVersion) \n"
				+ "BIND(xsd:float(?lastCJVersion) AS ?lastCJVersionFloat)"
				+ "} \n"
				+ "ORDER BY DESC (?lastCJVersionFloat) \n"
				+ "LIMIT 1 \n"
				+ "} \n"
				+ "}";
	}	

}
