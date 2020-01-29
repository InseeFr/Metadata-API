package fr.insee.rmes.modeles.geo;

import java.util.stream.Stream;

public enum EnumTypeGeographie {

    COMMUNE("Commune"), REGION("Region"), DEPARTEMENT("Departement"), ARRONDISSEMENT("Arrondissement");

    private String typeObjetGeo;

    private EnumTypeGeographie(String typeObjetGeo) {
        this.typeObjetGeo = typeObjetGeo;
    }

    public String getTypeObjetGeo() {
        return typeObjetGeo;
    }

    public static Stream<EnumTypeGeographie> stream() {
        return Stream.of(EnumTypeGeographie.values());
    }

}
