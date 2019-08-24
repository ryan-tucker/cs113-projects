package edu.miracosta.cs113;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * CircularArrayQueueTester : a test class for a Queue interface implementation which utilizes a circular array.
 */
public class CircularArrayQueueTester {

    /** Values to be added to the queue. */
    private static final String[] STRING_VALUES = { "Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf" };

    /** The initial capacity of the queue. */
    private static final int INITIAL_CAPACITY = 5;

    /** Object derived from the Queue abstract class. */
    private Queue<String> circularQueue;

    /**
     * Helper function for test operations which utilize methods offer or add.
     *
     * @param numElements the number of elements to add to the queue
     * @param isOffer flag for using either offer (true) or add (false)
     */
    @SuppressWarnings("ManualArrayToCollectionCopy")
    private void populateQueue(int numElements, boolean isOffer) {
        if (isOffer) {
            for (int i = 0; i < numElements; i ++) {
                circularQueue.offer(STRING_VALUES[i]);
            }
        }
        else {
            for (int i = 0; i < numElements; i ++) {
                circularQueue.add(STRING_VALUES[i]);
            }
        }
    }

    /**
     * Creates a circular queue with its default initial capacity. This function is executed every single time
     * before each test runs.
     */
    @Before
    public void setup() {
        circularQueue = new CircularArrayQueue<String>(INITIAL_CAPACITY);
    }

    // region peek and element tests

    @Test
    public void testPeekEmpty() {
        assertEquals("Test peek failed - Call from an empty queue should return null.", null, circularQueue.peek());
    }

    @Test
    public void testElementEmpty() {
        try {
            circularQueue.element();
            fail("Test element failed - Call from an empty queue should throw NoSuchElementException.");
        } catch (NoSuchElementException nse) { /* Test Success */ }
    }

    // endregion peek and element tests
    // region offer tests

    @Test
    public void testOfferAndExamineOne() {
        populateQueue(1, true);
        assertEquals("Test failed - Call to peek should return the head of this queue.", STRING_VALUES[0], circularQueue.peek());
        assertEquals("Test failed - Call to element should return the head of this queue.", STRING_VALUES[0], circularQueue.element());
    }

    @Test
    public void testOfferAndExamineMany() {
        populateQueue(3, true);
        assertEquals("Test failed - Call to peek should return the head of this queue.", STRING_VALUES[0], circularQueue.peek());
        assertEquals("Test failed - Call to element should return the head of this queue.", STRING_VALUES[0], circularQueue.element());
    }

    @Test
    public void testOfferPromptingReallocation() {
        populateQueue(INITIAL_CAPACITY + 2, true); // To be verified by poll and remove tests
    }

    // endregion offer tests
    // region add tests

    @Test
    public void testAddAndExamineOne() {
        populateQueue(1, false);
        assertEquals("Test failed - Call to peek should return the head of this queue.", STRING_VALUES[0], circularQueue.peek());
        assertEquals("Test failed - Call to element should return the head of this queue.", STRING_VALUES[0], circularQueue.element());
    }

    @Test
    public void testAddAndExamineMany() {
        populateQueue(3, false);
        assertEquals("Test failed - Call to peek should return the head of this queue.", STRING_VALUES[0], circularQueue.peek());
        assertEquals("Test failed - Call to element should return the head of this queue.", STRING_VALUES[0], circularQueue.element());
    }

    @Test
    public void testAddError() {
        populateQueue(INITIAL_CAPACITY, false);
        try {
            circularQueue.add(STRING_VALUES[INITIAL_CAPACITY]);
            fail("Test add failed - Attempt to add to full queue without reallocation prompts IllegalStateException.");
        } catch (IllegalStateException ise) { /* Test Success */ }
    }

    // endregion add tests
    // region poll tests

    @Test
    public void testPollEmpty() {
        assertEquals("Test poll failed - Call from an empty queue should return null.", null, circularQueue.poll());
    }

    @Test
    public void testPollOne() {
        circularQueue.offer(STRING_VALUES[0]);
        assertEquals("Test poll failed - Call should return the head of the queue.", STRING_VALUES[0], circularQueue.poll());

        circularQueue.add(STRING_VALUES[1]);
        assertEquals("Test poll failed - Call should return the head of the queue.", STRING_VALUES[1], circularQueue.poll());
    }

    @Test
    public void testPollMany() {
        populateQueue(3, true);
        for (int i = 0; i < 3; i ++) {
            assertEquals("Test poll failed - Call should return the head of the queue.", STRING_VALUES[i], circularQueue.poll());
        }
    }

    @Test
    public void testPollManyAfterReallocation() {
        populateQueue(INITIAL_CAPACITY, true);

        circularQueue.offer(STRING_VALUES[INITIAL_CAPACITY]);
        circularQueue.offer(STRING_VALUES[INITIAL_CAPACITY + 1]);

        for (int i = 0; i < (INITIAL_CAPACITY + 1); i ++) {
            assertEquals("Test poll failed - Call should return the head of the queue.", STRING_VALUES[i], circularQueue.poll());
        }
    }

    // endregion poll tests
    // region remove tests

    @Test
    public void testRemoveEmpty() {
        try {
            circularQueue.remove();
            fail("Test remove failed - Call from an empty queue should throw NoSuchElementException.");
        } catch (NoSuchElementException nsee) { /* Test Success */ }
    }

    @Test
    public void testRemoveOne() {
        circularQueue.offer(STRING_VALUES[0]);
        assertEquals("Test remove failed - Call should return the head of the queue.", STRING_VALUES[0], circularQueue.remove());

        circularQueue.add(STRING_VALUES[1]);
        assertEquals("Test remove failed - Call should return the head of the queue.", STRING_VALUES[1], circularQueue.remove());
    }

    @Test
    public void testRemoveMany() {
        populateQueue(3, true);
        for (int i = 0; i < 3; i ++) {
            assertEquals("Test remove failed - Call should return the head of the queue.", STRING_VALUES[i], circularQueue.remove());
        }
    }

    @Test
    public void testRemoveManyAfterReallocation() {
        populateQueue(INITIAL_CAPACITY, true);

        circularQueue.offer(STRING_VALUES[INITIAL_CAPACITY]);
        circularQueue.offer(STRING_VALUES[INITIAL_CAPACITY + 1]);

        for (int i = 0; i < (INITIAL_CAPACITY + 1); i ++) {
            assertEquals("Test remove failed - Call should return the head of the queue.", STRING_VALUES[i], circularQueue.remove());
        }
    }

    // endregion remove tests

} // End of class CircularArrayQueueTester