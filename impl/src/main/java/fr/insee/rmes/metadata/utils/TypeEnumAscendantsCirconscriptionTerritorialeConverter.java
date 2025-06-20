package fr.insee.rmes.metadata.utils;

import fr.insee.rmes.metadata.model.TypeEnumAscendantsCirconscriptionTerritoriale;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;



@Component
public class TypeEnumAscendantsCirconscriptionTerritorialeConverter implements Converter<String, TypeEnumAscendantsCirconscriptionTerritoriale> {
    @Override
    public TypeEnumAscendantsCirconscriptionTerritoriale convert(String source) {
        for (TypeEnumAscendantsCirconscriptionTerritoriale type : TypeEnumAscendantsCirconscriptionTerritoriale.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumAscendantsCirconscriptionTerritoriale : " + source);
    }
}
