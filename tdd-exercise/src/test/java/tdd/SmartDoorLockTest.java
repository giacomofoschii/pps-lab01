package tdd;

import impl.SmartDoorLockImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    public static int PIN = 1234;
    public SmartDoorLock smartDoorLock = new SmartDoorLockImpl();

    @Test
    void testLocked() {
        assertTrue(smartDoorLock.isLocked());
    }

    @Test
    void testUnlocked() {
        smartDoorLock.unlock(PIN);
        assertFalse(smartDoorLock.isLocked());
    }
}
