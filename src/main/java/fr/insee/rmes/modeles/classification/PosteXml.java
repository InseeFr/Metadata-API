package fr.insee.rmes.modeles.classification;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class PosteXml extends Poste {

    @JsonRawValue // don't encode XML
    @Override
    public String getContenuLimite() {
        if (StringUtils.isNotEmpty(contenuLimite)) {
            return "<ContenuLimite>" + contenuLimite + "</ContenuLimite>";
        }
        else
            return null;
    }

    @JsonRawValue
    @Override
    public String getContenuCentral() {
        if (StringUtils.isNotEmpty(contenuCentral)) {
            return "<ContenuCentral>" + contenuCentral + "</ContenuCentral>";
        }
        else
            return null;
    }

    @JsonRawValue
    @Override
    public String getContenuExclu() {
        if (StringUtils.isNotEmpty(contenuExclu)) {
            return "<ContenuExclu>" + contenuExclu + "</ContenuExclu>";
        }
        else
            return null;
    }

    @JsonRawValue
    @Override
    public String getNoteGenerale() {
        if (StringUtils.isNotEmpty(noteGenerale)) {
            return "<NoteGenerale>" + noteGenerale + "</NoteGenerale>";
        }
        else
            return null;
    }
}
