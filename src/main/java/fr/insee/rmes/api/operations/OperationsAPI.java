package fr.insee.rmes.api.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.api.operations.documentations.CsvRubrique;
import fr.insee.rmes.api.operations.documentations.Document;
import fr.insee.rmes.api.operations.documentations.DocumentationSims;
import fr.insee.rmes.api.operations.documentations.Rubrique;
import fr.insee.rmes.api.utils.CSVUtils;
import fr.insee.rmes.api.utils.FileUtils;
import fr.insee.rmes.api.utils.ResponseUtils;
import fr.insee.rmes.api.utils.SparqlUtils;
import fr.insee.rmes.config.Configuration;

@Path("/operations")
public class OperationsAPI {

	private static Logger logger = LogManager.getLogger(OperationsAPI.class);

	@SuppressWarnings("unchecked")
	@Path("/arborescence")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getOperationsTree(@HeaderParam("Accept") String header,  @QueryParam("diffuseur") String diffuseur) {
		logger.debug("Received GET request operations tree");

		String csvResult = SparqlUtils.executeSparqlQuery(OperationsQueries.getOperationTree());
		List<FamilyToOperation> opList = (List<FamilyToOperation>) CSVUtils.populateMultiPOJO(csvResult, FamilyToOperation.class);

		if (opList.size() == 0) return Response.status(Status.NOT_FOUND).entity("").build();
		
		if (diffuseur != null && diffuseur.equals("insee.fr")) {
			opList = removeExclusions(opList);
		}

		Map<String, Famille> familyMap = new HashMap<String, Famille>();
		Map<String, Serie> serieMap = new HashMap<String, Serie>();


		for (FamilyToOperation familyToOperation : opList) {
			if (!serieMap.containsKey(familyToOperation.getSeriesId())) {
				Serie s = new Serie(familyToOperation.getSeries(),familyToOperation.getSeriesId(),familyToOperation.getSeriesLabelLg1(), familyToOperation.getSeriesLabelLg2());
				serieMap.put(s.getId(), s);
				String fId = familyToOperation.getFamilyId();
				if (familyMap.containsKey(fId)) {
					familyMap.get(fId).addSerie(s);			
				}else {//create family
					Famille f = new Famille(familyToOperation.getFamily(),familyToOperation.getFamilyId(), familyToOperation.getFamilyLabelLg1(),familyToOperation.getFamilyLabelLg2(), s);
					familyMap.put(f.getId(), f);
				}
			}
			if (StringUtils.isNotEmpty(familyToOperation.getOperationId())) {
				Operation o = new Operation(familyToOperation.getOperation(),familyToOperation.getOperationId(),familyToOperation.getOpLabelLg1(), familyToOperation.getOpLabelLg2(), familyToOperation.getSimsId());
				serieMap.get(familyToOperation.getSeriesId()).addOperation(o);
			}else if (StringUtils.isNotEmpty(familyToOperation.getIndicId())) {
				Indicateur i = new Indicateur(familyToOperation.getIndic(),familyToOperation.getIndicId(),familyToOperation.getIndicLabelLg1(), familyToOperation.getIndicLabelLg2(), familyToOperation.getSimsId());
				serieMap.get(familyToOperation.getSeriesId()).addIndicateur(i);
			}else if (StringUtils.isNotEmpty(familyToOperation.getSimsId() )) { //sims linked to serie
				serieMap.get(familyToOperation.getSeriesId()).setSimsId(familyToOperation.getSimsId());
			}
		}


		if (header.equals(MediaType.APPLICATION_XML)) {
			Familles familles = new Familles(new ArrayList<Famille>(familyMap.values()));
			return Response.ok(ResponseUtils.produceResponse(familles, header)).build();
		}
		else return Response.ok(ResponseUtils.produceResponse(familyMap.values(), header)).build();


	}

	private List<FamilyToOperation> removeExclusions(List<FamilyToOperation> opList) {
		String path = String.format("%s/storage/%s", Configuration.BASE_PATH, "exclusionsInseeFr.txt");
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
				logger.warn("Unknown exclusion type : "+ type);
				break;
			}
		}
		return opList;
	}

	@SuppressWarnings("unchecked")
	@Path("/documentation/{id: [0-9]{4}}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getDocumentation(@HeaderParam("Accept") String header, @PathParam("id") String id) {
		logger.debug("Received GET request documentation");

		String csvResult = SparqlUtils.executeSparqlQuery(OperationsQueries.getDocumentationTitle(id));
		DocumentationSims sims =  new DocumentationSims();
		CSVUtils.populatePOJO(csvResult, sims);

		if (sims.getUri() == null) return Response.status(Status.NOT_FOUND).entity("").build();

		csvResult = SparqlUtils.executeSparqlQuery(OperationsQueries.getDocumentationRubrics(id));
		List<CsvRubrique> csvRubriques = (List<CsvRubrique>) CSVUtils.populateMultiPOJO(csvResult, CsvRubrique.class);
		List<Rubrique> rubriques = new ArrayList<>();
		for (CsvRubrique cr : csvRubriques) {
			Rubrique r = new Rubrique(cr.getId(),cr.getUri(),cr.getType());
			r.setTitre(cr.getTitreLg1(),cr.getTitreLg2());
			r.setIdParent(cr.getIdParent());
			switch (cr.getType()) {
			case "DATE":
				r.setValeurSimple(cr.getValeurSimple());
				break;
			case "CODE_LIST":
				SimpleObject valeurCode = new SimpleObject(cr.getValeurSimple(), cr.getCodeUri(), cr.getLabelObjLg1(), cr.getLabelObjLg2());
				r.setValeurCode(valeurCode);
				break;	
			case "ORGANISATION":
				SimpleObject valeurOrg = new SimpleObject(cr.getValeurSimple(), cr.getOrganisationUri(), cr.getLabelObjLg1(), cr.getLabelObjLg2());
				r.setValeurOrganisation(valeurOrg);
				break;	
			case "RICH_TEXT":
				if (cr.getHasDoc()) {
					String csvDocs = SparqlUtils.executeSparqlQuery(OperationsQueries.getDocuments(id,r.getId()));
					List<Document> docs = (List<Document>) CSVUtils.populateMultiPOJO(csvDocs, Document.class);
					r.setDocuments(docs);}
			case "TEXT":
				r.setLabelLg1(cr.getLabelLg1());
				r.setLabelLg2(cr.getLabelLg2());
				break;	
			default:
				break;
			}
			rubriques.add(r);
		}
		sims.setRubriques(rubriques);

		return Response.ok(ResponseUtils.produceResponse(sims, header)).build();
	}


	@SuppressWarnings("unchecked")
	@Path("/serie/{idSeries}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getSeries(	@PathParam("idSeries") String idSeries, 
			@HeaderParam("Accept") String header) {
		logger.debug("Received GET request series");

		String csvResult = SparqlUtils.executeSparqlQuery(OperationsQueries.getSeries(idSeries));
		CsvSerie csvSerie = new CsvSerie();
		CSVUtils.populatePOJO(csvResult, csvSerie);

		if (csvSerie.getSeriesId() == null) return Response.status(Status.NOT_FOUND).entity("").build();

		Serie s = new Serie(csvSerie.getSeries(),csvSerie.getSeriesId(),csvSerie.getSeriesLabelLg1(), csvSerie.getSeriesLabelLg2());
		s.setSimsId(csvSerie.getSimsId());
		//create family
		SimpleObject fam = new SimpleObject(csvSerie.getFamilyId(), csvSerie.getFamily(), csvSerie.getFamilyLabelLg1(),csvSerie.getFamilyLabelLg2());
		s.setFamily(fam);

		if (StringUtils.isNotEmpty(csvSerie.getSeriesAbstractLg1())){
			s.setAbstractLg1(csvSerie.getSeriesAbstractLg1());
			s.setAbstractLg2(csvSerie.getSeriesAbstractLg2());
		}
		if (StringUtils.isNotEmpty(csvSerie.getSeriesHistoryNoteLg1())){
			s.setHistoryNoteLg1(csvSerie.getSeriesHistoryNoteLg1());
			s.setHistoryNoteLg2(csvSerie.getSeriesHistoryNoteLg2());
		}
		if (StringUtils.isNotEmpty(csvSerie.getType()) ) {
			SimpleObject type = new SimpleObject(csvSerie.getTypeId(), csvSerie.getType(), csvSerie.getTypeLabelLg1(),csvSerie.getTypeLabelLg2());
			s.setType(type);
		}
		if (StringUtils.isNotEmpty(csvSerie.getPeriodicity()) ) {
			SimpleObject periodicity = new SimpleObject(csvSerie.getPeriodicityId(), csvSerie.getPeriodicity(), csvSerie.getPeriodicityLabelLg1(),csvSerie.getPeriodicityLabelLg2());
			s.setAccrualPeriodicity(periodicity);
		}
		if (StringUtils.isNotEmpty(csvSerie.getSeriesAltLabel()) ) {
			s.setAltLabel(csvSerie.getSeriesAltLabel());
		}
		
		if (csvSerie.getHasOperation()) {
			String csv = SparqlUtils.executeSparqlQuery(OperationsQueries.getOperationBySeries(idSeries));
			List<Operation> liste = (List<Operation>) CSVUtils.populateMultiPOJO(csv, Operation.class);
			s.setOperations(liste);
		}
		
		if (csvSerie.getHasIndic()) {
			String csv = SparqlUtils.executeSparqlQuery(OperationsQueries.getIndicBySeries(idSeries));
			List<Indicateur> liste = (List<Indicateur>) CSVUtils.populateMultiPOJO(csv, Indicateur.class);
			s.setIndicateurs(liste);
		}
		if (csvSerie.getHasSeeAlso()) {
			String csv = SparqlUtils.executeSparqlQuery(OperationsQueries.getSeeAlsoBySeries(idSeries));
			List<SimpleObject> liste = (List<SimpleObject>) CSVUtils.populateMultiPOJO(csv, SimpleObject.class);
			s.setSeeAlso(liste);
		}
		if (csvSerie.getHasIsReplacedBy()) {
			String csv = SparqlUtils.executeSparqlQuery(OperationsQueries.getIsReplacedByBySeries(idSeries));
			List<Serie> liste = (List<Serie>) CSVUtils.populateMultiPOJO(csv, Serie.class);
			s.setIsReplacedBy(liste);
		}
		if (csvSerie.getHasReplaces()) {
			String csv = SparqlUtils.executeSparqlQuery(OperationsQueries.getReplacesBySeries(idSeries));
			List<Serie> liste = (List<Serie>) CSVUtils.populateMultiPOJO(csv, Serie.class);
			s.setReplaces(liste);
		}
		if (csvSerie.getHasCreator()) {
			String csv = SparqlUtils.executeSparqlQuery(OperationsQueries.getCreatorsBySeries(idSeries));
			List<SimpleObject> liste = (List<SimpleObject>) CSVUtils.populateMultiPOJO(csv, SimpleObject.class);
			s.setCreators(liste);
		}
		if (csvSerie.getHasContributor()) {
			String csv = SparqlUtils.executeSparqlQuery(OperationsQueries.getContributorsBySeries(idSeries));
			List<SimpleObject> liste = (List<SimpleObject>) CSVUtils.populateMultiPOJO(csv, SimpleObject.class);
			s.setContributors(liste);
		}

		return Response.ok(ResponseUtils.produceResponse(s, header)).build();

	}
	
	@SuppressWarnings("unchecked")
	@Path("/indicateur/{idIndicateur}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getIndicateur(	@PathParam("idIndicateur") String idIndicateur, 
			@HeaderParam("Accept") String header) {
		logger.debug("Received GET request indicator");

		String csvResult = SparqlUtils.executeSparqlQuery(OperationsQueries.getIndicator(idIndicateur));
		CsvIndicateur csvIndic = new CsvIndicateur();
		CSVUtils.populatePOJO(csvResult, csvIndic);

		if (csvIndic.getId() == null) return Response.status(Status.NOT_FOUND).entity("").build();

		Indicateur i = new Indicateur(csvIndic.getIndic(),csvIndic.getId(),csvIndic.getLabelLg1(), csvIndic.getLabelLg2(), csvIndic.getSimsId());

		if (StringUtils.isNotEmpty(csvIndic.getAltLabelLg1()) || StringUtils.isNotEmpty(csvIndic.getAltLabelLg2())  ) {
			i.setAltLabel(csvIndic.getAltLabelLg1(), csvIndic.getAltLabelLg2());
		}
		if (StringUtils.isNotEmpty(csvIndic.getAbstractLg1())){
			i.setAbstractLg1(csvIndic.getAbstractLg1());
			i.setAbstractLg2(csvIndic.getAbstractLg2());
		}
		if (StringUtils.isNotEmpty(csvIndic.getHistoryNoteLg1())){
			i.setHistoryNoteLg1(csvIndic.getHistoryNoteLg1());
			i.setHistoryNoteLg2(csvIndic.getHistoryNoteLg2());
		}
		if (StringUtils.isNotEmpty(csvIndic.getIdCreator())) {
			SimpleObject creator =  new SimpleObject(csvIndic.getIdCreator(), csvIndic.getUriCreator(), csvIndic.getLabelFrCreator(),csvIndic.getLabelEnCreator());
			i.setCreator(creator);
		}
		if (csvIndic.getHasContributor()) {
			String csv = SparqlUtils.executeSparqlQuery(OperationsQueries.getContributorsByIndic(idIndicateur));
			List<SimpleObject> liste = (List<SimpleObject>) CSVUtils.populateMultiPOJO(csv, SimpleObject.class);
			i.setContributors(liste);
		}
		if (csvIndic.getHasReplaces()) {
			String csv = SparqlUtils.executeSparqlQuery(OperationsQueries.getReplacesByIndic(idIndicateur));
			List<Indicateur> liste = (List<Indicateur>) CSVUtils.populateMultiPOJO(csv, Indicateur.class);
			i.setReplaces(liste);
		}
		if (csvIndic.getHasIsReplacedBy()) {
			String csv = SparqlUtils.executeSparqlQuery(OperationsQueries.getIsReplacedByByIndic(idIndicateur));
			List<Indicateur> liste = (List<Indicateur>) CSVUtils.populateMultiPOJO(csv, Indicateur.class);
			i.setIsReplacedBy(liste);
		}
		if (csvIndic.getHasSeeAlso()) {
			String csv = SparqlUtils.executeSparqlQuery(OperationsQueries.getSeeAlsoByIndic(idIndicateur));
			List<SimpleObject> liste = (List<SimpleObject>) CSVUtils.populateMultiPOJO(csv, SimpleObject.class);
			i.setSeeAlso(liste);
		}
		if (csvIndic.getHasWasGeneratedBy()) {
			String csv = SparqlUtils.executeSparqlQuery(OperationsQueries.getWasGeneratedByByIndic(idIndicateur));
			List<Serie> liste = (List<Serie>) CSVUtils.populateMultiPOJO(csv, Serie.class);
			i.setWasGeneratedBy(liste);
		}
		if (StringUtils.isNotEmpty(csvIndic.getPeriodicity()) ) {
			SimpleObject periodicity = new SimpleObject(csvIndic.getPeriodicityId(), csvIndic.getPeriodicity(), csvIndic.getPeriodicityLabelLg1(),csvIndic.getPeriodicityLabelLg2());
			i.setAccrualPeriodicity(periodicity);
		}

		return Response.ok(ResponseUtils.produceResponse(i, header)).build();

	}

}




