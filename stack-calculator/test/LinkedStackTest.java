package models;

import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Class containing the necessary unit tests for the Stack class that follows
 * a LinkedList adaptor pattern
 */
public class LinkedStackTest {
    /** 2D String array used for unit tests */
    private static final Integer[][] integerArrays = {{1, 2, 3, 4}};

    /** LinkedStack reference used for unit tests */
    private LinkedStack<Integer> integerStack;

    /**
     * Helper method used to fill either stack prior to specific unit
     * tests.
     * @param indexOne int value containing the index of the first array to push
     *                 onto the stack
     */
    private void populateStacks(int indexOne) {
        Integer[] arrayOne = (indexOne >= 0) ? LinkedStackTest.integerArrays[indexOne] : null;

        // Populate first stack
        if(arrayOne != null) {
            for(int index = 0; index < arrayOne.length; index++) {
                this.integerStack.push(arrayOne[index]);
            }
        }

    }

    @Before
    public void setup() {
        this.integerStack = new LinkedStack<Integer>();
    }

    /**
     * Unit test designed to test whether a LinkedStack is empty after
     * instantiation.
     */
    @Test
    public void testEmptyAfterInstantiated() {
        assertTrue(this.integerStack.empty());
    }

    /**
     * Unit test designed to test whether a LinkedStack is empty on an
     * nonempty list.
     */
    @Test
    public void testEmptyFails() {
        this.populateStacks(0);
        assertFalse("empty() should return true on empty stacks!", this.integerStack.empty());
    }

    /**
     * Unit test designed to test whether a LinkedStack is empty after
     * popping all elements.
     */
    @Test
    public void testEmptyAfterPopping() {
        this.populateStacks(0);

        while(!this.integerStack.empty()) {
            this.integerStack.pop();
        }

        assertTrue(this.integerStack.empty());
    }

    /**
     * Unit test designed to see if an EmptyStackException was thrown
     * when peeking an empty stack.
     */
    @Test
    public void testPeekOnEmptyStack() {
        try {
            this.integerStack.peek();
            fail("Empty stacks should throw EmptyStackException when peeking!");
        }catch(EmptyStackException exception) {
            // TEST PASSED :D:D:D:D:D:D:D
        }
    }

    /**
     * Unit test designed to see if the element being peeked is correct.
     */
    @Test
    public void testPeekOnFilledStack() {
        Integer[] array = LinkedStackTest.integerArrays[0];
        for(int index = 0; index < array.length; index++) {
            this.integerStack.push(array[index]);

            assertEquals("Value from peek() does not match expected value!", this.integerStack.peek(), array[index]);
        }
    }

    /**
     * Unit test designed to see if the element being pushed is correct.
     */
    @Test
    public void testPushOnStack() {
        Integer[] array = LinkedStackTest.integerArrays[0];
        for(int index = 0; index < array.length; index++) {
            assertEquals("Value being pushed does not match expected value!",
                    this.integerStack.push(array[index]), array[index]);
        }
    }

    /**
     * Unit test designed to see if an EmptyStackException was thrown
     * when popping an empty stack.
     */
    @Test
    public void testPopEmptyStackFails() {
        try {
            this.integerStack.pop();
            fail("Calling pop() on empty stacks should throw an EmptyStackException!");
        }catch(EmptyStackException exception) {
            //TEST PASSED :D:D:D:D:D:D
        }
    }

    /**
     * Unit test designed to see if the popped elements of a LinkedStack
     * are equal to the expected values.
     */
    @Test
    public void testPopFilledStack() {
        this.populateStacks(0);
        Integer[] array = LinkedStackTest.integerArrays[0];

        for(int index = array.length - 1; index >= 0; index--) {
            assertEquals("Expected value from pop() and received value do not match!",
                    this.integerStack.pop(), array[index]);
        }
    }
}