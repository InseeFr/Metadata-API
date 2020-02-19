package fr.insee.rmes.api.geo;

public class ConstGeoApi {

    public static final String PATH_COMMUNE = "/commune";
    public static final String PATH_PAYS = "/pays";
    public static final String PATH_REGION = "/region";
    public static final String PATH_DEPARTEMENT = "/departement";
    public static final String PATH_ARRONDISSEMENT = "/arrondissement";
    public static final String PATH_COMMUNE_ASSOCIEE = "/communeAssociee";
    public static final String PATH_COMMUNE_DELEGUEE = "/communeDeleguee";
    public static final String PATH_ARRONDISSEMENT_MUNICIPAL = "/arrondissementMunicipal";

    public static final String PATH_LISTE_COMMUNES = "/communes";
    public static final String PATH_LISTE_PAYS = "/pays";
    public static final String PATH_LISTE_REGION = "/region";
    public static final String PATH_LISTE_DEPARTEMENT = "/departement";
    public static final String PATH_LISTE_ARRONDISSEMENT = "/arrondissement";
    public static final String PATH_LISTE_COMMUNE_ASSOCIEE = "/communeAssociee";
    public static final String PATH_LISTE_COMMUNE_DELEGUEE = "/communeDeleguee";
    public static final String PATH_LISTE_ARRONDISSEMENT_MUNICIPAL = "/arrondissementMunicipal";

    public static final String PATH_ASCENDANT = "/ascendant";
    public static final String PATH_DESCENDANT = "/descendant";
    public static final String PATH_SUIVANT = "/suivant";
    public static final String PATH_PRECEDENT = "/precedent";

    public static final String PATTERN_COMMUNE = "[0-9][0-9AB][0-9]{3}";
    public static final String PATTERN_PAYS = "99[0-9]{3}";
    public static final String PATTERN_REGION = "[0-9]{2}";
    public static final String PATTERN_DEPARTEMENT = "([013-8][0-9])|(2[0-9AB])|(9[0-5])|(97[1-6])";
    public static final String PATTERN_ARRONDISSEMENT = "(([013-8][0-9])|(2[0-9AB])|(9[0-5])|(97[1-6]))[0-9]";
    public static final String PATTERN_ARRONDISSEMENT_MUNICIPAL = "";
}
