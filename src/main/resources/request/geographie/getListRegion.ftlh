SELECT DISTINCT ?uri ?code ?typeArticle ?intitule ?intituleSansArticle ?dateCreation ?dateSuppression  ?chefLieu
FROM <http://rdf.insee.fr/graphes/geo/cog>
		WHERE {
			?uri a igeo:Region ;
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
			OPTIONAL {
				?uri igeo:prefectureDeRegion ?chefLieuRDF .
				?chefLieuRDF igeo:codeINSEE ?chefLieu.
				OPTIONAL {
					?evenementCreationChefLieu igeo:creation ?chefLieuRDF ;
					igeo:date ?dateCreationChefLieu .
				}
				OPTIONAL {
					?evenementSuppressionChefLieu igeo:suppression ?chefLieuRDF ;
					igeo:date ?dateSuppressionChefLieu.
				}
				FILTER(!BOUND(?dateCreationChefLieu) || ?dateCreation <= '${date}'^^xsd:date)
				FILTER(!BOUND(?dateSuppressionChefLieu) || ?dateSuppression > '${date}'^^xsd:date)
			}
			
			FILTER(!BOUND(?dateCreation) || ?dateCreation <= '${date}'^^xsd:date)
			FILTER(!BOUND(?dateSuppression) || ?dateSuppression > '${date}'^^xsd:date)
		}
		
		
