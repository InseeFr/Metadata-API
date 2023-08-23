package fr.insee.rmes.modeles.geo;

import java.util.Optional;
import java.util.stream.Stream;

import fr.insee.rmes.modeles.geo.territoire.*;
import fr.insee.rmes.modeles.geo.territoires.*;
import fr.insee.rmes.utils.Constants;

public enum EnumTypeGeographie {

	COMMUNE("Commune", Commune.class,Communes.class, Constants.NONE),
	REGION("Region", Region.class,Regions.class, "prefectureDeRegion"),
	DEPARTEMENT("Departement", Departement.class,Departements.class, "prefecture"),
	ARRONDISSEMENT("Arrondissement", Arrondissement.class,Arrondissements.class, "sousPrefecture"),
	CANTON("Canton", Canton.class,Cantons.class,Constants.NONE),
	CANTON_OU_VILLE("CantonOuVille", CantonOuVille.class, CantonsEtVilles.class, Constants.NONE),
	COLLECTIVITE_D_OUTRE_MER("CollectiviteDOutreMer", CollectiviteDOutreMer.class,CollectivitesDOutreMer.class, Constants.NONE),
	COMMUNE_DELEGUEE("CommuneDeleguee", CommuneDeleguee.class,CommunesDeleguees.class,Constants.NONE),
	COMMUNE_ASSOCIEE("CommuneAssociee", CommuneAssociee.class,CommunesAssociees.class,Constants.NONE),
	ARRONDISSEMENT_MUNICIPAL("ArrondissementMunicipal",ArrondissementMunicipal.class,ArrondissementsMunicipaux.class, Constants.NONE),
	ZONE_EMPLOI("ZoneDEmploi2020", ZoneDEmploi2020.class,ZonesDEmploi2020.class,Constants.NONE),
	AIRE_ATTRACTION("AireDAttractionDesVilles2020",AireDAttractionDesVilles2020.class,AiresDAttractionDesVilles2020.class,Constants.NONE),
	UNITE_URBAINE("UniteUrbaine2020", UniteUrbaine2020.class,UnitesUrbaines2020.class,Constants.NONE),
	DISTRICT("District",District.class,Districts.class,Constants.NONE),
	CIRCONSCRIPTION_TERRITORIALE("CirconscriptionTerritoriale",CirconscriptionTerritoriale.class,CirconscriptionsTerritoriales.class,Constants.NONE),
	INTERCOMMUNALITE("Intercommunalite",Intercommunalite.class,Intercommunalites.class,Constants.NONE),
	BASSINDEVIE("BassinDeVie2022",BassinDeVie2022.class,BassinsDeVie2022.class,Constants.NONE);
	
	
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
