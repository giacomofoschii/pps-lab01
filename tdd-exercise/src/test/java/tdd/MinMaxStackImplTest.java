package tdd;

import impl.MinMaxStackImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {
    private static final int[] VALUES = {1, 4, 8, 2};
    private static final int MAX_VALUE = 8;
    private static final int MIN_VALUE = 1;
    private MinMaxStackImpl minMaxStack;

    @BeforeEach
    public void setUpEnviroment() {
        this.minMaxStack = new MinMaxStackImpl();
    }

    private void populateStack() {
        minMaxStack.push(VALUES[0]);
        minMaxStack.push(VALUES[1]);
        minMaxStack.push(VALUES[2]);
        minMaxStack.push(VALUES[3]);
    }

    @Test
    void testPushAndPop() {
        assertTrue(minMaxStack.isEmpty());
        populateStack();
        assertFalse(minMaxStack.isEmpty());
        assertAll(
                () -> assertEquals(VALUES[3], minMaxStack.pop()),
                () -> assertEquals(VALUES[2], minMaxStack.pop()),
                () -> assertEquals(VALUES[1], minMaxStack.pop()),
                () -> assertEquals(VALUES[0], minMaxStack.pop()),
                () -> assertTrue(minMaxStack.isEmpty())
        );
    }

    @Test
    void testEmptyPop() {
        assertAll(
                () -> assertTrue(minMaxStack.isEmpty()),
                () -> assertThrows(IllegalStateException.class, () -> minMaxStack.pop())
        );
    }

    @Test
    void testPeek() {
        populateStack();
        assertAll(
                () -> assertEquals(VALUES[3], minMaxStack.peek()),
                () -> assertEquals(VALUES[3], minMaxStack.peek()),
                () -> assertEquals(VALUES.length, minMaxStack.size()),
                () -> assertEquals(VALUES[3], minMaxStack.pop()),
                () -> assertNotEquals(VALUES[3], minMaxStack.size()),
                () -> assertNotEquals(VALUES[3], minMaxStack.peek())
        );
    }

    @Test
    void testSize() {
        populateStack();
    }

    @Test
    void testMinMax() {
        assertAll(
                () -> assertThrows(IllegalStateException.class, () -> minMaxStack.getMax()),
                () -> assertThrows(IllegalStateException.class, () -> minMaxStack.getMin()),
                () -> assertTrue(minMaxStack.isEmpty())
        );
        populateStack();
        assertAll(
                () -> assertEquals(MAX_VALUE, minMaxStack.getMax()),
                () -> assertEquals(MIN_VALUE, minMaxStack.getMin())
        );
    }
}