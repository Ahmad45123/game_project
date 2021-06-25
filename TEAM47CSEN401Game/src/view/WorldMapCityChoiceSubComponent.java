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

public class WorldMapCityChoiceSubComponent extends JPanel {

	City city;
	WorldMap worldmap;
	Game game;
	Player player;

	public WorldMapCityChoiceSubComponent(City c, WorldMap wm) {
		city = c;
		worldmap = wm;

		game = Launcher.getGame();
		player = Launcher.getPlayer();

		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
//		this.add(new JLabel("Name: " + c.getName()), BorderLayout.CENTER);

//		this.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
		this.setChoice(null);
	}

	void setChoice(City c) {
		city = c;

		this.removeAll();
		revalidate();
		repaint();
		if (city == null)
			return;

		JLabel cityName = new JLabel(c.getName());

		cityName.setFont(new Font("Arial", Font.PLAIN, 24));

		Launcher.setComponent(cityName, 140 / 2, 0, 140, 40, true);
		this.add(cityName);

		JLabel infoLabel = new JLabel("Turn under siege: " + city.getTurnsUnderSiege());
		infoLabel.setFont(new Font("Arial", Font.PLAIN, 11));

		Launcher.setComponent(infoLabel, 140 / 2, 30, 140, 30, true);
		this.add(infoLabel);
	}

	public City getCity() {
		return city;
	}
}
