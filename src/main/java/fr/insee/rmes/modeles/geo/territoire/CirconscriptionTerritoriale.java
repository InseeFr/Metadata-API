package fr.insee.rmes.modeles.geo.territoire;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;
import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "CirconscriptionTerritoriale")
@JacksonXmlRootElement(localName = "CirconscriptionTerritoriale")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant une circonscription territoriale")

public class CirconscriptionTerritoriale extends Territoire {
	
	public CirconscriptionTerritoriale() {
        this.type = EnumTypeGeographie.CIRCONSCRIPTION_TERRITORIALE.getTypeObjetGeo();
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public CirconscriptionTerritoriale(String code) {
        this.type = EnumTypeGeographie.CIRCONSCRIPTION_TERRITORIALE.getTypeObjetGeo();
        this.code = code;
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public CirconscriptionTerritoriale(
        String code,
        String uri,
        String intitule,
        String type,
        String dateCreation,
        String dateSuppression,
        IntituleSansArticle intituleSansArticle,
        String typeArticle) {
        super(code, uri, intitule, type, dateCreation, dateSuppression, intituleSansArticle);
        this.setTypeArticle(typeArticle);
    }



}
