SELECT ?id ?labelFr ?labelEn ?uri 
            {	 
            	?indic a insee:StatisticalIndicator . 
            	FILTER(STRAFTER(STR(?indic),'/produits/indicateur/') = '${idIndic}') 	 
            	?indic dcterms:contributor ?uri. 		 
            	?uri skos:prefLabel ?labelFr . 		 
            	FILTER (lang(?labelFr) = 'fr') 		 
            	OPTIONAL{?uri skos:prefLabel ?labelEn . 		 
            	FILTER (lang(?labelEn) = 'en')} 		 
            	?uri dcterms:identifier ?id . 
            } 