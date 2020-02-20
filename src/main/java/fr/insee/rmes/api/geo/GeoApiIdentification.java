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

import fr.insee.rmes.modeles.geo.Country;
import fr.insee.rmes.modeles.geo.territoire.Arrondissement;
import fr.insee.rmes.modeles.geo.territoire.ArrondissementMunicipal;
import fr.insee.rmes.modeles.geo.territoire.Commune;
import fr.insee.rmes.modeles.geo.territoire.CommuneAssociee;
import fr.insee.rmes.modeles.geo.territoire.CommuneDeleguee;
import fr.insee.rmes.modeles.geo.territoire.Departement;
import fr.insee.rmes.modeles.geo.territoire.Region;
import fr.insee.rmes.queries.geo.GeoQueries;
import fr.insee.rmes.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path(ConstGeoApi.PATH_GEO)
@Tag(name = ConstGeoApi.TAG_NAME, description = ConstGeoApi.TAG_DESCRIPTION)
public class GeoApiIdentification extends AbstractGeoApi {

    private static Logger logger = LogManager.getLogger(GeoApiIdentification.class);

    @Path(ConstGeoApi.PATH_COMMUNE + "/{code: " + ConstGeoApi.PATTERN_COMMUNE + "}")
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
            description = ConstGeoApi.PATTERN_COMMUNE_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_COMMUNE,
                type = Constants.TYPE_STRING)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer la commune active à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.FORMAT_DATE) String date) {

        logger.debug("Received GET request for commune {}", code);

        if ( ! this.verifyParameterDateIsRight(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult =
                sparqlUtils
                    .executeSparqlQuery(
                        GeoQueries.getCommuneByCodeAndDate(code, this.formatValidParameterDateIfIsNull(date)));
            Commune commune = (Commune) csvUtils.populatePOJO(csvResult, new Commune(code));
            return this
                .generateStatusResponse(
                    ! StringUtils.isEmpty(commune.getUri()),
                    commune,
                    this.getFirstValidHeader(header));
        }

    }

    @Path(ConstGeoApi.PATH_PAYS + "/{code: " + ConstGeoApi.PATTERN_PAYS + "}")
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
            description = ConstGeoApi.PATTERN_PAYS_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_PAYS,
                type = Constants.TYPE_STRING)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header) {

        logger.debug("Received GET request for country {}", code);

        Country country = new Country(code);
        String csvResult = sparqlUtils.executeSparqlQuery(GeoQueries.getCountry(code));
        country = (Country) csvUtils.populatePOJO(csvResult, country);

        if (country.getUri() == null) {
            return Response.status(Status.NOT_FOUND).entity("").build();
        }
        else {
            return Response.ok(responseUtils.produceResponse(country, this.getFirstValidHeader(header))).build();
        }

    }

    @Path(ConstGeoApi.PATH_REGION + "/{code: " + ConstGeoApi.PATTERN_REGION + "}")
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
            description = ConstGeoApi.PATTERN_REGION_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_REGION,
                type = Constants.TYPE_STRING)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer la region active à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.FORMAT_DATE) String date) {

        logger.debug("Received GET request for region {}", code);

        if ( ! this.verifyParameterDateIsRight(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult =
                sparqlUtils
                    .executeSparqlQuery(
                        GeoQueries.getRegionByCodeAndDate(code, this.formatValidParameterDateIfIsNull(date)));
            Region region = (Region) csvUtils.populatePOJO(csvResult, new Region(code));
            return this
                .generateStatusResponse(
                    ! StringUtils.isEmpty(region.getUri()),
                    region,
                    this.getFirstValidHeader(header));
        }
    }

    @Path(ConstGeoApi.PATH_DEPARTEMENT + "/{code: " + ConstGeoApi.PATTERN_DEPARTEMENT + "}")
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
            description = ConstGeoApi.PATTERN_DEPARTEMENT_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_DEPARTEMENT,
                type = Constants.TYPE_STRING)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer le département actif à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.FORMAT_DATE) String date) {

        logger.debug("Received GET request for departement {}", code);

        if ( ! this.verifyParameterDateIsRight(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult =
                sparqlUtils
                    .executeSparqlQuery(
                        GeoQueries.getDepartementByCodeAndDate(code, this.formatValidParameterDateIfIsNull(date)));
            Departement departement = (Departement) csvUtils.populatePOJO(csvResult, new Departement(code));
            return this
                .generateStatusResponse(
                    ! StringUtils.isEmpty(departement.getUri()),
                    departement,
                    this.getFirstValidHeader(header));
        }
    }

    @Path(ConstGeoApi.PATH_ARRONDISSEMENT + "/{code: " + ConstGeoApi.PATTERN_ARRONDISSEMENT + "}")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getcogarr",
        summary = "Informations sur un arrondissement français identifié par son code (trois chiffres)",
        responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = Arrondissement.class)))
        })
    public Response getArrondissement(
        @Parameter(
            description = ConstGeoApi.PATTERN_ARRONDISSEMENT_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_ARRONDISSEMENT,
                type = Constants.TYPE_STRING)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer l’arrondissement actif à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.FORMAT_DATE) String date) {

        logger.debug("Received GET request for arrondissement {}", code);

        if ( ! this.verifyParameterDateIsRight(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult =
                sparqlUtils
                    .executeSparqlQuery(
                        GeoQueries.getArrondissementByCodeAndDate(code, this.formatValidParameterDateIfIsNull(date)));
            Arrondissement arrondissement = (Arrondissement) csvUtils.populatePOJO(csvResult, new Arrondissement(code));
            return this
                .generateStatusResponse(
                    ! StringUtils.isEmpty(arrondissement.getUri()),
                    arrondissement,
                    this.getFirstValidHeader(header));
        }
    }

    @Path(ConstGeoApi.PATH_COMMUNE_ASSOCIEE + "/{code: " + ConstGeoApi.PATTERN_COMMUNE + "}")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getcogcomass",
        summary = "Informations sur une commune associée française identifiée par son code (cinq caractères)",
        description = "Cette requête renvoie également les communes des collectivités d'Outre-Mer",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(implementation = CommuneAssociee.class)),
                description = "Commune associée")
        })
    public Response getCommuneAssociee(
        @Parameter(
            description = ConstGeoApi.PATTERN_COMMUNE_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_COMMUNE,
                type = Constants.TYPE_STRING)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer la commune active à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.FORMAT_DATE) String date) {

        logger.debug("Received GET request for commune associée {}", code);

        if ( ! this.verifyParameterDateIsRight(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult =
                sparqlUtils
                    .executeSparqlQuery(
                        GeoQueries.getCommuneAssocieeByCodeAndDate(code, this.formatValidParameterDateIfIsNull(date)));
            CommuneAssociee communeAssociee =
                (CommuneAssociee) csvUtils.populatePOJO(csvResult, new CommuneAssociee(code));
            return this
                .generateStatusResponse(
                    ! StringUtils.isEmpty(communeAssociee.getUri()),
                    communeAssociee,
                    this.getFirstValidHeader(header));
        }

    }

    @Path(ConstGeoApi.PATH_COMMUNE_DELEGUEE + "/{code: " + ConstGeoApi.PATTERN_COMMUNE + "}")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getcogcomdel",
        summary = "Informations sur une commune déléguée française identifiée par son code (cinq caractères)",
        description = "Cette requête renvoie également les communes des collectivités d'Outre-Mer",
        responses = {
            @ApiResponse(
                content = @Content(schema = @Schema(implementation = CommuneDeleguee.class)),
                description = "Commune déléguée")
        })
    public Response getCommuneDeleguee(
        @Parameter(
            description = ConstGeoApi.PATTERN_COMMUNE_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_COMMUNE,
                type = Constants.TYPE_STRING)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer la commune déléguée active à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.FORMAT_DATE) String date) {

        logger.debug("Received GET request for commune déléguée {}", code);

        if ( ! this.verifyParameterDateIsRight(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult =
                sparqlUtils
                    .executeSparqlQuery(
                        GeoQueries.getCommuneDelegueeByCodeAndDate(code, this.formatValidParameterDateIfIsNull(date)));
            CommuneDeleguee communeDeleguee =
                (CommuneDeleguee) csvUtils.populatePOJO(csvResult, new CommuneDeleguee(code));
            return this
                .generateStatusResponse(
                    ! StringUtils.isEmpty(communeDeleguee.getUri()),
                    communeDeleguee,
                    this.getFirstValidHeader(header));
        }

    }

    @Path(ConstGeoApi.PATH_ARRONDISSEMENT_MUNICIPAL + "/{code: " + ConstGeoApi.PATTERN_ARRONDISSEMENT_MUNICIPAL + "}")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getcogarrmun",
        summary = "Informations sur un arrondissement municipal français identifié par son code (trois chiffres)",
        responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = ArrondissementMunicipal.class)))
        })
    public Response getArrondissementMunicipal(
        @Parameter(
            description = ConstGeoApi.PATTERN_ARRONDISSEMENT_MUNICIPAL_DESCRIPTION,
            required = true,
            schema = @Schema(
                pattern = ConstGeoApi.PATTERN_ARRONDISSEMENT_MUNICIPAL,
                type = Constants.TYPE_STRING)) @PathParam(Constants.CODE) String code,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header,
        @Parameter(
            description = "Filtre pour renvoyer l’arrondissement municipal actif à la date donnée. Par défaut, c’est la date courante. ",
            required = false,
            schema = @Schema(type = Constants.TYPE_STRING, format = Constants.FORMAT_DATE)) @QueryParam(
                value = Constants.FORMAT_DATE) String date) {

        logger.debug("Received GET request for arrondissement municipal {}", code);

        if ( ! this.verifyParameterDateIsRight(date)) {
            return this.generateBadRequestResponse();
        }
        else {
            String csvResult =
                sparqlUtils
                    .executeSparqlQuery(
                        GeoQueries
                            .getArrondissementmunicipalByCodeAndDate(
                                code,
                                this.formatValidParameterDateIfIsNull(date)));
            ArrondissementMunicipal arrondissementmunicipal =
                (ArrondissementMunicipal) csvUtils.populatePOJO(csvResult, new ArrondissementMunicipal(code));
            return this
                .generateStatusResponse(
                    ! StringUtils.isEmpty(arrondissementmunicipal.getUri()),
                    arrondissementmunicipal,
                    this.getFirstValidHeader(header));
        }
    }

}
