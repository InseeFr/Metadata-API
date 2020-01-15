package fr.insee.rmes.modeles.geo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public abstract class ZoneGeoJsonMixIn {

    @JsonCreator
    public ZoneGeoJsonMixIn(
        String code,
        String uri,
        String intitule,
        EnumTypeGeographie type,
        String dateCreation,
        String dateSuppression,
        IntituleSansArticle intituleSansArticle,
        String chefLieu) { }

    @JsonUnwrapped
    abstract String getIntituleSansArticle();
    
    @JsonInclude(Include.NON_EMPTY)
    abstract String getChefLieu();


}
