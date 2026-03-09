/**
 * Ken Amin
 * CEN 3024C - Software Development I
 * Date: March 8, 2026
 * Class: GameManager
 *
 * This class manages the collection of Game objects in the Video Game Manager
 * application. It handles loading games from a text file, saving games back to
 * the file, adding new games, removing games, searching for games, and
 * generating reports such as the backlog report.
 */


import java.util.*;
import java.io.*;

public class GameManager {

    // List that stores all game object
    private List<Game> games;
    //Name of the data file
    private String fileName;

    /**
     * Constructor: GameManager
     * Purpose: Initializes the manager and loads games from the file.
     */
    public GameManager(String fileName) {
        this.fileName = fileName;
        games = new ArrayList<>();
        // Load data from files
        loadGames();
    }

    /**
     * Method: loadGames
     * Purpose: Reads the text file and loads game data into memory.
     */
    private boolean loadGames() {

        try {
            File file = new File(fileName);
            if(!file.exists()) return true;

            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()) {

                String line = sc.nextLine();
                String[] p = line.split(",");

                //Create Game object from file data
                Game g = new Game(
                        Integer.parseInt(p[0]),
                        p[1],
                        p[2],
                        p[3],
                        Double.parseDouble(p[4]),
                        Double.parseDouble(p[5]),
                        Boolean.parseBoolean(p[6]),
                        Integer.parseInt(p[7])
                );

                games.add(g);
            }

            sc.close();
            return true;

        } catch(Exception e) {
            System.out.println("Error loading file.");
            return false;
        }
    }

    /**
     * Method: saveGames
     * Purpose: Saves all games to the text file.
     */
    private boolean saveGames() {

        try {
            //Write used to overwrite the file
            PrintWriter writer = new PrintWriter(fileName);

            // Write each game as a line in the file
            for(Game g : games) {
                writer.println(g.toFileString());
            }

            writer.close();
            return true;

        } catch(Exception e) {
            System.out.println("Error saving file.");
            return false;
        }
    }

    /**
     * Method: getAllGames
     * Purpose: Returns the entire game list.
     */
    public List<Game> getAllGames() {
        return games;
    }

    /**
     * Method: addGame
     * Purpose: Adds a new game if ID does not already exist.
     */
    public boolean addGame(Game game) {

        //Check for duplicated ID
        for(Game g : games) {
            if(g.getGameId() == game.getGameId())
                return false;
        }

        games.add(game);
        saveGames();
        return true;
    }

    /**
     * Method: removeGame
     * Purpose: Removes a game based on ID.
     */
    public boolean removeGame(int id) {

        for(Game g : games) {
            if(g.getGameId() == id) {
                games.remove(g);
                saveGames();
                return true;
            }
        }

        return false;
    }

    /**
     * Method: updateGameHours
     * purpose: Updates Game Hours based on ID
     */
    public boolean updateGameHours(int id, double newHours) {
        Game game = findGame(id);
        if(game == null) return false;
        if(!game.updateHours(newHours)) return false;
        saveGames();
        return true;
    }

    /**
     * Method: findGame
     * Purpose: Searches for a game using its ID.
     */
    public Game findGame(int id) {

        for(Game g : games) {
            if(g.getGameId() == id)
                return g;
        }

        return null;
    }

    /**
     * Method: backlogReport
     * Purpose: Returns all games that are not completed.
     */
    public List<Game> backlogReport() {

        List<Game> backlog = new ArrayList<>();

        //Check completion status of each game.
        for(Game g : games) {
            if(!g.isCompleted())
                backlog.add(g);
        }

        return backlog;
    }
}