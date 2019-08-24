package edu.miracosta.cs113;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character> implements Serializable{
    private static final String FILE = "MorseCode.txt";
    private static final int ASCII_OFFSET = 97;
    private static final String CODE_KEY_ALPHABETICAL = "*- -*** -*-* -** * **-* --* **** ** *--- -*- *-** -- -* --- *--* --*- *-* *** - **- ***- *-- -**- -*-- --**";
    private static final char LEFT = '*';
    private static final char RIGHT = '-';

    /**
     * Default Constructor which uses readMorseCodeTree() to populate the tree.
     */
    public MorseCodeTree(){
        super();
        this.root = new Node<>('!');
        readMorseCodeTree();
    }

    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */
    public String translateFromMorseCode(String morseCode) throws InvalidMorseCodeException{
        if (root == null){
            return "Morse Code Tree not initialized.";
        }else if (morseCode.contains(" ")){ //Multiple letters in Morse Code
            StringBuilder sb = new StringBuilder();
            String[] morseCodes = morseCode.split(" ");
            for (String morse: morseCodes){
                sb.append(translateSingleLetter(morse));
            }
            return sb.toString();
        }else{
            return "" + translateSingleLetter(morseCode);
        }
    }

    /**
     * Translates a single segment of Morse Code based on the logic above
     *
     * @param code String representing one letter in Morse Code
     * @return char representing lower case english letter
     * @throws InvalidMorseCodeException thrown if input characters are invalid or segment goes out of bounds of tree
     */
    public char translateSingleLetter(String code)throws InvalidMorseCodeException{
        char currentToken;
        Node<Character> current = root;
        for (int i = 0; i < code.length(); i ++){
            currentToken = code.charAt(i);
            if (currentToken != LEFT && currentToken != RIGHT){
                throw new InvalidMorseCodeException();
            }else if (current.right == null && current.left == null){
                throw new InvalidMorseCodeException();
            }else {
                switch (currentToken){
                    case LEFT:
                        current = current.left;
                        break;
                    case RIGHT:
                        current = current.right;
                        break;
                }
            }
        }
        return current.data;
    }

    /**
     * Outputs formatted table of all english letters and their respective Morse Code values
     */

    public void outputTranslationTable(){
        String[] codes = CODE_KEY_ALPHABETICAL.split(" ");
        System.out.println(" ----------");
        for (int i = 0; i < codes.length; i ++){
            System.out.printf("|%-4c:", i + ASCII_OFFSET);
            System.out.printf("%5s|\n", codes[i]);
            System.out.println(" ----------");
        }
    }

    /**
     * Translates Morse Code from a text file
     *
     * @param file text file containing Morse Code separated by spaces
     */
    public void decode(File file){
        Scanner in = null;
        try{
            in = new Scanner(file);
            try{
                while (in.hasNext()){
                    System.out.print(translateFromMorseCode(in.next()));
                }
                System.out.println();
            }catch (InvalidMorseCodeException e){
                System.out.println(e.getMessage());
                in.close();
            }
        }catch (FileNotFoundException e){
            System.out.println("Invalid File Name.");
        }
        if(in != null){
            in.close();
        }
    }

    /**
     * Wrapper method for translateFromMorseCode(String code) which internally handles Exceptions
     *
     * @param input
     */
    public void decode(String input){
        try{
            System.out.println(translateFromMorseCode(input));
        }catch (InvalidMorseCodeException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Populates the Morse Code Tree with lower case english letters based on their respective codes.
     */
    public void readMorseCodeTree(){
        try{
            Scanner in = new Scanner(new File(FILE));
            char letter;
            Node<Character> current = root;
            String morseToken;
            while(in.hasNextLine()){
                letter = in.next().charAt(0);
                morseToken = in.nextLine();
                for (int i = 0; i < morseToken.length(); i ++){
                        switch (morseToken.charAt(i)){
                            case '*':
                                if (current.left == null){
                                    current.left = new Node<>(letter);
                                }else{
                                    current = current.left;
                                }
                                break;
                            case '-':
                                if (current.right == null){
                                    current.right = new Node<>(letter);
                                }else{
                                    current = current.right;
                                }
                                break;
                        }
                    }
                    current = root;
                }
            in.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.exit(1);
        }
    }
} // End of class MorseCodeTree