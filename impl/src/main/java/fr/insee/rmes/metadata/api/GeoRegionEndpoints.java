package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.Departement;
import fr.insee.rmes.metadata.model.Region;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
public class GeoRegionEndpoints implements GeoRegionApi {

    private final RequestProcessor requestProcessor;

    public GeoRegionEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }


    @Override
    public ResponseEntity<Region> getcogreg(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, Region.class,"prefectureDeRegion"))
                .executeQuery()
                .singleResult(Region.class).toResponseEntity();
    }
}
