package edu.miracosta.cs113;

import edu.miracosta.cs113.change.ChangeCalculator;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * ChangeCalculatorTest : Tester class for demonstrating class ChangeCalculator's recursive method
 * calculateChange(int), which determines and prints all possible coin combinations representing a given monetary
 * value in cents.
 */
public class ChangeCalculatorTest {

    /** Expected name of text file generated by method printCombinationsToFile(int). */
    private static final String IN_FILE_NAME = "src/edu/miracosta/cs113/change/CoinCombinations.txt";

    /** Simple coin values to be tested (multiples of five). */
    private static final int[] FIVES = { 5, 10, 15, 20, 25, 30 };
    /** Numbers of unique coin combinations for simple multiples of five. */
    private static final int[] FIVES_COMBINATIONS = { 2, 4, 6, 9, 13, 18 };

    /** Larger coin values to be tested (multiples of five). */
    private static final int[] FIVES_LARGE = { 75, 80, 85, 90, 95, 100 };
    /** Numbers of unique coin combinations for larger multiples of five. */
    private static final int[] FIVES_LARGE_COMBINATIONS = { 121, 141, 163, 187, 213, 242 };

    /** Simple coin values to be tested (non-multiples of five). */
    private static final int[] OTHERS = { 3, 6, 9, 12, 21, 24, 27 };
    /** Numbers of unique coin combinations for simple non-multiples of five. */
    private static final int[] OTHERS_COMBINATIONS = { 1, 2, 2, 4, 9, 9, 13 };

    /** Larger coin values to be tested (non-multiples of five). */
    private static final int[] OTHERS_LARGE = { 76, 79, 82, 91, 94, 97, 101 };
    /** Numbers of unique coin combinations for larger non-multiples of five. */
    private static final int[] OTHERS_LARGE_COMBINATIONS = { 121, 121, 141, 187, 187, 213, 242 };

    // region ** Tests evaluating number of unique combinations **

    @Test
    public void testMultiplesOfFive() {
        for (int i = 0; i < FIVES.length; i ++) {
            assertEquals(FIVES_COMBINATIONS[i], ChangeCalculator.calculateChange(FIVES[i]));
            System.out.println("end");
        }
    }

    @Test
    public void testMultiplesOfFiveLarge() {
        for (int i = 0; i < FIVES_LARGE.length; i ++) {
            assertEquals(FIVES_LARGE_COMBINATIONS[i], ChangeCalculator.calculateChange(FIVES_LARGE[i]));
        }
    }

    @Test
    public void testOthers() {
        for (int i = 0; i < OTHERS.length; i ++) {
            assertEquals(OTHERS_COMBINATIONS[i], ChangeCalculator.calculateChange(OTHERS[i]));
        }
    }

    @Test
    public void testOthersLarge() {
        for (int i = 0; i < OTHERS_LARGE.length; i ++) {
            assertEquals(OTHERS_LARGE_COMBINATIONS[i], ChangeCalculator.calculateChange(OTHERS_LARGE[i]));
        }
    }

    // endregion ** Tests evaluating number of unique combinations **

    // region ** Test evaluating duplicate values **

    @Test
    public void testDuplicates() {
        // First verify that 75 cents yields 121 combinations
        assertEquals("Test duplicates failed - Incorrect number of combinations.",
                FIVES_LARGE_COMBINATIONS[0], ChangeCalculator.calculateChange(FIVES_LARGE[0]));

        // Set to contain lines read from "CoinCombinations.txt"
        // As a Set, this object's infrastructure prohibits the addition of duplicate elements
        Set<String> combinations = new HashSet<String>(FIVES_LARGE_COMBINATIONS[0]);

        // Flag for reading possible duplicate values
        boolean readSuccess = true;

        // Call upon static method to generate "CoinCombinations.txt", using 75 cents as input
        ChangeCalculator.printCombinationsToFile(FIVES_LARGE[0]);

        try (BufferedReader readIn = new BufferedReader(new FileReader(IN_FILE_NAME))) {
            String line = "";

            // Read each line, building a collection of unique coin combinations found
            // on each line of the generated text file
            while ((line = readIn.readLine()) != null) {
                // Sets to false if the addition of a duplicate element was attempted
                readSuccess = combinations.add(line);

                // If a duplicate value has been found
                if (! readSuccess) {
                    fail("Test failed - Perhaps duplicate coin combinations were written to this file?");
                }
            }
        }
        catch (FileNotFoundException fnfe) {
            fail("Test duplicates failed - File not found. Verify that an existing file has been named accordingly.");
        }
        catch (IOException ioe) {
            fail("Test duplicates failed - Unexpected IO Exception.");
        }
    }

    // endregion ** Test evaluating duplicate values **

} // End of class ChangeCalculatorTest