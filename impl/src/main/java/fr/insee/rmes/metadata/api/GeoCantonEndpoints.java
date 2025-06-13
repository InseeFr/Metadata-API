package fr.insee.rmes.metadata.api;


import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.Canton;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class GeoCantonEndpoints implements GeoCantonApi {

    private final RequestProcessor requestProcessor;

    public GeoCantonEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<Canton> getcogcan(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, Canton.class, "bureauCentralisateur"))
                .executeQuery()
                .singleResult(Canton.class).toResponseEntity();
    }

    @Override
    public ResponseEntity<List<Canton>> getcogcanliste (LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(date, Canton.class, "*"))
                .executeQuery()
                .listResult(Canton.class)
                .toResponseEntity();

    }

}

