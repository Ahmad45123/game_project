package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import engine.*;

public class GameOverView extends JPanel {
	private int xRes = 800;
	private int yRes = 600;
	private int midRes = xRes / 2;

	
	public GameOverView(boolean hasWon) {
		// JScrollPane scrollableMainPanel = new JScrollPane(mainPanel);
		this.setLayout(null);

		JLabel lbl = new JLabel();
		lbl.setFont(new Font("Comic Sans", Font.BOLD, 60));
		if(hasWon) {
			lbl.setText("You win!");
			lbl.setForeground(new Color(0, 255, 0));
		} else {
			lbl.setText("You lose!");
			lbl.setForeground(new Color(255, 0, 0));
		}		
		Launcher.setComponent(lbl, midRes, 0, 400, 200, true);
		this.add(lbl);
		
		ImagePanel bg = new ImagePanel("src/imgs/game_over.jpg");
		Launcher.setComponent(bg, midRes, 0, 800, 600,true);
		this.add(bg);
		
		
	}
}
