package tdd;

import impl.MinMaxStackImpl;

import java.lang.reflect.Array;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {
    private static final int[] VALUES = {1, 2, 3, 4};
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
        assertAll(
                () -> assertEquals(1, minMaxStack.pop()),
                () -> assertEquals(0, minMaxStack.pop()),
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
        populateStack();
    }
}