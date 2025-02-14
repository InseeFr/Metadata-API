package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.requestprocessor.DescendantsRequestParametizer;
import fr.insee.rmes.metadata.model.TypeEnumInclusDansDepartement;
import fr.insee.rmes.metadata.model.ListeDescendantsDepartementInner;
import fr.insee.rmes.metadata.requestprocessor.RequestProcessorBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

import static fr.insee.rmes.metadata.utils.EndpointsUtils.toResponseEntity;

@Controller
public class GeoEndpoints implements GeoDepartementApi {

    private final RequestProcessorBuilder requestProcessorBuilder;

    public GeoEndpoints(RequestProcessorBuilder requestProcessorBuilder) {
        this.requestProcessorBuilder = requestProcessorBuilder;
    }

    @Override
    public ResponseEntity<List<ListeDescendantsDepartementInner>> getcogdepdesc(String code, LocalDate date, TypeEnumInclusDansDepartement type, String filtreNomDescendant) {
        return toResponseEntity(requestProcessorBuilder.findDescendants(
                        new DescendantsRequestParametizer(code, date, type, filtreNomDescendant))
                .listResult(ListeDescendantsDepartementInner.class));
    }
}
