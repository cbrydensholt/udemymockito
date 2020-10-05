package udemymockito.demo;

import Repository_DataService.SomeDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SomeBusinessMockTest {
    SomeBusinessImpl business = new SomeBusinessImpl(); //laver objekt af vores business klasse
    SomeDataService dataServiceMock = mock(SomeDataService.class); //vi mocker vores klasse med forbindelse til data, for at give det noget hjemmelavet data
    //når nogen kalder "retrievealldata" metoden i vores repository/interface

    @BeforeEach
            public void before() { //før hver test sker dete.
        business.setSomeDataService(dataServiceMock); //hvis det var det rigtige program, ville det kommunikere med en database. Men vi vil ikke røre rigtig data, vi vil bare se om
        //- metoder virker
        //vi siger at hver gang vi kører dataServiceMetoder, bruges vores mock i stedet, som har det fabrikerede data
    }


    @Test
    void calculateSumUsingDataService_basic() {

        //vi vil lave en sample implementation af SomeDataService
        //vil vi returne dens data
        //når vi bruger stub, hardcoder vi dataen og siger beder den om at return det vi har kodet selv (f.eks. array som ses i stub eksemplet)
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{1,2,3}); //når vi kalder retrieveAllData, er det dette data der bliver brugt
        //dataServiceMock er en fake SomeDataService. Når nogen kalder metoden i vores fake dataservice, vil denne metode have dette som data ^
        int actualresult = business.calculateSumUsingDataService(); //vi kører vores service som tager sin metode og data fra vores repository.
        int expectedResult = 6;
        assertEquals(expectedResult, actualresult);
    }





    @Test
    void calculateSum_emptyArray() {

        //forklaring:
        //vi laver en mock/kopi af vores dataservice klasse, som indeholder metoden der giver os data
        //vi siger at hver gang denne retrieveAllData metode køres, giver vi den fake data, i dette tilfælde en tom array, som så kan køres i vores business metode (calculatesumusingdataservice)

        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{}); //vi siger at vi vil arbejde med data af en array som ikke indeholder noget.
        assertEquals(0, business.calculateSumUsingDataService());
    }




    @Test
    void calculateSum_oneValue() {


        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{10});
        int result = business.calculateSumUsingDataService();
        assertEquals(result, 10);
    }





}
