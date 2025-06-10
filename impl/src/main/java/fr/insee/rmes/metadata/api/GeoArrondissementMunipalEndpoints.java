package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.ArrondissementMunicipal;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;


@Controller
public class GeoArrondissementMunipalEndpoints implements GeoArrondissementMunicipalApi {

    private final RequestProcessor requestProcessor;

    public GeoArrondissementMunipalEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<ArrondissementMunicipal>  getcogarrmu (String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, ArrondissementMunicipal.class, "none"))
                .executeQuery()
                .singleResult(ArrondissementMunicipal.class).toResponseEntity();
    }

}

