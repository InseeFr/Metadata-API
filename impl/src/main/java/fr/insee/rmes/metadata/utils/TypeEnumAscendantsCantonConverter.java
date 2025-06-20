package fr.insee.rmes.metadata.utils;

import fr.insee.rmes.metadata.model.TypeEnumAscendantsCanton;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class TypeEnumAscendantsCantonConverter implements Converter<String, TypeEnumAscendantsCanton> {
    @Override
    public TypeEnumAscendantsCanton convert(String source) {
        for (TypeEnumAscendantsCanton type : TypeEnumAscendantsCanton.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumAscendantsCanton : " + source);
    }
}