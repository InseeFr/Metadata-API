PREFIX : <http://rdf.insee.fr/def/geo#>
SELECT ?uri ?type ?code ?typeArticle ?intitule ?intituleSansArticle ?dateCreation ?dateSuppression
WHERE {
?commune a :Commune ;
:codeINSEE '01053' .
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
OPTIONAL {?ressource :bureauCentralisateur ?chefLieuRDF. ?chefLieuRDF :codeINSEE ?cheflieu.}
OPTIONAL {?chefLieuRDF ^:creation/:date ?dateCreationChefLieu.}
OPTIONAL {?chefLieuRDF ^:suppression/:date ?dateSuppressionChefLieu.}
FILTER(!BOUND(?dateCreationCommune) || xsd:dateTime(?dateCreationCommune) <= NOW())
FILTER(!BOUND(?dateSuppressionCommune) || xsd:dateTime(?dateSuppressionCommune) > NOW())
FILTER(!BOUND(?dateCreation) || xsd:dateTime(?dateCreation) <= NOW())
FILTER(!BOUND(?dateSuppression) || xsd:dateTime(?dateSuppression) > NOW())
FILTER(!BOUND(?dateCreationChefLieu) || xsd:dateTime(?dateCreationChefLieu) <= NOW())
FILTER(!BOUND(?dateSuppressionChefLieu) || xsd:dateTime(?dateSuppressionChefLieu) > NOW())
}
ORDER BY ?code