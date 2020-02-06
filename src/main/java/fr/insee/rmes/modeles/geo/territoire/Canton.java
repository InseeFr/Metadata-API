package fr.insee.rmes.modeles.geo.territoire;

import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;

public class Canton extends Territoire {
    public Canton() {
        this.type = EnumTypeGeographie.CANTON.getTypeObjetGeo();
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public Canton(String code) {
        this.type = EnumTypeGeographie.CANTON.getTypeObjetGeo();
        this.code = code;
        this.intituleSansArticle = new IntituleSansArticle();
    }
}
