SELECT DISTINCT ?uri ?type ?code ?typeArticle ?intitule ?intituleSansArticle ?dateCreation ?dateSuppression ?chefLieu
FROM <http://rdf.insee.fr/graphes/geo/cog>

WHERE {
			?origine a igeo:${typeOrigine} ;
			       igeo:codeINSEE '${code}' .
			 BIND('${typeOrigine}' as ?type)       
			       
				   
<#if previous = "true">
			?evenementCreationOrigine igeo:creation ?origine ;
					     			  igeo:date ?dateCreationOrigine.
			FILTER(!BOUND(?dateCreationOrigine) || ?dateCreationOrigine <= '${date}'^^xsd:date)

			?evenementCreationOrigine igeo:suppression ?uri .
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
<#else>
			?evenementSuppressionOrigine igeo:suppression ?origine ;
					                  igeo:date ?dateSuppressionOrigine.
			FILTER(!BOUND(?dateSuppressionOrigine) || ?dateSuppressionOrigine > '${date}'^^xsd:date)
		                  
					                  
					                  
			?evenementSuppressionOrigine igeo:creation ?uri .
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
</#if>				   
				   
		
}
ORDER BY  ?code