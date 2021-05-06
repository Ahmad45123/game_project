package engine;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import units.*;

public final class Game {
	private Player player;
	private ArrayList<City> availableCities;
	private ArrayList<Distance> distances;
	private final int maxTurnCount;
	private int currentTurnCount;

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

	public void loadArmy(String cityName, String path) throws IOException {
		for (City c : availableCities) {
			if (c.getName().equals(cityName)) {
				Army cityArmy = new Army(cityName);
				ArrayList<Unit> armyUnits = new ArrayList<Unit>();
				String currentLine = "";
				FileReader fileReader = new FileReader(path);
				BufferedReader br = new BufferedReader(fileReader);
				while ((currentLine = br.readLine()) != null) {
					String currentUnitLine[] = currentLine.split(",");
					switch (currentUnitLine[0]) {
					case "Archer":
						Archer currentArcher;
						int currentArcherLevel = Integer.parseInt(currentUnitLine[1]);
						if (currentArcherLevel == 1)
							currentArcher = new Archer(currentArcherLevel, 60, 0.4, 0.5, 0.6);
						else if (currentArcherLevel == 2)
							currentArcher = new Archer(currentArcherLevel, 60, 0.4, 0.5, 0.6);
						else
							currentArcher = new Archer(currentArcherLevel, 70, 0.5, 0.6, 0.7);
						armyUnits.add(currentArcher);
						break;

					case "Infantry":
						Infantry currentInfantry;
						int currentInfantryLevel = Integer.parseInt(currentUnitLine[1]);
						if (currentInfantryLevel == 1)
							currentInfantry = new Infantry(currentInfantryLevel, 50, 0.5, 0.6, 0.7);
						else if (currentInfantryLevel == 2)
							currentInfantry = new Infantry(currentInfantryLevel, 50, 0.5, 0.6, 0.7);
						else
							currentInfantry = new Infantry(currentInfantryLevel, 60, 0.6, 0.7, 0.8);
						armyUnits.add(currentInfantry);
						break;

					case "Cavalry":
						Cavalry currentCavalry;
						int currentCavalryLevel = Integer.parseInt(currentUnitLine[1]);
						if (currentCavalryLevel == 1)
							currentCavalry = new Cavalry(currentCavalryLevel, 40, 0.6, 0.7, 0.75);
						else if (currentCavalryLevel == 2)
							currentCavalry = new Cavalry(currentCavalryLevel, 40, 0.6, 0.7, 0.75);
						else
							currentCavalry = new Cavalry(currentCavalryLevel, 60, 0.7, 0.8, 0.9);
						armyUnits.add(currentCavalry);
						break;
					}
				}
				br.close();
				cityArmy.setUnits(armyUnits);
				c.setDefendingArmy(cityArmy);
			}
		}
	}

	private void loadCitiesAndDistances() throws IOException {
		String currentLine = "";
		FileReader fileReader = new FileReader("distances.csv");
		BufferedReader br = new BufferedReader(fileReader);
		while ((currentLine = br.readLine()) != null) {
			String lineDistanceData[] = currentLine.split(",");
			Distance currentLineDistance = new Distance(lineDistanceData[0], lineDistanceData[1],
					Integer.parseInt(lineDistanceData[2]));
			distances.add(currentLineDistance);

			boolean firstCityExistCheck = true;
			boolean secondCityExistCheck = true;

			for (City c : availableCities) {
				if (c.getName().equals(lineDistanceData[0]))
					firstCityExistCheck = false;
				if (c.getName().equals(lineDistanceData[1]))
					secondCityExistCheck = false;
			}

			if (firstCityExistCheck == true) {
				City firstArgumentCity = new City(lineDistanceData[0]);
				availableCities.add(firstArgumentCity);
			}
			if (secondCityExistCheck == true) {
				City secondArgumentCity = new City(lineDistanceData[1]);
				availableCities.add(secondArgumentCity);
			}
		}
		br.close();
	}

	public Game(String playerName, String playerCity) throws IOException {
		// Initialize Variables
		this.availableCities = new ArrayList<City>();
		this.distances = new ArrayList<Distance>();
		this.maxTurnCount = 30;
		this.currentTurnCount = 1;
		this.player = new Player(playerName);

		// Load Cities And Distances
		loadCitiesAndDistances();

		for (City c : availableCities) {
			if (c.getName().contentEquals(playerCity))
				this.player.getControlledCities().add(c);
		}

		// LoadArmies for non Player Armies
		for (City c : availableCities) {
			if (!c.getName().equals(playerCity))
				loadArmy(c.getName(), c.getName().toLowerCase() + "_army.csv");
		}
	}

}
