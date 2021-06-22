package view;

import javax.swing.*;

import engine.Game;
import engine.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class WorldMap extends JPanel {
	private int xRes = 800;
	private int yRes = 600;
	private int midRes = xRes / 2;

	public WorldMap(String playerName) {
		Game game = Launcher.getGame();
		Player player = Launcher.getPlayer();

		this.setLayout(null);
		Insets insets = this.getInsets();
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);

		JLabel worldMap = new JLabel();
		worldMap.setText("World Map");
		worldMap.setFont(new Font("Ariel", Font.BOLD, 28));
		int labelXOffset = midRes;
//				- ((int) (worldMap.getFont().getStringBounds(worldMap.getText(), frc).getWidth())) / 2;
		int labelYOffset = 30;
		Launcher.setComponent(worldMap, labelXOffset, labelYOffset, 150, 50,true);
		this.add(worldMap);

		JLabel playerNameLabel = new JLabel();
		playerNameLabel.setText("Player Name: " + player.getName());
		playerNameLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(playerNameLabel, 5, 0, 300, 900,false);
		this.add(playerNameLabel);

		JLabel turnCountLabel = new JLabel();
		turnCountLabel.setText("Turn Count: " + game.getCurrentTurnCount());
		turnCountLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(turnCountLabel, 5, 0, 300, 950,false);
		this.add(turnCountLabel);

		JLabel foodCountLabel = new JLabel();
		foodCountLabel.setText("Food: " + Math.round(player.getFood() * 10) / 10.0);
		foodCountLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(foodCountLabel, 5, 0, 300, 1000,false);
		this.add(foodCountLabel);

		JLabel goldCountLabel = new JLabel();
		goldCountLabel.setText("Gold: " + Math.round(player.getTreasury() * 10) / 10.0);
		goldCountLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(goldCountLabel, 5, 0, 300, 1050,false);
		this.add(goldCountLabel);

	}

}
