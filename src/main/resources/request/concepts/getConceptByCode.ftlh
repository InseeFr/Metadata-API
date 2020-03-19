SELECT distinct ?uri ?intituleFr ?intituleEn ?replaces ?isReplacedBy 
?definitionFr ?definitionEn ?editorialNoteFr ?editorialNoteEn ?scopeNoteFr ?scopeNoteEn ?dateMiseAJour
FROM <http://rdf.insee.fr/graphes/concepts/definitions>
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
			
			OPTIONAL{ ?uri dcterms:modified ?dateMiseAJour } 
			OPTIONAL{ ?uri dcterms:replaces ?replaces } 

			
			
			OPTIONAL {
				?uri skos:definition ?defFr . 
				?defFr dcterms:language 'fr'^^xsd:language . 
				?defFr evoc:noteLiteral ?definitionFr . 
				?defFr insee:conceptVersion ?v . 
				filter not exists {
					?uri skos:definition ?def2Fr . 
					?def2Fr dcterms:language 'fr'^^xsd:language . 
					?def2Fr insee:conceptVersion ?v2
					filter (?v2 > ?v)
				}
			}  .
			
			OPTIONAL {?uri skos:definition ?defEn . 
				?defEn dcterms:language 'en'^^xsd:language . 
				?defEn evoc:noteLiteral ?definitionEn . 
				?defEn insee:conceptVersion ?v . 
				filter not exists {
					?uri skos:definition ?def2En . 
					?def2En dcterms:language 'en'^^xsd:language . 
					?def2En insee:conceptVersion ?v2
					filter (?v2 > ?v)
				}
			} .  

			OPTIONAL {
				?uri skos:scopeNote ?scopeFr . 
				?scopeFr dcterms:language 'fr'^^xsd:language . 
				?scopeFr evoc:noteLiteral ?scopeNoteFr . 
				?scopeFr insee:conceptVersion ?v .
				filter not exists {
					?uri skos:scopeNote ?scope2Fr .
					?scope2Fr dcterms:language 'fr'^^xsd:language . 
					?scope2Fr insee:conceptVersion ?v2
					filter (?v2 > ?v)
				}				
			} .  

			OPTIONAL {?uri skos:scopeNote ?scopeEn . 
				?scopeEn dcterms:language 'en'^^xsd:language . 
				?scopeEn evoc:noteLiteral ?scopeNoteEn . 
				?scopeEn insee:conceptVersion ?v . 
				filter not exists {
					?uri skos:scopeNote ?scope2En .
					?scope2En dcterms:language 'fr'^^xsd:language . 
					?scope2En insee:conceptVersion ?v2
					filter (?v2 > ?v)
				}
			} . 

			OPTIONAL {
				?uri skos:editorialNote ?editorialFr . 
				?editorialFr dcterms:language 'fr'^^xsd:language . 
				?editorialFr evoc:noteLiteral ?editorialNoteFr . 
				?editorialFr insee:conceptVersion ?v .
				filter not exists {
					?uri skos:editorialNote ?editorial2Fr . 
					?editorial2Fr dcterms:language 'fr'^^xsd:language . 
					?editorial2Fr insee:conceptVersion ?v2
					filter (?v2 > ?v)
				}				
			} . 

			OPTIONAL {
				?uri skos:editorialNote ?editorialEn . 
				?editorialEn dcterms:language 'en'^^xsd:language . 
				?editorialEn evoc:noteLiteral ?editorialNoteEn . 
				?editorialEn insee:conceptVersion ?v . 
				filter not exists {
					?uri skos:editorialNote ?editorial2En . 
					?editorial2En dcterms:language 'en'^^xsd:language . 
					?editorial2En insee:conceptVersion ?v2
					filter (?v2 > ?v)
				}
			} . 

}
ORDER BY DESC(?dateMiseAJour) 
LIMIT 1