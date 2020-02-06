package fr.insee.rmes.modeles.geo.territoire;

import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;

public class ArrondissementMunicipal extends Arrondissement {
    // No-args constructor needed for JAXB
    public ArrondissementMunicipal() {
        this.type = EnumTypeGeographie.ARRONDISSEMENT_MUNICIPAL.getTypeObjetGeo();
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public ArrondissementMunicipal(String code) {
        this.type = EnumTypeGeographie.ARRONDISSEMENT_MUNICIPAL.getTypeObjetGeo();
        this.code = code;
        this.intituleSansArticle = new IntituleSansArticle();
    }
}
