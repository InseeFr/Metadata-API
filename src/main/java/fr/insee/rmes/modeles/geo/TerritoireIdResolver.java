package fr.insee.rmes.modeles.geo;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

public class TerritoireIdResolver extends TypeIdResolverBase {

    private JavaType superType;

    @Override
    public String idFromValue(Object value) {
        return this.idFromValueAndType(value, value.getClass());
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        String typeId = EnumTypeGeographie.getTypeObjetGeoIgnoreCase(suggestedType.getSimpleName());
        return typeId;
    }

    @Override
    public Id getMechanism() {
        return Id.NAME;
    }

    @Override
    public JavaType typeFromId(DatabindContext context, String id) {

        Class<?> subType = EnumTypeGeographie.getClassByType(id);
        return context.constructSpecializedType(superType, subType);
    }

}
