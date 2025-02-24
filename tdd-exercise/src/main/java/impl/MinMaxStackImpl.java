package impl;

import tdd.MinMaxStack;
import java.util.*;

public class MinMaxStackImpl implements MinMaxStack {
    private final Stack<Integer> stack;

    public MinMaxStackImpl() {
        this.stack = new Stack<>();
    }

    @Override
    public void push(int value) {
        this.stack.push(value);
    }

    @Override
    public int pop() {
        if(this.stack.isEmpty()) throw new IllegalStateException("Cannot pop last element, stack is empty!");
        return this.stack.pop();
    }

    @Override
    public int peek() {
        if(this.stack.isEmpty()) throw new IllegalStateException("Cannot retrieve last element, stack is empty!");
        return this.stack.peek();
    }

    @Override
    public int getMin() {
        if(this.stack.isEmpty()) throw new IllegalStateException("Cannot get minimum, stack is empty!");
        return 0;
    }

    @Override
    public int getMax() {
        if(this.stack.isEmpty()) throw new IllegalStateException("Cannot get max, stack is empty!");
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public int size() {
        return this.stack.size();
    }
}
