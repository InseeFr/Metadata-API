package fr.insee.rmes.metadata.queryexecutor;

import fr.insee.rmes.metadata.model.TypeEnumInclusDansDepartement;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public interface ParametersForQuery {
    default Map<String, Object> toParameters(){
        Map<String, Object> parameters = new HashMap<>();

        ReflectionUtils.doWithLocalFields(this.getClass(), field -> {
            try {
                field.setAccessible(true); // Assure l'accès aux champs privés

                if ("date".equals(field.getName())) {
                    addDateFieldToParameters(parameters, field);
                } else {
                    addFieldsToParameters(parameters, field);
                }
            } catch (Exception e) {
                throw new RuntimeException("Erreur lors du traitement du champ : " + field.getName(), e);
            }
        });

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
            case LocalDate date -> date.toString();//normalement on n'est jamais dans ce cas : si le field est date, on appelle la méthode addDateFieldToParameters
            case Enum<?> enumeration -> enumeration.toString();
            default -> rawValue;
        };
    }

    default void addDateFieldToParameters(Map<String, Object> parameters, Field field) {
        Object rawValue = ReflectionUtils.getField(field, this);
        String fieldName = field.getName();
        var decodeValueDate = decodeValueDateForField(rawValue);
        parameters.put(fieldName, decodeValueDate);
    }

    default Object decodeValueDateForField(Object date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return date.toString();
    }

}
