package models;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;

/**
 * Stack class that follows a LinkedList adaptor pattern.
 * @param <E> Generic type reference containing the reference type
 *           to be used in the Stack.
 */
public class LinkedStack<E> implements StackInterface<E> {
    /** We understand that a Single LinkedList would be more efficient, but for now we will use
     * Java's implementation in order to focus on other parts of the project. */
    private List<E> data;

    /**
     * Default constructor. When invoked, a LinkedStack is instantiated with no elements.
     */
    public LinkedStack() {
        this.data = new LinkedList<E>();
    }

    @Override
    public boolean empty() {
        // Size is checked instead of the head node because there is potential
        // for adding null references to the Stack
        return this.data.size() == 0;
    }

    @Override
    public E peek() throws EmptyStackException {
        // Check if the Stack is empty beforehand
        if(this.empty()) {
            throw new EmptyStackException();
        }

        return this.data.get(0);
    }

    @Override
    public E push(E e) {
        this.data.add(0, e);

        return e;
    }

    @Override
    public E pop() throws EmptyStackException {
        // Check if the Stack is empty beforehand
        if(this.empty()) {
            throw new EmptyStackException();
        }

        return this.data.remove(0);
    }

    @Override
    public String toString() {
        String returnString = "LinkedStack\nNextElement: ";

        returnString += (this.empty()) ? "None\n" : (this.peek() + "\n");

        return returnString;
    }
}
