package tdd;

import impl.MinMaxStackImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {
    private static final List<Integer> VALUES = Arrays.asList(1, 4, 8, 2);
    private static final int MAX_VALUE = 8;
    private static final int MIN_VALUE = 1;
    private MinMaxStackImpl minMaxStack;

    @BeforeEach
    public void setUpEnviroment() {
        this.minMaxStack = new MinMaxStackImpl();
    }

    private void populateStack() {
        VALUES.forEach(i -> minMaxStack.push(i));
    }

    @Test
    void testPushAndPop() {
        assertTrue(minMaxStack.isEmpty());
        populateStack();
        assertFalse(minMaxStack.isEmpty());
        assertAll(
                () -> assertEquals(VALUES.getLast(), minMaxStack.pop()),
                () -> assertEquals(VALUES.get(2), minMaxStack.pop()),
                () -> assertEquals(VALUES.get(1), minMaxStack.pop()),
                () -> assertEquals(VALUES.getFirst(), minMaxStack.pop()),
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
                () -> assertEquals(VALUES.getLast(), minMaxStack.peek()),
                () -> assertEquals(VALUES.size(), minMaxStack.size()),
                () -> assertEquals(VALUES.getLast(), minMaxStack.pop()),
                () -> assertNotEquals(VALUES.size(), minMaxStack.size()),
                () -> assertNotEquals(VALUES.getLast(), minMaxStack.peek())
        );
    }

    @Test
    void testSize() {
        assertTrue(minMaxStack.isEmpty());
        populateStack();
        assertAll(
                () -> assertFalse(minMaxStack.isEmpty()),
                () -> assertEquals(VALUES.size(), minMaxStack.size())
        );
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