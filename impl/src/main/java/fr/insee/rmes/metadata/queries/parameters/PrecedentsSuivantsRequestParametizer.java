package fr.insee.rmes.metadata.queries.parameters;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;

public record PrecedentsSuivantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   Class<?> typeOrigine,
                                                   boolean previous) implements ParametersForQuery<PrecedentsSuivantsRequestParametizer> {


    @Override
    public ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent) {
//        if ("filtreNom".equals(recordComponent.getName())){
//            return new ParameterValueDecoder.DelegaterDecoder<>(stringValue -> stringValue ==null?"*": stringValue.toString());
//        }
        return ParametersForQuery.super.findParameterValueDecoder(recordComponent);
    }
}

