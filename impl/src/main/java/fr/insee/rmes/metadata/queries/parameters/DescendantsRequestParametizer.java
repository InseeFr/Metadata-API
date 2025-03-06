package fr.insee.rmes.metadata.queries.parameters;

import fr.insee.rmes.metadata.model.TypeEnumInclusDansDepartement;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;

public record DescendantsRequestParametizer(String code, LocalDate date,
                                            TypeEnumInclusDansDepartement type,
                                            String filtreNom,
                                            Class<?> typeOrigine,
                                            boolean ascendant) implements ParametersForQuery<DescendantsRequestParametizer> {
    public DescendantsRequestParametizer(String code, LocalDate date, TypeEnumInclusDansDepartement type, String filtreNomDescendant, Class<?> typeOrigine) {
        this(code, date, type, filtreNomDescendant, typeOrigine, false);
    }

    @Override
    public ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent) {
        if ("filtreNom".equals(recordComponent.getName())){
            return new ParameterValueDecoder.DelegaterDecoder<>(stringValue -> stringValue ==null?"*": stringValue.toString());
        }
        return ParametersForQuery.super.findParameterValueDecoder(recordComponent);
    }
}

