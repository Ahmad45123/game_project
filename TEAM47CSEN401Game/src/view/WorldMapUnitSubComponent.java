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

public class WorldMapUnitSubComponent extends JPanel {

	ArrayList<Unit> alu;

	WorldMap worldmap;
	Game game;
	Player player;

	public WorldMapUnitSubComponent(ArrayList<Unit> alu, WorldMap wm) {
		this.alu = alu;
		worldmap = wm;

		game = Launcher.getGame();
		player = Launcher.getPlayer();

		this.setLayout(new GridLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black));
//		this.add(new JLabel("Name: " + c.getName()), BorderLayout.CENTER);

//		this.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));

		for (int i = 0; i < alu.size(); i++) {
			Unit u = alu.get(i);
			this.add(new JLabel(
					"type: " + u.getClass().getName().substring(6) + "\ncnt: " + u.getCurrentSoldierCount() + "."));
		}

	}

}
