package fr.insee.rmes.api.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import fr.insee.rmes.api.AbstractMetadataApi;
import fr.insee.rmes.modeles.operations.CsvFamily;
import fr.insee.rmes.modeles.operations.CsvIndicateur;
import fr.insee.rmes.modeles.operations.CsvSerie;
import fr.insee.rmes.modeles.operations.Famille;
import fr.insee.rmes.modeles.operations.Familles;
import fr.insee.rmes.modeles.operations.Indicateur;
import fr.insee.rmes.modeles.operations.Serie;
import fr.insee.rmes.modeles.operations.documentations.DocumentationSims;
import fr.insee.rmes.queries.operations.OperationsQueries;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@Path("/operations")
@Tag(name = "operations", description = "Operations API")
public class OperationsAPI extends AbstractMetadataApi {

    private OperationsApiService operationsApiService = new OperationsApiService();
    
    
    @Path("/arborescence")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getSeries",
        summary = "Liste des opérations disponibles dans leur arborescence",
        responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = Familles.class)), description="Familles")
        })
    public Response getOperationsTree(
        @Parameter(
            description = "Le diffuseur des données (permet de filtrer les opérations retournées)",
            schema = @Schema(nullable = true, allowableValues = {
                "insee.fr"
            }, type = "string")) @QueryParam("diffuseur") String diffuseur,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header) {

        String csvResult = sparqlUtils.executeSparqlQuery(OperationsQueries.getFamilies());
        List<CsvFamily> familyList = csvUtils.populateMultiPOJO(csvResult, CsvFamily.class);
      
        if (familyList.isEmpty()) {
            return Response.status(Status.NOT_FOUND).entity("").build();
        }
        else {
        	Map<String,List<String>> exclusions = new HashMap<>();
            if (StringUtils.equals(diffuseur, "insee.fr")) {
            	exclusions = operationsApiService.readExclusions();
            }
        	Map<String, Famille> familyMap = operationsApiService.getListeFamilyToOperation(familyList,exclusions);

            if (header.equals(MediaType.APPLICATION_XML)) {
                Familles familles = new Familles(new ArrayList<>(familyMap.values()));
                return Response.ok(responseUtils.produceResponse(familles, header)).build();
            }
            else {
                return Response.ok(responseUtils.produceResponse(familyMap.values(), header)).build();
            }
        }

    }

    
    @Path("/rapportQualite/{id: [0-9]{4}}")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getDocumentation",
        summary = "Documentation d'une opération, d'un indicateur ou d'une série",
        responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = DocumentationSims.class)), description="Documentation SIMS")
        })
    public Response getDocumentation(
        @Parameter(
            description = "Identifiant de la documentation (format : [0-9]{4})",
            required = true,
            schema = @Schema(pattern = "[0-9]{4}", type = "string"),
            example = "1979") @PathParam("id") String id,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header) {
        String csvResult = sparqlUtils.executeSparqlQuery(OperationsQueries.getDocumentationTitle(id));
        DocumentationSims sims = new DocumentationSims();
        sims = (DocumentationSims) csvUtils.populatePOJO(csvResult, sims);

        if (sims.getUri() == null) {
            return Response.status(Status.NOT_FOUND).entity("").build();
        }
        else {
            sims.setRubriques(operationsApiService.getListRubriques(id));
            return Response.ok(responseUtils.produceResponse(sims, header)).build();
        }
    }

    
    @Path("/serie/{idSeries}")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(operationId = "getSeries", summary = "Informations sur une série statistique de l'Insee", responses = {
        @ApiResponse(content = @Content(schema = @Schema(implementation = Serie.class)), description="Séries")
    })
    public Response getSeries(
        @Parameter(
            description = "Identifiant de la série(format : s[0-9]{4})",
            required = true,
            schema = @Schema(pattern = "s[0-9]{4}", type = "string"), 
            example = "s1223") @PathParam("idSeries") String idSeries,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header) {
        String csvResult = sparqlUtils.executeSparqlQuery(OperationsQueries.getSeries(idSeries));
        CsvSerie csvSerie = new CsvSerie();
        csvSerie = (CsvSerie) csvUtils.populatePOJO(csvResult, csvSerie);

        if (csvSerie.getSeriesId() == null) {
            return Response.status(Status.NOT_FOUND).entity("").build();
        }
        else {
            return Response
                .ok(responseUtils.produceResponse(operationsApiService.getSerie(csvSerie, idSeries), header))
                .build();
        }
    }

    
    @Path("/indicateur/{idIndicateur}")
    @GET
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(operationId = "getIndicateur", summary = "Informations sur un indicateur de l'Insee", responses = {
        @ApiResponse(content = @Content(schema = @Schema(implementation = Indicateur.class)), description="Indicateur")
    })
    public Response getIndicateur(
        @Parameter(
            description = "Identifiant de l'indicateur (format : p[0-9]{4})",
            required = true,
            schema = @Schema(pattern = "p[0-9]{4}", type = "string"),
            example ="p1670") @PathParam("idIndicateur") String idIndicateur,
        @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) String header) {
        String csvResult = sparqlUtils.executeSparqlQuery(OperationsQueries.getIndicator(idIndicateur));
        CsvIndicateur csvIndic = new CsvIndicateur();
        csvIndic = (CsvIndicateur) csvUtils.populatePOJO(csvResult, csvIndic);

        if (csvIndic.getId() == null) {
            return Response.status(Status.NOT_FOUND).entity("").build();
        }
        else {
            return Response
                .ok(responseUtils.produceResponse(operationsApiService.getIndicateur(csvIndic, idIndicateur), header))
                .build();
        }
    }

}
