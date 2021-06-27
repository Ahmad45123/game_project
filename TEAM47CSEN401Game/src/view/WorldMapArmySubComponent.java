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
		JPanel topPanel = new JPanel(new BorderLayout());
		this.setLayout(new BorderLayout());

		JLabel label = new JLabel(a.getName() + ", Status: " + (a.getCurrentStatus() == Status.IDLE ? "Idle"
				: a.getCurrentStatus() == Status.MARCHING ? "Marching" : "Besieging") + " No. of Units:  " + a.getUnits().size());
		if (!a.getTarget().isEmpty())
			label = new JLabel(a.getName() + ": " + a.getCurrentLocation() + ", Dist: " + a.getDistancetoTarget()
					+ ", Status: " + (a.getCurrentStatus() == Status.IDLE ? "Idle"
							: a.getCurrentStatus() == Status.MARCHING ? "Marching" : "Besieging") + " No. of Units:  " + a.getUnits().size());
		topPanel.add(label, BorderLayout.WEST);

		WorldMapUnitSubComponent units = new WorldMapUnitSubComponent(army.getUnits(), wm);
		this.add(units, BorderLayout.CENTER);

		this.setBorder(BorderFactory.createEtchedBorder());

		JButton button = new JButton("Choose");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				worldmap.chooseArmy(army);

			}
		});
		topPanel.add(button, BorderLayout.EAST);
		
		
		this.add(topPanel,BorderLayout.NORTH);

	}

}
