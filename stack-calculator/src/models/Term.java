/**
 * Term.java: Term to be used as a node for a linked list representing a polynomial (Implemented in Polynomial.java)
 * A term consists of an exponent and a coefficient.
 *
 * @author  Ryan Tucker
 * @version 1.0
 */

package models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Term implements Comparable<Term> {
    private static final Pattern POLY_REGEX = Pattern.compile("([-+])?(\\d+)?(x)?(\\^)?(-)?(\\d+)?");
    private int exponent;
    private int coefficient;

    /**
     * Default Constructor. Both instance variables set to 1.
     */
    public Term() {
        setAll(1, 1);
    }

    /**
     * Full Constructor.
     *
     * @param coefficient integer representing coefficient of term. Can be any real number.
     * @param exponent integer representing exponent of term. Can be any real number.
     */
    public Term(int coefficient, int exponent) {
        setAll(coefficient, exponent);
    }

    /**
     * Constructor based on a String. String parsed using regular expression. Pattern
     * located in POLY_REGEX constant variable.
     *
     * @param term String containing a term.
     */
    public Term(String term) {
        Matcher polyMatcher = POLY_REGEX.matcher(term);
        int coefficient = 1;
        int exponent = 1;

        if (polyMatcher.find())
        {
            //Checking signs of coefficient(1) and exponent(5)
            if (polyMatcher.group(1) != null && polyMatcher.group(1).charAt(0) == '-') {
                coefficient *= -1;
            }
            if (polyMatcher.group(5) != null){
                exponent *= -1;
            }
            //Checking value of coefficient(2)
            if (polyMatcher.group(2) != null) {
                coefficient *= Integer.parseInt(polyMatcher.group(2));
            }
            //Checking presence of variable(3), carrot(4), and value of exponent(6)
            if (polyMatcher.group(3) != null) {
                if (polyMatcher.group(4) != null) {
                    if (polyMatcher.group(6) != null) {
                        exponent *= Integer.parseInt(polyMatcher.group(6));
                    }
                }
            }else{//Variable not found, assume exponent is 0
                exponent = 0;
            }
            setAll(coefficient,exponent);
        }
    }

    /**
     * Copy Constructor. Performs a deep copy.
     *
     * @param other original Term to be copied.
     */
    public Term(Term other) {
        if (other != null) {
            setAll(other.getCoefficient(), other.getExponent());
        }
    }

    @Override
    public Term clone() {
        return new Term(this);
    }

    /**
     * Compares two Terms based off of their exponent values.
     *
     * @param other Term to be compared to
     *
     * @return integer value of difference between exponent values. (Relative to calling object's exponent)
     */
    public int compareTo(Term other) {
        return this.getExponent() - other.getExponent();
    }

    /**
     * Sets all instance variables.
     *
     * @param coefficient integer representing coefficient. Can be any real number.
     * @param exponent integer representing exponent. Can be any real number.
     */
    public void setAll(int coefficient, int exponent) {
        setCoefficient(coefficient);
        setExponent(exponent);
    }

    /**
     * Accessor for exponent instance variable.
     *
     * @return integer representing exponent of Term object.
     */
    public int getExponent() {
        return exponent;
    }

    /**
     * Mutator for exponent instance variable.
     *
     * @param exponent integer representing new exponent value.
     */
    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    /**
     * Accessor for coefficient instance variable.
     *
     * @return integer representing coefficient of Term object.
     */
    public int getCoefficient() {
        return coefficient;
    }

    /**
     * Mutator for coefficient instance variable.
     *
     * @param coefficient integer representing new coefficient value.
     */
    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public boolean equals(Object other) {
        if (other != null && other.getClass() == this.getClass()) {
            Term temp = (Term) other;
            return this.getExponent() == temp.getExponent() &&
                    this.getCoefficient() == temp.getCoefficient();
        }

        return false;
    }


    /**
     * Mutator method designed to compute the power rule on the given term.
     * @return Term object containing the term after computing the power rule.
     */
    public Term powerRule() {
        if (this.getExponent() == 0) {
            this.setAll(0, 1);
        }else {
            this.setCoefficient(this.getCoefficient() * this.getExponent());
            this.setExponent(this.getExponent() - 1);
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int co = coefficient;

        if (coefficient == 0) {
            return "0";
        }

        if (coefficient > 0) {
            sb.append('+');
        } else {
            sb.append('-');
            co = Math.abs(co);
        }
        if (exponent == 0){
            sb.append(co);
            return sb.toString();
        }else if (coefficient != 1 && coefficient != -1){
            sb.append(co);
        }

        sb.append('x');

        if (exponent != 1) {
            sb.append('^');
            sb.append(exponent);
        }

        return sb.toString();
    }
}
