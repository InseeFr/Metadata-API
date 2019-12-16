SELECT ?uri ?type ?code ?typeArticle ?intitule ?intituleSansArticle ?dateCreation ?dateSuppression
WHERE {
	{
		SELECT DISTINCT ?ressource ?uri ?type ?code ?typeArticle ?intitule ?intituleSansArticle ?dateCreation ?dateSuppression
		WHERE {
			?ressource a :Commune ;
				a ?typeRDF;
				:codeINSEE '${code}' ;
				:codeArticle ?typeArticle ;
				:nom ?intitule ;
				:nomSansArticle ?intituleSansArticle .
			BIND(STR(?typeRDF) AS ?type).
			BIND(STR(?ressource) AS ?uri).
			BIND('${code}' as ?code)
			OPTIONAL {
				?evenementCreation :creation ?ressource ;
					:date ?dateCreation .
			}
			OPTIONAL {
				?evenementSuppression :suppression ?ressource ;
					:date ?dateSuppression.
			}
		}
	}
	FILTER(COALESCE(?dateCreation, '0000-01-01T00:00') <= ${:date})
	FILTER(COALESCE(?dateSuppression, '9999-12-31T23:59') > '2019-12-02T15:58')
}