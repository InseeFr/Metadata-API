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

import fr.insee.rmes.modeles.classification.Poste;
import fr.insee.rmes.modeles.classification.PosteJson;
import fr.insee.rmes.modeles.classification.PosteXml;
import fr.insee.rmes.modeles.classification.Postes;
import fr.insee.rmes.queries.classifications.ClassificationsQueries;
import fr.insee.rmes.utils.CSVUtils;
import fr.insee.rmes.utils.ResponseUtils;
import fr.insee.rmes.utils.SparqlUtils;

@Path("/nomenclature")
public class ClassificationApi {

    private static Logger logger = LogManager.getLogger(ClassificationApi.class);

    @GET
    @Path("/{code}")
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @SuppressWarnings("unchecked")
    public Response getClassificationByCode(
        @PathParam("code") String code,
        @HeaderParam(value = HttpHeaders.ACCEPT) String header) {
        logger.debug("Received GET request for classification " + code);

        String csvResult = SparqlUtils.executeSparqlQuery(ClassificationsQueries.getClassification(code));
        List<Poste> itemsList = (List<Poste>) CSVUtils.populateMultiPOJO(csvResult, Poste.class);

        if (itemsList.size() == 0) {
            return Response.status(Status.NOT_FOUND).entity("").build();
        }
        else if (header.equals(MediaType.APPLICATION_XML)) {
            List<? extends Poste> itemsListXml = (List<PosteXml>) CSVUtils.populateMultiPOJO(csvResult, PosteXml.class);
            return Response.ok(ResponseUtils.produceResponse(new Postes(itemsListXml), header)).build();
        }
        else {
            List<? extends Poste> itemsListJson =
                (List<PosteJson>) CSVUtils.populateMultiPOJO(csvResult, PosteJson.class);
            return Response.ok(ResponseUtils.produceResponse(itemsListJson, header)).build();
        }
    }

    @GET
    @Path("/{code}/tree")
    @Produces({
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    })
    @SuppressWarnings("unchecked")
    public Response getClassificationTreeByCode(
        @PathParam("code") String code,
        @HeaderParam(value = HttpHeaders.ACCEPT) String header) {
        logger.debug("Received GET request for classification tree " + code);

        String csvResult = SparqlUtils.executeSparqlQuery(ClassificationsQueries.getClassification(code));
        List<? extends Poste> itemsList = (List<? extends Poste>) CSVUtils.populateMultiPOJO(csvResult, Poste.class);

        if (itemsList.size() == 0) return Response.status(Status.NOT_FOUND).entity("").build();

        if (header.equals(MediaType.APPLICATION_XML)) {
            List<PosteXml> root = getTree(csvResult, PosteXml.class);
            return Response.ok(ResponseUtils.produceResponse(new Postes(root), header)).build();
        }
        else {
            List<PosteJson> root = getTree(csvResult, PosteJson.class);
            return Response.ok(ResponseUtils.produceResponse(new Postes(root), header)).build();
        }
    }

    @SuppressWarnings("unchecked")
    private <PosteClass> List<PosteClass> getTree(String csvResult, Class<? extends Poste> PosteClass) {
        List<PosteClass> root = new ArrayList<PosteClass>();
        List<PosteClass> liste = (List<PosteClass>) CSVUtils.populateMultiPOJO(csvResult, PosteClass);
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
