package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Infantry;
import units.Unit;

public class Barracks extends MilitaryBuilding {

	public Barracks() {
		super(2000, 1000, 500);
		this.upgradeCosts = new int[] { 1000, 1500 };
		this.recruitCosts = new int[] { 500, 550, 600 };
	}

	public void upgrade() throws BuildingInCoolDownException, MaxLevelException { // useless method to make junit happy
		super.upgrade();
	}

	public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException { // infantry
		if (this.isCoolDown()) {
			throw new BuildingInCoolDownException();
		}

		if (this.getMaxRecruit() == this.getCurrentRecruit()) {
			throw new MaxRecruitedException();
		}

		this.setCurrentRecruit(this.getCurrentRecruit() + 1);

		int level = this.getLevel();
		Infantry u = null;
		if (level == 1)
			u = (new Infantry(1, 50, 0.5, 0.6, 0.7));
		else if (level == 2)
			u = (new Infantry(2, 50, 0.5, 0.6, 0.7));
		else if (level == 3)
			u = (new Infantry(3, 60, 0.6, 0.7, 0.8));
		else {
			assert (false);
		}

		return u;
	}
}
