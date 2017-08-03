package fr.insee.rmes.api.geo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.api.geo.Region;

@Path("/geo")
public class GeoAPI {

	private static Logger logger = LogManager.getLogger(GeoAPI.class);

	@Path("/commune/{code: [0-9][0-9AB][0-9]{3}}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Region getCommune(@PathParam("code") String code) {

		logger.debug("Received GET request for commune " + code);

		Region region = new Region();
		region.setCode("75056");
		region.setIntitule("Paris");
		region.setUri("http://id.insee.fr/geo/commune/75056");
		
		return region;
	}

	@Path("/pays/{code: 99[0-9]{3}}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Country getCountry(@PathParam("code") String code) {

		logger.debug("Received GET request for country " + code);

		Country country = new Country();
		country.setCode("99126");
		country.setIntitule("Japon");
		country.setUri("http://id.insee.fr/geo/pays/99126");
		
		return country;
	}

	@Path("/region/{code: [0-9]{2}}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Region getRegion(@PathParam("code") String code) {

		logger.debug("Received GET request for region " + code);

		Region region = new Region();
		region.setCode("27");
		region.setIntitule("Bourgogne-Franche-Comté");
		region.setUri("http://id.insee.fr/geo/region/27");
		
		//return Response.status(Status.OK).entity(region).build();
		return region;
	}
}
