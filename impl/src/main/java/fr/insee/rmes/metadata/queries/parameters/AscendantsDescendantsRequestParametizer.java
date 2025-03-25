package fr.insee.rmes.metadata.queries.parameters;

import fr.insee.rmes.metadata.model.TypeEnumContenantDepartement;
import fr.insee.rmes.metadata.model.TypeEnumInclusDansDepartement;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;

public record AscendantsDescendantsRequestParametizer(String code,
                                                      LocalDate date,
                                                      TypeEnumInclusDansDepartement type,
                                                      TypeEnumContenantDepartement typeContenant,
                                                      String filtreNom,
                                                      Class<?> typeOrigine,
                                                      boolean ascendant) implements ParametersForQuery<AscendantsDescendantsRequestParametizer> {

    //for geo/departement/{code}/descendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumInclusDansDepartement typeEnum,
                                                   String filtreNom,
                                                   Class<?> typeOrigine) {
        this(code, date, typeEnum, null, filtreNom, typeOrigine, false);
    }

    //for geo/departement/{code}/ascendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumContenantDepartement typeEnum,
                                                   Class<?> typeOrigine) {
        this(code, date, null, typeEnum, null, typeOrigine, true);
    }


    @Override
    public ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent) {
        if ("filtreNom".equals(recordComponent.getName())){
            return new ParameterValueDecoder.DelegaterDecoder<>(stringValue -> stringValue ==null?"*": stringValue.toString());
        }
        return ParametersForQuery.super.findParameterValueDecoder(recordComponent);
    }
}

