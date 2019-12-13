package fr.insee.rmes.api.classifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import fr.insee.rmes.modeles.classification.correspondence.Association;
import fr.insee.rmes.modeles.classification.correspondence.Associations;
import fr.insee.rmes.modeles.classification.correspondence.Poste;
import fr.insee.rmes.modeles.classification.correspondence.RawCorrespondence;

public class CorrespondencesUtils {

    private CorrespondencesUtils() {
        super();
    }

    /**
     * get in shape rawlist in tree map
     * 1 source (map id) -> many target
     */
    /* when id correspondence */
    public static Associations getCorrespondenceByCorrespondenceId(List<RawCorrespondence> rawItemsList) {

        Map<Poste, List<Poste>> mapSourceTargetItems = getTreeMapTargetItemsBySourceByCorrespondenceId(rawItemsList);
        return organizeItemTreeMap(mapSourceTargetItems);
    }

    private static Associations organizeItemTreeMap(Map<Poste, List<Poste>> mapSourceTargetItems) {
        Associations associations = new Associations();
        mapSourceTargetItems.forEach((k, v) -> {

            Association assoc = new Association(k, v);
            associations.getAssociations().add(assoc);

        });

        return associations;
    }

    /**
     * This method transforms sparql query "table" result
     * for mapping 1 source item from source classification to to many many target item in target classification
     * @param idCorrespondence
     * @param rawItemsList
     * @return
     */
    public static Map<Poste, List<Poste>> getTreeMapTargetItemsBySourceByCorrespondenceId(
        List<RawCorrespondence> rawItemsList) {
        /* TreeMap for ordering map keys */
        Map<Poste, List<Poste>> groupedListItems = new TreeMap<>();
        for (RawCorrespondence curRawCorrespondence : rawItemsList) {
            Poste posteSource = newPoste1(curRawCorrespondence);
            if ( ! groupedListItems.containsKey(posteSource)) { // initialize map if new item source
                groupedListItems.put(posteSource, new ArrayList<Poste>());
            }
            // add targetItem
            Poste targetPoste = newPoste2(curRawCorrespondence);
            groupedListItems.get(posteSource).add(targetPoste);
        }
        return groupedListItems;
    }

    /* when id1 + id2 classifications */
    public static Associations getCorrespondenceByclassificationIds(
        String codeClassification,
        String targetCodeClassification,
        List<RawCorrespondence> rawItemsList) {

        Map<Poste, List<Poste>> mapSourceTargetItems =
            getTreeMapTargetItemsBySourceByClassificationsIds(codeClassification, rawItemsList);
        return organizeItemTreeMap(mapSourceTargetItems);
    }

    /**
     * for handling asymetrical correspondencies in RDF data
     */
    public static Map<Poste, List<Poste>> getTreeMapTargetItemsBySourceByClassificationsIds(
        String codeClassificationSource,
        List<RawCorrespondence> rawItemsList) {

        /* TreeMap for ordering map keys */
        Map<Poste, List<Poste>> groupedListItems = new TreeMap<>();

        /*
         * Classification correspondences are not symetrical in database => answering question : what is source / target
         * must in raw correspondances ?
         */
        /* if false => first fields of csv result are for source classification item */
        /* if true => second part of fields */
        boolean poste1IsSource = isPoste1Source(codeClassificationSource, rawItemsList);

        for (RawCorrespondence curRawCorrespondence : rawItemsList) {
            Poste posteSource = mapRawObjectToItemCorrespondence(curRawCorrespondence, poste1IsSource);
            if ( ! groupedListItems.containsKey(posteSource)) {// initialize map
                groupedListItems.put(posteSource, new ArrayList<Poste>());
            }
            // add targetItem
            groupedListItems
                .get(posteSource)
                .add(mapRawObjectToItemCorrespondence(curRawCorrespondence, ! poste1IsSource));
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
    private static Boolean isPoste1Source(String codeClassificationSource, List<RawCorrespondence> rawItemsList) {
        // using the first correspondence ( rawItemsList.get(0) )
        // => which csv result, first or second uri field (uriPoste1/2) does contain classification source id ?
        String uriPoste1FromRaw = rawItemsList.get(0).getUriPoste1();
        String classifSource = "/codes/" + codeClassificationSource + "/";
        return ! uriPoste1FromRaw.contains(classifSource);
    }

    private static Poste mapRawObjectToItemCorrespondence(RawCorrespondence corresp, boolean poste1IsSource) {
        return (poste1IsSource ? newPoste2(corresp) : newPoste1(corresp));
    }

    private static Poste newPoste1(RawCorrespondence corresp) {
        return new Poste(
            corresp.getCodePoste1(),
            corresp.getUriPoste1(),
            corresp.getIntituleFrPoste1(),
            corresp.getIntituleEnPoste1());
    }

    private static Poste newPoste2(RawCorrespondence corresp) {
        return new Poste(
            corresp.getCodePoste2(),
            corresp.getUriPoste2(),
            corresp.getIntituleFrPoste2(),
            corresp.getIntituleEnPoste2());
    }

}
