package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.Departement;
import fr.insee.rmes.metadata.model.TerritoireTousAttributs;
import fr.insee.rmes.metadata.model.TypeEnumContenantDepartement;
import fr.insee.rmes.metadata.model.TypeEnumInclusDansDepartement;
import fr.insee.rmes.metadata.queries.parameters.AscendantsDescendantsRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class GeoEndpoints implements GeoDepartementApi {

    private final RequestProcessor requestProcessor;

    public GeoEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogdepdesc(String code, LocalDate date, TypeEnumInclusDansDepartement type, String filtreNom) {
        return requestProcessor.queryforFindDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, null, filtreNom, Departement.class, false))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogdepasc(String code, LocalDate date, TypeEnumContenantDepartement type) {
        return requestProcessor.queryforFindDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, null, type,  null, Departement.class,true))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }


}
