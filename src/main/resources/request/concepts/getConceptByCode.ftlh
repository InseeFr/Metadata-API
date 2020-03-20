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
				?defFr pav:version ?v . 
				filter not exists {
					?uri skos:definition ?def2Fr . 
					?def2Fr dcterms:language 'fr'^^xsd:language . 
					?def2Fr pav:version ?v2
					filter (?v2 > ?v)
				}
			}  .
			
			OPTIONAL {?uri skos:definition ?defEn . 
				?defEn dcterms:language 'en'^^xsd:language . 
				?defEn evoc:noteLiteral ?definitionEn . 
				?defEn pav:version ?v . 
				filter not exists {
					?uri skos:definition ?def2En . 
					?def2En dcterms:language 'en'^^xsd:language . 
					?def2En pav:version ?v2
					filter (?v2 > ?v)
				}
			} .  

			OPTIONAL {
				?uri skos:scopeNote ?scopeFr . 
				?scopeFr dcterms:language 'fr'^^xsd:language . 
				?scopeFr evoc:noteLiteral ?scopeNoteFr . 
				?scopeFr pav:version ?v .
				filter not exists {
					?uri skos:scopeNote ?scope2Fr .
					?scope2Fr dcterms:language 'fr'^^xsd:language . 
					?scope2Fr pav:version ?v2
					filter (?v2 > ?v)
				}				
			} .  

			OPTIONAL {?uri skos:scopeNote ?scopeEn . 
				?scopeEn dcterms:language 'en'^^xsd:language . 
				?scopeEn evoc:noteLiteral ?scopeNoteEn . 
				?scopeEn pav:version ?v . 
				filter not exists {
					?uri skos:scopeNote ?scope2En .
					?scope2En dcterms:language 'fr'^^xsd:language . 
					?scope2En pav:version ?v2
					filter (?v2 > ?v)
				}
			} . 

			OPTIONAL {
				?uri skos:editorialNote ?editorialFr . 
				?editorialFr dcterms:language 'fr'^^xsd:language . 
				?editorialFr evoc:noteLiteral ?editorialNoteFr . 
				?editorialFr pav:version ?v .
				filter not exists {
					?uri skos:editorialNote ?editorial2Fr . 
					?editorial2Fr dcterms:language 'fr'^^xsd:language . 
					?editorial2Fr pav:version ?v2
					filter (?v2 > ?v)
				}				
			} . 

			OPTIONAL {
				?uri skos:editorialNote ?editorialEn . 
				?editorialEn dcterms:language 'en'^^xsd:language . 
				?editorialEn evoc:noteLiteral ?editorialNoteEn . 
				?editorialEn pav:version ?v . 
				filter not exists {
					?uri skos:editorialNote ?editorial2En . 
					?editorial2En dcterms:language 'en'^^xsd:language . 
					?editorial2En pav:version ?v2
					filter (?v2 > ?v)
				}
			} . 

}
ORDER BY DESC(?dateMiseAJour) 
LIMIT 1