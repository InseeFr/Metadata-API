package fr.insee.rmes.api.geo;

public class ConstGeoApi {

    public static final String PATH_SEPARATOR = "/";

    public static final String PATH_GEO = PATH_SEPARATOR + "geo";
    public static final String TAG_NAME = "geographie";
    public static final String TAG_DESCRIPTION = "Geographie API";

    public static final String PATH_COMMUNE = PATH_SEPARATOR + "commune";
    public static final String PATH_PAYS = PATH_SEPARATOR + "pays";
    public static final String PATH_REGION = PATH_SEPARATOR + "region";
    public static final String PATH_DEPARTEMENT = PATH_SEPARATOR + "departement";
    public static final String PATH_ARRONDISSEMENT = PATH_SEPARATOR + "arrondissement";
    public static final String PATH_COMMUNE_ASSOCIEE = PATH_SEPARATOR + "communeAssociee";
    public static final String PATH_COMMUNE_DELEGUEE = PATH_SEPARATOR + "communeDeleguee";
    public static final String PATH_ARRONDISSEMENT_MUNICIPAL = PATH_SEPARATOR + "arrondissementMunicipal";

    public static final String PATH_LISTE_COMMUNES = PATH_SEPARATOR + "communes";
    public static final String PATH_LISTE_PAYS = PATH_SEPARATOR + "pays";
    public static final String PATH_LISTE_REGION = PATH_SEPARATOR + "regions";
    public static final String PATH_LISTE_DEPARTEMENT = PATH_SEPARATOR + "departements";
    public static final String PATH_LISTE_ARRONDISSEMENT = PATH_SEPARATOR + "arrondissements";
    public static final String PATH_LISTE_COMMUNE_ASSOCIEE = PATH_SEPARATOR + "communesAssociees";
    public static final String PATH_LISTE_COMMUNE_DELEGUEE = PATH_SEPARATOR + "communesDeleguees";
    public static final String PATH_LISTE_ARRONDISSEMENT_MUNICIPAL = PATH_SEPARATOR + "arrondissementsMunicipaux";

    public static final String PATH_ASCENDANT = PATH_SEPARATOR + "ascendants";
    public static final String PATH_DESCENDANT = PATH_SEPARATOR + "descendants";
    public static final String PATH_SUIVANT = PATH_SEPARATOR + "suivants";
    public static final String PATH_PRECEDENT = PATH_SEPARATOR + "precedents";

    public static final String PATTERN_COMMUNE = "[0-9][0-9AB][0-9]{3}";
    public static final String PATTERN_PAYS = "99[0-9]{3}";
    public static final String PATTERN_REGION = "[0-9]{2}";
    public static final String PATTERN_DEPARTEMENT = "([013-8][0-9])|(2[0-9AB])|(9[0-5])|(97[1-6])";
    public static final String PATTERN_ARRONDISSEMENT = "(([013-8][0-9])|(2[0-9AB])|(9[0-5])|(97[1-6]))[0-9]";
    public static final String PATTERN_ARRONDISSEMENT_MUNICIPAL = "";

    public static final String PATTERN_COMMUNE_DESCRIPTION = "Code de la commune (cinq caractères)";
    public static final String PATTERN_PAYS_DESCRIPTION = "Code du pays (cinq chiffres, débutant par 99)";
    public static final String PATTERN_REGION_DESCRIPTION = "Code de la région (deux chiffres)";
    public static final String PATTERN_DEPARTEMENT_DESCRIPTION =
        "Code du département (deux ou trois chiffres, ou 2A, 2B)";
    public static final String PATTERN_ARRONDISSEMENT_DESCRIPTION =
        "Code de l'arrondissement (trois ou quatre caractères)";
    public static final String PATTERN_ARRONDISSEMENT_MUNICIPAL_DESCRIPTION =
        "Code de l'arrondissement municipal (trois ou quatre caractères)";

    public static final String ID_OPERATION_ASCENDANTS = "asc";
    public static final String ID_OPERATION_DESCENDANTS = "desc";
    public static final String ID_OPERATION_LISTE = "liste";
    public static final String ID_OPERATION_PRECEDENT = "prec";
    public static final String ID_OPERATION_SUIVANT = "suiv";

    private ConstGeoApi() {}

}
