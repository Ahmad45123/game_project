package units;

import java.util.ArrayList;

import exceptions.FriendlyFireException;

public class Cavalry extends Unit {

	public Cavalry(int level, int maxSoldierConunt, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
		super(level, maxSoldierConunt, idleUpkeep, marchingUpkeep, siegeUpkeep);
	}
	
	public void attack(Unit target) throws FriendlyFireException{
		if(target.getParentArmy().equals(this.getParentArmy())) throw new FriendlyFireException();
		double atkpts = 0;
		double atkfctr = 0;
		switch(this.getLevel()) {
		case 1:
			if(target.getClass().getName().contains("Archer")) atkfctr = 0.5;
			else if(target.getClass().getName().contains("Infantry")) atkfctr = 0.3;
			else if(target.getClass().getName().contains("Cavalry")) atkfctr = 0.2;
			break;
		case 2:
			if(target.getClass().getName().contains("Archer")) atkfctr = 0.6;
			else if(target.getClass().getName().contains("Infantry")) atkfctr = 0.4;
			else if(target.getClass().getName().contains("Cavalry")) atkfctr = 0.2;
			break;
		case 3:
			if(target.getClass().getName().contains("Archer")) atkfctr = 0.7;
			else if(target.getClass().getName().contains("Infantry")) atkfctr = 0.5;
			else if(target.getClass().getName().contains("Cavalry")) atkfctr = 0.3;
			break;
		}
		atkpts = atkfctr*this.getCurrentSoldierCount();
		Army enemyArmy = target.getParentArmy();
		ArrayList<Unit> enemyUnits = enemyArmy.getUnits();
		int intpts = (int) atkpts;
		if(intpts>=target.getCurrentSoldierCount()) {
			for(Unit z : enemyUnits) {
				if(z.equals(target)) {
					enemyUnits.remove(z);
					break;
				}
			}
		}
		else {
			Unit realTarget = null;
			for(Unit z : enemyUnits) {
				if(z.equals(target))
					realTarget = z;
					break;
			}
			int newSoldierCount = realTarget.getCurrentSoldierCount()-intpts;
			realTarget.setCurrentSoldierCount(newSoldierCount);
		}
	}
	
}
