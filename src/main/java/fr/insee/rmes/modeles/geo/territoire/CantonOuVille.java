package fr.insee.rmes.modeles.geo.territoire;


import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;

public class CantonOuVille extends Territoire {

    public CantonOuVille(String code) {
        this.type = EnumTypeGeographie.CANTON_OU_VILLE.getTypeObjetGeo();
        this.code = code;
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public CantonOuVille() {
        this.type = EnumTypeGeographie.CANTON_OU_VILLE.getTypeObjetGeo();
        this.intituleSansArticle = new IntituleSansArticle();
    }

}
