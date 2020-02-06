package fr.insee.rmes.modeles.geo.territoire;

import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;

public class CommuneDeleguee extends Territoire {
    // No-args constructor needed for JAXB
    public CommuneDeleguee() {
        this.type = EnumTypeGeographie.COMMUNE_DELEGUEE.getTypeObjetGeo();
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public CommuneDeleguee(String code) {
        this.type = EnumTypeGeographie.COMMUNE_DELEGUEE.getTypeObjetGeo();
        this.code = code;
        this.intituleSansArticle = new IntituleSansArticle();
    }
}
