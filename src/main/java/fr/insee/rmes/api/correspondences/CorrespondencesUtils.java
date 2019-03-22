package fr.insee.rmes.api.correspondences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CorrespondencesUtils {

	
	/**get in shape rawlist in tree map
	 * 1 source (map id) -> many target
	*/
	/*when id correspondence*/
	public static Associations getCorrespondenceByCorrespondenceId(String idCorrespondence,
			List<RawCorrespondence> rawItemsList) {

		Map<Poste, List<Poste>>  mapSourceTargetItems = getTreeMapTargetItemsBySourceByCorrespondenceId(rawItemsList);
		return organizeItemTreeMap(mapSourceTargetItems);
	}


	private static Associations organizeItemTreeMap(Map<Poste, List<Poste>> mapSourceTargetItems) {
		Associations correspondences = new Associations();
		mapSourceTargetItems.forEach((k,v) -> {
			
			Association corresp = new Association(k, v);
			correspondences.getCorrespondences().add(corresp);
			
		});
		
		
		return correspondences;
	}
	
	/**
	 * This method transforms sparql query "table" result
	 * for mapping 1 source item from source classification to to many many target item in target classification
	 * @param idCorrespondence
	 * @param rawItemsList
	 * @return
	 */
	public static Map<Poste, List<Poste>> getTreeMapTargetItemsBySourceByCorrespondenceId(List<RawCorrespondence> rawItemsList) {

		/*TreeMap for ordering map keys*/
		Map<Poste, List<Poste>> groupedListItems = new TreeMap<Poste, List<Poste> >();
		for (RawCorrespondence curRawCorrespondence : rawItemsList) {
	
			Poste posteSource = new Poste(curRawCorrespondence.getCodePoste1(), curRawCorrespondence.getUriPoste1(),
					curRawCorrespondence.getIntituleFrPoste1(), curRawCorrespondence.getIntituleEnPoste1());
			
			if (!groupedListItems.containsKey(posteSource)) { // add source and target items in map if new item source

				//code source item is the map of the key whitch representing a 1 to many correspondences from a target code
				groupedListItems.put(posteSource, new ArrayList<Poste>());

				//add targetItem
				Poste targetPoste = new Poste(curRawCorrespondence.getCodePoste2(), curRawCorrespondence.getUriPoste2(),
						curRawCorrespondence.getIntituleFrPoste2(), curRawCorrespondence.getIntituleEnPoste2());
				
				groupedListItems.get(posteSource).add(targetPoste);

			}

			else {

				//add targetItem only in map 
				Poste targetPoste = new Poste(curRawCorrespondence.getCodePoste2(), curRawCorrespondence.getUriPoste2(),
						curRawCorrespondence.getIntituleFrPoste2(), curRawCorrespondence.getIntituleEnPoste2());
				
				groupedListItems.get(posteSource).add(targetPoste);

			}
		}

		return groupedListItems;

	}
	
	
	
	
	/*when id1 + id2 classifications*/
	public static Associations getCorrespondenceByclassificationIds(String codeClassification, String targetCodeClassification,
			List<RawCorrespondence> rawItemsList) {

		Map<Poste, List<Poste>>  mapSourceTargetItems = getTreeMapTargetItemsBySourceByClassificationsIds(codeClassification, targetCodeClassification, rawItemsList);
		return organizeItemTreeMap(mapSourceTargetItems);
	}

	
	
	/**
	 * for handling asymetrical correspondencies in RDF data
	 */
	public static Map<Poste, List<Poste>> getTreeMapTargetItemsBySourceByClassificationsIds(
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
	
	

/**
 * Correspondance beetween 2 classifications is not symetrical.
 * the order for giving clasification must change results mappings
 * this method verify is swapping source <-> target items i necessary
 * to get the right correspondence
 * @param codeClassificationSource
 * @param rawItemsList
 * @return
 */

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
			boolean mustSwapAssociation) {

		Poste item = null;

		if (!mustSwapAssociation) {

			item = new Poste(corresp.getCodePoste1(), corresp.getUriPoste1(),
					corresp.getIntituleFrPoste1(), corresp.getIntituleEnPoste1());

		} else {

			item = new Poste(corresp.getCodePoste2(), corresp.getUriPoste2(),
					corresp.getIntituleFrPoste2(), corresp.getIntituleEnPoste2());

		}

		return item;
	}


}
