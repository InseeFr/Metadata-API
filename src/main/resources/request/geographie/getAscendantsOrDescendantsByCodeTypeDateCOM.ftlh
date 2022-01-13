SELECT ?uri ?code ?type ?typeArticle ?intitule ?intituleSansArticle ?dateCreation ?dateSuppression ?cheflieu
WHERE {
	{
		SELECT DISTINCT ?uri ?type ?code ?typeArticle ?intitule ?intituleSansArticle?cheflieu ?dateCreation ?dateSuppression ?dateCreationParent ?dateSuppressionParent
		WHERE {
			?parent a igeo:${typeOrigine} ;
				igeo:codeINSEE '${code}' ;
				(^igeo:subdivisionDirecteDe)+ ?ressource .
			?ressource a ?typeRDF;
<#if type != 'none'>
				a igeo:${type};
</#if>				
				igeo:codeINSEE ?code ;
				igeo:codeArticle ?typeArticle ;
				igeo:nom ?intitule ;
				igeo:nomSansArticle ?intituleSansArticle .
			BIND(STR(?typeRDF) AS ?type).
			BIND(STR(?ressource) AS ?uri).
			OPTIONAL {?ressource ((igeo:sousPrefecture|igeo:prefecture|igeo:prefectureDeRegion)/igeo:codeINSEE) ?cheflieu.}
			OPTIONAL {?parent (^igeo:creation/igeo:date) ?dateCreationParent.}
			OPTIONAL {?parent (^igeo:suppression/igeo:date) ?dateSuppressionParent.}
			OPTIONAL {?ressource (^igeo:creation/igeo:date) ?dateCreation.}
			OPTIONAL {?ressource (^igeo:suppression/igeo:date) ?dateSuppression.}
<#if filtreNom !='*'>
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
</#if>
		}
	}
	FILTER(!BOUND(?dateCreationParent) || ?dateCreationParent <= '${date}'^^xsd:date)
	FILTER(!BOUND(?dateSuppressionParent) || ?dateSuppressionParent > '${date}'^^xsd:date)
	FILTER(!BOUND(?dateCreation) || ?dateCreation <= '${date}'^^xsd:date)
	FILTER(!BOUND(?dateSuppression) || ?dateSuppression > '${date}'^^xsd:date)
}
ORDER BY ?type ?code