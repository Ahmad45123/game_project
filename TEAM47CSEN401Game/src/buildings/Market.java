package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Market extends EconomicBuilding {

	private int[] harvestAmount;

	public Market() {
		super(1500, 700);
		this.upgradeCosts = new int[] { 700, 1000 };
		this.harvestAmount = new int[] { 1000, 1500, 2000 };
	}

	public int harvest() {
		return harvestAmount[this.getLevel() - 1];// logically the harvests should be implemented in Economic Building
													// but the method is abstract??
	}

	public void upgrade() throws BuildingInCoolDownException, MaxLevelException { // useless method to make junit happy
		super.upgrade();
	}

}
