SELECT ?uri ?code ?type ?typeArticle ?intitule ?intituleSansArticle ?dateCreation ?dateSuppression ?chefLieu
WHERE {
	{
		SELECT DISTINCT ?uri ?type ?code ?typeArticle ?intitule ?intituleSansArticle?cheflieu ?dateCreation ?dateSuppression ?dateCreationParent ?dateSuppressionParent
		WHERE {
			?parent a igeo:District ;
				igeo:codeINSEE '${code}' .
				
			?ressource a ?typeRDF;
				igeo:codeINSEE ?code ;
				igeo:codeArticle ?typeArticle ;
				igeo:nom ?intitule ;
				igeo:nomSansArticle ?intituleSansArticle .
            BIND(?typeRDF AS ?type).
            FILTER(STR(?type) in("http://rdf.insee.fr/def/geo#CollectiviteDOutreMer") )
            FILTER(?code in(substr('${code}',1,3)))
			BIND(STR(?ressource) AS ?uri).
			OPTIONAL {?ressource ((igeo:sousPrefecture|igeo:prefecture|igeo:prefectureDeRegion)/igeo:codeINSEE) ?cheflieu.}
			OPTIONAL {?parent (^igeo:creation/igeo:date) ?dateCreationParent.}
			OPTIONAL {?parent (^igeo:suppression/igeo:date) ?dateSuppressionParent.}
			OPTIONAL {?ressource (^igeo:creation/igeo:date) ?dateCreation.}
			OPTIONAL {?ressource (^igeo:suppression/igeo:date) ?dateSuppression.}
		}
	}
	FILTER(!BOUND(?dateCreationParent) || ?dateCreationParent <= '2022-01-20'^^xsd:date)
	FILTER(!BOUND(?dateSuppressionParent) || ?dateSuppressionParent > '2022-01-20'^^xsd:date)
	FILTER(!BOUND(?dateCreation) || ?dateCreation <= '2022-01-20'^^xsd:date)
	FILTER(!BOUND(?dateSuppression) || ?dateSuppression > '2022-01-20'^^xsd:date)
}
ORDER BY ?type ?code