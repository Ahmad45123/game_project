package view;

import javax.swing.*;
import engine.*;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.NotEnoughGoldException;
import buildings.*;
import units.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import engine.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import exceptions.*;
import javax.swing.border.CompoundBorder;


public class MilitaryBuildingComponent extends JPanel {
	public MilitaryBuildingComponent(String buildingName, int level, Building m, City c) {
		this.setLayout(new BorderLayout());
		this.add(new JLabel(buildingName + "     Lv " + level),BorderLayout.NORTH);
		this.add(new JLabel((m.getLevel()==3?"Upgrade Cost: ---":"Upgrade Cost: " + Integer.toString(m.getUpgradeCost()))),BorderLayout.CENTER);
		JButton upgrade = new JButton("Upgrade");
		JButton recruit = new JButton("Recruit");
		this.add(recruit,BorderLayout.EAST);
		this.add(upgrade,BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
		
		upgrade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Launcher.getPlayer().upgradeBuilding(m);
					Launcher.initialiseCityView(c);
				}
				catch(MaxLevelException c) {
					JOptionPane.showMessageDialog(null, "Building is already at max level");
				}
				catch(NotEnoughGoldException a) {
					JOptionPane.showMessageDialog(null, "Not enough gold to upgrade");
				}
				catch(BuildingInCoolDownException b) {
					JOptionPane.showMessageDialog(null, "Building is currently in cooldown");
				}

			}
			
		});
		
		recruit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(buildingName.equals("ArcheryRange")) Launcher.getPlayer().recruitUnit("Archer", c.getName());
					else if(buildingName.equals("Stable")) Launcher.getPlayer().recruitUnit("Cavalry", c.getName());
					else Launcher.getPlayer().recruitUnit("Infantry", c.getName());
					Launcher.initialiseCityView(c);
				}
				catch(NotEnoughGoldException a) {
					JOptionPane.showMessageDialog(null, "Not enough gold to recruit");
				}
				catch(BuildingInCoolDownException b) {
					JOptionPane.showMessageDialog(null, "Building is currently in cooldown");
					
				}
				catch(MaxRecruitedException c) {
					JOptionPane.showMessageDialog(null, "max number of recruits already achieved");
				}
				
				
			}
			
			
		});
		
		
		
		
	}
	

}
