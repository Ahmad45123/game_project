package engine;

import java.util.ArrayList;
import buildings.EconomicBuilding;
import buildings.MilitaryBuilding;
import units.Army;

public class City {
	String name;
	ArrayList<EconomicBuilding> economicalBuildings;
	ArrayList<MilitaryBuilding> militaryBuildings;
	Army defendingArmy;
	int turnsUnderSiege;
	boolean underSiege;

	public Army getDefendingArmy() {
		return defendingArmy;
	}

	public void setDefendingArmy(Army defendingArmy) {
		this.defendingArmy = defendingArmy;
	}

	public int getTurnsUnderSiege() {
		return turnsUnderSiege;
	}

	public void setTurnsUnderSiege(int turnsUnderSiege) {
		this.turnsUnderSiege = turnsUnderSiege;
	}

	public boolean isUnderSiege() {
		return underSiege;
	}

	public void setUnderSiege(boolean underSiege) {
		this.underSiege = underSiege;
	}

	public String getName() {
		return name;
	}

	public ArrayList<EconomicBuilding> getEconomicalBuildings() {
		return economicalBuildings;
	}

	public ArrayList<MilitaryBuilding> getMilitaryBuildings() {
		return militaryBuildings;
	}

	public City(String name) {
		this.name = name;
		this.underSiege = false;
	}
}
