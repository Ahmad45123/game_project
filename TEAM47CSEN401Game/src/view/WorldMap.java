package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;



public class WorldMap extends JPanel {
	private int xRes = 800;
	private int yRes = 600;
	private int midRes = xRes/2;
	
	public WorldMap(String playerName) {
		this.setLayout(null);
		Insets insets = this.getInsets();
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		
		
		JLabel worldMap = new JLabel();
		worldMap.setText("World Map");
		worldMap.setFont(new Font("Ariel", Font.BOLD, 28));
		int labelWidth = 150;
		int labelHeight = 50;
		int labelXOffset = midRes - ((int)(worldMap.getFont().getStringBounds(worldMap.getText(), frc).getWidth()))/2;
		int labelYOffset = 30;
		Launcher.setComponent(worldMap, labelXOffset, labelYOffset, labelWidth, labelHeight);
		this.add(worldMap);

		JLabel playerNameLabel = new JLabel();
		playerNameLabel.setText("Player Name: " + playerName);
		playerNameLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		int playerNameLabelWidth = 300;
		int playerNameLabelHeight = 880;
		int playerNameLabelXOffset = 0;
		int playerNameLabelYOffset = 30;
		Launcher.setComponent(playerNameLabel, playerNameLabelXOffset, playerNameLabelYOffset, playerNameLabelWidth ,playerNameLabelHeight);
		this.add(playerNameLabel);


		// JLabel playerNameLabel = new JLabel();
		// playerNameLabel.setText("Player Name: " + playerName);
		// playerNameLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		// int playerNameLabelWidth = 300;
		// int playerNameLabelHeight = 880;
		// int playerNameLabelXOffset = 0;
		// int playerNameLabelYOffset = 30;
		// int playerNameLabelWidthOffset = 0;
		// int playerNameLabelHeightOffset = 0;
		// Dimension playerNameLabelSize = new Dimension(playerNameLabelWidth,playerNameLabelHeight);
		// playerNameLabel.setBounds(playerNameLabelXOffset + insets.left, playerNameLabelYOffset + insets.top, playerNameLabelWidthOffset + playerNameLabelSize.width, playerNameLabelHeightOffset + playerNameLabelSize.height);
		// this.add(playerNameLabel);

		// JLabel playerNameLabel = new JLabel();
		// playerNameLabel.setText("Player Name: " + playerName);
		// playerNameLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		// int playerNameLabelWidth = 300;
		// int playerNameLabelHeight = 880;
		// int playerNameLabelXOffset = 0;
		// int playerNameLabelYOffset = 30;
		// int playerNameLabelWidthOffset = 0;
		// int playerNameLabelHeightOffset = 0;
		// Dimension playerNameLabelSize = new Dimension(playerNameLabelWidth,playerNameLabelHeight);
		// playerNameLabel.setBounds(playerNameLabelXOffset + insets.left, playerNameLabelYOffset + insets.top, playerNameLabelWidthOffset + playerNameLabelSize.width, playerNameLabelHeightOffset + playerNameLabelSize.height);
		// this.add(playerNameLabel);
		
		
		
		
		
	}
	
}
