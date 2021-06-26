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

public class WorldMapArmySubComponent extends JPanel {

	Army army;
	WorldMap worldmap;
	Game game;
	Player player;

	public WorldMapArmySubComponent(Army a, WorldMap wm) {
		army = a;
		worldmap = wm;

		game = Launcher.getGame();
		player = Launcher.getPlayer();

		this.setLayout(new BorderLayout());

		JLabel label = new JLabel(a.getName() + ", status: " + (a.getCurrentStatus() == Status.IDLE ? "Idle"
				: a.getCurrentStatus() == Status.MARCHING ? "Marching" : "Besieging"));
		if (!a.getTarget().isEmpty())
			label = new JLabel(a.getName() + ": " + a.getCurrentLocation() + ", dist: " + a.getDistancetoTarget()
					+ ", status: " + (a.getCurrentStatus() == Status.IDLE ? "Idle"
							: a.getCurrentStatus() == Status.MARCHING ? "Marching" : "Besieging"));
//		Launcher.setComponent(label, 0, 0, 200, 100, false);
		this.add(label, BorderLayout.NORTH);

		WorldMapUnitSubComponent units = new WorldMapUnitSubComponent(army.getUnits(), wm);
		this.add(units, BorderLayout.CENTER);

		this.setBorder(BorderFactory.createEtchedBorder());

		JButton button = new JButton("choose");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				worldmap.chooseArmy(army);

			}
		});
		this.add(button, BorderLayout.SOUTH);

//		this.add(new JLabel(buildingName + "     Lv " + level),BorderLayout.NORTH);
//		this.add(new JLabel((b.getLevel()==3?"Upgrade Cost: ---":"Upgrade Cost: " + Integer.toString(b.getUpgradeCost()))),BorderLayout.CENTER);

	}

}
