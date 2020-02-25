package fr.insee.rmes.api.geo.integrationtest;

public class ConstantIntegrationTest {

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
}
