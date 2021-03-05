SELECT DISTINCT ?familyId ?familyLabelLg1 ?familyLabelLg2 ?family ?familyAltLabelLg1 ?familyAltLabelLg2 
           ?seriesId ?seriesLabelLg1 ?seriesLabelLg2 ?series ?seriesAltLabelLg1 ?seriesAltLabelLg2 
           ?simsId 
           ?operationId ?opLabelLg1 ?opLabelLg2  ?operation ?opAltLabelLg1 ?opAltLabelLg2 
            ?indicId ?indicLabelLg1 ?indicLabelLg2 ?indic ?indicAltLabelLg1 ?indicAltLabelLg2 
      { 
           	?family a insee:StatisticalOperationFamily .  
           	?family skos:prefLabel ?familyLabelLg1 . 
           	FILTER (lang(?familyLabelLg1 ) = 'fr') 
           	?family skos:prefLabel ?familyLabelLg2 . 
           	FILTER (lang(?familyLabelLg2 ) = 'en') 
           OPTIONAL {?family skos:altLabel ?familyAltLabelLg1 . 
           FILTER (lang(?familyAltLabelLg1) = 'fr') } . 
           OPTIONAL {?family skos:altLabel ?familyAltLabelLg2 . 
           FILTER (lang(?familyAltLabelLg2) = 'en') } . 
           	BIND(STRAFTER(STR(?family),'/operations/famille/') AS ?familyId ) . 
           
           	?family dcterms:hasPart|^dcterms:isPartOf ?series . 
           	?series a insee:StatisticalOperationSeries .  
           	?series skos:prefLabel ?seriesLabelLg1 . 
           	FILTER (lang(?seriesLabelLg1) = 'fr') 
           	?series skos:prefLabel ?seriesLabelLg2 . 
           	FILTER (lang(?seriesLabelLg2) = 'en') 
           OPTIONAL {?series skos:altLabel ?seriesAltLabelLg1 . 
           FILTER (lang(?seriesAltLabelLg1) = 'fr') } . 
           OPTIONAL {?series skos:altLabel ?seriesAltLabelLg2 . 
           FILTER (lang(?seriesAltLabelLg2) = 'en') } . 
           	BIND(STRAFTER(STR(?series),'/operations/serie/') AS ?seriesId) . 

            
           	{OPTIONAL {?series dcterms:hasPart|^dcterms:isPartOf ?operation . 
           		?operation a insee:StatisticalOperation .  
           		?operation skos:prefLabel ?opLabelLg1 . 
           		FILTER (lang(?opLabelLg1) = 'fr') 
           		?operation skos:prefLabel ?opLabelLg2 . 
           		FILTER (lang(?opLabelLg2) = 'en') 
           OPTIONAL {?operation skos:altLabel ?opAltLabelLg1 . 
           FILTER (lang(?opAltLabelLg1) = 'fr') } . 
           OPTIONAL {?operation skos:altLabel ?opAltLabelLg2 . 
           FILTER (lang(?opAltLabelLg2) = 'en') } . 
           		BIND(STRAFTER(STR(?operation),'/operations/operation/') AS ?operationId) . 
           		OPTIONAL { ?sims sdmx-mm:target ?operation . 
           			 ?sims a sdmx-mm:MetadataReport . 
           				BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . 
           		}
			  }
			}
			
           	UNION
           	{OPTIONAL{?indic prov:wasGeneratedBy ?series . 
           		?indic a insee:StatisticalIndicator .  
           		?indic skos:prefLabel ?indicLabelLg1 . 
           		FILTER (lang(?indicLabelLg1) = 'fr') 
           		?indic skos:prefLabel ?indicLabelLg2 . 
           		FILTER (lang(?indicLabelLg2) = 'en') 
           OPTIONAL {?indic skos:altLabel ?indicAltLabelLg1 . 
           FILTER (lang(?indicAltLabelLg1) = 'fr') } . 
           OPTIONAL {?indic skos:altLabel ?indicAltLabelLg2 . 
           FILTER (lang(?indicAltLabelLg2) = 'en') } . 
           		BIND(STRAFTER(STR(?indic),'/produits/indicateur/') AS ?indicId) . 
           		OPTIONAL { ?sims sdmx-mm:target ?indic . 
           			 ?sims a sdmx-mm:MetadataReport . 
           				BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . 
           		}
           	}}
           	UNION
           	{OPTIONAL { ?sims sdmx-mm:target ?series . 
           		?sims a sdmx-mm:MetadataReport . 
           		BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . 
           	}}
           }
            order by ?familyId ?seriesId 