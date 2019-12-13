package fr.insee.rmes.api.geo;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/geo")
@Tag(name = "géographie", description = "Geographie API")
public class GeoAPI extends MetadataApi {

    private static Logger logger = LogManager.getLogger(GeoAPI.class);

    @Path("/commune/{code: [0-9][0-9AB][0-9]{3}}")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getcogcom",
        summary = "Informations sur une commune française identifiée par son code (cinq caractères)",
        description = "Cette requête renvoie également les communes des collectivités d'Outre-Mer",
        responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = Commune.class)))
        })
    public Response getCommune(
        @Parameter(
            description = "Code de la commune (cinq caractères)",
            required = true,
            schema = @Schema(pattern = "[0-9][0-9AB][0-9]{3}", type = "string")) @PathParam("code") String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header) {

        logger.debug("Received GET request for commune {0}", code);

        Commune commune = new Commune(code);
        String csvResult = sparqlUtils.executeSparqlQuery(GeoQueries.getCommune(code));
        commune = (Commune) csvUtils.populatePOJO(csvResult, commune);

        if (commune.getUri() == null) {
            return Response.status(Status.NOT_FOUND).entity("").build();
        }
        else {
            return Response.ok(responseUtils.produceResponse(commune, header)).build();
        }
    }

    @Path("/pays/{code: 99[0-9]{3}}")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getcogpay",
        summary = "Informations sur un pays identifié par son code (cinq chiffres)",
        responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = Country.class)))
        })
    public Response getCountry(
        @Parameter(
            description = "Code du pays (cinq chiffres, débutant par 99)",
            required = true,
            schema = @Schema(pattern = "99[0-9]{3}", type = "string")) @PathParam("code") String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header) {

        logger.debug("Received GET request for country {0}", code);

        Country country = new Country(code);
        String csvResult = sparqlUtils.executeSparqlQuery(GeoQueries.getCountry(code));
        country = (Country) csvUtils.populatePOJO(csvResult, country);

        if (country.getUri() == null) {
            return Response.status(Status.NOT_FOUND).entity("").build();
        }
        else {
            return Response.ok(responseUtils.produceResponse(country, header)).build();
        }

    }

    @Path("/region/{code: [0-9]{2}}")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getcogreg",
        summary = "Informations sur une région française identifiée par son code (deux chiffres)",
        responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = Region.class)))
        })
    public Response getRegion(
        @Parameter(
            description = "Code de la région (deux chiffres)",
            required = true,
            schema = @Schema(pattern = "[0-9]{2}", type = "string")) @PathParam("code") String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header) {

        logger.debug("Received GET request for region {0}", code);

        Region region = new Region(code);
        String csvResult = sparqlUtils.executeSparqlQuery(GeoQueries.getRegion(code));
        region = (Region) csvUtils.populatePOJO(csvResult, region);

        if (region.getUri() == null) {
            return Response.status(Status.NOT_FOUND).entity("").build();
        }
        else {
            return Response.ok(responseUtils.produceResponse(region, header)).build();
        }
    }
}
