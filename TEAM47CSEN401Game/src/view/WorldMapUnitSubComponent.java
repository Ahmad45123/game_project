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

		this.setLayout(new GridLayout(0,1,4,4));
		this.setBorder(BorderFactory.createLineBorder(Color.black));

		for (int i = 0; i < alu.size(); i++) {
			Unit u = alu.get(i);
			JLabel curr = new JLabel("Type: " + u.getClass().getSimpleName() + "  Lv. "+ u.getLevel()+"  Soldier Count: " + u.getCurrentSoldierCount() + "/" + u.getMaxSoldierCount());
			curr.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
			this.add(curr);
		}
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

	}

}
