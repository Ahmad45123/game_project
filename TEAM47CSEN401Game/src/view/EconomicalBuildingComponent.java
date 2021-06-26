package view;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import engine.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import engine.*;
import exceptions.*;
import buildings.*;
import units.*;
import javax.swing.border.CompoundBorder;

public class EconomicalBuildingComponent extends JPanel {

	public EconomicalBuildingComponent(String buildingName, int level, Building b, City c) {
		this.setLayout(new BorderLayout());
		this.add(new JLabel(buildingName + "     Lv " + level), BorderLayout.NORTH);
		this.add(new JLabel(
				(b.getLevel() == 3 ? "Upgrade Cost: ---" : "Upgrade Cost: " + Integer.toString(b.getUpgradeCost()))),
				BorderLayout.CENTER);
		JButton upgrade = new JButton("Upgrade");
		this.add(upgrade, BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));

		upgrade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Launcher.getPlayer().upgradeBuilding(b);
					Launcher.initialiseCityView(c);
				} catch (MaxLevelException zr) {
					JOptionPane.showMessageDialog(null, "Building is already at max level");
				} catch (NotEnoughGoldException z) {
					JOptionPane.showMessageDialog(null, "Not enough gold to upgrade");
				} catch (BuildingInCoolDownException zu) {
					JOptionPane.showMessageDialog(null, "Building is currently in cooldown");
				}

			}

		});

	}

}
