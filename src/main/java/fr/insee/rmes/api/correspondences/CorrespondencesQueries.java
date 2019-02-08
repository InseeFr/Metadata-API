package fr.insee.rmes.api.correspondences;

public class CorrespondencesQueries {

	public static String getCorrespondencesByIds(String classificationCode, String targetClassificationCode) {
		
		String query = 		
				"select ?uriPosteSource ?codePosteSource ?intituleFrPosteSource ?intituleEnPosteSource ?uriPosteCible ?codePosteCible ?intituleFrPosteCible ?intituleEnPosteCible\n"
				+"where {  \n"
				+"  ?tableCorrespondance xkos:compares ?nomenclature.\n"
				+"  ?tableCorrespondance skos:prefLabel ?preflabel.\n"
				+"  ?tableCorrespondance dc:description ?description.\n"
				+"  ?tableCorrespondance xkos:madeOf ?association.\n"
				+"  ?association xkos:sourceConcept ?uriPosteSource.\n"
				+"  ?uriPosteSource skos:notation ?codePosteSource.\n"
				+"  ?uriPosteSource skos:prefLabel ?intituleFrPosteSource.\n"
				+"  FILTER (lang(?intituleFrPosteSource) = 'fr')  .\n"
				+"  ?uriPosteSource skos:prefLabel ?intituleEnPosteSource.\n"
				+"  FILTER (lang(?intituleEnPosteSource) = 'en')  .\n"
				+"  ?association xkos:targetConcept ?uriPosteCible.\n"
				+"  ?uriPosteCible skos:notation ?codePosteCible.\n"
				+"  ?uriPosteCible skos:prefLabel ?intituleFrPosteCible.\n"
				+"  FILTER (lang(?intituleFrPosteCible) = 'fr')  .\n"
				+"  ?uriPosteCible skos:prefLabel ?intituleEnPosteCible.\n"
				+"  FILTER (lang(?intituleEnPosteCible) = 'en')  .\n"
				// ex : correspondance nafrCible->cpfrCibleSource
				+"  filter  ( (contains(str(?uriPosteSource),'/codes/" + classificationCode.toLowerCase()  + "/') && contains(str(?uriPosteCible),'/codes/" + targetClassificationCode.toLowerCase()  + "/')  )\n"
				// ou dans l'autre sens ex : cpfrCibleSource->nafrCible
				+"   || (contains(str(?uriPosteSource),'/codes/" + targetClassificationCode.toLowerCase()  + "/') && contains(str(?uriPosteCible),'/codes/" + classificationCode.toLowerCase()  + "/')  ) )\n"
				+"} \n";
		
		return query;
		
		
	}

	public static String getAllCorrespondences() {
		
		String query = 	"select ?idTableCorrespondance ?idNomclatureSource ?idNomclatureCible ?uriTableCorrespondance  ?intituleFr ?intituleEn ?descriptionFr ?descriptionEn\n"
						+"where {  \n"
						+"  ?uriTableCorrespondance rdf:type xkos:Correspondence.\n"
						+"  optional{?uriTableCorrespondance skos:prefLabel ?intituleFr.\n"
						+"  filter (lang(?intituleFr) = 'fr')}\n"
						+"  optional{?uriTableCorrespondance skos:prefLabel ?intituleEn.\n"
						+"  filter (lang(?intituleEn) = 'en')}\n"
						+"  optional{?uriTableCorrespondance dc:description ?descriptionFr.\n"
						+"  filter (lang(?descriptionFr) = 'fr')}\n"
						+"  optional{?uriTableCorrespondance dc:description ?descriptionEn.\n"
						+"  filter (lang(?descriptionEn) = 'en')}\n"
						+"  bind ( strafter(str(?uriTableCorrespondance ), '/codes/' ) as ?idTableCorrespondance )\n"
						+"  optional { bind ( strbefore(str(?idTableCorrespondance ), '-' ) as ?idNomclatureSource ) }\n"
						+"  optional { bind ( strafter(str(?idTableCorrespondance ), '-' ) as ?idNomclatureCible ) }\n"
						+"} \n";
		
		return query;
	}
	
	

}
