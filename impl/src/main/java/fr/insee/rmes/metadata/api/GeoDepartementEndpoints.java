package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.*;
import fr.insee.rmes.metadata.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.PrecedentsRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class GeoDepartementEndpoints implements GeoDepartementApi {

    private final RequestProcessor requestProcessor;

    public GeoDepartementEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogdepdesc(String code, LocalDate date, TypeEnumInclusDansDepartement type, String filtreNom) {
        return requestProcessor.queryforFindDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, filtreNom, Departement.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogdepasc(String code, LocalDate date, TypeEnumContenantDepartement type) {
        return requestProcessor.queryforFindDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, Departement.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireBaseChefLieu>>  getcogdepprec(String code, LocalDate date) {
        return requestProcessor.queryforFindPrecedents()
                .with(new PrecedentsRequestParametizer(code, date, Departement.class))
                .executeQuery()
                .listResult(TerritoireBaseChefLieu.class)
                .toResponseEntity();
    }


}
