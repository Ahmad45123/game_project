package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Farm extends EconomicBuilding {

	private int[] harvestAmount;

	public Farm() {
		super(1000, 500);
		this.upgradeCosts = new int[] { 500, 700 };
		this.harvestAmount = new int[] { 500, 700, 100 };
	}

	public int harvest() {
		return harvestAmount[this.getLevel() - 1];// logically the harvests should be implemented in Economic Building
													// but the method is abstract??
	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException { // useless method to make junit happy
		super.upgrade();
	}

}
