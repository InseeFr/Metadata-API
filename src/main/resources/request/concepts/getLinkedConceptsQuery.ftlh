SELECT ?id (?ConceptLinked as ?uri) ?typeOfLink 
FROM <http://rdf.insee.fr/graphes/concepts/definitions>
		WHERE {  

				 {<${uriConcept}> dcterms:replaces ?ConceptLinked . 
				 BIND('replaces' AS ?typeOfLink)} 
				 
				 UNION
				 { ?ConceptLinked dcterms:replaces <${uriConcept}>. 
				 BIND('isReplacedBy' AS ?typeOfLink)} 
				 			
				 BIND(STRAFTER(STR(?ConceptLinked),'/definition/') AS ?id) . 				
		 } 
				 
		 ORDER BY ?typeOfLink