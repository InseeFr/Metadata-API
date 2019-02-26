package fr.insee.rmes.api.correspondences;

public class CorrespondencesQueries {

	public static String getCorrespondencesByIds(String classificationCode, String targetClassificationCode) {
		
		/*String query = 		
				"select distinct ?uriPosteSource ?codePosteSource ?intituleFrPosteSource ?intituleEnPosteSource ?uriPosteCible ?codePosteCible ?intituleFrPosteCible ?intituleEnPosteCible\n"
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
				//+"  filter  (contains(str(?uriPosteSource),'/codes/" + classificationCode.toLowerCase()  + "/') && contains(str(?uriPosteCible),'/codes/" + targetClassificationCode.toLowerCase()  + "/')  ) \n"
				+"} \n"
				+ "ORDER BY ?uriPosteSource ?uriPosteCible";
		
		return query;*/
		
		
		String query = 
				"select distinct ?uriPoste1 ?codePoste1 ?intituleFrPoste1 ?intituleEnPoste1 ?uriPoste2 ?codePoste2 ?intituleFrPoste2 ?intituleEnPoste2 \n" 
				+"where {  \n"
				+"  ?tableCorrespondance xkos:compares ?nomenclature.\n"
				+"  ?tableCorrespondance skos:prefLabel ?preflabel.\n"
				+"  ?tableCorrespondance dc:description ?description.\n"
				+"  ?tableCorrespondance xkos:madeOf ?association.\n"
				+"  ?association xkos:sourceConcept ?uriPoste1.\n"
				+"  ?uriPoste1 skos:notation ?codePoste1.\n"
				+"  ?uriPoste1 skos:prefLabel ?intituleFrPoste1.\n"
				+"  FILTER (lang(?intituleFrPoste1) = 'fr')  .\n"
				+"  ?uriPoste1 skos:prefLabel ?intituleEnPoste1.\n"
				+"  FILTER (lang(?intituleEnPoste1) = 'en')  .\n"
				+"  ?association xkos:targetConcept ?uriPoste2.\n"
				+"  ?uriPoste2 skos:notation ?codePoste2.\n"
				+"  ?uriPoste2 skos:prefLabel ?intituleFrPoste2.\n"
				+"  FILTER (lang(?intituleFrPoste2) = 'fr')  .\n"
				+"  ?uriPoste2 skos:prefLabel ?intituleEnPoste2.\n"
				+"  FILTER (lang(?intituleEnPoste2) = 'en')  .\n"
				// ex : correspondance nafrCible->cpfrCibleSource
				+"  filter  ( (contains(str(?uriPoste1),'/codes/" + classificationCode.toLowerCase()  + "/') && contains(str(?uriPoste2),'/codes/" + targetClassificationCode.toLowerCase()  + "/')  )\n"
				// ou dans l'autre sens ex : cpfrCibleSource->nafrCible
				+"   || (contains(str(?uriPoste1),'/codes/" + targetClassificationCode.toLowerCase()  + "/') && contains(str(?uriPoste2),'/codes/" + classificationCode.toLowerCase()  + "/')  ) )\n"
				//+"  filter  (contains(str(?uriPoste1),'/codes/" + classificationCode.toLowerCase()  + "/') && contains(str(?uriPoste2),'/codes/" + targetClassificationCode.toLowerCase()  + "/')  ) \n"
				+"  bind( if (contains(str(?uriPoste1),'/codes/" + classificationCode.toLowerCase()  + "'),?codePoste1,?codePoste2) as ?orderingSource )\n"
				+"  bind( if (contains(str(?uriPoste2),'/codes/" + targetClassificationCode.toLowerCase()  + "'),?codePoste2,?codePoste1) as ?orderingTarget )\n"
				+"} \n"
				+ "ORDER BY ?orderingSource ?orderingTarget";

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
