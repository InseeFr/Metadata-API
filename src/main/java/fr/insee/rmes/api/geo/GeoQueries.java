package fr.insee.rmes.api.geo;

import fr.insee.rmes.api.Configuration;

public class GeoQueries {
	
	public static String getCommune(String code) {
		return "SELECT ?uri ?intitule WHERE { \n"
				+ "?uri rdf:type igeo:Commune . \n"
				+ "?uri igeo:codeINSEE '" + code + "'^^xsd:token . \n"
				+ "?uri igeo:nom ?intitule \n"
				// Ensure that is not the IGN URI
				+ "FILTER (REGEX(STR(?uri), '" + Configuration.BASE_HOST + "')) \n"
				+ "FILTER (lang(?intitule) = 'fr') \n"
				+ "}";
	}
	
	public static String getCountry(String code) {
		return "SELECT ?uri ?intitule WHERE { \n"
				+ "VALUES ?country { igeo:TerritoireAutonomeOuASouveraineteSpeciale igeo:Etat igeo:Pays } \n"
				+ "?uri rdf:type ?country . \n"
				+ "?uri igeo:codeINSEE '" + code + "'^^xsd:token . \n"
				+ "?uri igeo:nom ?intitule \n"
				// Ensure that is not the dbpedia URI
				+ "FILTER (REGEX(STR(?uri), '" + Configuration.BASE_HOST + "')) \n"
				+ "FILTER (lang(?intitule) = 'fr') \n"
				+ "}";
	}
	
	public static String getRegion(String code) {
		return "SELECT ?uri ?intitule WHERE { \n"
				+ "?uri rdf:type igeo:Region . \n"
				+ "?uri igeo:codeINSEE '" + code + "'^^xsd:token . \n"
				+ "?uri igeo:nom ?intitule \n"
				// Ensure that is not the IGN URI
				+ "FILTER (REGEX(STR(?uri), '" + Configuration.BASE_HOST + "')) \n"
				+ "FILTER (lang(?intitule) = 'fr') \n"
				+ "}";
	}

}
