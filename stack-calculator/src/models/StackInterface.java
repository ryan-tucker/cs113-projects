package models;

import java.util.EmptyStackException;

/**
 * Stack interface containing all of the methods to implement for
 * Stack behavior. A separate interface was made, since the API
 * includes Stack as a class. In addition to this, since the Stack
 * class is an instance of the List interface, precariously using
 * polymorphism could allow for the potential to iterate over elements
 * that are not at the front.
 */
public interface StackInterface<E> {
    /**
     * Accessor method used to determine whether the current
     * Stack is empty.
     * @return boolean value determining whether the Stack is empty.
     */
    public boolean empty();

    /**
     * Accessor method used to get the element on the top of the Stack.
     * If the Stack is empty, an EmptyStackException is thrown.
     * @return Generic type E containing the element on top of the Stack.
     * @throws EmptyStackException if the Stack is Empty.
     */
    public E peek() throws EmptyStackException;

    /**
     * Mutator method used to push the given reference on top of the Stack.
     * @param e Generic type reference containing the element to push on top
     *          of the Stack.
     * @return Generic type E containing the element that was pushed on top of the
     * Stack.
     */
    public E push(E e);

    /**
     * Accessor method used to get the element on the top of the Stack,
     * while also removing the element from the top of the Stack.
     * If the Stack is empty, an EmptyStackException is thrown.
     * @throws EmptyStackException if the Stack is Empty.
     */
    public E pop() throws EmptyStackException;
}
