package udemymockito.demo;

import Repository_DataService.SomeDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SomeBusinessInjectMockTest {

    //nu vil vi gerne oprette en instance of SomeBusinessImpl som automatisk bruger vores mock.
    //create en instance af vores mock, og injecte det ind i vores business object. I stedet for at vi skal gøre det automatisk
    @InjectMocks //vores mock injectes automatisk ind i dette objekt
    SomeBusinessImpl business = new SomeBusinessImpl(); //laver objekt af vores business klasse

    @Mock //laver en fake "SomeDataService" klasse, som er klassen der har hul til vores data. Med en mock kan vi lave fabrikeret data til at teste i vores metoder.
    SomeDataService dataServiceMock; //vi gør dette i stedet for at skrive @BeforeEach
   // public void before() {
       // business.setSomeDataService(dataServiceMock);



    //SomeDataService dataServiceMock = mock(SomeDataService.class); //vi mocker vores klasse med forbindelse til data, for at give det noget hjemmelavet data
    //når nogen kalder "retrievealldata" metoden i vores repository/interface



    @Test
    void calculateSumUsingDataService_basic() {

        //vi vil lave en sample implementation af SomeDataService
        //vil vi returne dens data
        //når vi bruger stub, hardcoder vi dataen og siger beder den om at return det vi har kodet selv (f.eks. array som ses i stub eksemplet)
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{1,2,3}); //når vi kalder retrieveAllData, er det dette data der bliver brugt
        //dataServiceMock er en fake SomeDataService. Når nogen kalder metoden i vores fake dataservice, vil denne metode have dette som data ^
        //vi kører vores service som tager sin metode og data fra vores repository.
        assertEquals(6, business.calculateSumUsingDataService());
    }





    @Test
    void calculateSum_emptyArray() {

        //forklaring:
        //vi laver en mock/kopi af vores dataservice klasse, som indeholder metoden der giver os data
        //vi siger at hver gang denne retrieveAllData metode køres, giver vi den fake data, i dette tilfælde en tom array, som så kan køres i vores business metode (calculatesumusingdataservice)

        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{}); //vi siger at vi vil arbejde med data af en array som ikke indeholder noget.
        int result = business.calculateSumUsingDataService();
        assertEquals(result, 0);
    }




    @Test
    void calculateSum_oneValue() {


        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{10});
        int result = business.calculateSumUsingDataService();
        assertEquals(result, 10);
    }





}
