package fr.insee.rmes.modeles.geo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public abstract class CommuneJsonMixIn {

    @JsonCreator
    public CommuneJsonMixIn(
        String code,
        String uri,
        String intitule,
        EnumTypeGeographie type,
        String dateCreation,
        String dateSuppression,
        IntituleSansArticle intituleSansArticle) { }

    @JsonUnwrapped
    abstract String getIntituleSansArticle();


}
