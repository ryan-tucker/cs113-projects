/**
 * CalculatorModelTest.java : Various Tests for the CalculatorModel class.
 *
 * @author  Ryan Tucker
 * @version 1.0
 */
package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;


public class CalculatorModelTest {
    private static final String[] INFIX_EXPRESSIONS = {"14 + 18 / 2 * 18", "15 * 18 + 12/ 3 + 9", "(9 + 33 - 6) / 6 - 3", "(17 - 3) * (14 - 6) - 22", "150 * (2 + 2)"};
    private static final String[] INFIX_RESULTS = {"176", "283", "3", "90", "600"};

    private static final String[] INFIX_BOUNDARY_CASES = {"", "1++1"};
    private static final String[] INFIX_BOUNDARY_CASE_RESULTS = {"0", ""};

    private static final String[] DERIVATIVE_STRINGS = {"4x^2 + 2x + 6", "0", "( 14x^7 - 2x^5 + 2x )", "4x^3 - x + 3"};
    private static final String[] DERIVATIVE_EXPECTED = {"8x+2+0", "0", "(98x^6-10x^4+2)", "12x^2-1+0"};

    private static final int[] OPERANDS_1 = {1, 5, 2, 0, 15, 10};
    private static final int[] OPERANDS_2 = {15, 5, 3, 2, 3, 0};
    private static final int[] CALCULATE_ADD_RESULTS = {16, 10, 5, 2, 18, 10};
    private static final int[] CALCULATE_SUBTRACT_RESULTS = {-14, 0, -1, -2, 12, 10};
    private static final int[] CALCULATE_MULTIPLY_RESULTS = {15, 25, 6, 0, 45, 0};
    private static final int[] CALCULATE_DIVIDE_RESULTS = {0, 1, 0, 0, 5, 0};
    private CalculatorModel calculator;

    @Before
    public void setup() {
        calculator = new CalculatorModel();
    }

    /**
     * Tests evaluate(String expression) for various infix expressions, including parentheses.
     */
    @Test
    public void testEvaluate() {
        String infix;
        for (int i = 0; i < INFIX_EXPRESSIONS.length; i ++) {
            infix = INFIX_EXPRESSIONS[i];
            Assert.assertEquals("Calculated result does not match expected result.", INFIX_RESULTS[i], calculator.evaluate(infix));
        }
    }

    /**
     * Tests CalculatorModel's calculate(int operand1, int operand2, char operator) with various operands to be added.
     */
    @Test
    public void testCalculateAdd() {
        for (int i = 0; i < OPERANDS_1.length; i ++) {
            Assert.assertEquals(CALCULATE_ADD_RESULTS[i],CalculatorModel.calculate(OPERANDS_1[i],OPERANDS_2[i], '+'));
        }
    }

    /**
     * Tests CalculatorModel's calculate(int operand1, int operand2, char operator) with various operands to be subtracted.
     */
    @Test
    public void testCalculateSubtract() {
        for (int i = 0; i < OPERANDS_1.length; i ++) {
            Assert.assertEquals(CALCULATE_SUBTRACT_RESULTS[i],CalculatorModel.calculate(OPERANDS_1[i],OPERANDS_2[i], '-'));
        }
    }

    /**
     * Tests CalculatorModel's calculate(int operand1, int operand2, char operator) with various operands to be multiplied.
     */
    @Test
    public void testCalculateMultiply() {
        for (int i = 0; i < OPERANDS_1.length; i ++) {
            Assert.assertEquals(CALCULATE_MULTIPLY_RESULTS[i],CalculatorModel.calculate(OPERANDS_1[i],OPERANDS_2[i], '*'));
        }
    }

    /**
     * Tests CalculatorModel's calculate(int operand1, int operand2, char operator) with various operands to be divided.
     */
    @Test
    public void testCalculateDivide() {
        for (int i = 0; i < OPERANDS_1.length - 1; i ++) {
                Assert.assertEquals(CALCULATE_DIVIDE_RESULTS[i],CalculatorModel.calculate(OPERANDS_1[i],OPERANDS_2[i], '/'));
        }
    }

    /**
     * Tests CalculatorModel's calculate(int operand1, int operand2, char operator) when trying to divide by 0.
     */
    @Test
    public void testCalculateDivideThrowsIllegalArgumentException() {
        try{
            CalculatorModel.calculate(OPERANDS_1[OPERANDS_1.length - 1], OPERANDS_2[OPERANDS_2.length - 1], '/');
            fail("Cannot divide by zero!");
        }catch (IllegalArgumentException e) {
            // PASSES :D:D:D:
        }
    }

    /**
     * Unit tests used to see if an empty infix expression is evaluated to 0.
     */
    @Test
    public void testEmptyExpression() {
        Assert.assertEquals("Empty expressions should evaluate to 0!",
                this.calculator.evaluate(CalculatorModelTest.INFIX_BOUNDARY_CASES[0]),
                CalculatorModelTest.INFIX_BOUNDARY_CASE_RESULTS[0]);
    }

    /**
     * Unit test used to determine if an IllegalArgumentException is thrown for double operators
     */
    @Test
    public void testDoubleOperator() {
        try {
            this.calculator.evaluate(CalculatorModelTest.INFIX_BOUNDARY_CASES[1]);
            fail("Infix expressions with two consecutive operators should result in an IllegalArgumentException.");
        }catch(IllegalArgumentException exception) {
            // TEST PASSES :D:D:D:D:D:D
        }
    }

    /**
     * Unit test used to determine if the power rule is being computed correctly by the model.
     */
    @Test
    public void testDerivative(){
        for (int i = 0; i < DERIVATIVE_STRINGS.length; i ++){
            Assert.assertEquals(DERIVATIVE_EXPECTED[i],calculator.derivative(DERIVATIVE_STRINGS[i]));
        }
    }

    /**
     * Unit test for derivative exceptions for multiplication
     */
    @Test
    public void testDerivativeExceptionMultiplication(){
        try{
            calculator.derivative("15x^2 * 17x");
            fail("Derivative with multiplication sign should throw Unsupported Operation Exception");
        }catch (UnsupportedOperationException e){
            //Passed
        }
    }

    /**
     * Unit test for derivative exceptions for division
     */
    @Test
    public void testDerivativeExceptionDivision(){
        try{
            calculator.derivative("-42x^3 / 9x^7");
            fail("Derivative with multiplication sign should throw Unsupported Operation Exception");
        }catch (UnsupportedOperationException e){
            //Passed
        }
    }
}


