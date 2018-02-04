import java.util.*;

public class BattleShipsGame {
    // Globals needed in too many spots to pass around
    // Current number of player and computer ships
    private static int numPlayerShips = 0;
    private static int numComputerShips = 0;

    public static void main(String[] args) {
        // Create input scanner and pass to needed methods
        Scanner input = new Scanner(System.in);
        char[][] ocean =  new char[10][10];
        makeEmptyOcean(ocean);
        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, the sea is empty.");
        printOcean(ocean, false);
        placeUserShips(input, ocean);
        placeComputerShips(ocean);
        printOcean(ocean, false);
        commenceBattle(input, ocean);
        showOutcome();
        // Close scanner
        input.close();
    }

    /**
     * Fill 2d array with the space character.
     * @param ocean 2d array to be filled
     */
    private static void makeEmptyOcean(char[][] ocean) {
        for (char[] row : ocean) {
            Arrays.fill(row, ' ');
        }
    }

    /**
     * Outputs a graphical representation of the ocean.
     * ' ' is an empty space
     * '@' is the location of a player ship, 'x' is the location of a sunken player ship
     * 'C' is the location of a computer ship, '!' is the location of a sunken computer ship
     * '-' is the location of a player miss, '|' is the location of computer miss
     * '+' is the location of both player and computer having missed
     * @param ocean the 2d array representing the ocean
     * @param showComputer false to show all or true to hide computer moves, useful for debugging
     */
    private static void printOcean(char[][] ocean, boolean showComputer) {
        System.out.println("");
        System.out.println("   0123456789");
        // Loop through array printing characters
        for (int i = 0; i < ocean.length; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < ocean[i].length; j++) {
                // If showComputer is true, print all characters
                if (showComputer) System.out.print(ocean[i][j]);
                // If location contains computer ship or computer shot, print a space
                else if (ocean[i][j] == 'C' || ocean[i][j] == '|') System.out.print(' ');
                // If both player and computer have missed at that location, print - indicating player missed
                else if (ocean[i][j] == '+') System.out.print('-');
                // Print all other chars which include ' ', '@', '!' and 'x'
                else System.out.print(ocean[i][j]);
            }
            System.out.println("| " + i);
        }
        System.out.println("   0123456789");
        System.out.println("");
    }

    /**
     * Allow user to place their ships
     * @param input A scanner to get user input
     * @param ocean 3d array representing the ocean
     */
    private static void placeUserShips(Scanner input, char[][] ocean) {
        // Setup array to print first, second, etc
        String[] textNum = {"first", "second", "third", "fourth", "fifth"};
        // Player gets to place 5 ships
        while (numPlayerShips < 5) {
            // Get x and y coordinates from player
            System.out.print("Enter X coordinate for your " + textNum[numPlayerShips] + " ship: ");
            int x = input.nextInt();
            System.out.print("Enter Y coordinate for your " + textNum[numPlayerShips] + " ship: ");
            int y = input.nextInt();
            // Test to make sure they are inside the ocean
            if (x < 0 || x > 9 || y < 0 || y > 9) {
                System.out.println("Invalid Location! Outside Grid!");
            } else if (ocean[x][y] == ' ') { // Test it is an empty spot
                ocean[x][y] = '@';
                numPlayerShips++;
            } else {
                System.out.println("Invalid Location! You already have a ship there!");
            }
        }
    }

    /**
     * Randomly place computer ships
     * @param ocean 2d array representing ocean
     */
    private static void placeComputerShips(char[][] ocean) {
        Random rand = new Random();
        System.out.println("Computer is deploying ships");
        while (numComputerShips < 5) {
            // Limit to bounds of ocean
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            // Test it is an empty spot
            if (ocean[x][y] == ' ') {
                ocean[x][y] = 'C';
                numComputerShips++;
                System.out.println(numComputerShips + ". ship DEPLOYED");
            }
        }
    }

    /**
     * Allow player and computer to take turns while both still have at least one ship
     * @param input Scanner to accept user input
     * @param ocean 2d array representing ocean
     */
    private static void commenceBattle(Scanner input, char[][] ocean) {
        while (numComputerShips > 0 && numPlayerShips > 0) {
            printShipsLeft();
            takePlayerTurn(input, ocean);
            takeComputerTurn(ocean);
            printOcean(ocean, false);
        }
    }

    /**
     * Display ships left
     */
    private static void printShipsLeft() {
        System.out.println("Your ships left: " + numPlayerShips + " | Computer ships left: " + numComputerShips);
        System.out.println("------------------------------------------");
    }

    /**
     * Allow player to take a shot
     * @param input Scanner to accept user input
     * @param ocean 2d array representing ocean
     */
    private static void takePlayerTurn(Scanner input, char[][] ocean) {
        System.out.println("YOUR TURN");
        int x = -1;
        int y = -1;
        boolean validCoords = false;
        while (!validCoords) {
            // Get x and y coordinates
            System.out.print("Enter X coordinate: ");
            x = input.nextInt();
            System.out.print("Enter Y coordinate: ");
            y = input.nextInt();
            // Test they are within ocean
            if (x < 0 || x > 9 || y < 0 || y > 9) System.out.println("Invalid Location! Outside Grid!");
            // Test if they already shot there
            else if (ocean[x][y] == '-' || ocean[x][y] == '+') System.out.println("You already tried that!");
            // Test if ship already sunk there
            else if (ocean[x][y] == 'x' || ocean[x][y] == '!') System.out.println("That ship is already sunk!");
            else validCoords = true;
        }
        // Test what was hit if anything
        if (ocean[x][y] == 'C') {
            ocean[x][y] = '!';
            numComputerShips--;
            System.out.println("Boom! You sunk the ship!");
        } else if (ocean[x][y] == '@') {
            ocean[x][y] = 'x';
            numPlayerShips--;
            System.out.println("Oh no, you sunk your own ship :(");
        } else if (ocean[x][y] == ' '){
            ocean[x][y] = '-';
            System.out.println("Sorry, you missed");
        } else {
            ocean[x][y] = '+';
            System.out.println("Sorry, you missed");
        }
    }

    /**
     * Allow computer to take a shot
     * @param ocean 2d array of ocean
     */
    private static void takeComputerTurn(char[][] ocean) {
        System.out.println("COMPUTER'S TURN");
        Random rand = new Random();
        int x = -1;
        int y = -1;
        boolean validCoords = false;
        while (!validCoords) {
            // Limit to bounds of array
            x = rand.nextInt(10);
            y = rand.nextInt(10);
            // If not a sunk ship or shot location it is a valid choice
            if (ocean[x][y] != 'x' && ocean[x][y] != '|' && ocean[x][y] != '+' && ocean[x][y] != '!') {
                validCoords = true;
            }
        }
        // Test what was hit if anything
        if (ocean[x][y] == '@') {
            ocean[x][y] = 'x';
            numPlayerShips--;
            System.out.println("The Computer sunk one of your ships!");
        } else if (ocean[x][y] == 'C') {
            ocean[x][y] = '!';
            numComputerShips--;
            System.out.println("The Computer sunk one of its own ships");
        } else if (ocean[x][y] == '-') {
            ocean[x][y] = '+';
            System.out.println("Computer missed");
        } else {
            ocean[x][y] = '|';
            System.out.println("Computer missed");
        }
    }

    /**
     * Show outcome of game
     */
    private static void showOutcome() {
        printShipsLeft();
        if (numComputerShips == 0) System.out.println("Hooray! You won the battle! :)");
        else System.out.println("Sorry, You lose!");
    }

}
