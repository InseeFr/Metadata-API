package fr.insee.rmes.api.geo;

public class ConstGeoApi {

    public static final String PATH_SEPARATOR = "/";

    public static final String PATH_GEO = PATH_SEPARATOR + "geo";
    public static final String TAG_NAME = "geographie";
    public static final String TAG_DESCRIPTION = "Geographie API";

    public static final String PATH_CIRCO_TER = PATH_SEPARATOR + "circonscriptionTerritoriale";
    public static final String PATH_CANTON = PATH_SEPARATOR + "canton";
    public static final String PATH_IRIS=PATH_SEPARATOR + "iris";
    public static final String PATH_COMMUNE = PATH_SEPARATOR + "commune";
    public static final String PATH_CANTON_OU_VILLE = PATH_SEPARATOR + "cantonOuVille";
    public static final String PATH_PAYS = PATH_SEPARATOR + "pays";
    public static final String PATH_REGION = PATH_SEPARATOR + "region";
    public static final String PATH_DEPARTEMENT = PATH_SEPARATOR + "departement";
    public static final String PATH_ZONE_EMPLOI = PATH_SEPARATOR + "zoneDEmploi2020";
    public static final String PATH_AIRE_ATTRACTION = PATH_SEPARATOR + "aireDAttractionDesVilles2020";
    public static final String PATH_UNITE_URBAINE = PATH_SEPARATOR + "uniteUrbaine2020";
    public static final String PATH_ARRONDISSEMENT = PATH_SEPARATOR + "arrondissement";
    public static final String PATH_COMMUNE_ASSOCIEE = PATH_SEPARATOR + "communeAssociee";
    public static final String PATH_COMMUNE_DELEGUEE = PATH_SEPARATOR + "communeDeleguee";
    public static final String PATH_ARRONDISSEMENT_MUNICIPAL = PATH_SEPARATOR + "arrondissementMunicipal";
    public static final String PATH_COM= PATH_SEPARATOR + "collectiviteDOutreMer";
    public static final String PATH_DISTRICT= PATH_SEPARATOR + "district";
    public static final String PATH_LISTE_COM= PATH_SEPARATOR +"collectivitesDOutreMer";
    public static final String PATH_LISTE_CANTON_OU_VILLE= PATH_SEPARATOR +"cantonsEtVilles";
    public static final String PATH_INTERCO= PATH_SEPARATOR + "intercommunalite";
    public static final String PATH_BASSINDEVIE= PATH_SEPARATOR + "bassinDeVie2022";
    public static final String PATH_LISTE_IRIS = PATH_SEPARATOR + "iris";
    public static final String PATH_LISTE_COMMUNE = PATH_SEPARATOR + "communes";
    public static final String PATH_LISTE_PAYS = PATH_SEPARATOR + "payss";
    public static final String PATH_LISTE_CANTON = PATH_SEPARATOR + "cantons";
    public static final String PATH_LISTE_REGION = PATH_SEPARATOR + "regions";
    public static final String PATH_LISTE_DEPARTEMENT = PATH_SEPARATOR + "departements";
    public static final String PATH_LISTE_ARRONDISSEMENT = PATH_SEPARATOR + "arrondissements";
    public static final String PATH_LISTE_COMMUNE_ASSOCIEE = PATH_SEPARATOR + "communesAssociees";
    public static final String PATH_LISTE_COMMUNE_DELEGUEE = PATH_SEPARATOR + "communesDeleguees";
    public static final String PATH_LISTE_ARRONDISSEMENT_MUNICIPAL = PATH_SEPARATOR + "arrondissementsMunicipaux";
    public static final String PATH_LISTE_ZONE_EMPLOI = PATH_SEPARATOR + "zonesDEmploi2020";
    public static final String PATH_LISTE_AIRE_ATTRACTION = PATH_SEPARATOR + "airesDAttractionDesVilles2020";
    public static final String PATH_LISTE_UNITE_URBAINE = PATH_SEPARATOR + "unitesUrbaines2020";
    public static final String PATH_LISTE_DISTRICT = PATH_SEPARATOR + "districts";
    public static final String PATH_LISTE_INTERCO = PATH_SEPARATOR + "intercommunalites";
    public static final String PATH_LISTE_BASSINDEVIE= PATH_SEPARATOR + "bassinsDeVie2022";
    
    public static final String PATH_ASCENDANT = PATH_SEPARATOR + "ascendants";
    public static final String PATH_DESCENDANT = PATH_SEPARATOR + "descendants";
    public static final String PATH_SUIVANT = PATH_SEPARATOR + "suivants";
    public static final String PATH_PRECEDENT = PATH_SEPARATOR + "precedents";
    public static final String PATH_PROJECTION = PATH_SEPARATOR + "projetes";


    public static final String PATTERN_COMMUNE = "[0-9][0-9AB][0-9]{3}";
    public static final String PATTERN_CIRCO_TER = "986[1-3]{2}";
    public static final String PATTERN_CANTON_OU_VILLE = "(([0-9]{2})|(2[0-9AB])|(97[1-6]))([0-9]{2})";
    public static final String PATTERN_COM ="9[78][1-9]";
    public static final String PATTERN_DISTRICT ="9[78][1-9]{3}";
    public static final String PATTERN_INTERCO ="[0-9]{9}";
    public static final String PATTERN_CANTON = "(([0-9]{2})|(2[0-9AB])|(97[1-6]))([0-9]{2})";

    public static final String PATTERN_IRIS="[0-9][0-9AB][0-9]{7}";
    public static final String PATTERN_IRIS_DESCRIPTION= "Code Insee de l’Iris (9 caractères)";
    public static final String PATTERN_PAYS = "99[0-9]{3}";
    public static final String PATTERN_REGION = "[0-9]{2}";
    public static final String PATTERN_ZONE_EMPLOI = "[0-9]{4}";
    public static final String PATTERN_UNITE_URBAINE = "[0-9][a-zA-Z0-9][0-9]{3}";
    public static final String PATTERN_AIRE_ATTRACTION = "[a-zA-Z0-9]{3}";
    public static final String PATTERN_DEPARTEMENT = "([013-8][0-9])|(2[0-9AB])|(9[0-5])|(97[1-6])";
    public static final String PATTERN_ARRONDISSEMENT = "(([013-8][0-9])|(2[0-9AB])|(9[0-5])|(97[1-6]))[0-9]";
    public static final String PATTERN_ARRONDISSEMENT_MUNICIPAL = "";
    public static final String PATTERN_BASSINDEVIE = "[0-9][0-9AB][0-9]{3}";
    public static final String PATTERN_BASSINDEVIE_DESCRIPTION = "Code du bassin de vie (cinq caractères)";
    public static final String PATTERN_CANTON_OU_VILLE_DESCRIPTION = "Code d'un canton-ou-ville' (quatre ou cinq caractères pour les DOM ex: 97602)";
    public static final String PATTERN_CIRCO_TER_DESCRIPTION = "Code de la circonscription territoriale (cinq caractères)";
    public static final String PATTERN_COMMUNE_DESCRIPTION = "Code de la commune (cinq caractères)";
    public static final String PATTERN_COM_DESCRIPTION= "Code de la collectivité d'outre-mer (trois caractères)";
    public static final String PATTERN_COMMUNE_ASSOCIEE_DESCRIPTION = "Code de la commune associée (cinq caractères)";
    public static final String PATTERN_COMMUNE_DELEGUEE_DESCRIPTION = "Code de la commune déléguée (cinq caractères)";
    public static final String PATTERN_DISTRICT_DESCRIPTION = "Code du district (cinq caractères)";
    public static final String PATTERN_INTERCO_DESCRIPTION = "Code de l'intercommunalité (neuf caractères)";
    public static final String PATTERN_ZONE_EMPLOI_DESCRIPTION = "Code de la zone d'emploi (quatre chiffres)";
    public static final String PATTERN_UNITE_URBAINE_DESCRIPTION = "Code de l'unité urbaine (cinq chiffres)";
    public static final String PATTERN_AIRE_ATTRACTION_DESCRIPTION = "Code de l'aire d'attraction (trois chiffres)";
    public static final String PATTERN_PAYS_DESCRIPTION = "Code du pays (cinq chiffres, débutant par 99)";
    public static final String PATTERN_REGION_DESCRIPTION = "Code de la région (deux chiffres)";
    public static final String PATTERN_CANTON_DESCRIPTION = "Code du canton (quatre chiffres pour la Métropole ou cinq chiffres pour les DOM ou 2A/2B plus 2 chiffres pour la Corse)" ;
    public static final String PATTERN_DEPARTEMENT_DESCRIPTION =
        "Code du département (deux ou trois caractères)";
    public static final String PATTERN_ARRONDISSEMENT_DESCRIPTION =
        "Code de l'arrondissement (trois ou quatre caractères)";
    public static final String PATTERN_ARRONDISSEMENT_MUNICIPAL_DESCRIPTION =
        "Code de l'arrondissement municipal (cinq caractères)";

    public static final String ID_OPERATION_ASCENDANTS = "asc";
    public static final String ID_OPERATION_DESCENDANTS = "desc";
    public static final String ID_OPERATION_LISTE = "liste";
    public static final String ID_OPERATION_PRECEDENT = "prec";
    public static final String ID_OPERATION_SUIVANT = "suiv";
    public static final String ID_OPERATION_PROJECTION = "proj";
    public static final String ID_OPERATION_COMMUNES = "communes";
    public static final String ID_OPERATION_PROJECTIONS = "allpro";

    public static final String ERREUR_PATTERN= "Le code saisi ne respecte pas le format attendu";

    private ConstGeoApi() {}

}
