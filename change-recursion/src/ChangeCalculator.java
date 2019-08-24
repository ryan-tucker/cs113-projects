/**
 * ChangeCalculator.java: Recursively calculates the total combinations of coins that can make up money values.
 * Also can output all combinations to a .txt file.
 *
 * @author Ryan Tucker
 * @version 1.0
 */

package edu.miracosta.cs113.change;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * ChangeCalculator : Class containing the recursive method calculateChange, which determines and prints all
 * possible coin combinations representing a given monetary value in cents.
 *
 * Problem derived from Koffman & Wolfgang's Data Structures: Abstraction and Design Using Java (2nd ed.):
 * Ch. 5, Programming Project #7, pg. 291.
 *
 * An additional method, printCombinationsToFile(int), has been added for the equivalent tester file to verify
 * that all given coin combinations are unique.
 */
public class ChangeCalculator {
    private static final String FILE = "src/edu/miracosta/cs113/change/CoinCombinations.txt";

    /**
     * Wrapper method for determining all possible unique combinations of quarters, dimes, nickels, and pennies that
     * equal the given monetary value in cents.
     *
     * In addition to returning the number of unique combinations, this method will print out each combination to the
     * console. The format of naming each combination is up to the user, as long as they adhere to the expectation
     * that the coins are listed in descending order of their value (quarters, dimes, nickels, then pennies). Examples
     * include "1Q 2D 3N 4P", and "[1, 2, 3, 4]".
     *
     * @param cents a monetary value in cents
     * @return the total number of unique combinations of coins of which the given value is comprised
     */
    public static int calculateChange(int cents) {
        int[] coins = {25,10,5,1};
        ArrayList<String> outputs = new ArrayList<>();
        String output = "0 0 0 0";
        int total = calculateChangeRecursion(coins,coins.length,cents,output,outputs);
        for (String combo: outputs){
            System.out.println(combo);
        }
        return total;
    }

    private static int calculateChangeRecursion(int[] coins, int currentIndex, int cents, String output, ArrayList<String> outputs)
    {
        if (cents == 0){
            //outputs.add(output);
            return 1;
        }
        if (cents < 0 || currentIndex <= 0){
            return 0;
        }

        int nextCoinValue = calculateChangeRecursion(coins,currentIndex - 1, cents, output, outputs);
        String[] temp = output.split("\\s+");
        int current = Integer.parseInt(temp[currentIndex - 1]) + 1;
        temp[currentIndex - 1] = Integer.toString(current);
        output = "";
        for (int i = 0; i < temp.length; i ++){
            if (i != temp.length - 1){
                output += temp[i] + " ";
            }else{
                output += temp[i];
            }
        }
        int subtractCoinValue = calculateChangeRecursion(coins,currentIndex, cents-coins[currentIndex-1], output, outputs);

        return nextCoinValue + subtractCoinValue;

    }


    /**
     * Calls upon calculateChange(int) to calculate and print all possible unique combinations of quarters, dimes,
     * nickels, and pennies that equal the given value in cents.
     *
     * Similar to calculateChange's function in printing each combination to the console, this method will also
     * produce a text file named "CoinCombinations.txt", writing each combination on their own separate lines.
     *
     * @param cents a monetary value in cents
     */
    public static void printCombinationsToFile(int cents) {
        int[] coins = {25,10,5,1};
        ArrayList<String> outputs = new ArrayList<>();
        String output = "0 0 0 0";
        int total = calculateChangeRecursion(coins,coins.length,cents,output,outputs);

        try{
            PrintWriter writer = new PrintWriter(new File(FILE));
            for (String combo: outputs){
                System.out.println(combo);
                writer.print(combo + "\n");
            }
            writer.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
} // End of class ChangeCalculator