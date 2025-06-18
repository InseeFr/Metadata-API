package fr.insee.rmes.metadata.queries.parameters;

import fr.insee.rmes.metadata.model.*;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;

public record AscendantsDescendantsRequestParametizer(String code,
                                                      LocalDate date,
                                                      TypeEnumAscendantsArrondissement typeEnumAscendantsArrondissement,
                                                      TypeEnumDescendantsDepartement typeEnumDescendantsDepartement,
                                                      TypeEnumAscendantsDepartement typeEnumAscendantsDepartement,
                                                      TypeEnumDescendantsCommune typeEnumDescendantsCommune,
                                                      TypeEnumAscendantsCommune typeEnumAscendantsCommune,
                                                      String filtreNom,
                                                      Class<?> typeOrigine,
                                                      boolean ascendant) implements ParametersForQuery<AscendantsDescendantsRequestParametizer> {

    //for geo/departement/{code}/descendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumDescendantsDepartement typeEnumDescendantsDepartement,
                                                   String filtreNom,
                                                   Class<?> typeOrigine) {
        this(code, date, null, typeEnumDescendantsDepartement, null,null, null,filtreNom, typeOrigine, false);
    }

    //for geo/arrondissement/{code}/ascendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumAscendantsArrondissement typeEnum,
                                                   Class<?> typeOrigine) {
        this(code, date, typeEnum, null, null, null, null,null,typeOrigine, true);
    }
    //for geo/departement/{code}/ascendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumAscendantsDepartement typeEnum,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, typeEnum, null, null,null,typeOrigine, true);
    }



    //for geo/commune/{code}/descendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumDescendantsCommune typeEnumDescendantsCommune,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, typeEnumDescendantsCommune,null, null, typeOrigine, false);
    }

    //for geo/commune/{code}/ascendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumAscendantsCommune typeEnumAscendantsCommune,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, null, typeEnumAscendantsCommune, null, typeOrigine, true);
    }


    @Override
    public ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent) {
        if ("filtreNom".equals(recordComponent.getName())){
            return new ParameterValueDecoder.DelegaterDecoder<>(stringValue -> stringValue ==null?"*": stringValue.toString());
        }
        return ParametersForQuery.super.findParameterValueDecoder(recordComponent);
    }
}

