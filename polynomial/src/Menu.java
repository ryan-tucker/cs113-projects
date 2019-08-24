/**
 * Menu.java: Class for menu system to be implemented in a Driver for Polynomial / Term.
 * No error checking, assume the user follows instructions.
 *
 * @author  Ryan Tucker
 * @version 1.0
 */
package edu.miracosta.cs113;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.ListIterator;
import java.util.Scanner;

public class Menu {
    private Polynomial polynomial1;
    private Polynomial polynomial2;
    private Scanner key;

    /**
     * Default Constructor for Menu. Initializes both Polynomials and Scanner.
     */
    public Menu(){
        polynomial1 = new Polynomial();
        polynomial2 = new Polynomial();
        key = new Scanner(System.in);
    }

    /**
     * Full Constructor.
     *
     * @param polynomial1 Polynomial object.
     * @param polynomial2 Polynomial object.
     * @param key Scanner used for user input.
     */
    public Menu(Polynomial polynomial1, Polynomial polynomial2, Scanner key){
        setAll(polynomial1,polynomial2,key);
    }

    /**
     * Copy Constructor. Performs a deep copy of all data excluding the Scanner.
     *
     * @param original Menu object to be copied.
     */
    public Menu(Menu original){
        if (original != null){
            setPolynomial1(new Polynomial(original.polynomial1));
            setPolynomial2(new Polynomial(original.polynomial2));
            setKey(original.key);
        }
    }

    /**
     * Sets all instance variables.
     *
     * @param polynomial1 Polynomial object.
     * @param polynomial2 Polynomial object.
     * @param key Scanner used for user input.
     */
    public void setAll(Polynomial polynomial1, Polynomial polynomial2, Scanner key){
        setPolynomial1(polynomial1);
        setPolynomial2(polynomial2);
        setKey(key);
    }

    /**
     * Accessor for polynomial1.
     *
     * @return Polynomial object.
     */
    public Polynomial getPolynomial1() {
        return polynomial1;
    }

    /**
     * Mutator for polynomial1.
     *
     * @param polynomial1 new Polynomial object.
     */
    public void setPolynomial1(Polynomial polynomial1) {
        if (polynomial1 != null){
            this.polynomial1 = polynomial1;
        }else{
            this.polynomial1 = new Polynomial();
        }

    }

    /**
     * Accessor for polynomial2.
     *
     * @return Polynomial object.
     */
    public Polynomial getPolynomial2() {
        return polynomial2;
    }

    /**
     * Mutator for polynomial2.
     *
     * @param polynomial2 new Polynomial object.
     */
    public void setPolynomial2(Polynomial polynomial2) {
        if (polynomial2 != null){
            this.polynomial2 = polynomial2;
        }else{
            this.polynomial2 = new Polynomial();
        }
    }

    /**
     * Accessor for Scanner key instance variable.
     *
     * @return Scanner key.
     */
    public Scanner getKey() {
        return key;
    }

    /**
     * Mutator for Scanner key instance variable.
     *
     * @param key new Scanner object.
     */
    public void setKey(Scanner key) {
        if (key != null){
            this.key = key;
        }else{
            this.key = new Scanner(System.in);
        }
    }

    /**
     * Loop for main menu. Sub-menus (polynomialMenu) can be accessed from this menu.
     */
    public void mainMenu(){
        boolean running = true;
        while (running){
            int input;
            input = getIntInput("1:Edit Polynomial #1\n2:Edit Polynomial #2\n3:Add Polynomials\n4:Exit");
            if (input == 1){
                polynomialMenu(polynomial1);
            }
            else if (input ==2){
                polynomialMenu(polynomial2);
            }
            else if (input == 3){
                Polynomial result = new Polynomial(polynomial1);
                result.add(polynomial2);
                System.out.println(result);
            }
            else if (input == 4){
                System.out.println("Exiting Program.");
                key.close();
                System.exit(0);
            }
            else{
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    /**
     * Sub-menu for editing either Polynomial object (Instance variables)
     *
     * @param toEdit Polynomial object the user wants to edit
     */
    public void polynomialMenu(Polynomial toEdit){
        boolean running = true;

        while (running) {
            int input;
            input = getIntInput("1:Add Terms\n2:Remove Terms\n3:Clear All Terms\n4:Return to Main Menu");
            if (input == 1){
                addTerm(toEdit);
            }else if (input == 2){
                removeTerm(toEdit);
            }else if (input == 3){
                toEdit.clear();
            }else if (input == 4){
                running = false;
            }
        }
    }

    /**
     * Helper method for getting integer input from the user.
     *
     * @param choices String containing numbered choices for user to choose from
     *
     * @return The integer representing user choice
     */
    public int getIntInput(String choices){
        System.out.println(choices);
        int temp = key.nextInt();
        key.nextLine();
        return temp;
    }

    /**
     * Method to be called in polynomialMenu. User enters a new term to add to Polynomial.
     *
     * @param toEdit Polynomial object the user wants to edit.
     */
    public void addTerm(Polynomial toEdit){
        boolean running = true;
        while (running) {
            System.out.println("Enter a single term you would like to add. 4x^2 or 4 or 4x (Enter 'stop' to stop adding)");
            String termString = key.nextLine();
            if (termString.equalsIgnoreCase("stop")){
                running = false;
            }else{
                Term toAdd = new Term(termString);
                toEdit.addTerm(toAdd);
                System.out.println(toEdit);
            }
        }
    }

    /**
     * Method contained in polynomialMenu for user to remove terms from Polynomial object.
     *
     * @param toEdit Polynomial object the user wants to edit.
     */
    public void removeTerm(Polynomial toEdit) {
        boolean running = true;
        while (running) {
            System.out.println(toEdit);
            System.out.println("Enter a single term you would like to remove. EX: 4x^2 or 4 or 4x (Enter 'stop' to stop removing)");
            String termString = getStringInput();
            if (termString.equalsIgnoreCase("stop")){
                running = false;
            }else{
                Term toRemove = new Term(termString);
                toEdit.removeTerm(toRemove);
                System.out.println(toEdit);
            }
        }
    }

    /**
     * Simple helper method for getting String input from the user.
     *
     * @return String containing user input.
     */
    public String getStringInput() {
        String temp = key.nextLine();
        return temp;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "polynomial1=" + polynomial1 +
                ", polynomial2=" + polynomial2 +
                ", key=" + key +
                '}';
    }
}
