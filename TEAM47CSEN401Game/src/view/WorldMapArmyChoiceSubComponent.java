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

public class WorldMapArmyChoiceSubComponent extends JPanel {

	Army army;
	

	WorldMap worldmap;
	Game game;
	Player player;

	public WorldMapArmyChoiceSubComponent(Army c, WorldMap wm) {
		army = c;
		worldmap = wm;

		game = Launcher.getGame();
		player = Launcher.getPlayer();

		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
//		this.add(new JLabel("Name: " + c.getName()), BorderLayout.CENTER);

//		this.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
		this.setChoice(null);
	}

	void setChoice(Army a) {
		army = a;

		this.removeAll();
		revalidate();
		repaint();
		if (army == null)
			return;

		JLabel armyName = new JLabel(army.getName());

		armyName.setFont(new Font("Arial", Font.PLAIN, 14));

		Launcher.setComponent(armyName, 140 / 2, 0, 140, 40, true);
		this.add(armyName);

		JLabel infoLabel = new JLabel("Target: " + army.getTarget());
		infoLabel.setFont(new Font("Arial", Font.PLAIN, 11));

		Launcher.setComponent(infoLabel, 140 / 2, 30, 140, 30, true);
		this.add(infoLabel);
	}
	
	
	public Army getArmy() {
		return army;
	}
}
