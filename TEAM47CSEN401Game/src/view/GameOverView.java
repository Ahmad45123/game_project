package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import engine.*;

public class GameOverView extends JPanel {
	private int xRes = Launcher.getxRes();
	private int yRes = Launcher.getyRes();
	private int midRes = Launcher.getMidRes();

	public GameOverView() {
		// JScrollPane scrollableMainPanel = new JScrollPane(mainPanel);
		boolean hasWon = false;
		if(Launcher.getPlayer().getControlledCities().size()==Launcher.getGame().getAvailableCities().size()) hasWon = true;
		this.setLayout(null);
		this.setBackground(Color.black);

		JLabel lbl = new JLabel();
		lbl.setFont(new Font("Comic Sans", Font.BOLD, 60));
		if (hasWon) {
			lbl.setText("You win!");
			lbl.setForeground(new Color(0, 255, 0));
		} else {
			lbl.setText("You lose!");
			lbl.setForeground(new Color(255, 0, 0));
		}
		Launcher.setComponent(lbl, midRes, 0, 400, 200, true);
		this.add(lbl);

		ImagePanel bg = new ImagePanel("src/imgs/game_over.jpg");
		Launcher.setComponent(bg, midRes, 0, 800, 600, true);
		this.add(bg);

	}
}
