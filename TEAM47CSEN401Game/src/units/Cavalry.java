package units;

import java.util.ArrayList;

import exceptions.FriendlyFireException;

public class Cavalry extends Unit {

	public Cavalry(int level, int maxSoldierConunt, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
		super(level, maxSoldierConunt, idleUpkeep, marchingUpkeep, siegeUpkeep);
	}
	
	public void attack(Unit target) throws FriendlyFireException{
		if(target.getParentArmy().equals(this.getParentArmy())) throw new FriendlyFireException();
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
		int intpts = (int) (this.getCurrentSoldierCount()*atkfctr);
		if(intpts>=target.getCurrentSoldierCount()) {
			target.setCurrentSoldierCount(0);
		}
		else {
			int newSoldierCount = target.getCurrentSoldierCount() - (int) (this.getCurrentSoldierCount() * atkfctr);
			target.setCurrentSoldierCount(newSoldierCount);
		}
		target.getParentArmy().handleAttackedUnit(target);
	}
	
}
