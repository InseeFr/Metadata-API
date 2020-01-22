package fr.insee.rmes.api.geo;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fr.insee.rmes.api.MetadataApi;
import fr.insee.rmes.utils.DateUtils;

public abstract class GeoAPI extends MetadataApi {

    public Response generateStatusResponse(boolean objectIsFound, Object o, String header) {

        if (objectIsFound) {
            return Response.ok(responseUtils.produceResponse(o, header)).build();
        }
        else {
            return Response.status(Status.NOT_FOUND).entity("").build();
        }
    }

    public Response generateBadRequestResponse() {
        return Response.status(Status.BAD_REQUEST).entity("").build();
    }

    public String formatDate(String parameter) {
        if (parameter != null) {
            if (DateUtils.isValidDate(parameter)) {
                return parameter;
            }
            else {
                return null;
            }
        }
        else {
            return DateUtils.getDateTodayStringFormat();
        }
    }
}
