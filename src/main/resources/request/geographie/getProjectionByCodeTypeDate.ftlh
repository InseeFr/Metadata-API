SELECT DISTINCT ?uri ?type ?code ?typeArticle ?intitule ?intituleSansArticle ?dateCreation ?dateSuppression ?chefLieu
FROM <http://rdf.insee.fr/graphes/geo/cog>

WHERE {
			?origine a igeo:${typeOrigine} ;
			       igeo:codeINSEE '${code}' .
			 BIND('${typeOrigine}' as ?type)       
			 
			 OPTIONAL{
				?evenementCreationOrigine igeo:creation ?origine ;
					                  	igeo:date ?dateCreationOrigine.
			}
			OPTIONAL{
				?evenementSuppressionOrigine igeo:suppression ?origine ;
					                  igeo:date ?dateSuppressionOrigine.
			}
			
			FILTER(!BOUND(?dateCreationOrigine) || ?dateCreationOrigine <= '${date}'^^xsd:date) 
			FILTER(!BOUND(?dateSuppressionOrigine) || ?dateSuppressionOrigine > '${date}'^^xsd:date)


			   
<#if previous = "true">
		OPTIONAL{          		
				?origine (^igeo:creation/igeo:suppression)+ ?uriProj .
		}
		
			BIND(IF((!BOUND(?uriProj)&&(?dateCreationOrigine <= '${dateProjection}'^^xsd:date)),
				?origine, 
				IF(!BOUND(?uriProj),"no response",?uriProj)) 
				AS ?uri).

<#else>
		OPTIONAL{          		
				?origine (^igeo:suppression/igeo:creation)+ ?uriProj .
		}
		
		BIND(IF((!BOUND(?uriProj)&&(?dateSuppressionOrigine >= '${dateProjection}'^^xsd:date|| !BOUND(?dateSuppressionOrigine))),
				?origine, 
				IF(!BOUND(?uriProj),"no response",?uriProj)) 
				AS ?uri).

</#if>					

			?uri a igeo:${typeOrigine} ;
					igeo:codeINSEE ?code ;
					igeo:codeArticle ?typeArticle;
					igeo:nom ?intitule;
					igeo:nomSansArticle ?intituleSansArticle .
			OPTIONAL {
				?evenementCreation igeo:creation ?uri ;
					               igeo:date ?dateCreation .
			}
			OPTIONAL {
				?evenementSuppression igeo:suppression ?uri ;
					                  igeo:date ?dateSuppression.
			}
			
			OPTIONAL {
			 	?uri ((igeo:sousPrefecture|igeo:prefecture|igeo:prefectureDeRegion)/igeo:codeINSEE) ?chefLieu.
			}
			
			FILTER(!BOUND(?dateCreation) || ?dateCreation <= '${dateProjection}'^^xsd:date)
			FILTER(!BOUND(?dateSuppression) || ?dateSuppression > '${dateProjection}'^^xsd:date)
				   
		
}
ORDER BY  ?code