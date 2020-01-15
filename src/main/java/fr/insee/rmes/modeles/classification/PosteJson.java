package fr.insee.rmes.modeles.classification;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class PosteJson extends Poste {

    @JacksonXmlProperty(localName = "ContenuLimite")
    public String getContenuLimite() {
        return contenuLimite;
    }

    @JacksonXmlProperty(localName = "ContenuCentral")
    public String getContenuCentral() {
        return contenuCentral;
    }

    @JacksonXmlProperty(localName = "ContenuLimite")
    public String getContenuExclu() {
        return contenuExclu;
    }

    @JacksonXmlProperty(localName = "NoteGenerale")
    public String getNoteGenerale() {
        return noteGenerale;
    }

}
