package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Archer;
import units.Unit;

public class ArcheryRange extends MilitaryBuilding {

	public ArcheryRange() {
		super(1500, 800, 400);
		this.upgradeCosts = new int[] { 800, 700 };
		this.recruitCosts = new int[] { 400, 450, 500 };
	}

	public void upgrade() throws BuildingInCoolDownException, MaxLevelException { // useless method to make junit happy
		super.upgrade();
	}

	public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException { // Archer
		if (this.isCoolDown()) {
			throw new BuildingInCoolDownException();
		}

		if (this.getMaxRecruit() == this.getCurrentRecruit()) {
			throw new MaxRecruitedException();
		}

		this.setCurrentRecruit(this.getCurrentRecruit() + 1);

		int level = this.getLevel();
		Archer u = null;
		if (level == 1)
			u = (new Archer(1, 60, 0.4, 0.5, 0.6));

		else if (level == 2)
			u = (new Archer(2, 60, 0.4, 0.5, 0.6));
		else if (level == 3)
			u = (new Archer(3, 70, 0.5, 0.6, 0.7));
		else {
			assert (false);
		}

		return u;
	}
}
