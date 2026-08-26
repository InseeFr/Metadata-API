SELECT distinct ?url ?labelLg1 ?labelLg2 ?dateMiseAJour ?langue 
FROM <http://rdf.insee.fr/graphes/qualite/rapport/${idSims}>
FROM <http://rdf.insee.fr/graphes/qualite/documents>
WHERE {
    ?text rdf:type <http://purl.org/dc/dcmitype/Text> .
    ?text dcterms:language <${LANG}> .
    FILTER(REGEX(STR(?text), '${idRubric}'))

     {
            SELECT ?text ?document (COUNT(DISTINCT ?intermediate) AS ?position)
            WHERE {
                ?text insee:additionalMaterial ?startList .
                FILTER(isBlank(?startList))
                ?startList rdf:rest* ?element .
                ?startList rdf:rest* ?intermediate .
                ?intermediate rdf:rest* ?element .
                ?element rdf:first ?document .
            }
            GROUP BY ?text ?document
        }

        ?document rdf:type <http://xmlns.com/foaf/0.1/Document> .
        ?document <http://schema.org/url> ?url .

        OPTIONAL { ?document rdfs:label ?labelLg1 . FILTER(lang(?labelLg1) = 'fr') }
        OPTIONAL { ?document rdfs:label ?labelLg2 . FILTER(lang(?labelLg2) = 'en') }
        OPTIONAL { ?document pav:lastRefreshedOn ?dateMiseAJour . }
        OPTIONAL { ?document dc:language ?langue . }
    }
    ORDER BY ?position