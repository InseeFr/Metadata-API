package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.BassinDeVie2022;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;


@Controller
public class GeoBassinDeVieEndpoints implements GeoBassinDeVieApi {

    private final RequestProcessor requestProcessor;

    public GeoBassinDeVieEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<BassinDeVie2022> getcogbass(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, BassinDeVie2022.class, "none"))
                .executeQuery()
                .singleResult(BassinDeVie2022.class).toResponseEntity();
    }

    @Override
    public ResponseEntity<List<BassinDeVie2022>> getcogbassliste (LocalDate date, String filtreNom) {
        String finalFiltreNom = filtreNom == null ? "*" : filtreNom;
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(date, BassinDeVie2022.class, finalFiltreNom,"none", true))
                .executeQuery()
                .listResult(BassinDeVie2022.class)
                .toResponseEntity();

    }

}