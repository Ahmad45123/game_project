package view;

import javax.swing.*;

import engine.City;
import engine.Game;
import engine.Player;
import units.Archer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class WorldMap extends JPanel {
	// use global const or something
	private int xRes = 800;
	private int yRes = 600;
	private int midRes = xRes / 2;

	public WorldMap() {
		Game game = Launcher.getGame();
		Player player = Launcher.getPlayer();

		this.setLayout(null);
		Insets insets = this.getInsets();
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);

		JLabel worldMap = new JLabel();
		worldMap.setText("World Map");
		worldMap.setFont(new Font("Ariel", Font.BOLD, 28));
		int labelXOffset = midRes;
		int labelYOffset = 30;
		Launcher.setComponent(worldMap, labelXOffset, labelYOffset, 150, 50, true);
		this.add(worldMap);

		JLabel playerNameLabel = new JLabel();
		playerNameLabel.setText("Player Name: " + player.getName());
		playerNameLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(playerNameLabel, 5, 0, 300, 900, false);
		this.add(playerNameLabel);

		JLabel turnCountLabel = new JLabel();
		turnCountLabel.setText("Turn Count: " + game.getCurrentTurnCount());
		turnCountLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(turnCountLabel, 5, 0, 300, 950, false);
		this.add(turnCountLabel);

		JLabel foodCountLabel = new JLabel();
		foodCountLabel.setText("Food: " + Math.round(player.getFood() * 10) / 10.0);
		foodCountLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(foodCountLabel, 5, 0, 300, 1000, false);
		this.add(foodCountLabel);

		JLabel goldCountLabel = new JLabel();
		goldCountLabel.setText("Gold: " + Math.round(player.getTreasury() * 10) / 10.0);
		goldCountLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(goldCountLabel, 5, 0, 300, 1050, false);
		this.add(goldCountLabel);

//		JButton romeButton = new JButton("Rome");
//		Launcher.setComponent(romeButton, 200, 100, 100, 50, true);
//		romeButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				String name = romeButton.getText().toLowerCase();
//				for (City c : player.getControlledCities()) {
//					if (c.getName().toLowerCase().equals(name)) {
//						Launcher.initialiseCityView(c);
//						return;
//					}
//				}
//
//				for (City c : game.getAvailableCities()) {
//					if (c.getName().toLowerCase().contentEquals(name)) {
//						// check whether to attack first or large siege or just move the army or will
//						// the attack option be in city view
//						// maybe just show error if not under siege 
//						Launcher.initialisePreBattleView(c);
////						JOptionPane.showMessageDialog(null, "pre-battle view");
//					}
//				}
//			}
//		});
//		this.add(romeButton);
		City cairoCity = null , romeCity = null , spartaCity =null;
		for (City c : game.getAvailableCities()) {
			if (c.getName().toLowerCase().contentEquals("cairo"))
				cairoCity = c;
			else if (c.getName().toLowerCase().contentEquals("rome"))
				romeCity = c;
			else if (c.getName().toLowerCase().contentEquals("sparta"))
				spartaCity = c;

		}
		assert(cairoCity != null && romeCity != null && spartaCity != null);
		
		
		
		WorldMapCitySubComponent romePanel = new WorldMapCitySubComponent(romeCity, this);
		Launcher.setComponent(romePanel, xRes/6, 100, 210, 50, true);
		this.add(romePanel);
		
		WorldMapCitySubComponent spartaPanel = new WorldMapCitySubComponent(spartaCity, this);
		Launcher.setComponent(spartaPanel, xRes/2, 100, 210, 50, true);
		this.add(spartaPanel);
		
		WorldMapCitySubComponent cairoPanel = new WorldMapCitySubComponent(cairoCity, this);
		Launcher.setComponent(cairoPanel, 5*xRes/6, 100, 210, 50, true);
		this.add(cairoPanel);
//		
//		
//		JButton spartaButton = new JButton("Sparta");
//		Launcher.setComponent(spartaButton, 400, 200, 100, 50, true);
//		spartaButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				String name = spartaButton.getText().toLowerCase();
//				for (City c : player.getControlledCities()) {
//					if (c.getName().toLowerCase().equals(name)) {
//						Launcher.initialiseCityView(c);
//						return;
//					}
//				}
//
//				for (City c : game.getAvailableCities()) {
//					if (c.getName().toLowerCase().contentEquals(name)) {
//						// check whether to attack first or large seige or just move the army or will
//						// the attack option be in city view
//						Launcher.initialisePreBattleView(c);
////						JOptionPane.showMessageDialog(null, "pre-battle view");
//					}
//				}
//			}
//		});
//		this.add(spartaButton);
//
//		JButton cairoButton = new JButton("Cairo");
//		Launcher.setComponent(cairoButton, 600, 340, 100, 50, true);
//		cairoButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				String name = cairoButton.getText().toLowerCase();
//				for (City c : player.getControlledCities()) {
//					if (c.getName().toLowerCase().equals(name)) {
//						Launcher.initialiseCityView(c);
//						return;
//					}
//				}
//
//				for (City c : game.getAvailableCities()) {
//					if (c.getName().toLowerCase().contentEquals(name)) {
//						// check whether to attack first or large seige or just move the army or will
//						// the attack option be in city view
//						Launcher.initialisePreBattleView(c);
////						JOptionPane.showMessageDialog(null, "pre-battle view");
//					}
//				}
//			}
//		});
//		this.add(cairoButton);

		JButton nextTurnButton = new JButton("Next Turn");
		Launcher.setComponent(nextTurnButton, 680, 500, 100, 50, true);
		nextTurnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// JUST FOR TESTING
				// add units
//				try {
//				player.build("archeryrange", "Rome");
				game.endTurn();
				Launcher.initialiseWorldMap();
//				player.recruitUnit("archer", "Rome");
//				System.out.println("recurited archer");
//				}catch(Exception ev) {
//					System.out.println(ev);
//				}

//				City ci = null;
//				for (City c : game.getAvailableCities()) {
//					if (c.getName().toLowerCase().contentEquals("sparta")) {ci = c;}}
//				// just for test not real values;
//				player.initiateArmy(ci, new Archer(1, 1, 1, 1, 1), "testarmy");
//				System.out.println("added army to sparta");
			}
		});
		this.add(nextTurnButton);

		JButton ArmyListButton = new JButton("ArmyListview");
		Launcher.setComponent(ArmyListButton, 550, 500, 100, 50, true);
		ArmyListButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Launcher.initialiseArmyListview();
			}
		});
		this.add(ArmyListButton);

	}

	public void chooseCity(City city) {
		// TODO Auto-generated method stub
		System.out.println("chose: " + city.getName());

	}

}
