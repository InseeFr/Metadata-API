package fr.insee.rmes.api.operations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.config.Configuration;
import fr.insee.rmes.modeles.operations.CsvIndicateur;
import fr.insee.rmes.modeles.operations.CsvSerie;
import fr.insee.rmes.modeles.operations.Famille;
import fr.insee.rmes.modeles.operations.FamilyToOperation;
import fr.insee.rmes.modeles.operations.Indicateur;
import fr.insee.rmes.modeles.operations.ObjectWithSimsId;
import fr.insee.rmes.modeles.operations.Operation;
import fr.insee.rmes.modeles.operations.Serie;
import fr.insee.rmes.modeles.operations.SimpleObject;
import fr.insee.rmes.modeles.operations.documentations.CsvRubrique;
import fr.insee.rmes.modeles.operations.documentations.Document;
import fr.insee.rmes.modeles.operations.documentations.Rubrique;
import fr.insee.rmes.modeles.operations.documentations.RubriqueRichText;
import fr.insee.rmes.queries.operations.OperationsQueries;
import fr.insee.rmes.utils.CSVUtils;
import fr.insee.rmes.utils.FileUtils;
import fr.insee.rmes.utils.Lang;
import fr.insee.rmes.utils.SparqlUtils;

public class OperationsApiService {

    private static Logger logger = LogManager.getLogger(OperationsApiService.class);
    
    private SparqlUtils sparqlUtils;
    private CSVUtils csvUtils;

    public OperationsApiService() {
		super();
		sparqlUtils = new SparqlUtils();
		csvUtils = new CSVUtils();
	}

	public Map<String, Famille> getListeFamilyToOperation(List<FamilyToOperation> opList) {
        Map<String, Famille> familyMap = new HashMap<>();
        Map<String, Serie> serieMap = new HashMap<>();

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
                else {
                    // create family
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
            else if (StringUtils.isNotEmpty(familyToOperation.getSimsId())) {
                // sims linked to serie
                serieMap.get(familyToOperation.getSeriesId()).setSimsId(familyToOperation.getSimsId());
            }
        }
        return familyMap;
    }

    public List<FamilyToOperation> removeExclusions(List<FamilyToOperation> opList) {
        String path = String.format("%s/storage/%s", Configuration.getFileStorage(), "exclusionsInseeFr.txt");
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
                    logger.warn("Unknown exclusion type : {}", type);
                break;
            }
        }
        return opList;
    }

    public List<Rubrique> getListRubriques(String id) {
        String csvResult = sparqlUtils.executeSparqlQuery(OperationsQueries.getDocumentationRubrics(id));
        List<CsvRubrique> csvRubriques = csvUtils.populateMultiPOJO(csvResult, CsvRubrique.class);
        Map<String,Rubrique> rubriquesById = new HashMap<>();
        for (CsvRubrique cr : csvRubriques) {
            addCsvRubricToRubricMap(id, rubriquesById, cr);
        }
        return rubriquesById.values().stream().collect(Collectors.toList());

    }

	private void addCsvRubricToRubricMap(String id, Map<String, Rubrique> rubriquesById, CsvRubrique cr) {
		Rubrique r = new Rubrique(cr.getId(), cr.getUri(), cr.getType());
		r.setTitre(cr.getTitreLg1(), cr.getTitreLg2());
		r.setIdParent(cr.getIdParent());
		switch (cr.getType()) {
		    case "DATE":
		        r.setValeurSimple(cr.getValeurSimple());
		    break;
		    case "CODE_LIST":
		        SimpleObject codeListElement = new SimpleObject(
		                cr.getValeurSimple(),
		                cr.getCodeUri(),
		                cr.getLabelObjLg1(),
		                cr.getLabelObjLg2());

		        if (cr.getMaxOccurs() != null && rubriquesById.containsKey(cr.getId())) {
		            r = rubriquesById.get(cr.getId());
		            r.addValeurCode(codeListElement);
		            rubriquesById.remove(cr.getId());
		        }else {
		            r.setValeurCode(Stream.of(codeListElement).collect(Collectors.toList()));
		        }
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
		    	RubriqueRichText richTextLg1 = new RubriqueRichText(cr.getLabelLg1(), Lang.FR);                         	                	
		        if (Boolean.TRUE.equals(cr.isHasDocLg1())) {
		            String csvDocs = sparqlUtils.executeSparqlQuery(OperationsQueries.getDocuments(id, r.getId(), Lang.FR));
		            List<Document> docs = csvUtils.populateMultiPOJO(csvDocs, Document.class);
		            richTextLg1.setDocuments(docs);
		        }
		        r.addRichTexts(richTextLg1);
		        if (StringUtils.isNotEmpty(cr.getLabelLg2())) {
		        	RubriqueRichText richTextLg2 = new RubriqueRichText(cr.getLabelLg2(), Lang.EN);                         	                	
		            if (Boolean.TRUE.equals(cr.isHasDocLg2())) {
		                String csvDocs = sparqlUtils.executeSparqlQuery(OperationsQueries.getDocuments(id, r.getId(), Lang.EN));
		                List<Document> docs = csvUtils.populateMultiPOJO(csvDocs, Document.class);
		                richTextLg2.setDocuments(docs);
		            }
		            r.addRichTexts(richTextLg2);
		        }                    
		    break;
		    case "TEXT":
		        r.setLabelLg1(cr.getLabelLg1());
		        r.setLabelLg2(cr.getLabelLg2());
		    break;
		    case "GEOGRAPHY":
		        SimpleObject valeurGeo =
		        new SimpleObject(
		            cr.getValeurSimple(),
		            cr.getGeoUri(),
		            cr.getLabelObjLg1(),
		            cr.getLabelObjLg2());
		    r.setValeurGeographie(valeurGeo);
		    break;
		    default:
		    break;
		}
		rubriquesById.putIfAbsent(r.getId(), r);
	}

	/**
	 * Transform csvSeries in Serie
	 * @param csvSerie
	 * @param idSeries
	 * @return
	 */
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

        if (Boolean.TRUE.equals(csvSerie.isHasOperation())) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getOperationBySeries(idSeries));
            List<Operation> liste = csvUtils.populateMultiPOJO(csv, Operation.class);
            s.setOperations(liste);
        }

        if (Boolean.TRUE.equals(csvSerie.isHasIndic())) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getIndicBySeries(idSeries));
            List<Indicateur> liste = csvUtils.populateMultiPOJO(csv, Indicateur.class);
            s.setIndicateurs(liste);
        }
        if (Boolean.TRUE.equals(csvSerie.isHasSeeAlso())) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getSeeAlsoBySeries(idSeries));
            List<SimpleObject> liste = csvUtils.populateMultiPOJO(csv, SimpleObject.class);
            s.setSeeAlso(liste);
        }
        if (Boolean.TRUE.equals(csvSerie.isHasIsReplacedBy())) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getIsReplacedByBySeries(idSeries));
            List<Serie> liste = csvUtils.populateMultiPOJO(csv, Serie.class);
            s.setIsReplacedBy(liste);
        }
        if (Boolean.TRUE.equals(csvSerie.isHasReplaces())) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getReplacesBySeries(idSeries));
            List<Serie> liste = csvUtils.populateMultiPOJO(csv, Serie.class);
            s.setReplaces(liste);
        }
        if (Boolean.TRUE.equals(csvSerie.isHasPublisher())) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getPublishersBySeries(idSeries));
            List<SimpleObject> liste = csvUtils.populateMultiPOJO(csv, SimpleObject.class);
            s.setPublishers(liste);
        }
        if (Boolean.TRUE.equals(csvSerie.isHasContributor())) {
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
        if (StringUtils.isNotEmpty(csvIndic.getIdPublisher())) {
            SimpleObject publisher =
                new SimpleObject(
                    csvIndic.getIdPublisher(),
                    csvIndic.getUriPublisher(),
                    csvIndic.getLabelFrPublisher(),
                    csvIndic.getLabelEnPublisher());
            i.setPublisher(publisher);
        }
        if (Boolean.TRUE.equals(csvIndic.isHasContributor())) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getContributorsByIndic(idIndicateur));
            List<SimpleObject> liste = csvUtils.populateMultiPOJO(csv, SimpleObject.class);
            i.setContributors(liste);
        }
        if (Boolean.TRUE.equals(csvIndic.isHasReplaces())) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getReplacesByIndic(idIndicateur));
            List<Indicateur> liste = csvUtils.populateMultiPOJO(csv, Indicateur.class);
            i.setReplaces(liste);
        }
        if (Boolean.TRUE.equals(csvIndic.isHasIsReplacedBy())) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getIsReplacedByByIndic(idIndicateur));
            List<Indicateur> liste = csvUtils.populateMultiPOJO(csv, Indicateur.class);
            i.setIsReplacedBy(liste);
        }
        if (Boolean.TRUE.equals(csvIndic.isHasSeeAlso())) {
            String csv = sparqlUtils.executeSparqlQuery(OperationsQueries.getSeeAlsoByIndic(idIndicateur));
            List<ObjectWithSimsId> liste = csvUtils.populateMultiPOJO(csv, ObjectWithSimsId.class);
            i.setSeeAlso(liste);
        }
        if (Boolean.TRUE.equals(csvIndic.isHasWasGeneratedBy())) {
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
