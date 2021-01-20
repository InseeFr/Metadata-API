SELECT ?label                                           
            {	 
            	?object a ${typeObject} . 
            	FILTER(STRAFTER(STR(?object),'${uriObject}') = '${idObject}') 	 
            	?object dc:creator ?label. 		 
            } 