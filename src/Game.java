/**
 * Ken Amin
 * CEN 3024C - Software Development I
 * Date: March 8, 2026
 * Class: Game
 *
 * This class represents a video game object within the Video Game Manager
 * application. It stores information about a single game including its ID,
 * title, platform, genre, purchase price, hours played, completion status,
 * and release year. The class also provides methods to update game progress
 * and convert game data into a format suitable for saving to a text file.
 */


public class Game {

    private int gameId;
    private String title;
    private String platform;
    private String genre;
    private double purchasePrice;
    private double hoursPlayed;
    private boolean completed;
    private int releaseYear;

    /**
     * Constructor: Game
     * Purpose: Initializes a Game object with all required attributes.
     * Parameters: gameId, title, platform, genre, purchasePrice,
     *             hoursPlayed, completed, releaseYear
     * Return: none
     */
    public Game(int gameId, String title, String platform, String genre,
                double purchasePrice, double hoursPlayed,
                boolean completed, int releaseYear) {

        //Assign values to the instance variables
        this.gameId = gameId;
        this.title = title;
        this.platform = platform;
        this.genre = genre;
        this.purchasePrice = purchasePrice;
        this.hoursPlayed = hoursPlayed;
        this.completed = completed;
        this.releaseYear = releaseYear;
    }

    // Getter methods return stored game information
    public int getGameId() { return gameId; }
    public String getTitle() { return title; }
    public String getPlatform() { return platform; }
    public String getGenre() { return genre; }
    public double getPurchasePrice() { return purchasePrice; }
    public double getHoursPlayed() { return hoursPlayed; }
    public boolean isCompleted() { return completed; }
    public int getReleaseYear() { return releaseYear; }

    /**
     * Method: updateHours
     * Purpose: Updates the number of hours played for the game.
     * Parameter: newHours
     * Return: boolean (true if update is successful)
     */
    public boolean updateHours(double newHours) {
        // Prevent negative values
        if(newHours < 0) return false;
        //Update hours played
        this.hoursPlayed = newHours;
        return true;
    }

    /**
     * Method: updateCompleted
     * Purpose: Updates the completion status of the game.
     * Parameter: status
     * Return: boolean
     */
    public boolean updateCompleted(boolean status) {
        this.completed = status;
        return true;
    }

    /**
     * Method: toFileString
     * Purpose: Converts game data into a comma-separated format for saving to file.
     * Return: String
     */
    public String toFileString() {
        // Create CSV format for file storage
        return gameId + "," + title + "," + platform + "," + genre + ","
                + purchasePrice + "," + hoursPlayed + ","
                + completed + "," + releaseYear;
    }

    /**
     * Method: toString
     * Purpose: Formats the game information for display in the console.
     * Return: String
     */
    public String toString() {
        // Return readable game info
        return "ID: " + gameId +
                " | Title: " + title +
                " | Platform: " + platform +
                " | Genre: " + genre +
                " | Price: $" + purchasePrice +
                " | Hours: " + hoursPlayed +
                " | Completed: " + completed +
                " | Year: " + releaseYear;
    }
}
