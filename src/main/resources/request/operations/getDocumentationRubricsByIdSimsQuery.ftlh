SELECT ?uri ?id ?idParent ?titreLg1 ?titreLg2 ?type ?valeurSimple ?labelLg1 ?labelLg2 ?codeUri ?organisationUri ?hasDoc ?labelObjLg1 ?labelObjLg2      
            	FROM <http://rdf.insee.fr/graphes/qualite/rapport/${idSims}> 
            	FROM <http://rdf.insee.fr/graphes/codes> 
            	FROM <http://rdf.insee.fr/graphes/organisations> 
            	FROM <http://rdf.insee.fr/graphes/def/simsv2fr> 
            	FROM <http://rdf.insee.fr/graphes/concepts/qualite> 
            WHERE { 
            	   ?report rdf:type sdmx-mm:MetadataReport . 
            		?reporturi sdmx-mm:metadataReport ?report . 
            		OPTIONAL {			
            			?mas sdmx-mm:metadataAttributeProperty ?uri . 
            			?mas sdmx-mm:parent ?uriParent . 
            		    BIND(REPLACE( STR(?uriParent) , '(.*/)(\\w.+$)', '$2' ) AS ?idParent) . 	
            		} 
            		?uri sdmx-mm:concept ?concept . 
            		?concept skos:prefLabel ?titreLg1 ; 
             		 skos:prefLabel ?titreLg2 ; 
            		FILTER(lang(?titreLg1) = 'fr') 
            		FILTER(lang(?titreLg2) = 'en') 
            		BIND(REPLACE( STR(?uri) , '(.*/)(\\w.+$)', '$2' ) AS ?id) . 
		   { 
               { 
            		?reporturi ?uri ?valeurSimple . 
            		FILTER ( datatype(?valeurSimple) = xsd:date ) 
            		BIND('DATE' AS ?type) . 
            	} 
            				 
            	UNION { 
            		?reporturi ?uri ?text . 
            		?text rdf:type <http://purl.org/dc/dcmitype/Text> 
            		OPTIONAL{ ?text rdf:value ?labelLg1 . 
            			FILTER(lang(?labelLg1) = 'fr') 
            		} 
            		OPTIONAL{?text rdf:value ?labelLg2 . 
            			FILTER(lang(?labelLg2) = 'en') 
            		} 
            		BIND(EXISTS{?text insee:additionalMaterial ?doc} AS ?hasDoc) 
            		BIND('RICH_TEXT' AS ?type) . 
            	} 
            			 
            	UNION { 
            		?reporturi ?uri ?labelLg1 . 
            		FILTER(lang(?labelLg1) = 'fr') 
            		OPTIONAL{?reporturi ?uri ?labelLg2 . 
            			FILTER(lang(?labelLg2) = 'en') } 
            		BIND('TEXT' AS ?type) . 
            	} 		 
            	UNION { 
            		?reporturi ?uri ?codeUri . 
            		?codeUri skos:notation ?valeurSimple . 
            		?codeUri skos:inScheme ?listUri . 
            		?listUri skos:notation ?codeList . 
            		?codeUri skos:prefLabel ?labelObjLg1 . 
            		FILTER (lang(?labelObjLg1) = 'fr') . 
            		?codeUri skos:prefLabel ?labelObjLg2 . 
            		FILTER (lang(?labelObjLg2) = 'en') . 
            		BIND('CODE_LIST' AS ?type) . 
            	} 
            	UNION { 
            		?reporturi ?uri ?organisationUri . 
            		?organisationUri dcterms:identifier ?valeurSimple . 
            		OPTIONAL { ?organisationUri skos:prefLabel ?labelObjLg1 . 
            			FILTER (lang(?labelObjLg1) = 'fr')} 
            		OPTIONAL {?organisationUri skos:prefLabel ?labelObjLg2 . 
            			FILTER (lang(?labelObjLg2) = 'en') } 
            		BIND('ORGANISATION' AS ?type) . 
            	} 
            }
            } 