package fr.insee.rmes.api.geo;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.modeles.geo.Arrondissement;
import fr.insee.rmes.modeles.geo.Commune;
import fr.insee.rmes.modeles.geo.Country;
import fr.insee.rmes.modeles.geo.Departement;
import fr.insee.rmes.modeles.geo.Region;
import fr.insee.rmes.queries.geo.GeoQueries;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/geo")
@Tag(name = "geographie", description = "Geographie API")
public class GeoApiIdentification extends GeoAPI {

    private static Logger logger = LogManager.getLogger(GeoApiIdentification.class);

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
            @ApiResponse(content = @Content(schema = @Schema(implementation = Commune.class)), description = "Commune")
        })
    public Response getCommune(
        @Parameter(
            description = "Code de la commune (cinq caractères)",
            required = true,
            schema = @Schema(pattern = "[0-9][0-9AB][0-9]{3}", type = "string")) @PathParam("code") String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer la commune active à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = "string", format = "date")) @QueryParam(value = "date") String date) {

        logger.debug("Received GET request for commune {}", code);

        date = this.formatDate(date);

        if (date == null) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult = sparqlUtils.executeSparqlQuery(GeoQueries.getCommuneByCodeAndDate(code, date));
            Commune commune = (Commune) csvUtils.populatePOJO(csvResult, new Commune(code));
            return this.generateStatusResponse( ! StringUtils.isEmpty(commune.getUri()), commune, header);
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
            @ApiResponse(content = @Content(schema = @Schema(implementation = Country.class)), description = "Pays")
        })
    public Response getCountry(
        @Parameter(
            description = "Code du pays (cinq chiffres, débutant par 99)",
            required = true,
            schema = @Schema(pattern = "99[0-9]{3}", type = "string")) @PathParam("code") String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header) {

        logger.debug("Received GET request for country {}", code);

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
            @ApiResponse(content = @Content(schema = @Schema(implementation = Region.class)), description = "Région")
        })
    public Response getRegion(
        @Parameter(
            description = "Code de la région (deux chiffres)",
            required = true,
            schema = @Schema(pattern = "[0-9]{2}", type = "string")) @PathParam("code") String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer la region active à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = "string", format = "date")) @QueryParam(value = "date") String date) {

        logger.debug("Received GET request for region {}", code);

        date = this.formatDate(date);

        if (date == null) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult = sparqlUtils.executeSparqlQuery(GeoQueries.getRegionByCodeAndDate(code, date));
            Region region = (Region) csvUtils.populatePOJO(csvResult, new Region(code));
            return this.generateStatusResponse( ! StringUtils.isEmpty(region.getUri()), region, header);
        }
    }

    @Path("/departement/{code: ([013-8][0-9])|(2[0-9AB])|(9[0-5])|(97[1-6])}")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getcogdep",
        summary = "Informations sur un département français identifié par son code (deux chiffres)",
        responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = Departement.class)))
        })
    public Response getDepartement(
        @Parameter(
            description = "Code du département (deux ou trois chiffres, ou 2A, 2B)",
            required = true,
            schema = @Schema(
                pattern = "([013-8][0-9])|(2[0-9AB])|(9[0-5])|(97[1-6])",
                type = "string")) @PathParam("code") String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer le département actif à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = "string", format = "date")) @QueryParam(value = "date") String date) {

        logger.debug("Received GET request for departement {}", code);

        date = this.formatDate(date);

        if (date == null) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult = sparqlUtils.executeSparqlQuery(GeoQueries.getDepartementByCodeAndDate(code, date));
            Departement departement = (Departement) csvUtils.populatePOJO(csvResult, new Departement(code));
            return this.generateStatusResponse( ! StringUtils.isEmpty(departement.getUri()), departement, header);
        }
    }

    @Path("/arrondissement/{code: (([013-8][0-9])|(2[0-9AB])|(9[0-5])|(97[1-6]))[0-9]}")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getcogarr",
        summary = "Informations sur un arrondissement français identifié par son code (trois chiffres)",
        responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = Departement.class)))
        })
    public Response getArrondissement(
        @Parameter(
            description = "Code de l'arrondissement (trois ou quatre caractères)",
            required = true,
            schema = @Schema(pattern = "(([013-8][0-9])|(2[0-9AB])|(9[0-5])|(97[1-6]))[0-9]", type = "string")) @PathParam("code") String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer l’arrondissement actif à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = "string", format = "date")) @QueryParam(value = "date") String date) {

        logger.debug("Received GET request for arrondissement {}", code);

        date = this.formatDate(date);

        if (date == null) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult = sparqlUtils.executeSparqlQuery(GeoQueries.getArrondissementByCodeAndDate(code, date));
            Arrondissement arrondissement = (Arrondissement) csvUtils.populatePOJO(csvResult, new Arrondissement(code));
            return this.generateStatusResponse( ! StringUtils.isEmpty(arrondissement.getUri()), arrondissement, header);
        }
    }
}
