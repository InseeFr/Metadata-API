package fr.insee.rmes.queries.operations;

public class OperationsQueries {

    public static String getSeries(String id) {
        return " SELECT ?familyId ?familyLabelLg1 ?familyLabelLg2 ?family \n "
            + "				 ?seriesId ?seriesLabelLg1 ?seriesLabelLg2 ?seriesAbstractLg1 ?seriesAbstractLg2 \n "
            + "				 ?seriesHistoryNoteLg1 ?seriesHistoryNoteLg2 ?seriesAltLabelLg1  ?seriesAltLabelLg2 ?series ?simsId \n "
            + "				 ?periodicity ?type \n "
            + "				 ?periodicityLabelLg1 ?typeLabelLg1 \n "
            + "				 ?periodicityLabelLg2 ?typeLabelLg2 \n "
            + "				 ?periodicityId ?typeId \n "
            + "				?hasSeeAlso ?hasIsReplacedBy ?hasReplaces ?hasOperation ?hasIndic ?hasCreator ?hasContributor  \n "
            + " \n "
            + "	{  \n "
            + "		?series a insee:StatisticalOperationSeries . \n "
            + "		FILTER(STRAFTER(STR(?series),'/operations/serie/') = '"
            + id
            + "')  \n "
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
            + "		OPTIONAL { ?series skos:altLabel ?seriesAltLabelLg1 . "
            + "					FILTER (lang(?seriesAltLabelLg1) = 'fr') } \n "
            + "		OPTIONAL { ?series skos:altLabel ?seriesAltLabelLg2 . "
            + "					FILTER (lang(?seriesAltLabelLg2) = 'en') } \n "
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
            + " {	 \n"
            + " 	?series a insee:StatisticalOperationSeries . \n"
            + " 	FILTER(STRAFTER(STR(?series),'/operations/serie/') = '"
            + idSeries
            + "') 	 \n"
            + " 	?series dcterms:hasPart ?uri . 		 \n"
            + " 	?uri a insee:StatisticalOperation . 		 \n"
            + " 	?uri skos:prefLabel ?labelFr . 		 \n"
            + " 	FILTER (lang(?labelFr) = 'fr') 		 \n"
            + " 	?uri skos:prefLabel ?labelEn . 		 \n"
            + " 	FILTER (lang(?labelEn) = 'en') 		 \n"
            + " 	BIND(STRAFTER(STR(?uri),'/operations/operation/') AS ?id) . 	 \n"
            + " 	OPTIONAL { ?sims sdmx-mm:target ?uri . 			 \n"
            + " 		?sims a sdmx-mm:MetadataReport . 				 \n"
            + " 		BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . \n"
            + " 	} 	 \n"
            + " } ";
    }

    public static String getOperationTree() {
        return "SELECT DISTINCT ?familyId ?familyLabelLg1 ?familyLabelLg2 ?family ?familyAltLabelLg1 ?familyAltLabelLg2 "
            + "?seriesId ?seriesLabelLg1 ?seriesLabelLg2 ?series ?seriesAltLabelLg1 ?seriesAltLabelLg2 "
            + "?simsId "
            + "?operationId ?opLabelLg1 ?opLabelLg2  ?operation ?opAltLabelLg1 ?opAltLabelLg2 \r\n"
            + " ?indicId ?indicLabelLg1 ?indicLabelLg2 ?indic ?indicAltLabelLg1 ?indicAltLabelLg2 \r\n"
            + " { \r\n"
            + "	?family a insee:StatisticalOperationFamily .  \r\n"
            + "	?family skos:prefLabel ?familyLabelLg1 . \r\n"
            + "	FILTER (lang(?familyLabelLg1 ) = 'fr') \r\n"
            + "	?family skos:prefLabel ?familyLabelLg2 . \r\n"
            + "	FILTER (lang(?familyLabelLg2 ) = 'en') \r\n"
            + "OPTIONAL {?family skos:altLabel ?familyAltLabelLg1 . \n"
            + "FILTER (lang(?familyAltLabelLg1) = 'fr') } . \n"
            + "OPTIONAL {?family skos:altLabel ?familyAltLabelLg2 . \n"
            + "FILTER (lang(?familyAltLabelLg2) = 'en') } . \n"
            + "	BIND(STRAFTER(STR(?family),'/operations/famille/') AS ?familyId ) . \r\n"
            + "\r\n"
            + "	?family dcterms:hasPart ?series . \r\n"
            + "	?series a insee:StatisticalOperationSeries .  \r\n"
            + "	?series skos:prefLabel ?seriesLabelLg1 . \r\n"
            + "	FILTER (lang(?seriesLabelLg1) = 'fr') \r\n"
            + "	?series skos:prefLabel ?seriesLabelLg2 . \r\n"
            + "	FILTER (lang(?seriesLabelLg2) = 'en') \r\n"
            + "OPTIONAL {?series skos:altLabel ?seriesAltLabelLg1 . \n"
            + "FILTER (lang(?seriesAltLabelLg1) = 'fr') } . \n"
            + "OPTIONAL {?series skos:altLabel ?seriesAltLabelLg2 . \n"
            + "FILTER (lang(?seriesAltLabelLg2) = 'en') } . \n"
            + "	BIND(STRAFTER(STR(?series),'/operations/serie/') AS ?seriesId) . \r\n"
            +

            "\r\n"
            + "	{OPTIONAL {?series dcterms:hasPart ?operation . \r\n"
            + "		?operation a insee:StatisticalOperation .  \r\n"
            + "		?operation skos:prefLabel ?opLabelLg1 . \r\n"
            + "		FILTER (lang(?opLabelLg1) = 'fr') \r\n"
            + "		?operation skos:prefLabel ?opLabelLg2 . \r\n"
            + "		FILTER (lang(?opLabelLg2) = 'en') \r\n"
            + "OPTIONAL {?operation skos:altLabel ?opAltLabelLg1 . \n"
            + "FILTER (lang(?opAltLabelLg1) = 'fr') } . \n"
            + "OPTIONAL {?operation skos:altLabel ?opAltLabelLg2 . \n"
            + "FILTER (lang(?opAltLabelLg2) = 'en') } . \n"
            + "		BIND(STRAFTER(STR(?operation),'/operations/operation/') AS ?operationId) . \r\n"
            + "		OPTIONAL { ?sims sdmx-mm:target ?operation . \r\n"
            + "			 ?sims a sdmx-mm:MetadataReport . \r\n"
            + "				BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . \r\n"
            + "		}\r\n"
            + "	}}\r\n"
            + "	UNION\r\n"
            + "	{OPTIONAL{?indic prov:wasGeneratedBy ?series . \r\n"
            + "		?indic a insee:StatisticalIndicator .  \r\n"
            + "		?indic skos:prefLabel ?indicLabelLg1 . \r\n"
            + "		FILTER (lang(?indicLabelLg1) = 'fr') \r\n"
            + "		?indic skos:prefLabel ?indicLabelLg2 . \r\n"
            + "		FILTER (lang(?indicLabelLg2) = 'en') \r\n"
            + "OPTIONAL {?indic skos:altLabel ?indicAltLabelLg1 . \n"
            + "FILTER (lang(?indicAltLabelLg1) = 'fr') } . \n"
            + "OPTIONAL {?indic skos:altLabel ?indicAltLabelLg2 . \n"
            + "FILTER (lang(?indicAltLabelLg2) = 'en') } . \n"
            + "		BIND(STRAFTER(STR(?indic),'/produits/indicateur/') AS ?indicId) . \r\n"
            + "		OPTIONAL { ?sims sdmx-mm:target ?indic . \r\n"
            + "			 ?sims a sdmx-mm:MetadataReport . \r\n"
            + "				BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . \r\n"
            + "		}\r\n"
            + "	}}\r\n"
            + "	UNION\r\n"
            + "	{OPTIONAL { ?sims sdmx-mm:target ?series . \r\n"
            + "		?sims a sdmx-mm:MetadataReport . \r\n"
            + "		BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . \r\n"
            + "	}}\r\n"
            + "}\r\n"
            + " order by ?familyId ?seriesId ";

    }

    public static String getDocumentationTitle(String idSims) {
        return " SELECT ?id ?uri ?labelLg1 ?labelLg2 ?idCible ?cible ?labelCibleLg1 ?labelCibleLg2 "
            + " FROM <http://rdf.insee.fr/graphes/qualite/rapport/"
            + idSims
            + "> "
            + " WHERE { "
            + " 	?uri rdf:type sdmx-mm:MetadataReport .   "
            + " 		 "
            + " 	 OPTIONAL{ ?uri sdmx-mm:target ?cible .  "
            + " BIND(STRAFTER(STR(?cible),'/') AS ?idCible) . "
            + " 			  ?cible rdfs:label ?labelCibleLg1 . "
            + " 			  FILTER(lang(?labelCibleLg1) = 'fr') "
            + " 			  OPTIONAL{?cible rdfs:label ?labelCibleLg2 . "
            + " 			  FILTER(lang(?labelCibleLg2) = 'en') }  "
            + " } "
            + " 				 "
            + " 	 OPTIONAL{ ?uri rdfs:label ?labelLg1 .  "
            + " 		FILTER(lang(?labelLg1) = 'fr') "
            + " 	 } "
            + " 	 OPTIONAL{ ?uri rdfs:label ?labelLg2 .  "
            + " 		FILTER(lang(?labelLg2) = 'en') "
            + " 	 } "
            + " 				 "
            + " 	FILTER(STRENDS(STR(?uri), '"
            + idSims
            + "')) "
            + " BIND ("
            + idSims
            + " AS ?id )  "
            + " } ";
    }

    public static String getDocumentationRubrics(String idSims) {
        return "SELECT ?uri ?id ?idParent ?titreLg1 ?titreLg2 ?type ?valeurSimple ?labelLg1 ?labelLg2 ?codeUri ?organisationUri ?hasDoc ?labelObjLg1 ?labelObjLg2      "
            + "	FROM <http://rdf.insee.fr/graphes/qualite/rapport/"
            + idSims
            + "> "
            + "	FROM <http://rdf.insee.fr/graphes/codes> "
            + "	FROM <http://rdf.insee.fr/graphes/organisations> "
            + "	FROM <http://rdf.insee.fr/graphes/def/simsv2fr> "
            + "	FROM <http://rdf.insee.fr/graphes/concepts/qualite> "
            + "WHERE { "
            + "	{ "
            + "		?report rdf:type sdmx-mm:MetadataReport . "
            + "		?reporturi sdmx-mm:metadataReport ?report . "
            + "		OPTIONAL {?mas sdmx-mm:metadataAttributeProperty ?uri . "
            + "			?mas sdmx-mm:parent ?uriParent . } "
            + "		BIND(REPLACE( STR(?uriParent) , '(.*/)(\\\\w.+$)', '$2' ) AS ?idParent) . "
            + "		?uri sdmx-mm:concept ?concept . "
            + "		?concept skos:prefLabel ?titreLg1 ; skos:prefLabel ?titreLg2 ; "
            + "		FILTER(lang(?titreLg1) = 'fr') "
            + "		FILTER(lang(?titreLg2) = 'en') "
            + "		BIND(REPLACE( STR(?uri) , '(.*/)(\\\\w.+$)', '$2' ) AS ?id) . "
            + "		?reporturi ?uri ?valeurSimple . "
            + "		FILTER ( datatype(?valeurSimple) = xsd:date ) "
            + "		BIND('DATE' AS ?type) . "
            + "	} "
            + "				 "
            + "	UNION { "
            + "		?report rdf:type sdmx-mm:MetadataReport . "
            + "		?reporturi sdmx-mm:metadataReport ?report . "
            + "		OPTIONAL {			?mas sdmx-mm:metadataAttributeProperty ?uri . "
            + "			?mas sdmx-mm:parent ?uriParent . } "
            + "		BIND(REPLACE( STR(?uriParent) , '(.*/)(\\\\w.+$)', '$2' ) AS ?idParent) . "
            + "		?uri sdmx-mm:concept ?concept . "
            + "		?concept skos:prefLabel ?titreLg1 ; "
            + " 		 skos:prefLabel ?titreLg2 ; "
            + "		FILTER(lang(?titreLg1) = 'fr') "
            + "		FILTER(lang(?titreLg2) = 'en') "
            + "		?reporturi ?uri ?text . "
            + "		?text rdf:type <http://purl.org/dc/dcmitype/Text> "
            + "		BIND(REPLACE( STR(?uri) , '(.*/)(\\\\w.+$)', '$2' ) AS ?id) . "
            + "		OPTIONAL{ ?text rdf:value ?labelLg1 . "
            + "			FILTER(lang(?labelLg1) = 'fr') "
            + "		} "
            + "		OPTIONAL{?text rdf:value ?labelLg2 . "
            + "			FILTER(lang(?labelLg2) = 'en') "
            + "		} "
            + "		BIND(EXISTS{?text insee:additionalMaterial ?doc} AS ?hasDoc) "
            + "		BIND('RICH_TEXT' AS ?type) . "
            + "	} "
            + "			 "
            + "	UNION { "
            + "		?report rdf:type sdmx-mm:MetadataReport . "
            + "		?reporturi sdmx-mm:metadataReport ?report . "
            + "		OPTIONAL { ?mas sdmx-mm:metadataAttributeProperty ?uri . "
            + "			?mas sdmx-mm:parent ?uriParent . } "
            + "		BIND(REPLACE( STR(?uriParent) , '(.*/)(\\\\w.+$)', '$2' ) AS ?idParent) . "
            + "		?uri sdmx-mm:concept ?concept . "
            + "		?concept skos:prefLabel ?titreLg1 ; skos:prefLabel ?titreLg2 ; "
            + "		FILTER(lang(?titreLg1) = 'fr') "
            + "		FILTER(lang(?titreLg2) = 'en') "
            + "		BIND(REPLACE( STR(?uri) , '(.*/)(\\\\w.+$)', '$2' ) AS ?id) . "
            + "		?reporturi ?uri ?labelLg1 . "
            + "		FILTER(lang(?labelLg1) = 'fr') "
            + "		OPTIONAL{?reporturi ?uri ?labelLg2 . "
            + "			FILTER(lang(?labelLg2) = 'en') } "
            + "		BIND('TEXT' AS ?type) . "
            + "	} 		 "
            + "	UNION { "
            + "		?report rdf:type sdmx-mm:MetadataReport . "
            + "		?reporturi sdmx-mm:metadataReport ?report . "
            + "		OPTIONAL {?mas sdmx-mm:metadataAttributeProperty ?uri . "
            + "			?mas sdmx-mm:parent ?uriParent . } "
            + "		BIND(REPLACE( STR(?uriParent) , '(.*/)(\\\\w.+$)', '$2' ) AS ?idParent) . "
            + "		?uri sdmx-mm:concept ?concept . "
            + "		?concept skos:prefLabel ?titreLg1 ; skos:prefLabel ?titreLg2 ; "
            + "		FILTER(lang(?titreLg1) = 'fr') "
            + "		FILTER(lang(?titreLg2) = 'en') "
            + "		BIND(REPLACE( STR(?uri) , '(.*/)(\\\\w.+$)', '$2' ) AS ?id) . "
            + "		?reporturi ?uri ?codeUri . "
            + "		?codeUri skos:notation ?valeurSimple . "
            + "		?codeUri skos:inScheme ?listUri . "
            + "		?listUri skos:notation ?codeList . "
            + "		?codeUri skos:prefLabel ?labelObjLg1 . "
            + "		FILTER (lang(?labelObjLg1) = 'fr') . "
            + "		?codeUri skos:prefLabel ?labelObjLg2 . "
            + "		FILTER (lang(?labelObjLg2) = 'en') . "
            + "		BIND('CODE_LIST' AS ?type) . "
            + "	} "
            + "	UNION { "
            + "		?report rdf:type sdmx-mm:MetadataReport . "
            + "		?reporturi sdmx-mm:metadataReport ?report . "
            + "		OPTIONAL { ?mas sdmx-mm:metadataAttributeProperty ?uri . "
            + "			?mas sdmx-mm:parent ?uriParent . } "
            + "		BIND(REPLACE( STR(?uriParent) , '(.*/)(\\\\w.+$)', '$2' ) AS ?idParent) . "
            + "		?uri sdmx-mm:concept ?concept . "
            + "		?concept skos:prefLabel ?titreLg1 ; skos:prefLabel ?titreLg2 ; "
            + "		FILTER(lang(?titreLg1) = 'fr') "
            + "		FILTER(lang(?titreLg2) = 'en') "
            + "		?reporturi ?uri ?organisationUri . "
            + "		BIND(REPLACE( STR(?uri) , '(.*/)(\\\\w.+$)', '$2' ) AS ?id) . "
            + "		?organisationUri dcterms:identifier ?valeurSimple . "
            + "		OPTIONAL { ?organisationUri skos:prefLabel ?labelObjLg1 . "
            + "			FILTER (lang(?labelObjLg1) = 'fr')} "
            + "		OPTIONAL {?organisationUri skos:prefLabel ?labelObjLg2 . "
            + "			FILTER (lang(?labelObjLg2) = 'en') } "
            + "		BIND('ORGANISATION' AS ?type) . "
            + "	} "
            + "} ";
    }

    public static String getDocuments(String idSims, String idRubric) {
        return "SELECT ?url ?labelLg1 ?labelLg2 ?dateMiseAJour ?langue "
            + "FROM <http://rdf.insee.fr/graphes/qualite/rapport/"
            + idSims
            + "> "
            + "WHERE { "
            + "	 ?text rdf:type <http://purl.org/dc/dcmitype/Text> . "
            + "	 ?text insee:additionalMaterial ?document . "
            + "	 ?document rdf:type <http://xmlns.com/foaf/0.1/Document> . "
            + "	 "
            + "	 ?document <http://schema.org/url> ?url "
            + " "
            + " 	 OPTIONAL{ ?document rdfs:label ?labelLg1 . "
            + "		FILTER(lang(?labelLg1) = 'fr') "
            + "	 } "
            + "	 OPTIONAL{ ?document rdfs:label ?labelLg2 . "
            + "		FILTER(lang(?labelLg2) = 'en') "
            + "	 } "
            + "	 "
            + "	 OPTIONAL{ ?document pav:lastRefreshedOn ?dateMiseAJour . } "
            + "	 OPTIONAL{ ?document dc:language ?langue . } "
            + "	 "
            + "	FILTER(REGEX(STR(?text), '"
            + idRubric
            + "')) "
            + " "
            + " } ";
    }

    public static String getIndicBySeries(String idSeries) {
        return "SELECT ?id ?labelFr ?labelEn ?uri ?simsId \n"
            + "{	 \n"
            + "	?series a insee:StatisticalOperationSeries . \n"
            + "	FILTER(STRAFTER(STR(?series),'/operations/serie/') = '"
            + idSeries
            + "') 	 \n"
            + "	?uri prov:wasGeneratedBy ?series . 		 \n"
            + "			?uri a insee:StatisticalIndicator . 		 \n"
            + "			?uri skos:prefLabel ?labelFr . 		 \n"
            + "			FILTER (lang(?labelFr) = 'fr') 		 \n"
            + "			?uri skos:prefLabel ?labelEn . 		 \n"
            + "			FILTER (lang(?labelEn) = 'en') 		 \n"
            + "			BIND(STRAFTER(STR(?uri),'/produits/indicateur/') AS ?id) . 	\n"
            + "			OPTIONAL { ?sims sdmx-mm:target ?uri . 			 \n"
            + "				?sims a sdmx-mm:MetadataReport . 				 \n"
            + "				BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . \n"
            + "			} 	 	 \n"
            + "} \n";
    }

    public static String getSeriesByIndic(String idIndic) {
        return "SELECT ?id ?labelFr ?labelEn ?uri ?simsId \n"
            + "{	 \n"
            + "	?indic a insee:StatisticalIndicator . \n"
            + "	FILTER(STRAFTER(STR(?series),'/produits/indicateur/') = '"
            + idIndic
            + "') 	 \n"
            + "	?indic prov:wasGeneratedBy ?series . 		 \n"
            + "			?series a insee:StatisticalOperationSeries . 		 \n"
            + "			?series skos:prefLabel ?labelFr . 		 \n"
            + "			FILTER (lang(?labelFr) = 'fr') 		 \n"
            + "			?series skos:prefLabel ?labelEn . 		 \n"
            + "			FILTER (lang(?labelEn) = 'en') 		 \n"
            + "			BIND(STRAFTER(STR(?series),'/operations/serie/') AS ?id) . 	\n"
            + "			OPTIONAL { ?sims sdmx-mm:target ?series . 			 \n"
            + "				?sims a sdmx-mm:MetadataReport . 				 \n"
            + "				BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . \n"
            + "			} 	 	 \n"
            + "} \n";
    }

    public static String getSeeAlsoBySeries(String idSeries) {
        return getLinkDifferentTypeByObject(
            idSeries,
            "rdfs:seeAlso",
            "insee:StatisticalOperationSeries",
            "/operations/serie/");

    }

    public static String getSeeAlsoByIndic(String idIndic) {
        return getLinkDifferentTypeByObject(
            idIndic,
            "rdfs:seeAlso",
            "insee:StatisticalIndicator",
            "/produits/indicateur/");
    }

    public static String getWasGeneratedByByIndic(String idIndic) {
        return getLinkDifferentTypeByObject(
            idIndic,
            "prov:wasGeneratedBy",
            "insee:StatisticalIndicator",
            "/produits/indicateur/");

    }

    public static String getIsReplacedByBySeries(String idSeries) {
        return getLinkSameTypeByObject(
            idSeries,
            "dcterms:isReplacedBy",
            "insee:StatisticalOperationSeries",
            "/operations/serie/");
    }

    public static String getReplacesBySeries(String idSeries) {
        return getLinkSameTypeByObject(
            idSeries,
            "dcterms:replaces",
            "insee:StatisticalOperationSeries",
            "/operations/serie/");
    }

    public static String getIsReplacedByByIndic(String idIndic) {
        return getLinkSameTypeByObject(
            idIndic,
            "dcterms:isReplacedBy",
            "insee:StatisticalIndicator",
            "/produits/indicateur/");
    }

    public static String getReplacesByIndic(String idIndic) {
        return getLinkSameTypeByObject(
            idIndic,
            "dcterms:replaces",
            "insee:StatisticalIndicator",
            "/produits/indicateur/");
    }

    private static String getLinkSameTypeByObject(
        String idObject,
        String linkPredicate,
        String typeObject,
        String uriObject) {
        return "SELECT ?id ?labelFr ?labelEn ?uri ?simsId \n"
            + "{	 \n"
            + "	?obj a "
            + typeObject
            + " . \n"
            + "	FILTER(STRAFTER(STR(?obj),'"
            + uriObject
            + "') = '"
            + idObject
            + "') 	 \n"
            + "	?obj "
            + linkPredicate
            + " ?uri . 		 \n"
            + "	?uri a "
            + typeObject
            + " . 		 \n" // Same type
            + "	?uri skos:prefLabel ?labelFr . 		 \n"
            + "	FILTER (lang(?labelFr) = 'fr') 		 \n"
            + "	?uri skos:prefLabel ?labelEn . 		 \n"
            + "	FILTER (lang(?labelEn) = 'en') 		 \n"
            + "	BIND(STRAFTER(STR(?uri),'"
            + uriObject
            + "') AS ?id) . \n"
            + "	OPTIONAL { ?sims sdmx-mm:target ?uri . 			 \n"
            + "		?sims a sdmx-mm:MetadataReport . 				 \n"
            + "		BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . 		\n"
            + "	} \n"
            + "} \n";
    }

    private static String getLinkDifferentTypeByObject(
        String idObject,
        String linkPredicate,
        String typeObject,
        String uriObject) {
        return "SELECT ?id ?labelFr ?labelEn ?uri ?simsId\n"
            + "FROM <http://rdf.insee.fr/graphes/operations> "
            + "FROM <http://rdf.insee.fr/graphes/produits> "
            + "WHERE { "
            + "	?obj a "
            + typeObject
            + " . \n"
            + "	FILTER(STRAFTER(STR(?obj),'"
            + uriObject
            + "') = '"
            + idObject
            + "') 	 \n"
            + "	?obj "
            + linkPredicate
            + " ?uri . 		 \n"
            + "		?uri skos:prefLabel ?labelFr . 		 \n"
            + "		FILTER (lang(?labelFr) = 'fr') 		 \n"
            + "		?uri skos:prefLabel ?labelEn . 		 \n"
            + "		FILTER (lang(?labelEn) = 'en') 		 \n"
            + "	BIND(STRAFTER(STR(?uri),'"
            + uriObject
            + "') AS ?id) . \n"
            + "	OPTIONAL { ?sims sdmx-mm:target ?uri . 			 \n"
            + "		?sims a sdmx-mm:MetadataReport . 				 \n"
            + "		BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . 		\n"
            + "	} \n"
            + "} \n";
    }

    public static String getCreatorsBySeries(String idSeries) {
        return "SELECT ?id ?labelFr ?labelEn ?uri                                          \n"
            + "{	 \n"
            + "	?series a insee:StatisticalOperationSeries . \n"
            + "	FILTER(STRAFTER(STR(?series),'/operations/serie/') = '"
            + idSeries
            + "') 	 \n"
            + "	?series dcterms:creator ?uri. 		 \n"
            + "	?uri skos:prefLabel ?labelFr . 		 \n"
            + "	FILTER (lang(?labelFr) = 'fr') 		 \n"
            + "	OPTIONAL{?uri skos:prefLabel ?labelEn . 		 \n"
            + "	FILTER (lang(?labelEn) = 'en')} 		 \n"
            + "	?uri dcterms:identifier ?id . \n"
            + "} \n";
    }

    public static String getContributorsBySeries(String idSeries) {
        return "SELECT ?id ?labelFr ?labelEn ?uri \n"
            + "{	 \n"
            + "	?series a insee:StatisticalOperationSeries . \n"
            + "	FILTER(STRAFTER(STR(?series),'/operations/serie/') = '"
            + idSeries
            + "') 	 \n"
            + "	?series dcterms:contributor ?uri. 		 \n"
            + "	?uri skos:prefLabel ?labelFr . 		 \n"
            + "	FILTER (lang(?labelFr) = 'fr') 		 \n"
            + "	OPTIONAL{?uri skos:prefLabel ?labelEn . 		 \n"
            + "	FILTER (lang(?labelEn) = 'en')} 		 \n"
            + "	?uri dcterms:identifier ?id . \n"
            + "} \n";
    }

    public static String getContributorsByIndic(String idIndic) {
        return "SELECT ?id ?labelFr ?labelEn ?uri \n"
            + "{	 \n"
            + "	?indic a insee:StatisticalIndicator . \n"
            + "	FILTER(STRAFTER(STR(?indic),'/produits/indicateur/') = '"
            + idIndic
            + "') 	 \n"
            + "	?indic dcterms:contributor ?uri. 		 \n"
            + "	?uri skos:prefLabel ?labelFr . 		 \n"
            + "	FILTER (lang(?labelFr) = 'fr') 		 \n"
            + "	OPTIONAL{?uri skos:prefLabel ?labelEn . 		 \n"
            + "	FILTER (lang(?labelEn) = 'en')} 		 \n"
            + "	?uri dcterms:identifier ?id . \n"
            + "} \n";
    }

    public static String getIndicator(String idIndic) {
        return " SELECT ?id ?indic ?labelLg1 ?labelLg2 ?altLabelLg1 ?altLabelLg2 ?abstractLg1 ?abstractLg2 ?simsId \n "
            + " ?historyNoteLg1 ?historyNoteLg2 \n "
            + " ?periodicity ?periodicityLabelLg1 ?periodicityLabelLg2 ?periodicityId "
            + " ?hasContributor ?hasReplaces ?hasIsReplacedBy ?hasSeeAlso ?hasWasGeneratedBy \n "
            + " ?uriCreator ?idCreator ?labelFrCreator ?labelEnCreator "

            + " WHERE { \n "
            + " FILTER(STRENDS(STR(?indic),'/produits/indicateur/"
            + idIndic
            + "')) . \n "
            + " BIND(STRAFTER(STR(?indic),'/produits/indicateur/') AS ?id)  \n "

            + " ?indic skos:prefLabel ?labelLg1 \n "
            + " FILTER (lang(?labelLg1) = 'fr') \n "
            + " OPTIONAL{?indic skos:prefLabel ?labelLg2 \n "
            + " 	FILTER (lang(?labelLg2) = 'en') } \n "
            + " OPTIONAL{?indic skos:altLabel ?altLabelLg1 \n "
            + " 	FILTER (lang(?altLabelLg1) = 'fr') } \n "
            + " OPTIONAL{?indic skos:altLabel ?altLabelLg2 \n "
            + " 	FILTER (lang(?altLabelLg2) = 'en') } \n "
            + " OPTIONAL{?indic dcterms:abstract ?abstractLg1 \n "
            + " 	FILTER (lang(?abstractLg1) = 'fr') } \n "
            + " OPTIONAL{?indic dcterms:abstract ?abstractLg2 \n "
            + " 	FILTER (lang(?abstractLg2) = 'en') } \n "
            + " OPTIONAL{?indic skos:historyNote ?historyNoteLg1 \n "
            + " 	FILTER (lang(?historyNoteLg1) = 'fr') } \n "
            + " OPTIONAL{?indic skos:historyNote ?historyNoteLg2 \n "
            + " 	FILTER (lang(?historyNoteLg2) = 'en') } \n "
            + " OPTIONAL {?indic dcterms:accrualPeriodicity ?accrualPeriodicity . \n "
            + " 	?accrualPeriodicity skos:notation ?accrualPeriodicityCode . \n "
            + " 	?accrualPeriodicity skos:inScheme ?accrualPeriodicityCodeList . \n "
            + " 	?accrualPeriodicityCodeList skos:notation ?accrualPeriodicityList . \n "
            + " } \n "
            + "OPTIONAL { \n "
            + "				?indic dcterms:accrualPeriodicity ?periodicity . \n "
            + "				?periodicity skos:prefLabel ?periodicityLabelLg1 .  \n "
            + "				FILTER (lang(?periodicityLabelLg1) = 'fr')  \n "
            + "				?periodicity skos:prefLabel ?periodicityLabelLg2 .  \n "
            + "				FILTER (lang(?periodicityLabelLg2) = 'en')  \n "
            + "				BIND(STRAFTER(STR(?periodicity),'/codes/frequence/') AS ?periodicityId) .  \n "
            + "		} \n "

            + " OPTIONAL {?indic dcterms:creator ?uriCreator . \n "
            + " 		?uriCreator dcterms:identifier ?idCreator . \n "
            + "			?uriCreator skos:prefLabel ?labelFrCreator . 		 \n"
            + "			FILTER (lang(?labelFrCreator) = 'fr') 		 \n"
            + "			OPTIONAL{?uriCreator skos:prefLabel ?labelEnCreator . 		 \n"
            + "					FILTER (lang(?labelEnCreator) = 'en')} 		 \n"
            + " } \n "
            + "		BIND(EXISTS{?indic dcterms:contributor ?a} AS ?hasContributor) . \n "
            + "		BIND(EXISTS{?indic dcterms:replaces ?b} AS ?hasReplaces) .  \n "
            + "		BIND(EXISTS{?indic dcterms:isReplacedBy ?c} AS ?hasIsReplacedBy) .  \n "
            + "		BIND(EXISTS{?indic rdfs:seeAlso ?d} AS ?hasSeeAlso) .  \n "
            + "		BIND(EXISTS{?indic prov:wasGeneratedBy ?e} AS ?hasWasGeneratedBy) .  \n "
            + "	OPTIONAL { ?sims sdmx-mm:target ?indic . 			 \n"
            + "		?sims a sdmx-mm:MetadataReport . 				 \n"
            + "		BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . \n"
            + "	} \n"

            + " } \n ";
    }
}
