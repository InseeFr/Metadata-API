package fr.insee.rmes.api.geo;

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

import fr.insee.rmes.api.MetadataApi;
import fr.insee.rmes.modeles.geo.Commune;
import fr.insee.rmes.modeles.geo.Country;
import fr.insee.rmes.modeles.geo.Region;
import fr.insee.rmes.queries.geo.GeoQueries;
import fr.insee.rmes.utils.ResponseUtils;

@Path("/geo")
public class GeoAPI extends MetadataApi {

    private static Logger logger = LogManager.getLogger(GeoAPI.class);

    @Path("/commune/{code: [0-9][0-9AB][0-9]{3}}")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    public Response getCommune(@PathParam("code") String code, @HeaderParam("Accept") String header) {

        logger.debug("Received GET request for commune " + code);

        Commune commune = new Commune(code);
        String csvResult = sparqlUtils.executeSparqlQuery(GeoQueries.getCommune(code));
        csvUtils.populatePOJO(csvResult, commune);

        if (commune.getUri() == null) return Response.status(Status.NOT_FOUND).entity("").build();
        return Response.ok(ResponseUtils.produceResponse(commune, header)).build();
    }

    @Path("/pays/{code: 99[0-9]{3}}")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    public Response getCountry(@PathParam("code") String code, @HeaderParam("Accept") String header) {

        logger.debug("Received GET request for country " + code);

        Country country = new Country(code);
        String csvResult = sparqlUtils.executeSparqlQuery(GeoQueries.getCountry(code));
        csvUtils.populatePOJO(csvResult, country);

        if (country.getUri() == null) return Response.status(Status.NOT_FOUND).entity("").build();
        return Response.ok(ResponseUtils.produceResponse(country, header)).build();
    }

    @Path("/region/{code: [0-9]{2}}")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    public Response getRegion(@PathParam("code") String code, @HeaderParam("Accept") String header) {

        logger.debug("Received GET request for region " + code);

        Region region = new Region(code);
        String csvResult = sparqlUtils.executeSparqlQuery(GeoQueries.getRegion(code));
        csvUtils.populatePOJO(csvResult, region);

        if (region.getUri() == null) return Response.status(Status.NOT_FOUND).entity("").build();
        return Response.ok(ResponseUtils.produceResponse(region, header)).build();
    }
}
