package fr.insee.rmes.api.operations;

public class OperationsQueries {
	

	public static String getSeries(String id) {
		return " SELECT ?familyId ?familyLabelLg1 ?familyLabelLg2 ?family \n "
				+ "				 ?seriesId ?seriesLabelLg1 ?seriesLabelLg2 ?seriesAbstractLg1 ?seriesAbstractLg2 \n "
				+ "				 ?seriesHistoryNoteLg1 ?seriesHistoryNoteLg2 ?seriesAltLabel ?series ?simsId \n "
				+ "				 ?periodicity ?type \n "
				+ "				 ?periodicityLabelLg1 ?typeLabelLg1 \n "
				+ "				 ?periodicityLabelLg2 ?typeLabelLg2 \n "
				+ "				 ?periodicityId ?typeId \n "
				+ "				?hasSeeAlso ?hasIsReplacedBy ?hasReplaces ?hasOperation ?hasIndic ?hasCreator ?hasContributor  \n "
				+ " \n "
				+ "	{  \n "
				+ "		?series a insee:StatisticalOperationSeries . \n "
				+ "		FILTER(STRAFTER(STR(?series),'/operations/serie/') = '"+id+"')  \n "
				+ "		BIND(STRAFTER(STR(?series),'/operations/serie/') AS ?seriesId)  \n "
				+ "		?series skos:prefLabel ?seriesLabelLg1 .  \n "
				+ "		FILTER (lang(?seriesLabelLg1) = 'fr')  \n "
				+ "		?series skos:prefLabel ?seriesLabelLg2 .  \n "
				+ "		FILTER (lang(?seriesLabelLg2) = 'en')  \n "
				+ " \n "
				+ "		?series dcterms:isPartOf ?family .  \n "
				+ "		?family a insee:StatisticalOperationFamily .  \n "
				+ "		?family skos:prefLabel ?familyLabelLg1 .  \n "
				+ "		FILTER (lang(?familyLabelLg1 ) = 'fr')  \n "
				+ "		?family skos:prefLabel ?familyLabelLg2 .  \n "
				+ "		FILTER (lang(?familyLabelLg2 ) = 'en')  \n "
				+ "		BIND(STRAFTER(STR(?family),'/operations/famille/') AS ?familyId ) .  \n "
				+ " \n "
				+ "		OPTIONAL { ?series dcterms:abstract ?seriesAbstractLg1 .  \n "
				+ "					FILTER (lang(?seriesAbstractLg1) = 'fr') } \n "
				+ "		OPTIONAL { ?series dcterms:abstract ?seriesAbstractLg2 .  \n "
				+ "					FILTER (lang(?seriesAbstractLg2) = 'en') } \n "
				+ "		OPTIONAL { ?series skos:historyNote ?seriesHistoryNoteLg1 .  \n "
				+ "					FILTER (lang(?seriesHistoryNoteLg1) = 'fr') }  \n "
				+ "		OPTIONAL { ?series skos:historyNote ?seriesHistoryNoteLg2 .  \n "
				+ "					FILTER (lang(?seriesHistoryNoteLg2) = 'en') }  \n "
				+ "		OPTIONAL { ?series skos:altLabel ?seriesAltLabel . } \n "
				+ " \n "
				+ "		OPTIONAL { \n "
				+ "				?series dcterms:accrualPeriodicity ?periodicity . \n "
				+ "				?periodicity skos:prefLabel ?periodicityLabelLg1 .  \n "
				+ "				FILTER (lang(?periodicityLabelLg1) = 'fr')  \n "
				+ "				?periodicity skos:prefLabel ?periodicityLabelLg2 .  \n "
				+ "				FILTER (lang(?periodicityLabelLg2) = 'en')  \n "
				+ "				BIND(STRAFTER(STR(?periodicity),'/codes/frequence/') AS ?periodicityId) .  \n "
				+ "		} \n "
				+ " \n "
				+ "		OPTIONAL { \n "
				+ "			?series dcterms:type ?type . \n "
				+ "			?type skos:prefLabel ?typeLabelLg1 .  \n "
				+ "			FILTER (lang(?typeLabelLg1) = 'fr')  \n "
				+ "			?type skos:prefLabel ?typeLabelLg2 .  \n "
				+ "			FILTER (lang(?typeLabelLg2) = 'en')  \n "
				+ "			BIND(STRAFTER(STR(?type),'/codes/categorieSource/') AS ?typeId) .  \n "
				+ "		} \n "
				+ " \n "
				+ "		OPTIONAL { ?sims sdmx-mm:target ?series .  \n "
				+ "			?sims a sdmx-mm:MetadataReport .  \n "
				+ "			BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) .  \n "
				+ "		} \n "
				+ " \n "
				+ "		BIND(EXISTS{?series rdfs:seeAlso ?seeAlso} AS ?hasSeeAlso) \n "
				+ "		BIND(EXISTS{?series dcterms:isReplacedBy ?isReplacedBy} AS ?hasIsReplacedBy) \n "
				+ "		BIND(EXISTS{?series dcterms:replaces ?replaces} AS ?hasReplaces) \n "
				+ "		BIND(EXISTS{?series dcterms:hasPart ?operation} AS ?hasOperation) \n "
				+ "		BIND(EXISTS{?indic prov:wasGeneratedBy ?series} AS ?hasIndic) \n "
				+ "		BIND(EXISTS{?series dcterms:creator ?creator} AS ?hasCreator) \n "
				+ "		BIND(EXISTS{?series dcterms:contributor ?contrib} AS ?hasContributor) \n "

				+ " \n "
				+ "	} ";
	}
	
	public static String getOperationBySeries(String idSeries) {
		return " SELECT ?id ?labelFr ?labelEn ?uri ?simsId \n"
				+" {	 \n"
				+" 	?series a insee:StatisticalOperationSeries . \n"
				+" 	FILTER(STRAFTER(STR(?series),'/operations/serie/') = '"+idSeries+"') 	 \n"
				+" 	?series dcterms:hasPart ?uri . 		 \n"
				+" 	?uri a insee:StatisticalOperation . 		 \n"
				+" 	?uri skos:prefLabel ?labelFr . 		 \n"
				+" 	FILTER (lang(?labelFr) = 'fr') 		 \n"
				+" 	?uri skos:prefLabel ?labelEn . 		 \n"
				+" 	FILTER (lang(?labelEn) = 'en') 		 \n"
				+" 	BIND(STRAFTER(STR(?uri),'/operations/operation/') AS ?id) . 	 \n"
				+" 	OPTIONAL { ?sims sdmx-mm:target ?uri . 			 \n"
				+" 		?sims a sdmx-mm:MetadataReport . 				 \n"
				+" 		BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . \n"	
				+" 	} 	 \n"
				+" } ";
	}
		
	
	
	public static String getOperationTree() {
		return "SELECT DISTINCT ?familyId ?familyLabelLg1 ?familyLabelLg2 ?family ?seriesId ?seriesLabelLg1 ?seriesLabelLg2 ?series ?simsId ?operationId ?opLabelLg1 ?opLabelLg2  ?operation\r\n" + 
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
				"	UNION\r\n" + 
				"	{OPTIONAL { ?sims sdmx-mm:target ?series . \r\n" + 
				"		?sims a sdmx-mm:MetadataReport . \r\n" + 
				"		BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . \r\n" + 
				"	}}\r\n" + 
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

	public static String getIndicBySeries(String idSeries) {
		return "SELECT ?id ?labelFr ?labelEn ?uri ?simsId \n"
				+"{	 \n"
				+"	?series a insee:StatisticalOperationSeries . \n"
				+"	FILTER(STRAFTER(STR(?series),'/operations/serie/') = '"+idSeries+"') 	 \n"
				+"	?uri prov:wasGeneratedBy ?series . 		 \n"
				+"			?uri a insee:StatisticalIndicator . 		 \n"
				+"			?uri skos:prefLabel ?labelFr . 		 \n"
				+"			FILTER (lang(?labelFr) = 'fr') 		 \n"
				+"			?uri skos:prefLabel ?labelEn . 		 \n"
				+"			FILTER (lang(?labelEn) = 'en') 		 \n"
				+"			BIND(STRAFTER(STR(?uri),'/produits/indicateur/') AS ?id) . 	\n"
				+"			OPTIONAL { ?sims sdmx-mm:target ?uri . 			 \n"
				+"				?sims a sdmx-mm:MetadataReport . 				 \n"
				+"				BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . \n" 		
				+"			} 	 	 \n"
				+"} \n";
	}

	public static String getSeeAlsoBySeries(String idSeries) {
		return "SELECT ?id ?labelFr ?labelEn ?uri \n"
				+"FROM <http://rdf.insee.fr/graphes/operations> "
				+"WHERE { "
				+"	?series a insee:StatisticalOperationSeries . \n"
				+"	FILTER(STRAFTER(STR(?series),'/operations/serie/') = '"+idSeries+"') 	 \n"
				+"	?series rdfs:seeAlso ?uri . 		 \n"
				+"		?uri skos:prefLabel ?labelFr . 		 \n"
				+"		FILTER (lang(?labelFr) = 'fr') 		 \n"
				+"		?uri skos:prefLabel ?labelEn . 		 \n"
				+"		FILTER (lang(?labelEn) = 'en') 		 \n"
				+"		BIND(STRAFTER(STR(?uri),'/operations/serie/') AS ?id) . 	 \n"
				+"} \n";
	}

	public static String getIsReplacedByBySeries(String idSeries) {
		return "SELECT ?id ?labelFr ?labelEn ?uri ?simsId \n"
				+"{	 \n"
				+"	?series a insee:StatisticalOperationSeries . \n"
				+"	FILTER(STRAFTER(STR(?series),'/operations/serie/') = '"+idSeries+"') 	 \n"
				+"	?series dcterms:isReplacedBy ?uri . 		 \n"
				+"	?uri a insee:StatisticalOperationSeries . 		 \n"
				+"	?uri skos:prefLabel ?labelFr . 		 \n"
				+"	FILTER (lang(?labelFr) = 'fr') 		 \n"
				+"	?uri skos:prefLabel ?labelEn . 		 \n"
				+"	FILTER (lang(?labelEn) = 'en') 		 \n"
				+"	BIND(STRAFTER(STR(?uri),'/operations/serie/') AS ?id) . \n"
				+"	OPTIONAL { ?sims sdmx-mm:target ?uri . 			 \n"
				+"		?sims a sdmx-mm:MetadataReport . 				 \n"
				+"		BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . 		\n"
				+"	} \n"
				+"} \n";
	}

	public static String getReplacesBySeries(String idSeries) {
		return "SELECT ?id ?labelFr ?labelEn ?uri ?simsId \n"
				+"{	 \n"
				+"	?series a insee:StatisticalOperationSeries . \n"
				+"	FILTER(STRAFTER(STR(?series),'/operations/serie/') = '"+idSeries+"') 	 \n"
				+"	?series dcterms:replaces ?uri. 		 \n"
				+"	?uri a insee:StatisticalOperationSeries . 		 \n"
				+"	?uri skos:prefLabel ?labelFr . 		 \n"
				+"	FILTER (lang(?labelFr) = 'fr') 		 \n"
				+"	?uri skos:prefLabel ?labelEn . 		 \n"
				+"	FILTER (lang(?labelEn) = 'en') 		 \n"
				+"	BIND(STRAFTER(STR(?uri),'/operations/serie/') AS ?id) . \n"
				+"	OPTIONAL { ?sims sdmx-mm:target ?uri . 			 \n"
				+"		?sims a sdmx-mm:MetadataReport . 				 \n"
				+"		BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . \n"	
				+"	} \n"
				+"} \n";
		}

	public static String getCreatorsBySeries(String idSeries) {
		return "SELECT ?id ?labelFr ?labelEn ?uri                                          \n"
				+"{	 \n"
				+"	?series a insee:StatisticalOperationSeries . \n"
				+"	FILTER(STRAFTER(STR(?series),'/operations/serie/') = '"+idSeries+"') 	 \n"
				+"	?series dcterms:creator ?uri. 		 \n"
				+"	?uri skos:prefLabel ?labelFr . 		 \n"
				+"	FILTER (lang(?labelFr) = 'fr') 		 \n"
				+"	OPTIONAL{?uri skos:prefLabel ?labelEn . 		 \n"
				+"	FILTER (lang(?labelEn) = 'en')} 		 \n"
				+"	?uri dcterms:identifier ?id . \n"
				+"} \n";
	}

	public static String getContributorsBySeries(String idSeries) {
		return "SELECT ?id ?labelFr ?labelEn ?uri \n"
				+"{	 \n"
				+"	?series a insee:StatisticalOperationSeries . \n"
				+"	FILTER(STRAFTER(STR(?series),'/operations/serie/') = '"+idSeries+"') 	 \n"
				+"	?series dcterms:contributor ?uri. 		 \n"
				+"	?uri skos:prefLabel ?labelFr . 		 \n"
				+"	FILTER (lang(?labelFr) = 'fr') 		 \n"
				+"	OPTIONAL{?uri skos:prefLabel ?labelEn . 		 \n"
				+"	FILTER (lang(?labelEn) = 'en')} 		 \n"
				+"	?uri dcterms:identifier ?id . \n"
				+"} \n";
	}
		
}
