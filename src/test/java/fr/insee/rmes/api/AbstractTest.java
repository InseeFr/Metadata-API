package fr.insee.rmes.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.insee.rmes.utils.CSVUtils;
import fr.insee.rmes.utils.ResponseUtils;
import fr.insee.rmes.utils.SparqlUtils;

public abstract class AbstractTest {

    @Mock
    protected SparqlUtils mockSparqlUtils;

    @Mock
    protected CSVUtils mockCSVUtils;

    @Mock
    protected ResponseUtils mockResponseUtils;

    protected List<Object> list = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
}
