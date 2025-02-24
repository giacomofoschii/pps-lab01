package tdd;

import impl.SmartDoorLockImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    public static final int WRONG_PIN = 1111;
    public static final int PIN = 1234;
    public static final int NEW_PIN = 5678;
    public static final int ATTEMPTS = 1;
    private SmartDoorLockImpl smartDoorLock;

    @BeforeEach
    void setUpEnviroment() {
        smartDoorLock =new SmartDoorLockImpl(PIN);
    }

    @Test
    void testLocked() {
        assertTrue(smartDoorLock.isLocked());
    }

    @Test
    void testUnlocked() {
        smartDoorLock.unlock(PIN);
        assertFalse(smartDoorLock.isLocked());
    }

    @Test
    void setPin() {
        smartDoorLock.unlock(PIN);
        smartDoorLock.setPin(NEW_PIN);
        assertEquals(NEW_PIN, smartDoorLock.getPin());
    }

    @Test
    void testWrongUnlocked() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> smartDoorLock.unlock(WRONG_PIN)),
                () -> assertEquals(ATTEMPTS, smartDoorLock.getFailedAttempts())
        );
    }
}
