package fr.insee.rmes.api.geo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.rmes.api.AbstractMetadataApi;
import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.territoire.Projection;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoires.Projections;
import fr.insee.rmes.queries.geo.CsvGeoUtils;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.DateUtils;

public abstract class AbstractGeoApi extends AbstractMetadataApi {

    protected CsvGeoUtils csvGeoUtils = new CsvGeoUtils();

    private static Logger logger = LogManager.getLogger(AbstractGeoApi.class);

    // Method to find a territoire object
    protected Response generateResponseATerritoireByCode(String csvResult, String header, Territoire territoire) {

        territoire = (Territoire) csvUtils.populatePOJO(csvResult, territoire);
        return this
            .generateStatusResponse(
                ! StringUtils.isEmpty(territoire.getUri()),
                territoire,
                this.getFirstValidHeader(header));
    }

    // Method to find a list of territoires
    protected Response generateResponseListOfTerritoire(
        String csvResult,
        String header,
        Class<?> rootClass,
        Class<? extends Territoire> classObject) {
        List<? extends Territoire> listeTerritoires = csvUtils.populateMultiPOJO(csvResult, classObject);
        return this.generateListStatusResponse(rootClass, listeTerritoires, this.getFirstValidHeader(header));
    }

    protected boolean verifyParametersTypeAndDateAreValid(String typeTerritoire, String date) {
        return (this.verifyParameterTypeTerritoireIsRight(typeTerritoire)) && (this.verifyParameterDateIsRight(date));
    }

    protected boolean verifyParameterTypeTerritoireIsRight(String typeTerritoire) {
        return (typeTerritoire == null)
            || (EnumTypeGeographie
                .streamValuesTypeGeo()
                .anyMatch(s -> s.getTypeObjetGeo().equalsIgnoreCase(typeTerritoire)));
    }

    protected String formatValidParametertypeTerritoireIfIsNull(String typeTerritoire) {
        return (typeTerritoire != null) ? EnumTypeGeographie.getTypeObjetGeoIgnoreCase(typeTerritoire) : Constants.NONE;
    }

    protected boolean verifyParameterDateIsRight(String date, boolean withHistory) {
        return (date == null) || (DateUtils.isValidDate(date)) || (withHistory && date.equals("*"));
    }

    protected boolean verifyParameterDateIsRight(String date) {
        return this.verifyParameterDateIsRight(date, false);
    }

    protected String formatValidParameterDateIfIsNull(String date) {
        return (date != null) ? date : DateUtils.getDateTodayStringFormat();
    }

    protected Response generateStatusResponse(boolean objectIsFound, Object o, String header) {
        if (objectIsFound) {
            return Response.ok(responseUtils.produceResponse(o, header)).build();
        }
        else {
            return Response.status(Status.NOT_FOUND).entity("").build();
        }
    }

    /**
     * @param listObject : for XML, pojo containing a list
     * @param o : for JSON, list of pojo
     * @param header
     * @return
     */
    protected Response generateListStatusResponse(Class<?> listObject, List<?> o, String header) {
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

    protected Response generateBadRequestResponse() {
        return Response.status(Status.BAD_REQUEST).entity("").build();
    }

    // Method to find a list of projections
    protected Response generateResponseListOfProjection(String csvResult, String header) {
        List<Projection> listProj = csvGeoUtils.populateProjections(csvResult);
        return this.generateListStatusResponse(Projections.class, listProj, this.getFirstValidHeader(header));
    }
}
