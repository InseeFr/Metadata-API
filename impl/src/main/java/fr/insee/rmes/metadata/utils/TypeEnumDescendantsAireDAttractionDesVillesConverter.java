package fr.insee.rmes.metadata.utils;

import fr.insee.rmes.metadata.model.TypeEnumDescendantsAireDAttractionDesVilles;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TypeEnumDescendantsAireDAttractionDesVillesConverter implements Converter<String, TypeEnumDescendantsAireDAttractionDesVilles> {
    @Override
    public TypeEnumDescendantsAireDAttractionDesVilles convert(String source) {
        for (TypeEnumDescendantsAireDAttractionDesVilles type : TypeEnumDescendantsAireDAttractionDesVilles.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumDescendantsAireDAttractionDesVilles: " + source);
    }
}
