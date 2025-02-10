package fr.insee.rmes.metadata.queryexecutor;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public interface ParametersForQuery {
    default Map<String, Object> toParameters(){
        Map<String, Object> parameters = new HashMap<>();
        ReflectionUtils.doWithLocalFields(this.getClass(), field -> addFieldsToParameters(parameters, field));
        return parameters;
    }

    default void addFieldsToParameters(Map<String, Object> parameters, Field field) {
        Object rawValue = ReflectionUtils.getField(field, this);
        String fieldName = field.getName();
        var decodedValue = decodeValueForField(rawValue, fieldName);
        parameters.put(fieldName, decodedValue);
    }

    default Object decodeValueForField(Object rawValue, String fieldName) {
        return switch (rawValue) {
            case LocalDate date -> date.toString();
            case Enum<?> enumeration -> enumeration.toString();
            default -> rawValue;
        };
    }
}
