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
import java.util.ArrayList;

import engine.*;
import javax.swing.border.CompoundBorder;

public class WorldMapArmyList extends JPanel {

	Game game;
	Player player;
	WorldMap wm;

	public WorldMapArmyList(ArrayList<Army> ala, WorldMap wm) {
		this.wm = wm;
		game = Launcher.getGame();
		player = Launcher.getPlayer();

//		this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setLayout(new GridLayout(2, 2));
//		this.add(new JLabel("Name: " + c.getName()), BorderLayout.CENTER);

//		this.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));

		for (int i = 0; i < ala.size(); i++) {
			WorldMapArmySubComponent wsc = new WorldMapArmySubComponent(ala.get(i), wm);
//			Launcher.setComponent(wsc, 0, y, width, height, centered);
//			System.out.println("added componenet");
			this.add(wsc);
		}
	}

	void setChoice(Army a) {

		wm.chooseArmy(a);
	}

}
