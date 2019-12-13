package fr.insee.rmes.api.operations;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.insee.rmes.api.AbstractApiTest;
import fr.insee.rmes.modeles.operations.FamilyToOperation;

@ExtendWith(MockitoExtension.class)
public class OperationsApiServiceTest extends AbstractApiTest {

    @InjectMocks
    private OperationsApiService operationsApiService;

    private List<FamilyToOperation> opList;
    
    @Test
    public void givenGetListeFamilyToOperation_whenListIsEmpty_thenResponseIsOk() {
        
    }
}
