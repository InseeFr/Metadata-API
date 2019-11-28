SELECT ?id ?labelFr ?labelEn ?uri 
           {	 
           	?series a insee:StatisticalOperationSeries . 
           	FILTER(STRAFTER(STR(?series),'/operations/serie/') = '${idSeries}') 	 
           	?series dcterms:contributor ?uri. 		 
           	?uri skos:prefLabel ?labelFr . 		 
           	FILTER (lang(?labelFr) = 'fr') 		 
           	OPTIONAL{?uri skos:prefLabel ?labelEn . 		 
           	FILTER (lang(?labelEn) = 'en')} 		 
           	?uri dcterms:identifier ?id . 
           } 