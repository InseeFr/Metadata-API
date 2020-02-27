package fr.insee.rmes.queries.geo;

import java.util.HashMap;
import java.util.Map;

import fr.insee.rmes.config.Configuration;
import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.queries.Queries;
import fr.insee.rmes.utils.Constants;

public class GeoQueries extends Queries {

    private static final String QUERIES_FOLDER = "geographie/";

    /* IDENTIFICATION */
    public static String getCommuneByCodeAndDate(String code, String date) {
        return getTerritoire(code, date, EnumTypeGeographie.COMMUNE);
    }

    public static String getDepartementByCodeAndDate(String code, String date) {
        return getTerritoire(code, date, EnumTypeGeographie.DEPARTEMENT);
    }

    public static String getRegionByCodeAndDate(String code, String date) {
        return getTerritoire(code, date, EnumTypeGeographie.REGION);
    }

    public static String getArrondissementByCodeAndDate(String code, String date) {
        return getTerritoire(code, date, EnumTypeGeographie.ARRONDISSEMENT);
    }

    public static String getCommuneAssocieeByCodeAndDate(String code, String date) {
        return getTerritoire(code, date, EnumTypeGeographie.COMMUNE_ASSOCIEE);
    }

    public static String getCommuneDelegueeByCodeAndDate(String code, String date) {
        return getTerritoire(code, date, EnumTypeGeographie.COMMUNE_DELEGUEE);
    }

    public static String getArrondissementmunicipalByCodeAndDate(String code, String date) {
        return getTerritoire(code, date, EnumTypeGeographie.ARRONDISSEMENT_MUNICIPAL);
    }

    /* LIST */
    public static String getListCommunes(String date) {
        return getTerritoire(Constants.NONE, date, EnumTypeGeographie.COMMUNE);
    }

    public static String getListDept(String date) {
        return getTerritoire(Constants.NONE, date, EnumTypeGeographie.DEPARTEMENT);
    }

    public static String getListRegion(String date) {
        return getTerritoire(Constants.NONE, date, EnumTypeGeographie.REGION);
    }

    public static String getListArrondissements(String date) {
        return getTerritoire(Constants.NONE, date, EnumTypeGeographie.ARRONDISSEMENT);
    }

    public static String getListArrondissementsMunicipaux(String date) {
        return getTerritoire(Constants.NONE, date, EnumTypeGeographie.ARRONDISSEMENT_MUNICIPAL);
    }

    public static String getListCommunesAssociees(String date) {
        return getTerritoire(Constants.NONE, date, EnumTypeGeographie.COMMUNE_ASSOCIEE);
    }

    public static String getListCommunesDeleguees(String date) {
        return getTerritoire(Constants.NONE, date, EnumTypeGeographie.COMMUNE_DELEGUEE);
    }

    /* ASCENDANT */
    public static String getAscendantsCommune(String code, String date, String type) {
        return getAscendantOrDescendantsQuery(code, date, type, EnumTypeGeographie.COMMUNE, true);
    }

    public static String getAscendantsDepartement(String code, String date, String type) {
        return getAscendantOrDescendantsQuery(code, date, type, EnumTypeGeographie.DEPARTEMENT, true);
    }

    public static String getAscendantsCommuneDeleguee(String code, String date, String type) {
        return getAscendantOrDescendantsQuery(code, date, type, EnumTypeGeographie.COMMUNE_DELEGUEE, true);
    }

    public static String getAscendantsCommuneAssociee(String code, String date, String type) {
        return getAscendantOrDescendantsQuery(code, date, type, EnumTypeGeographie.COMMUNE_ASSOCIEE, true);
    }

    public static String getAscendantsArrondissementMunicipal(String code, String date, String type) {
        return getAscendantOrDescendantsQuery(code, date, type, EnumTypeGeographie.ARRONDISSEMENT_MUNICIPAL, true);
    }

    public static String getAscendantsArrondissement(String code, String date, String type) {
        return getAscendantOrDescendantsQuery(code, date, type, EnumTypeGeographie.ARRONDISSEMENT, true);
    }

    /* DESCENDANT */
    public static String getDescendantsCommune(String code, String date, String type) {
        return getAscendantOrDescendantsQuery(code, date, type, EnumTypeGeographie.COMMUNE, false);
    }

    public static String getDescendantsDepartement(String code, String date, String type) {
        return getAscendantOrDescendantsQuery(code, date, type, EnumTypeGeographie.DEPARTEMENT, false);
    }

    public static String getDescendantsRegion(String code, String date, String type) {
        return getAscendantOrDescendantsQuery(code, date, type, EnumTypeGeographie.REGION, false);
    }

    public static String getDescendantsArrondissement(String code, String date, String type) {
        return getAscendantOrDescendantsQuery(code, date, type, EnumTypeGeographie.ARRONDISSEMENT, false);
    }

    private static String getAscendantOrDescendantsQuery(
        String code,
        String date,
        String type,
        EnumTypeGeographie typeOrigine,
        boolean ascendant) {
        Map<String, Object> params = buildCodeAndDateParams(code, date);
        params.put("type", type);
        params.put("typeOrigine", typeOrigine.getTypeObjetGeo());
        params.put("ascendant", String.valueOf(ascendant));
        return buildRequest(QUERIES_FOLDER, "getAscendantsOrDescendantsByCodeTypeDate.ftlh", params);
    }

    // NEXT
    public static String getNextCommune(String code, String date) {
        return getPreviousOrNextQuery(code, date, EnumTypeGeographie.COMMUNE, false);
    }

    public static String getNextDepartement(String code, String date) {
        return getPreviousOrNextQuery(code, date, EnumTypeGeographie.DEPARTEMENT, false);
    }

    public static String getNextRegion(String code, String date) {
        return getPreviousOrNextQuery(code, date, EnumTypeGeographie.REGION, false);
    }

    public static String getNextArrondissement(String code, String date) {
        return getPreviousOrNextQuery(code, date, EnumTypeGeographie.ARRONDISSEMENT, false);
    }

    public static String getNextArrondissementMunicipal(String code, String date) {
        return getPreviousOrNextQuery(code, date, EnumTypeGeographie.ARRONDISSEMENT_MUNICIPAL, false);
    }

    // PREVIOUS
    public static String getPreviousCommune(String code, String date) {
        return getPreviousOrNextQuery(code, date, EnumTypeGeographie.COMMUNE, true);
    }

    public static String getPreviousDepartement(String code, String date) {
        return getPreviousOrNextQuery(code, date, EnumTypeGeographie.DEPARTEMENT, true);
    }

    public static String getPreviousRegion(String code, String date) {
        return getPreviousOrNextQuery(code, date, EnumTypeGeographie.REGION, true);
    }

    public static String getPreviousArrondissement(String code, String date) {
        return getPreviousOrNextQuery(code, date, EnumTypeGeographie.ARRONDISSEMENT, true);
    }

    public static String getPreviousArrondissementMunicipal(String code, String date) {
        return getPreviousOrNextQuery(code, date, EnumTypeGeographie.ARRONDISSEMENT_MUNICIPAL, true);
    }

    private static String getPreviousOrNextQuery(
        String code,
        String date,
        EnumTypeGeographie typeOrigine,
        boolean precedent) {
        Map<String, Object> params = buildCodeAndDateParams(code, date);
        params.put("typeOrigine", typeOrigine.getTypeObjetGeo());
        params.put("previous", String.valueOf(precedent));
        return buildRequest(QUERIES_FOLDER, "getPreviousOrNextByCodeTypeDate.ftlh", params);
    }

    /* UTILS */
    private static String getTerritoire(String code, String date, EnumTypeGeographie typeGeo) {
        Map<String, Object> params = buildCodeAndDateParams(code, date);
        params.put("territoire", typeGeo.getTypeObjetGeo());
        params.put("chefLieu", typeGeo.getChefLieuPredicate());
        return buildRequest(QUERIES_FOLDER, "getTerritoireByCodeAndDate.ftlh", params);
    }

    private static Map<String, Object> buildCodeAndDateParams(String code, String date) {
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("date", date);
        return params;
    }

    public static String getCountry(String code) {
        return "SELECT ?uri ?intitule ?intituleEntier \n"
            + "FROM <http://rdf.insee.fr/graphes/geo/cog> \n"
            + "WHERE { \n"
            + "?uri rdf:type igeo:Etat . \n"
            + "?uri igeo:codeINSEE '"
            + code
            + "' . \n"
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

}
