package fr.insee.rmes.metadata.requestprocessor;

import fr.insee.rmes.metadata.queries.QueryBuilder;
import fr.insee.rmes.metadata.queryexecutor.QueryExecutor;
import fr.insee.rmes.metadata.unmarshaller.Unmarshaller;
import org.springframework.stereotype.Component;

@Component
public record RequestProcessorBuilder(QueryBuilder queryBuilder, QueryExecutor queryExecutor, Unmarshaller unmarshaller) {

    public RequestProcessor findDescendants(DescendantsRequestParametizer descendantsRequestParametizer) {
        return new RequestProcessor(queryBuilder, descendantsRequestParametizer.toParameters(), QueryBuilder.ASCENDANTS_OR_DESCENDANTS,
                queryExecutor, unmarshaller);
    }
}
