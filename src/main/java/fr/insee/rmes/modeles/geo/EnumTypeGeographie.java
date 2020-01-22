package fr.insee.rmes.modeles.geo;

public enum EnumTypeGeographie {

    COMMUNE("Commune"), REGION("Region"), DEPARTEMENT("Departement"), ARRONDISSEMENT("Arrondissement");

    private String typeObjetGeo;

    private EnumTypeGeographie(String typeObjetGeo) {
        this.typeObjetGeo = typeObjetGeo;
    }

    public String getTypeObjetGeo() {
        return typeObjetGeo;
    }

}
