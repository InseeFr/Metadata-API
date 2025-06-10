package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.Pays;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import java.time.LocalDate;
import java.util.List;


@Controller
public class GeoPaysEndpoints implements GeoPaysApi {

    private final RequestProcessor requestProcessor;

    public GeoPaysEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<Pays> getcogpays (String code, LocalDate date) {
        return requestProcessor.queryforFindPays()
                .with(new TerritoireRequestParametizer(code, date, Pays.class, "none"))
                .executeQuery()
                .singleResult(Pays.class).toResponseEntity();
    }

    @Override
    public ResponseEntity<List<Pays>> getcogpayslist (LocalDate date) {
        return requestProcessor.queryforFindPays()
                .with(new TerritoireRequestParametizer(date, Pays.class, "none"))
                .executeQuery()
                .listResult(Pays.class)
                .toResponseEntity();

    }

}