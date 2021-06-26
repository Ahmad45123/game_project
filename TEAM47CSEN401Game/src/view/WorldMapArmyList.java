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
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setLayout(new GridLayout(0, 1));;

		for (int i = 0; i < ala.size(); i++) {
			WorldMapArmySubComponent wsc = new WorldMapArmySubComponent(ala.get(i), wm);
			this.add(wsc);
		}
	}

	void setChoice(Army a) {

		wm.chooseArmy(a);
	}

}
