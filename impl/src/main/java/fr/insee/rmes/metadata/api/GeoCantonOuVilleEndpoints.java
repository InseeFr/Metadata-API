package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.CantonOuVille;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;


@Controller
public class GeoCantonOuVilleEndpoints implements GeoCantonEtVilleApi{

    private final RequestProcessor requestProcessor;

    public GeoCantonOuVilleEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<CantonOuVille> getcogcanvil(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, CantonOuVille.class, "none"))
                .executeQuery()
                .singleResult(CantonOuVille.class)
                .toResponseEntity();

    }

    @Override
    public ResponseEntity<List<CantonOuVille>> getcogcanvilliste (LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(date, CantonOuVille.class, "none"))
                .executeQuery()
                .listResult(CantonOuVille.class)
                .toResponseEntity();

    }


}
