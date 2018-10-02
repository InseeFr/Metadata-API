package fr.insee.rmes.api.classifications;

public class ClassificationsQueries {

	public static String getClassification(String classificationId) {

		return "select ?uri	?code	?uriParent	?codeParent	?intituleFr	?intituleEn	\n"
				+ "?contenuLimite	?contenuCentral			\n"
				+ "?exclusions			\n"
				+ " from <http://rdf.insee.fr/graphes/codes/" + classificationId.toLowerCase() + ">\n" + "where{\n"
				+ " ?uri rdf:type 	skos:Concept.\n" + " ?uri skos:notation ?code.\n" + " \n" + " optional {\n"
				+ "    ?uriParent skos:narrower ?uri. \n" + "	?uriParent skos:notation ?codeParent.\n"
				+ " \n" + " }\n" + " \n" + " optional { ?uri skos:prefLabel ?intituleFr.\n"
				+ " FILTER langMatches( lang(?intituleFr ), 'fr' ) }\n" + " \n"
				+ " optional { ?uri skos:prefLabel ?intituleEn.\n"
				+ " FILTER langMatches( lang(?intituleEn ), 'en' ) }\n" + " \n" + " \n"
				+ " optional {?uri xkos:additionalContentNote ?uriACNote.\n"
				+ " ?uriACNote 	evoc:noteLiteral  ?contenuLimite .\n" + " ?uriACNote insee:validFrom ?dateDebACN.\n"
				+ " filter  (NOT EXISTS {?uriACNote insee:validUntil ?dateFinACN} )\n" + " }\n" + " \n"
				+ " optional {?uri xkos:coreContentNote ?uriCCNote.\n"
				+ " ?uriCCNote 	evoc:noteLiteral  ?contenuCentral .\n" + " ?uriCCNote insee:validFrom ?dateDebCCN.\n"
				+ " filter  (NOT EXISTS {?uriCCNote insee:validUntil ?dateFinCCN} )\n" + " }\n" + " \n" + " \n"
				+ " optional {?uri xkos:exclusionNote ?uriENote.\n"
				+ " ?uriENote 	evoc:noteLiteral  ?exclusions .\n" + " ?uriENote insee:validFrom ?dateDebEN.\n"
				+ " filter  (NOT EXISTS {?uriENote insee:validUntil ?dateFinEN} )\n" + " }\n" + " \n"
				+ " optional {?uri skos:scopeNote ?uriSNote.\n" + " ?uriSNote 	evoc:noteLiteral  ?plainTextSN .\n"
				+ " ?uriSNote insee:validFrom ?dateDebSN.\n"
				+ " filter  (NOT EXISTS {?uriSNote insee:validUntil ?dateFinSN} )\n" + " }\n" + "}\n"
				+ "order by ?code\n";

		
	}

	public static String getClassificationsDescriptions() {

		return "SELECT ?code ?uri ?intitule  WHERE {\n" + "	?uri skos:prefLabel ?intitule .\n"
				+ "	?uri rdf:type skos:ConceptScheme .\n"
				+ "	bind  (strbefore(strafter(str(?uri),'/codes/'),'/') as ?code)\n"
				+ "	FILTER(regex(str(?uri),'/codes/'))\n" + "	FILTER(!regex(str(?uri),'/cj/'))\n"
				+ "	FILTER(lang(?intitule) = 'fr')\n" + "}\n" + "ORDER BY ?intitule\n";

	}
}
