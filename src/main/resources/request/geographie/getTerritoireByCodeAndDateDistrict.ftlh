SELECT ?uri ?type ?code ?typeArticle ?intitule ?intituleSansArticle ?cheflieu ?dateCreation ?dateSuppression 

WHERE {
    BIND(substr('${code}',1,3) as ?com).
    { 
		SELECT DISTINCT ?uri ?type ?code ?typeArticle ?intitule ?intituleSansArticle ?cheflieu ?dateCreation ?dateSuppression ?dateCreationParent ?dateSuppressionParent 
        
		WHERE {
            
			?parent a igeo:CollectiviteDOutreMer ;
				igeo:codeINSEE ?com ;
				(^igeo:subdivisionDirecteDe)+ ?ressource .
				?ressource a ?typeRDF;
				igeo:codeINSEE '${code}' ;
                igeo:codeArticle ?typeArticle ;
				igeo:nom ?intitule ;
				igeo:nomSansArticle ?intituleSansArticle .
            BIND(?typeRDF AS ?type).
            BIND('${code}' as ?code) .
            FILTER(STR(?type) in("http://rdf.insee.fr/def/geo#District") )
            BIND(STR(?ressource) AS ?uri).
			OPTIONAL {?ressource ((igeo:sousPrefecture|igeo:prefecture|igeo:prefectureDeRegion)/igeo:codeINSEE) ?cheflieu.}
			OPTIONAL {?parent (^igeo:creation/igeo:date) ?dateCreationParent.}
			OPTIONAL {?parent (^igeo:suppression/igeo:date) ?dateSuppressionParent.}
			OPTIONAL {?ressource (^igeo:creation/igeo:date) ?dateCreation.}
			OPTIONAL {?ressource (^igeo:suppression/igeo:date) ?dateSuppression.}
		}
	}
	FILTER(!BOUND(?dateCreationParent) || ?dateCreationParent <= '2022-01-12'^^xsd:date)
	FILTER(!BOUND(?dateSuppressionParent) || ?dateSuppressionParent > '2022-01-12'^^xsd:date)
	FILTER(!BOUND(?dateCreation) || ?dateCreation <= '2022-01-12'^^xsd:date)
	FILTER(!BOUND(?dateSuppression) || ?dateSuppression > '2022-01-12'^^xsd:date)
}
ORDER BY ?type ?code