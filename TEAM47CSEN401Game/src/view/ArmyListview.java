package view;

import javax.swing.*;
import javax.swing.event.*;

import engine.*;
import units.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class ArmyListview extends JPanel {
	// this is everywhere :(
	private int xRes = 800;
	private int yRes = 600;
	private int midRes = xRes / 2;

	private JTextArea textArea;

	private String currentArmyInfo, currentUnitInfo;
	private ArrayList<Unit> unitsArrayList;
	private JList<String> unitsList;
	private JScrollPane unitsListScroller;

	public ArmyListview() {

		Game game = Launcher.getGame();
		Player player = Launcher.getPlayer();

		this.setLayout(null);

		textArea = new JTextArea();
		Launcher.setComponent(textArea, xRes / 2, 400 - 60, 400, 250, true);
		this.add(textArea);
		// controlled armies

		ArrayList<Army> controlledArmies = Launcher.getPlayer().getControlledArmies();

		DefaultListModel<String> l1 = new DefaultListModel<>();

		ArrayList<Army> allArmies = new ArrayList<Army>();

		allArmies.add(null);
		l1.add(0, "controlled and defending armies");

		for (int i = 0; i < controlledArmies.size(); i++) {
			Army a = controlledArmies.get(i);
			assert (a != null);
			int ni = allArmies.size();
			l1.add(ni, a.getName());
			allArmies.add(a);
		}

		ArrayList<City> controlledCities = Launcher.getPlayer().getControlledCities();
		for (int i = 0; i < controlledCities.size(); i++) {
			Army a = controlledCities.get(i).getDefendingArmy();
			assert (a != null);
			l1.add(allArmies.size(), a.getName());
			allArmies.add(a);
		}

		updateUnitsArrayList(null);

		JList<String> allArmiesList = new JList<>(l1);
		JScrollPane allArmiesListScroller = new JScrollPane(allArmiesList);
		allArmiesListScroller.setPreferredSize(new Dimension(250, 80));

		allArmiesList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (list.getSelectedIndex() == -1) {
					return;
				}

				Army a = allArmies.get(list.getSelectedIndex());

				updateCurrentArmyInfo(a);
				if (a == null)
					updateUnitsArrayList(null);
				else
					updateUnitsArrayList(a.getUnits());

			}
		});

		Launcher.setComponent(allArmiesListScroller, xRes / 2 + 20, 20, xRes / 2 - 40, 300, false);
		this.add(allArmiesListScroller);

	}

	String extractArmyInfo(Army a) {
		return "Name: " + a.getName() + "\n Target: " + a.getTarget();
	}

	String extractUnitInfo(Unit a) {
		return "level: " + a.getLevel() + "\n current soilder count: " + a.getCurrentSoldierCount();
	}

	void updateCurrentArmyInfo(Army a) {

		currentUnitInfo = "";
		if (a == null)
			currentArmyInfo = "select something :)";
		else
			currentArmyInfo = extractArmyInfo(a);

		updateTextArea();
	}

	void updateCurrentUnitInfo(Unit a) {
		currentUnitInfo = extractUnitInfo(a);
		updateTextArea();
	}

	void updateTextArea() {
		textArea.setText("Army:\n" + currentArmyInfo + "\n\n Unit: \n\n" + currentUnitInfo);
	}

	void updateUnitsArrayList(ArrayList<Unit> a) {
		if (a == null)
			a = new ArrayList<Unit>();

		unitsArrayList = a;
		String[] temp = new String[a.size()];
		for (int i = 0; i < a.size(); i++) {
			Unit b = a.get(i);
			temp[i] = b.getClass().getName() + " lvl " + b.getLevel();
		}
		unitsList = new JList<>(temp);
		if (unitsListScroller != null)
			this.remove(unitsListScroller);
		unitsListScroller = new JScrollPane(unitsList);
		unitsListScroller.setPreferredSize(new Dimension(250, 80));

		unitsList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (list.getSelectedIndex() == -1) {
					return;
				}

				Unit a = unitsArrayList.get(list.getSelectedIndex());

				updateCurrentUnitInfo(a);

			}
		});

		Launcher.setComponent(unitsListScroller, 20, 20, xRes / 2 - 40, 300, false);
		this.add(unitsListScroller);

	}
}
