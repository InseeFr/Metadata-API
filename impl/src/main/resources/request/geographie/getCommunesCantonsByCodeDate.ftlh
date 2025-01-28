PREFIX : <http://rdf.insee.fr/def/geo#>
SELECT ?uri ?type ?code ?typeArticle ?intitule ?intituleSansArticle ?dateCreation ?dateSuppression ?chefLieu
WHERE {
?commune a :Commune ;
:codeINSEE '${code}' .
?ressource a :Canton;
a ?typeRDF;
^:subdivisionDirecteDe|^:territoireCommunAvec ?commune ;
:codeINSEE ?code;
:codeArticle ?typeArticle ;
:nom ?intitule ;
:nomSansArticle ?intituleSansArticle .
BIND(STRAFTER(STR(?typeRDF),"http://rdf.insee.fr/def/geo#") AS ?type).
BIND(STR(?ressource) AS ?uri).
OPTIONAL {?commune (^:creation/:date) ?dateCreationCommune. }
OPTIONAL {?commune (^:suppression/:date) ?dateSuppressionCommune. }
OPTIONAL {?ressource (^:creation/:date) ?dateCreation. }
OPTIONAL {?ressource (^:suppression/:date) ?dateSuppression. }
OPTIONAL {?ressource :bureauCentralisateur ?chefLieuRDF. ?chefLieuRDF :codeINSEE ?chefLieu.}
OPTIONAL {?chefLieuRDF ^:creation/:date ?dateCreationChefLieu.}
OPTIONAL {?chefLieuRDF ^:suppression/:date ?dateSuppressionChefLieu.}
FILTER(!BOUND(?dateCreationCommune) || xsd:dateTime(?dateCreationCommune) <= '${date}'^^xsd:date)
FILTER(!BOUND(?dateSuppressionCommune) || xsd:dateTime(?dateSuppressionCommune) > '${date}'^^xsd:date)
FILTER(!BOUND(?dateCreation) || xsd:dateTime(?dateCreation) <= '${date}'^^xsd:date)
FILTER(!BOUND(?dateSuppression) || xsd:dateTime(?dateSuppression) > '${date}'^^xsd:date)
FILTER(!BOUND(?dateCreationChefLieu) || xsd:dateTime(?dateCreationChefLieu) <= '${date}'^^xsd:date)
FILTER(!BOUND(?dateSuppressionChefLieu) || xsd:dateTime(?dateSuppressionChefLieu) > '${date}'^^xsd:date)
}
ORDER BY ?code