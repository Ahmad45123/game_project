package view;

import javax.swing.*;
import engine.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import engine.*;
import view.*;
import buildings.*;
import units.*;

public class CityView extends JLayeredPane {
	private int xRes = 1280;
	private int yRes = 720;
	private int midRes = xRes / 2;

	public CityView(City c) {
		
		Game game = Launcher.getGame();
		Player player = Launcher.getPlayer();
		
		
		this.setLayout(null);
		
		JLabel cityView = new JLabel();
		cityView.setText("City View");
		cityView.setFont(new Font("Ariel", Font.BOLD, 28));
		Launcher.setComponent(cityView, midRes-60, 15, 150, 50, true);
		this.add(cityView);
		
		JLabel playerNameLabel = new JLabel();
		playerNameLabel.setText("Player Name: " + player.getName());
		playerNameLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(playerNameLabel, 575-40+400, 0, 300, 25, false);
		this.add(playerNameLabel);

		JLabel turnCountLabel = new JLabel();
		turnCountLabel.setText("Turn Count: " + game.getCurrentTurnCount());
		turnCountLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(turnCountLabel, 575-40+400, 0, 300, 75, false);
		this.add(turnCountLabel);

		JLabel foodCountLabel = new JLabel();
		foodCountLabel.setText("Food: " + Math.round(player.getFood() * 10) / 10.0);
		foodCountLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(foodCountLabel, 575-40+400, 0, 300, 125, false);
		this.add(foodCountLabel);

		JLabel goldCountLabel = new JLabel();
		goldCountLabel.setText("Gold: " + Math.round(player.getTreasury() * 10) / 10.0);
		goldCountLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(goldCountLabel, 575-40+400, 0, 300, 175, false);
		this.add(goldCountLabel);
		
		JButton backButton  = new JButton();
		backButton.setText("Back to World Map");
		Launcher.setComponent(backButton, 20, 5, 175, 62, false);		
		this.add(backButton);
		
		JLabel currentCity = new JLabel();
		currentCity.setText("City: " + c.getName());
		currentCity.setFont(new Font("Ariel", Font.BOLD, 20));
		Launcher.setComponent(currentCity, 50, 35, 200, 100, false);
		this.add(currentCity);
		
		JButton nextTurn = new JButton();
		nextTurn.setText("Next Turn");
		Launcher.setComponent(nextTurn, midRes-60, 75, 130, 35, true);
		this.add(nextTurn);
		
		JButton buildFarm = new JButton();
		buildFarm.setText("Build Farm");
		buildFarm.setMargin(new Insets(0, 0, 0, 0));
		Launcher.setComponent(buildFarm, 675+450, 160, 130, 75, false);
		this.add(buildFarm);
		
		JLabel farmCost = new JLabel("Cost: 1000");
		farmCost.setFont(new Font("Ariel", Font.BOLD, 14));
		Launcher.setComponent(farmCost, 725+460, 205, 80, 80, true);
		this.add(farmCost);
		
		JButton buildMarket = new JButton();
		buildMarket.setText("Build Market");
		buildMarket.setMargin(new Insets(0, 0, 0, 0));
		Launcher.setComponent(buildMarket, 675+450, 265, 130, 75, false);
		this.add(buildMarket);
		
		JLabel marketCost = new JLabel("Cost: 1500");
		marketCost.setFont(new Font("Ariel", Font.BOLD, 14));
		Launcher.setComponent(marketCost, 725+460, 310, 80, 80, true);
		this.add(marketCost);
		
		JButton buildArcheryRange  = new JButton();
		buildArcheryRange.setText("Build Archery Range");
		buildArcheryRange.setMargin(new Insets(0, 0, 0, 0));
		Launcher.setComponent(buildArcheryRange, 675+450, 370, 130, 75, false);
		this.add(buildArcheryRange);
		
		JLabel archeryRangeCost = new JLabel("Cost: 1500");
		archeryRangeCost.setFont(new Font("Ariel", Font.BOLD, 14));
		Launcher.setComponent(archeryRangeCost, 725+460, 415, 80, 80, true);
		this.add(archeryRangeCost);
		
		JButton buildBarracks = new JButton();
		buildBarracks.setText("Build Barracks");
		buildBarracks.setMargin(new Insets(0, 0, 0, 0));
		Launcher.setComponent(buildBarracks, 675+450, 475, 130, 75, false);
		this.add(buildBarracks);
		
		JLabel barracksCost = new JLabel("Cost: 2000");
		barracksCost.setFont(new Font("Ariel", Font.BOLD, 14));
		Launcher.setComponent(barracksCost, 725+460, 520, 80, 80, true);
		this.add(barracksCost);
		
		JButton buildStable = new JButton();
		buildStable.setText("Build Stable");
		buildStable.setMargin(new Insets(0, 0, 0, 0));
		Launcher.setComponent(buildStable, 675+450, 580, 130, 75, false);
		this.add(buildStable);
		
		JLabel stableCost = new JLabel("Cost: 2500");
		stableCost.setFont(new Font("Ariel", Font.BOLD, 14));
		Launcher.setComponent(stableCost, 725+460, 625, 80, 80, true);
		this.add(stableCost);
		
		JLabel defendingArmyLabel = new JLabel();
		defendingArmyLabel.setText("Defending Army");
		defendingArmyLabel.setFont(new Font("Ariel", Font.BOLD, 20));
		Launcher.setComponent(defendingArmyLabel, 260, 100, 200, 80, true);
		this.add(defendingArmyLabel);
		
		JPanel defendingArmyPanel = new JPanel();
		JScrollPane scrollableDefendingArmyPanel = new JScrollPane(defendingArmyPanel);
		scrollableDefendingArmyPanel.setBackground(Color.RED);
		defendingArmyPanel.setLayout(new GridLayout(3,3,10,10));
//		defendingArmyPanel.add(new DefendingArmyComponent("Archer",1));
//		defendingArmyPanel.add(new DefendingArmyComponent("Archer",1));
//		defendingArmyPanel.add(new DefendingArmyComponent("Archer",1));
//		defendingArmyPanel.add(new DefendingArmyComponent("Archer",1));
//		defendingArmyPanel.add(new DefendingArmyComponent("Archer",1));
//		defendingArmyPanel.add(new DefendingArmyComponent("Archer",1));	
		scrollableDefendingArmyPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollableDefendingArmyPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Launcher.setComponent(scrollableDefendingArmyPanel, 0, 162, 550, 230, false);
		this.add(scrollableDefendingArmyPanel);
		
		JLabel buildingsLabel = new JLabel();
		buildingsLabel.setText("Economic Buildings");
		buildingsLabel.setFont(new Font("Ariel", Font.BOLD, 20));
		Launcher.setComponent(buildingsLabel, 500+330, 100, 200, 80, true);
		this.add(buildingsLabel);
		
		JPanel buildingsPanel = new JPanel();
		JScrollPane scrollableBuildingsPanel = new JScrollPane(buildingsPanel);
		scrollableBuildingsPanel.setBackground(Color.BLUE);
		buildingsPanel.setLayout(new GridLayout(3,3,5,5));
//		buildingsPanel.add(new EconomicalBuildingComponent("Farm",1));
//		buildingsPanel.add(new EconomicalBuildingComponent("Farm",1));
//		buildingsPanel.add(new EconomicalBuildingComponent("Farm",1));
//		buildingsPanel.add(new EconomicalBuildingComponent("Farm",1));
//		buildingsPanel.add(new EconomicalBuildingComponent("Farm",1));
//		buildingsPanel.add(new EconomicalBuildingComponent("Farm",1));
//		buildingsPanel.add(new EconomicalBuildingComponent("Farm",1));
//		buildingsPanel.add(new EconomicalBuildingComponent("Farm",1));
		scrollableBuildingsPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollableBuildingsPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Launcher.setComponent(scrollableBuildingsPanel, 345+220, 162, 550, 230, false);
		this.add(scrollableBuildingsPanel);
		
		JLabel stationedArmiesLabel = new JLabel();
		stationedArmiesLabel.setText("Stationed Armies");
		stationedArmiesLabel.setFont(new Font("Ariel", Font.BOLD, 20));
		Launcher.setComponent(stationedArmiesLabel, 260, 315+30+30, 200, 80, true);
		this.add(stationedArmiesLabel);
		
		JPanel stationedArmiesPanel = new JPanel();
		JScrollPane scrollableStationedArmiesPanel = new JScrollPane(stationedArmiesPanel);
		scrollableStationedArmiesPanel.setBackground(Color.YELLOW);
		stationedArmiesPanel.setLayout(new GridLayout(0,1,5,5));
//		stationedArmiesPanel.add(new StationedArmyComponent(1));
//		stationedArmiesPanel.add(new StationedArmyUnitComponent("Archer",1));
//		stationedArmiesPanel.add(new StationedArmyUnitComponent("Archer",1));
//		stationedArmiesPanel.add(new StationedArmyUnitComponent("Archer",1));
//		stationedArmiesPanel.add(new StationedArmyUnitComponent("Archer",1));
//		stationedArmiesPanel.add(new StationedArmyComponent(2));
//		stationedArmiesPanel.add(new StationedArmyUnitComponent("Archer",1));
//		stationedArmiesPanel.add(new StationedArmyUnitComponent("Archer",1));
//		stationedArmiesPanel.add(new StationedArmyUnitComponent("Archer",1));
//		stationedArmiesPanel.add(new StationedArmyUnitComponent("Archer",1));
		scrollableStationedArmiesPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollableStationedArmiesPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Launcher.setComponent(scrollableStationedArmiesPanel, 0, 375+30+40, 550, 230, false);
		this.add(scrollableStationedArmiesPanel);
		
		JLabel militaryBuildingsLabel = new JLabel();
		militaryBuildingsLabel.setText("Military Buildings");
		militaryBuildingsLabel.setFont(new Font("Ariel", Font.BOLD, 20));
		Launcher.setComponent(militaryBuildingsLabel, 500+330, 315+30+30, 200, 80, true);
		this.add(militaryBuildingsLabel);
		
		JPanel militaryBuildingsPanel = new JPanel();
		JScrollPane scrollableMilitaryBuildingsPanel = new JScrollPane(militaryBuildingsPanel);
		militaryBuildingsPanel.setLayout(new GridLayout(3,3,5,5));
//		militaryBuildingsPanel.add(new MilitaryBuildingComponent("Stable", 2));
//		militaryBuildingsPanel.add(new MilitaryBuildingComponent("Stable", 2));
//		militaryBuildingsPanel.add(new MilitaryBuildingComponent("Stable", 2));
//		militaryBuildingsPanel.add(new MilitaryBuildingComponent("Stable", 2));
		scrollableMilitaryBuildingsPanel.setBackground(Color.GREEN);
		scrollableMilitaryBuildingsPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollableMilitaryBuildingsPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Launcher.setComponent(scrollableMilitaryBuildingsPanel, 345+220, 375+30+40, 550, 230, false);
		this.add(scrollableMilitaryBuildingsPanel);
		
		
		//functionality 
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				Launcher.initialiseWorldMap();
			}
		});
		
		nextTurn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				game.endTurn();
				Launcher.initialiseCityView(c);
			}
		});
		
		
		for(EconomicBuilding bu : c.getEconomicalBuildings()) {
			buildingsPanel.add(new EconomicalBuildingComponent(bu.getClass().getSimpleName(),bu.getLevel(),bu,c));
		}
		
		for(MilitaryBuilding mu : c.getMilitaryBuildings()) {
			militaryBuildingsPanel.add(new MilitaryBuildingComponent(mu.getClass().getSimpleName(), mu.getLevel(),mu,c));	
		}
		
		for(Unit du : c.getDefendingArmy().getUnits()) {			
			 defendingArmyPanel.add(new DefendingArmyComponent(du.getClass().getSimpleName(),du.getLevel(),du,c,this));	
			
		}
		
		for(Army ar : player.getControlledArmies()) {
			stationedArmiesPanel.add(new StationedArmyComponent(ar.getName()));
			for(Unit au : ar.getUnits()) {
				stationedArmiesPanel.add(new StationedArmyUnitComponent(au.getClass().getSimpleName(),au.getLevel(), au, c));
			}
		}
		
		buildFarm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					player.build("Farm", c.getName());
					Launcher.initialiseCityView(c);
				}
				catch (Exception excep) {
					JOptionPane.showMessageDialog(null, "Not enough gold to build");
				}
			}
		});
		
		buildMarket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					player.build("Market", c.getName());
					Launcher.initialiseCityView(c);
				}
				catch (Exception excep) {
					JOptionPane.showMessageDialog(null, "Not enough gold to build");
				}
			}
		});
		
		buildArcheryRange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					player.build("ArcheryRange", c.getName());
					Launcher.initialiseCityView(c);
				}
				catch (Exception excep) {
					JOptionPane.showMessageDialog(null, "Not enough gold to build");
				}
			}
		});
		
		buildBarracks.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					player.build("Barracks", c.getName());
					Launcher.initialiseCityView(c);
				}
				catch (Exception excep) {
					JOptionPane.showMessageDialog(null, "Not enough gold to build");
				}
			}
		});
		
		buildStable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					player.build("Stable", c.getName());
					Launcher.initialiseCityView(c);
				}
				catch (Exception excep) {
					JOptionPane.showMessageDialog(null, "Not enough gold to build");
				}
			}
		});
			
	}
	
	public CityView getCityView() {
		return this;
	}

}
