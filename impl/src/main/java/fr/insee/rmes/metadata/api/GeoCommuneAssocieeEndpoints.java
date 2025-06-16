package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.CommuneAssociee;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;


@Controller
public class GeoCommuneAssocieeEndpoints implements GeoCommuneAssocieeApi{

    private final RequestProcessor requestProcessor;

    public GeoCommuneAssocieeEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<CommuneAssociee> getcogcoma(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, CommuneAssociee.class, "none"))
                .executeQuery()
                .singleResult(CommuneAssociee.class)
                .toResponseEntity();

    }

    @Override
    public ResponseEntity<List<CommuneAssociee>> getcogcomaliste (LocalDate date) {
       return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(date, CommuneAssociee.class, "none"))
                .executeQuery()
                .listResult(CommuneAssociee.class)
                .toResponseEntity();

    }



}