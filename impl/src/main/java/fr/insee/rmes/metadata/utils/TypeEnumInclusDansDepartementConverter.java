package fr.insee.rmes.metadata.utils;

import fr.insee.rmes.metadata.model.TypeEnumInclusDansDepartement;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TypeEnumInclusDansDepartementConverter implements Converter<String, TypeEnumInclusDansDepartement> {
    @Override
    public TypeEnumInclusDansDepartement convert(String source) {
        for (TypeEnumInclusDansDepartement type : TypeEnumInclusDansDepartement.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumInclusDansDepartement: " + source);
    }
}
