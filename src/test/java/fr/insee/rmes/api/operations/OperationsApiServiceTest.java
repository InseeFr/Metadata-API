package fr.insee.rmes.api.operations;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.insee.rmes.api.AbstractApiTest;

@ExtendWith(MockitoExtension.class)
public class OperationsApiServiceTest extends AbstractApiTest {

    @InjectMocks
    private OperationsApiService operationsApiService;
}
