package edu.miracosta.cs113;

/**
 * InvalidMorseCodeException.java : Custom exception for use in MorseCodeTree.java.
 *
 * To be thrown if Morse Code input contains invalid characters or does not follow the structure of the tree.
 */
public class InvalidMorseCodeException extends Exception {
    public InvalidMorseCodeException(){
        super("Invalid Morse Code input");
    }
}
