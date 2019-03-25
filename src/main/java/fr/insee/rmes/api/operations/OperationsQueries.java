package fr.insee.rmes.api.operations;

public class OperationsQueries {
	
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


}
