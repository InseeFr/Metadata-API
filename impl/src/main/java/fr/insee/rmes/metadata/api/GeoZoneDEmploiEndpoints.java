package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.ZoneDEmploi2020;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;


@Controller
public class GeoZoneDEmploiEndpoints implements GeoZoneDEmploiApi{

    private final RequestProcessor requestProcessor;

    public GeoZoneDEmploiEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<ZoneDEmploi2020> getcogze(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, ZoneDEmploi2020.class, "none"))
                .executeQuery()
                .singleResult(ZoneDEmploi2020.class)
                .toResponseEntity();

    }
    @Override
    public ResponseEntity<List<ZoneDEmploi2020>> getcogzeliste (LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(date, ZoneDEmploi2020.class, "none"))
                .executeQuery()
                .listResult(ZoneDEmploi2020.class)
                .toResponseEntity();

    }



}
