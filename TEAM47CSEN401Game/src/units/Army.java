package units;

import java.util.ArrayList;

import exceptions.MaxCapacityException;

/**
 * @author mohammad.hussein
 *
 */
public class Army {
	private Status currentStatus;
	private ArrayList<Unit> units;
	private int distancetoTarget;
	private String target;
	private String currentLocation;
	@SuppressWarnings("unused")
	private final int maxToHold = 10;

	public Army(String currentLocation) {
		this.currentLocation = currentLocation;
		currentStatus = Status.IDLE;
		units = new ArrayList<Unit>();
		distancetoTarget = -1;
		target = "";
	}

	public Status getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Status currentStatus) {
		this.currentStatus = currentStatus;
	}

	public ArrayList<Unit> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<Unit> units) {
		this.units = units;
	}

	public int getDistancetoTarget() {
		return distancetoTarget;
	}

	public void setDistancetoTarget(int distancetoTarget) {
		this.distancetoTarget = distancetoTarget;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public int getMaxToHold() {
		return maxToHold;
	}

	public void relocateUnit(Unit unit) throws MaxCapacityException {
		// assert
		unit.getParentArmy().getUnits().remove(unit);
		units.add(unit);
	}

	public void handleAttackedUnit(Unit u) {
		if (u.getCurrentSoldierCount() == 0) {
			units.remove(u);
		}
	}

	public double foodNeeded() {
		double ret = 0;
		for (int i = 0; i < units.size(); i++) {
			switch (currentStatus) {
			case IDLE:
				ret += units.get(i).getCurrentSoldierCount() * units.get(i).getIdleUpkeep();
				break;
			case MARCHING:
				ret += units.get(i).getCurrentSoldierCount() * units.get(i).getMarchingUpkeep();
				break;
			case BESIEGING:
				ret += units.get(i).getCurrentSoldierCount() * units.get(i).getSiegeUpkeep();
				break;
			}
		}
		return ret;
	}
}
