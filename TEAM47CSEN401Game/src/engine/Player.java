package engine;

import java.util.ArrayList;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyCityException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import exceptions.TargetNotReachedException;
import units.Army;
import units.Status;
import units.Unit;

public class Player {
	private String name;
	private ArrayList<City> controlledCities;
	private ArrayList<Army> controlledArmies;
	private double treasury;
	private double food;

	public Player(String name) {
		this.name = name;
		this.controlledCities = new ArrayList<City>();
		this.controlledArmies = new ArrayList<Army>();
	}

	public double getTreasury() {
		return treasury;
	}

	public void setTreasury(double treasury) {
		this.treasury = treasury;
	}

	public double getFood() {
		return food;
	}

	public void setFood(double food) {
		this.food = food;
	}

	public String getName() {
		return name;
	}

	public ArrayList<City> getControlledCities() {
		return controlledCities;
	}

	public ArrayList<Army> getControlledArmies() {
		return controlledArmies;
	}

	public void recruitUnit(String type, String cityName)
			throws BuildingInCoolDownException, MaxRecruitedException, NotEnoughGoldException {
		City city = null;
		for (int i = 0; i < controlledCities.size(); i++) {
			if (controlledCities.get(i).getName().equals(cityName)) {
				city = controlledCities.get(i);
			}
		}
		@SuppressWarnings("rawtypes")
		Class target = null;
		if (type.equals("Archer")) {
			target = ArcheryRange.class;
		} else if (type.equals("Cavalry")) {
			target = Stable.class;
		} else if (type.equals("Infantry")) {
			target = Barracks.class;
		} else {
			assert (false);
		}
//		assert (city != null);
		if(city == null) {
			return;
		}
		assert (target != null);

		boolean inCoolDown = false;
		boolean maxRec = false;
		int cost = -1;

		for (int i = 0; i < city.getMilitaryBuildings().size(); i++) {
			MilitaryBuilding b = city.getMilitaryBuildings().get(i);
			if (!target.isInstance(b)) {
				continue;
			}
			cost = b.getRecruitmentCost();
			if (cost > treasury) {
				throw new NotEnoughGoldException();
			}

			if (b.isCoolDown()) {
				inCoolDown = true;
				continue;
			}

			try {
				Unit u = b.recruit();
				u.setParentArmy(city.getDefendingArmy());
				city.getDefendingArmy().getUnits().add(u);
				treasury -= cost;
				return;
			} catch (Exception e) {
				if (e instanceof MaxRecruitedException) {
					maxRec = true;
					continue;
				}
				throw e;
			}

		}

		if (inCoolDown) {
			throw new BuildingInCoolDownException();
		}
		if (maxRec) {
			throw new MaxRecruitedException();
		}
		assert (false); // if properly initialized should be unreachable
	}

	public void build(String type, String cityName) throws NotEnoughGoldException {
		City city = null;
		for (int i = 0; i < controlledCities.size(); i++) {
			if (controlledCities.get(i).getName().equals(cityName)) {
				city = controlledCities.get(i);
			}
		}
		Building target = null;
		if (type.equals("ArcheryRange")) {
			target = new ArcheryRange();
		} else if (type.equals("Stable")) {
			target = new Stable();
		} else if (type.equals("Barracks")) {
			target = new Barracks();
		} else if (type.equals("Farm")) {
			target = new Farm();
		} else if (type.equals("Market")) {
			target = new Market();
		} else {
			assert (false);
		}
		assert (city != null);
		assert (target != null);

		int cost = target.getCost();
		if (cost > treasury) {
			throw new NotEnoughGoldException();
		}
		
		if (target instanceof EconomicBuilding) {
			for (int i = 0; i < city.getEconomicalBuildings().size(); i++) {
				EconomicBuilding e = city.getEconomicalBuildings().get(i);
				if (e.getClass() == target.getClass()) {
					return; //("same type already exists"); // idk what to do or throw
				}
			}
			city.getEconomicalBuildings().add((EconomicBuilding) target);
		} else {
			for (int i = 0; i < city.getMilitaryBuildings().size(); i++) {
				MilitaryBuilding e = city.getMilitaryBuildings().get(i);
				if (e.getClass() == target.getClass()) {
					return; //("same type already exists"); // idk what to do or throw
				}
			}
			city.getMilitaryBuildings().add((MilitaryBuilding) target);
		}
		
		treasury -= cost;

		// what should i do with the cooldown? or does it deactivate it or what?!??!?
		//  NO PUBLIC TESTS FOR THIS!!!!!!!!!!!!
		target.setCoolDown(true);
	}

	public void upgradeBuilding(Building b)
			throws NotEnoughGoldException, BuildingInCoolDownException, MaxLevelException {
		int cost = b.getUpgradeCost();
		if (cost > treasury) {
			throw new NotEnoughGoldException();
		}
		b.upgrade();
		treasury -= cost;

	}

	public void initiateArmy(City city, Unit unit) {
		Army army = new Army(city.getName());
		army.getUnits().add(unit);
		city.getDefendingArmy().getUnits().remove(unit);
		unit.setParentArmy(army);
		controlledArmies.add(army);
	}

	public void laySiege(Army army, City city) throws TargetNotReachedException, FriendlyCityException {
//		if (!army.getTarget().equals(city.getName()))
//			assert (false);// city not even targeted LOL
		if (army.getDistancetoTarget() > 0 || army.getCurrentLocation() != city.getName()) {
			throw new TargetNotReachedException();
		}
		for (int i = 0; i < controlledCities.size(); i++) {
			if (controlledCities.get(i).getName() == city.getName())// dont know whether to compare names or instances{
				throw new FriendlyCityException();
		}
		
		army.setCurrentStatus(Status.BESIEGING);
		city.setUnderSiege(true);
		city.setTurnsUnderSiege(0);
	}
}
