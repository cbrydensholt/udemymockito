package udemymockito.demo;

import Repository_DataService.SomeDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/*
Vi har en business klasse med de metoder vi gerne vil køre
de er afhængige af data, derfor har vi en dataService klasse der har hul til dataen
vi vil ikke bruge rigtig data, vi bruger fabrikeret data til at teste vores metoder
derfor mocker vi vores data klasse, som betyder at vi kan give fabrikeret data til vores business metoder
 */


@ExtendWith(MockitoExtension.class)
public class SomeBusinessInjectMockTestUdenComments {

    @InjectMocks //vores mock injectes automatisk ind i dette objekt
    SomeBusinessImpl business = new SomeBusinessImpl(); //laver objekt af vores business klasse

    @Mock
    SomeDataService dataServiceMock; //vi gør dette i stedet for at skrive @BeforeEach



    @Test
    void calculateSumUsingDataService_basic() {


        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{1,2,3});
        int actualresult = business.calculateSumUsingDataService();
        int expectedResult = 6;
        assertEquals(expectedResult, actualresult);
    }





    @Test
    void calculateSum_emptyArray() {


        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{});
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
