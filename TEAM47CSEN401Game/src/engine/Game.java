package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import buildings.Farm;
import buildings.Market;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.Unit;

public class Game {
	private Player player;
	private ArrayList<City> availableCities;
	private ArrayList<Distance> distances;
	private final int maxTurnCount = 30;
	private int currentTurnCount;

	public Game(String playerName, String playerCity) throws IOException {

		player = new Player(playerName);
		availableCities = new ArrayList<City>();
		distances = new ArrayList<Distance>();
		currentTurnCount = 1;
		loadCitiesAndDistances();
		for (City c : availableCities) {
			if (c.getName().equals(playerCity))

				player.getControlledCities().add(c);

			else
				loadArmy(c.getName(), c.getName().toLowerCase() + "_army.csv");

		}
	}

	private void loadCitiesAndDistances() throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("distances.csv"));
		String currentLine = br.readLine();
		ArrayList<String> names = new ArrayList<String>();

		while (currentLine != null) {

			String[] content = currentLine.split(",");
			if (!names.contains(content[0])) {
				availableCities.add(new City(content[0]));
				names.add(content[0]);
			} else if (!names.contains(content[1])) {
				availableCities.add(new City(content[1]));
				names.add(content[1]);
			}
			distances.add(new Distance(content[0], content[1], Integer.parseInt(content[2])));
			currentLine = br.readLine();

		}
		br.close();
	}

	public void loadArmy(String cityName, String path) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(path));
		String currentLine = br.readLine();
		Army resultArmy = new Army(cityName);
		while (currentLine != null) {
			String[] content = currentLine.split(",");
			String unitType = content[0].toLowerCase();
			int unitLevel = Integer.parseInt(content[1]);
			Unit u = null;
			if (unitType.equals("archer")) {
				if (unitLevel == 1)
					u = (new Archer(1, 60, 0.4, 0.5, 0.6));

				else if (unitLevel == 2)
					u = (new Archer(2, 60, 0.4, 0.5, 0.6));
				else
					u = (new Archer(3, 70, 0.5, 0.6, 0.7));
			} else if (unitType.equals("infantry")) {
				if (unitLevel == 1)
					u = (new Infantry(1, 50, 0.5, 0.6, 0.7));

				else if (unitLevel == 2)
					u = (new Infantry(2, 50, 0.5, 0.6, 0.7));
				else
					u = (new Infantry(3, 60, 0.6, 0.7, 0.8));
			} else if (unitType.equals("cavalry")) {
				if (unitLevel == 1)
					u = (new Cavalry(1, 40, 0.6, 0.7, 0.75));

				else if (unitLevel == 2)
					u = (new Cavalry(2, 40, 0.6, 0.7, 0.75));
				else
					u = (new Cavalry(3, 60, 0.7, 0.8, 0.9));
			}
			u.setParentArmy(resultArmy);
			resultArmy.getUnits().add(u);
			currentLine = br.readLine();
		}
		br.close();
		for (City c : availableCities) {
			if (c.getName().toLowerCase().equals(cityName.toLowerCase()))
				c.setDefendingArmy(resultArmy);
		}
	}

	public ArrayList<City> getAvailableCities() {
		return availableCities;
	}

	public ArrayList<Distance> getDistances() {
		return distances;
	}

	public int getCurrentTurnCount() {
		return currentTurnCount;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getMaxTurnCount() {
		return maxTurnCount;
	}

	public void setCurrentTurnCount(int currentTurnCount) {
		this.currentTurnCount = currentTurnCount;
	}
	
	public void targetCity(Army army, String targetName) {
		// Apparently I only need to check if it's blank :/
		// https://piazza.com/class/kndahm74mstxn?cid=395
		if(!army.getTarget().isBlank()) {
			return; // TODO: make sure this is correct from here https://piazza.com/class/kndahm74mstxn?cid=403
		}
		
		String currentLoc = army.getCurrentLocation();
		int targetValue = -1;
		for(Distance dist : this.distances) {
			if((dist.getFrom().equals(currentLoc) && dist.getTo().equals(targetName)) || (dist.getTo().equals(currentLoc) && dist.getFrom().equals(targetName))) {
				targetValue = dist.getDistance();
			}
		}
		
		assert(targetValue != -1); // TODO: Make sure there's no errors we need to raise?
	
		army.setTarget(targetName);
		army.setDistancetoTarget(targetValue);
	}
	
	public void endTurn() {
		// Increment turn count.
		this.currentTurnCount ++;
		
		// Collect food and treasure and reset cooldowns.
		for(var city : player.getControlledCities()) {
			for(var building : city.getEconomicalBuildings()) {
				building.setCoolDown(false);
				if(building instanceof Farm)
					this.player.setFood(this.player.getFood() + building.harvest());
				else if(building instanceof Market)
					this.player.setTreasury(this.player.getTreasury() + building.harvest());
			}
			for(var building : city.getMilitaryBuildings()) {
				building.setCoolDown(false);
				building.setCurrentRecruit(0);
			}
		}
		
		// Calc needed food for all armies.
		double neededFood = 0;
		for(var army : this.player.getControlledArmies()) {
			neededFood += army.foodNeeded();
		}
		
		// Decrement Food.
		player.setFood(player.getFood() - neededFood);
		if(player.getFood() < 0) player.setFood(0);
	
		// If starving, remove 10%
		if(player.getFood() == 0) {
			for(var army : this.player.getControlledArmies()) {
				for(var unit : army.getUnits()) {
					int toRem = (int)(unit.getCurrentSoldierCount() * 0.1);
					unit.setCurrentSoldierCount(unit.getCurrentSoldierCount() - toRem);
				}
			}
		}
		
		// If army has target, increment distance.
		for(var army : player.getControlledArmies()) {
			if(!army.getTarget().isEmpty()) {
				int dist = army.getDistancetoTarget();
				if(dist-1 == 0) {
					army.setCurrentLocation(army.getTarget());
					army.setTarget("");
					army.setDistancetoTarget(0);
				} else {
					army.setDistancetoTarget(dist-1);
				}
			}
		}
		
		// Handle Siege
		for(var city : getAvailableCities()) {
			if(city.isUnderSiege()) {
				city.setTurnsUnderSiege(city.getTurnsUnderSiege() + 1);
				
				if(city.getTurnsUnderSiege() >= 3) {
					//  Not sure if turns under siege should reset hmm
					city.setTurnsUnderSiege(0);
					city.setUnderSiege(false);
					continue;
				}
				
				for(var unit : city.getDefendingArmy().getUnits()) {
					int toRem = (int)(unit.getCurrentSoldierCount() * 0.1);
					unit.setCurrentSoldierCount(unit.getCurrentSoldierCount() - toRem);
				}
			}
		}
	}
	
	
}
