package tdd;

import impl.CircularQueueImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {
    private static final List<Integer> VALUES = Arrays.asList(1, 4, 8);
    private static final int EXTRA_VALUE = 5;
    public static final int SIZE_AFTER_POP = 2;
    private CircularQueueImpl circularQueue;

    @BeforeEach
    public void setUpEnviroment() {
        this.circularQueue = new CircularQueueImpl();
    }

    private void populateQueue() {
        VALUES.forEach(i -> this.circularQueue.push(i));
    }

    @Test
    void testSize() {
        assertTrue(this.circularQueue.isEmpty());
        populateQueue();
        assertEquals(VALUES.size(), this.circularQueue.getSize());
    }

    @Test
    void testPop() {
        populateQueue();
        assertAll(
                () -> assertEquals(VALUES.getLast(), this.circularQueue.pop()),
                () -> assertEquals(SIZE_AFTER_POP, this.circularQueue.getSize())
        );
    }

    @Test
    void testEmptyPop() {
        assertAll(
                () -> assertTrue(this.circularQueue.isEmpty()),
                () -> assertThrows(IllegalStateException.class, () -> this.circularQueue.pop())
        );
    }

    @Test
    void testPush() {
        assertTrue(this.circularQueue.isEmpty());
        populateQueue();
        assertFalse(this.circularQueue.isEmpty());
    }

    @Test
    void testOverwrite() {
        populateQueue();
        assertEquals(VALUES.getFirst(), this.circularQueue.getFirst());
        this.circularQueue.push(EXTRA_VALUE);
        assertAll(
                () -> assertEquals(VALUES.size(), this.circularQueue.getSize()),
                () -> assertEquals(EXTRA_VALUE, this.circularQueue.peek()),
                () -> assertNotEquals(VALUES.getFirst(), this.circularQueue.getFirst())
        );
    }

    @Test
    void testPeek() {
        populateQueue();
        assertAll(
                () -> assertEquals(VALUES.getLast(), this.circularQueue.peek()),
                () -> assertEquals(VALUES.size(), this.circularQueue.getSize())
        );
    }

    @Test
    void testEmptyPeek() {
        assertAll(
                () -> assertTrue(this.circularQueue.isEmpty()),
                () -> assertThrows(IllegalStateException.class, () -> this.circularQueue.peek())
        );
    }
}
