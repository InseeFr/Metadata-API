package fr.insee.rmes.metadata.utils;

import fr.insee.rmes.metadata.model.TypeEnumContenantDepartement;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TypeEnumContenantDepartementConverter implements Converter<String, TypeEnumContenantDepartement> {
    @Override
    public TypeEnumContenantDepartement convert(String source) {
        for (TypeEnumContenantDepartement type : TypeEnumContenantDepartement.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumInclusDansDepartement: " + source);
    }
}
