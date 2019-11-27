package fr.insee.rmes.api.classifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.api.MetadataApi;
import fr.insee.rmes.modeles.classification.Poste;
import fr.insee.rmes.modeles.classification.PosteJson;
import fr.insee.rmes.modeles.classification.PosteXml;
import fr.insee.rmes.modeles.classification.Postes;
import fr.insee.rmes.queries.classifications.ClassificationsQueries;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/nomenclature")
@Tag(name = "nomenclatures", description = "Nomenclatures API")
public class ClassificationApi extends MetadataApi {

    private static Logger logger = LogManager.getLogger(ClassificationApi.class);

    @GET
    @Path("/{code}/postes")
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getClassificationByCode",
        summary = "Liste des postes d'une nomenclature (autres que \"catégories juridiques\")",
        responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = Postes.class)))
        })
    public Response getClassificationByCode(
        @Parameter(
            required = true,
            description = "Identifiant de la nomenclature (hors cj)") @PathParam("code") String code,
        @Parameter(hidden = true) @HeaderParam(value = HttpHeaders.ACCEPT) String header) {
        logger.debug("Received GET request for classification " + code);

        String csvResult = sparqlUtils.executeSparqlQuery(ClassificationsQueries.getClassification(code));
        List<Poste> itemsList = (List<Poste>) csvUtils.populateMultiPOJO(csvResult, Poste.class);

        if (itemsList.size() == 0) {
            return Response.status(Status.NOT_FOUND).entity("").build();
        }
        else if (header.equals(MediaType.APPLICATION_XML)) {
            List<? extends Poste> itemsListXml = (List<PosteXml>) csvUtils.populateMultiPOJO(csvResult, PosteXml.class);
            return Response.ok(responseUtils.produceResponse(new Postes(itemsListXml), header)).build();
        }
        else {
            List<? extends Poste> itemsListJson =
                (List<PosteJson>) csvUtils.populateMultiPOJO(csvResult, PosteJson.class);
            return Response.ok(responseUtils.produceResponse(itemsListJson, header)).build();
        }
    }

    @GET
    @Path("/{code}/arborescence")
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @Operation(
        operationId = "getClassificationTreeByCode",
        summary = "Liste des postes d'une nomenclature (autres que \"catégories juridiques\")",
        responses = {
            @ApiResponse(content = @Content(schema = @Schema(implementation = Postes.class)))
        })
    public Response getClassificationTreeByCode(
        @Parameter(
            required = true,
            description = "Identifiant de la nomenclature (hors cj)") @PathParam("code") String code,
        @Parameter(hidden = true) @HeaderParam(value = HttpHeaders.ACCEPT) String header) {
        logger.debug("Received GET request for classification tree " + code);

        String csvResult = sparqlUtils.executeSparqlQuery(ClassificationsQueries.getClassification(code));
        List<? extends Poste> itemsList = (List<? extends Poste>) csvUtils.populateMultiPOJO(csvResult, Poste.class);

        if (itemsList.size() == 0)
            return Response.status(Status.NOT_FOUND).entity("").build();

        if (header.equals(MediaType.APPLICATION_XML)) {
            List<PosteXml> root = getTree(csvResult, PosteXml.class);
            return Response.ok(responseUtils.produceResponse(new Postes(root), header)).build();
        }
        else {
            List<PosteJson> root = getTree(csvResult, PosteJson.class);
            return Response.ok(responseUtils.produceResponse(new Postes(root), header)).build();
        }
    }

    private <PosteClass> List<PosteClass> getTree(String csvResult, Class<PosteClass> PosteClass) {
        List<PosteClass> root = new ArrayList<>();
        List<PosteClass> liste = (List<PosteClass>) csvUtils.populateMultiPOJO(csvResult, PosteClass);
        Map<String, PosteClass> postes =
            liste.stream().collect(Collectors.toMap(p -> ((Poste) p).getCode(), Function.identity()));

        for (PosteClass poste : liste) {
            if (StringUtils.isNotEmpty(((Poste) poste).getCodeParent())) {
                PosteClass posteParent = postes.get(((Poste) poste).getCodeParent());
                ((Poste) posteParent).addPosteEnfant((Poste) poste);
            }
            else
                root.add(poste);
        }
        return root;
    }

}
