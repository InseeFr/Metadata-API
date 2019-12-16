package fr.insee.rmes.modeles.geo;

public enum EnumTypeGeographie {

    COMMUNE("Commune");

    private String typeObjetGeo;

    private EnumTypeGeographie(String typeObjetGeo) {
        this.typeObjetGeo = typeObjetGeo;
    }

    public String getTypeObjetGeo() {
        return typeObjetGeo;
    }

}
