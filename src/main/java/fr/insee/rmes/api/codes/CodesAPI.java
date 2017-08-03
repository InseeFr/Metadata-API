package fr.insee.rmes.api.codes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.api.utils.SparqlUtils;

@Path("/codes")
public class CodesAPI {

	private static Logger logger = LogManager.getLogger(CodesAPI.class);

	@Path("/cj/n3/{code}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public CategorieJuridiqueNiveauIII getCategorieJuridiqueNiveauIII(@PathParam("code") String code) {
		CategorieJuridiqueNiveauIII cjNiveau3 = new CategorieJuridiqueNiveauIII();
		cjNiveau3.setCode(code);
		String csvResult = SparqlUtils.executeSparqlQuery(CJQueries.getCategorieJuridiqueNiveauIII(code));
		cjNiveau3.populateFromCSV(csvResult);
		return cjNiveau3;
	}

	@Path("/nafr2/sousClasse/{code}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public SousClasseNAF2008 getSousClasseNAF2008(@PathParam("code") String code) {

		logger.debug("Received GET request for NAF sub-class " + code);

		SousClasseNAF2008 sousClasse = new SousClasseNAF2008(code);
		sousClasse.setIntitule("Ici la sous-classe NAF");
		sousClasse.setUri("http://id.insee.fr/codes/nafr2/sousClasse/" + code);
		return sousClasse;
	}

	@Path("/nafr2/classe/{code}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ClasseNAF2008 getClasseNAF2008(@PathParam("code") String code) {

		logger.debug("Received GET request for NAF class " + code);

		ClasseNAF2008 classe = new ClasseNAF2008();
		classe.setCode(code);
		classe.setIntitule("Ici la classe NAF");
		classe.setUri("http://id.insee.fr/codes/nafr2/sousClasse/" + code);
		return classe;
	}

}
