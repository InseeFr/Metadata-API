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

import fr.insee.rmes.modeles.geo.territoire.Arrondissement;
import fr.insee.rmes.modeles.geo.territoire.ArrondissementMunicipal;
import fr.insee.rmes.modeles.geo.territoire.Commune;
import fr.insee.rmes.modeles.geo.territoire.CommuneAssociee;
import fr.insee.rmes.modeles.geo.territoire.CommuneDeleguee;
import fr.insee.rmes.modeles.geo.territoire.Departement;
import fr.insee.rmes.modeles.geo.territoire.Region;
import fr.insee.rmes.modeles.geo.territoires.Communes;
import fr.insee.rmes.modeles.geo.territoires.Departements;
import fr.insee.rmes.modeles.geo.territoires.Regions;
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
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = Commune.class)),
                description = "Communes")
        })
    public Response getListeCommunes(
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer les communes actives à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = "string", format = "date")) @QueryParam(value = "date") String date) {

        logger.debug("Received GET request for all communes");

        if ( ! this.verifyParameterDateIsRight(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult =
                sparqlUtils.executeSparqlQuery(GeoQueries.getListCommunes(this.formatValidParameterDateIfIsNull(date)));
            List<Commune> listeCommune = csvUtils.populateMultiPOJO(csvResult, Commune.class);
            return this.generateListStatusResponse(Communes.class, listeCommune, this.getFirstValidHeader(header));
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
                content = @Content(schema = @Schema(type = ARRAY, implementation = Departement.class)),
                description = "Départements")
        })
    public Response getListeDepartements(
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer les départements actifs à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = "string", format = "date")) @QueryParam(value = "date") String date) {

        logger.debug("Received GET request for all departements");

        if ( ! this.verifyParameterDateIsRight(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult =
                sparqlUtils.executeSparqlQuery(GeoQueries.getListDept(this.formatValidParameterDateIfIsNull(date)));
            List<Departement> listeDepartement = csvUtils.populateMultiPOJO(csvResult, Departement.class);
            return this
                .generateListStatusResponse(Departements.class, listeDepartement, this.getFirstValidHeader(header));
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
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = Region.class)),
                description = "Commune")
        })
    public Response getListeRegions(
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer les régions actives à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = "string", format = "date")) @QueryParam(value = "date") String date) {

        logger.debug("Received GET request for all regions");

        if ( ! this.verifyParameterDateIsRight(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult =
                sparqlUtils.executeSparqlQuery(GeoQueries.getListRegion(this.formatValidParameterDateIfIsNull(date)));
            List<Region> listeRegion = csvUtils.populateMultiPOJO(csvResult, Region.class);
            return this.generateListStatusResponse(Regions.class, listeRegion, this.getFirstValidHeader(header));
        }
    }

    @Path("/arrondissements")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getcoglistearr",
        summary = "La requête renvoie toutes les arrondissements actifs à la date donnée. Par défaut, c’est la date courante.",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = Arrondissement.class)),
                description = "Commune")
        })
    public Response getListeArrondissements(
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer les arrondissements actifs à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = "string", format = "date")) @QueryParam(value = "date") String date) {

        logger.debug("Received GET request for all arrondissements");

        if ( ! this.verifyParameterDateIsRight(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult =
                sparqlUtils
                    .executeSparqlQuery(GeoQueries.getListArrondissements(this.formatValidParameterDateIfIsNull(date)));
            List<Arrondissement> listeArrondissement = csvUtils.populateMultiPOJO(csvResult, Arrondissement.class);
            return this
                .generateListStatusResponse(Regions.class, listeArrondissement, this.getFirstValidHeader(header));
        }
    }

    @Path("/arrondissementsmunicipaux")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getcoglistearrmun",
        summary = "La requête renvoie toutes les arrondissements municipaux actifs à la date donnée. Par défaut, c’est la date courante.",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = ArrondissementMunicipal.class)),
                description = "Commune")
        })
    public Response getListeArrondissementsmunicipaux(
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer les arrondissements municipaux actifs à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = "string", format = "date")) @QueryParam(value = "date") String date) {

        logger.debug("Received GET request for all arrondissements municipaux");

        if ( ! this.verifyParameterDateIsRight(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult =
                sparqlUtils
                    .executeSparqlQuery(
                        GeoQueries.getListArrondissementsMunicipaux(this.formatValidParameterDateIfIsNull(date)));
            List<ArrondissementMunicipal> listeArrondissementsMunicipaux =
                csvUtils.populateMultiPOJO(csvResult, ArrondissementMunicipal.class);
            return this
                .generateListStatusResponse(
                    Regions.class,
                    listeArrondissementsMunicipaux,
                    this.getFirstValidHeader(header));
        }
    }

    @Path("/communesassociees")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getcoglistecomaas",
        summary = "La requête renvoie toutes les communes associées actives à la date donnée. Par défaut, c’est la date courante.",
        description = "Cette requête renvoie également les communes des collectivités d'Outre-Mer",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = CommuneAssociee.class)),
                description = "Communes associées")
        })
    public Response getListeCommunesAssociées(
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer les communes associées actives à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = "string", format = "date")) @QueryParam(value = "date") String date) {

        logger.debug("Received GET request for all communes associées");

        if ( ! this.verifyParameterDateIsRight(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult =
                sparqlUtils
                    .executeSparqlQuery(
                        GeoQueries.getListCommunesAssociees(this.formatValidParameterDateIfIsNull(date)));
            List<CommuneAssociee> listeCommuneAssociées = csvUtils.populateMultiPOJO(csvResult, CommuneAssociee.class);
            return this
                .generateListStatusResponse(Communes.class, listeCommuneAssociées, this.getFirstValidHeader(header));
        }
    }

    @Path("/communesdeleguees")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getcoglistecom",
        summary = "La requête renvoie toutes les communes déléguées actives à la date donnée. Par défaut, c’est la date courante.",
        description = "Cette requête renvoie également les communes des collectivités d'Outre-Mer",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(type = ARRAY, implementation = CommuneDeleguee.class)),
                description = "Communes déléguées")
        })
    public Response getListeCommunesDeleguees(
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer les communes déléguées actives à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = "string", format = "date")) @QueryParam(value = "date") String date) {

        logger.debug("Received GET request for all communes déléguées");

        if ( ! this.verifyParameterDateIsRight(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult =
                sparqlUtils
                    .executeSparqlQuery(
                        GeoQueries.getListCommunesDeleguees(this.formatValidParameterDateIfIsNull(date)));
            List<CommuneDeleguee> listeCommuneDeleguee = csvUtils.populateMultiPOJO(csvResult, CommuneDeleguee.class);
            return this
                .generateListStatusResponse(Communes.class, listeCommuneDeleguee, this.getFirstValidHeader(header));
        }
    }

}
