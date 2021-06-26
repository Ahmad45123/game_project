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

public class WorldMapCitySubComponent extends JPanel {

	City city;
	WorldMap worldmap;
	Game game;
	Player player;

	public WorldMapCitySubComponent(City c, WorldMap wm) {
		city = c;
		worldmap = wm;

		game = Launcher.getGame();
		player = Launcher.getPlayer();

		this.setLayout(null);
//		this.setBorder(BorderFactory.createLineBorder(Color.black));
//		this.add(new JLabel("Name: " + c.getName()), BorderLayout.CENTER);

		JButton cityButton = new JButton(c.getName());

		cityButton.setFont(new Font("Arial", Font.BOLD, 30));
		cityButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (player.getControlledCities().contains(city))
					Launcher.initialiseCityView(city);

				else
					JOptionPane.showMessageDialog(null, "sry u don't ctrl this city");

			}
		});
		Launcher.setComponent(cityButton, 0, 0, 140, 30, false);
		this.add(cityButton);

		JTextArea info = new JTextArea("siege: " + (c.getTurnsUnderSiege() == -1 ? "no" : c.getTurnsUnderSiege()));
		Launcher.setComponent(info, 0, 30, 140, 20, false);
		this.add(info);

		JButton chooseButton = new JButton("choose");
		chooseButton.setFont(new Font("Arial", Font.PLAIN, 11));
		chooseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				worldmap.chooseCity(city);
			}
		});

		Launcher.setComponent(chooseButton, 140, 0, 70, 50, false);
		this.add(chooseButton);

//		this.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
	}

}
