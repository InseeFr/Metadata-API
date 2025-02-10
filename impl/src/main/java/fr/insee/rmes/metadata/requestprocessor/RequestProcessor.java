package fr.insee.rmes.metadata.requestprocessor;

import fr.insee.rmes.metadata.queries.QueryBuilder;
import fr.insee.rmes.metadata.queryexecutor.QueryExecutor;
import fr.insee.rmes.metadata.unmarshaller.Unmarshaller;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public record RequestProcessor(QueryBuilder queryBuilder, Map<String, Object> parameters, Path queryFile, QueryExecutor queryExecutor, Unmarshaller unmarshaller) {

    public <E> List<E> listResult(Class<E> clazz) {
        parameters.put("typeOrigine", clazz.getSimpleName());
        var query = queryBuilder.build(parameters, queryFile);
        return unmarshaller.unmarshalList(queryExecutor.execute(query), clazz);
    }
}
