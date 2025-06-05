package fr.insee.rmes.metadata.api;


import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.CommuneDeleguee;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
public class GeoCommuneDelegueeEndpoints implements GeoCommuneDelegueeApi{

    private final RequestProcessor requestProcessor;

    public GeoCommuneDelegueeEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<CommuneDeleguee> getcogcomd(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, CommuneDeleguee.class, "none"))
                .executeQuery()
                .singleResult(CommuneDeleguee.class)
                .toResponseEntity();

    }


}
