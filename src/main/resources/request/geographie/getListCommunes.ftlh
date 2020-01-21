SELECT DISTINCT ?uri ?code ?typeArticle ?intitule ?intituleSansArticle ?dateCreation ?dateSuppression
FROM <http://rdf.insee.fr/graphes/geo/cog>
		WHERE {
			?uri a igeo:Commune ;
				 igeo:codeINSEE ?code ;
				 igeo:codeArticle ?typeArticle ;
				 igeo:nom ?intitule ;
				 igeo:nomSansArticle ?intituleSansArticle .
			OPTIONAL {
				?evenementCreation igeo:creation ?uri ;
					               igeo:date ?dateCreation .
			}
			OPTIONAL {
				?evenementSuppression igeo:suppression ?uri ;
					                  igeo:date ?dateSuppression.
			}

			FILTER(!BOUND(?dateCreation) || ?dateCreation <= '${date}'^^xsd:date)
			FILTER(!BOUND(?dateSuppression) || ?dateSuppression > '${date}'^^xsd:date)
		}
		ORDER BY ?code