package buildings;

public abstract class MilitaryBuilding extends Building {
	private int recruitmentCost;
	private int currentRecruit;
	private int maxRecruit;
	
	public MilitaryBuilding(int cost, int upgradeCost, int recruitmentCost) {
		super(cost,upgradeCost);
		this.recruitmentCost = recruitmentCost;
		this.maxRecruit = 3;
	}
	
	public int getRecruitmentCost() {
		return this.recruitmentCost;
	}
	
	public void setRecruitmentCost(int recruitmentCost) {
		this.recruitmentCost = recruitmentCost;
	}
	
	public int getCurrentRecruit() {
		return this.currentRecruit;
	}
	
	public void setCurrentRecruit(int currentRecruit) {
		this.currentRecruit = currentRecruit;
	}
	
	public int getMaxRecruit() {
		return this.maxRecruit;
	}
	
}
