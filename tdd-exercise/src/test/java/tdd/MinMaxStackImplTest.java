package tdd;

import impl.MinMaxStackImpl;

import org.junit.jupiter.api.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {
    private static final List<Integer> VALUES = Arrays.asList(1, 4, 8, 2);
    private static final int MAX_VALUE = 8;
    private static final int MIN_VALUE = 1;
    private static final int SIZE_AFTER_TWO_POP = 2;
    private MinMaxStackImpl minMaxStack;

    @BeforeEach
    public void setUpEnviroment() {
        this.minMaxStack = new MinMaxStackImpl();
    }

    private void populateStack() {
        VALUES.forEach(i -> minMaxStack.push(i));
    }

    @Test
    void testPush() {
        populateStack();
        assertFalse(minMaxStack.isEmpty());
    }

    @Test
    void testPop() {
        populateStack();
        assertAll(
                () -> assertEquals(VALUES.getLast(), minMaxStack.pop()),
                () -> assertEquals(VALUES.get(2), minMaxStack.pop()),
                () -> assertEquals(SIZE_AFTER_TWO_POP, minMaxStack.size())
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
                () -> assertEquals(VALUES.size(), minMaxStack.size())
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
    void testEmptyMinMax() {
        assertAll(
                () -> assertThrows(IllegalStateException.class, () -> minMaxStack.getMax()),
                () -> assertThrows(IllegalStateException.class, () -> minMaxStack.getMin()),
                () -> assertTrue(minMaxStack.isEmpty())
        );
    }

    @Test
    void testMinMax() {
        populateStack();
        assertAll(
                () -> assertEquals(MAX_VALUE, minMaxStack.getMax()),
                () -> assertEquals(MIN_VALUE, minMaxStack.getMin())
        );
    }
}