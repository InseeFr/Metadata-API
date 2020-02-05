package fr.insee.rmes.api.geo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.api.MetadataApi;
import fr.insee.rmes.utils.DateUtils;

public abstract class GeoAPI extends MetadataApi {

    private static Logger logger = LogManager.getLogger(GeoAPI.class);

    public Response generateStatusResponse(boolean objectIsFound, Object o, String header) {
        if (objectIsFound) {
            return Response.ok(responseUtils.produceResponse(o, header)).build();
        }
        else {
            return Response.status(Status.NOT_FOUND).entity("").build();
        }
    }

    public Response generateListStatusResponse(Class<?> listObject, List<?> o, String header) {
        if (o.isEmpty()) {
            return Response.status(Status.NOT_FOUND).entity("").build();
        }
        else if (StringUtils.equalsAnyIgnoreCase(header, MediaType.APPLICATION_XML)) {
            Constructor<?> constructor;
            Object sampleObject = null;
            try {
                constructor = listObject.getConstructor(List.class);
                sampleObject = constructor.newInstance(o);
            }
            catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                logger.error(e.getMessage());
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity("").build();
            }
            return Response.ok(responseUtils.produceResponse(sampleObject, header)).build();
        }
        else if (StringUtils.equalsAnyIgnoreCase(header, MediaType.APPLICATION_JSON)) {
            return Response.ok(responseUtils.produceResponse(o, header)).build();
        }
        else {
            return Response.status(Status.NOT_ACCEPTABLE).entity("").build();
        }
    }

    public Response generateBadRequestResponse() {
        return Response.status(Status.BAD_REQUEST).entity("").build();
    }

    /**
     * @param header from the url contains the list of headers accepted
     * @return the first valid header
     */
    public String getFirstValidHeader(String header) {

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

    protected boolean verifyParameterDateIsRight(String date) {
        return (date == null) || (DateUtils.isValidDate(date));
    }

    public String formatValidParameterDateIfIsNull(String date) {
        return (date != null) ? date : DateUtils.getDateTodayStringFormat();
    }

}
