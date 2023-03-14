package fr.insee.rmes.modeles.geo.territoire;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.IntituleSansArticle;
import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "BassinDeVie2022")
@JacksonXmlRootElement(localName = "BassinDeVie2022")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Objet représentant un bassin de vie")
public class BassinDeVie2022 extends Territoire {

    // No-args constructor needed for JAXB
    public BassinDeVie2022() {
        this.type = EnumTypeGeographie.BASSINDEVIE.getTypeObjetGeo();
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public BassinDeVie2022(String code) {
        this.type = EnumTypeGeographie.BASSINDEVIE.getTypeObjetGeo();
        this.code = code;
        this.intituleSansArticle = new IntituleSansArticle();
    }

    public BassinDeVie2022(
        String code,
        String uri,
        String intitule,
        String type,
        String dateCreation,
        String dateSuppression,
        IntituleSansArticle intituleSansArticle,
        String chefLieu) {
        super(code, uri, intitule, type, dateCreation, dateSuppression, intituleSansArticle, chefLieu);
    }


}
