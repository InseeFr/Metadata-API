package fr.insee.rmes.metadata.queries.parameters;

import fr.insee.rmes.metadata.model.TypeEnumContenantDepartement;
import fr.insee.rmes.metadata.model.TypeEnumInclusDansDepartement;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;

public record PrecedentsRequestParametizer(String code,
                                           LocalDate date,
                                           Class<?> typeOrigine,
                                           boolean previous) implements ParametersForQuery<PrecedentsRequestParametizer> {

    //for geo/departement/{code}/precedents
    public PrecedentsRequestParametizer(String code,
                                        LocalDate date,
                                        Class<?> typeOrigine) {
        this(code, date, typeOrigine, true);
    }



    @Override
    public ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent) {
//        if ("filtreNom".equals(recordComponent.getName())){
//            return new ParameterValueDecoder.DelegaterDecoder<>(stringValue -> stringValue ==null?"*": stringValue.toString());
//        }
        return ParametersForQuery.super.findParameterValueDecoder(recordComponent);
    }
}

