package tdd;

/**
 *  Task 3 - TDD for Circular Queue
 *  A simple CircularQueue that stores integers with a **fixed** capacity.
 *  When full, new elements overwrite the oldest ones.
 *  
 *  When removing elements, the oldest ones are removed first.
 *  Therefore, giving [4, 5, 3], the first element to be removed is 4, then 5, and finally 3.
 *  
 *  For the exercise:
 *   - Think about the test cases you need to write.
 *   - Introduce methods in the interface in order to make the tests pass.
 *   - Refactor
 */
public interface CircularQueue {

    /**
     * Pushes an Integer onto the queue
     *
     * @param value The Integer to push
     */
    void push(int value);

    /**
     * Removes and returns the last element pushed in the queue.
     *
     * @return The popped element.
     * @throws IllegalStateException if the stack is empty.
     */
    int pop();

    /**
     * Retrieves, but does not remove, the last element pushed in the queue.
     *
     * @return The last element pushed in the stack.
     * @throws IllegalStateException if the queue is empty.
     */
    int peek();

    /**
     * Checks if the queue is empty.
     *'
     * @return true if the queue is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Return the max number of elements that the queue can store.
     *
     * @return the max size for the queue
     */
    int getMaxSize();

    /**
     * Return the current number of elements stored in the queue
     *
     * @return the current size of the queue
     */
    int getSize();
}