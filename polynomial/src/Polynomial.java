/**
 * Polynomial.java: Contains a linked list of type Term.
 *
 * @author  Ryan Tucker
 * @version 1.0
 */
package edu.miracosta.cs113;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

public class Polynomial {
    private LinkedList<Term> polynomial;

    /**
     * Default Constructor. Instantiates linked list instance variable (polynomial)
     */
    public Polynomial() {
        polynomial = new LinkedList<Term>();
    }

    /**
     * Full Constructor. Warning: A deep copy is not made of linked list passed in.
     *
     * @param polynomial Linked List of type Term.
     */
    public Polynomial(LinkedList<Term> polynomial){
        this.polynomial = new LinkedList<>(polynomial);
    }

    /**
     * Copy constructor. Performs a deep copy.
     * @param other Original Polynomial object to be copied.
     */
    public Polynomial(Polynomial other) {
        if (other != null && other.polynomial != null) {
            Term current = null;
            this.polynomial = new LinkedList<Term>();

            ListIterator iterator = other.polynomial.listIterator();

            while (iterator.hasNext()) {
                current = (Term) iterator.next();
                this.polynomial.add(new Term(current));
            }
        }
    }

    /**
     * Populates linked list with terms from array of type Term. Linked List will be sorted based on
     * exponent values.
     *
     * @param terms array of type Term. Does not need to be sorted.
     */
    public void createPolynomial(Term[] terms) {
        for (Term term : terms) {
            this.addTerm(term);
        }
    }

    /**
     * Adds two polynomials together, calculating resultant coefficients of like exponents
     * and removing Terms which result in a coefficient of 0.
     *
     * @param other Polynomial object to add to calling object.
     */
    public void add(Polynomial other) {
        for (Term term : other.polynomial) {
            this.addTerm(term);
        }
    }

    /**
     * Adds a term to Linked List instance variable. Term placed in linked list based
     * on value of exponent. If a term in the linked list already exists with the same
     * exponent, the coefficients of the two terms will be added together.
     *
     * @param toAdd Term to add to linked list.
     */
    public void addTerm(Term toAdd) {
        boolean isAdded = false;
        Term current = null;

        ListIterator iterator = polynomial.listIterator();

        while (iterator.hasNext() && !isAdded) {
            current = (Term) iterator.next();

            if (toAdd.compareTo(current) > 0) {
                iterator.previous();
                iterator.add(toAdd);
                isAdded = true;
            } else if (toAdd.compareTo(current) == 0) {
                if (current.getCoefficient() + toAdd.getCoefficient() == 0) {
                    iterator.remove();
                } else {
                    iterator.set(new Term(current.getCoefficient() + toAdd.getCoefficient(), current.getExponent()));
                }
                isAdded = true;
            }
        }

        if (!isAdded) { //Must be smallest exponent value, add to end of the list
            polynomial.add(toAdd);
        }
    }

    /**
     * Remove a term from the linked list instance variable. Uses Java's linked list remove method.
     *
     * @param toRemove Term to remove from linked list instance variable.
     */
    public void removeTerm(Term toRemove){
        polynomial.remove(toRemove);
    }

    /**
     * Clear linked list using Java's linked list clear method.
     */
    public void clear() {
        polynomial.clear();
    }

    /**
     * Returns number of terms contained in linked list using Java's linked list size method.
     *
     * @return integer representing size of the linked list.
     */
    public int getNumTerms() {
        return polynomial.size();
    }

    /**
     * Returns Term at specified index in linked list.
     *
     * @param index index of Term to be accessed. :ge
     *
     * @return Term at index in linked list or null value if index is out of bounds.
     */
    public Term getTerm(int index) {
        if (index > -1 && index < getNumTerms()){
            return polynomial.get(index);
        }else{
            return null;
        }

    }

    @Override
    public boolean equals(Object other){
        if (other != null && this.getClass() == other.getClass()){
            Polynomial temp = (Polynomial) other;
            if (temp.getNumTerms() != this.getNumTerms()){
                return false;
            }else{
                for (int i = 0; i < polynomial.size(); i ++){
                    if (!this.getTerm(i).equals(temp.getTerm(i))){
                        return false;
                    }
                }
            }
            return true;
        }else{
            return false;
        }

    }
    public String toString() {
        if (polynomial.isEmpty()) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < polynomial.size(); i++) {
            sb.append(polynomial.get(i));
            sb.append(' ');
            if (i == 0) {
                if (sb.charAt(0) == '+') {
                    sb.deleteCharAt(0);
                }
            }
        }

        return sb.toString();
    }

}
