package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Cavalry;
import units.Unit;

public class Stable extends MilitaryBuilding {

	public Stable() {
		super(2500, 1500, 600);
		this.upgradeCosts = new int[] { 1500, 2000 };
		this.recruitCosts = new int[] { 600, 650, 700 };
	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException { // useless method to make junit happy
		super.upgrade();
	}
	
	public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException { // Cavalry
		if (this.isCoolDown()) {
			throw new BuildingInCoolDownException();
		}

		if (this.getMaxRecruit() == this.getCurrentRecruit()) {
			throw new MaxRecruitedException();
		}

		this.setCurrentRecruit(this.getCurrentRecruit() + 1);

		int level = this.getLevel();
		Cavalry u = null;
		if (level == 1)
			u = (new Cavalry(1, 40, 0.6, 0.7, 0.75));
		else if (level == 2)
			u = (new Cavalry(2, 40, 0.6, 0.7, 0.75));
		else if (level == 3)
			u = (new Cavalry(3, 60, 0.7, 0.8, 0.9));
		else {
			assert (false);
		}

		return u;
	}
}
