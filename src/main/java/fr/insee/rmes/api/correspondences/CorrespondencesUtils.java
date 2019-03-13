package fr.insee.rmes.api.correspondences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CorrespondencesUtils {

	
	public static Correspondences getCorrespondences(String codeClassification, String targetCodeClassification,
			List<RawCorrespondence> rawItemsList) {

		Correspondences correspondences = new Correspondences();
		Map<Poste, List<Poste>>  mapSourceTargetItems = CorrespondencesUtils.getTreeMapTargetItemsBySource(codeClassification, targetCodeClassification, rawItemsList);
		
		mapSourceTargetItems.forEach((k,v) -> {
			
			Correspondence corresp = new Correspondence(k, v);
			correspondences.getCorrespondences().add(corresp);
			
		});
		
		
		return correspondences;
	}

	
	
	/**
	 * for handling asymetrical correspondencies in RDF data
	 */
	public static Map<Poste, List<Poste>> getTreeMapTargetItemsBySource(
			String codeClassificationSource, String targetCodeClassification, List<RawCorrespondence> rawItemsList) {

		/*TreeMap for ordering map keys*/
		Map<Poste, List<Poste>> groupedListItems = new TreeMap<Poste, List<Poste> >();
		
		/*Classification correspondences are not symetrical in database => answering question : what is source / target must in raw correspondances ?*/
		/*if false => first fields of csv result are for source classification item*/
		/*if true => second part of fields */
		boolean mustSwapCorrespondences = shouldSwapRawCorespondences(codeClassificationSource, rawItemsList);

		for (RawCorrespondence curRawCorrespondence : rawItemsList) {
			

			Poste posteSource = mapRawObjectToItemCorrespondence(curRawCorrespondence, mustSwapCorrespondences)  ;

			if (!groupedListItems.containsKey(posteSource)) {

				//code source item is the map of the key whitch representing a 1 to many correspondences from a target code
				groupedListItems.put(posteSource, new ArrayList<Poste>());

				//add targetItem
				Poste targetPoste = mapRawObjectToItemCorrespondence(curRawCorrespondence, !mustSwapCorrespondences);
				
				groupedListItems.get(posteSource).add(targetPoste);

			}

			else {

				//add targetItem
				groupedListItems.get(posteSource)
						.add(mapRawObjectToItemCorrespondence(curRawCorrespondence, !mustSwapCorrespondences));

			}
		}

		return groupedListItems;

	}


	private static Boolean shouldSwapRawCorespondences(String codeClassificationSource,
			List<RawCorrespondence> rawItemsList) {

		//using the first correspondence ( rawItemsList.get(0) ) 
		//=> which csv result, first or second uri field (uriPoste1/2) does contain classification source id ? 
		
		String uriPoste1FromRaw = rawItemsList.get(0).getUriPoste1();
		
		String toVerify = "/codes/" + codeClassificationSource + "/";

		if (!uriPoste1FromRaw.contains(toVerify)) {

			return true; // => last fields concern source classification
		}

		return false; // => first fields concern source classification;

	}

	private static Poste mapRawObjectToItemCorrespondence(RawCorrespondence corresp,
			boolean mustSwapCorrespondences) {

		Poste item = null;

		if (!mustSwapCorrespondences) {

			item = new Poste(corresp.getCodePoste1(), corresp.getUriPoste1(),
					corresp.getIntituleFrPoste1(), corresp.getIntituleEnPoste1());

		} else {

			item = new Poste(corresp.getCodePoste2(), corresp.getUriPoste2(),
					corresp.getIntituleFrPoste2(), corresp.getIntituleEnPoste2());

		}

		return item;
	}


}
