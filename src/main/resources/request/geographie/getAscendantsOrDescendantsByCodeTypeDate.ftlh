SELECT DISTINCT ?uri ?type ?code ?typeArticle ?intitule ?intituleSansArticle ?dateCreation ?dateSuppression ?chefLieu
FROM <http://rdf.insee.fr/graphes/geo/cog>
		WHERE {
			?origine a igeo:${typeOrigine} ;
			       igeo:codeINSEE '${code}' .
				   
<#if ascendant = "true">
			?origine igeo:subdivisionDirecteDe+ ?uri .
<#else>
			?uri igeo:subdivisionDirecteDe+ ?origine .
</#if>				   
				   
			?uri igeo:codeINSEE ?code ;
			     a ?type ;
				 igeo:codeArticle ?typeArticle ;
				 igeo:nom ?intitule ;
				 igeo:nomSansArticle ?intituleSansArticle .
				 
<#if type != "none">
			?uri a '${type}' .
			BIND('${code}' as ?code) 
</#if>
			
			OPTIONAL {
			 	?uri ((igeo:sousPrefecture|igeo:prefecture|igeo:prefectureDeRegion)/igeo:codeINSEE) ?chefLieu.
			}

			OPTIONAL {
				?evenementCreationOrigine igeo:creation ?origine ;
					               igeo:date ?dateCreationOrigine.
			}
			OPTIONAL {
				?evenementSuppressionOrigine igeo:suppression ?origine ;
					                  igeo:date ?dateSuppressionOrigine.
			}
			FILTER(!BOUND(?dateCreationOrigine) || ?dateCreationOrigine <= '${date}'^^xsd:date)
			FILTER(!BOUND(?dateSuppressionOrigine) || ?dateSuppressionOrigine > '${date}'^^xsd:date)
			
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
		ORDER BY ?type ?code