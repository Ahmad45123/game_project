package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.*;

import engine.*;
import units.*;

public class PreBattleView extends JPanel {
	// todo universal const for res
	private int xRes = Launcher.getxRes();
	private int yRes = Launcher.getyRes();
	private int midRes = Launcher.getMidRes();
	private JPanel armiesPanel;
	private JScrollPane scrollableArmiesPanel;
	City city;

	public PreBattleView(City city) { // given city is not controlled by player
		Game game = Launcher.getGame();
		Player player = Launcher.getPlayer();
		this.city = city;

		this.setLayout(null);
		Insets insets = this.getInsets();
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);

		JLabel cityName = new JLabel();
		cityName.setText(city.getName() + "prebattle");
		cityName.setFont(new Font("Ariel", Font.BOLD, 28));
		int labelXOffset = midRes;
		int labelYOffset = 30;
		Launcher.setComponent(cityName, labelXOffset, labelYOffset, 250, 80, true);
		this.add(cityName);

		ArrayList<Army> aoi = new ArrayList<Army>();
		for (Army a : player.getControlledArmies()) {
			assert (a != city.getDefendingArmy());
			if (
//					a.getTarget().contentEquals(city.getName())||
			(a.getCurrentLocation().toLowerCase().contentEquals(city.getName().toLowerCase()))) {
				aoi.add(a);
			}
		}

		boolean siege = false;
		for (Army a : aoi) {
			if (a.getCurrentStatus() == Status.BESIEGING) {
				siege = true;
			}
		}

		armiesPanel = new JPanel();
		scrollableArmiesPanel = new JScrollPane(armiesPanel);
		scrollableArmiesPanel.setBackground(siege ? Color.BLUE : Color.BLACK);
		armiesPanel.setLayout(new GridLayout(3, 3, 5, 5));
//		armiesPanel.add(new EconomicalBuildingComponent("Farm", 1));

		for (Army a : aoi) {
			armiesPanel.add(
					new ArmyPreBattleComponent(a, this, !siege || a.getCurrentStatus() == Status.BESIEGING, !siege));
		}

		scrollableArmiesPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		scrollableArmiesPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Launcher.setComponent(scrollableArmiesPanel, 125, 150, 550, 400, false);
		this.add(scrollableArmiesPanel);

		JButton backButton = new JButton();
		backButton.setText("Back to World Map");
		Launcher.setComponent(backButton, 20, 5, 175, 62, false);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Launcher.initialiseWorldMap();
			}
		});

		this.add(backButton);

	}

	public void attack(Army a) {
		// move to battle view
		System.out.println("attack army called");
//		Launcher.initialiseBattleView(city,a);
	}

	public void siege(Army a) {
		try {
			Launcher.getPlayer().laySiege(a, city);
		} catch (Exception excep) {
			assert (false);
			// should never happen
			// only enemy city and friendly armies should appear in this view
		}
		System.out.println("siege called");
		Launcher.initialisePreBattleView(city);
	}
}
