package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.*;
import fr.insee.rmes.metadata.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;


@Controller
public class GeoIntercommunaliteEndpoints implements GeoIntercommunaliteApi {

    private final RequestProcessor requestProcessor;

    public GeoIntercommunaliteEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<Intercommunalite> getcoginterco(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, Intercommunalite.class,"none"))
                .executeQuery()
                .singleResult(Intercommunalite.class).toResponseEntity();
    }
}

