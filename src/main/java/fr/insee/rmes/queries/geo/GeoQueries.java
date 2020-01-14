package fr.insee.rmes.queries.geo;

import java.util.HashMap;
import java.util.Map;

import fr.insee.rmes.config.Configuration;
import fr.insee.rmes.queries.Queries;

public class GeoQueries extends Queries {

    private static final String QUERIES_FOLDER = "geographie/";

    public static String getCommuneByCodeAndDate(String code, String date) {
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("date", date);
        return buildRequest(QUERIES_FOLDER, "getCommuneByCodeAndDate.ftlh", params);
    }
    
    public static String getDepartementByCodeAndDate(String code, String date) {
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("date", date);
        return buildRequest(QUERIES_FOLDER, "getDeptByCodeAndDate.ftlh", params);
    }
    
    public static String getRegionByCodeAndDate(String code, String date) {
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("date", date);
        return buildRequest(QUERIES_FOLDER, "getRegionByCodeAndDate.ftlh", params);
    }

    @Deprecated
    public static String getCountry(String code) {
        return "SELECT ?uri ?intitule ?intituleEntier \n"
            + "FROM <http://rdf.insee.fr/graphes/geo/cog> \n"
            + "WHERE { \n"
            + "?uri rdf:type igeo:Etat . \n"
            + "?uri igeo:codeINSEE '"
            + code
            + "'^^xsd:token . \n"
            + "?uri igeo:nom ?intitule . \n"
            + "?uri igeo:nomEntier ?intituleEntier . \n"
            // Ensure that is not the dbpedia URI
            + "FILTER (REGEX(STR(?uri), '"
            + Configuration.getBaseHost()
            + "')) \n"
            + "FILTER (lang(?intitule) = 'fr') \n"
            + "FILTER (lang(?intituleEntier) = 'fr') \n"
            + "}";
    }

    @Deprecated
    public static String getRegion(String code) {
        return "SELECT ?uri ?intitule \n"
            + "FROM <http://rdf.insee.fr/graphes/geo/cog> \n"
            + "WHERE { \n"
            + "?uri rdf:type igeo:Region . \n"
            + "?uri igeo:codeINSEE '"
            + code
            + "'^^xsd:token . \n"
            + "?uri igeo:nom ?intitule \n"
            // Ensure that is not the IGN URI
            + "FILTER (REGEX(STR(?uri), '"
            + Configuration.getBaseHost()
            + "')) \n"
            + "FILTER (lang(?intitule) = 'fr') \n"
            + "}";
    }

}
