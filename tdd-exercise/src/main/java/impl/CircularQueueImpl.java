package impl;

import tdd.CircularQueue;

import java.util.LinkedList;
import java.util.Queue;

public class CircularQueueImpl implements CircularQueue {
    private static final int MAX_SIZE = 3;
    private final Queue<Integer> queue;

    public CircularQueueImpl() {
        this.queue = new LinkedList<>();
    }

    @Override
    public void push(int value) {
        if(getSize() >= getMaxSize()){
            this.queue.remove();
        }
        this.queue.add(value);
    }

    @Override
    public int pop() {
        if(isEmpty()) throw new IllegalStateException("The Queue is empty");
        return ((LinkedList<Integer>) this.queue).removeLast();
    }

    @Override
    public int peek() {
        if(isEmpty()) throw new IllegalStateException("The Queue is empty");
        return ((LinkedList<Integer>) this.queue).getLast();
    }

    @Override
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    @Override
    public int getMaxSize() {
        return MAX_SIZE;
    }

    @Override
    public int getSize() {
        return this.queue.size();
    }

    @Override
    public int getFirst() {
        return this.queue.element();
    }
}
