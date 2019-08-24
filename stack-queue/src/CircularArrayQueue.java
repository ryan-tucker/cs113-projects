/**
 * CircularArrayQueue.java: Implementation of a queue using a circular array.
 *
 * @author Ryan Tucker
 * @version 1.0
 */


package edu.miracosta.cs113;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class CircularArrayQueue<E> implements Queue<E> {
    private static final int INITIAL_CAPACITY = 10;
    private E[] data;
    private int size;
    private int front;
    private int rear;
    private int capacity;

    /**
     * Default Constructor. Initializes all instance variables.
     */
    public CircularArrayQueue(){
        capacity = INITIAL_CAPACITY;
        data = (E[])new Object[INITIAL_CAPACITY];
        size = 0;
        front = 0;
        rear = size - 1;
    }

    /**
     * Constructor with specific initial capacity for inner data structure.
     *
     * @param initalCapacity integer representing initial capacity of circular array
     */
    public CircularArrayQueue(int initalCapacity){
        capacity = initalCapacity;
        data = (E[])new Object[capacity];
        size = 0;
        front = 0;
        rear = size -1;
    }

    @Override
    public boolean add(E e) {
        if (capacity == size){
            throw new IllegalStateException();
        }

        rear = (rear + 1) % capacity;
        data[rear] = e;
        size ++;
        return true;
    }

    @Override
    public boolean offer(E e) {
        if (capacity == size){
            reallocate();
        }

        rear = (rear + 1) % capacity;
        data[rear] = e;
        size ++;
        return true;
    }

    @Override
    public E remove() {
        if (size == 0){
            throw new NoSuchElementException();
        }else{
            E toReturn = data[front];
            front = (front + 1) % capacity;
            size --;
            return toReturn;
        }
    }

    @Override
    public E poll() {
        E toReturn = data[front];
        front = (front + 1) % capacity;
        size --;
        return toReturn;
    }

    @Override
    public E element() {
        if (size == 0){
            throw new NoSuchElementException();
        }else{
            return data[front];
        }
    }

    @Override
    public E peek() {
        return data[front];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        int j = front;
        for (int i = 0; i < size; i ++){
            if (data[j].equals(o)){
                return true;
            }
            j = (j + 1) % capacity;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = front;

            @Override
            public boolean hasNext() {
                return index != rear;
            }

            @Override
            public E next() {
                if (hasNext()){
                    E temp = data[index];
                    index = (index + 1) % capacity;
                    return temp;
                }else{
                    throw new NoSuchElementException();
                }
            }
        };
    }

    //ToDo
    public Object[] toArray() {
        return new Object[0];
    }

    //ToDo
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        int j = front;
        for (int i = 0; i < size; i ++){
            if (data[j].equals(o)){
                if (j == front){
                    remove();
                }else{
                    data[j] = null;
                    shiftArray(j);
                    size --;
                }
                return true;

            }
            j = (j + 1) % capacity;
        }
        return false;
    }

    /**
     * Helper method for shifting circular array after removing an element.
     *
     * @param indexRemoved Integer representing index of removed element.
     */
    private void shiftArray(int indexRemoved){
        int index = indexRemoved;
        while (index != rear){
            data[index] = data[(index + 1) % capacity];
            index = (index + 1) % capacity;
        }
        rear --;
    }

    //ToDo
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    //ToDo
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    //ToDo
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    //ToDo
    public boolean retainAll(Collection<?> c) {
        return false;
    }



    @Override
    public void clear() {
        data = (E[])new Object[INITIAL_CAPACITY];
        size = 0;
        front = 0;
        rear = size - 1;
    }

    /**
     * Helper method for offer(E e) which is called if current array is filled.
     * A new array of double the size is created and the elements are copied over
     * from the original. Data in the new array will be in the same order, however it
     * will always start at index 0 and not wrap around.
     */
    private void reallocate(){
        E[] temp = (E[])new Object[capacity * 2];

        int j = front;

        for (int i = 0; i < size; i ++){
            temp[i] = data[j];
            j = (j + 1) % capacity;
        }

        capacity *= 2;
        data = temp;
        front = 0;
        rear = size - 1;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        int j = front;
        for (int i = 0; i < size; i ++){
            sb.append(j);
            sb.append(':');
            sb.append(data[j]);
            sb.append('\n');
            j = (j + 1) % capacity;
        }
        return sb.toString();
    }

    /**
     * Accessor for size
     *
     * @return integer representing current number of elements in the array
     */
    public int getSize() {
        return size;
    }

    /**
     * Mutator for size
     *
     * @param size integer representing current number of elements in the array
     */
    private void setSize(int size) {
        this.size = size;
    }

    /**
     * Accessor for front
     *
     * @return integer representing index of the first element in the array
     */
    public int getFront() {
        return front;
    }

    /**
     * Mutator for front
     * @param front integer representing index of the first element in the array
     */
    private void setFront(int front) {
        this.front = front;
    }

    /**
     * Accessor for rear
     *
     * @return integer representing index of last element in the array
     */
    public int getRear() {
        return rear;
    }

    /**
     * Mutator for rear
     * @param rear integer representing index of last element in the array
     */
    private void setRear(int rear) {
        this.rear = rear;
    }
}
