package fr.insee.rmes.api.geo.integrationtest;

public class ConstantForIntegration {

    public final static String COMMUNE_MOCK_SERVER_RETURN_GET =
        "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
            + "http://id.insee.fr/geo/commune/2217cf41-787a-4d8a-8c61-abd5683c5b8a,01002,5,L'Abergement-de-Varey,Abergement-de-Varey,1943-01-01,,\r\n"
            + "";
    public final static String COMMUNE_EXPECTED_RESPONSE_GET_JSON =
        "{\"code\":\"01002\",\"uri\":\"http://id.insee.fr/geo/commune/2217cf41-787a-4d8a-8c61-abd5683c5b8a\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Abergement-de-Varey\",\"typeArticle\":\"5\",\"intitule\":\"L'Abergement-de-Varey\"}";

    public final static String COMMUNE_EXPECTED_RESPONSE_GET_XML =
        "<Commune code=\"01002\" uri=\"http://id.insee.fr/geo/commune/2217cf41-787a-4d8a-8c61-abd5683c5b8a\">"
            + "<Intitule>L'Abergement-de-Varey</Intitule>"
            + "<Type>Commune</Type>"
            + "<DateCreation>1943-01-01</DateCreation>"
            + "<IntituleSansArticle typeArticle=\"5\">Abergement-de-Varey</IntituleSansArticle>"
            + "</Commune>";

    public static final String COMMUNE_MOCK_SERVER_RETURN_LISTE =
        "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
            + "http://id.insee.fr/geo/commune/0ba291b3-c220-45e2-b2f9-a666789623f8,01001,5,L'Abergement-Clémenciat,Abergement-Clémenciat,1943-01-01,,\r\n"
            + "http://id.insee.fr/geo/commune/2217cf41-787a-4d8a-8c61-abd5683c5b8a,01002,5,L'Abergement-de-Varey,Abergement-de-Varey,1943-01-01,,\r\n"
            + "http://id.insee.fr/geo/commune/00b46592-a710-486c-9a4c-bdf964c90c82,01004,1,Ambérieu-en-Bugey,Ambérieu-en-Bugey,1955-03-31,,\r\n"
            + "http://id.insee.fr/geo/commune/3d68f1b7-513d-4a52-aa9b-ba3b93bd02c4,01005,1,Ambérieux-en-Dombes,Ambérieux-en-Dombes,1943-01-01,,\r\n"
            + "";

    public static final String COMMUNE_EXPECTED_RESPONSE_LISTE_TOP_JSON =
        "["
            + "{\"code\":\"01001\",\"uri\":\"http://id.insee.fr/geo/commune/0ba291b3-c220-45e2-b2f9-a666789623f8\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Abergement-Clémenciat\",\"typeArticle\":\"5\",\"intitule\":\"L'Abergement-Clémenciat\"},"
            + "{\"code\":\"01002\",\"uri\":\"http://id.insee.fr/geo/commune/2217cf41-787a-4d8a-8c61-abd5683c5b8a\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Abergement-de-Varey\",\"typeArticle\":\"5\",\"intitule\":\"L'Abergement-de-Varey\"},"
            + "{\"code\":\"01004\",\"uri\":\"http://id.insee.fr/geo/commune/00b46592-a710-486c-9a4c-bdf964c90c82\",\"type\":\"Commune\",\"dateCreation\":\"1955-03-31\",\"intituleSansArticle\":\"Ambérieu-en-Bugey\",\"typeArticle\":\"1\",\"intitule\":\"Ambérieu-en-Bugey\"},"
            + "{\"code\":\"01005\",\"uri\":\"http://id.insee.fr/geo/commune/3d68f1b7-513d-4a52-aa9b-ba3b93bd02c4\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Ambérieux-en-Dombes\",\"typeArticle\":\"1\",\"intitule\":\"Ambérieux-en-Dombes\"}"
            + "]";

    public static final String COMMUNE_EXPECTED_RESPONSE_LISTE_TOP_XML =
        "<Communes><Commune code=\"01001\" uri=\"http://id.insee.fr/geo/commune/0ba291b3-c220-45e2-b2f9-a666789623f8\"><Intitule>L'Abergement-Clémenciat</Intitule><Type>Commune</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"5\">Abergement-Clémenciat</IntituleSansArticle></Commune><Commune code=\"01002\" uri=\"http://id.insee.fr/geo/commune/2217cf41-787a-4d8a-8c61-abd5683c5b8a\"><Intitule>L'Abergement-de-Varey</Intitule><Type>Commune</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"5\">Abergement-de-Varey</IntituleSansArticle></Commune><Commune code=\"01004\" uri=\"http://id.insee.fr/geo/commune/00b46592-a710-486c-9a4c-bdf964c90c82\"><Intitule>Ambérieu-en-Bugey</Intitule><Type>Commune</Type><DateCreation>1955-03-31</DateCreation><IntituleSansArticle typeArticle=\"1\">Ambérieu-en-Bugey</IntituleSansArticle></Commune><Commune code=\"01005\" uri=\"http://id.insee.fr/geo/commune/3d68f1b7-513d-4a52-aa9b-ba3b93bd02c4\"><Intitule>Ambérieux-en-Dombes</Intitule><Type>Commune</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"1\">Ambérieux-en-Dombes</IntituleSansArticle></Commune></Communes>";

    public static final String COMMUNE_MOCK_SERVER_RETURN_ASCENDANTS =
        "uri,code,type,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
            + "http://id.insee.fr/geo/arrondissement/d4677635-ed01-444c-9dfe-c9e7b70ad515,011,http://rdf.insee.fr/def/geo#Arrondissement,0,Belley,Belley,2017-01-01,,01034\r\n"
            + "http://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc,01,http://rdf.insee.fr/def/geo#Departement,5,Ain,Ain,1943-01-01,,01053\r\n"
            + "http://id.insee.fr/geo/region/6db44a21-5ae3-41b5-b16d-0ab30964f78d,84,http://rdf.insee.fr/def/geo#Region,1,Auvergne-Rhône-Alpes,Auvergne-Rhône-Alpes,2016-01-01,,69123\r\n";

    public static final Object COMMUNE_EXPECTED_RESPONSE_ASCENDANTS_JSON =
        "["
            + "{\"code\":\"011\",\"uri\":\"http://id.insee.fr/geo/arrondissement/d4677635-ed01-444c-9dfe-c9e7b70ad515\",\"type\":\"Arrondissement\",\"dateCreation\":\"2017-01-01\",\"intituleSansArticle\":\"Belley\",\"typeArticle\":\"0\",\"chefLieu\":\"01034\",\"intitule\":\"Belley\"},"
            + "{\"code\":\"01\",\"uri\":\"http://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc\",\"type\":\"Departement\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Ain\",\"typeArticle\":\"5\",\"chefLieu\":\"01053\",\"intitule\":\"Ain\"},"
            + "{\"code\":\"84\",\"uri\":\"http://id.insee.fr/geo/region/6db44a21-5ae3-41b5-b16d-0ab30964f78d\",\"type\":\"Region\",\"dateCreation\":\"2016-01-01\",\"intituleSansArticle\":\"Auvergne-Rhône-Alpes\",\"typeArticle\":\"1\",\"chefLieu\":\"69123\",\"intitule\":\"Auvergne-Rhône-Alpes\"}"
            + "]";

    public static final Object COMMUNE_EXPECTED_RESPONSE_ASCENDANTS_XML =
        "<Territoires>"
            + "<Arrondissement code=\"011\" uri=\"http://id.insee.fr/geo/arrondissement/d4677635-ed01-444c-9dfe-c9e7b70ad515\"><Intitule>Belley</Intitule><Type>Arrondissement</Type><DateCreation>2017-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Belley</IntituleSansArticle><ChefLieu>01034</ChefLieu></Arrondissement>"
            + "<Departement code=\"01\" uri=\"http://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc\"><Intitule>Ain</Intitule><Type>Departement</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"5\">Ain</IntituleSansArticle><ChefLieu>01053</ChefLieu></Departement>"
            + "<Region code=\"84\" uri=\"http://id.insee.fr/geo/region/6db44a21-5ae3-41b5-b16d-0ab30964f78d\"><Intitule>Auvergne-Rhône-Alpes</Intitule><Type>Region</Type><DateCreation>2016-01-01</DateCreation><IntituleSansArticle typeArticle=\"1\">Auvergne-Rhône-Alpes</IntituleSansArticle><ChefLieu>69123</ChefLieu></Region>"
            + "</Territoires>";

    public static final String COMMUNE_MOCK_SERVER_RETURN_DESCENDANTS =
        "uri,code,type,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
            + "http://id.insee.fr/geo/communeDeleguee/641df128-1268-495a-900d-41ab5bbeccff,01015,http://rdf.insee.fr/def/geo#CommuneDeleguee,1,Arbignieu,Arbignieu,2016-01-01,,\r\n"
            + "http://id.insee.fr/geo/communeDeleguee/aa807dbe-2394-4e56-8216-3b1ca361608e,01340,http://rdf.insee.fr/def/geo#CommuneDeleguee,0,Saint-Bois,Saint-Bois,2016-01-01,,\r\n";

    public static final Object COMMUNE_EXPECTED_RESPONSE_DESCENDANTS_JSON =
        "["
            + "{\"code\":\"01015\",\"uri\":\"http://id.insee.fr/geo/communeDeleguee/641df128-1268-495a-900d-41ab5bbeccff\",\"type\":\"CommuneDeleguee\",\"dateCreation\":\"2016-01-01\",\"intituleSansArticle\":\"Arbignieu\",\"typeArticle\":\"1\",\"intitule\":\"Arbignieu\"},"
            + "{\"code\":\"01340\",\"uri\":\"http://id.insee.fr/geo/communeDeleguee/aa807dbe-2394-4e56-8216-3b1ca361608e\",\"type\":\"CommuneDeleguee\",\"dateCreation\":\"2016-01-01\",\"intituleSansArticle\":\"Saint-Bois\",\"typeArticle\":\"0\",\"intitule\":\"Saint-Bois\"}"
            + "]";

    public static final Object COMMUNE_EXPECTED_RESPONSE_DESCENDANTS_XML =
        "<Territoires>"
            + "<CommuneDeleguee code=\"01015\" uri=\"http://id.insee.fr/geo/communeDeleguee/641df128-1268-495a-900d-41ab5bbeccff\"><Intitule>Arbignieu</Intitule><Type>CommuneDeleguee</Type><DateCreation>2016-01-01</DateCreation><IntituleSansArticle typeArticle=\"1\">Arbignieu</IntituleSansArticle></CommuneDeleguee>"
            + "<CommuneDeleguee code=\"01340\" uri=\"http://id.insee.fr/geo/communeDeleguee/aa807dbe-2394-4e56-8216-3b1ca361608e\"><Intitule>Saint-Bois</Intitule><Type>CommuneDeleguee</Type><DateCreation>2016-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Saint-Bois</IntituleSansArticle></CommuneDeleguee>"
            + "</Territoires>";

    public static final String DEPARTEMENT_MOCK_SERVER_RETURN_GET =
        "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
            + "http://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc,01,5,Ain,Ain,1943-01-01,,01053\r\n";

    public static final Object DEPARTEMENT_EXPECTED_RESPONSE_GET_JSON =
        "{\"code\":\"01\",\"uri\":\"http://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc\",\"type\":\"Departement\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Ain\",\"typeArticle\":\"5\",\"chefLieu\":\"01053\",\"intitule\":\"Ain\"}";

    public static final Object DEPARTEMENT_EXPECTED_RESPONSE_GET_XML =
        "<Departement code=\"01\" uri=\"http://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc\"><Intitule>Ain</Intitule><Type>Departement</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"5\">Ain</IntituleSansArticle><ChefLieu>01053</ChefLieu></Departement>";

    public static final String DEPARTEMENT_MOCK_SERVER_RETURN_LISTE =
        "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
            + "http://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc,01,5,Ain,Ain,1943-01-01,,01053\r\n"
            + "http://id.insee.fr/geo/departement/e0fe7594-a3d1-4c71-91b6-c96ed609cbcc,02,5,Aisne,Aisne,1943-01-01,,02408\r\n"
            + "http://id.insee.fr/geo/departement/626728b8-60a2-4f47-b88e-6d4dbea327f7,03,5,Allier,Allier,1943-01-01,,03190\r\n"
            + "http://id.insee.fr/geo/departement/beefd3c7-fdcd-4a50-bd07-d1f9b45bee2f,04,4,Alpes-de-Haute-Provence,Alpes-de-Haute-Provence,1970-04-13,,04070";

    public static final Object DEPARTEMENT_EXPECTED_RESPONSE_LISTE_TOP_JSON =
        "["
            + "{\"code\":\"01\",\"uri\":\"http://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc\",\"type\":\"Departement\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Ain\",\"typeArticle\":\"5\",\"chefLieu\":\"01053\",\"intitule\":\"Ain\"},"
            + "{\"code\":\"02\",\"uri\":\"http://id.insee.fr/geo/departement/e0fe7594-a3d1-4c71-91b6-c96ed609cbcc\",\"type\":\"Departement\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Aisne\",\"typeArticle\":\"5\",\"chefLieu\":\"02408\",\"intitule\":\"Aisne\"},"
            + "{\"code\":\"03\",\"uri\":\"http://id.insee.fr/geo/departement/626728b8-60a2-4f47-b88e-6d4dbea327f7\",\"type\":\"Departement\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Allier\",\"typeArticle\":\"5\",\"chefLieu\":\"03190\",\"intitule\":\"Allier\"},"
            + "{\"code\":\"04\",\"uri\":\"http://id.insee.fr/geo/departement/beefd3c7-fdcd-4a50-bd07-d1f9b45bee2f\",\"type\":\"Departement\",\"dateCreation\":\"1970-04-13\",\"intituleSansArticle\":\"Alpes-de-Haute-Provence\",\"typeArticle\":\"4\",\"chefLieu\":\"04070\",\"intitule\":\"Alpes-de-Haute-Provence\"}"
            + "]";

    public static final Object DEPARTEMENT_EXPECTED_RESPONSE_LISTE_TOP_XML =
        "<Departements>"
            + "<Departement code=\"01\" uri=\"http://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc\"><Intitule>Ain</Intitule><Type>Departement</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"5\">Ain</IntituleSansArticle><ChefLieu>01053</ChefLieu></Departement>"
            + "<Departement code=\"02\" uri=\"http://id.insee.fr/geo/departement/e0fe7594-a3d1-4c71-91b6-c96ed609cbcc\"><Intitule>Aisne</Intitule><Type>Departement</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"5\">Aisne</IntituleSansArticle><ChefLieu>02408</ChefLieu></Departement>"
            + "<Departement code=\"03\" uri=\"http://id.insee.fr/geo/departement/626728b8-60a2-4f47-b88e-6d4dbea327f7\"><Intitule>Allier</Intitule><Type>Departement</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"5\">Allier</IntituleSansArticle><ChefLieu>03190</ChefLieu></Departement>"
            + "<Departement code=\"04\" uri=\"http://id.insee.fr/geo/departement/beefd3c7-fdcd-4a50-bd07-d1f9b45bee2f\"><Intitule>Alpes-de-Haute-Provence</Intitule><Type>Departement</Type><DateCreation>1970-04-13</DateCreation><IntituleSansArticle typeArticle=\"4\">Alpes-de-Haute-Provence</IntituleSansArticle><ChefLieu>04070</ChefLieu></Departement>"
            + "</Departements>";

    public static final String DEPARTEMENT_MOCK_SERVER_RETURN_ASCENDANTS =
        "uri,code,type,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
            + "http://id.insee.fr/geo/region/6db44a21-5ae3-41b5-b16d-0ab30964f78d,84,http://rdf.insee.fr/def/geo#Region,1,Auvergne-Rhône-Alpes,Auvergne-Rhône-Alpes,2016-01-01,,69123\r\n";

    public static final Object DEPARTEMENT_EXPECTED_RESPONSE_ASCENDANTS_JSON =
        "["
            + "{\"code\":\"84\",\"uri\":\"http://id.insee.fr/geo/region/6db44a21-5ae3-41b5-b16d-0ab30964f78d\",\"type\":\"Region\",\"dateCreation\":\"2016-01-01\",\"intituleSansArticle\":\"Auvergne-Rhône-Alpes\",\"typeArticle\":\"1\",\"chefLieu\":\"69123\",\"intitule\":\"Auvergne-Rhône-Alpes\"}"
            + "]";

    public static final Object DEPARTEMENT_EXPECTED_RESPONSE_ASCENDANTS_XML =
        "<Territoires>"
            + "<Region code=\"84\" uri=\"http://id.insee.fr/geo/region/6db44a21-5ae3-41b5-b16d-0ab30964f78d\"><Intitule>Auvergne-Rhône-Alpes</Intitule><Type>Region</Type><DateCreation>2016-01-01</DateCreation><IntituleSansArticle typeArticle=\"1\">Auvergne-Rhône-Alpes</IntituleSansArticle><ChefLieu>69123</ChefLieu></Region>"
            + "</Territoires>";

    public static final String DEPARTEMENT_MOCK_SERVER_RETURN_DESCENDANTS =
        "uri,code,type,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
            + "http://id.insee.fr/geo/arrondissement/d4677635-ed01-444c-9dfe-c9e7b70ad515,011,http://rdf.insee.fr/def/geo#Arrondissement,0,Belley,Belley,2017-01-01,,01034\r\n"
            + "http://id.insee.fr/geo/arrondissement/bafd343b-c49a-4c79-a79a-349415d23127,012,http://rdf.insee.fr/def/geo#Arrondissement,0,Bourg-en-Bresse,Bourg-en-Bresse,2017-01-01,,01053\r\n"
            + "http://id.insee.fr/geo/arrondissement/a2196f60-5d0b-4fa6-8dfd-d5c30936e461,013,http://rdf.insee.fr/def/geo#Arrondissement,0,Gex,Gex,2017-01-01,,01173\r\n"
            + "http://id.insee.fr/geo/arrondissement/be7259d5-f1af-4cd4-8334-125e2d991946,014,http://rdf.insee.fr/def/geo#Arrondissement,0,Nantua,Nantua,2017-01-01,,01269\r\n"
            + "http://id.insee.fr/geo/commune/0ba291b3-c220-45e2-b2f9-a666789623f8,01001,http://rdf.insee.fr/def/geo#Commune,5,L'Abergement-Clémenciat,Abergement-Clémenciat,1943-01-01,,";

    public static final Object DEPARTEMENT_EXPECTED_RESPONSE_DESCENDANTS_JSON =
        "["
            + "{\"code\":\"011\",\"uri\":\"http://id.insee.fr/geo/arrondissement/d4677635-ed01-444c-9dfe-c9e7b70ad515\",\"type\":\"Arrondissement\",\"dateCreation\":\"2017-01-01\",\"intituleSansArticle\":\"Belley\",\"typeArticle\":\"0\",\"chefLieu\":\"01034\",\"intitule\":\"Belley\"},"
            + "{\"code\":\"012\",\"uri\":\"http://id.insee.fr/geo/arrondissement/bafd343b-c49a-4c79-a79a-349415d23127\",\"type\":\"Arrondissement\",\"dateCreation\":\"2017-01-01\",\"intituleSansArticle\":\"Bourg-en-Bresse\",\"typeArticle\":\"0\",\"chefLieu\":\"01053\",\"intitule\":\"Bourg-en-Bresse\"},"
            + "{\"code\":\"013\",\"uri\":\"http://id.insee.fr/geo/arrondissement/a2196f60-5d0b-4fa6-8dfd-d5c30936e461\",\"type\":\"Arrondissement\",\"dateCreation\":\"2017-01-01\",\"intituleSansArticle\":\"Gex\",\"typeArticle\":\"0\",\"chefLieu\":\"01173\",\"intitule\":\"Gex\"},"
            + "{\"code\":\"014\",\"uri\":\"http://id.insee.fr/geo/arrondissement/be7259d5-f1af-4cd4-8334-125e2d991946\",\"type\":\"Arrondissement\",\"dateCreation\":\"2017-01-01\",\"intituleSansArticle\":\"Nantua\",\"typeArticle\":\"0\",\"chefLieu\":\"01269\",\"intitule\":\"Nantua\"},"
            + "{\"code\":\"01001\",\"uri\":\"http://id.insee.fr/geo/commune/0ba291b3-c220-45e2-b2f9-a666789623f8\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Abergement-Clémenciat\",\"typeArticle\":\"5\",\"intitule\":\"L'Abergement-Clémenciat\"}"
            + "]";

    public static final Object DEPARTEMENT_EXPECTED_RESPONSE_DESCENDANTS_XML =
        "<Territoires>"
            + "<Arrondissement code=\"011\" uri=\"http://id.insee.fr/geo/arrondissement/d4677635-ed01-444c-9dfe-c9e7b70ad515\"><Intitule>Belley</Intitule><Type>Arrondissement</Type><DateCreation>2017-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Belley</IntituleSansArticle><ChefLieu>01034</ChefLieu></Arrondissement>"
            + "<Arrondissement code=\"012\" uri=\"http://id.insee.fr/geo/arrondissement/bafd343b-c49a-4c79-a79a-349415d23127\"><Intitule>Bourg-en-Bresse</Intitule><Type>Arrondissement</Type><DateCreation>2017-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Bourg-en-Bresse</IntituleSansArticle><ChefLieu>01053</ChefLieu></Arrondissement>"
            + "<Arrondissement code=\"013\" uri=\"http://id.insee.fr/geo/arrondissement/a2196f60-5d0b-4fa6-8dfd-d5c30936e461\"><Intitule>Gex</Intitule><Type>Arrondissement</Type><DateCreation>2017-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Gex</IntituleSansArticle><ChefLieu>01173</ChefLieu></Arrondissement>"
            + "<Arrondissement code=\"014\" uri=\"http://id.insee.fr/geo/arrondissement/be7259d5-f1af-4cd4-8334-125e2d991946\"><Intitule>Nantua</Intitule><Type>Arrondissement</Type><DateCreation>2017-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Nantua</IntituleSansArticle><ChefLieu>01269</ChefLieu></Arrondissement>"
            + "<Commune code=\"01001\" uri=\"http://id.insee.fr/geo/commune/0ba291b3-c220-45e2-b2f9-a666789623f8\"><Intitule>L'Abergement-Clémenciat</Intitule><Type>Commune</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"5\">Abergement-Clémenciat</IntituleSansArticle></Commune>"
            + "</Territoires>";

    public static final String ARRONDISSEMENT_MOCK_SERVER_RETURN_GET =
        "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
            + "http://id.insee.fr/geo/arrondissement/e6e49119-fbb8-4279-a78c-e3ddfeaf36ad,213,0,Montbard,Montbard,1993-01-01,,21425";

    public static final Object ARRONDISSEMENT_EXPECTED_RESPONSE_GET_JSON =
        "{\"code\":\"213\",\"uri\":\"http://id.insee.fr/geo/arrondissement/e6e49119-fbb8-4279-a78c-e3ddfeaf36ad\",\"type\":\"Arrondissement\",\"dateCreation\":\"1993-01-01\",\"intituleSansArticle\":\"Montbard\",\"typeArticle\":\"0\",\"chefLieu\":\"21425\",\"intitule\":\"Montbard\"}";;
    public static final Object ARRONDISSEMENT_EXPECTED_RESPONSE_GET_XML =
        "<Arrondissement code=\"213\" uri=\"http://id.insee.fr/geo/arrondissement/e6e49119-fbb8-4279-a78c-e3ddfeaf36ad\"><Intitule>Montbard</Intitule><Type>Arrondissement</Type><DateCreation>1993-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Montbard</IntituleSansArticle><ChefLieu>21425</ChefLieu></Arrondissement>";

    public static final String ARRONDISSEMENT_MOCK_SERVER_RETURN_LISTE =
        "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
            + "http://id.insee.fr/geo/arrondissement/d4677635-ed01-444c-9dfe-c9e7b70ad515,011,0,Belley,Belley,2017-01-01,,01034\r\n"
            + "http://id.insee.fr/geo/arrondissement/bafd343b-c49a-4c79-a79a-349415d23127,012,0,Bourg-en-Bresse,Bourg-en-Bresse,2017-01-01,,01053\r\n"
            + "http://id.insee.fr/geo/arrondissement/a2196f60-5d0b-4fa6-8dfd-d5c30936e461,013,0,Gex,Gex,2017-01-01,,01173\r\n"
            + "http://id.insee.fr/geo/arrondissement/be7259d5-f1af-4cd4-8334-125e2d991946,014,0,Nantua,Nantua,2017-01-01,,01269";

    public static final Object ARRONDISSEMENT_EXPECTED_RESPONSE_LISTE_TOP_JSON =
        "["
            + "{\"code\":\"011\",\"uri\":\"http://id.insee.fr/geo/arrondissement/d4677635-ed01-444c-9dfe-c9e7b70ad515\",\"type\":\"Arrondissement\",\"dateCreation\":\"2017-01-01\",\"intituleSansArticle\":\"Belley\",\"typeArticle\":\"0\",\"chefLieu\":\"01034\",\"intitule\":\"Belley\"},"
            + "{\"code\":\"012\",\"uri\":\"http://id.insee.fr/geo/arrondissement/bafd343b-c49a-4c79-a79a-349415d23127\",\"type\":\"Arrondissement\",\"dateCreation\":\"2017-01-01\",\"intituleSansArticle\":\"Bourg-en-Bresse\",\"typeArticle\":\"0\",\"chefLieu\":\"01053\",\"intitule\":\"Bourg-en-Bresse\"},"
            + "{\"code\":\"013\",\"uri\":\"http://id.insee.fr/geo/arrondissement/a2196f60-5d0b-4fa6-8dfd-d5c30936e461\",\"type\":\"Arrondissement\",\"dateCreation\":\"2017-01-01\",\"intituleSansArticle\":\"Gex\",\"typeArticle\":\"0\",\"chefLieu\":\"01173\",\"intitule\":\"Gex\"},"
            + "{\"code\":\"014\",\"uri\":\"http://id.insee.fr/geo/arrondissement/be7259d5-f1af-4cd4-8334-125e2d991946\",\"type\":\"Arrondissement\",\"dateCreation\":\"2017-01-01\",\"intituleSansArticle\":\"Nantua\",\"typeArticle\":\"0\",\"chefLieu\":\"01269\",\"intitule\":\"Nantua\"}"
            + "]";

    public static final Object ARRONDISSEMENT_EXPECTED_RESPONSE_LISTE_TOP_XML =
        "<Arrondissements>"
            + "<Arrondissement code=\"011\" uri=\"http://id.insee.fr/geo/arrondissement/d4677635-ed01-444c-9dfe-c9e7b70ad515\"><Intitule>Belley</Intitule><Type>Arrondissement</Type><DateCreation>2017-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Belley</IntituleSansArticle><ChefLieu>01034</ChefLieu></Arrondissement>"
            + "<Arrondissement code=\"012\" uri=\"http://id.insee.fr/geo/arrondissement/bafd343b-c49a-4c79-a79a-349415d23127\"><Intitule>Bourg-en-Bresse</Intitule><Type>Arrondissement</Type><DateCreation>2017-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Bourg-en-Bresse</IntituleSansArticle><ChefLieu>01053</ChefLieu></Arrondissement>"
            + "<Arrondissement code=\"013\" uri=\"http://id.insee.fr/geo/arrondissement/a2196f60-5d0b-4fa6-8dfd-d5c30936e461\"><Intitule>Gex</Intitule><Type>Arrondissement</Type><DateCreation>2017-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Gex</IntituleSansArticle><ChefLieu>01173</ChefLieu></Arrondissement>"
            + "<Arrondissement code=\"014\" uri=\"http://id.insee.fr/geo/arrondissement/be7259d5-f1af-4cd4-8334-125e2d991946\"><Intitule>Nantua</Intitule><Type>Arrondissement</Type><DateCreation>2017-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Nantua</IntituleSansArticle><ChefLieu>01269</ChefLieu></Arrondissement>"
            + "</Arrondissements>";;

    public static final String ARRONDISSEMENT_MOCK_SERVER_RETURN_ASCENDANTS =
        "uri,code,type,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
            + "http://id.insee.fr/geo/departement/31647c0a-d1c1-4b1c-a0c9-125164f48106,21,http://rdf.insee.fr/def/geo#Departement,3,Côte-d'Or,Côte-d'Or,1943-01-01,,21231\r\n"
            + "http://id.insee.fr/geo/region/830a70a3-ef73-4fe9-9bf9-ce5eed5b1a54,27,http://rdf.insee.fr/def/geo#Region,0,Bourgogne-Franche-Comté,Bourgogne-Franche-Comté,2016-01-01,,21231\r\n";

    public static final Object ARRONDISSEMENT_EXPECTED_RESPONSE_ASCENDANTS_JSON =
        "["
            + "{\"code\":\"21\",\"uri\":\"http://id.insee.fr/geo/departement/31647c0a-d1c1-4b1c-a0c9-125164f48106\",\"type\":\"Departement\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Côte-d'Or\",\"typeArticle\":\"3\",\"chefLieu\":\"21231\",\"intitule\":\"Côte-d'Or\"},"
            + "{\"code\":\"27\",\"uri\":\"http://id.insee.fr/geo/region/830a70a3-ef73-4fe9-9bf9-ce5eed5b1a54\",\"type\":\"Region\",\"dateCreation\":\"2016-01-01\",\"intituleSansArticle\":\"Bourgogne-Franche-Comté\",\"typeArticle\":\"0\",\"chefLieu\":\"21231\",\"intitule\":\"Bourgogne-Franche-Comté\"}"
            + "]";

    public static final Object ARRONDISSEMENT_EXPECTED_RESPONSE_ASCENDANTS_XML =
        "<Territoires>"
            + "<Departement code=\"21\" uri=\"http://id.insee.fr/geo/departement/31647c0a-d1c1-4b1c-a0c9-125164f48106\"><Intitule>Côte-d'Or</Intitule><Type>Departement</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"3\">Côte-d'Or</IntituleSansArticle><ChefLieu>21231</ChefLieu></Departement>"
            + "<Region code=\"27\" uri=\"http://id.insee.fr/geo/region/830a70a3-ef73-4fe9-9bf9-ce5eed5b1a54\"><Intitule>Bourgogne-Franche-Comté</Intitule><Type>Region</Type><DateCreation>2016-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Bourgogne-Franche-Comté</IntituleSansArticle><ChefLieu>21231</ChefLieu></Region>"
            + "</Territoires>";

    public static final String ARRONDISSEMENT_MOCK_SERVER_RETURN_DESCENDANTS =
        "uri,code,type,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
            + "http://id.insee.fr/geo/commune/8c4b5970-8a92-4ce4-9a42-3c7383236275,21004,http://rdf.insee.fr/def/geo#Commune,1,Aignay-le-Duc,Aignay-le-Duc,1943-01-01,,\r\n"
            + "http://id.insee.fr/geo/commune/1ed68fb0-2c0f-47e5-9fef-b34684c9d7a1,21006,http://rdf.insee.fr/def/geo#Commune,1,Aisey-sur-Seine,Aisey-sur-Seine,1943-01-01,,\r\n"
            + "http://id.insee.fr/geo/commune/56ffb732-7dbb-4f62-b92c-fcee071f4efa,21007,http://rdf.insee.fr/def/geo#Commune,1,Aisy-sous-Thil,Aisy-sous-Thil,1943-01-01,,\r\n"
            + "http://id.insee.fr/geo/commune/533630bf-ea82-453a-ae6b-0962c51c6d3f,21008,http://rdf.insee.fr/def/geo#Commune,1,Alise-Sainte-Reine,Alise-Sainte-Reine,1943-01-01,,";;
    public static final Object ARRONDISSEMENT_EXPECTED_RESPONSE_DESCENDANTS_JSON =
        "["
            + "{\"code\":\"21004\",\"uri\":\"http://id.insee.fr/geo/commune/8c4b5970-8a92-4ce4-9a42-3c7383236275\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Aignay-le-Duc\",\"typeArticle\":\"1\",\"intitule\":\"Aignay-le-Duc\"},"
            + "{\"code\":\"21006\",\"uri\":\"http://id.insee.fr/geo/commune/1ed68fb0-2c0f-47e5-9fef-b34684c9d7a1\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Aisey-sur-Seine\",\"typeArticle\":\"1\",\"intitule\":\"Aisey-sur-Seine\"},"
            + "{\"code\":\"21007\",\"uri\":\"http://id.insee.fr/geo/commune/56ffb732-7dbb-4f62-b92c-fcee071f4efa\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Aisy-sous-Thil\",\"typeArticle\":\"1\",\"intitule\":\"Aisy-sous-Thil\"},"
            + "{\"code\":\"21008\",\"uri\":\"http://id.insee.fr/geo/commune/533630bf-ea82-453a-ae6b-0962c51c6d3f\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Alise-Sainte-Reine\",\"typeArticle\":\"1\",\"intitule\":\"Alise-Sainte-Reine\"}"
            + "]";
    public static final Object ARRONDISSEMENT_EXPECTED_RESPONSE_DESCENDANTS_XML =
        "<Territoires>"
            + "<Commune code=\"21004\" uri=\"http://id.insee.fr/geo/commune/8c4b5970-8a92-4ce4-9a42-3c7383236275\"><Intitule>Aignay-le-Duc</Intitule><Type>Commune</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"1\">Aignay-le-Duc</IntituleSansArticle></Commune>"
            + "<Commune code=\"21006\" uri=\"http://id.insee.fr/geo/commune/1ed68fb0-2c0f-47e5-9fef-b34684c9d7a1\"><Intitule>Aisey-sur-Seine</Intitule><Type>Commune</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"1\">Aisey-sur-Seine</IntituleSansArticle></Commune>"
            + "<Commune code=\"21007\" uri=\"http://id.insee.fr/geo/commune/56ffb732-7dbb-4f62-b92c-fcee071f4efa\"><Intitule>Aisy-sous-Thil</Intitule><Type>Commune</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"1\">Aisy-sous-Thil</IntituleSansArticle></Commune>"
            + "<Commune code=\"21008\" uri=\"http://id.insee.fr/geo/commune/533630bf-ea82-453a-ae6b-0962c51c6d3f\"><Intitule>Alise-Sainte-Reine</Intitule><Type>Commune</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"1\">Alise-Sainte-Reine</IntituleSansArticle></Commune>"
            + "</Territoires>";

    public static final String REGION_MOCK_SERVER_RETURN_GET =
        "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
            + "http://id.insee.fr/geo/region/a464366d-321a-49ae-ba50-2624c4468657,01,3,Guadeloupe,Guadeloupe,2007-07-15,,97105";

    public static final Object REGION_EXPECTED_RESPONSE_GET_JSON =
        "{\"code\":\"01\",\"uri\":\"http://id.insee.fr/geo/region/a464366d-321a-49ae-ba50-2624c4468657\",\"type\":\"Region\",\"dateCreation\":\"2007-07-15\",\"intituleSansArticle\":\"Guadeloupe\",\"typeArticle\":\"3\",\"chefLieu\":\"97105\",\"intitule\":\"Guadeloupe\"}";

    public static final Object REGION_EXPECTED_RESPONSE_GET_XML =
        "<Region code=\"01\" uri=\"http://id.insee.fr/geo/region/a464366d-321a-49ae-ba50-2624c4468657\"><Intitule>Guadeloupe</Intitule><Type>Region</Type><DateCreation>2007-07-15</DateCreation><IntituleSansArticle typeArticle=\"3\">Guadeloupe</IntituleSansArticle><ChefLieu>97105</ChefLieu></Region>";

    public static final String REGION_MOCK_SERVER_RETURN_LISTE =
        "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
            + "http://id.insee.fr/geo/region/a464366d-321a-49ae-ba50-2624c4468657,01,3,Guadeloupe,Guadeloupe,2007-07-15,,97105\r\n"
            + "http://id.insee.fr/geo/region/87fbb303-895c-42b3-b152-0eff1b5995c0,02,3,Martinique,Martinique,1982-03-02,,97209\r\n"
            + "http://id.insee.fr/geo/region/c090fea1-7739-4885-99da-749923130f72,03,3,Guyane,Guyane,1982-03-02,,97302\r\n"
            + "http://id.insee.fr/geo/region/23e22caa-9bf3-4aaf-96ee-0c1fa0e323e5,04,0,La Réunion,La Réunion,1982-03-02,,97411";

    public static final Object REGION_EXPECTED_RESPONSE_LISTE_TOP_JSON =
        "["
            + "{\"code\":\"01\",\"uri\":\"http://id.insee.fr/geo/region/a464366d-321a-49ae-ba50-2624c4468657\",\"type\":\"Region\",\"dateCreation\":\"2007-07-15\",\"intituleSansArticle\":\"Guadeloupe\",\"typeArticle\":\"3\",\"chefLieu\":\"97105\",\"intitule\":\"Guadeloupe\"},"
            + "{\"code\":\"02\",\"uri\":\"http://id.insee.fr/geo/region/87fbb303-895c-42b3-b152-0eff1b5995c0\",\"type\":\"Region\",\"dateCreation\":\"1982-03-02\",\"intituleSansArticle\":\"Martinique\",\"typeArticle\":\"3\",\"chefLieu\":\"97209\",\"intitule\":\"Martinique\"},"
            + "{\"code\":\"03\",\"uri\":\"http://id.insee.fr/geo/region/c090fea1-7739-4885-99da-749923130f72\",\"type\":\"Region\",\"dateCreation\":\"1982-03-02\",\"intituleSansArticle\":\"Guyane\",\"typeArticle\":\"3\",\"chefLieu\":\"97302\",\"intitule\":\"Guyane\"},"
            + "{\"code\":\"04\",\"uri\":\"http://id.insee.fr/geo/region/23e22caa-9bf3-4aaf-96ee-0c1fa0e323e5\",\"type\":\"Region\",\"dateCreation\":\"1982-03-02\",\"intituleSansArticle\":\"La Réunion\",\"typeArticle\":\"0\",\"chefLieu\":\"97411\",\"intitule\":\"La Réunion\"}"
            + "]";

    public static final Object REGION_EXPECTED_RESPONSE_LISTE_TOP_XML =
        "<Regions>"
            + "<Region code=\"01\" uri=\"http://id.insee.fr/geo/region/a464366d-321a-49ae-ba50-2624c4468657\"><Intitule>Guadeloupe</Intitule><Type>Region</Type><DateCreation>2007-07-15</DateCreation><IntituleSansArticle typeArticle=\"3\">Guadeloupe</IntituleSansArticle><ChefLieu>97105</ChefLieu></Region>"
            + "<Region code=\"02\" uri=\"http://id.insee.fr/geo/region/87fbb303-895c-42b3-b152-0eff1b5995c0\"><Intitule>Martinique</Intitule><Type>Region</Type><DateCreation>1982-03-02</DateCreation><IntituleSansArticle typeArticle=\"3\">Martinique</IntituleSansArticle><ChefLieu>97209</ChefLieu></Region>"
            + "<Region code=\"03\" uri=\"http://id.insee.fr/geo/region/c090fea1-7739-4885-99da-749923130f72\"><Intitule>Guyane</Intitule><Type>Region</Type><DateCreation>1982-03-02</DateCreation><IntituleSansArticle typeArticle=\"3\">Guyane</IntituleSansArticle><ChefLieu>97302</ChefLieu></Region>"
            + "<Region code=\"04\" uri=\"http://id.insee.fr/geo/region/23e22caa-9bf3-4aaf-96ee-0c1fa0e323e5\"><Intitule>La Réunion</Intitule><Type>Region</Type><DateCreation>1982-03-02</DateCreation><IntituleSansArticle typeArticle=\"0\">La Réunion</IntituleSansArticle><ChefLieu>97411</ChefLieu></Region>"
            + "</Regions>";

    public static final String REGION_MOCK_SERVER_RETURN_DESCENDANTS =
        "uri,code,type,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
            + "http://id.insee.fr/geo/arrondissement/06c629c0-b1c0-462b-884c-4393e77dd761,9711,http://rdf.insee.fr/def/geo#Arrondissement,0,Basse-Terre,Basse-Terre,1993-01-01,,97105\r\n"
            + "http://id.insee.fr/geo/arrondissement/57449b26-7a1e-4a51-bc6e-6f6aa96ebef7,9712,http://rdf.insee.fr/def/geo#Arrondissement,0,Pointe-à-Pitre,Pointe-à-Pitre,1993-01-01,,97120\r\n"
            + "http://id.insee.fr/geo/commune/cf73afd7-a50e-49f8-9b86-23945b0bdfb0,97101,http://rdf.insee.fr/def/geo#Commune,4,Les Abymes,Abymes,1943-01-01,,\r\n"
            + "http://id.insee.fr/geo/commune/1eb1a160-fc5d-470e-8bd5-ed98f3621805,97102,http://rdf.insee.fr/def/geo#Commune,1,Anse-Bertrand,Anse-Bertrand,1943-01-01,,";

    public static final Object REGION_EXPECTED_RESPONSE_DESCENDANTS_JSON =
        "["
            + "{\"code\":\"9711\",\"uri\":\"http://id.insee.fr/geo/arrondissement/06c629c0-b1c0-462b-884c-4393e77dd761\",\"type\":\"Arrondissement\",\"dateCreation\":\"1993-01-01\",\"intituleSansArticle\":\"Basse-Terre\",\"typeArticle\":\"0\",\"chefLieu\":\"97105\",\"intitule\":\"Basse-Terre\"},"
            + "{\"code\":\"9712\",\"uri\":\"http://id.insee.fr/geo/arrondissement/57449b26-7a1e-4a51-bc6e-6f6aa96ebef7\",\"type\":\"Arrondissement\",\"dateCreation\":\"1993-01-01\",\"intituleSansArticle\":\"Pointe-à-Pitre\",\"typeArticle\":\"0\",\"chefLieu\":\"97120\",\"intitule\":\"Pointe-à-Pitre\"},"
            + "{\"code\":\"97101\",\"uri\":\"http://id.insee.fr/geo/commune/cf73afd7-a50e-49f8-9b86-23945b0bdfb0\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Abymes\",\"typeArticle\":\"4\",\"intitule\":\"Les Abymes\"},"
            + "{\"code\":\"97102\",\"uri\":\"http://id.insee.fr/geo/commune/1eb1a160-fc5d-470e-8bd5-ed98f3621805\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Anse-Bertrand\",\"typeArticle\":\"1\",\"intitule\":\"Anse-Bertrand\"}"
            + "]";

    public static final Object REGION_EXPECTED_RESPONSE_DESCENDANTS_XML =
        "<Territoires>"
            + "<Arrondissement code=\"9711\" uri=\"http://id.insee.fr/geo/arrondissement/06c629c0-b1c0-462b-884c-4393e77dd761\"><Intitule>Basse-Terre</Intitule><Type>Arrondissement</Type><DateCreation>1993-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Basse-Terre</IntituleSansArticle><ChefLieu>97105</ChefLieu></Arrondissement>"
            + "<Arrondissement code=\"9712\" uri=\"http://id.insee.fr/geo/arrondissement/57449b26-7a1e-4a51-bc6e-6f6aa96ebef7\"><Intitule>Pointe-à-Pitre</Intitule><Type>Arrondissement</Type><DateCreation>1993-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Pointe-à-Pitre</IntituleSansArticle><ChefLieu>97120</ChefLieu></Arrondissement>"
            + "<Commune code=\"97101\" uri=\"http://id.insee.fr/geo/commune/cf73afd7-a50e-49f8-9b86-23945b0bdfb0\"><Intitule>Les Abymes</Intitule><Type>Commune</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"4\">Abymes</IntituleSansArticle></Commune>"
            + "<Commune code=\"97102\" uri=\"http://id.insee.fr/geo/commune/1eb1a160-fc5d-470e-8bd5-ed98f3621805\"><Intitule>Anse-Bertrand</Intitule><Type>Commune</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"1\">Anse-Bertrand</IntituleSansArticle></Commune>"
            + "</Territoires>";
    public static final String COMMUNE_ASSOCIEE_MOCK_SERVER_RETURN_GET = null;
    public static final Object COMMUNE_ASSOCIEE_EXPECTED_RESPONSE_GET_JSON = null;
    public static final Object COMMUNE_ASSOCIEE_EXPECTED_RESPONSE_GET_XML = null;
    public static final String COMMUNE_ASSOCIEE_MOCK_SERVER_RETURN_LISTE = null;
    public static final Object COMMUNE_ASSOCIEE_EXPECTED_RESPONSE_LISTE_TOP_XML = null;
    public static final Object COMMUNE_ASSOCIEE_EXPECTED_RESPONSE_LISTE_TOP_JSON = null;
    public static final String COMMUNE_ASSOCIEE_MOCK_SERVER_RETURN_ASCENDANTS = null;
    public static final Object COMMUNE_ASSOCIEE_EXPECTED_RESPONSE_ASCENDANTS_JSON = null;
    public static final Object COMMUNE_ASSOCIEE_EXPECTED_RESPONSE_ASCENDANTS_XML = null;
}
