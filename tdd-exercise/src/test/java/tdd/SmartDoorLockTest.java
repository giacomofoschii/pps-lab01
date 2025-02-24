package tdd;

import impl.SmartDoorLockImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    public static final int WRONG_PIN = 1111;
    public static final int PIN = 1234;
    public static final int NEW_PIN = 5678;
    public static final int WRONG_DIGITS_PIN = 123;
    public static final int PIN_DEFAULT = -1;
    public static final int FIRST_ATTEMPT = 1;
    public static final int SECOND_ATTEMPT = 2;
    public static final int INITIAL_ATTEMPTS = 0;
    private SmartDoorLockImpl smartDoorLock;

    @BeforeEach
    void setUpEnviroment() {
        smartDoorLock = new SmartDoorLockImpl();
    }

    private void setPinAndLock() {
        smartDoorLock.setPin(PIN);
        smartDoorLock.lock();
    }

    @Test
    void testSetPin() {
        setPinAndLock();
        smartDoorLock.unlock(PIN);
        smartDoorLock.setPin(NEW_PIN);
        assertEquals(NEW_PIN, smartDoorLock.getPin());
    }

    @Test
    void testWrongDigitsSetPin() {
        assertEquals(PIN_DEFAULT, smartDoorLock.getPin());
        assertThrows(IllegalArgumentException.class, () -> smartDoorLock.setPin(WRONG_DIGITS_PIN));
    }

    @Test
    void testSetPinWithDoorLocked() {
        setPinAndLock();
        assertAll(
                () -> assertThrows(IllegalStateException.class, () -> smartDoorLock.setPin(NEW_PIN)),
                () -> assertNotEquals(NEW_PIN, smartDoorLock.getPin())
        );
    }

    @Test
    void testLockWithoutSetPin() {
        assertThrows(IllegalStateException.class, () -> smartDoorLock.lock());
    }

    @Test
    void testLock() {
        setPinAndLock();
        assertTrue(smartDoorLock.isLocked());
    }

    @Test
    void testUnlock() {
        setPinAndLock();
        smartDoorLock.unlock(PIN);
        assertFalse(smartDoorLock.isLocked());
    }

    @Test
    void testWrongPinUnlock() {
        setPinAndLock();
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> smartDoorLock.unlock(WRONG_PIN)),
                () -> assertEquals(FIRST_ATTEMPT, smartDoorLock.getFailedAttempts())
        );
    }

    @Test
    void testBlockedDoor() {
        setPinAndLock();
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, ()-> smartDoorLock.unlock(WRONG_PIN)),
                () -> assertEquals(FIRST_ATTEMPT, smartDoorLock.getFailedAttempts()),
                () -> assertThrows(IllegalArgumentException.class, () -> smartDoorLock.unlock(WRONG_PIN)),
                () -> assertEquals(SECOND_ATTEMPT, smartDoorLock.getFailedAttempts()),
                () -> assertThrows(IllegalStateException.class, () -> smartDoorLock.unlock(WRONG_PIN)),
                () -> assertEquals(smartDoorLock.getFailedAttempts(), smartDoorLock.getMaxAttempts()),
                () -> assertTrue(smartDoorLock.isBlocked())
        );
    }

    @Test
    void testReset() {
        testBlockedDoor();
        smartDoorLock.reset();
        assertFalse(smartDoorLock.isBlocked());
        assertFalse(smartDoorLock.isLocked());
        assertEquals(PIN_DEFAULT, smartDoorLock.getPin());
        assertEquals(INITIAL_ATTEMPTS, smartDoorLock.getFailedAttempts());
    }
}
