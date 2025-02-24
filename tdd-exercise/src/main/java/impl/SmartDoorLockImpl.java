package impl;

import tdd.SmartDoorLock;

public class SmartDoorLockImpl implements SmartDoorLock {
    private static final int MAX_ATTEMPTS = 3;

    private int pin;
    private int attempts;
    private boolean locked;
    private boolean blocked;

    public SmartDoorLockImpl(){
        resetAttempts();
        this.pin = -1;
        this.blocked = false;
        this.locked = false;
    }

    private void resetAttempts() {
        this.attempts = 0;
    }

    @Override
    public void setPin(int pin) {
        if(String.valueOf(pin).length() != 4) throw new IllegalArgumentException("Pin must be exactly 4 digits");
        if(!locked) {
            this.pin = pin;
        } else throw new IllegalStateException("Cannot set new pin, the door is locked");
    }

    @Override
    public int getPin() {
        return this.pin;
    }

    @Override
    public void unlock(int pin) {
        if(pin == this.pin) {
            this.locked = false;
            resetAttempts();
        } else {
            this.attempts++;
            if(this.attempts < getMaxAttempts()) {
                throw new IllegalArgumentException("Pin " + pin + " is not corrected");
            } else {
                this.blocked = true;
                throw new IllegalStateException("You reached te maximum number of attempts, the door is now blocked");
            }
        }
    }

    @Override
    public void lock() {
        if(this.pin != -1) {
            this.locked = true;
        } else throw new IllegalStateException("Pin is not setted");
    }

    @Override
    public boolean isLocked() {
        return this.locked;
    }

    @Override
    public boolean isBlocked() {
        return this.blocked;
    }

    @Override
    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    @Override
    public int getFailedAttempts() {
        return this.attempts;
    }

    @Override
    public void reset() {
        resetAttempts();
        this.pin = -1;
        this.locked = false;
        this.blocked = false;
    }
}
