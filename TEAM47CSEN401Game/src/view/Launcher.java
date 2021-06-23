package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import engine.*;

public class Launcher {
	private static int xRes = 800;
	private static int yRes = 600;
	private static int midRes = xRes / 2;
	private static JFrame window;
	private static Game game;
	private static Player player;
	private static FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);

	public static void setComponent(Component a, int x, int y, int width, int height, Boolean centered) {
		if (a.getClass().getName().contains("JLabel")) {
			JLabel z = (JLabel) a;
			if(centered) a.setLocation(x - ((int) (z.getFont().getStringBounds(z.getText(), frc).getWidth())) / 2, y);
			else a.setLocation(x, y);
			a.setSize(width, height);
		}
		else{
			if(centered) a.setLocation(x - width/2, y);
			else a.setLocation(x, y);
			a.setSize(width, height);
		}
	}

	public static void main(String args[]) {
		window = new JFrame();
		window.setTitle("Team 47 Game");
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Insets insets = window.getInsets();
		window.setSize(xRes + insets.left + insets.right, yRes + insets.top + insets.bottom);
		initialiseStartScreen();
		window.setVisible(true);
	}

	public static void initialiseStartScreen() {
		window.getContentPane().removeAll();
		window.add(new StartScreen());
		window.revalidate();
		window.repaint();
	}

	public static void initialiseWorldMap(String playerName) {
		window.getContentPane().removeAll();
		window.add(new WorldMap(playerName));
		window.revalidate();
		window.repaint();
	}
	
	public static void initialiseBattleView(City city) {
		window.getContentPane().removeAll();
		window.add(new BattleView(city));
		window.revalidate();
		window.repaint();
	}
	
	public static void initialiseArmyListview() {
		window.getContentPane().removeAll();
		window.add(new ArmyListview());
		window.revalidate();
		window.repaint();
	}
	

	public static Game getGame() {
		return game;
	}

	public static void setGame(Game inGame) {
		game = inGame;
	}

	public static Player getPlayer() {
		return player;
	}

	public static void setPlayer(Player p) {
		player = p;
	}

	public static void initialiseCityView(City c) {
		window.getContentPane().removeAll();
		window.add(new CityView(c));
		window.revalidate();
		window.repaint();
	}

}
