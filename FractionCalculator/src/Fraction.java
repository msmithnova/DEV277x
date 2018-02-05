/**
 * A class to create and work with fractions.
 */
public class Fraction {
    private int numerator;
    private int denominator;

    /**
     * Constructor where the client provides both numerator and denominator.
     * @param numerator An integer representing the numerator of the fraction.
     * @param denominator An integer representing the denominator of the fraction.
     */
    public Fraction(int numerator, int denominator) {
        if (denominator == 0) throw new IllegalArgumentException();
        if (denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Constructor where the client only provides the numerator.
     * The denominator is set to 1. ie. Whole number
     * @param numerator
     */
    public Fraction(int numerator) {
        this(numerator, 1);
    }

    /**
     * Constructor that sets numerator to 0 and denominator to 1. ie: 0
     */
    public Fraction() {
        this(0, 1);
    }

    /**
     * Returns the numerator.
     * @return Returns the numerator.
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * Returns the denominator.
     * @return Returns the denominator.
     */
    public int getDenominator() {
        return denominator;
    }


    /**
     * Returns the string "numerator/denominator"
     */
    @Override
    public String toString() {
        if (denominator == 1) {
            return "" + numerator;
        } else if (numerator == 0) {
            return "0";
        } else {
            return numerator + "/" + denominator;
        }
    }

    /**
     * Returns the fraction as a double.
     * @return Returns the fraction as a double.
     */
    public double toDouble() {
        return numerator / (1.0 * denominator);
    }

    /**
     * Adds provided fraction to this.
     * @param other Fraction to add to this.
     * @return Returns result in lowest terms.
     */
    public Fraction add(Fraction other) {
        int resultNumerator = numerator * other.denominator + other.numerator * denominator;
        int resultDenominator = denominator * other.denominator;
        Fraction result = new Fraction(resultNumerator, resultDenominator);
        result.toLowestTerms();
        return result;
    }

    /**
     * Subtracts provided fraction from this.
     * @param other Fraction to subtract from this.
     * @return Returns result in lowest terms.
     */
    public Fraction subtract(Fraction other) {
        int resultNumerator = numerator * other.denominator - other.numerator * denominator;
        int resultDenominator = denominator * other.denominator;
        Fraction result = new Fraction(resultNumerator, resultDenominator);
        result.toLowestTerms();
        return result;
    }

    /**
     * Multiplies provided fraction to this.
     * @param other Fraction to multiply to this.
     * @return Returns result in lowest terms.
     */
    public Fraction multiply(Fraction other) {
        int resultNumerator = numerator * other.numerator;
        int resultDenominator = denominator * other.denominator;
        Fraction result = new Fraction(resultNumerator, resultDenominator);
        result.toLowestTerms();
        return result;
    }

    /**
     * Divides this by provided fraction.
     * @param other Fraction to divide this by.
     * @return Returns result in lowest terms.
     */
    public Fraction divide(Fraction other) {
        if (other.numerator == 0) throw new IllegalArgumentException();
        int resultNumerator = numerator * other.denominator;
        int resultDenominator = denominator * other.numerator;
        Fraction result = new Fraction(resultNumerator, resultDenominator);
        result.toLowestTerms();
        return result;
    }

    /**
     * Returns if this and provided fractions are equal.
     * @param other Fraction to check if equal to this.
     * @return Returns true or false.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Fraction) {
            Fraction o = (Fraction) other;
            this.toLowestTerms();
            o.toLowestTerms();
            return numerator == o.numerator && denominator == o.denominator;
        }
        return false;
    }

    /**
     * Reduces fraction to lowest terms.
     */
    public void toLowestTerms() {
        int gcd = gcd(numerator, denominator);
        if (gcd != 0) {
            numerator = numerator / gcd;
            denominator = denominator / gcd;
        }
    }

    /**
     * Calculates the greatest common denominator.
     * @param num Client provided numerator.
     * @param den Client provided denominator.
     * @return Returns gcd.
     */
    public static int gcd(int num, int den) {
        while (num != 0 && den != 0) {
            int temp = num % den;
            num = den;
            den = temp;
        }
        return num;
    }
}
