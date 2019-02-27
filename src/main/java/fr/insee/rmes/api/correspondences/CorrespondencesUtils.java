package fr.insee.rmes.api.correspondences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CorrespondencesUtils {

	public static Map<String, List<ItemCorrespondence>> getTargetCorrespondencesBySource(
			String codeClassificationSource, String targetCodeClassification, List<RawCorrespondence> rawItemsList) {

		/*TreeMap for ordering map keys*/
		Map<String, List<ItemCorrespondence>> groupedListItems = new TreeMap<String, List<ItemCorrespondence>>();

		/*Classification correspondences are not symetrical in database => what is source / target must in raw correspondances ?*/
		boolean mustSwapCorrespondences = shouldSwapRawCorespondences(codeClassificationSource, rawItemsList);

		for (RawCorrespondence curRawCorrespondence : rawItemsList) {

			String codeSourceItem = getCodeItemSource(curRawCorrespondence, mustSwapCorrespondences)  ;

			if (!groupedListItems.containsKey(codeSourceItem)) {

				// TODO add itemType pour distinguer source vs item target
				groupedListItems.put(codeSourceItem, new ArrayList<ItemCorrespondence>());
				//add sourceItem
				groupedListItems.get(codeSourceItem)
						.add(mapRawObjectToItemCorrespondence(curRawCorrespondence, mustSwapCorrespondences));
				//add targetItem
				groupedListItems.get(codeSourceItem)
						.add(mapRawObjectToItemCorrespondence(curRawCorrespondence, !mustSwapCorrespondences));

			}

			else {

				//add targetItem
				groupedListItems.get(codeSourceItem)
						.add(mapRawObjectToItemCorrespondence(curRawCorrespondence, !mustSwapCorrespondences));

			}
		}

		return groupedListItems;

	}

	private static String getCodeItemSource(RawCorrespondence curRawCorrespondence, boolean mustSwapCorrespondences) {

		if (mustSwapCorrespondences) {
			
			return curRawCorrespondence.getCodePoste2();
			
		}
		
		return curRawCorrespondence.getCodePoste1();
	}


	private static Boolean shouldSwapRawCorespondences(String codeClassificationSource,
			List<RawCorrespondence> rawItemsList) {

		String uriPoste1FromRaw = rawItemsList.get(0).getUriPoste1();
		
		String toTest = "/codes/" + codeClassificationSource + "/";

		if (!uriPoste1FromRaw.contains(toTest)) {

			return true;
		}

		return false;

	}

	private static ItemCorrespondence mapRawObjectToItemCorrespondence(RawCorrespondence corresp,
			boolean mustSwapCorrespondences) {

		ItemCorrespondence item = null;

		if (!mustSwapCorrespondences) {

			item = new ItemCorrespondence(corresp.getCodePoste1(), corresp.getUriPoste1(),
					corresp.getIntituleFrPoste1(), corresp.getIntituleEnPoste1());

		} else {

			item = new ItemCorrespondence(corresp.getCodePoste2(), corresp.getUriPoste2(),
					corresp.getIntituleFrPoste2(), corresp.getIntituleEnPoste2());

		}

		return item;
	}

}
