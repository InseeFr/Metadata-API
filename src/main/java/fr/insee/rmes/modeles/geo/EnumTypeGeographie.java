package fr.insee.rmes.modeles.geo;

import java.util.Optional;
import java.util.stream.Stream;

import fr.insee.rmes.modeles.geo.territoire.AireAttraction;
import fr.insee.rmes.modeles.geo.territoire.Arrondissement;
import fr.insee.rmes.modeles.geo.territoire.ArrondissementMunicipal;
import fr.insee.rmes.modeles.geo.territoire.Canton;
import fr.insee.rmes.modeles.geo.territoire.CollectiviteDOutreMer;
import fr.insee.rmes.modeles.geo.territoire.Commune;
import fr.insee.rmes.modeles.geo.territoire.CommuneAssociee;
import fr.insee.rmes.modeles.geo.territoire.CommuneDeleguee;
import fr.insee.rmes.modeles.geo.territoire.Departement;
import fr.insee.rmes.modeles.geo.territoire.District;
import fr.insee.rmes.modeles.geo.territoire.Region;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoire.UniteUrbaine;
import fr.insee.rmes.modeles.geo.territoire.ZoneEmploi;
import fr.insee.rmes.modeles.geo.territoires.AiresAttraction;
import fr.insee.rmes.modeles.geo.territoires.Arrondissements;
import fr.insee.rmes.modeles.geo.territoires.ArrondissementsMunicipaux;
import fr.insee.rmes.modeles.geo.territoires.Cantons;
import fr.insee.rmes.modeles.geo.territoires.CollectivitesDOutreMer;
import fr.insee.rmes.modeles.geo.territoires.Communes;
import fr.insee.rmes.modeles.geo.territoires.CommunesAssociees;
import fr.insee.rmes.modeles.geo.territoires.CommunesDeleguees;
import fr.insee.rmes.modeles.geo.territoires.Departements;
import fr.insee.rmes.modeles.geo.territoires.Districts;
import fr.insee.rmes.modeles.geo.territoires.Regions;
import fr.insee.rmes.modeles.geo.territoires.Territoires;
import fr.insee.rmes.modeles.geo.territoires.UnitesUrbaines;
import fr.insee.rmes.modeles.geo.territoires.ZonesEmploi;
import fr.insee.rmes.utils.Constants;

public enum EnumTypeGeographie {

	COMMUNE("Commune", Commune.class,Communes.class, Constants.NONE),
	REGION("Region", Region.class,Regions.class, "prefectureDeRegion"),
	DEPARTEMENT("Departement", Departement.class,Departements.class, "prefecture"),
	ARRONDISSEMENT("Arrondissement", Arrondissement.class,Arrondissements.class, "sousPrefecture"),
	CANTON("Canton", Canton.class,Cantons.class,Constants.NONE),
	COLLECTIVITE_D_OUTRE_MER("CollectiviteDOutreMer", CollectiviteDOutreMer.class,CollectivitesDOutreMer.class, Constants.NONE),
	COMMUNE_DELEGUEE("CommuneDeleguee", CommuneDeleguee.class,CommunesDeleguees.class,Constants.NONE),
	COMMUNE_ASSOCIEE("CommuneAssociee", CommuneAssociee.class,CommunesAssociees.class,Constants.NONE),
	ARRONDISSEMENT_MUNICIPAL("ArrondissementMunicipal",ArrondissementMunicipal.class,ArrondissementsMunicipaux.class, Constants.NONE),
	ZONE_EMPLOI("ZoneDEmploi2020", ZoneEmploi.class,ZonesEmploi.class,Constants.NONE),
	AIRE_ATTRACTION("AireDAttractionDesVilles2020",AireAttraction.class,AiresAttraction.class,Constants.NONE),
	UNITE_URBAINE("UniteUrbaine2020", UniteUrbaine.class,UnitesUrbaines.class,Constants.NONE),
	DISTRICT("District",District.class,Districts.class,Constants.NONE);

	private String typeObjetGeo;
	private Class<? extends Territoire> classNameOfGeoType;
	private Class<? extends Territoires> classPluralGeoType;
	private String chefLieuPredicate;

	private <T> EnumTypeGeographie(
			String typeObjetGeo,
			Class<? extends Territoire> classNameOfGeoType,
			Class<? extends Territoires> classPluralGeoType,
			String chefLieuPredicate) {
		this.typeObjetGeo = typeObjetGeo;
		this.classNameOfGeoType = classNameOfGeoType;
		this.classPluralGeoType = classPluralGeoType;
		this.chefLieuPredicate = chefLieuPredicate;
	}

	public String getTypeObjetGeo() {
		return typeObjetGeo;
	}

	public Class<? extends Territoire> getClassNameOfGeoType() {
		return classNameOfGeoType;
	}


	public Class<? extends Territoires> getClassPluralGeoType() {
		return classPluralGeoType;
	}

	public static Stream<EnumTypeGeographie> streamValuesTypeGeo() {
		return Stream.of(EnumTypeGeographie.values());
	}

	public static Class<? extends Territoire> getClassByType(String type) {
		Optional<EnumTypeGeographie> optionalClass = getOptionalEnumType(type);
		return optionalClass.isPresent() ? optionalClass.get().getClassNameOfGeoType() : null;
	}

	public static Class<? extends Territoires> getPluralClassByType(String type) {
		Optional<EnumTypeGeographie> optionalClass = getOptionalEnumType(type);
		return optionalClass.isPresent() ? optionalClass.get().getClassPluralGeoType() : null;
	}

	private static Optional<EnumTypeGeographie> getOptionalEnumType(String type) {           
		return streamValuesTypeGeo().filter(s -> s.getTypeObjetGeo().equalsIgnoreCase(type)).findAny();
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
