SELECT ?label                                           
            {	 
            	?series a insee:StatisticalOperationSeries . 
            	FILTER(STRAFTER(STR(?series),'/operations/serie/') = '${idSeries}') 	 
            	?series dc:creator ?label. 		 
            } 