package fr.insee.rmes.queries.operations;

import java.util.HashMap;
import java.util.Map;

import fr.insee.rmes.queries.Queries;
import fr.insee.rmes.utils.Lang;

public class OperationsQueries extends Queries {

    private static final String INDICATOR_BASEURI = "/produits/indicateur/";
    private static final String SERIES_BASEURI = "/operations/serie/";
    private static final String INDICATOR_RDF_MODEL = "insee:StatisticalIndicator";
    private static final String SERIES_RDF_MODEL = "insee:StatisticalOperationSeries";
    private static final String ID_INDIC = "idIndic";
    private static final String ID_SERIES = "idSeries";
    private static final String ID_SIMS = "idSims";
    private static final String QUERIES_FOLDER = "operations/";
    
    public static String getSeries(String idSeries) {
        Map<String,Object> params = new HashMap<>();
        params.put("idSeries", idSeries);
        return buildRequest(QUERIES_FOLDER, "getSeriesByIdQuery.ftlh", params);
    }

    public static String getOperationBySeries(String idSeries) {
        Map<String,Object> params = new HashMap<>();
        params.put("idSeries", idSeries);
        return buildRequest(QUERIES_FOLDER, "getOperationBySeriesQuery.ftlh", params);
    }

    public static String getOperationTree() {
        return buildRequest(QUERIES_FOLDER, "getOperationTreeQuery.ftlh", null);

    }

    public static String getDocumentationTitle(String idSims) {
        Map<String,Object> params = new HashMap<>();
        params.put(ID_SIMS, idSims);
        return buildRequest(QUERIES_FOLDER, "getDocumentationTitleByIdSimsQuery.ftlh", params);
    }
    
    public static String getDocumentationRubrics(String idSims) {
        Map<String,Object> params = new HashMap<>();
        params.put(ID_SIMS, idSims);
        params.put("LG1_CL", Lang.FR.getUri());
        params.put("LG2_CL", Lang.EN.getUri());

        return buildRequest(QUERIES_FOLDER, "getDocumentationRubricsByIdSimsQuery.ftlh", params);
    }

    public static String getDocuments(String idSims, String idRubric, Lang lang) {
        Map<String,Object> params = new HashMap<>();
        params.put(ID_SIMS, idSims);
        params.put("idRubric", idRubric);
        params.put("LANG", lang.getUri());
        return buildRequest(QUERIES_FOLDER, "getDocumentsQueryByIdSimsIdRubric.ftlh", params);
    }

    public static String getIndicBySeries(String idSeries) {
        Map<String,Object> params = new HashMap<>();
        params.put(ID_SERIES, idSeries);
        return buildRequest(QUERIES_FOLDER, "getIndicBySeriesQuery.ftlh", params);
    }

    public static String getSeriesByIndic(String idIndic) {
        Map<String,Object> params = new HashMap<>();
        params.put(ID_INDIC, idIndic);
        return buildRequest(QUERIES_FOLDER, "getSeriesByIndicQuery.ftlh", params);
    }

    public static String getSeeAlsoBySeries(String idSeries) {
        return getLinkDifferentTypeByObject(
            idSeries,
            "rdfs:seeAlso",
            SERIES_RDF_MODEL,
            SERIES_BASEURI);

    }

    public static String getSeeAlsoByIndic(String idIndic) {
        return getLinkDifferentTypeByObject(
            idIndic,
            "rdfs:seeAlso",
            INDICATOR_RDF_MODEL,
            INDICATOR_BASEURI);
    }

    public static String getWasGeneratedByByIndic(String idIndic) {
        return getLinkDifferentTypeByObject(
            idIndic,
            "prov:wasGeneratedBy",
            INDICATOR_RDF_MODEL,
            INDICATOR_BASEURI);

    }

    public static String getIsReplacedByBySeries(String idSeries) {
        return getLinkSameTypeByObject(
            idSeries,
            "dcterms:isReplacedBy",
            SERIES_RDF_MODEL,
            SERIES_BASEURI);
    }

    public static String getReplacesBySeries(String idSeries) {
        return getLinkSameTypeByObject(
            idSeries,
            "dcterms:replaces",
            SERIES_RDF_MODEL,
            SERIES_BASEURI);
    }

    public static String getIsReplacedByByIndic(String idIndic) {
        return getLinkSameTypeByObject(
            idIndic,
            "dcterms:isReplacedBy",
            INDICATOR_RDF_MODEL,
            INDICATOR_BASEURI);
    }

    public static String getReplacesByIndic(String idIndic) {
        return getLinkSameTypeByObject(
            idIndic,
            "dcterms:replaces",
            INDICATOR_RDF_MODEL,
            INDICATOR_BASEURI);
    }

    private static String getLinkSameTypeByObject(
        String idObject,
        String linkPredicate,
        String typeObject,
        String uriObject) {
        Map<String,Object> params = new HashMap<>();
        params.put("idObject", idObject);
        params.put("linkPredicate", linkPredicate);
        params.put("typeObject", typeObject);
        params.put("uriObject", uriObject);
        return buildRequest(QUERIES_FOLDER, "getLinkSameTypeByObjectQuery.ftlh", params);
    }

    private static String getLinkDifferentTypeByObject(
        String idObject,
        String linkPredicate,
        String typeObject,
        String uriObject) {
        Map<String,Object> params = new HashMap<>();
        params.put("idObject", idObject);
        params.put("linkPredicate", linkPredicate);
        params.put("typeObject", typeObject);
        params.put("uriObject", uriObject);
        return buildRequest(QUERIES_FOLDER, "getLinkDifferentTypeByObjectQuery.ftlh", params);
    }

    public static String getPublishersBySeries(String idSeries) {
        Map<String,Object> params = new HashMap<>();
        params.put(ID_SERIES, idSeries);
        return buildRequest(QUERIES_FOLDER, "getPublishersBySeriesQuery.ftlh", params);
    }

    public static String getContributorsBySeries(String idSeries) {
        Map<String,Object> params = new HashMap<>();
        params.put(ID_SERIES, idSeries);
        return buildRequest(QUERIES_FOLDER, "getContributorsBySeriesQuery.ftlh", params);
    }

    public static String getContributorsByIndic(String idIndic) {
        Map<String,Object> params = new HashMap<>();
        params.put(ID_INDIC, idIndic);
        return buildRequest(QUERIES_FOLDER, "getContributorsByIndicQuery.ftlh", params);
    }

    public static String getIndicator(String idIndic) {
        Map<String,Object> params = new HashMap<>();
        params.put(ID_INDIC, idIndic);
        return buildRequest(QUERIES_FOLDER, "getIndicatorByIdQuery.ftlh", params);
    }

	public static String getCreatorsBySeries(String idSeries) {
	    Map<String,Object> params = new HashMap<>();
        params.put(ID_SERIES, idSeries);
        return buildRequest(QUERIES_FOLDER, "getCreatorsBySeriesQuery.ftlh", params);
    }
}
