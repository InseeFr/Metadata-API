package fr.insee.rmes.api.geo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import fr.insee.rmes.modeles.geo.Country;
import fr.insee.rmes.modeles.utils.Header;
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
public class PaysApi extends AbstractGeoApi {

    private static final String CODE_PATTERN = "/{code: " + ConstGeoApi.PATTERN_PAYS + "}";
    private static final String LITTERAL_ID_OPERATION = "getcogpays";
    private static final String LITTERAL_OPERATION_SUMMARY =
            "Informations sur un pays identifié par son code (cinq chiffres)";
    private static final String LITTERAL_RESPONSE_DESCRIPTION = "Pays";

    @Path(ConstGeoApi.PATH_PAYS + CODE_PATTERN)
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(operationId = LITTERAL_ID_OPERATION, summary = LITTERAL_OPERATION_SUMMARY, responses = {
            @ApiResponse(
                    content = @Content(schema = @Schema(implementation = Country.class)),
                    description = LITTERAL_RESPONSE_DESCRIPTION)
    })
    public Response getByCode(
            @Parameter(
                    description = ConstGeoApi.PATTERN_PAYS_DESCRIPTION,
                    required = true,
                    schema = @Schema(
                            pattern = ConstGeoApi.PATTERN_PAYS,
                            type = Constants.TYPE_STRING, example = "99217")) @PathParam(Constants.CODE) String code,
            @Parameter(hidden = true) @HeaderParam(HttpHeaders.ACCEPT) Header header) {
        if (!isValidCode(code)) {
            return Response.status(Status.BAD_REQUEST).entity("Invalid code").build();
        }

        Country country = new Country(code);
        String sanitizedCode = escapeSparql(code);
        String csvResult = sparqlUtils.executeSparqlQuery(GeoQueries.getCountry(sanitizedCode));
        country = (Country) csvUtils.populatePOJO(csvResult, country);

        if (country.getUri() == null) {
            return Response.status(Status.NOT_FOUND).entity("").build();
        } else {
            String acceptHeader = sanitizeAndValidateHeader(header.getString());

            if (acceptHeader == null) {
                return Response.status(Status.BAD_REQUEST).entity("Invalid Accept header").build();
            }
            return Response.ok(responseUtils.produceResponse(country, acceptHeader)).build();
        }
    }
    private boolean isValidCode(String code) {
        return code != null && code.matches(ConstGeoApi.PATTERN_PAYS);
    }

     private String escapeSparql(String input) {
        return input.replace("\"", "\\\"").replace("<", "\\u003C").replace(">", "\\u003E");
    }
    private String sanitizeAndValidateHeader(String header) {
        if (header == null || header.isEmpty()) {
            return null;
        }
        if (header.equals(MediaType.APPLICATION_JSON) || header.equals(MediaType.APPLICATION_XML)) {
            return header;
        }

        return null;
    }
}

