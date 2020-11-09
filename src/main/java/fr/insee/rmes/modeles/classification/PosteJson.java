package fr.insee.rmes.modeles.classification;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class PosteJson extends Poste {

    @JacksonXmlProperty(localName = "ContenuLimite")
    @Override
    public String getContenuLimite() {
        return contenuLimite;
    }

    @JacksonXmlProperty(localName = "ContenuCentral")
    @Override
    public String getContenuCentral() {
        return contenuCentral;
    }

    @JacksonXmlProperty(localName = "ContenuLimite")
    @Override
    public String getContenuExclu() {
        return contenuExclu;
    }

    @JacksonXmlProperty(localName = "NoteGenerale")
    @Override
    public String getNoteGenerale() {
        return noteGenerale;
    }

}
