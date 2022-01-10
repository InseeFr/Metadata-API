SELECT ?uri ?type ?code ?typeArticle ?intitule ?intituleSansArticle ?cheflieu ?dateCreation ?dateSuppression
WHERE {
	{
		SELECT DISTINCT ?uri ?type ?code ?typeArticle ?intitule ?intituleSansArticle?cheflieu ?dateCreation ?dateSuppression ?dateCreationParent ?dateSuppressionParent
		WHERE {
			?parent a :${typeOrigine} ;
				:codeINSEE '${code}' ;
				(^:subdivisionDirecteDe)+ ?ressource .
					?ressource a ?typeRDF;
				a :${type};
				:codeINSEE ?code ;
				:codeArticle ?typeArticle ;
				:nom ?intitule ;
				:nomSansArticle ?intituleSansArticle .
			BIND(STR(?typeRDF) AS ?type).
			BIND(STR(?ressource) AS ?uri).
			OPTIONAL {?ressource ((:sousPrefecture|:prefecture|:prefectureDeRegion)/:codeINSEE) ?cheflieu.}
			OPTIONAL {?parent (^:creation/:date) ?dateCreationParent.}
			OPTIONAL {?parent (^:suppression/:date) ?dateSuppressionParent.}
			OPTIONAL {?ressource (^:creation/:date) ?dateCreation.}
			OPTIONAL {?ressource (^:suppression/:date) ?dateSuppression.}
			# ## Début du filtre sur le libellé ##
			# La chaîne utilisateur dans une variable query
			BIND('${filtreNom}' AS ?query). 
			# Formattage du nom avec article pour comparaison non polluée par majuscules et accents
			BIND (REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(LCASE(?intitule), "[àáâãäåaaa]", "a"), "ç", "c"), "[èééêë]", "e"), "[ìíîïì]", "i"), "[óôõö]", "o"), "[ùúûü]", "u"), "ÿ", "y"), "[œ]", "oe"), "[-_\']", " "), "[^a-z0-9() ]", ""), "[ ]{2,}", " ") AS ?formattedNom) . 
			# Formattage du nom sans article pour comparaison non polluée par majuscules et accents
			BIND (REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(LCASE(?intituleSansArticle), "[àáâãäåaaa]", "a"), "ç", "c"), "[èééêë]", "e"), "[ìíîïì]", "i"), "[óôõö]", "o"), "[ùúûü]", "u"), "ÿ", "y"), "[œ]", "oe"), "[-_']", " "), "[^a-z0-9() ]", ""), "[ ]{2,}", " ") AS ?formattedNomSansArticle) . 
			# Formattage de la chaîne utilisateur pour comparaison non polluée par majuscules et accents (avec recodage saint/sainte et sur/sous). Ajout d’un ^ au début pour chercher les nom qui commencent par la chaîne utilisateur
			BIND (CONCAT("^", REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(LCASE(?query), "[àáâãäåaaa]", "a"), "ç", "c"), "[èééêë]", "e"), "[ìíîïì]", "i"), "[óôõö]", "o"), "[ùúûü]", "u"), "ÿ", "y"), "[œ]", "oe"), "([^/]+)[/]", "$1-sur-"),"([^\\\\]+)[\\\\]", "$1-sous-"), "[-_']", " "),"[^a-z0-9() ]", ""), "[ ]{2,}", " "), "^st(e)? ", "saint$1 "), "") AS ?formattedQuery) . 
			# Filtrage par comparaison de la chaîne utilisateur formatée avec le nom formaté avec et sans article
			FILTER (REGEX(?formattedNom, ?formattedQuery) || REGEX(?formattedNomSansArticle, ?formattedQuery))
			## Fin du filtre sur le libellé ##
		}
	}
	FILTER(!BOUND(?dateCreationParent) || ?dateCreationParent <= '${date}'^^xsd:date)
	FILTER(!BOUND(?dateSuppressionParent) || ?dateSuppressionParent > '${date}'^^xsd:date)
	FILTER(!BOUND(?dateCreation) || ?dateCreation <= '${date}'^^xsd:date)
	FILTER(!BOUND(?dateSuppression) || ?dateSuppression > '${date}'^^xsd:date)
}
ORDER BY ?type ?code