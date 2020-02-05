package fr.insee.rmes.modeles.geo;

import java.util.Optional;
import java.util.stream.Stream;

import fr.insee.rmes.modeles.geo.territoire.Arrondissement;
import fr.insee.rmes.modeles.geo.territoire.Commune;
import fr.insee.rmes.modeles.geo.territoire.Departement;
import fr.insee.rmes.modeles.geo.territoire.Region;
import fr.insee.rmes.modeles.geo.territoire.Territoire;

public enum EnumTypeGeographie {

    COMMUNE("Commune", Commune.class),
    REGION("Region", Region.class),
    DEPARTEMENT("Departement", Departement.class),
    ARRONDISSEMENT("Arrondissement", Arrondissement.class);

    private String typeObjetGeo;

    private Class<? extends Territoire> classNameOfGeoType;

    private <T> EnumTypeGeographie(String typeObjetGeo, Class<? extends Territoire> classNameOfGeoType) {
        this.typeObjetGeo = typeObjetGeo;
        this.classNameOfGeoType = classNameOfGeoType;
    }

    public String getTypeObjetGeo() {
        return typeObjetGeo;
    }

    public <T> Class<? extends Territoire> getClassNameOfGeoType() {
        return classNameOfGeoType;
    }

    public static Stream<EnumTypeGeographie> streamValuesTypeGeo() {
        return Stream.of(EnumTypeGeographie.values());
    }

    public static <T> Class<? extends Territoire> getClassByType(String type) {
        Optional<EnumTypeGeographie> optionalClass =
            streamValuesTypeGeo().filter(s -> s.getTypeObjetGeo().equalsIgnoreCase(type)).findAny();
        return optionalClass.isPresent() ? optionalClass.get().getClassNameOfGeoType() : null;
    }

}
