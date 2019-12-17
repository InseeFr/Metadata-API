SELECT DISTINCT ?uri ?code ?typeArticle ?intitule ?intituleSansArticle ?dateCreation ?dateSuppression
FROM <http://rdf.insee.fr/graphes/geo/cog>
		WHERE {
			?uri a igeo:commune ;
				 igeo:codeINSEE '${code}' ;
				 igeo:codeArticle ?typeArticle ;
				 igeo:nom ?intitule ;
				 igeo:nomSansArticle ?intituleSansArticle .
			BIND('${code}' as ?code)
			OPTIONAL {
				?evenementCreation igeo:creation ?uri ;
					               igeo:date ?dateCreation .
			}
			OPTIONAL {
				?evenementSuppression igeo:suppression ?uri ;
					                  igeo:date ?dateSuppression.
			}
			FILTER(COALESCE(?dateCreation, '0000-01-01T00:00') <= '${date}')
			FILTER(COALESCE(?dateSuppression, '9999-12-31T23:59') > '${date}')
		}