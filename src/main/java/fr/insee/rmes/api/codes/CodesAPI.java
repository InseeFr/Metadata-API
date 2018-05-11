package fr.insee.rmes.api.codes;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.api.codes.cj.CJQueries;
import fr.insee.rmes.api.codes.cj.CategorieJuridiqueNiveauII;
import fr.insee.rmes.api.codes.cj.CategorieJuridiqueNiveauIII;
import fr.insee.rmes.api.codes.naf2008.ClasseNAF2008;
import fr.insee.rmes.api.codes.naf2008.Naf2008Queries;
import fr.insee.rmes.api.codes.naf2008.SousClasseNAF2008;
import fr.insee.rmes.api.utils.CSVUtils;
import fr.insee.rmes.api.utils.ResponseUtils;
import fr.insee.rmes.api.utils.SparqlUtils;

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

		logger.debug("Received GET request for NAF class " + code);

		ClasseNAF2008 classe = new ClasseNAF2008(code);
		String csvResult = SparqlUtils.executeSparqlQuery(Naf2008Queries.getClasseNAF2008(code));
		CSVUtils.populatePOJO(csvResult, classe);

		if (classe.getUri() == null) return Response.status(Status.NOT_FOUND).entity("").build();
		return Response.ok(ResponseUtils.produceResponse(classe, header)).build();
	}

}
