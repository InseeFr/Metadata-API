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
public class GeoArrondissementMunipalEndpoints implements GeoArrondissementMunicipalApi {

    private final RequestProcessor requestProcessor;

    public GeoArrondissementMunipalEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogarrmuasc (String code, LocalDate date, TypeEnumAscendantsArrondissementMunicipal type) {
        return requestProcessor.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, ArrondissementMunicipal.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<ArrondissementMunicipal>  getcogarrmu (String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, ArrondissementMunicipal.class, "none"))
                .executeQuery()
                .singleResult(ArrondissementMunicipal.class).toResponseEntity();
    }

    @Override
    public ResponseEntity<List<ArrondissementMunicipal>> getcogarrmuliste (LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(date, ArrondissementMunicipal.class, "none"))
                .executeQuery()
                .listResult(ArrondissementMunicipal.class)
                .toResponseEntity();

    }

}

