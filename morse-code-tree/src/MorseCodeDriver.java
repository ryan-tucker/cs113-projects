package edu.miracosta.cs113;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * MorseCodeDriver.java : Menu program for experimenting with MorseCodeTree and its translation capabilities.
 *
 * @author Ryan Tucker
 * @version 1.0
 */
public class MorseCodeDriver {
    private static final String MENU_CHOICES = "1) View Translation Table.\n2) Decode From File.\n3) Decode From Console.\n4) Exit Program.";
    private MorseCodeTree mct;
    private Scanner key;
    private boolean running;

    public static void main(String[] args){
        MorseCodeDriver driver = new MorseCodeDriver();
        driver.menu();
    }

    /**
     * Default constructor
     */
    public MorseCodeDriver(){
        this.mct = new MorseCodeTree();
        running = true;
        key = new Scanner(System.in);
    }

    /**
     * Main Loop for menu choices. Do-While loop terminated by false running instance variable (set by exit()).
     */
    public void menu(){
        int choice;
        String input;
        System.out.println("Welcome to Ryan's Morse Code Program!");
        do{
            choice = getIntInput(MENU_CHOICES, 1,4);
            if (choice == 1){
                mct.outputTranslationTable();
            }else if (choice == 2){
                input = getStringInput("Enter the name of the file you would like to decode from.");
                mct.decode(new File(input));
            }else if (choice == 3){
                input = getStringInput("Enter the Morse Code in '*'s and '-'s separated by spaces.");
                mct.decode(input);
            }else if (choice == 4){
                exit();
            }
        }while (running);
    }

    /**
     * Handles integer input and allows for bounded input
     *
     * Will continue prompting user for input until it is valid.
     *
     * Clears buffer of new lines after getting valid input.
     *
     * @param message Message to be prompted to the user.
     * @param min minimum integer value accepted
     * @param max maximum integer value accepted
     * @return integer >= min && <= max
     */
    public int getIntInput(String message, int min, int max){
        int choice = Integer.MAX_VALUE;
        boolean isValid = false;

        do{
            try{
                System.out.println(message);
                choice = key.nextInt();
                if (choice >= min && choice <= max){
                    isValid = true;
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid Input. Please Try Again");
                key.nextLine();
            }
        }while(!isValid);
        key.nextLine();
        return choice;
    }

    /**
     * Simple helper method which gets String input from the user.
     *
     * No error checking is done on input data.
     *
     * @param message Message to be prompted to the user
     * @return String input from the user
     */
    public String getStringInput(String message){
        System.out.println(message);
        return key.nextLine();
    }

    /**
     * Closes Scanner and sets running to false, which controls Main Menu Do-While loop
     */
    public void exit(){
        running = false;
        key.close();
    }
}
