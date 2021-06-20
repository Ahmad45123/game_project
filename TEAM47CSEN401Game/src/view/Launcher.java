package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;


public class Launcher {
	private static int xRes = 800;
	private static int yRes = 600;
	private static int midRes = xRes/2;
	private static JFrame window;
	
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
	
	public static void initialiseStartScreen(){
		window.getContentPane().removeAll();
		window.add(new StartScreen());
		window.revalidate();
		window.repaint();
	}
	
	public static void initialiseWorldMap(){
		window.getContentPane().removeAll();
		window.add(new WorldMap());
		window.revalidate();
		window.repaint();
	}
	
	

}
