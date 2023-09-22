package fr.insee.rmes.api;

import fr.insee.rmes.utils.CSVUtils;
import fr.insee.rmes.utils.ResponseUtils;
import fr.insee.rmes.utils.SparqlUtils;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public abstract class AbstractApiTest {

    @Mock
    protected SparqlUtils mockSparqlUtils;

    @Mock
    protected CSVUtils mockCSVUtils;

    @Mock
    protected ResponseUtils mockResponseUtils;

    protected List<Object> list = new ArrayList<>();


    public void mockUtilsMethodsThenReturnListOfPojo(boolean isAttendResponseIsOk) {
        this.mockMethodsUtilsGeneric(isAttendResponseIsOk);
        when(mockCSVUtils.populateMultiPOJO(Mockito.anyString(), Mockito.any())).thenReturn(list);

    }

    public void mockUtilsMethodsThenReturnOnePojo(Object o, boolean isAttendResponseIsOk) {
        this.mockMethodsUtilsGeneric(isAttendResponseIsOk);
        when(mockCSVUtils.populatePOJO(Mockito.anyString(), Mockito.any())).thenReturn(o);

    }

    private void mockMethodsUtilsGeneric(boolean isAttendResponseIsOk) {
        when(mockSparqlUtils.executeSparqlQuery(Mockito.any())).thenReturn("");
        if (isAttendResponseIsOk) {
            when(mockResponseUtils.produceResponse(Mockito.any(), Mockito.any())).thenReturn(null);
        }
    }
}
