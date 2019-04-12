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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.api.utils.CSVUtils;
import fr.insee.rmes.api.utils.ResponseUtils;
import fr.insee.rmes.api.utils.SparqlUtils;

@Path("/operations")
public class OperationsAPI {

	private static Logger logger = LogManager.getLogger(OperationsAPI.class);
	
	@SuppressWarnings("unchecked")
	@Path("/tree")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getOperationsTree(@HeaderParam("Accept") String header) {
		logger.debug("Received GET request operations tree");
		
		String csvResult = SparqlUtils.executeSparqlQuery(OperationsQueries.getOperationTree());
		List<FamilyToOperation> opList = (List<FamilyToOperation>) CSVUtils.populateMultiPOJO(csvResult, FamilyToOperation.class);
		
		if (opList.size() == 0) return Response.status(Status.NOT_FOUND).entity("").build();
		
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

	
	@Path("/serie/{idSeries}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getSeries(	@PathParam("idSeries") String idSeries, 
								@HeaderParam("Accept") String header) {
		logger.debug("Received GET request series");
		
		String csvResult = SparqlUtils.executeSparqlQuery(OperationsQueries.getSeries(idSeries));
		
		//Serie series = new Serie(idSeries); 
		//SeriesToOperation series = new SeriesToOperation(idSeries);
		// CSVUtils.populatePOJO(csvResult, series);
		
		List<FamilyToOperation> opList = (List<FamilyToOperation>) CSVUtils.populateMultiPOJO(csvResult, FamilyToOperation.class);
		
		if (opList.size() == 0) return Response.status(Status.NOT_FOUND).entity("").build();
		
		FamilyToOperation firstFamilyToOperation=opList.get(0);
		Serie s = new Serie(firstFamilyToOperation.getSeries(),firstFamilyToOperation.getSeriesId(),firstFamilyToOperation.getSeriesLabelLg1(), firstFamilyToOperation.getSeriesLabelLg2());
		//create family
		Famille f = new Famille(firstFamilyToOperation.getFamily(),firstFamilyToOperation.getFamilyId(), firstFamilyToOperation.getFamilyLabelLg1(),firstFamilyToOperation.getFamilyLabelLg2(), s);
		// Add fields to Serie ?
		//s.setFamily(f);
		s.setAbstractLg1(firstFamilyToOperation.getSeriesAbstractLg1());
		s.setAbstractLg2(firstFamilyToOperation.getSeriesAbstractLg2());
		s.setHistoryNoteLg1(firstFamilyToOperation.getSeriesHistoryNoteLg1());
		s.setHistoryNoteLg2(firstFamilyToOperation.getSeriesHistoryNoteLg2());

		s.setAltLabel(firstFamilyToOperation.getSeriesAltLabel());
		
		for (FamilyToOperation familyToOperation : opList) {
			
			if (StringUtils.isNotEmpty(familyToOperation.getOperationId())) {
				Operation o = new Operation(familyToOperation.getOperation(),familyToOperation.getOperationId(),familyToOperation.getOpLabelLg1(), familyToOperation.getOpLabelLg2(), familyToOperation.getSimsId());
				s.addOperation(o);
			}else if (StringUtils.isNotEmpty(familyToOperation.getIndicId())) {
				Indicateur i = new Indicateur(familyToOperation.getIndic(),familyToOperation.getIndicId(),familyToOperation.getIndicLabelLg1(), familyToOperation.getIndicLabelLg2(), familyToOperation.getSimsId());
				s.addIndicateur(i);
			}else if (StringUtils.isNotEmpty(familyToOperation.getSimsId() )) { //sims linked to serie
				s.setSimsId(familyToOperation.getSimsId());
			}else if (StringUtils.isNotEmpty(familyToOperation.getSeeAlso() )) { //series linked to seeAlso
				Serie sa = new Serie(familyToOperation.getSeeAlso(),familyToOperation.getSeeAlsoId(),familyToOperation.getSeeAlsoLabelLg1(), familyToOperation.getSeeAlsoLabelLg2());
				s.addSeeAlso(sa);
			}else if (StringUtils.isNotEmpty(familyToOperation.getIsReplacedBy() )) { //series replaced by another
				Serie irb = new Serie(familyToOperation.getIsReplacedBy(),familyToOperation.getIsReplacedById(),familyToOperation.getIsReplacedByLabelLg1(), familyToOperation.getIsReplacedByLabelLg2());
				s.addIsReplacedBy(irb);
			}else if (StringUtils.isNotEmpty(familyToOperation.getReplaces() )) { //series replaces another
				Serie rep = new Serie(familyToOperation.getReplaces(),familyToOperation.getReplacesId(),familyToOperation.getReplacesLabelLg1(), familyToOperation.getReplacesLabelLg2());
				s.addReplaces(rep);
			}
		}

	    return Response.ok(ResponseUtils.produceResponse(s, header)).build();
		
	}
	

}


/*
@SuppressWarnings("unchecked")
List<SeriesToOperation> seriesList = (List<SeriesToOperation>) CSVUtils.populateMultiPOJO(csvResult, SeriesToOperation.class);
series=seriesList.get(0);
// add operations, famille, notes, liens...
*/


