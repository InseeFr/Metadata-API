SELECT DISTINCT ?uri ?code ?typeArticle ?intitule ?intituleSansArticle ?dateCreation ?dateSuppression
FROM <http://rdf.insee.fr/graphes/geo/cog>
		WHERE {
			?uri a igeo:Commune ;

				 igeo:codeArticle ?typeArticle ;
				 igeo:nom ?intitule ;
				 igeo:nomSansArticle ?intituleSansArticle .
				 
<#if code != "none">
			?uri igeo:codeINSEE '${code}' .
			BIND('${code}' as ?code)
<#else>
			?uri igeo:codeINSEE ?code .
</#if>
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