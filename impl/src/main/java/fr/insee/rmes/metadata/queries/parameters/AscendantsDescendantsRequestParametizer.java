package fr.insee.rmes.metadata.queries.parameters;

import fr.insee.rmes.metadata.model.TypeEnumContenantCommune;
import fr.insee.rmes.metadata.model.TypeEnumAscendantsDepartement;
import fr.insee.rmes.metadata.model.TypeEnumDescendantsDepartement;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;

public record AscendantsDescendantsRequestParametizer(String code,
                                                      LocalDate date,
                                                      TypeEnumDescendantsDepartement typeEnumDescendantsDepartement,
                                                      TypeEnumAscendantsDepartement typeEnumAscendantsDepartement,
                                                      TypeEnumContenantCommune typeContenantCommune,
                                                      String filtreNom,
                                                      Class<?> typeOrigine,
                                                      boolean ascendant) implements ParametersForQuery<AscendantsDescendantsRequestParametizer> {

    //for geo/departement/{code}/descendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumDescendantsDepartement typeEnumDescendantsDepartement,
                                                   String filtreNom,
                                                   Class<?> typeOrigine) {
        this(code, date, typeEnumDescendantsDepartement, null,null, filtreNom, typeOrigine, false);
    }

    //for geo/departement/{code}/ascendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumAscendantsDepartement typeEnum,
                                                   Class<?> typeOrigine) {
        this(code, date, null, typeEnum, null, null, typeOrigine, true);
    }

    //for geo/commune/{code}/ascendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumContenantCommune typeEnum,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, typeEnum, null, typeOrigine, true);
    }


    @Override
    public ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent) {
        if ("filtreNom".equals(recordComponent.getName())){
            return new ParameterValueDecoder.DelegaterDecoder<>(stringValue -> stringValue ==null?"*": stringValue.toString());
        }
        return ParametersForQuery.super.findParameterValueDecoder(recordComponent);
    }
}

