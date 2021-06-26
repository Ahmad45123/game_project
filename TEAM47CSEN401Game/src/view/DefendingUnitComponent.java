package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import units.*;

public class DefendingUnitComponent extends JPanel {
	
	public DefendingUnitComponent(Unit z,BattleView bv) {
		this.setLayout(new BorderLayout());
		this.add(new JLabel(z.getClass().getSimpleName()),BorderLayout.NORTH);
		this.add(new JLabel("Lv " + z.getLevel() + " Soldier Count: " + z.getCurrentSoldierCount() + "/" + z.getMaxSoldierCount()),BorderLayout.CENTER);
		JButton choose = new JButton("Attack");
		this.add(choose,BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
		
		
		choose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(bv.getState()==0) {
					JOptionPane.showMessageDialog(null,"Please choose between Manual Attack and Auto Resolve");
					
				}
				else if(bv.getState()==1) {
					
					
				}
				else {
					JOptionPane.showMessageDialog(null,"You have not chosen Manual Attack so you cannot select units manually");
				}
			}
			
		});
		
		
	}
	
	

}
