SELECT DISTINCT ?origine ?type ?codeOrigine ?typeArticleOrigine ?intituleOrigine ?intituleSansArticleOrigine ?dateCreationOrigine ?dateSuppressionOrigine ?chefLieuOrigine
	?uri ?code ?typeArticle ?intitule ?intituleSansArticle ?dateCreation ?dateSuppression ?chefLieu
FROM <http://rdf.insee.fr/graphes/geo/cog>

WHERE {
			?origine a igeo:${typeOrigine} ;
			       igeo:codeINSEE ?codeOrigine ;
			       	igeo:codeArticle ?typeArticleOrigine;
					igeo:nom ?intituleOrigine;
					igeo:nomSansArticle ?intituleSansArticleOrigine .
			 BIND('${typeOrigine}' as ?type)       
			 
			 		
			OPTIONAL {
			 	?origine ((igeo:sousPrefecture|igeo:prefecture|igeo:prefectureDeRegion)/igeo:codeINSEE) ?chefLieuOrigine .
			}
			       
				   
<#if previous = "true">
			?evenementCreationOrigine igeo:creation+ ?origine ;
					     			  igeo:date ?dateCreationOrigine.
			FILTER(!BOUND(?dateCreationOrigine) || ?dateCreationOrigine <= '${date}'^^xsd:date)

			?evenementCreationOrigine igeo:suppression+ ?uri .

<#else>
			?evenementSuppressionOrigine igeo:suppression+ ?origine ;
					                  igeo:date ?dateSuppressionOrigine.
			FILTER(!BOUND(?dateSuppressionOrigine) || ?dateSuppressionOrigine > '${date}'^^xsd:date)
		                  	                  
			?evenementSuppressionOrigine igeo:creation+ ?uri .
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