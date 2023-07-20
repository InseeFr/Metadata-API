package fr.insee.rmes.modeles.geo.territoire;


import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;

public class CantonOuVille extends Territoire {

    public CantonOuVille(String code) {
        this.type = EnumTypeGeographie.CANTON.getTypeObjetGeo();
        this.code = code;
        this.intituleSansArticle = new IntituleSansArticle();
    }

}
