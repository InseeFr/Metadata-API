package fr.insee.rmes.api.classifications;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

import fr.insee.rmes.api.codes.cj.CategorieJuridique;
import fr.insee.rmes.api.codes.cj.CategorieJuridiqueNiveauII;
import fr.insee.rmes.api.codes.cj.CategorieJuridiqueNiveauIII;
import fr.insee.rmes.api.codes.cj.CategoriesJuridiques;
import fr.insee.rmes.api.codes.na1973.GroupeNA1973;
import fr.insee.rmes.api.codes.naf1993.ClasseNAF1993;
import fr.insee.rmes.api.codes.naf2003.ClasseNAF2003;
import fr.insee.rmes.api.codes.naf2008.ClasseNAF2008;
import fr.insee.rmes.api.codes.naf2008.SousClasseNAF2008;
import fr.insee.rmes.api.utils.CSVUtils;
import fr.insee.rmes.api.utils.DateUtils;
import fr.insee.rmes.api.utils.ResponseUtils;
import fr.insee.rmes.api.utils.SparqlUtils;
import fr.insee.rmes.modeles.classification.Activite;
import fr.insee.rmes.modeles.classification.Activites;
import fr.insee.rmes.queries.ActivitesQueries;
import fr.insee.rmes.queries.CJQueries;
import fr.insee.rmes.queries.Na1973Queries;
import fr.insee.rmes.queries.Naf1993Queries;
import fr.insee.rmes.queries.Naf2003Queries;
import fr.insee.rmes.queries.Naf2008Queries;

@Path("/codes")
public class CodesAPI {

	private static Logger logger = LogManager.getLogger(CodesAPI.class);
	
	@Path("/cj/n2/{code: [0-9]{2}}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getCategorieJuridiqueNiveauII(@PathParam("code") String code, @HeaderParam("Accept") String header) {
		
		logger.debug("Received GET request for CJ 2nd level " + code);

		CategorieJuridiqueNiveauII cjNiveau2 = new CategorieJuridiqueNiveauII(code);
		String csvResult = SparqlUtils.executeSparqlQuery(CJQueries.getCategorieJuridiqueNiveauII(code));
		CSVUtils.populatePOJO(csvResult, cjNiveau2);
		
		if (cjNiveau2.getUri() == null) return Response.status(Status.NOT_FOUND).entity("").build();
		return Response.ok(ResponseUtils.produceResponse(cjNiveau2, header)).build();
	}

	@Path("/cj/n3/{code: [0-9]{4}}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getCategorieJuridiqueNiveauIII(@PathParam("code") String code, @HeaderParam("Accept") String header) {
		
		logger.debug("Received GET request for CJ 3rd level " + code);

		CategorieJuridiqueNiveauIII cjNiveau3 = new CategorieJuridiqueNiveauIII(code);
		String csvResult = SparqlUtils.executeSparqlQuery(CJQueries.getCategorieJuridiqueNiveauIII(code));
		CSVUtils.populatePOJO(csvResult, cjNiveau3);
		
		if (cjNiveau3.getUri() == null) return Response.status(Status.NOT_FOUND).entity("").build();
		return Response.ok(ResponseUtils.produceResponse(cjNiveau3, header)).build();
	}
	
	@SuppressWarnings("unchecked")
	@Path("/cj")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getCategoriesJuridiques(@QueryParam("code") String code, @QueryParam("date") String date
			, @HeaderParam("Accept") String header) {

		logger.debug("Received GET request for CJ: code:" + code + " date:" + date);
		
		String csvResult = "";	
		
		if (date == null) csvResult = SparqlUtils.executeSparqlQuery(CJQueries.getCJByCode(code));
		else if (date.equals("*")) csvResult = SparqlUtils.executeSparqlQuery(CJQueries.getCJ(code));
		else {
			if(!DateUtils.isValidDate(date)) return Response.status(Status.BAD_REQUEST).entity("").build();
			DateTime dt = DateUtils.getDateTimeFromDateString(date);
			csvResult = SparqlUtils.executeSparqlQuery(CJQueries.getCJByCodeAndDate(code, dt));
		}
		
		List<CategorieJuridique> cjList = (List<CategorieJuridique>) CSVUtils.populateMultiPOJO(csvResult, CategorieJuridique.class);
		
		// sub query return ,,,, result. So check list size and first element is not empty
		if (cjList.size() == 0 || cjList.get(0).getCode().equals("")) return Response.status(Status.NOT_FOUND).entity("").build();
		
		else if (header.equals(MediaType.APPLICATION_XML))
			return Response.ok(ResponseUtils.produceResponse(new CategoriesJuridiques(cjList), header)).build();
			
		else return Response.ok(ResponseUtils.produceResponse(cjList, header)).build();
	}


	@Path("/nafr2/sousClasse/{code: [0-9]{2}\\.[0-9]{2}[A-Z]}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getSousClasseNAF2008(@PathParam("code") String code, @HeaderParam("Accept") String header) {

		logger.debug("Received GET request for NAF sub-class " + code);

		SousClasseNAF2008 sousClasse = new SousClasseNAF2008(code);
		String csvResult = SparqlUtils.executeSparqlQuery(Naf2008Queries.getSousClasseNAF2008(code));
		CSVUtils.populatePOJO(csvResult, sousClasse);

		if (sousClasse.getUri() == null) return Response.status(Status.NOT_FOUND).entity("").build();
		return Response.ok(ResponseUtils.produceResponse(sousClasse, header)).build();
	}

	@Path("/nafr2/classe/{code: [0-9]{2}\\.[0-9]{2}}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getClasseNAF2008(@PathParam("code") String code, @HeaderParam("Accept") String header) {

		logger.debug("Received GET request for NAF rev. 2 class " + code);

		ClasseNAF2008 classe = new ClasseNAF2008(code);
		String csvResult = SparqlUtils.executeSparqlQuery(Naf2008Queries.getClasseNAF2008(code));
		CSVUtils.populatePOJO(csvResult, classe);

		if (classe.getUri() == null) return Response.status(Status.NOT_FOUND).entity("").build();
		return Response.ok(ResponseUtils.produceResponse(classe, header)).build();
	}
	
	@Path("/nafr1/classe/{code: [0-9]{2}\\.[0-9][A-Z]}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getClasseNAF2003(@PathParam("code") String code, @HeaderParam("Accept") String header) {

		logger.debug("Received GET request for NAF rev. 1 class " + code);

		ClasseNAF2003 classe = new ClasseNAF2003(code);
		String csvResult = SparqlUtils.executeSparqlQuery(Naf2003Queries.getClasseNAF2003(code));
		CSVUtils.populatePOJO(csvResult, classe);

		if (classe.getUri() == null) return Response.status(Status.NOT_FOUND).entity("").build();
		return Response.ok(ResponseUtils.produceResponse(classe, header)).build();
	}
	
	@Path("/naf/classe/{code: [0-9]{2}\\.[0-9][A-Z]}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getClasseNAF1993(@PathParam("code") String code, @HeaderParam("Accept") String header) {

		logger.debug("Received GET request for NAF class " + code);

		ClasseNAF1993 classe = new ClasseNAF1993(code);
		String csvResult = SparqlUtils.executeSparqlQuery(Naf1993Queries.getClasseNAF1993(code));
		CSVUtils.populatePOJO(csvResult, classe);

		if (classe.getUri() == null) return Response.status(Status.NOT_FOUND).entity("").build();
		return Response.ok(ResponseUtils.produceResponse(classe, header)).build();
	}
	
	@Path("/na73/groupe/{code: [0-9]{2}\\.[0-9]{2}}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getClasseNA1973(@PathParam("code") String code, @HeaderParam("Accept") String header) {

		logger.debug("Received GET request for NA 1973 group " + code);

		GroupeNA1973 groupe = new GroupeNA1973(code);
		String csvResult = SparqlUtils.executeSparqlQuery(Na1973Queries.getGroupeNA1973(code));
		CSVUtils.populatePOJO(csvResult, groupe);

		if (groupe.getUri() == null) return Response.status(Status.NOT_FOUND).entity("").build();
		return Response.ok(ResponseUtils.produceResponse(groupe, header)).build();
	}
	
	@SuppressWarnings("unchecked")
	@Path("/activites")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getActivities(@QueryParam("code") String code, @QueryParam("date") String date
			, @HeaderParam("Accept") String header) {

		logger.debug("Received GET request for Activities: " + code + " date:" + date);
		
		String csvResult = "";	
		
		if (date == null) csvResult = SparqlUtils.executeSparqlQuery(ActivitesQueries.getActiviteByCode(code));
		else if (date.equals("*")) csvResult = SparqlUtils.executeSparqlQuery(ActivitesQueries.getActivites(code));
		else {
			if(!DateUtils.isValidDate(date)) return Response.status(Status.BAD_REQUEST).entity("").build();
			DateTime dt = DateUtils.getDateTimeFromDateString(date);
			csvResult = SparqlUtils.executeSparqlQuery(ActivitesQueries.getActiviteByCodeAndDate(code, dt));
		}
		
		List<Activite> activityList = (List<Activite>) CSVUtils.populateMultiPOJO(csvResult, Activite.class);
		
		if (activityList.size() == 0) return Response.status(Status.NOT_FOUND).entity("").build();
		
		else if (header.equals(MediaType.APPLICATION_XML))
			return Response.ok(ResponseUtils.produceResponse(new Activites(activityList), header)).build();
			
		else return Response.ok(ResponseUtils.produceResponse(activityList, header)).build();
	}
	

}
