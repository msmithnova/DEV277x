import java.util.*;

/**
 * Performs operations on fractions
 */
public class FractionCalculator {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        intro();
        String operation = "";
        while (!operation.equals("q") && !operation.equals("Q")) {
            printSeparator();
            operation = getOperation(input);
            // If operation "q" or "Q" hen quit
            if (!operation.toUpperCase().equals("Q")) {
                Fraction frac1 = getFraction(input);
                Fraction frac2 = getFraction(input);
                // Cannot divide by zero, print equals comparison or perform operation
                if (operation.equals("/") && frac2.getNumerator() == 0) {
                    System.out.println(frac1.toString() + " " + operation + " " + frac2.toString() + " = Undefined");
                } else if (operation.equals("=")) {
                    System.out.println(frac1.toString() + " " + operation + " " + frac2.toString() + " is " + frac1.equals(frac2));
                } else {
                    Fraction result = performCalc(operation, frac1, frac2);
                    System.out.println(frac1.toString() + " " + operation + " " + frac2.toString() + " = " + result.toString());
                }
            }
        }
        input.close();
    }

    /**
     * Print out intro message.
     */
    private static void intro() {
        System.out.println("This program is a fraction calculator");
        System.out.println("It will add, subtract, multiply and divide fractions until you type Q to quit.");
        System.out.println("Please enter your fractions in the form a/b, where a and b are integers.");
    }

    /**
     * Print separator line.
     */
    private static void printSeparator() {
        System.out.println("-------------------------------------------------------------------------------");
    }

    /**
     * Get input of operation to be performed.
     * @param input Scanner to receive input.
     * @return Return valid input.
     */
    private static String getOperation(Scanner input) {
        String[] validEntryArray = {"+", "-", "/", "*", "=", "q", "Q"};
        boolean validEntry = false;
        String entry = "";
        System.out.print("Please enter an operation (+, -, /. *, = or Q to quit: ");
        while (!validEntry) {
            entry = input.nextLine();
            if (Arrays.asList(validEntryArray).contains(entry)) validEntry = true;
            else System.out.print("Invalid input (+, -, /. *, = or Q to quit: ");
        }
        return entry;
    }

    /**
     * Check if input matches regex and is thus valid.
     * Starts with optional '-' followed by 1 or more digits, optionally followed by a '/', a digit from 1 to 9
     * and 0 or more other digits, and then ends.
     * @param input String input to check if it is valid
     * @return return true or false
     */
    private static boolean validFraction(String input) {
        return input.matches("^-?[0-9]+(\\/[1-9][0-9]*)?$");
    }

    /**
     * Get a fraction from the user
     * @param input Scanner to accept input
     * @return returns a valid fraction
     */
    private static Fraction getFraction(Scanner input) {
        boolean validEntry = false;
        String entry = "";
        System.out.print("Please enter a fraction (a/b) or integer (a): ");
        while (!validEntry) {
            entry = input.nextLine();
            if (validFraction(entry)) validEntry = true;
            else System.out.print("Invalid fraction. Please enter (a/b) or (a), where a and b are integers and b is not zero: ");
        }
        // If whole number return that as a Fraction or split entry to create Fraction
        if (entry.matches("^-?[0-9]+$")) return new Fraction(Integer.parseInt(entry));
        else {
            String[] splitEntry = entry.split("/");
            return new Fraction(Integer.parseInt(splitEntry[0]), Integer.parseInt(splitEntry[1]));
        }
    }

    /**
     * Perform +, -, / or * operation on fractions
     * @param operation String representing operation to be performed
     * @param frac1 Fraction to perform operation on
     * @param frac2 Second Fraction to use in calculation
     * @return Return result as Fraction
     */
    private static Fraction performCalc(String operation, Fraction frac1, Fraction frac2) {
        Fraction result = new Fraction();
        switch (operation) {
            case "+":
                result = frac1.add(frac2);
                break;
            case "-":
                result = frac1.subtract(frac2);
                break;
            case "*":
                result = frac1.multiply(frac2);
                break;
            case "/":
                result = frac1.divide(frac2);
                break;
        }
        return result;
    }

}
