package units;

public abstract class Unit {
	private int level;
	private int maxSoldierCount;
	private int currentSoldierCount;
	private double idleUpkeep;
	private double marchingUpkeep;
	private double siegeUpkeep;
	
	public Unit(int level,int maxSoldierCount,double idleUpkeep,double marchingUpkeep,double siegeUpkeep) {
		this.level = level;
		this.maxSoldierCount = maxSoldierCount;
		this.idleUpkeep = idleUpkeep;
		this.marchingUpkeep = marchingUpkeep;
		this.siegeUpkeep = siegeUpkeep;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public int getMaxSoldierCount() {
		return this.maxSoldierCount;
	}
	
	public int getCurrentSoldierCount() {
		return this.currentSoldierCount;
	}
	
	public void setCurrentSoldierCount(int currentSoldierCount) {
		this.currentSoldierCount = currentSoldierCount;
	}
	
	public double getIdleUpkeep() {
		return this.idleUpkeep;
	}
	
	public double getMarchingUpkeep() {
		return this.marchingUpkeep;
	}
	
	public double getSiegeUpkeep() {
		return this.siegeUpkeep;
	}

}
