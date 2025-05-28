package fr.insee.rmes.metadata.queries.parameters;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;

public record TerritoireRequestParametizer(String code,
                                           LocalDate date,
                                           Class<?> typeOrigine,
                                           String filtreNom,
                                           String chefLieu,
                                           boolean com) implements ParametersForQuery<TerritoireRequestParametizer> {

    //for geo/departement/{code} and geo/region/{code}
    public TerritoireRequestParametizer(String code,
                                        LocalDate date,
                                        String chefLieu,
                                        Class<?> typeOrigine) {
        this(code, date, typeOrigine, "*", chefLieu, true);
    }

    //for geo/intercommunalite/{code}
    public TerritoireRequestParametizer(String code,
                                        LocalDate date,
                                        Class<?> typeOrigine) {
        this(code, date, typeOrigine, "*", "prefecture", true);
    }

    //for geo/departements
    public TerritoireRequestParametizer(LocalDate date,
                                        Class<?> typeOrigine) {
        this("none", date, typeOrigine, "*", "prefecture", true);
    }

    //for geo/communes
    public TerritoireRequestParametizer(LocalDate date,
                                        String filtreNom,
                                        Class<?> typeOrigine,
                                        boolean com) {
        this("none", date, typeOrigine, filtreNom, "none", com);
    }

    //for geo/iris/{code} (hasIrisDescendant)
    public TerritoireRequestParametizer(String code,
                                        Class<?> typeOrigine) {
        this(code, LocalDate.now(), typeOrigine, "*", "prefecture", true);
    }

    @Override
    public ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent) {
        return ParametersForQuery.super.findParameterValueDecoder(recordComponent);
    }


}