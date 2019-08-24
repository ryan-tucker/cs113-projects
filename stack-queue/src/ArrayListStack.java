/**
 * ArrayListStack.java: Implementation of a queue using an ArrayList
 *
 * @author Ryan Tucker
 * @version 1.0
 */
package edu.miracosta.cs113;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.ListIterator;

public class ArrayListStack<E> implements StackInterface<E>{
    private ArrayList<E> data;

    public ArrayListStack(){
        data = new ArrayList<E>();
    }

    @Override
    public boolean empty() {
        return data.isEmpty();
    }

    @Override
    public E peek() {
        if (!empty()){
            return data.get(data.size() - 1);
        }else{
            throw new EmptyStackException();
        }
    }

    @Override
    public E pop() {
        if (!empty()) {
            return data.remove(data.size() - 1);
        }else{
            throw new EmptyStackException();
        }

    }

    @Override
    public E push(E obj) {
        data.add(obj);
        return obj;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Rear :");
        for (E e: data){
            sb.append(e);
        }

        sb.append(" :Head");

        return sb.toString();
    }
}
