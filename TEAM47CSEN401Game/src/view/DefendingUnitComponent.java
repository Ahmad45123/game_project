package view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import units.*;

public class DefendingUnitComponent extends JPanel {
	
	public DefendingUnitComponent(Unit z) {
		this.setLayout(new BorderLayout());
		this.add(new JLabel(z.getClass().getSimpleName()),BorderLayout.NORTH);
		this.add(new JLabel("Lv " + z.getLevel() + " Soldier Count: " + z.getCurrentSoldierCount() + "/" + z.getMaxSoldierCount()),BorderLayout.CENTER);
		JButton choose = new JButton("Attack");
		this.add(choose,BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
	}
	
	

}
