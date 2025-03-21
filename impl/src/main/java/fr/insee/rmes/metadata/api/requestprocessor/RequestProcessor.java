package fr.insee.rmes.metadata.api.requestprocessor;

import fr.insee.rmes.metadata.queries.Query;
import fr.insee.rmes.metadata.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.CommuneRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.PrecedentsSuivantsRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.ProjetesRequestParametizer;
import fr.insee.rmes.metadata.queryexecutor.Csv;
import fr.insee.rmes.metadata.queryexecutor.QueryExecutor;
import fr.insee.rmes.metadata.unmarshaller.Unmarshaller;
import fr.insee.rmes.metadata.utils.EndpointsUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

import static fr.insee.rmes.metadata.queries.QueryBuilder.*;


@Component
public record RequestProcessor(fr.insee.rmes.metadata.queries.QueryBuilder queryBuilder, QueryExecutor queryExecutor, Unmarshaller unmarshaller) {

    public RequestProcessor.QueryBuilder queryforFindAscendantsDescendants() {
        return new RequestProcessor.QueryBuilder(ASCENDANTS_OR_DESCENDANTS, this);
    }

    public RequestProcessor.QueryBuilder queryforFindPrecedentsSuivants() {
        return new RequestProcessor.QueryBuilder(PRECEDENTS, this);
    }

    public RequestProcessor.QueryBuilder queryforFindProjetes() {
        return new RequestProcessor.QueryBuilder(PROJETES, this);
    }

    public RequestProcessor.QueryBuilder queryforFindCommune() {
        return new RequestProcessor.QueryBuilder(COMMUNE, this);
    }

    public record QueryBuilder(Path queryPath, RequestProcessor requestProcessor) {
        public ExecutableQuery with(AscendantsDescendantsRequestParametizer ascendantsDescendantsRequestParametizer) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(ascendantsDescendantsRequestParametizer.toParameters(), queryPath), requestProcessor);
        }
        public ExecutableQuery with(CommuneRequestParametizer communeRequestParametizer) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(communeRequestParametizer.toParameters(), queryPath), requestProcessor);
        }

        public ExecutableQuery with(PrecedentsSuivantsRequestParametizer precedentsRequestParametizer) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(precedentsRequestParametizer.toParameters(), queryPath), requestProcessor);
        }

        public ExecutableQuery with(ProjetesRequestParametizer projetesRequestParametizer) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(projetesRequestParametizer.toParameters(), queryPath), requestProcessor);
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
