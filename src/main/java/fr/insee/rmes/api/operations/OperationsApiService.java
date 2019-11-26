package fr.insee.rmes.api.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.config.Configuration;
import fr.insee.rmes.modeles.operations.CsvIndicateur;
import fr.insee.rmes.modeles.operations.CsvSerie;
import fr.insee.rmes.modeles.operations.Famille;
import fr.insee.rmes.modeles.operations.FamilyToOperation;
import fr.insee.rmes.modeles.operations.Indicateur;
import fr.insee.rmes.modeles.operations.Operation;
import fr.insee.rmes.modeles.operations.Serie;
import fr.insee.rmes.modeles.operations.SimpleObject;
import fr.insee.rmes.modeles.operations.documentations.CsvRubrique;
import fr.insee.rmes.modeles.operations.documentations.Document;
import fr.insee.rmes.modeles.operations.documentations.Rubrique;
import fr.insee.rmes.queries.operations.OperationsQueries;
import fr.insee.rmes.utils.CSVUtils;
import fr.insee.rmes.utils.FileUtils;
import fr.insee.rmes.utils.SparqlUtils;

public class OperationsApiService {

    private static Logger logger = LogManager.getLogger(OperationsApiService.class);
    private SparqlUtils sparqlUtils = new SparqlUtils();
    private CSVUtils csvUtils = new CSVUtils();

    public Map<String, Famille> getListeFamilyToOperation(List<FamilyToOperation> opList) {
        Map<String, Famille> familyMap = new HashMap<String, Famille>();
        Map<String, Serie> serieMap = new HashMap<String, Serie>();

        for (FamilyToOperation familyToOperation : opList) {
            if ( ! serieMap.containsKey(familyToOperation.getSeriesId())) {
                Serie s =
                    new Serie(
                        familyToOperation.getSeries(),
                        familyToOperation.getSeriesId(),
                        familyToOperation.getSeriesLabelLg1(),
                        familyToOperation.getSeriesLabelLg2());
                s.setAltLabel(familyToOperation.getSeriesAltLabelLg1(), familyToOperation.getSeriesAltLabelLg2());
                serieMap.put(s.getId(), s);
                String fId = familyToOperation.getFamilyId();
                if (familyMap.containsKey(fId)) {
                    familyMap.get(fId).addSerie(s);
                }
                else {// create family
                    Famille f =
                        new Famille(
                            familyToOperation.getFamily(),
                            familyToOperation.getFamilyId(),
                            familyToOperation.getFamilyLabelLg1(),
                            familyToOperation.getFamilyLabelLg2(),
                            s);
                    f.setAltLabel(familyToOperation.getFamilyAltLabelLg1(), familyToOperation.getFamilyAltLabelLg2());
                    familyMap.put(f.getId(), f);
                }
            }
            if (StringUtils.isNotEmpty(familyToOperation.getOperationId())) {
                Operation o =
                    new Operation(
                        familyToOperation.getOperation(),
                        familyToOperation.getOperationId(),
                        familyToOperation.getOpLabelLg1(),
                        familyToOperation.getOpLabelLg2(),
                        familyToOperation.getSimsId());
                o.setAltLabel(familyToOperation.getOpAltLabelLg1(), familyToOperation.getOpAltLabelLg2());
                serieMap.get(familyToOperation.getSeriesId()).addOperation(o);
            }
            else if (StringUtils.isNotEmpty(familyToOperation.getIndicId())) {
                Indicateur i =
                    new Indicateur(
                        familyToOperation.getIndic(),
                        familyToOperation.getIndicId(),
                        familyToOperation.getIndicLabelLg1(),
                        familyToOperation.getIndicLabelLg2(),
                        familyToOperation.getSimsId());
                i.setAltLabel(familyToOperation.getIndicAltLabelLg1(), familyToOperation.getIndicAltLabelLg2());
                serieMap.get(familyToOperation.getSeriesId()).addIndicateur(i);
            }
            else if (StringUtils.isNotEmpty(familyToOperation.getSimsId())) { // sims linked to serie
                serieMap.get(familyToOperation.getSeriesId()).setSimsId(familyToOperation.getSimsId());
            }
        }
        return familyMap;
    }

    public List<FamilyToOperation> removeExclusions(List<FamilyToOperation> opList) {
        String path = String.format("%s/storage/%s", Configuration.FILE_STORAGE_LOCATION, "exclusionsInseeFr.txt");
        List<List<String>> fileContent = FileUtils.readFile(path, ";");
        if (fileContent == null || fileContent.isEmpty()) {
            logger.warn("Exclusion file empty");
            return opList;
        }
        for (List<String> line : fileContent) {
            String type = line.get(0).trim();
            String id = line.get(1).trim();

            switch (type) {
                case "famille":
                    opList.removeIf(op -> (op.getFamilyId().equals(id)));
                break;
                case "serie":
                    opList.removeIf(op -> (op.getSeriesId().equals(id)));
                break;
                case "operation":
                    opList.removeIf(op -> (op.getOperationId().equals(id)));
                break;
                case "indicateur":
                    opList.removeIf(op -> (op.getIndicId().equals(id)));
                break;
                default:
                    logger.warn("Unknown exclusion type : " + type);
                break;
            }
        }
        return opList;
    }

    public List<Rubrique> getListRubriques(String csvResult, String id) {
        csvResult = sparqlUtils.executeSparqlQuery(OperationsQueries.getDocumentationRubrics(id));
        List<CsvRubrique> csvRubriques = csvUtils.populateMultiPOJO(csvResult, CsvRubrique.class);
        List<Rubrique> rubriques = new ArrayList<>();
        for (CsvRubrique cr : csvRubriques) {
            Rubrique r = new Rubrique(cr.getId(), cr.getUri(), cr.getType());
            r.setTitre(cr.getTitreLg1(), cr.getTitreLg2());
            r.setIdParent(cr.getIdParent());
            switch (cr.getType()) {
                case "DATE":
                    r.setValeurSimple(cr.getValeurSimple());
                break;
                case "CODE_LIST":
                    SimpleObject valeurCode =
                        new SimpleObject(
                            cr.getValeurSimple(),
                            cr.getCodeUri(),
                            cr.getLabelObjLg1(),
                            cr.getLabelObjLg2());
                    r.setValeurCode(valeurCode);
                break;
                case "ORGANISATION":
                    SimpleObject valeurOrg =
                        new SimpleObject(
                            cr.getValeurSimple(),
                            cr.getOrganisationUri(),
                            cr.getLabelObjLg1(),
                            cr.getLabelObjLg2());
                    r.setValeurOrganisation(valeurOrg);
                break;
                case "RICH_TEXT":
                    if (cr.getHasDoc()) {
                        String csvDocs = sparqlUtils.executeSparqlQuery(OperationsQueries.getDocuments(id, r.getId()));
                        List<Document> docs = csvUtils.populateMultiPOJO(csvDocs, Document.class);
                        r.setDocuments(docs);
                    }
                case "TEXT":
                    r.setLabelLg1(cr.getLabelLg1());
                    r.setLabelLg2(cr.getLabelLg2());
                break;
                default:
                break;
            }
            rubriques.add(r);
        }
        return rubriques;

    }

    public Serie getSerie(CsvSerie csvSerie, String idSeries) {
        Serie s =
            new Serie(
                csvSerie.getSeries(),
                csvSerie.getSeriesId(),
                csvSerie.getSeriesLabelLg1(),
                csvSerie.getSeriesLabelLg2());
        s.setSimsId(csvSerie.getSimsId());
        // create family
        SimpleObject fam =
            new SimpleObject(
                csvSerie.getFamilyId(),
                csvSerie.getFamily(),
                csvSerie.getFamilyLabelLg1(),
                csvSerie.getFamilyLabelLg2());
        s.setFamily(fam);

        if (StringUtils.isNotEmpty(csvSerie.getSeriesAbstractLg1())) {
            s.setAbstractLg1(csvSerie.getSeriesAbstractLg1());
            s.setAbstractLg2(csvSerie.getSeriesAbstractLg2());
        }
        if (StringUtils.isNotEmpty(csvSerie.getSeriesHistoryNoteLg1())) {
            s.setHistoryNoteLg1(csvSerie.getSeriesHistoryNoteLg1());
            s.setHistoryNoteLg2(csvSerie.getSeriesHistoryNoteLg2());
        }
        if (StringUtils.isNotEmpty(csvSerie.getType())) {
            SimpleObject type =
                new SimpleObject(
                    csvSerie.getTypeId(),
                    csvSerie.getType(),
                    csvSerie.getTypeLabelLg1(),
                    csvSerie.getTypeLabelLg2());
            s.setType(type);
        }
        if (StringUtils.isNotEmpty(csvSerie.getPeriodicity())) {
            SimpleObject periodicity =
                new SimpleObject(
                    csvSerie.getPeriodicityId(),
                    csvSerie.getPeriodicity(),
                    csvSerie.getPeriodicityLabelLg1(),
                    csvSerie.getPeriodicityLabelLg2());
            s.setAccrualPeriodicity(periodicity);
        }
        if (StringUtils.isNotEmpty(csvSerie.getSeriesAltLabelLg1())
            || StringUtils.isNotEmpty(csvSerie.getSeriesAltLabelLg2())) {
            s.setAltLabel(csvSerie.getSeriesAltLabelLg1(), csvSerie.getSeriesAltLabelLg2());
        }

        if (csvSerie.getHasOperation()) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getOperationBySeries(idSeries));
            List<Operation> liste = csvUtils.populateMultiPOJO(csv, Operation.class);
            s.setOperations(liste);
        }

        if (csvSerie.getHasIndic()) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getIndicBySeries(idSeries));
            List<Indicateur> liste = csvUtils.populateMultiPOJO(csv, Indicateur.class);
            s.setIndicateurs(liste);
        }
        if (csvSerie.getHasSeeAlso()) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getSeeAlsoBySeries(idSeries));
            List<SimpleObject> liste = csvUtils.populateMultiPOJO(csv, SimpleObject.class);
            s.setSeeAlso(liste);
        }
        if (csvSerie.getHasIsReplacedBy()) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getIsReplacedByBySeries(idSeries));
            List<Serie> liste = csvUtils.populateMultiPOJO(csv, Serie.class);
            s.setIsReplacedBy(liste);
        }
        if (csvSerie.getHasReplaces()) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getReplacesBySeries(idSeries));
            List<Serie> liste = csvUtils.populateMultiPOJO(csv, Serie.class);
            s.setReplaces(liste);
        }
        if (csvSerie.getHasCreator()) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getCreatorsBySeries(idSeries));
            List<SimpleObject> liste = csvUtils.populateMultiPOJO(csv, SimpleObject.class);
            s.setCreators(liste);
        }
        if (csvSerie.getHasContributor()) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getContributorsBySeries(idSeries));
            List<SimpleObject> liste = csvUtils.populateMultiPOJO(csv, SimpleObject.class);
            s.setContributors(liste);
        }
        return s;
    }

    public Indicateur getIndicateur(CsvIndicateur csvIndic, String idIndicateur) {
        Indicateur i =
            new Indicateur(
                csvIndic.getIndic(),
                csvIndic.getId(),
                csvIndic.getLabelLg1(),
                csvIndic.getLabelLg2(),
                csvIndic.getSimsId());

        if (StringUtils.isNotEmpty(csvIndic.getAltLabelLg1()) || StringUtils.isNotEmpty(csvIndic.getAltLabelLg2())) {
            i.setAltLabel(csvIndic.getAltLabelLg1(), csvIndic.getAltLabelLg2());
        }
        if (StringUtils.isNotEmpty(csvIndic.getAbstractLg1())) {
            i.setAbstractLg1(csvIndic.getAbstractLg1());
            i.setAbstractLg2(csvIndic.getAbstractLg2());
        }
        if (StringUtils.isNotEmpty(csvIndic.getHistoryNoteLg1())) {
            i.setHistoryNoteLg1(csvIndic.getHistoryNoteLg1());
            i.setHistoryNoteLg2(csvIndic.getHistoryNoteLg2());
        }
        if (StringUtils.isNotEmpty(csvIndic.getIdCreator())) {
            SimpleObject creator =
                new SimpleObject(
                    csvIndic.getIdCreator(),
                    csvIndic.getUriCreator(),
                    csvIndic.getLabelFrCreator(),
                    csvIndic.getLabelEnCreator());
            i.setCreator(creator);
        }
        if (csvIndic.getHasContributor()) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getContributorsByIndic(idIndicateur));
            List<SimpleObject> liste = csvUtils.populateMultiPOJO(csv, SimpleObject.class);
            i.setContributors(liste);
        }
        if (csvIndic.getHasReplaces()) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getReplacesByIndic(idIndicateur));
            List<Indicateur> liste = csvUtils.populateMultiPOJO(csv, Indicateur.class);
            i.setReplaces(liste);
        }
        if (csvIndic.getHasIsReplacedBy()) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getIsReplacedByByIndic(idIndicateur));
            List<Indicateur> liste = csvUtils.populateMultiPOJO(csv, Indicateur.class);
            i.setIsReplacedBy(liste);
        }
        if (csvIndic.getHasSeeAlso()) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getSeeAlsoByIndic(idIndicateur));
            List<SimpleObject> liste = csvUtils.populateMultiPOJO(csv, SimpleObject.class);
            i.setSeeAlso(liste);
        }
        if (csvIndic.getHasWasGeneratedBy()) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getWasGeneratedByByIndic(idIndicateur));
            List<Serie> liste = csvUtils.populateMultiPOJO(csv, Serie.class);
            i.setWasGeneratedBy(liste);
        }
        if (StringUtils.isNotEmpty(csvIndic.getPeriodicity())) {
            SimpleObject periodicity =
                new SimpleObject(
                    csvIndic.getPeriodicityId(),
                    csvIndic.getPeriodicity(),
                    csvIndic.getPeriodicityLabelLg1(),
                    csvIndic.getPeriodicityLabelLg2());
            i.setAccrualPeriodicity(periodicity);
        }
        return i;
    }

}
