package fr.insee.rmes.modeles.geo.territoire;

import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;

public class CommuneAssociee extends Commune {

    // No-args constructor needed for JAXB
    public CommuneAssociee() {
        this.type = EnumTypeGeographie.COMMUNE_ASSOCIEE.getTypeObjetGeo();
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public CommuneAssociee(String code) {
        this.type = EnumTypeGeographie.COMMUNE_ASSOCIEE.getTypeObjetGeo();
        this.code = code;
        this.intituleSansArticle = new IntituleSansArticle();
    }
}
