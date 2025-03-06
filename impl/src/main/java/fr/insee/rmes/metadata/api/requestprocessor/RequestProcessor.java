package fr.insee.rmes.metadata.api.requestprocessor;

import fr.insee.rmes.metadata.queries.parameters.DescendantsRequestParametizer;
import fr.insee.rmes.metadata.queries.Query;
import fr.insee.rmes.metadata.queryexecutor.Csv;
import fr.insee.rmes.metadata.queryexecutor.QueryExecutor;
import fr.insee.rmes.metadata.unmarshaller.Unmarshaller;
import fr.insee.rmes.metadata.utils.EndpointsUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

import static fr.insee.rmes.metadata.queries.QueryBuilder.ASCENDANTS_OR_DESCENDANTS;

@Component
public record RequestProcessor(fr.insee.rmes.metadata.queries.QueryBuilder queryBuilder, QueryExecutor queryExecutor, Unmarshaller unmarshaller) {

    public RequestProcessor.QueryBuilder queryforFindDescendants() {
        return new RequestProcessor.QueryBuilder(ASCENDANTS_OR_DESCENDANTS, this);
    }

    public record QueryBuilder(Path queryPath, RequestProcessor requestProcessor) {
        public ExecutableQuery with(DescendantsRequestParametizer descendantsRequestParametizer) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(descendantsRequestParametizer.toParameters(), queryPath), requestProcessor);
        }
    }

    public record ExecutableQuery(Query query, RequestProcessor requestProcessor) {
        public QueryResult executeQuery() {
            return new QueryResult(requestProcessor.queryExecutor().execute(query), requestProcessor);
        }
    }

    public record QueryResult(Csv csv,RequestProcessor requestProcessor) {
        public <E> ListResult<E> listResult(Class<E> clazz) {
            return new ListResult<>(requestProcessor.unmarshaller().unmarshalList(csv, clazz));
        }
    }

    public record ListResult<E>(List<E> result){

        public ResponseEntity<List<E>> toResponseEntity() {
            return EndpointsUtils.toResponseEntity(result);
        }
    }

}
