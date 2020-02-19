package fr.insee.rmes.api.geo;

import java.util.List;

import javax.ws.rs.core.Response;

import fr.insee.rmes.modeles.geo.EnumTypeGeographie;
import fr.insee.rmes.modeles.geo.territoire.Territoire;
import fr.insee.rmes.modeles.geo.territoires.Territoires;
import fr.insee.rmes.utils.Constants;

public abstract class AbstractGeoAscendantsAndDescendantsApi extends AbstractGeoApi {

    protected boolean verifyParametersApiAreValid(String typeTerritoire, String date) {
        return (this.verifyParameterTypeTerritoireIsRight(typeTerritoire)) && (this.verifyParameterDateIsRight(date));
    }

    protected boolean verifyParameterTypeTerritoireIsRight(String typeTerritoire) {
        return (typeTerritoire == null)
            || (EnumTypeGeographie
                .streamValuesTypeGeo()
                .anyMatch(s -> s.getTypeObjetGeo().equalsIgnoreCase(typeTerritoire)));
    }

    public String formatValidParametertypeTerritoireIfIsNull(String typeTerritoire) {
        return (typeTerritoire != null) ? EnumTypeGeographie.getTypeObjetGeoIgnoreCase(typeTerritoire) : Constants.NONE;
    }

    protected Response generateResponseListOfTerritoireForAscendantsOrDescendants(String csvResult, String header) {

        List<Territoire> listeTerritoires = csvUtils.populateMultiPOJO(csvResult, Territoire.class);
        return this.generateListStatusResponse(Territoires.class, listeTerritoires, this.getFirstValidHeader(header));

    }
}
