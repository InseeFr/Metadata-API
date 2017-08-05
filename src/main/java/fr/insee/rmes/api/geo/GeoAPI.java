package fr.insee.rmes.api.geo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.api.utils.CSVUtils;
import fr.insee.rmes.api.utils.SparqlUtils;

@Path("/geo")
public class GeoAPI {

	private static Logger logger = LogManager.getLogger(GeoAPI.class);

	@Path("/commune/{code: [0-9][0-9AB][0-9]{3}}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Commune getCommune(@PathParam("code") String code) {

		logger.debug("Received GET request for commune " + code);

		Commune commune = new Commune(code);
		String csvResult = SparqlUtils.executeSparqlQuery(GeoQueries.getCommune(code));
		CSVUtils.populatePOJO(csvResult, commune);
		
		return commune;
	}

	@Path("/pays/{code: 99[0-9]{3}}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Country getCountry(@PathParam("code") String code) {

		logger.debug("Received GET request for country " + code);

		Country country = new Country(code);
		String csvResult = SparqlUtils.executeSparqlQuery(GeoQueries.getCountry(code));
		CSVUtils.populatePOJO(csvResult, country);
		
		return country;
	}

	@Path("/region/{code: [0-9]{2}}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Region getRegion(@PathParam("code") String code) {

		logger.debug("Received GET request for region " + code);

		Region region = new Region(code);
		String csvResult = SparqlUtils.executeSparqlQuery(GeoQueries.getRegion(code));
		CSVUtils.populatePOJO(csvResult, region);
		
		return region;
	}
}
