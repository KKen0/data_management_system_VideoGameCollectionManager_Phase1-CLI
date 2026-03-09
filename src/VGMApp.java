/**
 * Ken Amin
 * CEN 3024C - Software Development I
 * Date: March 8, 2026
 * Class: VGMApp
 *
 * This class contains the main method and acts as the user interface for the
 * Video Game Manager application. It displays the menu, processes user input,
 * and interacts with the GameManager class to perform operations such as
 * adding, removing, updating, and viewing games.
 */


import java.util.*;
import java.io.File;

public class VGMApp {

    private GameManager manager;
    private Scanner scanner;

    /**
     * Constructor: VGMApp
     * Parameters: none
     * Return: none
     * Purpose: Initializes the scanner and asks the user for the text file name.
     */
    public VGMApp() {
        scanner = new Scanner(System.in);

        String fileName = requestFileName();

        manager = new GameManager(fileName);
    }

    /**
     * Method: requestFileName
     * Parameters: none
     * Return: String
     * Purpose: Prompts the user to enter a valid .txt file name for storing data.
     */
    private String requestFileName() {

        while(true) {

            System.out.print("Enter data file name (.txt only): ");
            String fileName = scanner.nextLine();

            // Check if file has .txt extension
            if(!fileName.toLowerCase().endsWith(".txt")) {
                System.out.println("Only .txt files are allowed.");
                continue;
            }

            // Check if file actually exists
            File file = new File(fileName);

            if(!file.exists()) {
                System.out.println("File does not exist. Please try again.");
                continue;
            }

            return fileName;
        }
    }

    /**
     * Method: run
     * Parameters: none
     * Return: boolean
     * Purpose: Controls the main program loop and processes user menu selections.
     */
    public boolean run() {

        boolean running = true;

        while(running) {

            displayMenu();

            int choice = readInt("Choose option: ");

            switch(choice) {

                case 1:
                    displayGames();
                    break;

                case 2:
                    addGame();
                    break;

                case 3:
                    removeGame();
                    break;

                case 4:
                    updateGame();
                    break;

                case 5:
                    backlog();
                    break;

                case 6:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        return true;
    }

    /**
     * Method: displayMenu
     * Parameters: none
     * Return: boolean
     * Purpose: Displays the application menu options.
     */
    private boolean displayMenu() {

        System.out.println("\n===== Video Game Manager =====");
        System.out.println("1. Display Games");
        System.out.println("2. Add Game");
        System.out.println("3. Remove Game");
        System.out.println("4. Update Game Hours");
        System.out.println("5. Backlog Report");
        System.out.println("6. Exit");

        return true;
    }

    /**
     * Method: displayGames
     * Parameters: none
     * Return: boolean
     * Purpose: Displays all games currently stored in the system.
     */
    private boolean displayGames() {

        List<Game> games = manager.getAllGames();

        if(games.isEmpty()) {
            System.out.println("No games found.");
            return true;
        }

        for(Game g : games)
            System.out.println(g);

        return true;
    }

    /**
     * Method: addGame
     * Parameters: none
     * Return: boolean
     * Purpose: Prompts the user for game details and adds a new game.
     */
    private boolean addGame() {

        int id = readInt("Game ID: ");
        String title = readString("Title: ");
        String platform = readString("Platform: ");
        String genre = readString("Genre: ");
        double price = readDouble("Purchase Price: ");
        double hours = readDouble("Hours Played: ");
        boolean completed = readBoolean("Completed (true/false): ");
        int year = readInt("Release Year: ");

        Game game = new Game(id,title,platform,genre,price,hours,completed,year);

        if(manager.addGame(game))
            System.out.println("Game added.");
        else
            System.out.println("Duplicate ID.");

        return true;
    }

    /**
     * Method: removeGame
     * Parameters: none
     * Return: boolean
     * Purpose: Removes a game based on the ID entered by the user.
     */
    private boolean removeGame() {

        int id = readInt("Enter Game ID to remove: ");

        if(manager.removeGame(id))
            System.out.println("Game removed.");
        else
            System.out.println("Game not found.");

        return true;
    }

    /**
     * Method: updateGame
     * Parameters: none
     * Return: boolean
     * Purpose: Updates the number of hours played for a specific game.
     */
    private boolean updateGame() {

        int id = readInt("Enter Game ID: ");

        double hours = readDouble("New hours played: ");

        if(manager.updateGameHours(id, hours))
            System.out.println("Game updated.");
        else
            System.out.println("Update failed.");

        return true;
    }

    /**
     * Method: backlog
     * Parameters: none
     * Return: boolean
     * Purpose: Displays all games that have not yet been completed.
     */
    private boolean backlog() {

        List<Game> backlog = manager.backlogReport();

        if(backlog.isEmpty()) {
            System.out.println("No backlog games.");
            return true;
        }

        for(Game g : backlog)
            System.out.println(g);

        return true;
    }

    private int readInt(String msg) {

        while(true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(scanner.nextLine());
            }
            catch(Exception e) {
                System.out.println("Enter valid integer.");
            }
        }
    }

    private double readDouble(String msg) {

        while(true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(scanner.nextLine());
            }
            catch(Exception e) {
                System.out.println("Enter valid number.");
            }
        }
    }

    private String readString(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private boolean readBoolean(String msg) {

        while(true) {

            System.out.print(msg);
            String input = scanner.nextLine();

            if(input.equalsIgnoreCase("true") ||
                    input.equalsIgnoreCase("false"))
                return Boolean.parseBoolean(input);

            System.out.println("Enter true or false.");
        }
    }

    public static void main(String[] args) {

        VGMApp app = new VGMApp();
        app.run();
    }
}