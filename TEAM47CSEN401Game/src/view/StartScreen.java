package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import engine.*;

public class StartScreen extends JPanel {
	private int xRes = 1280;
	private int yRes = 720;
	private int midRes = xRes / 2;

	public StartScreen() {
		// JScrollPane scrollableMainPanel = new JScrollPane(mainPanel);
		this.setLayout(null);

		// Get string in a certain font's pixel width example

		// String s = "Hello World";
		// Font z = new Font("Ariel", Font.BOLD, 28);
		// FontRenderContext frc = new FontRenderContext(new AffineTransform(), true,
		// true);
		// int textwidth = (int)(z.getStringBounds(s, frc).getWidth());
		// int textheight = (int)(font.getStringBounds(text, frc).getHeight());

		JButton startButton = new JButton("Start");
		Launcher.setComponent(startButton, midRes , 480+50, 200, 50,true);
		this.add(startButton);

		JLabel startMenu = new JLabel();
		startMenu.setText("Start Menu");
		startMenu.setFont(new Font("Ariel", Font.BOLD, 28));
		Launcher.setComponent(startMenu, midRes, 30, 150, 50,true);
		this.add(startMenu);

		JLabel nameEntry = new JLabel();
		nameEntry.setText("Enter your name:");
		nameEntry.setFont(new Font("Ariel", Font.BOLD, 20));
		Launcher.setComponent(nameEntry, midRes, 30, 200, 230,true);
		this.add(nameEntry);

		JLabel cityEntry = new JLabel();
		cityEntry.setText("Choose your city:");
		cityEntry.setFont(new Font("Ariel", Font.BOLD, 20));
		Launcher.setComponent(cityEntry, midRes, 30, 200, 600,true);
		this.add(cityEntry);

		JTextField nameInput = new JTextField();
		Launcher.setComponent(nameInput, midRes, 180, 200, 30,true);
		this.add(nameInput);

		String[] cities = { "Rome", "Cairo", "Sparta" };
		JComboBox cityInput = new JComboBox(cities);
		Launcher.setComponent(cityInput, midRes, 360, 200, 30,true);
		this.add(cityInput);

		JLabel karimName = new JLabel();
		karimName.setText("Karim ElMosalamy 49-4884");
		karimName.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(karimName, 0, 30, 300, 880+270,false);
		this.add(karimName);

		JLabel ahmedName = new JLabel();
		ahmedName.setText("Ahmed Mamdooh 49-4934");
		ahmedName.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(ahmedName, 0, 30, 300, 930+270,false);
		this.add(ahmedName);

		JLabel noureldinName = new JLabel();
		noureldinName.setText("Noureldin Shaker 49-7911");
		noureldinName.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(noureldinName, 0, 30, 300, 980+270,false);
		this.add(noureldinName);

		// Smooth transition between start screen and world map and included error
		// functionality
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (nameInput.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Name field is empty");
				else {
					try {
						// initialize game and player instances in launcher
						Launcher.setGame(new Game(nameInput.getText(), (String) cityInput.getSelectedItem()));
						Launcher.setPlayer(Launcher.getGame().getPlayer());
						Launcher.initialiseWorldMap();
					} catch (Exception excep) {
						JOptionPane.showMessageDialog(null, "IOException while creating Game");
						System.out.println("exeption in StartScreen initilizer " + excep.toString());
					}

				}
			}
		});

	}

}