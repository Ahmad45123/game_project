package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import engine.*;
import units.*;

public class Launcher {
	private static int xRes = 1280;
	private static int yRes = 720;
	private static int midRes = getxRes() / 2;
	private static JFrame window;
	private static Game game;
	private static Player player;
	private static FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);

	public static void setComponent(Component a, int x, int y, int width, int height, Boolean centered) {
		if (a.getClass().getName().contains("JLabel")) {
			JLabel z = (JLabel) a;
			if (centered)
				a.setLocation(x - ((int) (z.getFont().getStringBounds(z.getText(), frc).getWidth())) / 2, y);
			else
				a.setLocation(x, y);
			a.setSize(width, height);
		} else {
			if (centered)
				a.setLocation(x - width / 2, y);
			else
				a.setLocation(x, y);
			a.setSize(width, height);
		}
	}

	public static void main(String args[]) {
		window = new JFrame();
		window.setTitle("Team 47 Game");
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setSize(getxRes(), getyRes());
		initialiseStartScreen();
		window.setVisible(true);

	}

	public static void initialiseStartScreen() {
		window.getContentPane().removeAll();
		window.add(new StartScreen());
		window.revalidate();
		window.repaint();
	}

	public static void initialiseGameOverScreen() {
		window.getContentPane().removeAll();
		window.add(new GameOverView());
		window.revalidate();
		window.repaint();
	}

	public static void initialiseWorldMap() {
		boolean battleTime = false;
		boolean endTime = false;
		endTime = Launcher.getGame().isGameOver();
		for(City q : Launcher.getGame().getAvailableCities()) {
			boolean there = false;;
			for(Army a : Launcher.getPlayer().getControlledArmies()) {
				if(a.getCurrentLocation().toLowerCase().equals(q.getName().toLowerCase())) {
					there = true;
				}
			}
			if(there==false) {
				q.setTurnsUnderSiege(-1);
				q.setUnderSiege(false);
			}
			
		}
		
		for(City q : Launcher.getGame().getAvailableCities()) {
			if(q.isUnderSiege()==false&&q.getTurnsUnderSiege()==3) {
				battleTime = true;
			}
		}
		
		if(endTime) Launcher.initialiseGameOverScreen();
		else if(battleTime) {
			City defCity = new City("");
			for(City c : Launcher.getGame().getAvailableCities()) {
				if(c.isUnderSiege()==false&&c.getTurnsUnderSiege()==3) {
					defCity = c;
					break;
				}
			}
			Army attackArmy = new Army("");
			for(Army a : Launcher.getPlayer().getControlledArmies()) {
				if(a.getCurrentLocation().toLowerCase().equals(defCity.getName().toLowerCase())) {
					attackArmy = a;
					break;
				}
			}
			Army defArmy = defCity.getDefendingArmy();
			defCity.setTurnsUnderSiege(-1);
			Launcher.initialiseBattleView(attackArmy, defArmy);
		}
		else {
			window.getContentPane().removeAll();
			window.add(new WorldMap());
			window.revalidate();
			window.repaint();
			
		}
		
	}

	public static void initialiseCityView(City c) {
		boolean battleTime = false;
		boolean endTime = false;
		endTime = Launcher.getGame().isGameOver();
		for(City q : Launcher.getGame().getAvailableCities()) {
			if(q.isUnderSiege()==false&&q.getTurnsUnderSiege()==3) {
				battleTime = true;
			}
		}
		if(endTime) Launcher.initialiseGameOverScreen();
		else if(battleTime) {
			City defCity = new City("");
			for(City q : Launcher.getGame().getAvailableCities()) {
				if(q.isUnderSiege()==false&&q.getTurnsUnderSiege()==3) {
					defCity = q;
					break;
				}
			}
			Army attackArmy = new Army("");
			for(Army a : Launcher.getPlayer().getControlledArmies()) {
				if(a.getCurrentLocation().toLowerCase().equals(defCity.getName().toLowerCase())) {
					attackArmy = a;
					break;
				}
			}
			Army defArmy = defCity.getDefendingArmy();
			defCity.setTurnsUnderSiege(-1);
			Launcher.initialiseBattleView(attackArmy, defArmy);
		}
		else{
			window.getContentPane().removeAll();
			window.add(new CityView(c));
			window.revalidate();
			window.repaint();
		}
	}

	public static void initialiseBattleView(Army attackingArmy, Army defendingArmy) {
		window.getContentPane().removeAll();
		window.add(new BattleView(attackingArmy, defendingArmy));
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

	public static int getxRes() {
		return xRes;
	}

	public static int getyRes() {
		return yRes;
	}

	public static int getMidRes() {
		return midRes;
	}

	public static JFrame getWindow() {
		return window;
	}

}
