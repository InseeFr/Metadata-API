package fr.insee.rmes.api.codes.cj;

public class CJQueries {

	// TODO Modify query to return only current items
	public static String getCategorieJuridiqueNiveauIII(String code) {
		return "SELECT ?uri ?intitule WHERE { \n"
				+ "?uri skos:notation '" + code + "' . \n"
				+ "<http://id.insee.fr/codes/cj/n3> skos:member ?uri . \n"
				+ "?uri skos:prefLabel ?intitule  \n"
				+ "FILTER (lang(?intitule) = 'fr') \n"
				+ "}";
	}	

}
