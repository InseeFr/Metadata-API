package fr.insee.rmes.api;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import javax.ws.rs.core.MediaType;

import fr.insee.rmes.utils.CSVUtils;
import fr.insee.rmes.utils.ResponseUtils;
import fr.insee.rmes.utils.SparqlUtils;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Opération réussie"),
    @ApiResponse(responseCode = "400", description = "La syntaxe de la requête est incorrecte"),
    @ApiResponse(responseCode = "401", description = "Une authentification est nécessaire pour accéder à la ressource"),
    @ApiResponse(responseCode = "404", description = "Ressource non trouvée"),
    @ApiResponse(responseCode = "406", description = "L'en-tête HTTP 'Accept' contient une valeur non acceptée"),
    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
})
public abstract class AbstractMetadataApi {

    protected static final String ARRAY = "array";

    protected SparqlUtils sparqlUtils;

    protected CSVUtils csvUtils;

    protected ResponseUtils responseUtils;

    protected AbstractMetadataApi() {

        this.sparqlUtils = new SparqlUtils();
        this.csvUtils = new CSVUtils();
        this.responseUtils = new ResponseUtils();
    }

    /**
     * @param header from the url contains the list of headers accepted
     * @return the first valid header
     */
    protected String getFirstValidHeader(String header) {

        Stream<String> stream = Arrays.stream(header.split(","));
        Optional<String> validHeader =
            stream.filter(s -> s.equals(MediaType.APPLICATION_JSON) || s.equals(MediaType.APPLICATION_XML)).findFirst();

        if (validHeader.isPresent()) {
            return validHeader.get();
        }
        else {
            return header;
        }
    }

}
