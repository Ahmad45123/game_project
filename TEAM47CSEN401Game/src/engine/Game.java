package engine;

import java.io.IOException;
import java.util.ArrayList;

public class Game {
    Player player;
    ArrayList<City> availableCities;
    ArrayList<Distance> distances;
    int maxTurnCount;
    int currentTurnCount;

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public ArrayList<City> getAvailableCities() {
        return availableCities;
    }
    public ArrayList<Distance> getDistances() {
        return distances;
    }
    public int getMaxTurnCount() {
        return maxTurnCount;
    }
    public int getCurrentTurnCount() {
        return currentTurnCount;
    }
    public void setCurrentTurnCount(int currentTurnCount) {
        this.currentTurnCount = currentTurnCount;
    }

    public Game(String playerName,String playerCity) throws IOException {
        // Initialize Variables
        this.availableCities = new ArrayList<City>();
        this.distances = new ArrayList<Distance>();
        this.maxTurnCount = 30;
        this.currentTurnCount = 1;
        this.player = new Player(playerName);
        
        // Load Cities
    }
}
