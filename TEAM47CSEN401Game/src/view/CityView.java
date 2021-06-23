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

public class CityView extends JPanel {
	private int xRes = 800;
	private int yRes = 600;
	private int midRes = xRes / 2;

	public CityView(City c) {
		this.setLayout(null);
		
		JLabel cityView = new JLabel();
		cityView.setText("City View");
		cityView.setFont(new Font("Ariel", Font.BOLD, 28));
		Launcher.setComponent(cityView, midRes-60, 15, 150, 50, true);
		this.add(cityView);
		
		JLabel playerNameLabel = new JLabel();
		playerNameLabel.setText("Player Name: testName");
		playerNameLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(playerNameLabel, 575-40, 0, 300, 25, false);
		this.add(playerNameLabel);

		JLabel turnCountLabel = new JLabel();
		turnCountLabel.setText("Turn Count: 50");
		turnCountLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(turnCountLabel, 575-40, 0, 300, 75, false);
		this.add(turnCountLabel);

		JLabel foodCountLabel = new JLabel();
		foodCountLabel.setText("Food: 50000");
		foodCountLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(foodCountLabel, 575-40, 0, 300, 125, false);
		this.add(foodCountLabel);

		JLabel goldCountLabel = new JLabel();
		goldCountLabel.setText("Gold: 50000");
		goldCountLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(goldCountLabel, 575-40, 0, 300, 175, false);
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
		
		JButton buildFarm = new JButton();
		buildFarm.setText("Build Farm");
		buildFarm.setMargin(new Insets(0, 0, 0, 0));
		Launcher.setComponent(buildFarm, 675, 120+10, 105, 80, false);
		this.add(buildFarm);
		
		JButton buildMarket = new JButton();
		buildMarket.setText("Build Market");
		buildMarket.setMargin(new Insets(0, 0, 0, 0));
		Launcher.setComponent(buildMarket, 675, 205+10, 105, 80, false);
		this.add(buildMarket);
		
		JButton buildArcheryRange  = new JButton();
		buildArcheryRange.setText("Build Arch-Range");
		buildArcheryRange.setMargin(new Insets(0, 0, 0, 0));
		Launcher.setComponent(buildArcheryRange, 675, 290+10, 105, 80, false);
		this.add(buildArcheryRange);
		
		JButton buildBarracks = new JButton();
		buildBarracks.setText("Build Barracks");
		buildBarracks.setMargin(new Insets(0, 0, 0, 0));
		Launcher.setComponent(buildBarracks, 675, 375+10, 105, 80, false);
		this.add(buildBarracks);
		
		JButton buildStable = new JButton();
		buildStable.setText("Build Stable");
		buildStable.setMargin(new Insets(0, 0, 0, 0));
		Launcher.setComponent(buildStable, 675, 460+10, 105, 80, false);
		this.add(buildStable);
		
		JLabel defendingArmyLabel = new JLabel();
		defendingArmyLabel.setText("Defending Army");
		defendingArmyLabel.setFont(new Font("Ariel", Font.BOLD, 20));
		Launcher.setComponent(defendingArmyLabel, 165, 100, 200, 80, true);
		this.add(defendingArmyLabel);
		
		JPanel defendingArmyPanel = new JPanel();
		JScrollPane scrollableDefendingArmyPanel = new JScrollPane(defendingArmyPanel);
		scrollableDefendingArmyPanel.setBackground(Color.RED);
		defendingArmyPanel.setLayout(new GridLayout(3,3,10,10));
		defendingArmyPanel.add(new DefendingArmyComponent("Archer",1));
		defendingArmyPanel.add(new DefendingArmyComponent("Archer",1));
		defendingArmyPanel.add(new DefendingArmyComponent("Archer",1));
		defendingArmyPanel.add(new DefendingArmyComponent("Archer",1));
		defendingArmyPanel.add(new DefendingArmyComponent("Archer",1));
		defendingArmyPanel.add(new DefendingArmyComponent("Archer",1));	
		scrollableDefendingArmyPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollableDefendingArmyPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Launcher.setComponent(scrollableDefendingArmyPanel, 10, 162, 325, 180, false);
		this.add(scrollableDefendingArmyPanel);
		
		JLabel buildingsLabel = new JLabel();
		buildingsLabel.setText("Buildings");
		buildingsLabel.setFont(new Font("Ariel", Font.BOLD, 20));
		Launcher.setComponent(buildingsLabel, 500, 100, 200, 80, true);
		this.add(buildingsLabel);
		
		JPanel buildingsPanel = new JPanel();
		JScrollPane scrollableBuildingsPanel = new JScrollPane(buildingsPanel);
		scrollableBuildingsPanel.setBackground(Color.BLUE);
		buildingsPanel.setLayout(new GridLayout(3,3,5,5));
		buildingsPanel.add(new EconomicalBuildingComponent("Farm",1));
		buildingsPanel.add(new EconomicalBuildingComponent("Farm",1));
		buildingsPanel.add(new EconomicalBuildingComponent("Farm",1));
		buildingsPanel.add(new EconomicalBuildingComponent("Farm",1));
		buildingsPanel.add(new EconomicalBuildingComponent("Farm",1));
		buildingsPanel.add(new EconomicalBuildingComponent("Farm",1));
		buildingsPanel.add(new EconomicalBuildingComponent("Farm",1));
		buildingsPanel.add(new EconomicalBuildingComponent("Farm",1));
		scrollableBuildingsPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollableBuildingsPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Launcher.setComponent(scrollableBuildingsPanel, 345, 162, 325, 180, false);
		this.add(scrollableBuildingsPanel);
		
		JLabel stationedArmiesLabel = new JLabel();
		stationedArmiesLabel.setText("Stationed Armies");
		stationedArmiesLabel.setFont(new Font("Ariel", Font.BOLD, 20));
		Launcher.setComponent(stationedArmiesLabel, 165, 315, 200, 80, true);
		this.add(stationedArmiesLabel);
		
		JPanel stationedArmiesPanel = new JPanel();
		JScrollPane scrollableStationedArmiesPanel = new JScrollPane(stationedArmiesPanel);
		scrollableStationedArmiesPanel.setBackground(Color.YELLOW);
		stationedArmiesPanel.setLayout(new GridLayout(0,1,5,5));
		stationedArmiesPanel.add(new StationedArmyComponent(1));
		stationedArmiesPanel.add(new StationedArmyUnitComponent("Archer",1));
		stationedArmiesPanel.add(new StationedArmyUnitComponent("Archer",1));
		stationedArmiesPanel.add(new StationedArmyUnitComponent("Archer",1));
		stationedArmiesPanel.add(new StationedArmyUnitComponent("Archer",1));
		stationedArmiesPanel.add(new StationedArmyComponent(2));
		stationedArmiesPanel.add(new StationedArmyUnitComponent("Archer",1));
		stationedArmiesPanel.add(new StationedArmyUnitComponent("Archer",1));
		stationedArmiesPanel.add(new StationedArmyUnitComponent("Archer",1));
		stationedArmiesPanel.add(new StationedArmyUnitComponent("Archer",1));
		scrollableStationedArmiesPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollableStationedArmiesPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Launcher.setComponent(scrollableStationedArmiesPanel, 10, 375, 325, 180, false);
		this.add(scrollableStationedArmiesPanel);
		
		JLabel militaryBuildingsLabel = new JLabel();
		militaryBuildingsLabel.setText("Military Buildings");
		militaryBuildingsLabel.setFont(new Font("Ariel", Font.BOLD, 20));
		Launcher.setComponent(militaryBuildingsLabel, 500, 315, 200, 80, true);
		this.add(militaryBuildingsLabel);
		
		JPanel militaryBuildingsPanel = new JPanel();
		JScrollPane scrollableMilitaryBuildingsPanel = new JScrollPane(militaryBuildingsPanel);
		militaryBuildingsPanel.setLayout(new GridLayout(3,3,5,5));
		militaryBuildingsPanel.add(new MilitaryBuildingComponent("Stable", 2));
		militaryBuildingsPanel.add(new MilitaryBuildingComponent("Stable", 2));
		militaryBuildingsPanel.add(new MilitaryBuildingComponent("Stable", 2));
		militaryBuildingsPanel.add(new MilitaryBuildingComponent("Stable", 2));
		scrollableMilitaryBuildingsPanel.setBackground(Color.GREEN);
		scrollableMilitaryBuildingsPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollableMilitaryBuildingsPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Launcher.setComponent(scrollableMilitaryBuildingsPanel, 345, 375, 325, 180, false);
		this.add(scrollableMilitaryBuildingsPanel);
		
		
		
	}

}
