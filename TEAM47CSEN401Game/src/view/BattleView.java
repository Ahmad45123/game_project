package view;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import javax.swing.*;

import engine.*;


public class BattleView extends JPanel {
	// todo universal const for res
	private int xRes = 800;
	private int yRes = 600;
	private int midRes = xRes / 2;
	
	
	public BattleView(City city) {
		Game game = Launcher.getGame();
		Player player = Launcher.getPlayer();

		this.setLayout(null);
		Insets insets = this.getInsets();
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		
		JLabel cityName = new JLabel();
		cityName.setText(city.getName() + "battle");
		cityName.setFont(new Font("Ariel", Font.BOLD, 28));
		int labelXOffset = midRes;
		int labelYOffset = 30;
		Launcher.setComponent(cityName, labelXOffset, labelYOffset, 150, 50, true);
		this.add(cityName);
	}
}
