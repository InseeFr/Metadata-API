package fr.insee.rmes.metadata.api;


import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.UniteUrbaine2020;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class GeoUniteUrbaineEndpoints implements GeoUniteUrbaineApi {

    private final RequestProcessor requestProcessor;

    public GeoUniteUrbaineEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<UniteUrbaine2020> getcoguu(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, UniteUrbaine2020.class, "none"))
                .executeQuery()
                .singleResult(UniteUrbaine2020.class).toResponseEntity();
    }

    @Override
    public ResponseEntity<List<UniteUrbaine2020>> getcoguuliste (LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(date, UniteUrbaine2020.class, "none"))
                .executeQuery()
                .listResult(UniteUrbaine2020.class)
                .toResponseEntity();
    }

}

