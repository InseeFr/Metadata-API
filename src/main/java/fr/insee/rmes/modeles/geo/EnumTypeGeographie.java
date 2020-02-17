package fr.insee.rmes.modeles.geo;

import java.util.Optional;
import java.util.stream.Stream;

import fr.insee.rmes.modeles.geo.territoire.Arrondissement;
import fr.insee.rmes.modeles.geo.territoire.ArrondissementMunicipal;
import fr.insee.rmes.modeles.geo.territoire.Canton;
import fr.insee.rmes.modeles.geo.territoire.Commune;
import fr.insee.rmes.modeles.geo.territoire.CommuneAssociee;
import fr.insee.rmes.modeles.geo.territoire.CommuneDeleguee;
import fr.insee.rmes.modeles.geo.territoire.Departement;
import fr.insee.rmes.modeles.geo.territoire.Region;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.utils.Constants;

public enum EnumTypeGeographie {

    COMMUNE("Commune", Commune.class, Constants.NONE),
    REGION("Region", Region.class, "prefectureDeRegion"),
    DEPARTEMENT("Departement", Departement.class, "prefecture"),
    ARRONDISSEMENT("Arrondissement", Arrondissement.class, "sousPrefecture"),
    CANTON("Canton", Canton.class, Constants.NONE),
    COMMUNE_DELEGUEE("CommuneDeleguee", CommuneDeleguee.class, Constants.NONE),
    COMMUNE_ASSOCIEE("CommuneAssociee", CommuneAssociee.class, Constants.NONE),
    ARRONDISSEMENT_MUNICIPAL("ArrondissementMunicipal", ArrondissementMunicipal.class, Constants.NONE);

    private String typeObjetGeo;

    private Class<? extends Territoire> classNameOfGeoType;

    private String chefLieuPredicate;

    private <T> EnumTypeGeographie(
        String typeObjetGeo,
        Class<? extends Territoire> classNameOfGeoType,
        String chefLieuPredicate) {
        this.typeObjetGeo = typeObjetGeo;
        this.classNameOfGeoType = classNameOfGeoType;
        this.chefLieuPredicate = chefLieuPredicate;
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

    public static String getTypeObjetGeoIgnoreCase(String typeObjetGeo) {
        Optional<EnumTypeGeographie> enumTypeGeographie =
            EnumTypeGeographie
                .streamValuesTypeGeo()
                .filter(s -> s.getTypeObjetGeo().equalsIgnoreCase(typeObjetGeo))
                .findFirst();
        return enumTypeGeographie.isPresent() ? enumTypeGeographie.get().getTypeObjetGeo() : null;
    }

    public String getChefLieuPredicate() {
        return chefLieuPredicate;
    }

}
