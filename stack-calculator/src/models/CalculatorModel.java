package models;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * CalculatorModel.java : Concrete class using the stack data structure to evaluate infix math expressions.
 *
 * @author Ryan Tucker, Noah Teshima
 * @version 1.0
 */
public class CalculatorModel implements CalculatorInterface {
    private static final String OPERATORS = "-+*/()";
    private static final int[] PRECEDENCE = {1, 1, 2, 2, -1, -1};
    private StackInterface<Character> operators;
    private StackInterface<Integer> operands;


    /**
     * Default Constructor.
     */
    public CalculatorModel() {
        operators = new LinkedStack<>();
        operands = new LinkedStack<>();
    }

    @Override
    public String evaluate(String expression) throws IllegalArgumentException {
        try{
            return this.convertPostfix(expression);
        }catch (EmptyStackException e){
            throw new IllegalArgumentException();
        }
    }

    /**
     * Helper method designed to convert the given infix expression
     * to postfix.
     * @param infixExpression String reference containing the infix expression to
     *                        convert.
     * @return String object containing the postfix expression.
     * @throws EmptyStackException if the given infix expression has invalid syntax.
     * This is thrown when either the infixExpression is empty, there are double operators, or
     * invalid characters are given.
     */
    private String convertPostfix(String infixExpression) throws EmptyStackException {
        // Since Strings are immutable, we can work directly with the given reference.
        infixExpression = infixExpression.replaceAll(" ", "");
        // Keep track of the current integer being added
        String currentInteger = "";
        // Keep track of current index in expression
        int index = 0;
        // While there are still parts of the expression to evaluate
        while(index < infixExpression.length()) {
            char currentChar = infixExpression.charAt(index);
            // Check whether the next character is an operator.
            boolean isOperator = isOperator(currentChar);

            // If we come across an operator, we need to add the current integer to the expression
            // and evaluate the order of precedence of the operator.
            if(isOperator) {
                // Add the operand passed over onto the stack
                if(currentInteger.length() > 0) {
                    this.operands.push(new Integer(currentInteger));
                    currentInteger = "";
                }

                // Process the current operator
                this.processOperator(currentChar);
            }else {
                // Append to the current integer
                currentInteger += currentChar;
            }
            index++;
        }

        // Add the final operand
        if (currentInteger.length() != 0){
            this.operands.push(new Integer(currentInteger));
        }

        while(!this.operators.empty()) {
            int operandTwo = this.operands.pop();
            int operandOne = this.operands.pop();
            int result = this.calculate(operandOne, operandTwo, this.operators.pop());
            this.operands.push(result);
        }

        return (""+(this.operands.empty() ? 0 : this.operands.pop()));
    }

    @Override
    public String derivative(String expression) throws NumberFormatException, UnsupportedOperationException{
        StringBuilder sb = new StringBuilder();
        String[] stringTerms = expression.split(" ");
        String currentComponent;
        String termString;
        Term currentTerm;

        for (int index = 0; index < stringTerms.length; index++) {
            currentComponent = stringTerms[index];

            // If the current component is an operator, append it to the result.
            // Otherwise do power rule
            if (currentComponent.length() == 1 && this.isOperator(currentComponent.charAt(0))) {
                if (currentComponent.charAt(0) == '/' || currentComponent.charAt(0) == '*'){
                    throw new UnsupportedOperationException("Can't take derivative of " + currentComponent.charAt(0) + " operator.");
                }
                sb.append(currentComponent);
            } else {
                currentTerm = new Term(currentComponent);
                currentTerm.powerRule();
                termString = currentTerm.toString();
                termString = (termString.length() > 1) ? termString.substring(1) : termString;

                sb.append(termString);
            }
        }

        return sb.toString();
    }

    /**
     * Adds operator to operators based on precedence. Will compute operator on top of stack with top two operands if new operator to be added is
     * of lesser precedence.
     *
     * @param operator char representing operator to be added to stack
     */
    private void processOperator(char operator) throws EmptyStackException {
        int operand1, operand2;
        if (operators.empty() || operator == '(') {
            operators.push(operator);
        }else{
            char top = operators.peek();
            if (precedence(operator) > precedence(top)) {
                operators.push(operator);
            }else{
            while(!operators.empty() && precedence(operator) <= precedence(top)) {
                    operators.pop();

                    if (top == '(') { //Reached bounding point for current parentheses block
                        break;
                    }

                    operand2 = operands.pop();
                    operand1 = operands.pop();
                    operands.push(calculate(operand1,operand2,top));

                    if(!operators.empty()) {
                        top = operators.peek();
                    }
                }
                if (operator != ')') {
                    operators.push(operator);
                }
            }
        }
    }

    /**
     * Helper method for doing simple operations
     *
     * @param operand1 int representing operand lower in the operands stack
     * @param operand2 int representing operand higher in the operands stack
     * @param operator char representing operator from the operators stack
     *
     * @return int representing result of operation
     * @throws IllegalArgumentException Thrown when attempting to divide by 0
     * @throws UnsupportedOperationException Thrown when char operator passed in not a supported operator
     */
    protected static int calculate(int operand1, int operand2, char operator)
            throws IllegalArgumentException, UnsupportedOperationException {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new IllegalArgumentException("Cannot Divide by 0");
                }
                return operand1 / operand2;
            default:
                throw new UnsupportedOperationException();
        }
    }

    /**
     * Checks if character passed to method is an operator
     *
     * @param operator char to be checked if it is an acceptable operator
     * @return boolean true if operator, false if not
     */
    private boolean isOperator(char operator) {
        return OPERATORS.indexOf(operator) != -1;
    }

    /**
     * Returns int value representing order of operations for operators.
     * Larger int values have higher precedence.
     *
     * @param operator char representing an operator whose precedence will be evaluated.
     * @return int value representing precedence of given operator.
     */
    private int precedence(char operator) {
        return PRECEDENCE[OPERATORS.indexOf(operator)];
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Operators Stack: ");
        sb.append(operators.toString());
        sb.append("\n");
        sb.append("Operands Stack: ");
        sb.append(operands.toString());

        return sb.toString();
    }
}


