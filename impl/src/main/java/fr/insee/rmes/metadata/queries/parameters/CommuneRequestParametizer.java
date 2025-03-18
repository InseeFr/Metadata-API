package fr.insee.rmes.metadata.queries.parameters;

import fr.insee.rmes.metadata.model.TypeEnumInclusDansDepartement;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;

public record CommuneRequestParametizer(String code,
                                        LocalDate date,
                                        Class<?> typeOrigine,
                                        String filtreNom,
                                        String chefLieu,
                                        boolean com) implements ParametersForQuery<CommuneRequestParametizer> {

public CommuneRequestParametizer(String code,
                                 LocalDate date,
                                 Class<?> typeOrigine) {
    this(code, date, typeOrigine, "*", "none", true);
}
    @Override
    public ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent) {
        return ParametersForQuery.super.findParameterValueDecoder(recordComponent);
    }


}