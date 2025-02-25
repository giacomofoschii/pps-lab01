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
        VALUES.forEach(i -> this.minMaxStack.push(i));
    }

    @Test
    void testPush() {
        populateStack();
        assertFalse(this.minMaxStack.isEmpty());
    }

    @Test
    void testPop() {
        populateStack();
        assertAll(
                () -> assertEquals(VALUES.getLast(), this.minMaxStack.pop()),
                () -> assertEquals(VALUES.get(2), this.minMaxStack.pop()),
                () -> assertEquals(SIZE_AFTER_TWO_POP, this.minMaxStack.size())
        );
    }

    @Test
    void testEmptyPop() {
        assertAll(
                () -> assertTrue(this.minMaxStack.isEmpty()),
                () -> assertThrows(IllegalStateException.class, () -> this.minMaxStack.pop())
        );
    }

    @Test
    void testPeek() {
        populateStack();
        assertAll(
                () -> assertEquals(VALUES.getLast(), this.minMaxStack.peek()),
                () -> assertEquals(VALUES.size(), this.minMaxStack.size())
        );
    }

    @Test
    void testSize() {
        assertTrue(this.minMaxStack.isEmpty());
        populateStack();
        assertAll(
                () -> assertFalse(this.minMaxStack.isEmpty()),
                () -> assertEquals(VALUES.size(), this.minMaxStack.size())
        );
    }

    @Test
    void testEmptyMinMax() {
        assertAll(
                () -> assertThrows(IllegalStateException.class, () -> this.minMaxStack.getMax()),
                () -> assertThrows(IllegalStateException.class, () -> this.minMaxStack.getMin()),
                () -> assertTrue(this.minMaxStack.isEmpty())
        );
    }

    @Test
    void testMinMax() {
        populateStack();
        assertAll(
                () -> assertEquals(MAX_VALUE, this.minMaxStack.getMax()),
                () -> assertEquals(MIN_VALUE, this.minMaxStack.getMin())
        );
    }
}