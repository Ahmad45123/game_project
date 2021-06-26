package view;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import engine.*;
import units.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import engine.*;
import javax.swing.border.CompoundBorder;

public class ArmyPreBattleComponent extends JPanel {

	Army army;
	PreBattleView pbv;
	boolean active;
	boolean siegeActive;

	public ArmyPreBattleComponent(Army a, PreBattleView pbv, boolean attackActive, boolean siegeActive) {
		army = a;
		this.pbv = pbv;
		this.active = attackActive;
		this.siegeActive = siegeActive;

		this.setLayout(new BorderLayout());
		this.add(new JLabel("Name: " + a.getName()), BorderLayout.NORTH);
//		this.add(new JLabel("Target: " + a.getTarget()),BorderLayout.CENTER);

		if (attackActive) {
			JButton button = new JButton("attack");
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					pbv.attack(army);
				}
			});
			this.add(button, BorderLayout.SOUTH);
		}

		if (siegeActive) {
			JButton button = new JButton("siege");
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					pbv.siege(army);
				}
			});
			this.add(button, BorderLayout.CENTER);
		}
		this.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
	}

}
