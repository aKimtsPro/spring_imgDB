package bstrom.akimts.demo_JUNIT;

import bstrom.akimts.demo_JUNIT.exception.DivideByZeroException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatriceImplTest {

    CalculatriceImpl calculatrice = new CalculatriceImpl();

    @Test
    void viewAsserts(){

        // assertThrows(exception,executable lancant l'exception)
        // assertDoesNotThrow(exception, executable);

        // assertNull(null);
        // assertNotNull(not null);

        // assertEquals(expected, actual[, message]) - via la méthode Equals
        // assertNotEquals(expected, actual);
        // assertSame(objA, objB); - via l'adresse
        // assertNotSame(objA, objB);
        // assertArrayEquals(tabA, tabB);
        // assertIterableEquals(list1, list2);

        // assertAll( assertions sous la forme d'Executable);

        // assertTrue(boolean);
        // assertFalse(boolean);

//        assertTimeout(                      // ne coupe pas à la fin du timer
//                Duration.of(3, ChronoUnit.SECONDS),
//                ()-> Thread.sleep(5000)
//                );
//        assertTimeoutPreemptively(          // Coupe à la fin du timer
//                Duration.of(3, ChronoUnit.SECONDS),
//                ()-> Thread.sleep(5000)
//        );
    }

    @Test
    void plus() {

        double expected = 4;
        double actual = calculatrice.plus(2, 2);

        assertEquals( expected, actual );

    }

    @Test
    void minus() {

        double expected = 0;
        double actual = calculatrice.minus(2,2);

        assertEquals(expected,actual);
    }

    @Test
    void divide_whenBNotZero() {

        double expected = 1;
        double actual = calculatrice.divide(2,2);

        assertEquals(expected,actual);
    }

    @Test
    void divide_whenBIZero(){
        assertThrows(DivideByZeroException.class,() -> calculatrice.divide(2,0));
    }

    @Test
    void multiply() {

        double expected = 4;
        double actual = calculatrice.multiply(2,2);

        assertEquals(expected,actual);
    }
}