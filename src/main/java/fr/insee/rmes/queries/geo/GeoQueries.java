package fr.insee.rmes.queries.geo;

import java.util.HashMap;
import java.util.Map;

import fr.insee.rmes.config.Configuration;
import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.queries.Queries;
import fr.insee.rmes.utils.Constants;

public class GeoQueries extends Queries {

    private static final String CODE = "code";
    private static final String ASCENDANT = "ascendant";
    private static final String TYPE = "type";
    private static final String DATE_PROJECTION = "dateProjection";
    private static final String DATE = "date";
    private static final String TYPE_ORIGINE = "typeOrigine";
    private static final String PREVIOUS = "previous";
    private static final String QUERIES_FOLDER = "geographie/";
    private static final String FILTRE = "filtreNomCommune";

    /* IDENTIFICATION */
    public static String getZoneEmploiByCodeAndDate(String code, String date) {
        return getTerritoire(code, date, EnumTypeGeographie.ZONE_EMPLOI);
    }
    
    public static String getUniteUrbaineByCodeAndDate(String code, String date) {
        return getTerritoire(code, date, EnumTypeGeographie.UNITE_URBAINE);
    }
    
    public static String getAireAttractionByCodeAndDate(String code, String date) {
        return getTerritoire(code, date, EnumTypeGeographie.AIRE_ATTRACTION);
    }
    
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
    public static String getListCommunes(String date,String filtreNomCommune,boolean com) {
        return getTerritoireFiltre(Constants.NONE, date,filtreNomCommune, com, EnumTypeGeographie.COMMUNE);
    }

    public static String getListDepartements(String date) {
        return getTerritoire(Constants.NONE, date, EnumTypeGeographie.DEPARTEMENT);
    }

    public static String getListRegions(String date) {
        return getTerritoire(Constants.NONE, date, EnumTypeGeographie.REGION);
    }

    public static String getListAiresAttraction(String date) {
        return getTerritoire(Constants.NONE, date, EnumTypeGeographie.AIRE_ATTRACTION);
    }
    
    public static String getListUnitesUrbaines(String date) {
        return getTerritoire(Constants.NONE, date, EnumTypeGeographie.UNITE_URBAINE);
    }
    
    public static String getListZonesEmploi(String date) {
        return getTerritoire(Constants.NONE, date, EnumTypeGeographie.ZONE_EMPLOI);
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

    public static String getDescendantsZoneEmploi(String code, String date, String type) {
        return getAscendantOrDescendantsQuery(code, date, type, EnumTypeGeographie.ZONE_EMPLOI, false);
    }
    
    public static String getDescendantsAireAttraction(String code, String date, String type) {
        return getAscendantOrDescendantsQuery(code, date, type, EnumTypeGeographie.AIRE_ATTRACTION, false);
    }
    
    public static String getDescendantsUniteUrbaine(String code, String date, String type) {
        return getAscendantOrDescendantsQuery(code, date, type, EnumTypeGeographie.UNITE_URBAINE, false);
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

    // PROJECTION
    public static String getProjectionCommune(String code, String date, String dateProjection) {
        return getProjectionQuery(code, date, dateProjection, EnumTypeGeographie.COMMUNE);
    }

    public static String getProjectionDepartement(String code, String date, String dateProjection) {
        return getProjectionQuery(code, date, dateProjection, EnumTypeGeographie.DEPARTEMENT);
    }

    public static String getProjectionRegion(String code, String date, String dateProjection) {
        return getProjectionQuery(code, date, dateProjection, EnumTypeGeographie.REGION);
    }

    public static String getProjectionArrondissement(String code, String date, String dateProjection) {
        return getProjectionQuery(code, date, dateProjection, EnumTypeGeographie.ARRONDISSEMENT);
    }

    public static String getProjectionArrondissementMunicipal(String code, String date, String dateProjection) {
        return getProjectionQuery(code, date, dateProjection, EnumTypeGeographie.ARRONDISSEMENT_MUNICIPAL);
    }
    
    // ALL PROJECTIONs
    public static String getAllProjectionCommune(String date, String dateProjection) {
        return getAllProjectionQuery(date, dateProjection, EnumTypeGeographie.COMMUNE);
    }

    public static String getAllProjectionDepartement(String date, String dateProjection) {
        return getAllProjectionQuery(date, dateProjection, EnumTypeGeographie.DEPARTEMENT);
    }

    public static String getAllProjectionRegion(String date, String dateProjection) {
        return getAllProjectionQuery(date, dateProjection, EnumTypeGeographie.REGION);
    }

    public static String getAllProjectionArrondissement(String date, String dateProjection) {
        return getAllProjectionQuery(date, dateProjection, EnumTypeGeographie.ARRONDISSEMENT);
    }

    public static String getAllProjectionArrondissementMunicipal( String date, String dateProjection) {
        return getAllProjectionQuery(date, dateProjection, EnumTypeGeographie.ARRONDISSEMENT_MUNICIPAL);
    }

    /* UTILS */

    private static String getProjectionQuery(
        String code,
        String date,
        String dateProjection,
        EnumTypeGeographie typeOrigine) {
        boolean previous = dateProjection.compareTo(date) <= 0;
        Map<String, Object> params = buildCodeAndDateParams(code, date);
        params.put(TYPE_ORIGINE, typeOrigine.getTypeObjetGeo());
        params.put(PREVIOUS, String.valueOf(previous));
        params.put(DATE_PROJECTION, String.valueOf(dateProjection));
        return buildRequest(QUERIES_FOLDER, "getProjectionByCodeTypeDate.ftlh", params);
    }
    
    private static String getAllProjectionQuery(
        String date,
        String dateProjection,
        EnumTypeGeographie typeOrigine) {
        boolean previous = dateProjection.compareTo(date) <= 0;
        Map<String, Object> params = new HashMap<>();
        params.put(DATE, date);
        params.put(TYPE_ORIGINE, typeOrigine.getTypeObjetGeo());
        params.put(PREVIOUS, String.valueOf(previous));
        params.put(DATE_PROJECTION, String.valueOf(dateProjection));
        return buildRequest(QUERIES_FOLDER, "getAllProjectionByTypeDate.ftlh", params);
    }

    private static String getAscendantOrDescendantsQuery(
        String code,
        String date,
        String type,
        EnumTypeGeographie typeOrigine,
        boolean ascendant) {
        Map<String, Object> params = buildCodeAndDateParams(code, date);
        params.put(TYPE, type);
        params.put(TYPE_ORIGINE, typeOrigine.getTypeObjetGeo());
        params.put(ASCENDANT, String.valueOf(ascendant));
        return buildRequest(QUERIES_FOLDER, "getAscendantsOrDescendantsByCodeTypeDate.ftlh", params);
    }

    private static String getPreviousOrNextQuery(
        String code,
        String date,
        EnumTypeGeographie typeOrigine,
        boolean precedent) {
        Map<String, Object> params = buildCodeAndDateParams(code, date);
        params.put(TYPE_ORIGINE, typeOrigine.getTypeObjetGeo());
        params.put(PREVIOUS, String.valueOf(precedent));
        return buildRequest(QUERIES_FOLDER, "getPreviousOrNextByCodeTypeDate.ftlh", params);
    }

    private static String getTerritoire(String code, String date, EnumTypeGeographie typeGeo) {
        Map<String, Object> params = buildCodeAndDateParams(code, date);
        params.put("territoire", typeGeo.getTypeObjetGeo());
        params.put("chefLieu", typeGeo.getChefLieuPredicate());
        return buildRequest(QUERIES_FOLDER, "getTerritoireByCodeAndDate.ftlh", params);
    }

    private static Map<String, Object> buildCodeAndDateParams(String code, String date) {
        Map<String, Object> params = new HashMap<>();
        params.put(CODE, code);
        params.put(DATE, date);
        return params;
    }
    
    
    private static String getTerritoireFiltre(String code, String date, String filtreNomCommune, boolean com, EnumTypeGeographie typeGeo) {
        Map<String, Object> params = buildCodeAndDateAndFilterParams(code, date, filtreNomCommune);
        params.put("territoire", typeGeo.getTypeObjetGeo());
        params.put("chefLieu", typeGeo.getChefLieuPredicate());
        return buildRequest(QUERIES_FOLDER, "getTerritoireByCodeAndDateAndFiltreNomCommune.ftlh", params);
    } 
    
    private static Map<String, Object> buildCodeAndDateAndFilterParams(String code, String date, String filtreNomCommune) {
        Map<String, Object> params = new HashMap<>();
        params.put(CODE, code);
        params.put(DATE, date);
        params.put(FILTRE, filtreNomCommune);
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
