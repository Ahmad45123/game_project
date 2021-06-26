package view;

import javax.swing.*;

import engine.City;
import engine.Game;
import engine.Player;
import units.Archer;
import units.Army;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

// purposely dumb design in order to use some exceptions

public class WorldMap extends JPanel {
	// use global const or something
	private int xRes = Launcher.getxRes();
	private int yRes = Launcher.getyRes();
	private int midRes = Launcher.getMidRes();

	WorldMapCityChoiceSubComponent cityChoice;
	WorldMapArmyChoiceSubComponent armyChoice;

	public WorldMap() {
		Game game = Launcher.getGame();
		Player player = Launcher.getPlayer();

		this.setLayout(null);
		Insets insets = this.getInsets();
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);

		JLabel worldMap = new JLabel();
		worldMap.setText("World Map");
		worldMap.setFont(new Font("Ariel", Font.BOLD, 40));
		int labelXOffset = midRes;
		int labelYOffset = 30;
		Launcher.setComponent(worldMap, labelXOffset, labelYOffset, 250, 50, true);
		this.add(worldMap);

		JLabel playerNameLabel = new JLabel();
		playerNameLabel.setText("Player Name: " + player.getName());
		playerNameLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(playerNameLabel, 5, yRes - 140, 300, 20, false);
		this.add(playerNameLabel);
//
		JLabel turnCountLabel = new JLabel();
		turnCountLabel.setText("Turn Count: " + game.getCurrentTurnCount());
		turnCountLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(turnCountLabel, 5, yRes - 120, 300, 20, false);
		this.add(turnCountLabel);
//
		JLabel foodCountLabel = new JLabel();
		foodCountLabel.setText("Food: " + Math.round(player.getFood() * 10) / 10.0);
		foodCountLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(foodCountLabel, 5, yRes - 100, 300, 20, false);
		this.add(foodCountLabel);

		JLabel goldCountLabel = new JLabel();
		goldCountLabel.setText("Gold: " + Math.round(player.getTreasury() * 10) / 10.0);
		goldCountLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(goldCountLabel, 5, yRes - 80, 300, 20, false);
		this.add(goldCountLabel);

		City cairoCity = null, romeCity = null, spartaCity = null;
		for (City c : game.getAvailableCities()) {
			if (c.getName().toLowerCase().contentEquals("cairo"))
				cairoCity = c;
			else if (c.getName().toLowerCase().contentEquals("rome"))
				romeCity = c;
			else if (c.getName().toLowerCase().contentEquals("sparta"))
				spartaCity = c;

		}
		assert (cairoCity != null && romeCity != null && spartaCity != null);

		WorldMapCitySubComponent romePanel = new WorldMapCitySubComponent(romeCity, this);
		Launcher.setComponent(romePanel, xRes / 6, 100, 210, 50, true);
		this.add(romePanel);

		WorldMapCitySubComponent spartaPanel = new WorldMapCitySubComponent(spartaCity, this);
		Launcher.setComponent(spartaPanel, xRes / 2, 100, 210, 50, true);
		this.add(spartaPanel);

		WorldMapCitySubComponent cairoPanel = new WorldMapCitySubComponent(cairoCity, this);
		Launcher.setComponent(cairoPanel, 5 * xRes / 6, 100, 210, 50, true);
		this.add(cairoPanel);

		JLabel cityLabel = new JLabel();
		cityLabel.setText("City");
		cityLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(cityLabel, 4 * xRes / 6 + 180, 7 * yRes / 12 + 45, 140, 70, true);
		this.add(cityLabel);

		JLabel armyLabel = new JLabel();
		armyLabel.setText("Army");
		armyLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(armyLabel, 4 * xRes / 6, 7 * yRes / 12 + 45, 140, 70, true);
		this.add(armyLabel);

		cityChoice = new WorldMapCityChoiceSubComponent(null, this);
		Launcher.setComponent(cityChoice, 4 * xRes / 6 + 180, 7 * yRes / 12 + 100, 140, 70, true);
		this.add(cityChoice);

		armyChoice = new WorldMapArmyChoiceSubComponent(null, this);
		Launcher.setComponent(armyChoice, 4 * xRes / 6, 7 * yRes / 12 + 100, 140, 70, true);
		this.add(armyChoice);

		JButton nextTurnButton = new JButton("Next Turn");
		Launcher.setComponent(nextTurnButton, 940, 610, 100, 50, true);
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

//		JButton ArmyListButton = new JButton("ArmyListview");
//		Launcher.setComponent(ArmyListButton, 550, 500, 100, 50, true);
//		ArmyListButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Launcher.initialiseArmyListview();
//			}
//		});
//		this.add(ArmyListButton);

		JButton siegeButton = new JButton("Siege");
		Launcher.setComponent(siegeButton, xRes - 125, 480 + 100, 100, 50, false);
		siegeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkBoxes())
					return;
				Army army = armyChoice.getArmy();
				City city = cityChoice.getCity();
				if (city.isUnderSiege()) {
					JOptionPane.showMessageDialog(null, "city is already under siege");
					return;
				}
				try {
					player.laySiege(army, city);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.toString());
				}
			}
		});
		this.add(siegeButton);

		JButton attackButton = new JButton("Attack");
		Launcher.setComponent(attackButton, xRes - 125, 425 + 100, 100, 50, false);
		attackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkBoxes())
					return;
				Army army = armyChoice.getArmy();
				City city = cityChoice.getCity();
				if (army.getCurrentLocation() != city.getName()) {
					JOptionPane.showMessageDialog(null, "army is not at the city");
					return;
				}
				if (army.getDistancetoTarget() > 0) {
					JOptionPane.showMessageDialog(null, "army is on its way to the city");
					return;
				}
				Launcher.initialiseBattleView(army, city.getDefendingArmy());
			}
		});
		this.add(attackButton);

		JButton moveButton = new JButton("Move");
		Launcher.setComponent(moveButton, xRes - 125, 370 + 100, 100, 50, false);
		moveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkBoxes())
					return;
				Army army = armyChoice.getArmy();
				City city = cityChoice.getCity();
				if (army.getCurrentLocation() == city.getName())
					JOptionPane.showMessageDialog(null, "army is already at the city");
				game.targetCity(army, city.getName());

			}
		});
		this.add(moveButton);

		// TODO find way to set defending army

		ArrayList<Army> romeArmies = new ArrayList<Army>(), spartaArmies = new ArrayList<Army>(),
				cairoArmies = new ArrayList<Army>(), otherArmies = new ArrayList<Army>();

		for (int i = 0; i < player.getControlledArmies().size(); i++) {
			Army a = player.getControlledArmies().get(i);
			assert (a != null);
			if (a.getCurrentLocation().contentEquals("Rome"))
				romeArmies.add(a);
			else if (a.getCurrentLocation().contentEquals("Sparta"))
				spartaArmies.add(a);
			else if (a.getCurrentLocation().contentEquals("Cairo"))
				cairoArmies.add(a);
			else
				otherArmies.add(a);
		}

		WorldMapArmyList romeArmyList = new WorldMapArmyList(romeArmies, this);
		JScrollPane romeArmyPanel = new JScrollPane(romeArmyList);
		romeArmyPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Launcher.setComponent(romeArmyPanel, 30, 175, 360, 250, false);
		this.add(romeArmyPanel);

		WorldMapArmyList spartaArmyList = new WorldMapArmyList(spartaArmies, this);
		JScrollPane spartaArmyPanel = new JScrollPane(spartaArmyList);
		spartaArmyPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Launcher.setComponent(spartaArmyPanel, 400, 175, 360, 250, false);
		this.add(spartaArmyPanel);

		WorldMapArmyList cairoArmyList = new WorldMapArmyList(cairoArmies, this);
		JScrollPane cairoArmyPanel = new JScrollPane(cairoArmyList);
		cairoArmyPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Launcher.setComponent(cairoArmyPanel, 800, 175, 360, 250, false);
		this.add(cairoArmyPanel);

		if (otherArmies.size() > 0) {
			System.out.println("army");
			System.out.println(otherArmies.get(0).getCurrentLocation());
			System.out.println(otherArmies.get(0).getDistancetoTarget());
		}

		WorldMapArmyList otherArmyList = new WorldMapArmyList(otherArmies, this);
		JScrollPane otherArmyPanel = new JScrollPane(otherArmyList);
		otherArmyPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Launcher.setComponent(otherArmyPanel, 300, 430, 360, 250, false);
		this.add(otherArmyPanel);

	}

	public void chooseCity(City city) {
		// TODO Auto-generated method stub
		cityChoice.setChoice(city);

	}

	public void chooseArmy(Army army) {
		armyChoice.setChoice(army);
	}

	boolean checkBoxes() {
		if (cityChoice.getCity() == null) {
			JOptionPane.showMessageDialog(null, "City field is empty");
			return true;
		}
		if (armyChoice.getArmy() == null) {
			JOptionPane.showMessageDialog(null, "Army field is empty");
			return true;
		}
		return false;
	}

}
