SELECT ?uri ?intituleFr ?intituleEn ?replaces ?isReplacedBy 
?definitionFr ?definitionEn ?editorialNoteFr ?editorialNoteEn ?scopeNoteFr ?scopeNoteEn
WHERE { 
			?uri skos:inScheme ?uriScheme . 
			FILTER(REGEX(STR(?uriScheme),'/concepts/definitions/scheme')) 
			?uri skos:notation '${id}' . 
			?uri skos:prefLabel ?intituleFr . 
			FILTER(lang(?intituleFr) = 'fr') 
			?uri skos:prefLabel ?intituleEn . 
			FILTER(lang(?intituleEn) = 'en') 

			OPTIONAL{ ?uri dcterms:replaces ?replaces } 
			OPTIONAL{ ?isReplacedBy dcterms:replaces ?uri } 
			
			
			OPTIONAL {
				?uri skos:definition ?defFr . 
				?defFr dcterms:language 'fr'^^xsd:language . 
				?defFr evoc:noteLiteral ?definitionFr . 
				?defFr insee:conceptVersion ?v . 
				filter not exists {
					?defFr insee:conceptVersion ?v2
					filter (?v2 > ?dv)
				}
			}  .
			
			OPTIONAL {?uri skos:definition ?defEn . 
				?defEn dcterms:language 'en'^^xsd:language . 
				?defEn evoc:noteLiteral ?definitionEn . 
				?defEn insee:conceptVersion ?v . 
				filter not exists {
					?defEn insee:conceptVersion ?v2
					filter (?v2 > ?dv)
				}
			} .  

			OPTIONAL {?uri skos:scopeNote ?scopeFr . 
				?scopeFr dcterms:language 'fr'^^xsd:language . 
				?scopeFr evoc:noteLiteral ?scopeNoteFr . 
				?scopeFr insee:conceptVersion ?v .
				filter not exists {
					?scopeFr insee:conceptVersion ?v2
					filter (?v2 > ?dv)
				}				
			} .  

			OPTIONAL {?uri skos:scopeNote ?scopeEn . 
				?scopeEn dcterms:language 'en'^^xsd:language . 
				?scopeEn evoc:noteLiteral ?scopeNoteEn . 
				?scopeEn insee:conceptVersion ?v . 
				filter not exists {
					?scopeEn insee:conceptVersion ?v2
					filter (?v2 > ?dv)
				}
			} . 

			OPTIONAL {?uri skos:editorialNote ?editorialFr . 
				?editorialFr dcterms:language 'fr'^^xsd:language . 
				?editorialFr evoc:noteLiteral ?editorialNoteFr . 
				?editorialFr insee:conceptVersion ?v .
				filter not exists {
					?editorialFr insee:conceptVersion ?v2
					filter (?v2 > ?dv)
				}				
			} . 

			OPTIONAL {?uri skos:editorialNote ?editorialEn . 
				?editorialEn dcterms:language 'en'^^xsd:language . 
				?editorialEn evoc:noteLiteral ?editorialNoteEn . 
				?editorialEn insee:conceptVersion ?v . 
				filter not exists {
					?editorialEn insee:conceptVersion ?v2
					filter (?v2 > ?dv)
				}
			} . 

}
ORDER BY ?labelFr