SELECT DISTINCT ?uri ?type ?code ?typeArticle ?intitule ?intituleSansArticle ?dateCreation ?dateSuppression ?chefLieu
FROM <http://rdf.insee.fr/graphes/geo/cog>
		WHERE {
			?enfant a igeo:Commune ;
			       igeo:codeINSEE '${code}' ;
					igeo:subdivisionDirecteDe+ ?uri .
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
				?evenementCreationEnfant igeo:creation ?enfant ;
					               igeo:date ?dateCreationEnfant .
			}
			OPTIONAL {
				?evenementSuppressionEnfant igeo:suppression ?enfant ;
					                  igeo:date ?dateSuppressionEnfant.
			}
			FILTER(!BOUND(?dateCreationEnfant) || ?dateCreationEnfant <= '${date}'^^xsd:date)
			FILTER(!BOUND(?dateSuppressionEnfant) || ?dateSuppressionEnfant > '${date}'^^xsd:date)
			
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