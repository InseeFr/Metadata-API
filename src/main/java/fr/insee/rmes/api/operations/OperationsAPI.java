package fr.insee.rmes.api.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
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
		
		Map<String, Family> familyMap = new HashMap<String, Family>();
		Map<String, Serie> serieMap = new HashMap<String, Serie>();

		
		for (FamilyToOperation familyToOperation : opList) {
			if (!serieMap.containsKey(familyToOperation.getSeriesId())) {
				Serie s = new Serie(familyToOperation.getSeriesId(),familyToOperation.getSeriesLabelLg1(), familyToOperation.getSeriesLabelLg2());
				serieMap.put(s.getId(), s);
				String fId = familyToOperation.getFamilyId();
				if (familyMap.containsKey(fId)) {
					familyMap.get(fId).addSerie(s);			
				}else {//create family
					Family f = new Family(familyToOperation.getFamilyId(), familyToOperation.getFamilyLabelLg1(),familyToOperation.getFamilyLabelLg2(), s);
					familyMap.put(f.getId(), f);
				}
			}
			if (StringUtils.isNotEmpty(familyToOperation.getOperationId())) {
				Operation o = new Operation(familyToOperation.getOperationId(),familyToOperation.getOpLabelLg1(), familyToOperation.getOpLabelLg2(), familyToOperation.getSimsId());
				serieMap.get(familyToOperation.getSeriesId()).addOperation(o);
			}else if (StringUtils.isNotEmpty(familyToOperation.getIndicId())) {
				Indicateur i = new Indicateur(familyToOperation.getIndicId(),familyToOperation.getIndicLabelLg1(), familyToOperation.getIndicLabelLg2(), familyToOperation.getSimsId());
				serieMap.get(familyToOperation.getSeriesId()).addIndicateur(i);
			}else if (StringUtils.isNotEmpty(familyToOperation.getSimsId() )) { //sims linked to serie
				serieMap.get(familyToOperation.getSeriesId()).setSimsId(familyToOperation.getSimsId());
			}
		}
		
		
		if (header.equals(MediaType.APPLICATION_XML))
			return Response.ok(ResponseUtils.produceResponse(new Tree(new ArrayList<Family>(familyMap.values())), header)).build();
			
		else return Response.ok(ResponseUtils.produceResponse(familyMap.values(), header)).build();
		
		
	}


}

