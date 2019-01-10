package fr.insee.rmes.api.correspondences;

public class CorrespondencesQueries {

	public static String getCorrespondences(String classificationCode, String targetClassificationCode) {
		
		String query = "select (?s as ?uri) (?sCode as ?code) (?sLibelle as ?intitule) (?o as ?uriCible) (?oCode as ?codeCible) (?oLibelle as ?intituleCible) "
				+ " (strafter(str(?p),'#') as ?typeCorrespondence) \n"
				+"where {\n"
				+"  ?s ?p ?o.\n"
				+"  filter (contains(str(?s),'/" + classificationCode.toLowerCase()  + "/'))\n"
				+"  filter (contains(str(?o),'/" + targetClassificationCode.toLowerCase()  + "/'))\n"
				+"  ?s skos:notation ?sCode.\n"
				+"  ?s skos:prefLabel ?sLibelle.\n"
				+"  filter (langMatches(lang(?sLibelle), 'FR') )\n"
				+"  ?o skos:notation ?oCode.\n"
				+"  ?o skos:prefLabel ?oLibelle.\n"
				+"  filter (langMatches(lang(?oLibelle), 'FR') )\n"
				+"  filter (contains(str(?p),'relatedMatch'))   \n"
				+"  filter (strbefore(strafter(str(?s),'/codes/'),'/') != strbefore(strafter(str(?o),'/codes/'),'/'))\n"
				+"} \n";
		
		return query;
	}
	
	

}
