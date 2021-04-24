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
    
    
    //no clue what this actually does, the description is very annoyingly vague
    public void loadArmy(String cityName, String path) throws IOException{
    	
    }
    
    
    // there are 2 distances.csv in the folder structure because eclipse wont make this function work without it... so just leave them as is
    //CSV is not my favorite file format
    private void loadCitiesAndDistances() throws IOException{
    	String currentLine = "";
    	FileReader fileReader = new FileReader("distances.csv");
    	BufferedReader br = new BufferedReader(fileReader);
    	while((currentLine = br.readLine()) != null) {
    		//Splits every line in the CSV into a string array of size 3, string[0] is cityname1, string[1] is cityname2 and string[2] is the distance between them
    		//makes a distance object and adds it to the ArrayList, also adds the cities to the availableCities ArrayList if they are not found within it already
    		String lineDistanceData[] = currentLine.split(",");
    		Distance currentLineDistance = new Distance(lineDistanceData[0],lineDistanceData[1],Integer.parseInt(lineDistanceData[2]));
    		distances.add(currentLineDistance);
    		
    		City first = new City(lineDistanceData[0]);
    		City second  = new City(lineDistanceData[1]);
    		if(!availableCities.contains(first)) {
    			availableCities.add(first);
    		}
    		if(!availableCities.contains(second)) {
    			availableCities.add(second);
    		}	
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
        
        // Load Cities And Distances
        loadCitiesAndDistances();
    }    
    
}
