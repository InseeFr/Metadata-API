package fr.insee.rmes.api.geo;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.modeles.geo.Commune;
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
public class GeoApiListe extends GeoAPI {

    private static Logger logger = LogManager.getLogger(GeoApiListe.class);

    @Path("/communes")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getcoglistecom",
        summary = "La requête renvoie toutes les communes actives à la date donnée. Par défaut, c’est la date courante.",
        description = "Cette requête renvoie également les communes des collectivités d'Outre-Mer",
        responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = Commune.class)), description = "Commune")
        })
    public Response getListeCommunes(
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer les communes actives à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = "string", format = "date")) @QueryParam(value = "date") String date) {

        logger.debug("Received GET request for all communes");

        date = this.formatDate(date);

        if (date == null) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult = sparqlUtils.executeSparqlQuery(GeoQueries.getListCommunes(date));
            List<Commune> listeCommune = csvUtils.populateMultiPOJO(csvResult, Commune.class);
            return this.generateStatusResponse( ! listeCommune.isEmpty(), listeCommune, header);
        }
    }

    @Path("/departements")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getcoglistedep",
        summary = "La requête renvoie tous les départements actifs à la date donnée. Par défaut, c’est la date courante. ",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(implementation = Departement.class)),
                description = "Commune")
        })
    public Response getListeDepartements(
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer les départements actifs à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = "string", format = "date")) @QueryParam(value = "date") String date) {

        logger.debug("Received GET request for all departements");

        date = this.formatDate(date);

        if (date == null) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult = sparqlUtils.executeSparqlQuery(GeoQueries.getListDept(date));
            List<Departement> listeDepartement = csvUtils.populateMultiPOJO(csvResult, Departement.class);
            return this.generateStatusResponse( ! listeDepartement.isEmpty(), listeDepartement, header);
        }
    }

    @Path("/regions")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getcoglistere",
        summary = "La requête renvoie toutes les régions actives à la date donnée. Par défaut, c’est la date courante.",
        responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = Region.class)), description = "Commune")
        })
    public Response getListeRegions(
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer les régions actives à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = "string", format = "date")) @QueryParam(value = "date") String date) {

        logger.debug("Received GET request for all regions");

        date = this.formatDate(date);

        if (date == null) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult = sparqlUtils.executeSparqlQuery(GeoQueries.getListRegion(date));
            List<Region> listeRegion = csvUtils.populateMultiPOJO(csvResult, Region.class);
            return this.generateStatusResponse( ! listeRegion.isEmpty(), listeRegion, header);
        }
    }
}
