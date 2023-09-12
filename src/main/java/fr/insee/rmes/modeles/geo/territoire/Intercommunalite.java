package fr.insee.rmes.modeles.geo.territoire;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;
import io.swagger.v3.oas.annotations.media.Schema;


@XmlRootElement(name = "Intercommunalite")
@JacksonXmlRootElement(localName = "Intercommunalite")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant une intercommunalite")

public class Intercommunalite extends Territoire {
	
	public Intercommunalite() {
        this.type = EnumTypeGeographie.INTERCOMMUNALITE.getTypeObjetGeo();
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public Intercommunalite(String code) {
        this.type = EnumTypeGeographie.INTERCOMMUNALITE.getTypeObjetGeo();
        this.code = code;    
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public Intercommunalite(
        String code,
        String uri,
        String intitule,
        String type,
        String dateCreation,
        String dateSuppression,
        IntituleSansArticle intituleSansArticle,
        String intituleComplet,
        String categorieJuridique,
        String typeArticle) {
        super(code, uri, intitule, type, dateCreation, dateSuppression, intituleSansArticle,intituleComplet,categorieJuridique);
        this.setTypeArticle(typeArticle);
    }

}
