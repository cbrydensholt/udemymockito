package udemymockito.demo;

import Repository_DataService.SomeDataService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SomeBusinessImplTest {

    SomeDataService someDataService; //interface til vores repository

    @Test
    void calculateSum_basic() {

        SomeBusinessImpl business = new SomeBusinessImpl();
        int result = business.calculateSum(new int[]{1,2,3});
        int expectedResult = 6;
        assertEquals(result, expectedResult);
    }

    @Test
    void calculateSum_emptyArray() {

        SomeBusinessImpl business = new SomeBusinessImpl();
        int result = business.calculateSum(new int[]{});
        int expectedResult = 0;
        assertEquals(result, expectedResult);
    }


    @Test
    void calculateSum_oneValue() {

        SomeBusinessImpl business = new SomeBusinessImpl();
        int result = business.calculateSum(new int[]{5});
        int expectedResult = 5;
        assertEquals(result, expectedResult);
    }


}