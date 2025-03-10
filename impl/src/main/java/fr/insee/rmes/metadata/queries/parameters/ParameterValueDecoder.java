package fr.insee.rmes.metadata.queries.parameters;

import fr.insee.rmes.metadata.model.TypeEnumInclusDansDepartement;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;
import java.util.function.Function;

@FunctionalInterface
interface ParameterValueDecoder<T>{

    String STRING_CLASS = "java.lang.String";
    String BOOLEAN_CLASS = "boolean";
    String CLASS_CLASS = "java.lang.Class";
    String LOCALE_DATE_CLASS = "java.time.LocalDate";
    String ENUM_INCLUS_DANS_DEPARTEMENT_CLASS= "fr.insee.rmes.metadata.model.TypeEnumInclusDansDepartement";

    static <U> ParameterValueDecoder<U> of(Class<U> type) {
        return switch (type.getName()){
            case BOOLEAN_CLASS /*boolean ?*/-> bool -> (boolean) bool ?"true":"false";
            case CLASS_CLASS -> clazz -> ((Class<?>)clazz).getSimpleName();
            case STRING_CLASS -> String::valueOf;
            case LOCALE_DATE_CLASS -> localDate -> String.valueOf(localDate==null?LocalDate.now():localDate);
            case ENUM_INCLUS_DANS_DEPARTEMENT_CLASS -> enumDepValue -> enumDepValue ==null?"none": ((TypeEnumInclusDansDepartement)enumDepValue).getValue();
            case String ignored when Enum.class.isAssignableFrom(type) -> simpleEnum -> ((Enum<?>)simpleEnum).name();
            default -> throw new IllegalArgumentException("Unsupported type: " + type.getName());
        };

    }

    String decode(T value);

    /**
     * Class for custom decoder in {@link ParametersForQuery} records such as {@link DescendantsRequestParametizer} which
     * need to customize decoder for a type which has yet a standard decoder in the children of ParameterValueDecoder
     * <p>
     * For example {@link DescendantsRequestParametizer} needs a custom treatment for Strings for attribute <code>filtreNom</code>.
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
     * used when explicitly instanced in a method such as {@link DescendantsRequestParametizer#findParameterValueDecoder(RecordComponent)}
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
