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

	public ArmyListview() {

		Game game = Launcher.getGame();
		Player player = Launcher.getPlayer();

		this.setLayout(null);

		JTextArea textArea = new JTextArea();
		Launcher.setComponent(textArea, xRes / 2, 400 - 60, 400, 250, true);
		this.add(textArea);
		// controlled armies

		ArrayList<Army> controlledArmies = Launcher.getPlayer().getControlledArmies();

		DefaultListModel<String> l1 = new DefaultListModel<>();

		controlledArmies.add(0, null);
		l1.add(0, "controlled armies");

		for (int i = 1; i < controlledArmies.size(); i++) {
			Army a = controlledArmies.get(i);
			l1.add(i, a.getName());

		}

		JList<String> controlledArmiesList = new JList<>(l1);
		JScrollPane controlledArmiesListScroller = new JScrollPane(controlledArmiesList);
		controlledArmiesListScroller.setPreferredSize(new Dimension(250, 80));

		controlledArmiesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					Army a = controlledArmies.get(controlledArmiesList.getSelectedIndex());
					String armyInfo;

					// add all the army info you need :)

					if (a == null) {
						armyInfo = "select something :)";
					} else {
						armyInfo = extractArmyInfo(a);
					}

					textArea.setText(armyInfo);
				}
			}
		});
		
		controlledArmiesList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if(list.getSelectedIndex() == -1) {
		        	return;
		        }
		        
		        String armyInfo;
		        
		        Army a = controlledArmies.get(list.getSelectedIndex());
		        
		        if (a == null) {
					armyInfo = "select something :)";
				} else {
					armyInfo = extractArmyInfo(a);
				}
		        
		        textArea.setText(armyInfo);
		        
		    }
		});
		

		Launcher.setComponent(controlledArmiesListScroller, xRes / 2 + 20, 20, xRes / 2 - 40, 300, false);
		this.add(controlledArmiesListScroller);

		// armies in controlled cities

		ArrayList<City> controlledCities = Launcher.getPlayer().getControlledCities();
		ArrayList<Army> ownedArmiesArrayList = new ArrayList<>();
		DefaultListModel<String> l2 = new DefaultListModel<>();

		ownedArmiesArrayList.add(null);
		l2.add(0, "owned armies");

		for (int i = 0; i < controlledCities.size(); i++) {
			Army a = controlledCities.get(i).getDefendingArmy();

			assert (a != null);

			System.out.println(a.getName());

			ownedArmiesArrayList.add(a);
			l2.add(i + 1, a.getName());

		}

		JList<String> ownedArmiesList = new JList<>(l2);
		JScrollPane ownedArmiesListScroller = new JScrollPane(ownedArmiesList);
		ownedArmiesListScroller.setPreferredSize(new Dimension(250, 80));
		ownedArmiesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					Army a = ownedArmiesArrayList.get(ownedArmiesList.getSelectedIndex());
					String armyInfo;

					// add all the army info you need :)

					if (a == null) {
						armyInfo = "select something :)";
					} else {
						armyInfo = extractArmyInfo(a);
					}

					textArea.setText(armyInfo);
				}
			}
		});
		Launcher.setComponent(ownedArmiesListScroller, 20, 20, xRes / 2 - 40, 300, false);
		this.add(ownedArmiesListScroller);

	}
	
	public static String extractArmyInfo(Army a) {
		return "Name: " + a.getName() + "\n Target: " + a.getTarget();
	}
	

}
