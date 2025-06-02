package fr.insee.rmes.metadata.api;


import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.UniteUrbaine2020;
import fr.insee.rmes.metadata.model.UniteUrbaine2020;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
public class GeoUniteUrbaineEndpoints implements GeoUniteUrbaineApi {

    private final RequestProcessor requestProcessor;

    public GeoUniteUrbaineEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<UniteUrbaine2020> getcoguu(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, "prefecture", UniteUrbaine2020.class))
                .executeQuery()
                .singleResult(UniteUrbaine2020.class).toResponseEntity();
    }

}

