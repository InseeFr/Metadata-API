package fr.insee.rmes.api.geo.pseudointegrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.rmes.modeles.utils.Date;
import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;
import org.xml.sax.SAXException;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.Diff;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ConstantForIntegration {

    public static final ObjectMapper MAPPER = new ObjectMapper();
    public static final String COMMUNES_FROM_CANTON_EXPECTED_RESPONSE_GET_JSON = "[\n" +
            "\t{\n" +
            "\t\t\"code\": \"33273\",\n" +
            "\t\t\"uri\": \"http://id.insee.fr/geo/commune/c1e73781-7e69-4212-9645-a525ad190b22\",\n" +
            "\t\t\"type\": \"Commune\",\n" +
            "\t\t\"intitule\": \"Martignas-sur-Jalle\",\n" +
            "\t\t\"intituleSansArticle\": \"Martignas-sur-Jalle\",\n" +
            "\t\t\"typeArticle\": \"0\",\n" +
            "\t\t\"inclusion\": \"totale\",\n" +
            "\t\t\"dateCreation\": \"1943-01-01\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"code\": \"33281\",\n" +
            "\t\t\"uri\": \"http://id.insee.fr/geo/commune/aa63f8a3-49a2-41e3-a039-36dc639be4a1\",\n" +
            "\t\t\"type\": \"Commune\",\n" +
            "\t\t\"intitule\": \"Mérignac\",\n" +
            "\t\t\"intituleSansArticle\": \"Mérignac\",\n" +
            "\t\t\"typeArticle\": \"0\",\n" +
            "\t\t\"inclusion\": \"partielle\",\n" +
            "\t\t\"dateCreation\": \"1943-01-01\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"code\": \"33422\",\n" +
            "\t\t\"uri\": \"http://id.insee.fr/geo/commune/dbab54d2-ea7e-4117-aaa9-4ea1a7110749\",\n" +
            "\t\t\"type\": \"Commune\",\n" +
            "\t\t\"intitule\": \"Saint-Jean-d'Illac\",\n" +
            "\t\t\"intituleSansArticle\": \"Saint-Jean-d'Illac\",\n" +
            "\t\t\"typeArticle\": \"0\",\n" +
            "\t\t\"inclusion\": \"totale\",\n" +
            "\t\t\"dateCreation\": \"1943-01-01\"\n" +
            "\t}\n" +
            "]";

    public static final String COMMUNES_FROM_CANTON_EXPECTED_RESPONSE_GET_XML = "<Communes>\n" +
            "\t<Commune code=\"33273\" uri=\"http://id.insee.fr/geo/commune/c1e73781-7e69-4212-9645-a525ad190b22\">\n" +
            "\t\t<Intitule>Martignas-sur-Jalle</Intitule>\n" +
            "\t\t<Type>Commune</Type>\n" +
            "\t\t<DateCreation>1943-01-01</DateCreation>\n" +
            "\t\t<IntituleSansArticle typeArticle=\"0\">Martignas-sur-Jalle</IntituleSansArticle>\n" +
            "\t\t<Inclusion>totale</Inclusion>\n" +
            "\t</Commune>\n" +
            "\t<Commune code=\"33281\" uri=\"http://id.insee.fr/geo/commune/aa63f8a3-49a2-41e3-a039-36dc639be4a1\">\n" +
            "\t\t<Intitule>Mérignac</Intitule>\n" +
            "\t\t<Type>Commune</Type>\n" +
            "\t\t<DateCreation>1943-01-01</DateCreation>\n" +
            "\t\t<IntituleSansArticle typeArticle=\"0\">Mérignac</IntituleSansArticle>\n" +
            "\t\t<Inclusion>partielle</Inclusion>\n" +
            "\t</Commune>\n" +
            "\t<Commune code=\"33422\" uri=\"http://id.insee.fr/geo/commune/dbab54d2-ea7e-4117-aaa9-4ea1a7110749\">\n" +
            "\t\t<Intitule>Saint-Jean-d'Illac</Intitule>\n" +
            "\t\t<Type>Commune</Type>\n" +
            "\t\t<DateCreation>1943-01-01</DateCreation>\n" +
            "\t\t<IntituleSansArticle typeArticle=\"0\">Saint-Jean-d'Illac</IntituleSansArticle>\n" +
            "\t\t<Inclusion>totale</Inclusion>\n" +
            "\t</Commune>\n" +
            "</Communes>";

    // **********************
    // COMMUNE
    // **********************
    public final static String COMMUNE_MOCK_SERVER_RETURN_GET =
            "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/commune/2217cf41-787a-4d8a-8c61-abd5683c5b8a,01002,5,L'Abergement-de-Varey,Abergement-de-Varey,1943-01-01,,\r\n";
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
                    + "http://id.insee.fr/geo/commune/3d68f1b7-513d-4a52-aa9b-ba3b93bd02c4,01005,1,Ambérieux-en-Dombes,Ambérieux-en-Dombes,1943-01-01,,\r\n";
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
    public static final String COMMUNE_EXPECTED_RESPONSE_ASCENDANTS_JSON =
            "["
                    + "{\"code\":\"011\",\"uri\":\"http://id.insee.fr/geo/arrondissement/d4677635-ed01-444c-9dfe-c9e7b70ad515\",\"type\":\"Arrondissement\",\"dateCreation\":\"2017-01-01\",\"intituleSansArticle\":\"Belley\",\"typeArticle\":\"0\",\"chefLieu\":\"01034\",\"intitule\":\"Belley\"},"
                    + "{\"code\":\"01\",\"uri\":\"http://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc\",\"type\":\"Departement\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Ain\",\"typeArticle\":\"5\",\"chefLieu\":\"01053\",\"intitule\":\"Ain\"},"
                    + "{\"code\":\"84\",\"uri\":\"http://id.insee.fr/geo/region/6db44a21-5ae3-41b5-b16d-0ab30964f78d\",\"type\":\"Region\",\"dateCreation\":\"2016-01-01\",\"intituleSansArticle\":\"Auvergne-Rhône-Alpes\",\"typeArticle\":\"1\",\"chefLieu\":\"69123\",\"intitule\":\"Auvergne-Rhône-Alpes\"}"
                    + "]";
    public static final String COMMUNE_EXPECTED_RESPONSE_ASCENDANTS_XML =
            "<Territoires>"
                    + "<Arrondissement code=\"011\" uri=\"http://id.insee.fr/geo/arrondissement/d4677635-ed01-444c-9dfe-c9e7b70ad515\"><Intitule>Belley</Intitule><Type>Arrondissement</Type><DateCreation>2017-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Belley</IntituleSansArticle><ChefLieu>01034</ChefLieu></Arrondissement>"
                    + "<Departement code=\"01\" uri=\"http://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc\"><Intitule>Ain</Intitule><Type>Departement</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"5\">Ain</IntituleSansArticle><ChefLieu>01053</ChefLieu></Departement>"
                    + "<Region code=\"84\" uri=\"http://id.insee.fr/geo/region/6db44a21-5ae3-41b5-b16d-0ab30964f78d\"><Intitule>Auvergne-Rhône-Alpes</Intitule><Type>Region</Type><DateCreation>2016-01-01</DateCreation><IntituleSansArticle typeArticle=\"1\">Auvergne-Rhône-Alpes</IntituleSansArticle><ChefLieu>69123</ChefLieu></Region>"
                    + "</Territoires>";
    public static final String COMMUNE_MOCK_SERVER_RETURN_DESCENDANTS =
            "uri,code,type,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/communeDeleguee/641df128-1268-495a-900d-41ab5bbeccff,01015,http://rdf.insee.fr/def/geo#CommuneDeleguee,1,Arbignieu,Arbignieu,2016-01-01,,\r\n"
                    + "http://id.insee.fr/geo/communeDeleguee/aa807dbe-2394-4e56-8216-3b1ca361608e,01340,http://rdf.insee.fr/def/geo#CommuneDeleguee,0,Saint-Bois,Saint-Bois,2016-01-01,,\r\n";
    public static final String COMMUNE_EXPECTED_RESPONSE_DESCENDANTS_JSON =
            "["
                    + "{\"code\":\"01015\",\"uri\":\"http://id.insee.fr/geo/communeDeleguee/641df128-1268-495a-900d-41ab5bbeccff\",\"type\":\"CommuneDeleguee\",\"dateCreation\":\"2016-01-01\",\"intituleSansArticle\":\"Arbignieu\",\"typeArticle\":\"1\",\"intitule\":\"Arbignieu\"},"
                    + "{\"code\":\"01340\",\"uri\":\"http://id.insee.fr/geo/communeDeleguee/aa807dbe-2394-4e56-8216-3b1ca361608e\",\"type\":\"CommuneDeleguee\",\"dateCreation\":\"2016-01-01\",\"intituleSansArticle\":\"Saint-Bois\",\"typeArticle\":\"0\",\"intitule\":\"Saint-Bois\"}"
                    + "]";
    public static final String COMMUNE_EXPECTED_RESPONSE_DESCENDANTS_XML =
            "<Territoires>"
                    + "<CommuneDeleguee code=\"01015\" uri=\"http://id.insee.fr/geo/communeDeleguee/641df128-1268-495a-900d-41ab5bbeccff\"><Intitule>Arbignieu</Intitule><Type>CommuneDeleguee</Type><DateCreation>2016-01-01</DateCreation><IntituleSansArticle typeArticle=\"1\">Arbignieu</IntituleSansArticle></CommuneDeleguee>"
                    + "<CommuneDeleguee code=\"01340\" uri=\"http://id.insee.fr/geo/communeDeleguee/aa807dbe-2394-4e56-8216-3b1ca361608e\"><Intitule>Saint-Bois</Intitule><Type>CommuneDeleguee</Type><DateCreation>2016-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Saint-Bois</IntituleSansArticle></CommuneDeleguee>"
                    + "</Territoires>";
    public static final String COMMUNE_MOCK_SERVER_RETURN_PRECEDENTS =
            "uri,type,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/commune/339c2d61-c685-4643-ab69-df8c86bbf2b0,Commune,01004,1,Ambérieu,Ambérieu,1943-01-01,1955-03-31,\r\n";
    public static final String COMMUNE_EXPECTED_RESPONSE_PRECEDENTS_JSON =
            "["
                    + "{"
                    + "\"code\":\"01004\","
                    + "\"uri\":\"http://id.insee.fr/geo/commune/339c2d61-c685-4643-ab69-df8c86bbf2b0\","
                    + "\"type\":\"Commune\","
                    + "\"dateCreation\":\"1943-01-01\","
                    + "\"dateSuppression\":\"1955-03-31\","
                    + "\"intituleSansArticle\":\"Ambérieu\","
                    + "\"typeArticle\":\"1\","
                    + "\"intitule\":\"Ambérieu\""
                    + "}"
                    + "]";
    public static final String COMMUNE_EXPECTED_RESPONSE_PRECEDENTS_XML =
            "<Communes>"
                    + "<Commune code=\"01004\" uri=\"http://id.insee.fr/geo/commune/339c2d61-c685-4643-ab69-df8c86bbf2b0\">"
                    + "<Intitule>Ambérieu</Intitule>"
                    + "<Type>Commune</Type>"
                    + "<DateCreation>1943-01-01</DateCreation>"
                    + "<DateSuppression>1955-03-31</DateSuppression>"
                    + "<IntituleSansArticle typeArticle=\"1\">Ambérieu</IntituleSansArticle>"
                    + "</Commune>"
                    + "</Communes>";


    public static final String CantonsCOMMUNE_MOCK_SERVER_RETURN_LISTE =
            "code,uri,type,dateCreation,intitule,intituleSansArticle,typeArticle\r\n"
                    + "0105,http://id.insee.fr/geo/canton/622ce50c-2ff4-470d-a0f3-f85baa8813a7,Canton,2016-01-01,Bourg-en-Bresse-1,Bourg-en-Bresse-1,0\r\n"
                    + "0106,http://id.insee.fr/geo/canton/888731da-1820-4662-9cc1-17be3544a01c,Canton,2016-01-01,Bourg-en-Bresse-2,Bourg-en-Bresse-2,0\r\n";
    public static final String CantonsCOMMUNE_EXPECTED_RESPONSE_LISTE_TOP_JSON =
            "["
                    + "{"
                    + "\"code\":\"0105\","
                    + "\"uri\":\"http://id.insee.fr/geo/canton/622ce50c-2ff4-470d-a0f3-f85baa8813a7\","
                    + "\"type\":\"Canton\","
                    + "\"dateCreation\":\"2016-01-01\","
                    + "\"intitule\":\"Bourg-en-Bresse-1\","
                    + "\"intituleSansArticle\":\"Bourg-en-Bresse-1\","
                    + "\"typeArticle\":\"0\""
                    + "},"
                    + "{"
                    + "\"code\":\"0106\","
                    + "\"uri\":\"http://id.insee.fr/geo/canton/888731da-1820-4662-9cc1-17be3544a01c\","
                    + "\"type\":\"Canton\","
                    + "\"dateCreation\":\"2016-01-01\","
                    + "\"intitule\":\"Bourg-en-Bresse-2\","
                    + "\"intituleSansArticle\":\"Bourg-en-Bresse-2\","
                    + "\"typeArticle\":\"0\""
                    + "}"
                    + "]";
    public static final String CantonsCOMMUNE_EXPECTED_RESPONSE_LISTE_TOP_XML =
            "<Cantons>" +
                    "<Canton code=\"0105\" uri=\"http://id.insee.fr/geo/canton/622ce50c-2ff4-470d-a0f3-f85baa8813a7\">" +
                    "<Intitule>Bourg-en-Bresse-1</Intitule>" +
                    "<Type>Canton</Type>" +
                    "<DateCreation>2016-01-01</DateCreation>" +
                    "<IntituleSansArticle typeArticle=\"0\">Bourg-en-Bresse-1</IntituleSansArticle>" +
                    "</Canton>" +
                    "<Canton code=\"0106\" uri=\"http://id.insee.fr/geo/canton/888731da-1820-4662-9cc1-17be3544a01c\">" +
                    "<Intitule>Bourg-en-Bresse-2</Intitule>" +
                    "<Type>Canton</Type>" +
                    "<DateCreation>2016-01-01</DateCreation>" +
                    "<IntituleSansArticle typeArticle=\"0\">Bourg-en-Bresse-2</IntituleSansArticle>" +
                    "</Canton>" +
                    "</Cantons>";

    // **********************
    // DEPARTEMENT
    // **********************
    public static final String DEPARTEMENT_MOCK_SERVER_RETURN_GET =
            "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc,01,5,Ain,Ain,1943-01-01,,01053\r\n";
    public static final String DEPARTEMENT_EXPECTED_RESPONSE_GET_JSON =
            "{\"code\":\"01\",\"uri\":\"http://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc\",\"type\":\"Departement\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Ain\",\"typeArticle\":\"5\",\"chefLieu\":\"01053\",\"intitule\":\"Ain\"}";
    public static final String DEPARTEMENT_EXPECTED_RESPONSE_GET_XML =
            "<Departement code=\"01\" uri=\"http://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc\"><Intitule>Ain</Intitule><Type>Departement</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"5\">Ain</IntituleSansArticle><ChefLieu>01053</ChefLieu></Departement>";
    public static final String DEPARTEMENT_MOCK_SERVER_RETURN_LISTE =
            "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc,01,5,Ain,Ain,1943-01-01,,01053\r\n"
                    + "http://id.insee.fr/geo/departement/e0fe7594-a3d1-4c71-91b6-c96ed609cbcc,02,5,Aisne,Aisne,1943-01-01,,02408\r\n"
                    + "http://id.insee.fr/geo/departement/626728b8-60a2-4f47-b88e-6d4dbea327f7,03,5,Allier,Allier,1943-01-01,,03190\r\n"
                    + "http://id.insee.fr/geo/departement/beefd3c7-fdcd-4a50-bd07-d1f9b45bee2f,04,4,Alpes-de-Haute-Provence,Alpes-de-Haute-Provence,1970-04-13,,04070";
    public static final String DEPARTEMENT_EXPECTED_RESPONSE_LISTE_TOP_JSON =
            "["
                    + "{\"code\":\"01\",\"uri\":\"http://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc\",\"type\":\"Departement\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Ain\",\"typeArticle\":\"5\",\"chefLieu\":\"01053\",\"intitule\":\"Ain\"},"
                    + "{\"code\":\"02\",\"uri\":\"http://id.insee.fr/geo/departement/e0fe7594-a3d1-4c71-91b6-c96ed609cbcc\",\"type\":\"Departement\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Aisne\",\"typeArticle\":\"5\",\"chefLieu\":\"02408\",\"intitule\":\"Aisne\"},"
                    + "{\"code\":\"03\",\"uri\":\"http://id.insee.fr/geo/departement/626728b8-60a2-4f47-b88e-6d4dbea327f7\",\"type\":\"Departement\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Allier\",\"typeArticle\":\"5\",\"chefLieu\":\"03190\",\"intitule\":\"Allier\"},"
                    + "{\"code\":\"04\",\"uri\":\"http://id.insee.fr/geo/departement/beefd3c7-fdcd-4a50-bd07-d1f9b45bee2f\",\"type\":\"Departement\",\"dateCreation\":\"1970-04-13\",\"intituleSansArticle\":\"Alpes-de-Haute-Provence\",\"typeArticle\":\"4\",\"chefLieu\":\"04070\",\"intitule\":\"Alpes-de-Haute-Provence\"}"
                    + "]";
    public static final String DEPARTEMENT_EXPECTED_RESPONSE_LISTE_TOP_XML =
            "<Departements>"
                    + "<Departement code=\"01\" uri=\"http://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc\"><Intitule>Ain</Intitule><Type>Departement</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"5\">Ain</IntituleSansArticle><ChefLieu>01053</ChefLieu></Departement>"
                    + "<Departement code=\"02\" uri=\"http://id.insee.fr/geo/departement/e0fe7594-a3d1-4c71-91b6-c96ed609cbcc\"><Intitule>Aisne</Intitule><Type>Departement</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"5\">Aisne</IntituleSansArticle><ChefLieu>02408</ChefLieu></Departement>"
                    + "<Departement code=\"03\" uri=\"http://id.insee.fr/geo/departement/626728b8-60a2-4f47-b88e-6d4dbea327f7\"><Intitule>Allier</Intitule><Type>Departement</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"5\">Allier</IntituleSansArticle><ChefLieu>03190</ChefLieu></Departement>"
                    + "<Departement code=\"04\" uri=\"http://id.insee.fr/geo/departement/beefd3c7-fdcd-4a50-bd07-d1f9b45bee2f\"><Intitule>Alpes-de-Haute-Provence</Intitule><Type>Departement</Type><DateCreation>1970-04-13</DateCreation><IntituleSansArticle typeArticle=\"4\">Alpes-de-Haute-Provence</IntituleSansArticle><ChefLieu>04070</ChefLieu></Departement>"
                    + "</Departements>";
    public static final String DEPARTEMENT_MOCK_SERVER_RETURN_ASCENDANTS =
            "uri,code,type,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/region/6db44a21-5ae3-41b5-b16d-0ab30964f78d,84,http://rdf.insee.fr/def/geo#Region,1,Auvergne-Rhône-Alpes,Auvergne-Rhône-Alpes,2016-01-01,,69123\r\n";
    public static final String DEPARTEMENT_EXPECTED_RESPONSE_ASCENDANTS_JSON =
            "["
                    + "{\"code\":\"84\",\"uri\":\"http://id.insee.fr/geo/region/6db44a21-5ae3-41b5-b16d-0ab30964f78d\",\"type\":\"Region\",\"dateCreation\":\"2016-01-01\",\"intituleSansArticle\":\"Auvergne-Rhône-Alpes\",\"typeArticle\":\"1\",\"chefLieu\":\"69123\",\"intitule\":\"Auvergne-Rhône-Alpes\"}"
                    + "]";
    public static final String DEPARTEMENT_EXPECTED_RESPONSE_ASCENDANTS_XML =
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
    public static final String DEPARTEMENT_EXPECTED_RESPONSE_DESCENDANTS_JSON =
            "["
                    + "{\"code\":\"011\",\"uri\":\"http://id.insee.fr/geo/arrondissement/d4677635-ed01-444c-9dfe-c9e7b70ad515\",\"type\":\"Arrondissement\",\"dateCreation\":\"2017-01-01\",\"intituleSansArticle\":\"Belley\",\"typeArticle\":\"0\",\"chefLieu\":\"01034\",\"intitule\":\"Belley\"},"
                    + "{\"code\":\"012\",\"uri\":\"http://id.insee.fr/geo/arrondissement/bafd343b-c49a-4c79-a79a-349415d23127\",\"type\":\"Arrondissement\",\"dateCreation\":\"2017-01-01\",\"intituleSansArticle\":\"Bourg-en-Bresse\",\"typeArticle\":\"0\",\"chefLieu\":\"01053\",\"intitule\":\"Bourg-en-Bresse\"},"
                    + "{\"code\":\"013\",\"uri\":\"http://id.insee.fr/geo/arrondissement/a2196f60-5d0b-4fa6-8dfd-d5c30936e461\",\"type\":\"Arrondissement\",\"dateCreation\":\"2017-01-01\",\"intituleSansArticle\":\"Gex\",\"typeArticle\":\"0\",\"chefLieu\":\"01173\",\"intitule\":\"Gex\"},"
                    + "{\"code\":\"014\",\"uri\":\"http://id.insee.fr/geo/arrondissement/be7259d5-f1af-4cd4-8334-125e2d991946\",\"type\":\"Arrondissement\",\"dateCreation\":\"2017-01-01\",\"intituleSansArticle\":\"Nantua\",\"typeArticle\":\"0\",\"chefLieu\":\"01269\",\"intitule\":\"Nantua\"},"
                    + "{\"code\":\"01001\",\"uri\":\"http://id.insee.fr/geo/commune/0ba291b3-c220-45e2-b2f9-a666789623f8\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Abergement-Clémenciat\",\"typeArticle\":\"5\",\"intitule\":\"L'Abergement-Clémenciat\"}"
                    + "]";
    public static final String DEPARTEMENT_EXPECTED_RESPONSE_DESCENDANTS_XML =
            "<Territoires>"
                    + "<Arrondissement code=\"011\" uri=\"http://id.insee.fr/geo/arrondissement/d4677635-ed01-444c-9dfe-c9e7b70ad515\"><Intitule>Belley</Intitule><Type>Arrondissement</Type><DateCreation>2017-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Belley</IntituleSansArticle><ChefLieu>01034</ChefLieu></Arrondissement>"
                    + "<Arrondissement code=\"012\" uri=\"http://id.insee.fr/geo/arrondissement/bafd343b-c49a-4c79-a79a-349415d23127\"><Intitule>Bourg-en-Bresse</Intitule><Type>Arrondissement</Type><DateCreation>2017-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Bourg-en-Bresse</IntituleSansArticle><ChefLieu>01053</ChefLieu></Arrondissement>"
                    + "<Arrondissement code=\"013\" uri=\"http://id.insee.fr/geo/arrondissement/a2196f60-5d0b-4fa6-8dfd-d5c30936e461\"><Intitule>Gex</Intitule><Type>Arrondissement</Type><DateCreation>2017-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Gex</IntituleSansArticle><ChefLieu>01173</ChefLieu></Arrondissement>"
                    + "<Arrondissement code=\"014\" uri=\"http://id.insee.fr/geo/arrondissement/be7259d5-f1af-4cd4-8334-125e2d991946\"><Intitule>Nantua</Intitule><Type>Arrondissement</Type><DateCreation>2017-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Nantua</IntituleSansArticle><ChefLieu>01269</ChefLieu></Arrondissement>"
                    + "<Commune code=\"01001\" uri=\"http://id.insee.fr/geo/commune/0ba291b3-c220-45e2-b2f9-a666789623f8\"><Intitule>L'Abergement-Clémenciat</Intitule><Type>Commune</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"5\">Abergement-Clémenciat</IntituleSansArticle></Commune>"
                    + "</Territoires>";
    public static final String DEPARTEMENT_MOCK_SERVER_RETURN_PRECEDENTS =
            "uri,type,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/departement/21cde70f-d7be-4410-9a04-5f6f73dc5e70,Departement,78,3,Seine-et-Oise,Seine-et-Oise,1943-01-01,1968-01-01,78646\r\n";
    public static final String DEPARTEMENT_EXPECTED_RESPONSE_PRECEDENTS_JSON = "[{\"code\":\"78\",\"uri\":\"http://id.insee.fr/geo/departement/21cde70f-d7be-4410-9a04-5f6f73dc5e70\",\"type\":\"Departement\",\"dateCreation\":\"1943-01-01\",\"dateSuppression\":\"1968-01-01\",\"intituleSansArticle\":\"Seine-et-Oise\",\"typeArticle\":\"3\",\"chefLieu\":\"78646\",\"intitule\":\"Seine-et-Oise\"}]";
    public static final String DEPARTEMENT_EXPECTED_RESPONSE_PRECEDENTS_XML = "<Departements><Departement code=\"78\" uri=\"http://id.insee.fr/geo/departement/21cde70f-d7be-4410-9a04-5f6f73dc5e70\"><Intitule>Seine-et-Oise</Intitule><Type>Departement</Type><DateCreation>1943-01-01</DateCreation><DateSuppression>1968-01-01</DateSuppression><IntituleSansArticle typeArticle=\"3\">Seine-et-Oise</IntituleSansArticle><ChefLieu>78646</ChefLieu></Departement></Departements>";
    public static final String DEPARTEMENT_MOCK_SERVER_RETURN_SUIVANTS =
            "uri,type,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/departement/816c56af-148a-4a63-b68b-792f7b608bab,Departement,78,4,Yvelines,Yvelines,1968-01-01,,78646\r\n"
                    + "http://id.insee.fr/geo/departement/41c663b2-29cd-44ee-9d72-04836804dd99,Departement,91,5,Essonne,Essonne,1968-01-01,,91228\r\n"
                    + "http://id.insee.fr/geo/departement/49cc57f5-a9ff-4a9e-819d-def0a63a1fac,Departement,95,2,Val-d'Oise,Val-d'Oise,1968-01-01,,95500\r\n";
    public static final String DEPARTEMENT_EXPECTED_RESPONSE_SUIVANTS_JSON = "[{\"code\":\"78\",\"uri\":\"http://id.insee.fr/geo/departement/816c56af-148a-4a63-b68b-792f7b608bab\",\"type\":\"Departement\",\"dateCreation\":\"1968-01-01\",\"intituleSansArticle\":\"Yvelines\",\"typeArticle\":\"4\",\"chefLieu\":\"78646\",\"intitule\":\"Yvelines\"},{\"code\":\"91\",\"uri\":\"http://id.insee.fr/geo/departement/41c663b2-29cd-44ee-9d72-04836804dd99\",\"type\":\"Departement\",\"dateCreation\":\"1968-01-01\",\"intituleSansArticle\":\"Essonne\",\"typeArticle\":\"5\",\"chefLieu\":\"91228\",\"intitule\":\"Essonne\"},{\"code\":\"95\",\"uri\":\"http://id.insee.fr/geo/departement/49cc57f5-a9ff-4a9e-819d-def0a63a1fac\",\"type\":\"Departement\",\"dateCreation\":\"1968-01-01\",\"intituleSansArticle\":\"Val-d'Oise\",\"typeArticle\":\"2\",\"chefLieu\":\"95500\",\"intitule\":\"Val-d'Oise\"}]";
    public static final String DEPARTEMENT_EXPECTED_RESPONSE_SUIVANTS_XML = "<Departements><Departement code=\"78\" uri=\"http://id.insee.fr/geo/departement/816c56af-148a-4a63-b68b-792f7b608bab\"><Intitule>Yvelines</Intitule><Type>Departement</Type><DateCreation>1968-01-01</DateCreation><IntituleSansArticle typeArticle=\"4\">Yvelines</IntituleSansArticle><ChefLieu>78646</ChefLieu></Departement><Departement code=\"91\" uri=\"http://id.insee.fr/geo/departement/41c663b2-29cd-44ee-9d72-04836804dd99\"><Intitule>Essonne</Intitule><Type>Departement</Type><DateCreation>1968-01-01</DateCreation><IntituleSansArticle typeArticle=\"5\">Essonne</IntituleSansArticle><ChefLieu>91228</ChefLieu></Departement><Departement code=\"95\" uri=\"http://id.insee.fr/geo/departement/49cc57f5-a9ff-4a9e-819d-def0a63a1fac\"><Intitule>Val-d'Oise</Intitule><Type>Departement</Type><DateCreation>1968-01-01</DateCreation><IntituleSansArticle typeArticle=\"2\">Val-d'Oise</IntituleSansArticle><ChefLieu>95500</ChefLieu></Departement></Departements>";

    // **********************
    // ARRONDISSEMENT
    // **********************
    public static final String ARRONDISSEMENT_MOCK_SERVER_RETURN_GET =
            "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/arrondissement/e6e49119-fbb8-4279-a78c-e3ddfeaf36ad,213,0,Montbard,Montbard,1993-01-01,,21425";
    public static final String ARRONDISSEMENT_EXPECTED_RESPONSE_GET_JSON =
            "{\"code\":\"213\",\"uri\":\"http://id.insee.fr/geo/arrondissement/e6e49119-fbb8-4279-a78c-e3ddfeaf36ad\",\"type\":\"Arrondissement\",\"dateCreation\":\"1993-01-01\",\"intituleSansArticle\":\"Montbard\",\"typeArticle\":\"0\",\"chefLieu\":\"21425\",\"intitule\":\"Montbard\"}";
    public static final String ARRONDISSEMENT_EXPECTED_RESPONSE_GET_XML =
            "<Arrondissement code=\"213\" uri=\"http://id.insee.fr/geo/arrondissement/e6e49119-fbb8-4279-a78c-e3ddfeaf36ad\"><Intitule>Montbard</Intitule><Type>Arrondissement</Type><DateCreation>1993-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Montbard</IntituleSansArticle><ChefLieu>21425</ChefLieu></Arrondissement>";
    public static final String ARRONDISSEMENT_MOCK_SERVER_RETURN_LISTE =
            "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/arrondissement/d4677635-ed01-444c-9dfe-c9e7b70ad515,011,0,Belley,Belley,2017-01-01,,01034\r\n"
                    + "http://id.insee.fr/geo/arrondissement/bafd343b-c49a-4c79-a79a-349415d23127,012,0,Bourg-en-Bresse,Bourg-en-Bresse,2017-01-01,,01053\r\n"
                    + "http://id.insee.fr/geo/arrondissement/a2196f60-5d0b-4fa6-8dfd-d5c30936e461,013,0,Gex,Gex,2017-01-01,,01173\r\n"
                    + "http://id.insee.fr/geo/arrondissement/be7259d5-f1af-4cd4-8334-125e2d991946,014,0,Nantua,Nantua,2017-01-01,,01269";
    public static final String ARRONDISSEMENT_EXPECTED_RESPONSE_LISTE_TOP_JSON =
            "["
                    + "{\"code\":\"011\",\"uri\":\"http://id.insee.fr/geo/arrondissement/d4677635-ed01-444c-9dfe-c9e7b70ad515\",\"type\":\"Arrondissement\",\"dateCreation\":\"2017-01-01\",\"intituleSansArticle\":\"Belley\",\"typeArticle\":\"0\",\"chefLieu\":\"01034\",\"intitule\":\"Belley\"},"
                    + "{\"code\":\"012\",\"uri\":\"http://id.insee.fr/geo/arrondissement/bafd343b-c49a-4c79-a79a-349415d23127\",\"type\":\"Arrondissement\",\"dateCreation\":\"2017-01-01\",\"intituleSansArticle\":\"Bourg-en-Bresse\",\"typeArticle\":\"0\",\"chefLieu\":\"01053\",\"intitule\":\"Bourg-en-Bresse\"},"
                    + "{\"code\":\"013\",\"uri\":\"http://id.insee.fr/geo/arrondissement/a2196f60-5d0b-4fa6-8dfd-d5c30936e461\",\"type\":\"Arrondissement\",\"dateCreation\":\"2017-01-01\",\"intituleSansArticle\":\"Gex\",\"typeArticle\":\"0\",\"chefLieu\":\"01173\",\"intitule\":\"Gex\"},"
                    + "{\"code\":\"014\",\"uri\":\"http://id.insee.fr/geo/arrondissement/be7259d5-f1af-4cd4-8334-125e2d991946\",\"type\":\"Arrondissement\",\"dateCreation\":\"2017-01-01\",\"intituleSansArticle\":\"Nantua\",\"typeArticle\":\"0\",\"chefLieu\":\"01269\",\"intitule\":\"Nantua\"}"
                    + "]";
    public static final String ARRONDISSEMENT_EXPECTED_RESPONSE_LISTE_TOP_XML =
            "<Arrondissements>"
                    + "<Arrondissement code=\"011\" uri=\"http://id.insee.fr/geo/arrondissement/d4677635-ed01-444c-9dfe-c9e7b70ad515\"><Intitule>Belley</Intitule><Type>Arrondissement</Type><DateCreation>2017-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Belley</IntituleSansArticle><ChefLieu>01034</ChefLieu></Arrondissement>"
                    + "<Arrondissement code=\"012\" uri=\"http://id.insee.fr/geo/arrondissement/bafd343b-c49a-4c79-a79a-349415d23127\"><Intitule>Bourg-en-Bresse</Intitule><Type>Arrondissement</Type><DateCreation>2017-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Bourg-en-Bresse</IntituleSansArticle><ChefLieu>01053</ChefLieu></Arrondissement>"
                    + "<Arrondissement code=\"013\" uri=\"http://id.insee.fr/geo/arrondissement/a2196f60-5d0b-4fa6-8dfd-d5c30936e461\"><Intitule>Gex</Intitule><Type>Arrondissement</Type><DateCreation>2017-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Gex</IntituleSansArticle><ChefLieu>01173</ChefLieu></Arrondissement>"
                    + "<Arrondissement code=\"014\" uri=\"http://id.insee.fr/geo/arrondissement/be7259d5-f1af-4cd4-8334-125e2d991946\"><Intitule>Nantua</Intitule><Type>Arrondissement</Type><DateCreation>2017-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Nantua</IntituleSansArticle><ChefLieu>01269</ChefLieu></Arrondissement>"
                    + "</Arrondissements>";
    public static final String ARRONDISSEMENT_MOCK_SERVER_RETURN_ASCENDANTS =
            "uri,code,type,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/departement/31647c0a-d1c1-4b1c-a0c9-125164f48106,21,http://rdf.insee.fr/def/geo#Departement,3,Côte-d'Or,Côte-d'Or,1943-01-01,,21231\r\n"
                    + "http://id.insee.fr/geo/region/830a70a3-ef73-4fe9-9bf9-ce5eed5b1a54,27,http://rdf.insee.fr/def/geo#Region,0,Bourgogne-Franche-Comté,Bourgogne-Franche-Comté,2016-01-01,,21231\r\n";
    public static final String ARRONDISSEMENT_EXPECTED_RESPONSE_ASCENDANTS_JSON =
            "["
                    + "{\"code\":\"21\",\"uri\":\"http://id.insee.fr/geo/departement/31647c0a-d1c1-4b1c-a0c9-125164f48106\",\"type\":\"Departement\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Côte-d'Or\",\"typeArticle\":\"3\",\"chefLieu\":\"21231\",\"intitule\":\"Côte-d'Or\"},"
                    + "{\"code\":\"27\",\"uri\":\"http://id.insee.fr/geo/region/830a70a3-ef73-4fe9-9bf9-ce5eed5b1a54\",\"type\":\"Region\",\"dateCreation\":\"2016-01-01\",\"intituleSansArticle\":\"Bourgogne-Franche-Comté\",\"typeArticle\":\"0\",\"chefLieu\":\"21231\",\"intitule\":\"Bourgogne-Franche-Comté\"}"
                    + "]";
    public static final String ARRONDISSEMENT_EXPECTED_RESPONSE_ASCENDANTS_XML =
            "<Territoires>"
                    + "<Departement code=\"21\" uri=\"http://id.insee.fr/geo/departement/31647c0a-d1c1-4b1c-a0c9-125164f48106\"><Intitule>Côte-d'Or</Intitule><Type>Departement</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"3\">Côte-d'Or</IntituleSansArticle><ChefLieu>21231</ChefLieu></Departement>"
                    + "<Region code=\"27\" uri=\"http://id.insee.fr/geo/region/830a70a3-ef73-4fe9-9bf9-ce5eed5b1a54\"><Intitule>Bourgogne-Franche-Comté</Intitule><Type>Region</Type><DateCreation>2016-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Bourgogne-Franche-Comté</IntituleSansArticle><ChefLieu>21231</ChefLieu></Region>"
                    + "</Territoires>";
    public static final String ARRONDISSEMENT_MOCK_SERVER_RETURN_DESCENDANTS =
            "uri,code,type,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/commune/8c4b5970-8a92-4ce4-9a42-3c7383236275,21004,http://rdf.insee.fr/def/geo#Commune,1,Aignay-le-Duc,Aignay-le-Duc,1943-01-01,,\r\n"
                    + "http://id.insee.fr/geo/commune/1ed68fb0-2c0f-47e5-9fef-b34684c9d7a1,21006,http://rdf.insee.fr/def/geo#Commune,1,Aisey-sur-Seine,Aisey-sur-Seine,1943-01-01,,\r\n"
                    + "http://id.insee.fr/geo/commune/56ffb732-7dbb-4f62-b92c-fcee071f4efa,21007,http://rdf.insee.fr/def/geo#Commune,1,Aisy-sous-Thil,Aisy-sous-Thil,1943-01-01,,\r\n"
                    + "http://id.insee.fr/geo/commune/533630bf-ea82-453a-ae6b-0962c51c6d3f,21008,http://rdf.insee.fr/def/geo#Commune,1,Alise-Sainte-Reine,Alise-Sainte-Reine,1943-01-01,,";
    public static final String ARRONDISSEMENT_EXPECTED_RESPONSE_DESCENDANTS_JSON =
            "["
                    + "{\"code\":\"21004\",\"uri\":\"http://id.insee.fr/geo/commune/8c4b5970-8a92-4ce4-9a42-3c7383236275\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Aignay-le-Duc\",\"typeArticle\":\"1\",\"intitule\":\"Aignay-le-Duc\"},"
                    + "{\"code\":\"21006\",\"uri\":\"http://id.insee.fr/geo/commune/1ed68fb0-2c0f-47e5-9fef-b34684c9d7a1\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Aisey-sur-Seine\",\"typeArticle\":\"1\",\"intitule\":\"Aisey-sur-Seine\"},"
                    + "{\"code\":\"21007\",\"uri\":\"http://id.insee.fr/geo/commune/56ffb732-7dbb-4f62-b92c-fcee071f4efa\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Aisy-sous-Thil\",\"typeArticle\":\"1\",\"intitule\":\"Aisy-sous-Thil\"},"
                    + "{\"code\":\"21008\",\"uri\":\"http://id.insee.fr/geo/commune/533630bf-ea82-453a-ae6b-0962c51c6d3f\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Alise-Sainte-Reine\",\"typeArticle\":\"1\",\"intitule\":\"Alise-Sainte-Reine\"}"
                    + "]";
    public static final String ARRONDISSEMENT_EXPECTED_RESPONSE_DESCENDANTS_XML =
            "<Territoires>"
                    + "<Commune code=\"21004\" uri=\"http://id.insee.fr/geo/commune/8c4b5970-8a92-4ce4-9a42-3c7383236275\"><Intitule>Aignay-le-Duc</Intitule><Type>Commune</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"1\">Aignay-le-Duc</IntituleSansArticle></Commune>"
                    + "<Commune code=\"21006\" uri=\"http://id.insee.fr/geo/commune/1ed68fb0-2c0f-47e5-9fef-b34684c9d7a1\"><Intitule>Aisey-sur-Seine</Intitule><Type>Commune</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"1\">Aisey-sur-Seine</IntituleSansArticle></Commune>"
                    + "<Commune code=\"21007\" uri=\"http://id.insee.fr/geo/commune/56ffb732-7dbb-4f62-b92c-fcee071f4efa\"><Intitule>Aisy-sous-Thil</Intitule><Type>Commune</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"1\">Aisy-sous-Thil</IntituleSansArticle></Commune>"
                    + "<Commune code=\"21008\" uri=\"http://id.insee.fr/geo/commune/533630bf-ea82-453a-ae6b-0962c51c6d3f\"><Intitule>Alise-Sainte-Reine</Intitule><Type>Commune</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"1\">Alise-Sainte-Reine</IntituleSansArticle></Commune>"
                    + "</Territoires>";
    public static final String ARRONDISSEMENT_MOCK_SERVER_RETURN_PRECEDENTS =
            "uri,type,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/arrondissement/2f7cf574-f484-4724-99e2-9cdce527d6fc,Arrondissement,191,0,Brive-la-Gaillarde,Brive-la-Gaillarde,1993-01-01,2017-01-01,19031\r\n"
                    + "http://id.insee.fr/geo/arrondissement/0a3e9d35-4fd3-42a4-bac9-2428b095bdfb,Arrondissement,192,0,Tulle,Tulle,1993-01-01,2017-01-01,19272\r\n"
                    + "http://id.insee.fr/geo/arrondissement/cafc6057-1b9e-4372-ac5e-5e9d9980090b,Arrondissement,193,1,Ussel,Ussel,1993-01-01,2017-01-01,19275\r\n";
    public static final String ARRONDISSEMENT_EXPECTED_RESPONSE_PRECEDENTS_JSON =
            "["
                    + "{\"code\":\"191\",\"uri\":\"http://id.insee.fr/geo/arrondissement/2f7cf574-f484-4724-99e2-9cdce527d6fc\",\"type\":\"Arrondissement\",\"dateCreation\":\"1993-01-01\",\"dateSuppression\":\"2017-01-01\",\"intituleSansArticle\":\"Brive-la-Gaillarde\",\"typeArticle\":\"0\",\"chefLieu\":\"19031\",\"intitule\":\"Brive-la-Gaillarde\"},"
                    + "{\"code\":\"192\",\"uri\":\"http://id.insee.fr/geo/arrondissement/0a3e9d35-4fd3-42a4-bac9-2428b095bdfb\",\"type\":\"Arrondissement\",\"dateCreation\":\"1993-01-01\",\"dateSuppression\":\"2017-01-01\",\"intituleSansArticle\":\"Tulle\",\"typeArticle\":\"0\",\"chefLieu\":\"19272\",\"intitule\":\"Tulle\"},"
                    + "{\"code\":\"193\",\"uri\":\"http://id.insee.fr/geo/arrondissement/cafc6057-1b9e-4372-ac5e-5e9d9980090b\",\"type\":\"Arrondissement\",\"dateCreation\":\"1993-01-01\",\"dateSuppression\":\"2017-01-01\",\"intituleSansArticle\":\"Ussel\",\"typeArticle\":\"1\",\"chefLieu\":\"19275\",\"intitule\":\"Ussel\"}"
                    + "]";
    public static final String ARRONDISSEMENT_EXPECTED_RESPONSE_PRECEDENTS_XML =
            "<Arrondissements>"
                    + "<Arrondissement code=\"191\" uri=\"http://id.insee.fr/geo/arrondissement/2f7cf574-f484-4724-99e2-9cdce527d6fc\"><Intitule>Brive-la-Gaillarde</Intitule><Type>Arrondissement</Type><DateCreation>1993-01-01</DateCreation><DateSuppression>2017-01-01</DateSuppression><IntituleSansArticle typeArticle=\"0\">Brive-la-Gaillarde</IntituleSansArticle><ChefLieu>19031</ChefLieu></Arrondissement>"
                    + "<Arrondissement code=\"192\" uri=\"http://id.insee.fr/geo/arrondissement/0a3e9d35-4fd3-42a4-bac9-2428b095bdfb\"><Intitule>Tulle</Intitule><Type>Arrondissement</Type><DateCreation>1993-01-01</DateCreation><DateSuppression>2017-01-01</DateSuppression><IntituleSansArticle typeArticle=\"0\">Tulle</IntituleSansArticle><ChefLieu>19272</ChefLieu></Arrondissement>"
                    + "<Arrondissement code=\"193\" uri=\"http://id.insee.fr/geo/arrondissement/cafc6057-1b9e-4372-ac5e-5e9d9980090b\"><Intitule>Ussel</Intitule><Type>Arrondissement</Type><DateCreation>1993-01-01</DateCreation><DateSuppression>2017-01-01</DateSuppression><IntituleSansArticle typeArticle=\"1\">Ussel</IntituleSansArticle><ChefLieu>19275</ChefLieu></Arrondissement>"
                    + "</Arrondissements>";

    public static final String ARRONDISSEMENT_EXPECTED_RESPONSE_SUIVANTS_JSON =
            "["
                    + "{\"code\":\"191\",\"uri\":\"http://id.insee.fr/geo/arrondissement/2f7cf574-f484-4724-99e2-9cdce527d6fc\",\"type\":\"Arrondissement\",\"dateCreation\":\"1993-01-01\",\"dateSuppression\":\"2017-01-01\",\"intituleSansArticle\":\"Brive-la-Gaillarde\",\"typeArticle\":\"0\",\"chefLieu\":\"19031\",\"intitule\":\"Brive-la-Gaillarde\"},"
                    + "{\"code\":\"192\",\"uri\":\"http://id.insee.fr/geo/arrondissement/0a3e9d35-4fd3-42a4-bac9-2428b095bdfb\",\"type\":\"Arrondissement\",\"dateCreation\":\"1993-01-01\",\"dateSuppression\":\"2017-01-01\",\"intituleSansArticle\":\"Tulle\",\"typeArticle\":\"0\",\"chefLieu\":\"19272\",\"intitule\":\"Tulle\"},"
                    + "{\"code\":\"193\",\"uri\":\"http://id.insee.fr/geo/arrondissement/cafc6057-1b9e-4372-ac5e-5e9d9980090b\",\"type\":\"Arrondissement\",\"dateCreation\":\"1993-01-01\",\"dateSuppression\":\"2017-01-01\",\"intituleSansArticle\":\"Ussel\",\"typeArticle\":\"1\",\"chefLieu\":\"19275\",\"intitule\":\"Ussel\"}"
                    + "]";
    public static final String ARRONDISSEMENT_EXPECTED_RESPONSE_SUIVANTS_XML =
            "<Arrondissements>"
                    + "<Arrondissement code=\"191\" uri=\"http://id.insee.fr/geo/arrondissement/2f7cf574-f484-4724-99e2-9cdce527d6fc\"><Intitule>Brive-la-Gaillarde</Intitule><Type>Arrondissement</Type><DateCreation>1993-01-01</DateCreation><DateSuppression>2017-01-01</DateSuppression><IntituleSansArticle typeArticle=\"0\">Brive-la-Gaillarde</IntituleSansArticle><ChefLieu>19031</ChefLieu></Arrondissement>"
                    + "<Arrondissement code=\"192\" uri=\"http://id.insee.fr/geo/arrondissement/0a3e9d35-4fd3-42a4-bac9-2428b095bdfb\"><Intitule>Tulle</Intitule><Type>Arrondissement</Type><DateCreation>1993-01-01</DateCreation><DateSuppression>2017-01-01</DateSuppression><IntituleSansArticle typeArticle=\"0\">Tulle</IntituleSansArticle><ChefLieu>19272</ChefLieu></Arrondissement>"
                    + "<Arrondissement code=\"193\" uri=\"http://id.insee.fr/geo/arrondissement/cafc6057-1b9e-4372-ac5e-5e9d9980090b\"><Intitule>Ussel</Intitule><Type>Arrondissement</Type><DateCreation>1993-01-01</DateCreation><DateSuppression>2017-01-01</DateSuppression><IntituleSansArticle typeArticle=\"1\">Ussel</IntituleSansArticle><ChefLieu>19275</ChefLieu></Arrondissement>"
                    + "</Arrondissements>";

    // **********************
    // REGION
    // **********************
    public static final String REGION_MOCK_SERVER_RETURN_GET =
            "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/region/a464366d-321a-49ae-ba50-2624c4468657,01,3,Guadeloupe,Guadeloupe,2007-07-15,,97105";
    public static final String REGION_EXPECTED_RESPONSE_GET_JSON =
            "{\"code\":\"01\",\"uri\":\"http://id.insee.fr/geo/region/a464366d-321a-49ae-ba50-2624c4468657\",\"type\":\"Region\",\"dateCreation\":\"2007-07-15\",\"intituleSansArticle\":\"Guadeloupe\",\"typeArticle\":\"3\",\"chefLieu\":\"97105\",\"intitule\":\"Guadeloupe\"}";
    public static final String REGION_EXPECTED_RESPONSE_GET_XML =
            "<Region code=\"01\" uri=\"http://id.insee.fr/geo/region/a464366d-321a-49ae-ba50-2624c4468657\"><Intitule>Guadeloupe</Intitule><Type>Region</Type><DateCreation>2007-07-15</DateCreation><IntituleSansArticle typeArticle=\"3\">Guadeloupe</IntituleSansArticle><ChefLieu>97105</ChefLieu></Region>";
    public static final String REGION_MOCK_SERVER_RETURN_LISTE =
            "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/region/a464366d-321a-49ae-ba50-2624c4468657,01,3,Guadeloupe,Guadeloupe,2007-07-15,,97105\r\n"
                    + "http://id.insee.fr/geo/region/87fbb303-895c-42b3-b152-0eff1b5995c0,02,3,Martinique,Martinique,1982-03-02,,97209\r\n"
                    + "http://id.insee.fr/geo/region/c090fea1-7739-4885-99da-749923130f72,03,3,Guyane,Guyane,1982-03-02,,97302\r\n"
                    + "http://id.insee.fr/geo/region/23e22caa-9bf3-4aaf-96ee-0c1fa0e323e5,04,0,La Réunion,La Réunion,1982-03-02,,97411";
    public static final String REGION_EXPECTED_RESPONSE_LISTE_TOP_JSON =
            "["
                    + "{\"code\":\"01\",\"uri\":\"http://id.insee.fr/geo/region/a464366d-321a-49ae-ba50-2624c4468657\",\"type\":\"Region\",\"dateCreation\":\"2007-07-15\",\"intituleSansArticle\":\"Guadeloupe\",\"typeArticle\":\"3\",\"chefLieu\":\"97105\",\"intitule\":\"Guadeloupe\"},"
                    + "{\"code\":\"02\",\"uri\":\"http://id.insee.fr/geo/region/87fbb303-895c-42b3-b152-0eff1b5995c0\",\"type\":\"Region\",\"dateCreation\":\"1982-03-02\",\"intituleSansArticle\":\"Martinique\",\"typeArticle\":\"3\",\"chefLieu\":\"97209\",\"intitule\":\"Martinique\"},"
                    + "{\"code\":\"03\",\"uri\":\"http://id.insee.fr/geo/region/c090fea1-7739-4885-99da-749923130f72\",\"type\":\"Region\",\"dateCreation\":\"1982-03-02\",\"intituleSansArticle\":\"Guyane\",\"typeArticle\":\"3\",\"chefLieu\":\"97302\",\"intitule\":\"Guyane\"},"
                    + "{\"code\":\"04\",\"uri\":\"http://id.insee.fr/geo/region/23e22caa-9bf3-4aaf-96ee-0c1fa0e323e5\",\"type\":\"Region\",\"dateCreation\":\"1982-03-02\",\"intituleSansArticle\":\"La Réunion\",\"typeArticle\":\"0\",\"chefLieu\":\"97411\",\"intitule\":\"La Réunion\"}"
                    + "]";
    public static final String REGION_EXPECTED_RESPONSE_LISTE_TOP_XML =
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
    public static final String REGION_EXPECTED_RESPONSE_DESCENDANTS_JSON =
            "["
                    + "{\"code\":\"9711\",\"uri\":\"http://id.insee.fr/geo/arrondissement/06c629c0-b1c0-462b-884c-4393e77dd761\",\"type\":\"Arrondissement\",\"dateCreation\":\"1993-01-01\",\"intituleSansArticle\":\"Basse-Terre\",\"typeArticle\":\"0\",\"chefLieu\":\"97105\",\"intitule\":\"Basse-Terre\"},"
                    + "{\"code\":\"9712\",\"uri\":\"http://id.insee.fr/geo/arrondissement/57449b26-7a1e-4a51-bc6e-6f6aa96ebef7\",\"type\":\"Arrondissement\",\"dateCreation\":\"1993-01-01\",\"intituleSansArticle\":\"Pointe-à-Pitre\",\"typeArticle\":\"0\",\"chefLieu\":\"97120\",\"intitule\":\"Pointe-à-Pitre\"},"
                    + "{\"code\":\"97101\",\"uri\":\"http://id.insee.fr/geo/commune/cf73afd7-a50e-49f8-9b86-23945b0bdfb0\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Abymes\",\"typeArticle\":\"4\",\"intitule\":\"Les Abymes\"},"
                    + "{\"code\":\"97102\",\"uri\":\"http://id.insee.fr/geo/commune/1eb1a160-fc5d-470e-8bd5-ed98f3621805\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Anse-Bertrand\",\"typeArticle\":\"1\",\"intitule\":\"Anse-Bertrand\"}"
                    + "]";
    public static final String REGION_EXPECTED_RESPONSE_DESCENDANTS_XML =
            "<Territoires>"
                    + "<Arrondissement code=\"9711\" uri=\"http://id.insee.fr/geo/arrondissement/06c629c0-b1c0-462b-884c-4393e77dd761\"><Intitule>Basse-Terre</Intitule><Type>Arrondissement</Type><DateCreation>1993-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Basse-Terre</IntituleSansArticle><ChefLieu>97105</ChefLieu></Arrondissement>"
                    + "<Arrondissement code=\"9712\" uri=\"http://id.insee.fr/geo/arrondissement/57449b26-7a1e-4a51-bc6e-6f6aa96ebef7\"><Intitule>Pointe-à-Pitre</Intitule><Type>Arrondissement</Type><DateCreation>1993-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Pointe-à-Pitre</IntituleSansArticle><ChefLieu>97120</ChefLieu></Arrondissement>"
                    + "<Commune code=\"97101\" uri=\"http://id.insee.fr/geo/commune/cf73afd7-a50e-49f8-9b86-23945b0bdfb0\"><Intitule>Les Abymes</Intitule><Type>Commune</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"4\">Abymes</IntituleSansArticle></Commune>"
                    + "<Commune code=\"97102\" uri=\"http://id.insee.fr/geo/commune/1eb1a160-fc5d-470e-8bd5-ed98f3621805\"><Intitule>Anse-Bertrand</Intitule><Type>Commune</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"1\">Anse-Bertrand</IntituleSansArticle></Commune>"
                    + "</Territoires>";
    public static final String REGION_MOCK_SERVER_RETURN_PRECEDENTS =
            "uri,type,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/region/a38fd4fc-ac62-421f-84ab-048ec6e124b7,Region,01,3,Guadeloupe,Guadeloupe,1982-03-02,2007-07-15,97105\r\n";
    public static final String REGION_EXPECTED_RESPONSE_PRECEDENTS_JSON = "[{\"code\":\"01\",\"uri\":\"http://id.insee.fr/geo/region/a38fd4fc-ac62-421f-84ab-048ec6e124b7\",\"type\":\"Region\",\"dateCreation\":\"1982-03-02\",\"dateSuppression\":\"2007-07-15\",\"intituleSansArticle\":\"Guadeloupe\",\"typeArticle\":\"3\",\"chefLieu\":\"97105\",\"intitule\":\"Guadeloupe\"}]";
    public static final String REGION_EXPECTED_RESPONSE_PRECEDENTS_XML = "<Regions><Region code=\"01\" uri=\"http://id.insee.fr/geo/region/a38fd4fc-ac62-421f-84ab-048ec6e124b7\"><Intitule>Guadeloupe</Intitule><Type>Region</Type><DateCreation>1982-03-02</DateCreation><DateSuppression>2007-07-15</DateSuppression><IntituleSansArticle typeArticle=\"3\">Guadeloupe</IntituleSansArticle><ChefLieu>97105</ChefLieu></Region></Regions>";
    public static final String REGION_MOCK_SERVER_RETURN_SUIVANTS =
            "uri,type,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/region/a464366d-321a-49ae-ba50-2624c4468657,Region,01,3,Guadeloupe,Guadeloupe,2007-07-15,,97105\r\n";
    public static final String REGION_EXPECTED_RESPONSE_SUIVANTS_JSON = "[{\"code\":\"01\",\"uri\":\"http://id.insee.fr/geo/region/a464366d-321a-49ae-ba50-2624c4468657\",\"type\":\"Region\",\"dateCreation\":\"2007-07-15\",\"intituleSansArticle\":\"Guadeloupe\",\"typeArticle\":\"3\",\"chefLieu\":\"97105\",\"intitule\":\"Guadeloupe\"}]";
    public static final String REGION_EXPECTED_RESPONSE_SUIVANTS_XML = "<Regions><Region code=\"01\" uri=\"http://id.insee.fr/geo/region/a464366d-321a-49ae-ba50-2624c4468657\"><Intitule>Guadeloupe</Intitule><Type>Region</Type><DateCreation>2007-07-15</DateCreation><IntituleSansArticle typeArticle=\"3\">Guadeloupe</IntituleSansArticle><ChefLieu>97105</ChefLieu></Region></Regions>";

    // **********************
    // COMMUNE ASSOCIEE
    // **********************

    public static final String COMMUNE_ASSOCIEE_MOCK_SERVER_RETURN_GET =
            "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/communeAssociee/d4d9337b-25fe-4945-ba1c-14026d6f323b,01120,0,Cordieux,Cordieux,1973-01-01,,\r\n";
    public static final String COMMUNE_ASSOCIEE_EXPECTED_RESPONSE_GET_JSON =
            "{\"code\":\"01120\",\"uri\":\"http://id.insee.fr/geo/communeAssociee/d4d9337b-25fe-4945-ba1c-14026d6f323b\",\"type\":\"CommuneAssociee\",\"dateCreation\":\"1973-01-01\",\"intituleSansArticle\":\"Cordieux\",\"typeArticle\":\"0\",\"intitule\":\"Cordieux\"}";
    public static final String COMMUNE_ASSOCIEE_EXPECTED_RESPONSE_GET_XML =
            "<CommuneAssociee code=\"01120\" uri=\"http://id.insee.fr/geo/communeAssociee/d4d9337b-25fe-4945-ba1c-14026d6f323b\"><Intitule>Cordieux</Intitule><Type>CommuneAssociee</Type><DateCreation>1973-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Cordieux</IntituleSansArticle></CommuneAssociee>";
    public static final String COMMUNE_ASSOCIEE_MOCK_SERVER_RETURN_LISTE =
            "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/communeAssociee/d4d9337b-25fe-4945-ba1c-14026d6f323b,01120,0,Cordieux,Cordieux,1973-01-01,,\r\n"
                    + "http://id.insee.fr/geo/communeAssociee/0447fef4-7543-486b-ba77-91f2263521b3,01324,0,Rignat,Rignat,1974-01-01,,\r\n"
                    + "http://id.insee.fr/geo/communeAssociee/a3777eee-2bd3-4558-b530-e07e4d5168a4,02285,1,Escaufourt,Escaufourt,1973-09-01,,\r\n"
                    + "http://id.insee.fr/geo/communeAssociee/59a155d3-8db8-4a55-a41d-fe0abc28f4e2,02300,0,Fargniers,Fargniers,1974-01-01,,";
    public static final String COMMUNE_ASSOCIEE_EXPECTED_RESPONSE_LISTE_TOP_JSON =
            "["
                    + "{\"code\":\"01120\",\"uri\":\"http://id.insee.fr/geo/communeAssociee/d4d9337b-25fe-4945-ba1c-14026d6f323b\",\"type\":\"CommuneAssociee\",\"dateCreation\":\"1973-01-01\",\"intituleSansArticle\":\"Cordieux\",\"typeArticle\":\"0\",\"intitule\":\"Cordieux\"},"
                    + "{\"code\":\"01324\",\"uri\":\"http://id.insee.fr/geo/communeAssociee/0447fef4-7543-486b-ba77-91f2263521b3\",\"type\":\"CommuneAssociee\",\"dateCreation\":\"1974-01-01\",\"intituleSansArticle\":\"Rignat\",\"typeArticle\":\"0\",\"intitule\":\"Rignat\"},"
                    + "{\"code\":\"02285\",\"uri\":\"http://id.insee.fr/geo/communeAssociee/a3777eee-2bd3-4558-b530-e07e4d5168a4\",\"type\":\"CommuneAssociee\",\"dateCreation\":\"1973-09-01\",\"intituleSansArticle\":\"Escaufourt\",\"typeArticle\":\"1\",\"intitule\":\"Escaufourt\"},"
                    + "{\"code\":\"02300\",\"uri\":\"http://id.insee.fr/geo/communeAssociee/59a155d3-8db8-4a55-a41d-fe0abc28f4e2\",\"type\":\"CommuneAssociee\",\"dateCreation\":\"1974-01-01\",\"intituleSansArticle\":\"Fargniers\",\"typeArticle\":\"0\",\"intitule\":\"Fargniers\"}"
                    + "]";
    public static final String COMMUNE_ASSOCIEE_EXPECTED_RESPONSE_LISTE_TOP_XML =
            "<CommunesAssociees>"
                    + "<CommuneAssociee code=\"01120\" uri=\"http://id.insee.fr/geo/communeAssociee/d4d9337b-25fe-4945-ba1c-14026d6f323b\"><Intitule>Cordieux</Intitule><Type>CommuneAssociee</Type><DateCreation>1973-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Cordieux</IntituleSansArticle></CommuneAssociee>"
                    + "<CommuneAssociee code=\"01324\" uri=\"http://id.insee.fr/geo/communeAssociee/0447fef4-7543-486b-ba77-91f2263521b3\"><Intitule>Rignat</Intitule><Type>CommuneAssociee</Type><DateCreation>1974-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Rignat</IntituleSansArticle></CommuneAssociee>"
                    + "<CommuneAssociee code=\"02285\" uri=\"http://id.insee.fr/geo/communeAssociee/a3777eee-2bd3-4558-b530-e07e4d5168a4\"><Intitule>Escaufourt</Intitule><Type>CommuneAssociee</Type><DateCreation>1973-09-01</DateCreation><IntituleSansArticle typeArticle=\"1\">Escaufourt</IntituleSansArticle></CommuneAssociee>"
                    + "<CommuneAssociee code=\"02300\" uri=\"http://id.insee.fr/geo/communeAssociee/59a155d3-8db8-4a55-a41d-fe0abc28f4e2\"><Intitule>Fargniers</Intitule><Type>CommuneAssociee</Type><DateCreation>1974-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Fargniers</IntituleSansArticle></CommuneAssociee>"
                    + "</CommunesAssociees>";
    public static final String COMMUNE_ASSOCIEE_MOCK_SERVER_RETURN_ASCENDANTS =
            "uri,code,type,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/arrondissement/bafd343b-c49a-4c79-a79a-349415d23127,012,http://rdf.insee.fr/def/geo#Arrondissement,0,Bourg-en-Bresse,Bourg-en-Bresse,2017-01-01,,01053\r\n"
                    + "http://id.insee.fr/geo/commune/10417a27-eae8-41b7-837c-28e1b415ef77,01262,http://rdf.insee.fr/def/geo#Commune,0,Montluel,Montluel,1973-01-01,,\r\n"
                    + "http://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc,01,http://rdf.insee.fr/def/geo#Departement,5,Ain,Ain,1943-01-01,,01053\r\n"
                    + "http://id.insee.fr/geo/region/6db44a21-5ae3-41b5-b16d-0ab30964f78d,84,http://rdf.insee.fr/def/geo#Region,1,Auvergne-Rhône-Alpes,Auvergne-Rhône-Alpes,2016-01-01,,69123\r\n";
    public static final String COMMUNE_ASSOCIEE_EXPECTED_RESPONSE_ASCENDANTS_JSON =
            "["
                    + "{\"code\":\"012\",\"uri\":\"http://id.insee.fr/geo/arrondissement/bafd343b-c49a-4c79-a79a-349415d23127\",\"type\":\"Arrondissement\",\"dateCreation\":\"2017-01-01\",\"intituleSansArticle\":\"Bourg-en-Bresse\",\"typeArticle\":\"0\",\"chefLieu\":\"01053\",\"intitule\":\"Bourg-en-Bresse\"},"
                    + "{\"code\":\"01262\",\"uri\":\"http://id.insee.fr/geo/commune/10417a27-eae8-41b7-837c-28e1b415ef77\",\"type\":\"Commune\",\"dateCreation\":\"1973-01-01\",\"intituleSansArticle\":\"Montluel\",\"typeArticle\":\"0\",\"intitule\":\"Montluel\"},"
                    + "{\"code\":\"01\",\"uri\":\"http://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc\",\"type\":\"Departement\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Ain\",\"typeArticle\":\"5\",\"chefLieu\":\"01053\",\"intitule\":\"Ain\"},"
                    + "{\"code\":\"84\",\"uri\":\"http://id.insee.fr/geo/region/6db44a21-5ae3-41b5-b16d-0ab30964f78d\",\"type\":\"Region\",\"dateCreation\":\"2016-01-01\",\"intituleSansArticle\":\"Auvergne-Rhône-Alpes\",\"typeArticle\":\"1\",\"chefLieu\":\"69123\",\"intitule\":\"Auvergne-Rhône-Alpes\"}"
                    + "]";
    public static final String COMMUNE_ASSOCIEE_EXPECTED_RESPONSE_ASCENDANTS_XML =
            "<Territoires>"
                    + "<Arrondissement code=\"012\" uri=\"http://id.insee.fr/geo/arrondissement/bafd343b-c49a-4c79-a79a-349415d23127\"><Intitule>Bourg-en-Bresse</Intitule><Type>Arrondissement</Type><DateCreation>2017-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Bourg-en-Bresse</IntituleSansArticle><ChefLieu>01053</ChefLieu></Arrondissement>"
                    + "<Commune code=\"01262\" uri=\"http://id.insee.fr/geo/commune/10417a27-eae8-41b7-837c-28e1b415ef77\"><Intitule>Montluel</Intitule><Type>Commune</Type><DateCreation>1973-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Montluel</IntituleSansArticle></Commune>"
                    + "<Departement code=\"01\" uri=\"http://id.insee.fr/geo/departement/f1d6d530-6cad-4e87-9e68-cdfaadd912bc\"><Intitule>Ain</Intitule><Type>Departement</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"5\">Ain</IntituleSansArticle><ChefLieu>01053</ChefLieu></Departement>"
                    + "<Region code=\"84\" uri=\"http://id.insee.fr/geo/region/6db44a21-5ae3-41b5-b16d-0ab30964f78d\"><Intitule>Auvergne-Rhône-Alpes</Intitule><Type>Region</Type><DateCreation>2016-01-01</DateCreation><IntituleSansArticle typeArticle=\"1\">Auvergne-Rhône-Alpes</IntituleSansArticle><ChefLieu>69123</ChefLieu></Region>"
                    + "</Territoires>";

    // **********************
    // ARRONDISSEMENT MUNICIPAL
    // **********************
    public static final String ARRONDISSEMENT_MUNICIPAL_MOCK_SERVER_RETURN_GET =
            "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/arrondissementMunicipal/05b3eb1f-7563-492e-bf33-0b3f481fff8c,75107,0,Paris 7e Arrondissement,Paris 7e Arrondissement,1943-01-01,,\r\n";
    public static final String ARRONDISSEMENT_MUNICIPAL_EXPECTED_RESPONSE_GET_JSON =
            "{\"code\":\"75107\",\"uri\":\"http://id.insee.fr/geo/arrondissementMunicipal/05b3eb1f-7563-492e-bf33-0b3f481fff8c\",\"type\":\"ArrondissementMunicipal\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Paris 7e Arrondissement\",\"typeArticle\":\"0\",\"intitule\":\"Paris 7e Arrondissement\"}";
    public static final String ARRONDISSEMENT_MUNICIPAL_EXPECTED_RESPONSE_GET_XML =
            "<ArrondissementMunicipal code=\"75107\" uri=\"http://id.insee.fr/geo/arrondissementMunicipal/05b3eb1f-7563-492e-bf33-0b3f481fff8c\"><Intitule>Paris 7e Arrondissement</Intitule><Type>ArrondissementMunicipal</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Paris 7e Arrondissement</IntituleSansArticle></ArrondissementMunicipal>";
    public static final String ARRONDISSEMENT_MUNICIPAL_MOCK_SERVER_RETURN_LISTE =
            "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/arrondissementMunicipal/12e426af-7d95-4349-bef4-424e02292ffe,13201,0,Marseille 1er Arrondissement,Marseille 1er Arrondissement,1946-10-18,,\r\n"
                    + "http://id.insee.fr/geo/arrondissementMunicipal/6cfdc10f-93ec-428a-8f25-15ae4a9f2c93,13202,0,Marseille 2e Arrondissement,Marseille 2e Arrondissement,1946-10-18,,\r\n"
                    + "http://id.insee.fr/geo/arrondissementMunicipal/0e41b1c6-5c50-42c9-a091-c47a3e01df72,13203,0,Marseille 3e Arrondissement,Marseille 3e Arrondissement,1946-10-18,,\r\n"
                    + "http://id.insee.fr/geo/arrondissementMunicipal/68e37efb-59b2-4e17-955d-a80e107ec5f4,13204,0,Marseille 4e Arrondissement,Marseille 4e Arrondissement,1946-10-18,,";
    public static final String ARRONDISSEMENT_MUNICIPAL_EXPECTED_RESPONSE_LISTE_TOP_JSON =
            "["
                    + "{\"code\":\"13201\",\"uri\":\"http://id.insee.fr/geo/arrondissementMunicipal/12e426af-7d95-4349-bef4-424e02292ffe\",\"type\":\"ArrondissementMunicipal\",\"dateCreation\":\"1946-10-18\",\"intituleSansArticle\":\"Marseille 1er Arrondissement\",\"typeArticle\":\"0\",\"intitule\":\"Marseille 1er Arrondissement\"},"
                    + "{\"code\":\"13202\",\"uri\":\"http://id.insee.fr/geo/arrondissementMunicipal/6cfdc10f-93ec-428a-8f25-15ae4a9f2c93\",\"type\":\"ArrondissementMunicipal\",\"dateCreation\":\"1946-10-18\",\"intituleSansArticle\":\"Marseille 2e Arrondissement\",\"typeArticle\":\"0\",\"intitule\":\"Marseille 2e Arrondissement\"},"
                    + "{\"code\":\"13203\",\"uri\":\"http://id.insee.fr/geo/arrondissementMunicipal/0e41b1c6-5c50-42c9-a091-c47a3e01df72\",\"type\":\"ArrondissementMunicipal\",\"dateCreation\":\"1946-10-18\",\"intituleSansArticle\":\"Marseille 3e Arrondissement\",\"typeArticle\":\"0\",\"intitule\":\"Marseille 3e Arrondissement\"},"
                    + "{\"code\":\"13204\",\"uri\":\"http://id.insee.fr/geo/arrondissementMunicipal/68e37efb-59b2-4e17-955d-a80e107ec5f4\",\"type\":\"ArrondissementMunicipal\",\"dateCreation\":\"1946-10-18\",\"intituleSansArticle\":\"Marseille 4e Arrondissement\",\"typeArticle\":\"0\",\"intitule\":\"Marseille 4e Arrondissement\"}"
                    + "]";
    public static final String ARRONDISSEMENT_MUNICIPAL_EXPECTED_RESPONSE_LISTE_TOP_XML =
            "<ArrondissementsMunicipaux>"
                    + "<ArrondissementMunicipal code=\"13201\" uri=\"http://id.insee.fr/geo/arrondissementMunicipal/12e426af-7d95-4349-bef4-424e02292ffe\"><Intitule>Marseille 1er Arrondissement</Intitule><Type>ArrondissementMunicipal</Type><DateCreation>1946-10-18</DateCreation><IntituleSansArticle typeArticle=\"0\">Marseille 1er Arrondissement</IntituleSansArticle></ArrondissementMunicipal>"
                    + "<ArrondissementMunicipal code=\"13202\" uri=\"http://id.insee.fr/geo/arrondissementMunicipal/6cfdc10f-93ec-428a-8f25-15ae4a9f2c93\"><Intitule>Marseille 2e Arrondissement</Intitule><Type>ArrondissementMunicipal</Type><DateCreation>1946-10-18</DateCreation><IntituleSansArticle typeArticle=\"0\">Marseille 2e Arrondissement</IntituleSansArticle></ArrondissementMunicipal>"
                    + "<ArrondissementMunicipal code=\"13203\" uri=\"http://id.insee.fr/geo/arrondissementMunicipal/0e41b1c6-5c50-42c9-a091-c47a3e01df72\"><Intitule>Marseille 3e Arrondissement</Intitule><Type>ArrondissementMunicipal</Type><DateCreation>1946-10-18</DateCreation><IntituleSansArticle typeArticle=\"0\">Marseille 3e Arrondissement</IntituleSansArticle></ArrondissementMunicipal>"
                    + "<ArrondissementMunicipal code=\"13204\" uri=\"http://id.insee.fr/geo/arrondissementMunicipal/68e37efb-59b2-4e17-955d-a80e107ec5f4\"><Intitule>Marseille 4e Arrondissement</Intitule><Type>ArrondissementMunicipal</Type><DateCreation>1946-10-18</DateCreation><IntituleSansArticle typeArticle=\"0\">Marseille 4e Arrondissement</IntituleSansArticle></ArrondissementMunicipal>"
                    + "</ArrondissementsMunicipaux>";
    public static final String ARRONDISSEMENT_MUNICIPAL_MOCK_SERVER_RETURN_ASCENDANTS =
            "uri,code,type,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/arrondissement/0f7adb14-b9a3-4d32-a988-2a354ccd816a,751,http://rdf.insee.fr/def/geo#Arrondissement,0,Paris,Paris,1993-01-01,,75056\r\n"
                    + "http://id.insee.fr/geo/commune/13f2d896-0276-4902-a1a0-aef01746068e,75056,http://rdf.insee.fr/def/geo#Commune,0,Paris,Paris,1943-01-01,,\r\n"
                    + "http://id.insee.fr/geo/departement/aabe2556-309c-4a9b-b2e7-ae6668d36c5b,75,http://rdf.insee.fr/def/geo#Departement,0,Paris,Paris,1968-01-01,,75056\r\n"
                    + "http://id.insee.fr/geo/region/19325a20-8819-4c91-98e4-3060727e3d41,11,http://rdf.insee.fr/def/geo#Region,1,Île-de-France,Île-de-France,1982-03-02,,75056\r\n";
    public static final String ARRONDISSEMENT_MUNICIPAL_EXPECTED_RESPONSE_ASCENDANTS_JSON =
            "["
                    + "{\"code\":\"751\",\"uri\":\"http://id.insee.fr/geo/arrondissement/0f7adb14-b9a3-4d32-a988-2a354ccd816a\",\"type\":\"Arrondissement\",\"dateCreation\":\"1993-01-01\",\"intituleSansArticle\":\"Paris\",\"typeArticle\":\"0\",\"chefLieu\":\"75056\",\"intitule\":\"Paris\"},"
                    + "{\"code\":\"75056\",\"uri\":\"http://id.insee.fr/geo/commune/13f2d896-0276-4902-a1a0-aef01746068e\",\"type\":\"Commune\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Paris\",\"typeArticle\":\"0\",\"intitule\":\"Paris\"},"
                    + "{\"code\":\"75\",\"uri\":\"http://id.insee.fr/geo/departement/aabe2556-309c-4a9b-b2e7-ae6668d36c5b\",\"type\":\"Departement\",\"dateCreation\":\"1968-01-01\",\"intituleSansArticle\":\"Paris\",\"typeArticle\":\"0\",\"chefLieu\":\"75056\",\"intitule\":\"Paris\"},"
                    + "{\"code\":\"11\",\"uri\":\"http://id.insee.fr/geo/region/19325a20-8819-4c91-98e4-3060727e3d41\",\"type\":\"Region\",\"dateCreation\":\"1982-03-02\",\"intituleSansArticle\":\"Île-de-France\",\"typeArticle\":\"1\",\"chefLieu\":\"75056\",\"intitule\":\"Île-de-France\"}"
                    + "]";
    public static final String ARRONDISSEMENT_MUNICIPAL_EXPECTED_RESPONSE_ASCENDANTS_XML =
            "<Territoires>"
                    + "<Arrondissement code=\"751\" uri=\"http://id.insee.fr/geo/arrondissement/0f7adb14-b9a3-4d32-a988-2a354ccd816a\"><Intitule>Paris</Intitule><Type>Arrondissement</Type><DateCreation>1993-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Paris</IntituleSansArticle><ChefLieu>75056</ChefLieu></Arrondissement>"
                    + "<Commune code=\"75056\" uri=\"http://id.insee.fr/geo/commune/13f2d896-0276-4902-a1a0-aef01746068e\"><Intitule>Paris</Intitule><Type>Commune</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Paris</IntituleSansArticle></Commune>"
                    + "<Departement code=\"75\" uri=\"http://id.insee.fr/geo/departement/aabe2556-309c-4a9b-b2e7-ae6668d36c5b\"><Intitule>Paris</Intitule><Type>Departement</Type><DateCreation>1968-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Paris</IntituleSansArticle><ChefLieu>75056</ChefLieu></Departement>"
                    + "<Region code=\"11\" uri=\"http://id.insee.fr/geo/region/19325a20-8819-4c91-98e4-3060727e3d41\"><Intitule>Île-de-France</Intitule><Type>Region</Type><DateCreation>1982-03-02</DateCreation><IntituleSansArticle typeArticle=\"1\">Île-de-France</IntituleSansArticle><ChefLieu>75056</ChefLieu></Region>"
                    + "</Territoires>";
    public static final String ARRONDISSEMENT_MUNICIPAL_MOCK_SERVER_RETURN_PRECEDENTS =
            "uri,type,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/arrondissementMunicipal/c8ad8e72-685d-4b21-b3e7-1a014f940400,ArrondissementMunicipal,69385,0,Lyon 5e Arrondissement,Lyon 5e Arrondissement,1943-01-01,1964-08-12,\r\n";
    public static final String ARRONDISSEMENT_MUNICIPAL_EXPECTED_RESPONSE_PRECEDENTS_JSON =
            "["
                    + "{\"code\":\"69385\",\"uri\":\"http://id.insee.fr/geo/arrondissementMunicipal/c8ad8e72-685d-4b21-b3e7-1a014f940400\",\"type\":\"ArrondissementMunicipal\",\"dateCreation\":\"1943-01-01\",\"dateSuppression\":\"1964-08-12\",\"intituleSansArticle\":\"Lyon 5e Arrondissement\",\"typeArticle\":\"0\",\"intitule\":\"Lyon 5e Arrondissement\"}"
                    + "]";
    public static final String ARRONDISSEMENT_MUNICIPAL_EXPECTED_RESPONSE_PRECEDENTS_XML =
            "<ArrondissementsMunicipaux>"
                    + "<ArrondissementMunicipal code=\"69385\" uri=\"http://id.insee.fr/geo/arrondissementMunicipal/c8ad8e72-685d-4b21-b3e7-1a014f940400\"><Intitule>Lyon 5e Arrondissement</Intitule><Type>ArrondissementMunicipal</Type><DateCreation>1943-01-01</DateCreation><DateSuppression>1964-08-12</DateSuppression><IntituleSansArticle typeArticle=\"0\">Lyon 5e Arrondissement</IntituleSansArticle></ArrondissementMunicipal>"
                    + "</ArrondissementsMunicipaux>";
    public static final String ARRONDISSEMENT_MUNICIPAL_EXPECTED_RESPONSE_SUIVANTS_JSON =
            "["
                    + "{\"code\":\"69385\",\"uri\":\"http://id.insee.fr/geo/arrondissementMunicipal/c8ad8e72-685d-4b21-b3e7-1a014f940400\",\"type\":\"ArrondissementMunicipal\",\"dateCreation\":\"1943-01-01\",\"dateSuppression\":\"1964-08-12\",\"intituleSansArticle\":\"Lyon 5e Arrondissement\",\"typeArticle\":\"0\",\"intitule\":\"Lyon 5e Arrondissement\"}"
                    + "]";
    public static final String ARRONDISSEMENT_MUNICIPAL_EXPECTED_RESPONSE_SUIVANTS_XML =
            "<ArrondissementsMunicipaux>"
                    + "<ArrondissementMunicipal code=\"69385\" uri=\"http://id.insee.fr/geo/arrondissementMunicipal/c8ad8e72-685d-4b21-b3e7-1a014f940400\"><Intitule>Lyon 5e Arrondissement</Intitule><Type>ArrondissementMunicipal</Type><DateCreation>1943-01-01</DateCreation><DateSuppression>1964-08-12</DateSuppression><IntituleSansArticle typeArticle=\"0\">Lyon 5e Arrondissement</IntituleSansArticle></ArrondissementMunicipal>"
                    + "</ArrondissementsMunicipaux>";

    // **********************
    // COMMUNE DELEGUEE
    // **********************

    public static final String COMMUNE_DELEGUEE_MOCK_SERVER_RETURN_GET =
            "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/communeDeleguee/0028aa0b-7e3a-488a-8cd0-18baa0038c0e,50564,0,Sainteny,Sainteny,2016-01-01,,\r\n";

    public static final String COMMUNE_DELEGUEE_EXPECTED_RESPONSE_GET_JSON =
            "{\"code\":\"50564\",\"uri\":\"http://id.insee.fr/geo/communeDeleguee/0028aa0b-7e3a-488a-8cd0-18baa0038c0e\",\"type\":\"CommuneDeleguee\",\"dateCreation\":\"2016-01-01\",\"intituleSansArticle\":\"Sainteny\",\"typeArticle\":\"0\",\"intitule\":\"Sainteny\"}";

    public static final String COMMUNE_DELEGUEE_EXPECTED_RESPONSE_GET_XML =
            "<CommuneDeleguee code=\"50564\" uri=\"http://id.insee.fr/geo/communeDeleguee/0028aa0b-7e3a-488a-8cd0-18baa0038c0e\"><Intitule>Sainteny</Intitule><Type>CommuneDeleguee</Type><DateCreation>2016-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Sainteny</IntituleSansArticle></CommuneDeleguee>";

    public static final String COMMUNE_DELEGUEE_MOCK_SERVER_RETURN_LISTE =
            "uri,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/communeDeleguee/641df128-1268-495a-900d-41ab5bbeccff,01015,1,Arbignieu,Arbignieu,2016-01-01,,\r\n"
                    + "http://id.insee.fr/geo/communeDeleguee/6f68d69d-37b8-4a4d-9396-e44cb29e2196,01025,0,Bâgé-la-Ville,Bâgé-la-Ville,2018-01-01,,\r\n"
                    + "http://id.insee.fr/geo/communeDeleguee/22761b1a-5c65-432b-ba46-14f7db70e9d5,01033,0,Bellegarde-sur-Valserine,Bellegarde-sur-Valserine,2019-01-01,,\r\n"
                    + "http://id.insee.fr/geo/communeDeleguee/332702ee-93ad-4895-87cf-11f54603bd50,01036,0,Belmont-Luthézieu,Belmont-Luthézieu,2019-01-01,,";

    public static final String COMMUNE_DELEGUEE_EXPECTED_RESPONSE_LISTE_TOP_JSON =
            "["
                    + "{\"code\":\"01015\",\"uri\":\"http://id.insee.fr/geo/communeDeleguee/641df128-1268-495a-900d-41ab5bbeccff\",\"type\":\"CommuneDeleguee\",\"dateCreation\":\"2016-01-01\",\"intituleSansArticle\":\"Arbignieu\",\"typeArticle\":\"1\",\"intitule\":\"Arbignieu\"},"
                    + "{\"code\":\"01025\",\"uri\":\"http://id.insee.fr/geo/communeDeleguee/6f68d69d-37b8-4a4d-9396-e44cb29e2196\",\"type\":\"CommuneDeleguee\",\"dateCreation\":\"2018-01-01\",\"intituleSansArticle\":\"Bâgé-la-Ville\",\"typeArticle\":\"0\",\"intitule\":\"Bâgé-la-Ville\"},"
                    + "{\"code\":\"01033\",\"uri\":\"http://id.insee.fr/geo/communeDeleguee/22761b1a-5c65-432b-ba46-14f7db70e9d5\",\"type\":\"CommuneDeleguee\",\"dateCreation\":\"2019-01-01\",\"intituleSansArticle\":\"Bellegarde-sur-Valserine\",\"typeArticle\":\"0\",\"intitule\":\"Bellegarde-sur-Valserine\"},"
                    + "{\"code\":\"01036\",\"uri\":\"http://id.insee.fr/geo/communeDeleguee/332702ee-93ad-4895-87cf-11f54603bd50\",\"type\":\"CommuneDeleguee\",\"dateCreation\":\"2019-01-01\",\"intituleSansArticle\":\"Belmont-Luthézieu\",\"typeArticle\":\"0\",\"intitule\":\"Belmont-Luthézieu\"}"
                    + "]";

    public static final String COMMUNE_DELEGUEE_EXPECTED_RESPONSE_LISTE_TOP_XML =
            "<CommunesDeleguees>"
                    + "<CommuneDeleguee code=\"01015\" uri=\"http://id.insee.fr/geo/communeDeleguee/641df128-1268-495a-900d-41ab5bbeccff\"><Intitule>Arbignieu</Intitule><Type>CommuneDeleguee</Type><DateCreation>2016-01-01</DateCreation><IntituleSansArticle typeArticle=\"1\">Arbignieu</IntituleSansArticle></CommuneDeleguee>"
                    + "<CommuneDeleguee code=\"01025\" uri=\"http://id.insee.fr/geo/communeDeleguee/6f68d69d-37b8-4a4d-9396-e44cb29e2196\"><Intitule>Bâgé-la-Ville</Intitule><Type>CommuneDeleguee</Type><DateCreation>2018-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Bâgé-la-Ville</IntituleSansArticle></CommuneDeleguee>"
                    + "<CommuneDeleguee code=\"01033\" uri=\"http://id.insee.fr/geo/communeDeleguee/22761b1a-5c65-432b-ba46-14f7db70e9d5\"><Intitule>Bellegarde-sur-Valserine</Intitule><Type>CommuneDeleguee</Type><DateCreation>2019-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Bellegarde-sur-Valserine</IntituleSansArticle></CommuneDeleguee>"
                    + "<CommuneDeleguee code=\"01036\" uri=\"http://id.insee.fr/geo/communeDeleguee/332702ee-93ad-4895-87cf-11f54603bd50\"><Intitule>Belmont-Luthézieu</Intitule><Type>CommuneDeleguee</Type><DateCreation>2019-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Belmont-Luthézieu</IntituleSansArticle></CommuneDeleguee>"
                    + "</CommunesDeleguees>";

    public static final String COMMUNE_DELEGUEE_MOCK_SERVER_RETURN_ASCENDANTS =
            "uri,code,type,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu\r\n"
                    + "http://id.insee.fr/geo/arrondissement/e5baa41e-009d-40d0-87ab-9c6615b13f44,504,http://rdf.insee.fr/def/geo#Arrondissement,0,Saint-Lô,Saint-Lô,2019-01-01,,50502\r\n"
                    + "http://id.insee.fr/geo/commune/c160c88a-4359-4754-9ce5-e77ddf93abb1,50564,http://rdf.insee.fr/def/geo#Commune,0,Terre-et-Marais,Terre-et-Marais,2016-01-01,,\r\n"
                    + "http://id.insee.fr/geo/departement/de67dbb1-3cf4-44bc-8221-49ece0fe1128,50,http://rdf.insee.fr/def/geo#Departement,3,Manche,Manche,1943-01-01,,50502\r\n"
                    + "http://id.insee.fr/geo/region/de9ed89d-ec4d-453c-952e-f76f4426fe0c,28,http://rdf.insee.fr/def/geo#Region,0,Normandie,Normandie,2016-01-01,,76540\r\n";

    public static final String COMMUNE_DELEGUEE_EXPECTED_RESPONSE_ASCENDANTS_JSON =
            "["
                    + "{\"code\":\"504\",\"uri\":\"http://id.insee.fr/geo/arrondissement/e5baa41e-009d-40d0-87ab-9c6615b13f44\",\"type\":\"Arrondissement\",\"dateCreation\":\"2019-01-01\",\"intituleSansArticle\":\"Saint-Lô\",\"typeArticle\":\"0\",\"chefLieu\":\"50502\",\"intitule\":\"Saint-Lô\"},"
                    + "{\"code\":\"50564\",\"uri\":\"http://id.insee.fr/geo/commune/c160c88a-4359-4754-9ce5-e77ddf93abb1\",\"type\":\"Commune\",\"dateCreation\":\"2016-01-01\",\"intituleSansArticle\":\"Terre-et-Marais\",\"typeArticle\":\"0\",\"intitule\":\"Terre-et-Marais\"},"
                    + "{\"code\":\"50\",\"uri\":\"http://id.insee.fr/geo/departement/de67dbb1-3cf4-44bc-8221-49ece0fe1128\",\"type\":\"Departement\",\"dateCreation\":\"1943-01-01\",\"intituleSansArticle\":\"Manche\",\"typeArticle\":\"3\",\"chefLieu\":\"50502\",\"intitule\":\"Manche\"},"
                    + "{\"code\":\"28\",\"uri\":\"http://id.insee.fr/geo/region/de9ed89d-ec4d-453c-952e-f76f4426fe0c\",\"type\":\"Region\",\"dateCreation\":\"2016-01-01\",\"intituleSansArticle\":\"Normandie\",\"typeArticle\":\"0\",\"chefLieu\":\"76540\",\"intitule\":\"Normandie\"}"
                    + "]";

    public static final String COMMUNE_DELEGUEE_EXPECTED_RESPONSE_ASCENDANTS_XML =
            "<Territoires>"
                    + "<Arrondissement code=\"504\" uri=\"http://id.insee.fr/geo/arrondissement/e5baa41e-009d-40d0-87ab-9c6615b13f44\"><Intitule>Saint-Lô</Intitule><Type>Arrondissement</Type><DateCreation>2019-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Saint-Lô</IntituleSansArticle><ChefLieu>50502</ChefLieu></Arrondissement>"
                    + "<Commune code=\"50564\" uri=\"http://id.insee.fr/geo/commune/c160c88a-4359-4754-9ce5-e77ddf93abb1\"><Intitule>Terre-et-Marais</Intitule><Type>Commune</Type><DateCreation>2016-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Terre-et-Marais</IntituleSansArticle></Commune>"
                    + "<Departement code=\"50\" uri=\"http://id.insee.fr/geo/departement/de67dbb1-3cf4-44bc-8221-49ece0fe1128\"><Intitule>Manche</Intitule><Type>Departement</Type><DateCreation>1943-01-01</DateCreation><IntituleSansArticle typeArticle=\"3\">Manche</IntituleSansArticle><ChefLieu>50502</ChefLieu></Departement>"
                    + "<Region code=\"28\" uri=\"http://id.insee.fr/geo/region/de9ed89d-ec4d-453c-952e-f76f4426fe0c\"><Intitule>Normandie</Intitule><Type>Region</Type><DateCreation>2016-01-01</DateCreation><IntituleSansArticle typeArticle=\"0\">Normandie</IntituleSansArticle><ChefLieu>76540</ChefLieu></Region>"
                    + "</Territoires>";

    // **********************
    // PAYS
    // **********************
    public static final String PAYS_MOCK_SERVER_RETURN_GET =
            "uri,intitule,intituleEntier\r\n" + "http://id.insee.fr/geo/pays/99350,MAROC,ROYAUME DU MAROC\r\n";

    public static final String PAYS_EXPECTED_RESPONSE_GET_JSON =
            "{\"code\":\"99350\",\"uri\":\"http://id.insee.fr/geo/pays/99350\",\"intitule\":\"MAROC\",\"intituleEntier\":\"ROYAUME DU MAROC\"}";

    public static final String PAYS_EXPECTED_RESPONSE_GET_XML =
            "<Pays code=\"99350\" uri=\"http://id.insee.fr/geo/pays/99350\"><Intitule>MAROC</Intitule><IntituleEntier>ROYAUME DU MAROC</IntituleEntier></Pays>";

    public final static String CANTON_MOCK_SERVER_RETURN_GET =
            "code,uri,type,dateCreation,intitule,intituleSansArticle,typeArticle,ChefLieu\r\n"
                    + "0105,http://id.insee.fr/geo/canton/622ce50c-2ff4-470d-a0f3-f85baa8813a7,Canton,2016-01-01,Bourg-en-Bresse-1,Bourg-en-Bresse-1,0\r\n";


    public final static String CANTON_EXPECTED_RESPONSE_GET_JSON =
            "{\"code\":\"0105\",\"uri\":\"http://id.insee.fr/geo/canton/622ce50c-2ff4-470d-a0f3-f85baa8813a7\",\"type\":\"Canton\",\"dateCreation\":\"2016-01-01\",\"intitule\":\"Bourg-en-Bresse-1\",\"intituleSansArticle\":\"Bourg-en-Bresse-1\",\"typeArticle\":\"0\"}";

    public final static String CANTON_EXPECTED_RESPONSE_GET_XML =
            "<Canton code=\"0105\" uri=\"http://id.insee.fr/geo/canton/622ce50c-2ff4-470d-a0f3-f85baa8813a7\">" +
                    "<Intitule>Bourg-en-Bresse-1</Intitule>" +
                    "<Type>Canton</Type>" +
                    "<DateCreation>2016-01-01</DateCreation>" +
                    "<IntituleSansArticle typeArticle=\"0\">Bourg-en-Bresse-1</IntituleSansArticle>" +
                    "</Canton>";

    public static final String COMMUNES_FROM_CANTON_MOCK_SERVER_RETURN_GET = "uri,type,code,typeArticle,intitule,intituleSansArticle,inclusion,dateCreation,dateSuppression\n" +
            "http://id.insee.fr/geo/commune/c1e73781-7e69-4212-9645-a525ad190b22,Commune,33273,0,Martignas-sur-Jalle,Martignas-sur-Jalle,totale,1943-01-01,\n" +
            "http://id.insee.fr/geo/commune/aa63f8a3-49a2-41e3-a039-36dc639be4a1,Commune,33281,0,Mérignac,Mérignac,partielle,1943-01-01,\n" +
            "http://id.insee.fr/geo/commune/dbab54d2-ea7e-4117-aaa9-4ea1a7110749,Commune,33422,0,Saint-Jean-d'Illac,Saint-Jean-d'Illac,totale,1943-01-01,";

    public static final String COMMUNES_INCLUSES_DATEES1991_MOCK_SPARQL_RESPONSE = "uri,type,code,typeArticle,intitule,intituleSansArticle,inclusion,dateCreation,dateSuppression\n"
            /*+"http://id.insee.fr/geo/commune/494368e6-fab5-45dd-9e07-53c491a859ba,Commune,59368,3,La Madeleine,Madeleine,totale,1943-01-01,\n" +
            "http://id.insee.fr/geo/commune/344da3cb-6721-4c90-a5ab-03073ecdb42a,Commune,59386,0,Marquette-lez-Lille,Marquette-lez-Lille,totale,1962-01-28,\n" +
            "http://id.insee.fr/geo/commune/c9de9f70-fd1f-4a03-9c46-aaa1a8a5209e,Commune,59636,0,Wambrechies,Wambrechies,totale,1943-01-01,\n"*/;
    public static final String COMMUNES_INCLUSES_DATEES2000_MOCK_SPARQL_RESPONSE = "uri,type,code,typeArticle,intitule,intituleSansArticle,inclusion,dateCreation,dateSuppression\n"
            /*+ "http://id.insee.fr/geo/commune/c3c07f12-84f4-40f8-b7de-1cc5530ff67c,Commune,59350,0,Lille,Lille,partielle,2000-02-27,\n" +
            "http://id.insee.fr/geo/commune/494368e6-fab5-45dd-9e07-53c491a859ba,Commune,59368,3,La Madeleine,Madeleine,totale,1943-01-01,\n" +
            "http://id.insee.fr/geo/commune/344da3cb-6721-4c90-a5ab-03073ecdb42a,Commune,59386,0,Marquette-lez-Lille,Marquette-lez-Lille,totale,1962-01-28,\n" +
            "http://id.insee.fr/geo/commune/f2ff3651-332d-4c4a-9346-f3fbaf1cc555,Commune,59527,0,Saint-André-lez-Lille,Saint-André-lez-Lille,totale,1991-12-20,\n" +
            "http://id.insee.fr/geo/commune/c9de9f70-fd1f-4a03-9c46-aaa1a8a5209e,Commune,59636,0,Wambrechies,Wambrechies,totale,1943-01-01,\n"*/;
    public static final String COMMUNES_INCLUSES_MOCK_SPARQL_RESPONSE = "uri,type,code,typeArticle,intitule,intituleSansArticle,inclusion,dateCreation,dateSuppression\n" +
            "http://id.insee.fr/geo/commune/c3c07f12-84f4-40f8-b7de-1cc5530ff67c,Commune,59350,0,Lille,Lille,partielle,2000-02-27,\n" +
            "http://id.insee.fr/geo/commune/494368e6-fab5-45dd-9e07-53c491a859ba,Commune,59368,3,La Madeleine,Madeleine,totale,1943-01-01,\n" +
            "http://id.insee.fr/geo/commune/344da3cb-6721-4c90-a5ab-03073ecdb42a,Commune,59386,0,Marquette-lez-Lille,Marquette-lez-Lille,totale,1962-01-28,\n" +
            "http://id.insee.fr/geo/commune/f2ff3651-332d-4c4a-9346-f3fbaf1cc555,Commune,59527,0,Saint-André-lez-Lille,Saint-André-lez-Lille,totale,1991-12-20,\n" +
            "http://id.insee.fr/geo/commune/c9de9f70-fd1f-4a03-9c46-aaa1a8a5209e,Commune,59636,0,Wambrechies,Wambrechies,totale,1943-01-01,\n";

    public static final String COMMUNES_INCLUSES_DATEES1991_RESPONSE_GET_JSON="[\n" +
            "\t{\n" +
            "\t\t\"code\": \"59368\",\n" +
            "\t\t\"uri\": \"http://id.insee.fr/geo/commune/494368e6-fab5-45dd-9e07-53c491a859ba\",\n" +
            "\t\t\"type\": \"Commune\",\n" +
            "\t\t\"intitule\": \"La Madeleine\",\n" +
            "\t\t\"intituleSansArticle\": \"Madeleine\",\n" +
            "\t\t\"typeArticle\": \"3\",\n" +
            "\t\t\"inclusion\": \"totale\",\n" +
            "\t\t\"dateCreation\": \"1943-01-01\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"code\": \"59386\",\n" +
            "\t\t\"uri\": \"http://id.insee.fr/geo/commune/344da3cb-6721-4c90-a5ab-03073ecdb42a\",\n" +
            "\t\t\"type\": \"Commune\",\n" +
            "\t\t\"intitule\": \"Marquette-lez-Lille\",\n" +
            "\t\t\"intituleSansArticle\": \"Marquette-lez-Lille\",\n" +
            "\t\t\"typeArticle\": \"0\",\n" +
            "\t\t\"inclusion\": \"totale\",\n" +
            "\t\t\"dateCreation\": \"1962-01-28\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"code\": \"59636\",\n" +
            "\t\t\"uri\": \"http://id.insee.fr/geo/commune/c9de9f70-fd1f-4a03-9c46-aaa1a8a5209e\",\n" +
            "\t\t\"type\": \"Commune\",\n" +
            "\t\t\"intitule\": \"Wambrechies\",\n" +
            "\t\t\"intituleSansArticle\": \"Wambrechies\",\n" +
            "\t\t\"typeArticle\": \"0\",\n" +
            "\t\t\"inclusion\": \"totale\",\n" +
            "\t\t\"dateCreation\": \"1943-01-01\"\n" +
            "\t}\n" +
            "]";

    public static final String COMMUNES_INCLUSES_DATEES2000_RESPONSE_GET_JSON="[\n" +
            "\t{\n" +
            "\t\t\"code\": \"59350\",\n" +
            "\t\t\"uri\": \"http://id.insee.fr/geo/commune/c3c07f12-84f4-40f8-b7de-1cc5530ff67c\",\n" +
            "\t\t\"type\": \"Commune\",\n" +
            "\t\t\"intitule\": \"Lille\",\n" +
            "\t\t\"intituleSansArticle\": \"Lille\",\n" +
            "\t\t\"typeArticle\": \"0\",\n" +
            "\t\t\"inclusion\": \"partielle\",\n" +
            "\t\t\"dateCreation\": \"2000-02-27\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"code\": \"59368\",\n" +
            "\t\t\"uri\": \"http://id.insee.fr/geo/commune/494368e6-fab5-45dd-9e07-53c491a859ba\",\n" +
            "\t\t\"type\": \"Commune\",\n" +
            "\t\t\"intitule\": \"La Madeleine\",\n" +
            "\t\t\"intituleSansArticle\": \"Madeleine\",\n" +
            "\t\t\"typeArticle\": \"3\",\n" +
            "\t\t\"inclusion\": \"totale\",\n" +
            "\t\t\"dateCreation\": \"1943-01-01\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"code\": \"59386\",\n" +
            "\t\t\"uri\": \"http://id.insee.fr/geo/commune/344da3cb-6721-4c90-a5ab-03073ecdb42a\",\n" +
            "\t\t\"type\": \"Commune\",\n" +
            "\t\t\"intitule\": \"Marquette-lez-Lille\",\n" +
            "\t\t\"intituleSansArticle\": \"Marquette-lez-Lille\",\n" +
            "\t\t\"typeArticle\": \"0\",\n" +
            "\t\t\"inclusion\": \"totale\",\n" +
            "\t\t\"dateCreation\": \"1962-01-28\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"code\": \"59527\",\n" +
            "\t\t\"uri\": \"http://id.insee.fr/geo/commune/f2ff3651-332d-4c4a-9346-f3fbaf1cc555\",\n" +
            "\t\t\"type\": \"Commune\",\n" +
            "\t\t\"intitule\": \"Saint-André-lez-Lille\",\n" +
            "\t\t\"intituleSansArticle\": \"Saint-André-lez-Lille\",\n" +
            "\t\t\"typeArticle\": \"0\",\n" +
            "\t\t\"inclusion\": \"totale\",\n" +
            "\t\t\"dateCreation\": \"1991-12-20\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"code\": \"59636\",\n" +
            "\t\t\"uri\": \"http://id.insee.fr/geo/commune/c9de9f70-fd1f-4a03-9c46-aaa1a8a5209e\",\n" +
            "\t\t\"type\": \"Commune\",\n" +
            "\t\t\"intitule\": \"Wambrechies\",\n" +
            "\t\t\"intituleSansArticle\": \"Wambrechies\",\n" +
            "\t\t\"typeArticle\": \"0\",\n" +
            "\t\t\"inclusion\": \"totale\",\n" +
            "\t\t\"dateCreation\": \"1943-01-01\"\n" +
            "\t}\n" +
            "]";

    public static final String COMMUNES_INCLUSES_EXPECTED_RESPONSE_GET_JSON = COMMUNES_INCLUSES_DATEES2000_RESPONSE_GET_JSON;

    public static final String COMMUNES_INCLUSES_RESPONSE_GET_XML="<Communes>\n" +
            "    <Commune code=\"59350\" uri=\"http://id.insee.fr/geo/commune/c3c07f12-84f4-40f8-b7de-1cc5530ff67c\">\n" +
            "        <Type>Commune</Type>\n" +
            "        <Intitule>Lille</Intitule>\n" +
            "        <IntituleSansArticle typeArticle=\"0\">Lille</IntituleSansArticle>\n" +
            "        <Inclusion>partielle</Inclusion>\n" +
            "        <DateCreation>2000-02-27</DateCreation>\n" +
            "    </Commune>\n" +
            "    <Commune code=\"59368\" uri=\"http://id.insee.fr/geo/commune/494368e6-fab5-45dd-9e07-53c491a859ba\">\n" +
            "        <Type>Commune</Type>\n" +
            "        <Intitule>La Madeleine</Intitule>\n" +
            "        <IntituleSansArticle typeArticle=\"3\">Madeleine</IntituleSansArticle>\n" +
            "        <Inclusion>totale</Inclusion>\n" +
            "        <DateCreation>1943-01-01</DateCreation>\n" +
            "    </Commune>\n" +
            "    <Commune code=\"59386\" uri=\"http://id.insee.fr/geo/commune/344da3cb-6721-4c90-a5ab-03073ecdb42a\">\n" +
            "        <Type>Commune</Type>\n" +
            "        <Intitule>Marquette-lez-Lille</Intitule>\n" +
            "        <IntituleSansArticle typeArticle=\"0\">Marquette-lez-Lille</IntituleSansArticle>\n" +
            "        <Inclusion>totale</Inclusion>\n" +
            "        <DateCreation>1962-01-28</DateCreation>\n" +
            "    </Commune>\n" +
            "    <Commune code=\"59527\" uri=\"http://id.insee.fr/geo/commune/f2ff3651-332d-4c4a-9346-f3fbaf1cc555\">\n" +
            "        <Type>Commune</Type>\n" +
            "        <Intitule>Saint-André-lez-Lille</Intitule>\n" +
            "        <IntituleSansArticle typeArticle=\"0\">Saint-André-lez-Lille</IntituleSansArticle>\n" +
            "        <Inclusion>totale</Inclusion>\n" +
            "        <DateCreation>1991-12-20</DateCreation>\n" +
            "    </Commune>\n" +
            "    <Commune code=\"59636\" uri=\"http://id.insee.fr/geo/commune/c9de9f70-fd1f-4a03-9c46-aaa1a8a5209e\">\n" +
            "        <Type>Commune</Type>\n" +
            "        <Intitule>Wambrechies</Intitule>\n" +
            "        <IntituleSansArticle typeArticle=\"0\">Wambrechies</IntituleSansArticle>\n" +
            "        <Inclusion>totale</Inclusion>\n" +
            "        <DateCreation>1943-01-01</DateCreation>\n" +
            "    </Commune>\n" +
            "</Communes>";
    public static final String COMMUNE_MOCK_SERVER_RETURN_SUIVANTS = "uri,type,code,typeArticle,intitule,intituleSansArticle,dateCreation,dateSuppression,chefLieu,intituleComplet,categorieJuridique\n" +
            "http://id.insee.fr/geo/commune/c2e2d19d-1ace-4cdc-b80d-c37a1aa59d1e,Commune,14475,0,Noyers-Bocage,Noyers-Bocage,1958-10-13,2016-01-01,,,\n";
    public static final String COMMUNE_EXPECTED_RESPONSE_SUIVANTS_JSON ="["
            + "{"
            + "\"code\":\"14475\","
            + "\"uri\":\"http://id.insee.fr/geo/commune/c2e2d19d-1ace-4cdc-b80d-c37a1aa59d1e\","
            + "\"type\":\"Commune\","
            + "\"dateCreation\":\"1958-10-13\","
            + "\"dateSuppression\":\"2016-01-01\","
            + "\"intituleSansArticle\":\"Noyers-Bocage\","
            + "\"typeArticle\":\"0\","
            + "\"intitule\":\"Noyers-Bocage\""
            + "}"
            + "]";

    public static void assertEqualsJson(String expected, Object actual) {
        assertEqualsJsonOrXml(expected, actual, MAPPER::readTree, Assertions::assertEquals);
    }

    private static <TNode> void assertEqualsJsonOrXml(String expected, Object actual, ReaderThrowing<TNode> reader, BiConsumer<TNode, TNode> assertor) {
        TNode nodeExpected;
        TNode nodeActual;
        try {
            nodeExpected = reader.read(expected);
        } catch (SAXException | IOException e) {
            throw new AssertionFailedError("Error while parsing expected <[ " + expected + " ]> =====> ", e);
        }
        try {
            nodeActual = reader.read(actual.toString());
        } catch (SAXException | IOException e) {
            throw new AssertionFailedError("Error while parsing actual <[ " + actual + " ]> =====> ", e);
        }
        assertor.accept(nodeExpected, nodeActual);
    }

    public static void assertEqualsXml(String expected, Object actual) {
        assertEqualsJsonOrXml(expected, actual, Input::fromString,
                (i1, i2) -> {
                    Diff inputsDiffs = DiffBuilder.compare(i1)
                            .withTest(i2)
                            .ignoreWhitespace()
                            .ignoreComments()
                            .build();
                    assertFalse(inputsDiffs.hasDifferences(), inputsDiffs.fullDescription());
                });
    }

    private interface ReaderThrowing<TNode> {
        TNode read(String input) throws SAXException, IOException;
    }

    public interface GetWithCodeAndDate {
        Response get(String code, String mediaType, Date date);
    }


}
