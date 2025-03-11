package fr.insee.rmes.metadata.queries.parameters;

import fr.insee.rmes.metadata.model.TypeEnumInclusDansDepartement;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;

public record AscendantsDescendantsRequestParametizer(String code, LocalDate date,
                                                      TypeEnumInclusDansDepartement type,
                                                      String filtreNom,
                                                      Class<?> typeOrigine,
                                                      boolean ascendant) implements ParametersForQuery<AscendantsDescendantsRequestParametizer> {

    public static AscendantsDescendantsRequestParametizer ascendant(String code, LocalDate date,
                                                                    TypeEnumInclusDansDepartement type,
                                                                    String filtreNom,
                                                                    Class<?> typeOrigine) {
        return new AscendantsDescendantsRequestParametizer(code, date, type, filtreNom, typeOrigine, true);
    }

    public static AscendantsDescendantsRequestParametizer descendant(String code, LocalDate date,
                                                                     TypeEnumInclusDansDepartement type,
                                                                     String filtreNom,
                                                                     Class<?> typeOrigine) {
        return new AscendantsDescendantsRequestParametizer(code, date, type, filtreNom, typeOrigine, false);
    }



    @Override
    public ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent) {
        if ("filtreNom".equals(recordComponent.getName())){
            return new ParameterValueDecoder.DelegaterDecoder<>(stringValue -> stringValue ==null?"*": stringValue.toString());
        }
        return ParametersForQuery.super.findParameterValueDecoder(recordComponent);
    }
}

