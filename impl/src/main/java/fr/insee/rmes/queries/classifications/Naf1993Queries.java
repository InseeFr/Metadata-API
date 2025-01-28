package fr.insee.rmes.queries.classifications;

import fr.insee.rmes.config.Configuration;

public class Naf1993Queries {
	
	  private Naf1993Queries() {
		    throw new IllegalStateException("Utility class");
	  }

    public static String getClasseNAF1993(String code) {
        return "SELECT ?uri ?intitule WHERE { \n"
            + "?uri skos:notation '"
            + code
            + "' . \n"
            + "<"
            + Configuration.getBaseHost()
            + "/codes/naf/classes> skos:member ?uri . \n"
            + "?uri skos:prefLabel ?intitule  \n"
            + "FILTER (lang(?intitule) = 'fr') \n"
            + "}";
    }

}
