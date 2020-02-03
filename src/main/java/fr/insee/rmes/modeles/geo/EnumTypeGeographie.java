package fr.insee.rmes.modeles.geo;

import java.util.Optional;
import java.util.stream.Stream;

public enum EnumTypeGeographie {

    COMMUNE("Commune", Commune.class),
    REGION("Region", Region.class),
    DEPARTEMENT("Departement", Departement.class),
    ARRONDISSEMENT("Arrondissement", Arrondissement.class);

    private String typeObjetGeo;

    private Class<T> classNameOfGeoType;

    private <T> EnumTypeGeographie(String typeObjetGeo, Class<T> classNameOfGeoType) {
        this.typeObjetGeo = typeObjetGeo;
        this.classNameOfGeoType = classNameOfGeoType;
    }

    public String getTypeObjetGeo() {
        return typeObjetGeo;
    }

    public <T> Class<T> getClassNameOfGeoType() {
        return classNameOfGeoType;
    }

    public static Stream<EnumTypeGeographie> streamValuesTypeGeo() {
        return Stream.of(EnumTypeGeographie.values());
    }

    public static <T> Class<T> getClassByType(String type) {
        Optional<EnumTypeGeographie> optionalClass =
            streamValuesTypeGeo().filter(s -> s.getTypeObjetGeo().equalsIgnoreCase(type)).findAny();
        return optionalClass.isPresent() ? optionalClass.get().getClassNameOfGeoType() : null;
    }

}
