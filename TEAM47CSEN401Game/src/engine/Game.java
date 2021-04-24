package engine;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
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
    
    public void loadArmy(String cityName, String path) throws IOException{
    	
    }
    
    //Taken from the how to read CSV PDF in CMS
    //Function doesn't work for some reason???
    //On my eclipse it states that it cannot find the distances.csv file
    //CSV is not my favorite file format
    private void loadCitiesAndDistances() throws IOException{
    	String currentLine = "";
    	FileReader fileReader = new FileReader("distances.csv");
    	BufferedReader br = new BufferedReader(new FileReader("distances.csv"));
    	while((currentLine = br.readLine()) != null) {
    	}
    	br.close();
    }

    public Game(String playerName,String playerCity) throws IOException {
        // Initialize Variables
        this.availableCities = new ArrayList<City>();
        this.distances = new ArrayList<Distance>();
        this.maxTurnCount = 30;
        this.currentTurnCount = 1;
        this.player = new Player(playerName);
        // Load Cities
        loadCitiesAndDistances();
    }
   
    
    
}
