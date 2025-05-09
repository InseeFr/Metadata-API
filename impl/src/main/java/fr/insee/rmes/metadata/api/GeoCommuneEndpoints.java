package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.*;
import fr.insee.rmes.metadata.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static fr.insee.rmes.metadata.utils.EndpointsUtils.toResponseEntity;

@Controller
public class GeoCommuneEndpoints implements GeoCommuneApi {

    private final RequestProcessor requestProcessor;

    public GeoCommuneEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }


    @Override
    public ResponseEntity<Commune> getcogcom(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, Commune.class))
                .executeQuery()
                .singleResult(Commune.class).toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireBase>> getcogcomliste(LocalDate date, String filtreNom, Boolean com) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(date, filtreNom, Commune.class, com))
                .executeQuery()
                .listResult(TerritoireBase.class)
                .toResponseEntity();

    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogcomasc( String code, LocalDate date, TypeEnumContenantCommune type) {
        return requestProcessor.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, Commune.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }


}



