package fr.insee.rmes.api.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.insee.rmes.api.AbstractApiTest;
import fr.insee.rmes.modeles.operations.CsvFamily;
import fr.insee.rmes.modeles.operations.Famille;

@ExtendWith(MockitoExtension.class)
class OperationsApiServiceTest extends AbstractApiTest {

    @InjectMocks
    private OperationsApiService operationsApiService;

    private List<CsvFamily> opList = new ArrayList<>();
    private Map<String, Famille> familyMap = new HashMap<>();
    Map<String,List<String>> exclusions = new HashMap<>();

    @Test
    void givenGetListeFamilyToOperation_whenListIsEmpty() {
        Assertions.assertEquals(familyMap, operationsApiService.getListeFamilyToOperation(opList,exclusions));
    }

    private CsvFamily getAFamily(String someValue) {
    	CsvFamily family = new CsvFamily();
     /*   family.setSeries(someValue);
        family.setSeriesId(someValue);
        family.setSeriesLabelLg1(someValue);
        family.setSeriesLabelLg2(someValue);
        family.setSeriesAltLabelLg1(someValue);
        family.setSeriesAltLabelLg2(someValue);*/
        family.setUri(someValue);
        family.setId(someValue);
        family.setLabelLg1(someValue);
        family.setLabelLg2(someValue);
        family.setAltlabelLg1(someValue);
        family.setAltlabelLg2(someValue);
        return family;
    }

    @Test
    void givenGetListeFamilyToOperation_whenListContainsOneFamilyToOperation() {
        opList.add(this.getAFamily("1"));
        familyMap = operationsApiService.getListeFamilyToOperation(opList,exclusions);
        Assertions.assertTrue(familyMap.containsKey("1"));
    }

 /*   @Test
    void givenGetListeFamilyToOperation_whenListContainsOneFamilyToOperationWitOperationId() {
    	CsvFamily familyToOperation = this.getAFamily("1");
        familyToOperation.setOperation("1");
        familyToOperation.setOperationId("1");
        familyToOperation.setOpLabelLg1("1");
        familyToOperation.setOpLabelLg2("1");
        familyToOperation.setSimsId("1");
        familyToOperation.setOpAltLabelLg1("1");
        familyToOperation.setOpAltLabelLg2("1");
        opList.add(familyToOperation);
        familyMap = operationsApiService.getListeFamilyToOperation(opList,exclusions);
        Assertions.assertTrue(familyMap.containsKey("1"));
    }

    @Test
    void givenGetListeFamilyToOperation_whenListContainsOneFamilyToOperationWitSims() {
        FamilyToOperation familyToOperation = this.getAFamily("1");
        familyToOperation.setSimsId("1");
        opList.add(familyToOperation);
        familyMap = operationsApiService.getListeFamilyToOperation(opList,exclusions);
        Assertions.assertTrue(familyMap.containsKey("1"));
    }

    @Test
    void givenGetListeFamilyToOperation_whenListContainsOneFamilyToOperationWitIndicId() {
        FamilyToOperation familyToOperation = this.getAFamily("1");
        familyToOperation.setIndic("1");
        familyToOperation.setIndicId("1");
        familyToOperation.setIndicLabelLg1("1");
        familyToOperation.setIndicLabelLg2("1");
        familyToOperation.setSimsId("1");
        familyToOperation.setIndicAltLabelLg1("1");
        familyToOperation.setIndicAltLabelLg2("1");
        opList.add(familyToOperation);
        familyMap = operationsApiService.getListeFamilyToOperation(opList,exclusions);
        Assertions.assertTrue(familyMap.containsKey("1"));
    }

    @Test
    void givenRemoveExclusions_whenFileIsEmpty() {
        opList.add(this.getAFamily("1"));
        opList.add(this.getAFamily("2"));
        opList = operationsApiService.removeExclusions(opList,exclusions);
        Assertions.assertEquals(2, opList.size());

    }*/
    //TODO
    

}
