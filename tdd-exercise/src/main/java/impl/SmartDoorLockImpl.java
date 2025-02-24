package impl;

import tdd.SmartDoorLock;

public class SmartDoorLockImpl implements SmartDoorLock {
    private int pin;
    private int attempts;
    private boolean isLocked;
    private boolean isBlocked;

    public SmartDoorLockImpl(){
        this.attempts = 0;
        this.pin = -1;
        this.isBlocked = false;
        this.isLocked = false;
    }

    private void resetAttempts() {
        this.attempts = 0;
    }

    @Override
    public void setPin(int pin) {
        if(!isLocked) {
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
            this.isLocked = false;
            resetAttempts();
        } else {
            this.attempts++;
            throw new IllegalArgumentException("Pin " + pin + " is not corrected");
        }
    }

    @Override
    public void lock() {
        if(this.pin != -1) {
            this.isLocked = true;
        } else throw new IllegalStateException("Il pin non è settato");
    }

    @Override
    public boolean isLocked() {
        return this.isLocked;
    }

    @Override
    public boolean isBlocked() {
        return this.isBlocked;
    }

    @Override
    public int getMaxAttempts() {
        return 0;
    }

    @Override
    public int getFailedAttempts() {
        return this.attempts;
    }

    @Override
    public void reset() {

    }
}
