package fr.insee.rmes.metadata.utils;



import fr.insee.rmes.metadata.model.TypeEnumContenantCommune;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TypeEnumContenantCommuneConverter implements Converter<String, TypeEnumContenantCommune> {
    @Override
    public TypeEnumContenantCommune convert(String source) {
        for (TypeEnumContenantCommune type : TypeEnumContenantCommune.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumInclusDansCommune: " + source);
    }
}

