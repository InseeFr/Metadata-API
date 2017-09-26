package fr.insee.rmes.api.geo;

public class GeoQueries {
	
	public static String getCommune(String code) {
		return "SELECT ?uri ?intitule WHERE { \n"
				+ "?uri rdf:type igeo:Commune . \n"
				+ "?uri igeo:codeINSEE '" + code + "'^^xsd:token . \n"
				+ "?uri igeo:nom ?intitule \n"
				+ "}";
	}
	
	public static String getCountry(String code) {
		return "SELECT ?uri ?intitule WHERE { \n"
				+ "?uri rdf:type igeo:Pays . \n"
				+ "?uri igeo:codeINSEE '" + code + "'^^xsd:token . \n"
				+ "?uri igeo:nom ?intitule \n"
				+ "FILTER (lang(?intitule) = 'fr') \n"
				+ "}";
	}
	
	public static String getRegion(String code) {
		return "SELECT ?uri ?intitule WHERE { \n"
				+ "?uri rdf:type igeo:Region . \n"
				+ "?uri igeo:codeINSEE '" + code + "'^^xsd:token . \n"
				+ "?uri igeo:nom ?intitule \n"
				+ "FILTER (lang(?intitule) = 'fr') \n"
				+ "}";
	}

}
