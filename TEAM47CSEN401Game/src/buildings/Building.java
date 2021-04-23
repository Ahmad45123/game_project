package buildings;

public class Building {
	private int cost;
	private int level;
	private int upgradeCost;
	private boolean coolDown;
	
	public Building(int cost, int upgradeCost) {
		this.cost = cost;
		this.level = 1;
		this.upgradeCost = upgradeCost;
		this.coolDown = true;
	}
	
	public int getCost() {
		return this.getCost();
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getUpgradeCost() {
		return this.upgradeCost;
	}
	
	public void setUpgradeCost(int upgradeCost) {
		this.upgradeCost = upgradeCost;
	}
	
	public boolean getCoolDown() {
		return this.coolDown;
	}
	
	public void setCoolDown(boolean coolDown) {
		this.coolDown = coolDown;
	}
	

}
