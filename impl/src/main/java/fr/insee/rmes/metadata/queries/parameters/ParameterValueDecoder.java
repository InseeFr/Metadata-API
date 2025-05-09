package fr.insee.rmes.metadata.queries.parameters;

import fr.insee.rmes.metadata.model.TypeEnumAscendantsDepartement;
import fr.insee.rmes.metadata.model.TypeEnumAscendantsCommune;
import fr.insee.rmes.metadata.model.TypeEnumDescendantsCommune;
import fr.insee.rmes.metadata.model.TypeEnumDescendantsDepartement;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;
import java.util.function.Function;

@FunctionalInterface
interface ParameterValueDecoder<T>{

    String STRING_CLASS = "java.lang.String";
    String BOOLEAN_CLASS = "boolean";
    String CLASS_CLASS = "java.lang.Class";
    String LOCALE_DATE_CLASS = "java.time.LocalDate";
    String ENUM_DESCENDANTS_DEPARTEMENT_CLASS= "fr.insee.rmes.metadata.model.TypeEnumDescendantsDepartement";
    String ENUM_ASCENDANTS_DEPARTEMENT_CLASS= "fr.insee.rmes.metadata.model.TypeEnumAscendantsDepartement";
    String ENUM_DESCENDANTS_COMMUNE_CLASS= "fr.insee.rmes.metadata.model.TypeEnumDescendantsCommune";
    String ENUM_ASCENDANTS_COMMUNE_CLASS = "fr.insee.rmes.metadata.model.TypeEnumAscendantsCommune";

    static <U> ParameterValueDecoder<U> of(Class<U> type) {
        return switch (type.getName()){
            case BOOLEAN_CLASS -> bool -> (boolean) bool ?"true":"false";
            case CLASS_CLASS -> clazz -> ((Class<?>)clazz).getSimpleName();
            case STRING_CLASS -> String::valueOf;
            case LOCALE_DATE_CLASS -> localDate -> String.valueOf(localDate==null?LocalDate.now():localDate);
            case ENUM_DESCENDANTS_DEPARTEMENT_CLASS -> enumDepValue -> enumDepValue ==null?"none": ((TypeEnumDescendantsDepartement)enumDepValue).getValue();
            case ENUM_ASCENDANTS_DEPARTEMENT_CLASS -> enumDepValue -> enumDepValue ==null?"none": ((TypeEnumAscendantsDepartement)enumDepValue).getValue();
            case ENUM_DESCENDANTS_COMMUNE_CLASS -> enumDepValue -> enumDepValue ==null?"none": ((TypeEnumDescendantsCommune)enumDepValue).getValue();
            case ENUM_ASCENDANTS_COMMUNE_CLASS -> enumComValue -> enumComValue ==null?"none": ((TypeEnumAscendantsCommune)enumComValue).getValue();
            case String ignored when Enum.class.isAssignableFrom(type) -> simpleEnum -> ((Enum<?>)simpleEnum).name();
            default -> throw new IllegalArgumentException("Unsupported type: " + type.getName());
        };

    }

    String decode(T value);

    /**
     * Class for custom decoder in {@link ParametersForQuery} records such as {@link AscendantsDescendantsRequestParametizer} which
     * need to customize decoder for a type which has yet a standard decoder in the children of ParameterValueDecoder
     * <p>
     * For example {@link AscendantsDescendantsRequestParametizer} needs a custom treatment for Strings for attribute <code>filtreNom</code>.
     * So it overrides the method {@link  ParametersForQuery#findParameterValueDecoder(RecordComponent)} and for the attribute
     * <code>filtreNom</code>, it returns its custom decoder with this kind of code :
     * <code>
     * if ("filtreNom".equals(recordComponent.getName())){
     *    return new ParameterValueDecoder.DelegaterDecoder<String>(value -> value ==null?"*": value);
     * }
     * </code>
     * <p>
     * The DelegaterDecoder is never be returned by
     * { @link fr.insee.rmes.metadata.queries.parameters.ParameterValueDecoder#of(java.lang.Class)} and can only be
     * used when explicitly instanced in a method such as {@link AscendantsDescendantsRequestParametizer#findParameterValueDecoder(RecordComponent)}
     *
     * @param delegatedDecoder : a function applied to decode the value
     * @param <U> : the type for which the instance will decode
     */
    record DelegaterDecoder<U>(Function<U, String> delegatedDecoder) implements ParameterValueDecoder<U> {
        @Override
        public String decode(U value) {
            return delegatedDecoder.apply(value);
        }
    }

}
