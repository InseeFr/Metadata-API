package fr.insee.rmes.api.operations;

import java.util.HashMap;
import java.util.Map;

/*
import fr.insee.rmes.config.Config;
import fr.insee.rmes.exceptions.RmesException;
import fr.insee.rmes.persistance.service.sesame.utils.FreeMarkerUtils;
*/
public class OperationsQueries {
	

	private static StringBuilder variables;
	private static StringBuilder whereClause;
	static Map<String,Object> params ;
	
	/*
	 * Functions for building queries
	 */

	private static void initParams() {
		params = new HashMap<>();
		params.put("LG1", "fr");
		params.put("LG2", "en");
	} 
	
	private static void addVariableToList(String variable) {
		if (variables == null){
			variables = new StringBuilder();
		}
		variables.append(variable);
	}

	private static void addClauseToWhereClause(String clause) {
		if (whereClause == null){
			whereClause = new StringBuilder();
		}
		whereClause.append(clause);
	}
	

	private static void addOptionalClause(String predicate, String variableName){
		addClauseToWhereClause( "OPTIONAL{?series "+predicate+" "+variableName + "Lg1 \n");
		addClauseToWhereClause( "FILTER (lang("+variableName + "Lg1) = '" + params.get("LG1") + "') } \n ");
		addClauseToWhereClause( "OPTIONAL{?series "+predicate+" "+variableName + "Lg2 \n");
		addClauseToWhereClause( "FILTER (lang("+variableName + "Lg2) = '" + params.get("LG2") + "') } \n ");
	}

	/*
	 * Private functions to elaborate the getSeries query 
	 */
	
	private static void getSimpleAttr(String id) {
		
		addClauseToWhereClause(" FILTER(STRENDS(STR(?series),'/operations/serie/" + id+ "')) . \n" );
		
		addVariableToList(" ?prefLabelLg1 ?prefLabelLg2 ");
		addClauseToWhereClause( "?series skos:prefLabel ?prefLabelLg1 \n");
		addClauseToWhereClause( "FILTER (lang(?prefLabelLg1) = '" + params.get("LG1") + "')  \n ");
		addClauseToWhereClause( "OPTIONAL{?series skos:prefLabel ?prefLabelLg2 \n");
		addClauseToWhereClause( "FILTER (lang(?prefLabelLg2) = '" + params.get("LG2") + "') } \n ");
		
		addVariableToList(" ?altLabelLg1 ?altLabelLg2 ");
		addOptionalClause("skos:altLabel", "?altLabel");

		addVariableToList(" ?abstractLg1 ?abstractLg2 ");
		addOptionalClause("dcterms:abstract", "?abstract");

		addVariableToList(" ?historyNoteLg1 ?historyNoteLg2 ");
		addOptionalClause("skos:historyNote", "?historyNote");
	}
	
	public static String getOperations(String idSeries) {
		return "SELECT ?id ?labelLg1 ?labelLg2 \n"
				+ " FROM <http://rdf.insee.fr/graphes/operations> \n"
				+ "WHERE { \n" 

				+ "?series dcterms:hasPart ?uri . \n"
				+ "?uri skos:prefLabel ?labelLg1 . \n"
				+ "FILTER (lang(?labelLg1) = '" + params.get("LG1")	+ "') . \n"
				+ "?uri skos:prefLabel ?labelLg2 . \n"
				+ "FILTER (lang(?labelLg2) = '" + params.get("LG2")	+ "') . \n"
				+ "BIND(STRAFTER(STR(?uri),'/operations/operation/') AS ?id) . \n"

				+ "FILTER(STRENDS(STR(?series),'/operations/serie/" + idSeries + "')) . \n"
				+ "}"
				+ " ORDER BY ?id"
				;
	}
	
	/*
	 * reading the DB for the WB
	 */
	
	public static String getSeries2(String id) {

		initParams();
		variables=null;
		whereClause=null;
		getSimpleAttr(id);

			return "SELECT "
			+ variables.toString()
			+ " WHERE {  \n"
			+ whereClause.toString()
			+ "} \n"
			+ "LIMIT 1";
	}
	
	
	public static String getSeries(String id) {
	
		return "SELECT ?familyId ?familyLabelLg1 ?familyLabelLg2 ?family \r\n"
				+ "?seriesId ?seriesLabelLg1 ?seriesLabelLg2 ?seriesAbstractLg1 ?seriesAbstractLg2 " 
				+ "?seriesHistoryNoteLg1 ?seriesHistoryNoteLg2 ?seriesAltLabel ?series ?simsId "
				+ "?operationId ?opLabelLg1 ?opLabelLg2  ?operation\r\n" 
				+ " ?indicId ?indicLabelLg1 ?indicLabelLg2 ?indic "
				+ "?isReplacedBy ?isReplacedByLabelLg1 ?isReplacedByLabelLg2 ?isReplacedById \r\n" 
				+ "?replaces ?replacesLabelLg1 ?replacesLabelLg2 ?replacesId \r\n" 
				+ "?seeAlso ?seeAlsoLabelLg1 ?seeAlsoLabelLg2 ?seeAlsoId \r\n" 
				
				+ " { \r\n" + 
				"	?series a insee:StatisticalOperationSeries .  \r\n" + 
				"   FILTER(STRAFTER(STR(?series),'/operations/serie/') = '" + id + "') \r\n" + 
				"	BIND(STRAFTER(STR(?series),'/operations/serie/') AS ?seriesId)  \r\n" + 
				
				"	?series dcterms:isPartOf ?family .  \r\n" + 
				"	?family a insee:StatisticalOperationFamily .  \r\n" + 
				"	?family skos:prefLabel ?familyLabelLg1 . \r\n" + 
				"	FILTER (lang(?familyLabelLg1 ) = 'fr') \r\n" + 
				"	?family skos:prefLabel ?familyLabelLg2 . \r\n" + 
				"	FILTER (lang(?familyLabelLg2 ) = 'en') \r\n" + 
				"	BIND(STRAFTER(STR(?family),'/operations/famille/') AS ?familyId ) . \r\n" + 
				"\r\n" + 
				
				"	?series skos:prefLabel ?seriesLabelLg1 . \r\n" + 
				"	FILTER (lang(?seriesLabelLg1) = 'fr') \r\n" + 
				"	?series skos:prefLabel ?seriesLabelLg2 . \r\n" + 
				"	FILTER (lang(?seriesLabelLg2) = 'en') \r\n" + 
				
				"	?series dcterms:abstract ?seriesAbstractLg1 . \r\n" + 
				"	FILTER (lang(?seriesAbstractLg1) = 'fr') \r\n" + 
				"	?series dcterms:abstract ?seriesAbstractLg2 . \r\n" + 
				"	FILTER (lang(?seriesAbstractLg2) = 'en') \r\n" + 
				"	?series skos:historyNote ?seriesHistoryNoteLg1 . \r\n" + 
				"	FILTER (lang(?seriesHistoryNoteLg1) = 'fr') \r\n" + 
				"	?series skos:historyNote ?seriesHistoryNoteLg2 . \r\n" + 
				"	FILTER (lang(?seriesHistoryNoteLg2) = 'en') \r\n" + 
				"	?series skos:altLabel ?seriesAltLabel . \r\n" + 

					"\r\n" + 
				"	{OPTIONAL {?series rdfs:seeAlso ?seeAlso . \r\n" + 
				"		?seeAlso a insee:StatisticalOperationSeries .  \r\n" + 
				"		?seeAlso skos:prefLabel ?seeAlsoLabelLg1 . \r\n" + 
				"		FILTER (lang(?seeAlsoLabelLg1) = 'fr') \r\n" + 
				"		?seeAlso skos:prefLabel ?seeAlsoLabelLg2 . \r\n" + 
				"		FILTER (lang(?seeAlsoLabelLg2) = 'en') \r\n" + 
				"		BIND(STRAFTER(STR(?seeAlso),'/operations/serie/') AS ?seeAlsoId) . \r\n" + 
				"	}}\r\n" + 
				"	UNION\r\n" + 

					"\r\n" + 
				"	{OPTIONAL {?series dcterms:isReplacedBy ?isReplacedBy . \r\n" + 
				"		?isReplacedBy a insee:StatisticalOperationSeries .  \r\n" + 
				"		?isReplacedBy skos:prefLabel ?isReplacedByLabelLg1 . \r\n" + 
				"		FILTER (lang(?isReplacedByLabelLg1) = 'fr') \r\n" + 
				"		?isReplacedBy skos:prefLabel ?isReplacedByLabelLg2 . \r\n" + 
				"		FILTER (lang(?isReplacedByLabelLg2) = 'en') \r\n" + 
				"		BIND(STRAFTER(STR(?isReplacedBy),'/operations/serie/') AS ?isReplacedById) . \r\n" + 
				"	}}\r\n" + 
				
				"	UNION\r\n" + 

					"\r\n" + 
					
				"	{OPTIONAL {?series dcterms:replaces ?replaces. \r\n" + 
				"		?replaces a insee:StatisticalOperationSeries .  \r\n" + 
				"		?replaces skos:prefLabel ?replacesLabelLg1 . \r\n" + 
				"		FILTER (lang(?replacesLabelLg1) = 'fr') \r\n" + 
				"		?replaces skos:prefLabel ?replacesLabelLg2 . \r\n" + 
				"		FILTER (lang(?replacesLabelLg2) = 'en') \r\n" + 
				"		BIND(STRAFTER(STR(?replaces),'/operations/serie/') AS ?replacesId) . \r\n" + 
				"	}}\r\n" + 
				"	UNION\r\n" + 
				
				"\r\n" + 
				"	{OPTIONAL {?series dcterms:hasPart ?operation . \r\n" + 
				"		?operation a insee:StatisticalOperation .  \r\n" + 
				"		?operation skos:prefLabel ?opLabelLg1 . \r\n" + 
				"		FILTER (lang(?opLabelLg1) = 'fr') \r\n" + 
				"		?operation skos:prefLabel ?opLabelLg2 . \r\n" + 
				"		FILTER (lang(?opLabelLg2) = 'en') \r\n" + 
				"		BIND(STRAFTER(STR(?operation),'/operations/operation/') AS ?operationId) . \r\n" + 
				"		OPTIONAL { ?sims sdmx-mm:target ?operation . \r\n" + 
				"			   ?sims a sdmx-mm:MetadataReport . \r\n" + 
				"				BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . \r\n" + 
				"		}\r\n" + 
				"	}}\r\n" + 
				"	UNION\r\n" + 
				"	{OPTIONAL{?indic prov:wasGeneratedBy ?series . \r\n" + 
				"		?indic a insee:StatisticalIndicator .  \r\n" + 
				"		?indic skos:prefLabel ?indicLabelLg1 . \r\n" + 
				"		FILTER (lang(?indicLabelLg1) = 'fr') \r\n" + 
				"		?indic skos:prefLabel ?indicLabelLg2 . \r\n" + 
				"		FILTER (lang(?indicLabelLg2) = 'en') \r\n" + 
				"		BIND(STRAFTER(STR(?indic),'/produits/indicateur/') AS ?indicId) . \r\n" + 
				"		OPTIONAL { ?sims sdmx-mm:target ?indic . \r\n" + 
				"			   ?sims a sdmx-mm:MetadataReport . \r\n" + 
				"				BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . \r\n" + 
				"		}\r\n" + 
				"	}}\r\n" + 
				"	OPTIONAL { ?sims sdmx-mm:target ?series . \r\n" + 
				"		?sims a sdmx-mm:MetadataReport . \r\n" + 
				"		BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . \r\n" + 
				"	}\r\n" + 

				"}";	
	}
	
	public static String getOperationTree() {
		return "SELECT ?familyId ?familyLabelLg1 ?familyLabelLg2 ?family ?seriesId ?seriesLabelLg1 ?seriesLabelLg2 ?series ?simsId ?operationId ?opLabelLg1 ?opLabelLg2  ?operation\r\n" + 
				" ?indicId ?indicLabelLg1 ?indicLabelLg2 ?indic \r\n" + 
				" { \r\n" + 
				"	?family a insee:StatisticalOperationFamily .  \r\n" + 
				"	?family skos:prefLabel ?familyLabelLg1 . \r\n" + 
				"	FILTER (lang(?familyLabelLg1 ) = 'fr') \r\n" + 
				"	?family skos:prefLabel ?familyLabelLg2 . \r\n" + 
				"	FILTER (lang(?familyLabelLg2 ) = 'en') \r\n" + 
				"	BIND(STRAFTER(STR(?family),'/operations/famille/') AS ?familyId ) . \r\n" + 
				"\r\n" + 
				"	?family dcterms:hasPart ?series . \r\n" + 
				"	?series a insee:StatisticalOperationSeries .  \r\n" + 
				"	?series skos:prefLabel ?seriesLabelLg1 . \r\n" + 
				"	FILTER (lang(?seriesLabelLg1) = 'fr') \r\n" + 
				"	?series skos:prefLabel ?seriesLabelLg2 . \r\n" + 
				"	FILTER (lang(?seriesLabelLg2) = 'en') \r\n" + 
				"	BIND(STRAFTER(STR(?series),'/operations/serie/') AS ?seriesId) . \r\n" + 

				"\r\n" + 
				"	{OPTIONAL {?series dcterms:hasPart ?operation . \r\n" + 
				"		?operation a insee:StatisticalOperation .  \r\n" + 
				"		?operation skos:prefLabel ?opLabelLg1 . \r\n" + 
				"		FILTER (lang(?opLabelLg1) = 'fr') \r\n" + 
				"		?operation skos:prefLabel ?opLabelLg2 . \r\n" + 
				"		FILTER (lang(?opLabelLg2) = 'en') \r\n" + 
				"		BIND(STRAFTER(STR(?operation),'/operations/operation/') AS ?operationId) . \r\n" + 
				"		OPTIONAL { ?sims sdmx-mm:target ?operation . \r\n" + 
				"			 ?sims a sdmx-mm:MetadataReport . \r\n" + 
				"				BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . \r\n" + 
				"		}\r\n" + 
				"	}}\r\n" + 
				"	UNION\r\n" + 
				"	{OPTIONAL{?indic prov:wasGeneratedBy ?series . \r\n" + 
				"		?indic a insee:StatisticalIndicator .  \r\n" + 
				"		?indic skos:prefLabel ?indicLabelLg1 . \r\n" + 
				"		FILTER (lang(?indicLabelLg1) = 'fr') \r\n" + 
				"		?indic skos:prefLabel ?indicLabelLg2 . \r\n" + 
				"		FILTER (lang(?indicLabelLg2) = 'en') \r\n" + 
				"		BIND(STRAFTER(STR(?indic),'/produits/indicateur/') AS ?indicId) . \r\n" + 
				"		OPTIONAL { ?sims sdmx-mm:target ?indic . \r\n" + 
				"			 ?sims a sdmx-mm:MetadataReport . \r\n" + 
				"				BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . \r\n" + 
				"		}\r\n" + 
				"	}}\r\n" + 
				"	OPTIONAL { ?sims sdmx-mm:target ?series . \r\n" + 
				"		?sims a sdmx-mm:MetadataReport . \r\n" + 
				"		BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . \r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				" order by ?familyId ?seriesId ";
			
	}
	
	public static String getDocumentationTitle(String idSims) {
		return " SELECT ?id ?uri ?labelLg1 ?labelLg2 ?idCible ?cible ?labelCibleLg1 ?labelCibleLg2 "
				+" FROM <http://rdf.insee.fr/graphes/qualite/rapport/"+idSims+"> "
				+" WHERE { "
				+" 	?uri rdf:type sdmx-mm:MetadataReport .   "
				+" 		 "
				+" 	 OPTIONAL{ ?uri sdmx-mm:target ?cible .  "
				+ " BIND(STRAFTER(STR(?cible),'/') AS ?idCible) . "
				+" 			  ?cible rdfs:label ?labelCibleLg1 . "
				+" 			  FILTER(lang(?labelCibleLg1) = 'fr') "
				+" 			  OPTIONAL{?cible rdfs:label ?labelCibleLg2 . "
				+" 			  FILTER(lang(?labelCibleLg2) = 'en') }  "
				+" } "
				+" 				 "
				+" 	 OPTIONAL{ ?uri rdfs:label ?labelLg1 .  "
				+" 		FILTER(lang(?labelLg1) = 'fr') "
				+" 	 } "
				+" 	 OPTIONAL{ ?uri rdfs:label ?labelLg2 .  "
				+" 		FILTER(lang(?labelLg2) = 'en') "
				+" 	 } "
				+" 				 "
				+" 	FILTER(STRENDS(STR(?uri), '"+idSims+"')) "
				+ " BIND ("+idSims+" AS ?id )  "
				+" } ";
	}


	public static String getDocumentationRubrics(String idSims) {
		return "SELECT ?uri ?id ?idParent ?titreLg1 ?titreLg2 ?type ?valeurSimple ?labelLg1 ?labelLg2 ?codeUri ?organisationUri ?hasDoc ?labelObjLg1 ?labelObjLg2      "
				+"	FROM <http://rdf.insee.fr/graphes/qualite/rapport/"+idSims+"> "
				+"	FROM <http://rdf.insee.fr/graphes/codes> "
				+"	FROM <http://rdf.insee.fr/graphes/organisations> "
				+"	FROM <http://rdf.insee.fr/graphes/def/simsv2fr> "
				+"	FROM <http://rdf.insee.fr/graphes/concepts/qualite> "
				+"WHERE { "
				+"	{ "
				+"		?report rdf:type sdmx-mm:MetadataReport . "
				+"		?reporturi sdmx-mm:metadataReport ?report . "
				+"		OPTIONAL {?mas sdmx-mm:metadataAttributeProperty ?uri . "
				+"			?mas sdmx-mm:parent ?uriParent . } "
				+"		BIND(REPLACE( STR(?uriParent) , '(.*/)(\\\\w.+$)', '$2' ) AS ?idParent) . "
				+"		?uri sdmx-mm:concept ?concept . "
				+"		?concept skos:prefLabel ?titreLg1 ; skos:prefLabel ?titreLg2 ; "
				+"		FILTER(lang(?titreLg1) = 'fr') "
				+"		FILTER(lang(?titreLg2) = 'en') "
				+"		BIND(REPLACE( STR(?uri) , '(.*/)(\\\\w.+$)', '$2' ) AS ?id) . "
				+"		?reporturi ?uri ?valeurSimple . "
				+"		FILTER ( datatype(?valeurSimple) = xsd:date ) "
				+"		BIND('DATE' AS ?type) . "
				+"	} "
				+"				 "
				+"	UNION { "
				+"		?report rdf:type sdmx-mm:MetadataReport . "
				+"		?reporturi sdmx-mm:metadataReport ?report . "
				+"		OPTIONAL {			?mas sdmx-mm:metadataAttributeProperty ?uri . "
				+"			?mas sdmx-mm:parent ?uriParent . } "
				+"		BIND(REPLACE( STR(?uriParent) , '(.*/)(\\\\w.+$)', '$2' ) AS ?idParent) . "
				+"		?uri sdmx-mm:concept ?concept . "
				+"		?concept skos:prefLabel ?titreLg1 ; "
				+" 		 skos:prefLabel ?titreLg2 ; "
				+"		FILTER(lang(?titreLg1) = 'fr') "
				+"		FILTER(lang(?titreLg2) = 'en') "
				+"		?reporturi ?uri ?text . "
				+"		?text rdf:type <http://purl.org/dc/dcmitype/Text> "
				+"		BIND(REPLACE( STR(?uri) , '(.*/)(\\\\w.+$)', '$2' ) AS ?id) . "
				+"		OPTIONAL{ ?text rdf:value ?labelLg1 . "
				+"			FILTER(lang(?labelLg1) = 'fr') "
				+"		} "
				+"		OPTIONAL{?text rdf:value ?labelLg2 . "
				+"			FILTER(lang(?labelLg2) = 'en') "
				+"		} "
				+"		BIND(EXISTS{?text insee:additionalMaterial ?doc} AS ?hasDoc) "
				+"		BIND('RICH_TEXT' AS ?type) . "
				+"	} "
				+"			 "
				+"	UNION { "
				+"		?report rdf:type sdmx-mm:MetadataReport . "
				+"		?reporturi sdmx-mm:metadataReport ?report . "
				+"		OPTIONAL { ?mas sdmx-mm:metadataAttributeProperty ?uri . "
				+"			?mas sdmx-mm:parent ?uriParent . } "
				+"		BIND(REPLACE( STR(?uriParent) , '(.*/)(\\\\w.+$)', '$2' ) AS ?idParent) . "
				+"		?uri sdmx-mm:concept ?concept . "
				+"		?concept skos:prefLabel ?titreLg1 ; skos:prefLabel ?titreLg2 ; "
				+"		FILTER(lang(?titreLg1) = 'fr') "
				+"		FILTER(lang(?titreLg2) = 'en') "
				+"		BIND(REPLACE( STR(?uri) , '(.*/)(\\\\w.+$)', '$2' ) AS ?id) . "
				+"		?reporturi ?uri ?labelLg1 . "
				+"		FILTER(lang(?labelLg1) = 'fr') "
				+"		OPTIONAL{?reporturi ?uri ?labelLg2 . "
				+"			FILTER(lang(?labelLg2) = 'en') } "
				+"		BIND('TEXT' AS ?type) . "
				+"	} 		 "
				+"	UNION { "
				+"		?report rdf:type sdmx-mm:MetadataReport . "
				+"		?reporturi sdmx-mm:metadataReport ?report . "
				+"		OPTIONAL {?mas sdmx-mm:metadataAttributeProperty ?uri . "
				+"			?mas sdmx-mm:parent ?uriParent . } "
				+"		BIND(REPLACE( STR(?uriParent) , '(.*/)(\\\\w.+$)', '$2' ) AS ?idParent) . "
				+"		?uri sdmx-mm:concept ?concept . "
				+"		?concept skos:prefLabel ?titreLg1 ; skos:prefLabel ?titreLg2 ; "
				+"		FILTER(lang(?titreLg1) = 'fr') "
				+"		FILTER(lang(?titreLg2) = 'en') "
				+"		BIND(REPLACE( STR(?uri) , '(.*/)(\\\\w.+$)', '$2' ) AS ?id) . "
				+"		?reporturi ?uri ?codeUri . "
				+"		?codeUri skos:notation ?valeurSimple . "
				+"		?codeUri skos:inScheme ?listUri . "
				+"		?listUri skos:notation ?codeList . "
				+"		?codeUri skos:prefLabel ?labelObjLg1 . "
				+"		FILTER (lang(?labelObjLg1) = 'fr') . "
				+"		?codeUri skos:prefLabel ?labelObjLg2 . "
				+"		FILTER (lang(?labelObjLg2) = 'en') . "
				+"		BIND('CODE_LIST' AS ?type) . "
				+"	} "
				+"	UNION { "
				+"		?report rdf:type sdmx-mm:MetadataReport . "
				+"		?reporturi sdmx-mm:metadataReport ?report . "
				+"		OPTIONAL { ?mas sdmx-mm:metadataAttributeProperty ?uri . "
				+"			?mas sdmx-mm:parent ?uriParent . } "
				+"		BIND(REPLACE( STR(?uriParent) , '(.*/)(\\\\w.+$)', '$2' ) AS ?idParent) . "
				+"		?uri sdmx-mm:concept ?concept . "
				+"		?concept skos:prefLabel ?titreLg1 ; skos:prefLabel ?titreLg2 ; "
				+"		FILTER(lang(?titreLg1) = 'fr') "
				+"		FILTER(lang(?titreLg2) = 'en') "
				+"		?reporturi ?uri ?organisationUri . "
				+"		BIND(REPLACE( STR(?uri) , '(.*/)(\\\\w.+$)', '$2' ) AS ?id) . "
				+"		?organisationUri dcterms:identifier ?valeurSimple . "
				+"		OPTIONAL { ?organisationUri skos:prefLabel ?labelObjLg1 . "
				+"			FILTER (lang(?labelObjLg1) = 'fr')} "
				+"		OPTIONAL {?organisationUri skos:prefLabel ?labelObjLg2 . "
				+"			FILTER (lang(?labelObjLg2) = 'en') } "
				+"		BIND('ORGANISATION' AS ?type) . "
				+"	} "
				+"} ";
	}
	
	public static String getDocuments(String idSims, String idRubric) {
		return "SELECT ?url ?labelLg1 ?labelLg2 ?dateMiseAJour ?langue "
				+"FROM <http://rdf.insee.fr/graphes/qualite/rapport/"+idSims+"> "
				+"WHERE { "
				+"	 ?text rdf:type <http://purl.org/dc/dcmitype/Text> . "
				+"	 ?text insee:additionalMaterial ?document . "
				+"	 ?document rdf:type <http://xmlns.com/foaf/0.1/Document> . "
				+"	 "
				+"	 ?document <http://schema.org/url> ?url "
				+" "
				+" 	 OPTIONAL{ ?document rdfs:label ?labelLg1 . "
				+"		FILTER(lang(?labelLg1) = 'fr') "
				+"	 } "
				+"	 OPTIONAL{ ?document rdfs:label ?labelLg2 . "
				+"		FILTER(lang(?labelLg2) = 'en') "
				+"	 } "
				+"	 "
				+"	 OPTIONAL{ ?document pav:lastRefreshedOn ?dateMiseAJour . } "
				+"	 OPTIONAL{ ?document dc:language ?langue . } "
				+"	 "
				+"	FILTER(REGEX(STR(?text), '"+idRubric+"')) "
				+" "
				+" } ";
	}
		
}
