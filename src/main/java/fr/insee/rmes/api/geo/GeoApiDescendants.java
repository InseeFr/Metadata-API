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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.queries.geo.GeoQueries;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/geo")
@Tag(name = "geographie", description = "Geographie API")
public class GeoApiDescendants extends AbstractGeoAscendantsAnsDescendantsApi {

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
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
                description = "Commune")
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
            schema = @Schema(type = "string")) @QueryParam(value = "type") String typeTerritoire) {

        logger.debug("Received GET request for descendants of commune {}", code);

        if ( ! this.verifyParametersApiAreValid(typeTerritoire, date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseListOfTerritoireForAscendantsOrDescendants(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries
                                .getDescendantsCommune(
                                    code,
                                    this.formatValidParameterDateIfIsNull(date),
                                    this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire))),
                    header);
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
                content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
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
            schema = @Schema(type = "string")) @QueryParam(value = "type") String typeTerritoire) {

        logger.debug("Received GET request for descendants of departement {}", code);

        if ( ! this.verifyParametersApiAreValid(typeTerritoire, date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseListOfTerritoireForAscendantsOrDescendants(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries
                                .getDescendantsDepartement(
                                    code,
                                    this.formatValidParameterDateIfIsNull(date),
                                    this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire))),
                    header);
        }
    }

    @Path("/region/{code: [0-9]{2}}/descendants")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getcogcregdesc",
        summary = "Récupérer les informations concernant les territoires inclus dans la région",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
                description = "Region")
        })
    public Response getDescendantsFromRegion(
        @Parameter(
            description = "Code de la région (deux chiffres)",
            required = true,
            schema = @Schema(pattern = "[0-9]{2}", type = "string")) @PathParam("code") String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer la region active à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = "string", format = "date")) @QueryParam(value = "date") String date,
        @Parameter(
            description = "Filtre sur le type de territoire renvoyé.",
            required = false,
            schema = @Schema(type = "string")) @QueryParam(value = "type") String typeTerritoire) {

        logger.debug("Received GET request for descendants of region {}", code);

        if ( ! this.verifyParametersApiAreValid(typeTerritoire, date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseListOfTerritoireForAscendantsOrDescendants(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries
                                .getDescendantsRegion(
                                    code,
                                    this.formatValidParameterDateIfIsNull(date),
                                    this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire))),
                    header);
        }
    }
    
    @Path("/arrondissement/{code: (([013-8][0-9])|(2[0-9AB])|(9[0-5])|(97[1-6]))[0-9]}/descendants")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getcogarrdesc",
        summary = "Récupérer les informations concernant les territoires inclus dans l'arrondissement",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = Territoire.class)),
                description = "Arrondissement")
        })
    public Response getDescendantsFromArrondissement(
        @Parameter(
            description = "Code de l'arrondissement",
            required = true,
            schema = @Schema(pattern = "(([013-8][0-9])|(2[0-9AB])|(9[0-5])|(97[1-6]))[0-9]", type = "string")) @PathParam("code") String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer le territoire actif à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = "string", format = "date")) @QueryParam(value = "date") String date,
        @Parameter(
            description = "Filtre sur le type de territoire renvoyé.",
            required = false,
            schema = @Schema(type = "string")) @QueryParam(value = "type") String typeTerritoire) {

        logger.debug("Received GET request for descendants of Arrondissement {}", code);

        if ( ! this.verifyParametersApiAreValid(typeTerritoire, date)) {
            return this.generateBadRequestResponse();
        }
        else {
            return this
                .generateResponseListOfTerritoireForAscendantsOrDescendants(
                    sparqlUtils
                        .executeSparqlQuery(
                            GeoQueries
                                .getDescendantsArrondissement(
                                    code,
                                    this.formatValidParameterDateIfIsNull(date),
                                    this.formatValidParametertypeTerritoireIfIsNull(typeTerritoire))),
                    header);
        }
    }
}
