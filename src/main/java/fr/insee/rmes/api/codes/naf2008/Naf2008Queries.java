package fr.insee.rmes.api.codes.naf2008;

public class Naf2008Queries {
	
	public static String getClasseNAF2008(String code) {
		return "SELECT ?uri ?intitule WHERE { \n"
				+ "?uri skos:notation '" + code + "' . \n"
				+ "<http://id.insee.fr/codes/nafr2/classes> skos:member ?uri . \n"
				+ "?uri skos:prefLabel ?intitule  \n"
				+ "FILTER (lang(?intitule) = 'fr') \n"
				+ "}";
	}
	
	public static String getSousClasseNAF2008(String code) {
		return "SELECT ?uri ?intitule WHERE { \n"
				+ "?uri skos:notation '" + code + "' . \n"
				+ "<http://id.insee.fr/codes/nafr2/sousClasses> skos:member ?uri . \n"
				+ "?uri skos:prefLabel ?intitule  \n"
				+ "FILTER (lang(?intitule) = 'fr') \n"
				+ "}";
	}

}
