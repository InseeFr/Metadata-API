package fr.insee.rmes.api.codes.na1973;

import fr.insee.rmes.config.Configuration;

public class Na1973Queries {
	
	public static String getGroupeNA1973(String code) {
		return "SELECT ?uri ?intitule WHERE { \n"
				+ "?uri skos:notation '" + code + "' . \n"
				+ "<" + Configuration.BASE_HOST + "/codes/na1973/groupes> skos:member ?uri . \n"
				+ "?uri skos:prefLabel ?intitule  \n"
				+ "FILTER (lang(?intitule) = 'fr') \n"
				+ "}";
	}

}
