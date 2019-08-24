/**
 * DoubleLinkedList.java: Implementation of a double linked list with head and tail nodes.
 * Includes nested classes for Node and ListIterator.
 *
 * @author Ryan Tucker
 * @version 1.0
 */
package edu.miracosta.cs113;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Collection;
import java.util.ListIterator;
import java.util.Iterator;

/**
 * Main class which encapsulates all data/logic associated with a Double Linked List.
 * Implements the List interface. Most methods required for the List interface are implemented
 * using DoubleListIterator.
 *
 * @param <E> Generic data type
 */
public class DoubleLinkedList<E> implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * Default Constructor
     */
    public DoubleLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns the size of the linked list (Number of nodes)
     * @return integer representing the number of nodes in the linked list
     */
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object obj : this){
            if (obj.equals(o)){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a new instance of an Iterator for this linked list starting at index 0.
     * True class type for this Iterator is a DoubleListIterator
     *
     * @return new instance of Iterator for this linked list
     */
    @Override
    public Iterator<E> iterator() {
        return new DoubleListIterator();
    }

    //ToDo
    public Object[] toArray() {
        return new Object[0];
    }

    //ToDo
    public <T> T[] toArray(T[] a) {
        return null;
    }

    /**
     * Appends a new node to the end of the linked list with given data
     *
     * @param e Data to be stored in linked list. Generic E must implement comparable
     *
     * @return true in accordance with Collection interface
     */
    public boolean add(E e) {
        listIterator(size).add(e);
        return true;
    }

    /**
     * Attempts to find and remove Object o from this linked list.
     *
     * @param o Object to be removed
     *
     * @return true if Object o is found and removed. false if not present.
     */
    public boolean remove(Object o) {
        ListIterator<E> iterator = listIterator();
        E current;
        while (iterator.hasNext()){
            current = iterator.next();
            if (current.equals(o)){
                iterator.remove();
                return true;
            }
        }
        return false;
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
    public boolean addAll(int index, Collection<? extends E> c) {
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

    /**
     * Empties this linked list. Head and Tail nodes are set to null, unlinked nodes
     * are left for the garbage collector. Size set to 0.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        ListIterator<E> iterator = listIterator(index);
        if (!iterator.hasNext()){
            throw new IndexOutOfBoundsException();
        }
        return iterator.next();
    }

    /**
     * Sets data in node at index to new value (element). Returns overwritten data.
     *
     * @param index index of node to be overwritten
     * @param element non-null value representing data to be stored.
     * @return data which was replaced during operation
     */
    public E set(int index, E element) {
        ListIterator<E> iterator = listIterator(index);
        if (!iterator.hasNext()){
            throw new IndexOutOfBoundsException();
        }
        E toReturn = iterator.next();
        iterator.set(element);

        return toReturn;
    }

    /**
     * Adds a new Node with specified data after Node at index - 1, and before Node at index.
     * Immediate call to previous after this method would result in returning newly added Node.
     *
     * @param index resulting index of newly added Node.
     * @param element data to be added to linked list.
     */
    public void add(int index, E element) {
        ListIterator<E> iterator = listIterator(index);
        iterator.add(element);
    }

    /**
     * Removes the Node at specified index.
     *
     * @param index index of Node to be removed.
     * @return data removed from linked list.
     */
    public E remove(int index) {
        ListIterator<E> iterator = listIterator(index);
        if (!iterator.hasNext()){
            throw new IndexOutOfBoundsException();
        }

        E toRemove = iterator.next();
        iterator.remove();
        return toRemove;
    }

    /**
     * Returns the index of Object o in linked list. Returns -1 if not present.
     *
     * @param o Object to be found in linked list.
     * @return index of Object if found, -1 if Object is not present.
     */
    public int indexOf(Object o) {
        ListIterator<E> iterator = listIterator();
        E current;

        while (iterator.hasNext()){
            current = iterator.next();
            if (current.equals(o)){
                return iterator.previousIndex();
            }
        }

        return -1;
    }

    /**
     * Returns the index of the last occurrence of Object o in linked list.
     * Returns -1 if Object o is not present.
     *
     * @param o Object to be searched for in linked list.
     * @return index of last occurrence of object, or -1 if object not found.
     */
    public int lastIndexOf(Object o) {
        ListIterator<E> iterator = listIterator();
        E current;
        int lastOccurrence = -1;

        while (iterator.hasNext()){
            current = iterator.next();
            if (current.equals(o)){
                lastOccurrence = iterator.previousIndex();
            }
        }

        return lastOccurrence;
    }

    /**
     * Returns a new instance of a ListIterator (Implemented as DoubleListIterator)
     *
     * @return new instance of ListIterator starting at index 0
     */
    public ListIterator<E> listIterator() {
        return new DoubleListIterator();
    }

    /**
     * Returns a new instance of ListIterator (Implemented as DoubleListIterator)
     * at specified index.
     *
     * @param index index of object to be returned by first call to next()
     * @return new instance of ListIterator starting at specified index.
     */
    public ListIterator<E> listIterator(int index) {
        return new DoubleListIterator(index);
    }

    //ToDo
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean equals(Object other){
        DoubleLinkedList otherList;
        if (other == null || other.getClass() != this.getClass()){
            return false;
        }else{
            otherList = (DoubleLinkedList)other;
            if (this.size != otherList.size){
                return false;
            }else{
                ListIterator thisIterator = this.listIterator();
                ListIterator otherIterator = otherList.listIterator();

                while (thisIterator.hasNext()){
                    if (!thisIterator.next().equals(otherIterator.next())){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public String toString(){
        ListIterator<E> iterator = listIterator();
        StringBuilder sb = new StringBuilder();
        E temp = null;
        sb.append('[');
        while (iterator.hasNext()){
            temp = iterator.next();
            sb.append(temp);
            if (iterator.hasNext()){
                sb.append(',');
                sb.append(" ");
            }
        }
        sb.append(']');

        return sb.toString();
    }

    /**
     * Implementation of ListIterator for a Double Linked List.
     */
    private class DoubleListIterator implements ListIterator<E> {
        private int index;
        private Node<E> nextItem;
        private Node<E> lastItemReturned;

        /**
         * Default Constructor
         */
        private DoubleListIterator(){
            index = 0;
            nextItem = head;
            lastItemReturned = null;
        }

        /**
         * Constructor starting at specified index.
         * Index specifies next Node to be returned by next()
         *
         * @param index next Node to be returned by a call to next()
         */
        private DoubleListIterator(int index){
            nextItem = head;
            if (index > -1 && index <= size){
                if (index == size){
                    nextItem = null;
                    lastItemReturned = null;
                    this.index = size;
                }
                else{
                    for (int i = 0; i < index; i ++){
                        next();
                    }
                }
            }else{
                throw new IndexOutOfBoundsException();
            }
            lastItemReturned = null;
        }

        /**
         * Returns the next element in the list and advances the cursor position.
         *
         * @return next element in list
         */
        public E next(){
            if (nextItem != null && head != null) {
                lastItemReturned = nextItem;
                nextItem = nextItem.next;
                index ++;
                return lastItemReturned.getData();
            }else{
                throw new NoSuchElementException();
            }
        }

        //

        /**
         * Returns the index of the element that would be returned by a subsequent call to next().
         *
         * @return int representing index of next node
         */
        public int nextIndex(){
            return index;
        }

        /**
         * Returns true if this list iterator has more elements when traversing the list in the forward direction.
         *
         * @return boolean representing the existence of Node in the forward direction
         */
        public boolean hasNext(){
            return nextItem != null;
        }

        /**
         * Inserts the specified element into the list at current position
         *
         * @param e non-null element to be added to list
         */
        public void add(E e){
            Node<E> temp = new Node<E>(e);
            if (head == null){ //List is empty
                head = temp;
                tail = temp;
            }
            else if (!hasNext()){ //Iterator at end of the list
                tail.next = temp;
                temp.previous = tail;
                tail = temp;
            }
            else if (!hasPrevious()){ //Iterator at the beginning of the list
                head.previous = temp;
                temp.next = head;
                head = temp;
            }
            else{ //Iterator somewhere between two nodes
                Node next = nextItem;
                Node previous = nextItem.previous;
                temp.previous = previous;
                temp.next = next;
                next.previous = temp;
                previous.next = temp;
            }
            size ++;
            index ++;
            lastItemReturned = null;
        }

        /**
         * Returns true if this list iterator has more elements when traversing the list in the reverse direction.
         *
         * @return boolean representing existence of Node in reverse direction.
         */
        public boolean hasPrevious(){
            return (index - 1 > -1);
        }

        /**
         * Returns the previous element in the list and moves the cursor position backwards.
         *
         * @return element in previous position
         */
        public E previous(){
            E toReturn = null;
            if (head == null || !hasPrevious()){
                throw new NoSuchElementException();
            }else if (nextItem == null){ //at end of list
                lastItemReturned = tail;
                nextItem = tail;
                toReturn = tail.data;
            }
            else{
                lastItemReturned = nextItem.previous;
                nextItem = lastItemReturned;
                toReturn = lastItemReturned.data;
            }
            index --;
            return toReturn;
        }

        /**
         * Returns the index of the element that would be returned by a subsequent call to previous()
         *
         * @return current position - 1, or -1 if at the start of the list
         */
        public int previousIndex(){
            return index - 1;
        }

        /**
         * Removes from the list the last element that was returned by next() or previous() (optional operation).
         */
        public void remove(){
            Node temp;
            Node previous;
            Node next;
            if (lastItemReturned == null){
                throw new IllegalStateException();
            }
            temp = lastItemReturned;

            if (temp.previous == null){ //Removing head node
                head = head.next;
                if (head != null){
                    head.previous = null;
                }
            }else if(temp.next == null){ //Removing tail node
                tail = tail.previous;
                if (tail != null){
                    tail.next = null;
                }
            }else{
                previous = temp.previous;
                next = temp.next;
                previous.next = next;
                next.previous = previous;
            }

            lastItemReturned = null;
            size --;
        }

        /**
         * Replaces the last element returned by next() or previous() with the specified element (optional operation).
         *
         * @param e non-null element to replace last returned element.
         */
        public void set(E e){
            if (lastItemReturned == null){
                throw new IllegalStateException();
            }else{
                lastItemReturned.setData(e);
                lastItemReturned = null;
            }
        }
    }

    /**
     * Private inner class Node. Holds data and links to previous and next Nodes in list.
     */
    private static class Node<E>{
        private E data;
        private Node<E> next;
        private Node<E> previous;

        /**
         * Default Constructor
         */
        private Node(){
            this.data = null;
            this.next = null;
            this.previous = null;
        }

        /**
         * Constructor for adding data.
         *
         * @param data element to be added.
         */
        private Node(E data){
            this.data = data;
            this.next = null;
            this.previous = null;
        }

        /**
         * Full Constructor
         *
         * @param data element to be added
         * @param next link to Node in the forward direction
         * @param previous link to Node in backwards direction
         */
        private Node(E data, Node next, Node previous){
            this.data = data;
            this.next = next;
            this.previous = previous;
        }

        /**
         * Mutator for data instance variable
         *
         * @param data new value for data
         */
        private void setData(E data) {
            this.data = data;
        }

        /**
         * Mutator for next instance variable
         *
         * @param next Node to be linked to in the forward direction
         */
        private void setNext(Node<E> next) {
            this.next = next;
        }

        /**
         * Mutator for previous instance variable
         *
         * @param previous Node to be linked to in the backwards direction
         */
        private void setPrevious(Node<E> previous) {
            this.previous = previous;
        }

        private E getData(){
            return this.data;
        }

        @Override
        public boolean equals(Object other){
            Node temp;
            if (other == null || other.getClass() != this.getClass()){
                return false;
            }
            else{
                temp = (Node)other;
                return data.equals(temp.data);
            }
        }

        @Override
        public String toString(){
            return data.toString();
        }
    }
}