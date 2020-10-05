package udemymockito.demo;

import Repository_DataService.SomeDataService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SomeDataServiceStub implements SomeDataService{


    @Override
    public int[] retrieveAllData() { //vi laver en klon af vores repository som kan give noget "fake data". dette er en slags fake database vi bruger til tests
        return new int[]{1,2,3};
    }




}

class SomeDataServiceStubEmpty implements SomeDataService{ //stub = noget der står inde for noget andet kode. Her står vores dataservicestub inde for en rigtig database


    @Override
    public int[] retrieveAllData() {
        return new int[]{};
    }
}

class SomeDataServiceStubOneValue implements  SomeDataService{


    @Override
    public int[] retrieveAllData() {
        return new int[]{10};
    }
}


public class SomeBusinessStubTest {


    @Test
    void calculateSumUsingDataService_basic() {

        SomeBusinessImpl business = new SomeBusinessImpl(); //vi laver et objekt af vores business class med
        business.setSomeDataService(new SomeDataServiceStub()); //hvis det var det rigtige program, ville det kommunikere med en database. Men vi vil ikke røre rigtig data.
        int actualresult = business.calculateSumUsingDataService(); //vi kører vores service som tager sin metode og data fra vores repository.
        int expectedResult = 6;
        assertEquals(expectedResult, actualresult);
    }





    @Test
    void calculateSum_emptyArray() {

        SomeBusinessImpl business = new SomeBusinessImpl();
        business.setSomeDataService(new SomeDataServiceStubEmpty());
        int result = business.calculateSumUsingDataService();
        int expectedResult = 0;
        assertEquals(result, expectedResult);
    }


    @Test
    void calculateSum_oneValue() {

        SomeBusinessImpl business = new SomeBusinessImpl();
        business.setSomeDataService(new SomeDataServiceStubOneValue());
        int result = business.calculateSumUsingDataService();
        int expectedResult = 10;
        assertEquals(result, expectedResult);
    }


}
