SELECT DISTINCT ?uri ?type ?code ?intituleSansArticle ?intitule ?typeDIris ?dateCreation ?dateSuppression ?typeArticle
WHERE {
	{
		?s a igeo:Iris ;
			igeo:typeDIRIS ?uriTypeDIris ;
			igeo:codeINSEE '${code}';
			igeo:codeArticle ?typeArticle ;
			igeo:codeINSEE ?code .
		BIND(SUBSTR(STR(?uriTypeDIris ), STRLEN(STR(?uriTypeDIris )), 1) AS ?typeDIris)
	}
	UNION
	{
		BIND(SUBSTR('${code}', 1, 5) AS ?codeCommune)
		?s a igeo:Commune ;
			igeo:codeINSEE ?codeCommune .
		OPTIONAL {
			?iris igeo:subdivisionDirecteDe+ ?s ; a igeo:Iris .
			OPTIONAL {?iris ^igeo:creation/igeo:date ?dateDebutIris }
			OPTIONAL {?iris ^igeo:suppression/igeo:date ?dateFinIris }
			FILTER (!BOUND(?dateDebutIris) || ?dateDebutIris <= '${date}'^^xsd:date )
			FILTER (!BOUND(?dateFinIris) || ?dateFinIris > '${date}'^^xsd:date )
		}
		FILTER (!BOUND(?iris))
		BIND(CONCAT(?codeCommune, '0000') AS ?code)
		FILTER(?code = '${code}')
	}
	?s igeo:nom ?intitule;
		igeo:codeArticle ?typeArticle;
		igeo:nomSansArticle ?intituleSansArticle ;
		a ?typeRDF .
	BIND(STRAFTER(STR(?typeRDF),"http://rdf.insee.fr/def/geo#") AS ?type).
	BIND(STR(?s) AS ?uri).
	OPTIONAL {?s ^igeo:creation/igeo:date ?dateCreation }
	OPTIONAL {?s ^igeo:suppression/igeo:date ?dateSuppression }
	FILTER(!BOUND(?dateCreation) || ?dateCreation <= '${date}'^^xsd:date)
	FILTER(!BOUND(?dateSuppression) || ?dateSuppression > '${date}'^^xsd:date)
}
ORDER BY ?code