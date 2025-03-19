package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.*;
import fr.insee.rmes.metadata.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.CommuneRequestParametizer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

import static fr.insee.rmes.metadata.utils.EndpointsUtils.toResponseEntity;

@Controller
public class GeoCommuneEndpoints implements GeoCommuneApi {

    private final RequestProcessor requestProcessor;

    public GeoCommuneEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }


    @Override
    public ResponseEntity<Commune> getcogcom(String code, LocalDate date) {
        List<Commune> communes = requestProcessor.queryforFindCommune()
                .with(new CommuneRequestParametizer(code, date, Commune.class))
                .executeQuery()
                .listResult(Commune.class).result();
        if (communes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Commune commune = communes.get(0); // On suppose qu'il n'y a qu'une seule commune
        commune.setType(Commune.TypeEnum.COMMUNE);
        return toResponseEntity(commune);
    }


}



