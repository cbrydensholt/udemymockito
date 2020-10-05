package udemymockito.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListMockTest {
    List<String> mock = mock(List.class); //mocker en list class interface



    @Test
    public void size_basic(){
        when(mock.size()).thenReturn(5);
        assertEquals(5, mock.size());
    }

    @Test
    public void returnDifferentValues(){ //viser hvordan vi kan afprøve en test metode med forskellige data i samme "when" call.
        when(mock.size()).thenReturn(5).thenReturn(10);
        assertEquals(5, mock.size());
        assertEquals(10, mock.size());


    }


    @Test //en test med parametre
    public void returnWithGenericParameters(){
        List mock = mock(List.class); //mocker en list class interface
        when(mock.get(anyInt())).thenReturn("in 28 minutes"); //anyInt er en agrument matcher. betydningen giver sig selv. ligegyldigt hvilket index vi siger, returner den stringen
    assertEquals("in 28 minutes", mock.get(0));
        assertEquals("in 28 minutes", mock.get(1));

    }


    @Test
    public void verificationBasics(){

        String value = mock.get(0);
        String value2 = mock.get(1);


        //verify at .get metoden kaldes på vores mock

        verify(mock).get(0);
        verify(mock, times(2)).get(anyInt());
        verify(mock, atLeast(1)).get(1);
        verify(mock, atMost(2)).get(0);
        verify(mock, never()).get(5);

    }

}
