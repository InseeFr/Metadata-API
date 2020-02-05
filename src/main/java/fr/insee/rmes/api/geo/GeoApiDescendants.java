package fr.insee.rmes.api.geo;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.modeles.geo.Commune;
import fr.insee.rmes.modeles.geo.Departement;
import fr.insee.rmes.modeles.geo.Territoire;
import fr.insee.rmes.modeles.geo.Territoires;
import fr.insee.rmes.queries.geo.GeoQueries;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/geo")
@Tag(name = "geographie", description = "Geographie API")
public class GeoApiDescendants extends GeoAPI {

    private static Logger logger = LogManager.getLogger(GeoApiDescendants.class);

    @Path("/commune/{code: [0-9][0-9AB][0-9]{3}}/descendants")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getcogcomdesc",
        summary = "Récupérer les informations concernant les territoires inclus dans la commune",
        description = "Cette requête renvoie également les communes des collectivités d'Outre-Mer",
        responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = Commune.class)), description = "Commune")
        })
    public Response getDescendantsFromCommune(
        @Parameter(
            description = "Code de la commune (cinq caractères)",
            required = true,
            schema = @Schema(pattern = "[0-9][0-9AB][0-9]{3}", type = "string")) @PathParam("code") String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer la commune active à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = "string", format = "date")) @QueryParam(value = "date") String date,
        @Parameter(
            description = "Filtre sur le type de territoire renvoyé.",
            required = false,
            schema = @Schema(type = "string")) @QueryParam(value = "type") String type) {

        logger.debug("Received GET request for ascendants of commune {}", code);

        date = this.formatDate(date);
        boolean typeExiste = Boolean.TRUE;

        if (type != null) {
            typeExiste = this.verifyTypeExists(type);
        }
        else {
            type = "none";
        }

        if (date == null || ! typeExiste) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult = sparqlUtils.executeSparqlQuery(GeoQueries.getDescendantsCommune(code, date, type));
            List<Territoire> listeTerritoires = csvUtils.populateMultiPOJO(csvResult, Territoire.class);
            return this
                .generateListStatusResponse(Territoires.class, listeTerritoires, this.getFirstValidHeader(header));
        }
    }

    @Path("/departement/{code: ([013-8][0-9])|(2[0-9AB])|(9[0-5])|(97[1-6])}/descendants")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getcogcdepdesc",
        summary = "Récupérer les informations concernant les territoires inclus dans le département",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(implementation = Departement.class)),
                description = "Departement")
        })
    public Response getDescendantsFromDepartement(
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
            schema = @Schema(type = "string", format = "date")) @QueryParam(value = "date") String date,
        @Parameter(
            description = "Filtre sur le type de territoire renvoyé.",
            required = false,
            schema = @Schema(type = "string")) @QueryParam(value = "type") String type) {

        logger.debug("Received GET request for ascendants of departement {}", code);

        date = this.formatDate(date);
        boolean typeExiste = Boolean.TRUE;

        if (type != null) {
            typeExiste = this.verifyTypeExists(type);
        }
        else {
            type = "none";
        }

        if (date == null || ! typeExiste) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult = sparqlUtils.executeSparqlQuery(GeoQueries.getDescendantsDepartement(code, date, type));
            List<Territoire> listeTerritoires = csvUtils.populateMultiPOJO(csvResult, Territoire.class);
            return this
                .generateListStatusResponse(Territoires.class, listeTerritoires, this.getFirstValidHeader(header));
        }
    }
}
