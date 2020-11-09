package fr.insee.rmes.api.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.insee.rmes.api.AbstractApiTest;
import fr.insee.rmes.modeles.operations.Famille;
import fr.insee.rmes.modeles.operations.FamilyToOperation;
import fr.insee.rmes.utils.FileUtils;

@ExtendWith(MockitoExtension.class)
class OperationsApiServiceTest extends AbstractApiTest {

    @InjectMocks
    private OperationsApiService operationsApiService;

    @Mock
    protected FileUtils mockFileUtils;

    private List<FamilyToOperation> opList = new ArrayList<>();
    private Map<String, Famille> familyMap = new HashMap<>();

    @Test
    void givenGetListeFamilyToOperation_whenListIsEmpty() {
        Assertions.assertEquals(familyMap, operationsApiService.getListeFamilyToOperation(opList));
    }

    private FamilyToOperation getAFamilyToOperation(String someValue) {
        FamilyToOperation familyToOperation = new FamilyToOperation();
        familyToOperation.setSeries(someValue);
        familyToOperation.setSeriesId(someValue);
        familyToOperation.setSeriesLabelLg1(someValue);
        familyToOperation.setSeriesLabelLg2(someValue);
        familyToOperation.setSeriesAltLabelLg1(someValue);
        familyToOperation.setSeriesAltLabelLg2(someValue);
        familyToOperation.setFamily(someValue);
        familyToOperation.setFamilyId(someValue);
        familyToOperation.setFamilyLabelLg1(someValue);
        familyToOperation.setFamilyLabelLg2(someValue);
        familyToOperation.setFamilyAltLabelLg1(someValue);
        familyToOperation.setFamilyAltLabelLg2(someValue);
        return familyToOperation;
    }

    @Test
    void givenGetListeFamilyToOperation_whenListContainsOneFamilyToOperation() {
        opList.add(this.getAFamilyToOperation("1"));
        familyMap = operationsApiService.getListeFamilyToOperation(opList);
        Assertions.assertTrue(familyMap.containsKey("1"));
    }

    @Test
    void givenGetListeFamilyToOperation_whenListContainsOneFamilyToOperationWitOperationId() {
        FamilyToOperation familyToOperation = this.getAFamilyToOperation("1");
        familyToOperation.setOperation("1");
        familyToOperation.setOperationId("1");
        familyToOperation.setOpLabelLg1("1");
        familyToOperation.setOpLabelLg2("1");
        familyToOperation.setSimsId("1");
        familyToOperation.setOpAltLabelLg1("1");
        familyToOperation.setOpAltLabelLg2("1");
        opList.add(familyToOperation);
        familyMap = operationsApiService.getListeFamilyToOperation(opList);
        Assertions.assertTrue(familyMap.containsKey("1"));
    }

    @Test
    void givenGetListeFamilyToOperation_whenListContainsOneFamilyToOperationWitSims() {
        FamilyToOperation familyToOperation = this.getAFamilyToOperation("1");
        familyToOperation.setSimsId("1");
        opList.add(familyToOperation);
        familyMap = operationsApiService.getListeFamilyToOperation(opList);
        Assertions.assertTrue(familyMap.containsKey("1"));
    }

    @Test
    void givenGetListeFamilyToOperation_whenListContainsOneFamilyToOperationWitIndicId() {
        FamilyToOperation familyToOperation = this.getAFamilyToOperation("1");
        familyToOperation.setIndic("1");
        familyToOperation.setIndicId("1");
        familyToOperation.setIndicLabelLg1("1");
        familyToOperation.setIndicLabelLg2("1");
        familyToOperation.setSimsId("1");
        familyToOperation.setIndicAltLabelLg1("1");
        familyToOperation.setIndicAltLabelLg2("1");
        opList.add(familyToOperation);
        familyMap = operationsApiService.getListeFamilyToOperation(opList);
        Assertions.assertTrue(familyMap.containsKey("1"));
    }

    @Test
    void givenRemoveExclusions_whenFileIsEmpty() {
        // FamilyToOperation familyToOperation = this.getAFamilyToOperation("1");
        // opList.add(familyToOperation);
        opList.add(this.getAFamilyToOperation("1"));
        opList.add(this.getAFamilyToOperation("2"));
        opList = operationsApiService.removeExclusions(opList);
        Assertions.assertEquals(2, opList.size());

    }

}
