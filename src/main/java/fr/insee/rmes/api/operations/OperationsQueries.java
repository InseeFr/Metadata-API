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
				"}\r\n" + 
				" order by ?familyId ?seriesId ";
			
	}


		/*
	private static String buildSeriesRequest(String fileName, Map<String, Object> params) throws RmesException  {
		return FreeMarkerUtils.buildRequest("operations/series/", fileName, params);
	}
	 */
}
